package com.internousdev.yellow.action;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.DestinationInfoDAO;
import com.internousdev.yellow.dto.DestinationInfoDTO;
import com.internousdev.yellow.dto.PurchaseHistoryInfoDTO;
import com.internousdev.yellow.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware
{
	//	Receive
	private String productId;
	private String productName;
	private String productNameKana;
	private String imageFileName;
	private String imageFilePath;
	private String price;
	private String releaseCompany;
	private String releaseDate;
	private String productCount;

	//	Session
	private Map<String,Object>session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//宛先情報
		if(session.containsKey("loginId"))
		{
			DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
			List<DestinationInfoDTO> destinationInfoDtoList = new ArrayList<>();
			try
			{
				destinationInfoDtoList = destinationInfoDAO.getDestinationInfo(String.valueOf(session.get("loginId")));
				Iterator<DestinationInfoDTO> iterator = destinationInfoDtoList.iterator();
				if(!(iterator.hasNext()))
				{
						destinationInfoDtoList = null;
				}
				session.put("destinationInfoDtoList", destinationInfoDtoList);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
		    }
		}
		//商品購入履歴
		if(!session.containsKey("purchaseHistoryInfoDtoList"))
		{
			List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList=new ArrayList<PurchaseHistoryInfoDTO>();

			CommonUtility commonUtility = new CommonUtility();
			String[] productIdList= commonUtility.parseArrayList(productId);
			String[] productNameList = commonUtility.parseArrayList(productName);
			String[] productNameKanaList = commonUtility.parseArrayList(productNameKana);
			String[] imageFilePathList = commonUtility.parseArrayList(imageFilePath);
			String[] imageFileNameList = commonUtility.parseArrayList(imageFileName);
			String[] priceList = commonUtility.parseArrayList(price);
			String[] releaseCompanyList = commonUtility.parseArrayList(releaseCompany);
			String[] releaseDateList = commonUtility.parseArrayList(releaseDate);
			String[] productCountList = commonUtility.parseArrayList(productCount);

			for(int i = 0; i < productIdList.length; i++)
			{
				PurchaseHistoryInfoDTO purchaseHistoryInfoDTO = new PurchaseHistoryInfoDTO();
				purchaseHistoryInfoDTO.setUserId(String.valueOf(session.get("loginId")));
				purchaseHistoryInfoDTO.setProductId(Integer.parseInt(String.valueOf(productIdList[i])));
				purchaseHistoryInfoDTO.setProductName(String.valueOf(productNameList[i]));
				purchaseHistoryInfoDTO.setProductNameKana(String.valueOf(productNameKanaList[i]));
				purchaseHistoryInfoDTO.setImageFilePath(String.valueOf(imageFilePathList[i]));
				purchaseHistoryInfoDTO.setImageFileName(String.valueOf(imageFileNameList[i]));
				purchaseHistoryInfoDTO.setPrice(Integer.parseInt(String.valueOf(priceList[i])));
				purchaseHistoryInfoDTO.setReleaseCompany(String.valueOf(releaseCompanyList[i]));
				purchaseHistoryInfoDTO.setProductCount(Integer.parseInt(String.valueOf(productCountList[i])));
				purchaseHistoryInfoDTO.setSubtotal(purchaseHistoryInfoDTO.getProductCount() * purchaseHistoryInfoDTO.getPrice());

				try
				{
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
					purchaseHistoryInfoDTO.setReleaseDate(simpleDateFormat.parse(String.valueOf(releaseDateList[i])));
				}
				catch(ParseException e)
				{
					e.printStackTrace();
				}

				purchaseHistoryInfoDtoList.add(purchaseHistoryInfoDTO);
			}
			session.put("purchaseHistoryInfoDtoList",purchaseHistoryInfoDtoList );
		}

		if(session.containsKey("loginId"))
		{
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNameKana() {
		return productNameKana;
	}
	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getReleaseCompany() {
		return releaseCompany;
	}
	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getProductCount() {
		return productCount;
	}
	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
