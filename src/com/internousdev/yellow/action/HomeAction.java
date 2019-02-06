package com.internousdev.yellow.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.MCategoryDAO;
import com.internousdev.yellow.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware
{
	//	Session
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないなら取得
		if(!session.containsKey("mCategoryDtoList"))
		{
			MCategoryDAO mcategoryDAO = new MCategoryDAO();
			List<MCategoryDTO> mCategoryDtoList = mcategoryDAO.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
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
