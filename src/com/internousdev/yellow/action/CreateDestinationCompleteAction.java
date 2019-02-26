package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.DestinationInfoDAO;
import com.internousdev.yellow.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationCompleteAction extends ActionSupport implements SessionAware
{
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String email;
	private String tellNumber;
	private String userAddress;
	private Map<String, Object> session;
	private List<String> errorMsgList;
	private List<String> familyNameMsgList;
	private List<String> firstNameMsgList;
	private List<String> familyNameKanaMsgList;
	private List<String> firstNameKanaMsgList;
	private List<String> emailMsgList;
	private List<String> tellNumberMsgList;
	private List<String> userAddressMsgList;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		DestinationInfoDAO destinationInfoDao = new DestinationInfoDAO();

		InputChecker inputChecker = new InputChecker();

		errorMsgList = new ArrayList<String>();

		familyNameMsgList = inputChecker.doCheck("姓",familyName,1,16,true,true, true, false, false, false, false, false, false);
		firstNameMsgList = inputChecker.doCheck("名", firstName, 1, 16, true, true, true, false, false, true, false, false, false);
		familyNameKanaMsgList = inputChecker.doCheck("姓ふりがな", familyNameKana, 1, 16, false, false, true, false, false, false, false, false, false);
		firstNameKanaMsgList = inputChecker.doCheck("名ふりがな",  firstNameKana, 1, 16, false, false, true, false, false, false, false, false, false);
		emailMsgList = inputChecker.doCheck("メールアドレス", email, 10, 32, true, false, false, true, true, false, false, false, false);
		tellNumberMsgList = inputChecker.doCheck("電話番号", tellNumber, 10, 13, false, false, false, true, false, false, false, false, false);
		userAddressMsgList = inputChecker.doCheck("住所", userAddress, 10, 50, true, true, true, true, false, true, false, false, false);

        //	入力値チェックで、エラーがなかったら
        if(!familyNameMsgList.isEmpty()
        	||!firstNameMsgList.isEmpty()
        	||!familyNameKanaMsgList.isEmpty()
        	||!firstNameKanaMsgList.isEmpty()
        	||!emailMsgList.isEmpty()
        	||!tellNumberMsgList.isEmpty()
			||!userAddressMsgList.isEmpty())

			{
        	return ERROR;
        }

		int count = destinationInfoDao.insert(String.valueOf(session.get("loginId")), familyName, firstName, familyNameKana, firstNameKana, email, tellNumber, userAddress);
		if(count > 0)
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