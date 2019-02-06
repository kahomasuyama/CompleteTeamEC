package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dao.MCategoryDAO;
import com.internousdev.yellow.dto.CartInfoDTO;
import com.internousdev.yellow.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware
{

	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private Map<String,Object> session;

	public String execute()
	{
		//	商品カテゴリがないなら取得
		if(!session.containsKey("mCategoryDtoList"))
		{
			MCategoryDAO mcategoryDAO = new MCategoryDAO();
			List<MCategoryDTO> mCategoryDtoList = mcategoryDAO.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		String result=ERROR;
		String userId=null;
		CartInfoDAO cartInfoDao=new CartInfoDAO();
		List<CartInfoDTO> cartInfoDtoList=new ArrayList<CartInfoDTO>();

		session.remove("checkListErrorMessageList");

		if(session.containsKey("loginId"))
		{
			userId=String.valueOf(session.get("loginId"));
		}

		else if(session.containsKey("tempUserId")){
			userId=String.valueOf(session.get("tempUserId"));
		}

		cartInfoDtoList=cartInfoDao.getCartInfoDtoList(userId);
		Iterator<CartInfoDTO>iterator=cartInfoDtoList.iterator();
		if(!(iterator.hasNext()))
		{
			cartInfoDtoList=null;
		}
		session.put("cartInfoDtoList", cartInfoDtoList);

		int totalPrice=Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(userId)));
		session.put("totalPrice",totalPrice);
		result=SUCCESS;

		if(!session.containsKey("mCategoryDtoList"))
		{
			MCategoryDAO mCategoryDao=new MCategoryDAO();
			mCategoryDtoList=mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}
		return result;
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
