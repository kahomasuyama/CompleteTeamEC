package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dao.ProductInfoDAO;
import com.internousdev.yellow.dto.CartInfoDTO;
import com.internousdev.yellow.dto.ProductInfoDTO;
import com.internousdev.yellow.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware
{
	private int productId;
	private String productCount;

	private Map<String,Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//未ログイン時のときにtempUserIdの発行
		if(!(session.containsKey("loginId"))&& !(session.containsKey("tempUserId")))
		{
			CommonUtility commonUtility=new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}

		String userId = null;
		String tempUserId = null;
		if(session.containsKey("loginId"))
		{
			userId=String.valueOf(session.get("loginId"));
		}
		else
		{
			userId = String.valueOf(session.get("tempUserId"));
			tempUserId = String.valueOf(session.get("tempUserId"));
		}

		//	カートに入れる数
		int intProductCount = 0;
		try
		{
			intProductCount = Integer.parseInt(this.productCount);
		}
		catch (NumberFormatException e)
		{
			return ERROR;
		}
		boolean productCountInRange = (0 < intProductCount && intProductCount <= 5);

		//	商品情報取得
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		ProductInfoDTO productInfoDTO = productInfoDAO.getProductInfo(productId);
		boolean productInfoExists = (productInfoDTO != null);

		//	カートに追加できなかったら、エラーを返す
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		if(productCountInRange && productInfoExists && cartInfoDAO.addCart(userId, tempUserId, productId, Integer.parseInt(productCount), productInfoDTO.getPrice()))
		{
			//	カート情報取得
			List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
			cartInfoDtoList = cartInfoDAO.getCartInfoDtoList(userId);
			if(cartInfoDtoList.isEmpty())
			{
				cartInfoDtoList=null;
			}
			session.put("cartInfoDtoList",cartInfoDtoList);

			//	合計金額を取得
			int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(userId)));
			session.put("totalPrice", totalPrice);

			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
