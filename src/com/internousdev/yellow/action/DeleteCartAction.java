package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dto.CartInfoDTO;
import com.internousdev.yellow.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCartAction extends ActionSupport implements SessionAware
{
	private Collection<String> checkList;

	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();

	private Map<String, Object> session;
	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		String result=ERROR;
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		int count = 0;
		String userId = null;

		if(session.containsKey("loginId"))
		{
			userId = String.valueOf(session.get("loginId"));
		}

		else if (session.containsKey("tempUserId"))
		{
			userId = String.valueOf(session.get("tempUserId"));
		}

		for(String productId:checkList)
		{
			count += cartInfoDAO.delete(productId, userId);
		}

		if(count <= 0)
		{
			return ERROR;
		}
		else
		{
			List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
			cartInfoDtoList = cartInfoDAO.getCartInfoDtoList(userId);
			Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();

			if(!(iterator.hasNext()))
			{
				cartInfoDtoList = null;
			}
			session.put("cartInfoDtoList", cartInfoDtoList);

			int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(userId)));
			session.put("totalPrice", totalPrice);

			result=SUCCESS;
		}
		return result;
	}

	public List<MCategoryDTO> getmCategoryDtoList()
	{
		return mCategoryDtoList;
	}
	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList)
	{
		this.mCategoryDtoList = mCategoryDtoList;
	}
	public Collection<String> getCheckList()
	{
		return checkList;
	}
	public void setCheckList(Collection<String> checkList)
	{
		this.checkList = checkList;
	}
	public Map<String, Object> getSession()
	{
		return session;
	}
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}
}
