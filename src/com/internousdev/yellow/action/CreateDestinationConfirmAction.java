package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationConfirmAction  extends ActionSupport implements SessionAware
{
	//	Receive + Send
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private List<String> sexList = new ArrayList<String>();
	private String sex;
	private static final String MALE = "男性";
	private static final String FEMALE= "女性";
	private String defaultSexValue = MALE;
	private String email;
	private String tellNumber;
	private String userAddress;

	//	Send
	private List<String> errorMsgList;

	//	Session
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	String result = ERROR;
		InputChecker inputChecker = new InputChecker();
		errorMsgList = new ArrayList<String>();
		errorMsgList.addAll(inputChecker.doCheck("姓", familyName, 1, 16, true, true, true, false, false, false, true, false, false));
		errorMsgList.addAll(inputChecker.doCheck("名", firstName, 1, 16, true, true, true, false, false, false, true, false, false));
		errorMsgList.addAll(inputChecker.doCheck("姓ふりがな", familyNameKana, 1, 16, false, false, true, false, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("名ふりがな", firstNameKana, 1, 16, false, false, true, false, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("住所", userAddress, 15, 50, false, true, true, true, true, true, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("電話番号", tellNumber, 10, 13, false, false, false, true, false, false, false, false, false));
		errorMsgList.addAll(inputChecker.doCheck("メールアドレス", email, 10, 32, true, false, false, true, true, false, false, false, false));

		sexList.add(MALE);
		sexList.add(FEMALE);

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
	public List<String> getSexList() {
		return sexList;
	}
	public void setSexList(List<String> sexList) {
		this.sexList = sexList;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDefaultSexValue() {
		return defaultSexValue;
	}
	public void setDefaultSexValue(String defaultSexValue) {
		this.defaultSexValue = defaultSexValue;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTellNumber() {
		return tellNumber;
	}
	public void setTellNumber(String tellNumber) {
		this.tellNumber = tellNumber;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
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
