package com.internousdev.yellow.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.PurchaseHistoryInfoDAO;
import com.internousdev.yellow.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware
{
	private String sex;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		if(purchaseHistoryInfoDAO.deleteAll(String.valueOf(session.get("loginId"))) == 0)
		{
			return ERROR;
		}

		purchaseHistoryInfoDTOList = purchaseHistoryInfoDAO.getPurchaseHistoryList(String.valueOf(session.get("loginId")));
		if(purchaseHistoryInfoDTOList.isEmpty())
		{
			purchaseHistoryInfoDTOList = null;
		}

		return SUCCESS;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDtoList() {
		return purchaseHistoryInfoDTOList;
	}
	public void setPurchaseHistoryInfoDtoList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList) {
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDtoList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}