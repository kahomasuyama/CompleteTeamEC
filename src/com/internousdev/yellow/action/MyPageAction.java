package com.internousdev.yellow.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.UserInfoDAO;
import com.internousdev.yellow.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware
{
	private Map<String, Object> session;
	private UserInfoDTO userInfoDTO;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		UserInfoDAO userInfoDAO = new UserInfoDAO();

		userInfoDTO = userInfoDAO.getUserInfo(String.valueOf(session.get("loginId")));


		return SUCCESS;
	}

	public Map<String, Object> getSession()
	{
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}

	public UserInfoDTO getUserInfoDTO()
	{
		return userInfoDTO;
	}

	public void setUserInfoDTO(UserInfoDTO userInfoDTO)
	{
		this.userInfoDTO = userInfoDTO;
	}
}