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

public class CreateUserConfirmAction extends ActionSupport implements SessionAware
{
	//	Receive + Send
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private String email;
	private String loginId;
	private String password;

	//	Send
	private List<String> errorMsgList = new ArrayList<String>();
	private List<String> sexList = new ArrayList<String>();

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

		//	入力値チェック
		InputChecker inputChecker = new InputChecker();
		errorMsgList.addAll(inputChecker.doCheck("姓", familyName, 1, 16, true, true, true, false, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("名", firstName, 1, 16, true, true, true, false, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("姓ふりがな", familyNameKana, 1, 16, false, false, true, false, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("名ふりがな", firstNameKana, 1, 16, false, false, true, false, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("メールアドレス", email, 10, 32, true, false, false, true, true, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("ログインID", loginId, 1, 8, true, false, false, true, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false));

		//	ユーザーIDの存在チェック
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		if(userInfoDAO.isExistsUserInfo(loginId))
		{
			errorMsgList.add("すでにユーザーIDが登録されている");
		}

		//	エラーがないのならば
		if(errorMsgList.isEmpty())
		{
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}

	public List<String> getSexList() {
		return sexList;
	}
	public void setSexList(List<String> sexList) {
		this.sexList = sexList;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFamilyNameKana() {
		return familyNameKana;
	}
	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}
	public String getFirstNameKana() {
		return firstNameKana;
	}
	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public List<String> getErrorMsgList() {
		return errorMsgList;
	}
	public void setErrorMsgList(List<String> errorMsgList) {
		this.errorMsgList = errorMsgList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
