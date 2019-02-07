package com.internousdev.yellow.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport implements SessionAware
{
	private String categoryId;
	private Map<String,Object> session;
	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	セッション情報を初期化
		session.put("loginId", "");
		session.put("newPassword", "");

		return SUCCESS;
	}
	public String getCategoryId()
	{
		return categoryId;
	}
	public void setCategoryId(String categoryId)
	{
		this.categoryId = categoryId;
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
