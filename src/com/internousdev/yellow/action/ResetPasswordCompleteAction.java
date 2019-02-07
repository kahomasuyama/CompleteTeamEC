package com.internousdev.yellow.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware
{
	private String loginId;
	private String password;
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	DBのパスワードを更新
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		int count = userInfoDAO.resetPassword(String.valueOf(session.get("loginId")), String.valueOf(session.get("newPassword")));

		//	セッション情報を初期化
		session.put("loginId", "");
		session.put("newPassword", "");

		if (count > 0)
		{
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
