package com.internousdev.yellow.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.PurchaseHistoryInfoDAO;
import com.internousdev.yellow.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware
{

	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList;
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		PurchaseHistoryInfoDAO purchaseHistoryInfoDao = new PurchaseHistoryInfoDAO();
		purchaseHistoryInfoDtoList = purchaseHistoryInfoDao.getPurchaseHistoryList(String.valueOf(session.get("loginId")));
		Iterator<PurchaseHistoryInfoDTO> iterator = purchaseHistoryInfoDtoList.iterator();

		if(!(iterator.hasNext()))
		{

			purchaseHistoryInfoDtoList = null;
		}

		return SUCCESS;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDtoList()
	{
		return purchaseHistoryInfoDtoList;
	}

	public void setPurchaseHistoryInfoDtoList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList)
	{
		this.purchaseHistoryInfoDtoList = purchaseHistoryInfoDtoList;
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