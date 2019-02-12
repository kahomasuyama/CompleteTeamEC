package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dao.PurchaseHistoryInfoDAO;
import com.internousdev.yellow.dto.CartInfoDTO;
import com.internousdev.yellow.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware{

	private String id;
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	購入処理待ちの購入履歴
		@SuppressWarnings("unchecked")
		ArrayList<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList = (ArrayList<PurchaseHistoryInfoDTO>)session.get("purchaseHistoryInfoDtoList");

		//	選択された宛先IDを購入履歴の宛先IDに代入
		int destinationId = Integer.valueOf(id);
		for (PurchaseHistoryInfoDTO purchaseHistoryInfoDTO : purchaseHistoryInfoDtoList)
		{
			purchaseHistoryInfoDTO.setDestinationId(destinationId);
		}

		//	購入処理
		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		int count = 0;
		for(PurchaseHistoryInfoDTO dto : purchaseHistoryInfoDtoList)
		{
			count += purchaseHistoryInfoDAO.regist(String.valueOf(session.get("loginId")), dto.getProductId(), dto.getProductCount(), dto.getDestinationId(), dto.getPrice());
		}

		if(count > 0)
		{
			//	カートの中身を削除
			CartInfoDAO cartInfoDAO = new CartInfoDAO();
			count = cartInfoDAO.deleteAll(String.valueOf(session.get("loginId")));

			if(count > 0)
			{
				//	カートの中身を取得
				List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
				cartInfoDtoList = cartInfoDAO.getCartInfoDtoList(String.valueOf(session.get("loginId")));
				Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();

				if(!(iterator.hasNext()))
				{
					cartInfoDtoList = null;
				}
				session.put("cartInfoDtoList", cartInfoDtoList);

				//	合計金額を取得
				int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(String.valueOf(session.get("loginId")))));
				session.put("totalPrice", totalPrice);

				//	購入し終わった購入履歴を削除
				session.remove("purchaseHistoryInfoDtoList");

				return SUCCESS;
			}
		}

		return ERROR;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
