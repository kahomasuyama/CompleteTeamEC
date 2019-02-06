package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.MCategoryDAO;
import com.internousdev.yellow.dao.UserInfoDAO;
import com.internousdev.yellow.dto.MCategoryDTO;
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
	private List<String> loginIdErrorMessageList = new ArrayList<String>();
	private List<String> passwordErrorMessageList = new ArrayList<String>();
	private List<String> passwordIncorrectErrorMessageList = new ArrayList<String>();
	private List<String> newPasswordErrorMessageList = new ArrayList<String>();
	private List<String> reConfirmationNewPasswordErrorMessageList = new ArrayList<String>();
	private List<String> newPasswordIncorrectErrorMessageList = new ArrayList<String>();

	//	Session
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

		session.remove("loginIdErrorMessageList");
		session.remove("passwordErrorMassageList");
		session.remove("passwordIncorrectErrorMasageList");
		session.remove("newPasswordErrormassageList");
		session.remove("reConfirmationNewPasswordErrorMessageList");
		session.remove("newPasswordIncorrectErrorMessageList");

		InputChecker IC = new InputChecker();

		loginIdErrorMessageList=IC.doCheck("ログインID", loginId,1,8,true,false, false, true, false, false, false, false, false);
		passwordErrorMessageList = IC.doCheck("現在のパスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);
        newPasswordErrorMessageList=IC.doCheck("新しいパスワード", newPassword, 1, 16, true, false, false, true, false, false, false, false, false);
        reConfirmationNewPasswordErrorMessageList=IC.doCheck("新しいパスワード(再確認)", reConfirmationPassword, 1, 16, true, false, false, true, false, false, false, false, false);
        //TODO
        newPasswordIncorrectErrorMessageList=IC.doPasswordCheck(newPassword, reConfirmationPassword);

        //	全てが空なら
        if(loginIdErrorMessageList.isEmpty()
        		&& passwordErrorMessageList.isEmpty()
        		&& newPasswordErrorMessageList.isEmpty()
        		&& reConfirmationNewPasswordErrorMessageList.isEmpty()
        		&& newPasswordIncorrectErrorMessageList.isEmpty())
        {
		    UserInfoDAO userInfoDAO = new UserInfoDAO();

			if(userInfoDAO.isExistsUserInfo(loginId, password))
			{
				String concealedPassword = userInfoDAO.concealPassword(password);
				session.put("loginId", loginId);
				session.put("newPassword", newPassword);
				session.put("concealedPassword", concealedPassword);

				return SUCCESS;
			}
			else
			{
				passwordIncorrectErrorMessageList.add("入力されたパスワードが異なります。");
				session.put("passwordIncorrectErrorMessageList", passwordIncorrectErrorMessageList);

		        return ERROR;
			}
	    }
        //	空でないなら
        else
	    {
			session.put("loginIdErrorMessageList",loginIdErrorMessageList);
			session.put("passwordErrorMassageList",passwordErrorMessageList );
			session.put("newPasswordErrormassageList",newPasswordErrorMessageList);
			session.put("reConfirmationNewPasswordErrorMessageList",reConfirmationNewPasswordErrorMessageList);
			session.put(" newPasswordIncorrectErrorMessageList", newPasswordIncorrectErrorMessageList);

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
	public List<String> getLoginIdErrorMessageList() {
		return loginIdErrorMessageList;
	}
	public void setLoginIdErrorMessageList(List<String> loginIdErrorMessageList) {
		this.loginIdErrorMessageList = loginIdErrorMessageList;
	}
	public List<String> getPasswordErrorMessageList() {
		return passwordErrorMessageList;
	}
	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
		this.passwordErrorMessageList = passwordErrorMessageList;
	}
	public List<String> getPasswordIncorrectErrorMessageList() {
		return passwordIncorrectErrorMessageList;
	}
	public void setPasswordIncorrectErrorMessageList(List<String> passwordIncorrectErrorMessageList) {
		this.passwordIncorrectErrorMessageList = passwordIncorrectErrorMessageList;
	}
	public List<String> getNewPasswordErrorMessageList() {
		return newPasswordErrorMessageList;
	}
	public void setNewPasswordErrorMessageList(List<String> newPasswordErrorMessageList) {
		this.newPasswordErrorMessageList = newPasswordErrorMessageList;
	}
	public List<String> getReConfirmationNewPasswordErrorMessageList() {
		return reConfirmationNewPasswordErrorMessageList;
	}
	public void setReConfirmationNewPasswordErrorMessageList(List<String> reConfirmationNewPasswordErrorMessageList) {
		this.reConfirmationNewPasswordErrorMessageList = reConfirmationNewPasswordErrorMessageList;
	}
	public List<String> getNewPasswordIncorrectErrorMessageList() {
		return newPasswordIncorrectErrorMessageList;
	}
	public void setNewPasswordIncorrectErrorMessageList(List<String> newPasswordIncorrectErrorMessageList) {
		this.newPasswordIncorrectErrorMessageList = newPasswordIncorrectErrorMessageList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
