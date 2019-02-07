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
		errorMsgList.addAll(inputChecker.doCheck("ユーザーID", loginId,1,8,true,false, false, true, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("現在のパスワード", password, 1, 16, true, false, false, true, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("新しいパスワード", newPassword, 1, 16, true, false, false, true, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("新しいパスワード(再確認)", reConfirmationPassword, 1, 16, true, false, false, true, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doPasswordCheck(newPassword, reConfirmationPassword));

        //	入力値チェックで、エラーがなかったら
        if(errorMsgList.isEmpty())
        {
		    UserInfoDAO userInfoDAO = new UserInfoDAO();

		    //	ユーザーが存在するのならば
			if(userInfoDAO.checkPassword(loginId, password))
			{
				concealedPassword = userInfoDAO.concealPassword(newPassword);
				session.put("loginId", loginId);
				session.put("newPassword", newPassword);

				return SUCCESS;
			}
			//	存在しないなら
			else
			{
				errorMsgList.add("ユーザーIDまたは現在のパスワードが異なります。");

		        return ERROR;
			}
	    }
        //	入力値チェックでエラーがあったら、それを表示させる
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
}
