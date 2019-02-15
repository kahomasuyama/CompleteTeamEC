package com.internousdev.yellow.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware
{
	private Map<String,Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		String userId = null;
		if(session.containsKey("loginId"))
		{
			userId = String.valueOf(session.get("loginId"));
		}
		else if(session.containsKey("tempUserId"))
		{
			userId = String.valueOf(session.get("tempUserId"));
		}

		CartInfoDAO cartInfoDao = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDtoList = cartInfoDao.getCartInfoDtoList(userId);
		if(cartInfoDtoList.isEmpty())
		{
			cartInfoDtoList = null;
		}
		session.put("cartInfoDtoList", cartInfoDtoList);

		int totalPrice = Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(userId)));
		session.put("totalPrice", totalPrice);

		return SUCCESS;
	}

	public Map<String,Object> getSession()
	{
		return session;
	}

	public void setSession(Map<String,Object> session)
	{
		this.session=session;
	}

}
