package com.internousdev.yellow.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dao.DestinationInfoDAO;
import com.internousdev.yellow.dao.UserInfoDAO;
import com.internousdev.yellow.dto.DestinationInfoDTO;
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
		errorMsgList.addAll(inputChecker.doCheck("ユーザーID",loginId,1,8,true,false,false,true,false,false,false,false,false));
		errorMsgList.addAll(inputChecker.doCheck("パスワード",password,1,16,true,false,false,true,false,false,false,false,false));

		//	エラーメッセージがあるならば
		if(!errorMsgList.isEmpty())
		{
			return ERROR;
		}

		session.put("logined",0);

		//	DBにユーザーが存在しているかの確認
		UserInfoDAO userInfoDao = new UserInfoDAO();
		if(userInfoDao.isExistsUserInfo(loginId))
		{
			if(userInfoDao.login(loginId,password) > 0)
			{
				UserInfoDTO userInfoDTO = userInfoDao.getUserInfo(loginId);
				session.put("loginId", userInfoDTO.getUserId());
				int count = 0;
				CartInfoDAO cartInfoDao=new CartInfoDAO();

				count = cartInfoDao.linkToLoginId(String.valueOf(session.get("tempUserId")),loginId);
				if(count > 0)
				{
					DestinationInfoDAO destinationInfoDao=new DestinationInfoDAO();
					try
					{
						List<DestinationInfoDTO> destinationInfoDtoList = new  ArrayList<DestinationInfoDTO>();
						destinationInfoDtoList = destinationInfoDao.getDestinationInfo(loginId);
						Iterator<DestinationInfoDTO>iterator = destinationInfoDtoList.iterator();
						if(!(iterator.hasNext()))
						{
							destinationInfoDtoList=null;
						}
						session.put("destinationInfoDtoList",destinationInfoDtoList);
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					//cart.jspへ飛ぶ
					result="cart";
				}
				else
				{
					//home.jspへ飛ぶ
					result=SUCCESS;
				}
			}
			else
			{
				errorMsgList.add("ユーザーIDまたはパスワードが異なります。");
			}
			session.put("logined", 1);
		}
		else
		{
			errorMsgList.add("ユーザーIDまたはパスワードが異なります。");
		}
		return result;
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

	public List<String> getErrorMsgList()
	{
		return errorMsgList;
	}

	public void setErrorMsgList(List<String> errorMsgList)
	{
		this.errorMsgList = errorMsgList;
	}
}
