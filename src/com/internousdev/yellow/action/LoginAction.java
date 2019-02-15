package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dao.UserInfoDAO;
import com.internousdev.yellow.dto.CartInfoDTO;
import com.internousdev.yellow.dto.UserInfoDTO;
import com.internousdev.yellow.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware
{
	//	Receive
	private String loginId;
	private String password;
	private boolean savedLoginId;

	//	Send
	private List<String> errorMsgList;
	private List<String> passwordErrorMsgList;
	private List<String> loginIdErrorMsgList;

	//	Session
	private Map<String,Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	ユーザーID保存
		session.put("savedLoginId", savedLoginId);
		session.put("saveLoginId",loginId);

		//文字種の判定
		InputChecker inputChecker = new InputChecker();
		errorMsgList = new ArrayList<String>();
		loginIdErrorMsgList = inputChecker.doCheck("ユーザーID", loginId, 1, 8, true, false, false, true, false, false, false, false, false);
		passwordErrorMsgList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);

		//	エラーメッセージがあるならば
		if(!loginIdErrorMsgList.isEmpty()
			|| !passwordErrorMsgList.isEmpty())
		{
			return ERROR;
		}

		//	DBにユーザーが存在しているかの確認
		UserInfoDAO userInfoDao = new UserInfoDAO();
		if(!userInfoDao.isExistsUserInfo(loginId))
		{
			errorMsgList.add("ユーザーIDまたはパスワードが異なります。");
			return ERROR;
		}

		if(userInfoDao.login(loginId,password) == 0)
		{
			errorMsgList.add("ユーザーIDまたはパスワードが異なります。");
			return ERROR;
		}

		UserInfoDTO userInfoDTO = userInfoDao.getUserInfo(loginId);
		session.put("loginId", userInfoDTO.getUserId());
		session.put("logined", 1);

		//カート情報をユーザーに紐付ける。
		CartInfoDAO cartInfoDao = new CartInfoDAO();
		if(cartInfoDao.linkToLoginId(String.valueOf(session.get("tempUserId")),loginId) == 0)
		{
			//home.jspへ飛ぶ
			return SUCCESS;
		}

		List<CartInfoDTO> cartInfoDtoList = cartInfoDao.getCartInfoDtoList(loginId);
		if(cartInfoDtoList.isEmpty())
		{
			cartInfoDtoList = null;
		}
		session.put("cartInfoDtoList", cartInfoDtoList);

		int totalPrice = Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(loginId)));
		session.put("totalPrice", totalPrice);

		return "cart";
	}

	public List<String> getErrorMsgList() {
		return errorMsgList;
	}
	public void setErrorMsgList(List<String> errorMsgList) {
		this.errorMsgList = errorMsgList;
	}
	public String getLoginId()
	{
		return loginId;
	}
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public boolean isSavedLoginId()
	{
		return savedLoginId;
	}
	public void setSavedLoginId(boolean savedLoginId)
	{
		this.savedLoginId = savedLoginId;
	}
	public Map<String, Object> getSession()
	{
		return session;
	}
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}

	public List<String> getLoginIdErrorMsgList()
	{
		return loginIdErrorMsgList;
	}

	public void setLoginIdErrorMsgList(List<String> loginIdErrorMsgList)
	{
		this.loginIdErrorMsgList = loginIdErrorMsgList;
	}

	public List<String> getPasswordErrorMsgList()
	{
		return passwordErrorMsgList;
	}

	public void setPasswordErrorMsgList(List<String> passwordErrorMsgList)
	{
		this.passwordErrorMsgList = passwordErrorMsgList;
	}
}
