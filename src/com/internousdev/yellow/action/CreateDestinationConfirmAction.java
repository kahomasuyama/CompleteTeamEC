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
	private String email;
	private String tellNumber;
	private String userAddress;

	//	Send
	private List<String> errorMsgList;
	private List<String> familyNameErrorMsgList;
	private List<String> firstNameErrorMsgList;
	private List<String> familyNameKanaErrorMsgList;
	private List<String> firstNameKanaErrorMsgList;
	private List<String> emailErrorMsgList;
	private List<String> tellNumberErrorMsgList;
	private List<String> userAddressErrorMsgList;

	//	Session
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		InputChecker inputChecker = new InputChecker();
		errorMsgList = new ArrayList<String>();

		familyNameErrorMsgList = inputChecker.doCheck("姓", familyName, 1, 16, true, true, true, false, false, false, false, false, false);
		firstNameErrorMsgList = inputChecker.doCheck("名", firstName, 1, 16, true, true, true, false, false, false, false, false, false);
		familyNameKanaErrorMsgList = inputChecker.doCheck("姓ふりがな", familyNameKana, 1, 16, false, false, true, false, false, false, false, false, false);
		firstNameKanaErrorMsgList = inputChecker.doCheck("名ふりがな", firstNameKana, 1, 16, false, false, true, false, false, false, false, false, false);
		emailErrorMsgList = inputChecker.doCheck("住所", userAddress, 10, 50, true, true, true, true, false, true, false, false, false);
		tellNumberErrorMsgList = inputChecker.doCheck("電話番号", tellNumber, 10, 13, false, false, false, true, false, false, false, false, false);
		userAddressErrorMsgList = inputChecker.doCheck("メールアドレス", email, 10, 32, true, false, false, true, true, false, false, false, false);

		//	エラーがないのならば
				if(!familyNameErrorMsgList.isEmpty()
				|| !firstNameErrorMsgList.isEmpty()
			    || !familyNameKanaErrorMsgList.isEmpty()
			    || !firstNameKanaErrorMsgList.isEmpty()
			    || !emailErrorMsgList.isEmpty()
			    || !tellNumberErrorMsgList.isEmpty()
			    || !userAddressErrorMsgList.isEmpty())
				{
					return ERROR;
		}
		else
		{
			return SUCCESS;
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

	public List<String> getFamilyNameErrorMsgList() {
		return familyNameErrorMsgList;
	}
	public void setFamilyNameErrorMsgList(List<String> familyNameErrorMsgList) {
		this.familyNameErrorMsgList = familyNameErrorMsgList;
	}

	public List<String> getFirstNameErrorMsgList() {
		return firstNameErrorMsgList;
	}
	public void setFirstNameErrorMsgList(List<String> firstNameErrorMsgList) {
		this.firstNameErrorMsgList = firstNameErrorMsgList;
	}

	public List<String> getFamilyNameKanaErrorMsgList() {
		return familyNameKanaErrorMsgList;
	}
	public void setFamilyNameKanaErrorMsgList(List<String> familyNameKanaErrorMsgList) {
		this.familyNameKanaErrorMsgList = familyNameKanaErrorMsgList;
	}

	public List<String> getFirstNameKanaErrorMsgList() {
		return firstNameKanaErrorMsgList;
	}
	public void setFirstNameKanaErrorMsgList(List<String> firstNameKanaErrorMsgList) {
		this.firstNameKanaErrorMsgList = firstNameKanaErrorMsgList;
	}

	public List<String> getEmailErrorMsgList() {
		return emailErrorMsgList;
	}
	public void setEmailErrorMsgList(List<String> emailErrorMsgList) {
		this.emailErrorMsgList = emailErrorMsgList;
	}

	public List<String> getTellNumberErrorMsgList() {
		return tellNumberErrorMsgList;
	}
	public void setTellNumberErrorMsgList(List<String> tellNumberErrorMsgList) {
		this.tellNumberErrorMsgList = tellNumberErrorMsgList;
	}

	public List<String> getUserAddressErrorMsgList() {
		return userAddressErrorMsgList;
	}
	public void setUserAddressErrorMsgList(List<String> userAddressErrorMsgList) {
		this.userAddressErrorMsgList = userAddressErrorMsgList;
	}

}