package com.internousdev.yellow.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.MCategoryDAO;
import com.internousdev.yellow.dto.MCategoryDTO;
import com.internousdev.yellow.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware
{
	//	Session
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリを取得
		MCategoryDAO mcategoryDAO = new MCategoryDAO();
		List<MCategoryDTO >mCategoryDtoList = mcategoryDAO.getMCategoryList();
		session.put("mCategoryDtoList", mCategoryDtoList);

		//未ログイン時のときにtempUserIdの発行
		if(!(session.containsKey("loginId"))&& !(session.containsKey("tempUserId")))
		{
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}

		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
