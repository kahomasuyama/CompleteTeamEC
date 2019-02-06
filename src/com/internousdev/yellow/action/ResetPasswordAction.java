package com.internousdev.yellow.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.MCategoryDAO;
import com.internousdev.yellow.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport implements SessionAware
{
	private String categoryId;
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

		String result=ERROR;
		session.remove("loginIdErrorMessageList");
		session.remove("passwordErrorMessageList");
		session.remove("passwordIncorrectErrorMessageList");
		session.remove("newPasswordErrorMessageList");
		session.remove("reConfirmationNewPasswordErrorMessageList");
		session.remove("newPasswordIncorrectErrorMessageList");

		//	return SUCCESSではだめ？
		//	resultを使わないのならば、 一番上のString result = ERRORも消そう
		result=SUCCESS;
		return result;
	}
	public String getCategoryId()
	{
		return categoryId;
	}
	public void setCategoryId(String categoryId)
	{
		this.categoryId = categoryId;
	}
	public Map<String, Object> getSession()
	{
		return session;
	}
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}

}
