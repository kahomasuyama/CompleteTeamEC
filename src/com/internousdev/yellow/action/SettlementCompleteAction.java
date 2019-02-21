package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dao.PurchaseHistoryInfoDAO;
import com.internousdev.yellow.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware
{
    private String id;
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	ユーザーID
		String loginId = String.valueOf(session.get("loginId"));

		//	選択された宛先IDを購入履歴の宛先IDに代入
		int destinationId = Integer.valueOf(id);

		//	カートの中身を取得
		CartInfoDAO cartInfoDAO  = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDtoList = cartInfoDAO.getCartInfoDtoList(loginId);

		//	購入したカートのID
		List<Integer> idList = new ArrayList<Integer>();

		//	カートの中の商品を購入
		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		for (CartInfoDTO cartInfoDTO : cartInfoDtoList)
		{
			if(purchaseHistoryInfoDAO.regist(loginId, cartInfoDTO.getProductId(), cartInfoDTO.getProductCount(), destinationId, cartInfoDTO.getPrice()))
			{
				idList.add(cartInfoDTO.getId());
			}
		}

		//	カートの中身を削除
		cartInfoDAO.deleteAsId(idList);

		return SUCCESS;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
