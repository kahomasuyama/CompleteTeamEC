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

	@SuppressWarnings("unchecked")
	public String execute()
	{
		//	商品カテゴリがないなら取得
		List<MCategoryDTO> mCategoryDtoList = null;
		if(session.containsKey("mCategoryDtoList"))
		{
			mCategoryDtoList = (List<MCategoryDTO>) session.get("mCategoryDtoList");
		}
		if(mCategoryDtoList == null || mCategoryDtoList.isEmpty())
		{
			MCategoryDAO mcategoryDAO = new MCategoryDAO();
			mCategoryDtoList = mcategoryDAO.getMCategoryList();
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
