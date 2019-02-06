package com.internousdev.yellow.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.MCategoryDAO;
import com.internousdev.yellow.dao.UserInfoDAO;
import com.internousdev.yellow.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;
public class LogoutAction extends ActionSupport implements SessionAware
{
	private Map<String,Object>session;

	public String execute()
	{
		String result=ERROR;

		UserInfoDAO userInfoDAO=new UserInfoDAO();
		String loginId=String.valueOf(session.get("loginId"));
		boolean savedLoginId=Boolean.valueOf(String.valueOf(session.get("savedLoginId")));
		int count=userInfoDAO.logout(loginId);
		if(count>0)
		{
			session.clear();
			if(savedLoginId)
			{
				session.put("savedLoginId", savedLoginId);
				session.put("loginId", loginId);

				result= SUCCESS;
			}
		}

		//	商品カテゴリがないなら取得
		if(!session.containsKey("mCategoryDtoList"))
		{
			MCategoryDAO mcategoryDAO = new MCategoryDAO();
			List<MCategoryDTO> mCategoryDtoList = mcategoryDAO.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		return result;
	}
	public Map<String,Object> getSession()
	{
		return session;
	}
	@Override
	public void setSession(Map<String,Object>session)
	{
		this.session=session;
	}
}
