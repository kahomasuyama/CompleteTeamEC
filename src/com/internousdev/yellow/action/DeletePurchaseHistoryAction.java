package com.internousdev.yellow.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.PurchaseHistoryInfoDAO;
import com.internousdev.yellow.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware{
	private String sex;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;
	private Map<String, Object> session;
	public String execute()
	{
		String result = ERROR;

		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		int count = purchaseHistoryInfoDAO.deleteAll(String.valueOf(session.get("loginId")));
		if(count > 0)
		{
			purchaseHistoryInfoDTOList = purchaseHistoryInfoDAO.getPurchaseHistoryList(String.valueOf(session.get("loginId")));
			Iterator<PurchaseHistoryInfoDTO> iterator = purchaseHistoryInfoDTOList.iterator();
			if(!(iterator.hasNext()))
			{
				purchaseHistoryInfoDTOList = null;
			}

			result=SUCCESS;
		}
		return result;
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