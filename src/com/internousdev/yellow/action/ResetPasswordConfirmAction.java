package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.UserInfoDAO;
import com.internousdev.yellow.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware
{
	//	Receive
	private String loginId;
	private String password;
	private String newPassword;
	private String reConfirmationPassword;

	//	Send
	private String concealedPassword;
	private List<String> errorMsgList;
	private List<String> loginIdMsgList;
	private List<String> passwordMsgList;
	private List<String> newPasswordMsgList;
	private List<String> reConfirmationPasswordMsgList;

	//	Session
	private Map<String,Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	入力値チェック
		InputChecker inputChecker = new InputChecker();

		errorMsgList = new ArrayList<String>();

		loginIdMsgList = inputChecker.doCheck("ユーザーID", loginId,1,8,true,false, false, true, false, false, false, false, false);
		passwordMsgList = inputChecker.doCheck("現在のパスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);
		newPasswordMsgList = inputChecker.doCheck("新しいパスワード", newPassword, 1, 16, true, false, false, true, false, false, false, false, false);
		reConfirmationPasswordMsgList = inputChecker.doCheck("（再確認）", reConfirmationPassword, 1, 16, true, false, false, true, false, false, false, false, false);

        //	入力値チェックで、エラーがなかったら
        if(!loginIdMsgList.isEmpty()
        	||!passwordMsgList.isEmpty()
        	||!newPasswordMsgList.isEmpty()
        	||!reConfirmationPasswordMsgList.isEmpty())
        {
        	return ERROR;
        }

        // ユーザーIDの存在チェック
	    UserInfoDAO userInfoDAO = new UserInfoDAO();

		//	ユーザーIDとパスワードがないのならば
		if(!userInfoDAO.checkPassword(loginId, password))
		{
			errorMsgList.add("ユーザーIDまたは現在のパスワードが異なります。");
	        return ERROR;
		}

		//	パスワードが異なるならば
		errorMsgList = inputChecker.doPasswordCheck(newPassword, reConfirmationPassword);
		if(!errorMsgList.isEmpty())
		{
			return ERROR;
		}

		//	パスワードを一文字目のみ表示する
		StringBuilder stringBuilder = new StringBuilder("****************");
		concealedPassword = stringBuilder.replace(0, 1, newPassword.substring(0, 1)).toString();

		session.put("loginId", loginId);
		session.put("newPassword", newPassword);

		return SUCCESS;
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getReConfirmationPassword() {
		return reConfirmationPassword;
	}
	public void setReConfirmationPassword(String reConfirmationPassword) {
		this.reConfirmationPassword = reConfirmationPassword;
	}
	public String getConcealedPassword() {
		return concealedPassword;
	}
	public void setConcealedPassword(String concealedPassword) {
		this.concealedPassword = concealedPassword;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<String> getErrorMsgList() {
		return errorMsgList;
	}

	public void setErrorMsgList(List<String> errorMsgList) {
		this.errorMsgList = errorMsgList;
	}

	public List<String> getLoginIdMsgList() {
		return loginIdMsgList;
	}

	public void setLoginIdMsgList(List<String> loginIdMsgList) {
		this.loginIdMsgList = loginIdMsgList;
	}

	public List<String> getPasswordMsgList() {
		return passwordMsgList;
	}

	public void setPasswordMsgList(List<String> passwordMsgList) {
		this.passwordMsgList = passwordMsgList;
	}

	public List<String> getNewPasswordMsgList() {
		return newPasswordMsgList;
	}

	public void setNewPasswordMsgList(List<String> newPasswordMsgList) {
		this.newPasswordMsgList = newPasswordMsgList;
	}

	public List<String> getReConfirmationPasswordMsgList() {
		return reConfirmationPasswordMsgList;
	}

	public void setReConfirmationPasswordMsgList(List<String> reConfirmationPasswordMsgList) {
		this.reConfirmationPasswordMsgList = reConfirmationPasswordMsgList;
	}
}
