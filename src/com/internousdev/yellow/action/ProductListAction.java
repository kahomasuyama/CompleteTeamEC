package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.ProductInfoDAO;
import com.internousdev.yellow.dto.MCategoryDTO;
import com.internousdev.yellow.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductListAction extends ActionSupport implements SessionAware
{
	//	Send
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private List<ProductInfoDTO> productInfoList = new ArrayList<ProductInfoDTO>();

	//	Session
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	データベースよりデータを取得
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		productInfoList = productInfoDAO.getProductInfoList();

		return SUCCESS;
	}

	public List<MCategoryDTO> getmCategoryDtoList() {
		return mCategoryDtoList;
	}
	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList) {
		this.mCategoryDtoList = mCategoryDtoList;
	}
	public List<ProductInfoDTO> getProductInfoList() {
		return productInfoList;
	}
	public void setProductInfoList(List<ProductInfoDTO> productInfoList) {
		this.productInfoList = productInfoList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
