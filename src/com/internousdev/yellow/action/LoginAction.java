package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.Iterator;
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
	private Map<String,Object>session;

	public String execute()
	{
		String result=ERROR;

		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	ユーザーIDが保存されているか
		if(savedLoginId)
		{
			session.put("savedLoginId", true);
			session.put("loginId",loginId);
		}
		else
		{
			session.put("savedLoginId", false);
			session.put("loginId", loginId);
		}

		//文字種の判定
		InputChecker inputChecker=new InputChecker();
		errorMsgList = new ArrayList<String>();

		loginIdErrorMsgList = inputChecker.doCheck("ユーザーID",loginId,1,8,true,false,false,true,false,false,false,false,false);
		passwordErrorMsgList = inputChecker.doCheck("パスワード",password,1,16,true,false,false,true,false,false,false,false,false);

		//	エラーメッセージがあるならば
		if(!loginIdErrorMsgList.isEmpty()
			|| !passwordErrorMsgList.isEmpty())
		{
			return ERROR;
		}

		//	DBにユーザーが存在しているかの確認
		UserInfoDAO userInfoDao = new UserInfoDAO();
		if(userInfoDao.isExistsUserInfo(loginId))
		{
			int count = userInfoDao.login(loginId,password);
			if(count > 0)
			{
				UserInfoDTO userInfoDTO = userInfoDao.getUserInfo(loginId);
				session.put("loginId", userInfoDTO.getUserId());
				//カート情報をユーザーに紐付ける。
				CartInfoDAO cartInfoDao=new CartInfoDAO();
				count = cartInfoDao.linkToLoginId(String.valueOf(session.get("tempUserId")),loginId);

				if(count > 0)
				{
					//cart.jspへ飛ぶ
					result="cart";
					List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
					cartInfoDtoList = cartInfoDao.getCartInfoDtoList(loginId);
					Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();

					if(!(iterator.hasNext()))
					{
						cartInfoDtoList = null;
					}
					session.put("cartInfoDtoList", cartInfoDtoList);

					int totalPrice = Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(loginId)));
					session.put("totalPrice", totalPrice);

				}
				else
				{
					//home.jspへ飛ぶ
					result=SUCCESS;
				}
				session.put("logined", 1);
			}
			else
			{
				errorMsgList.add("ユーザーIDまたはパスワードが異なります。");
			}
		}
		else
		{
			errorMsgList.add("ユーザーIDまたはパスワードが異なります。");
		}
		return result;
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
