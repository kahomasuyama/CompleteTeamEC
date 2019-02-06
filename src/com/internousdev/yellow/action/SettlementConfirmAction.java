package com.internousdev.yellow.action;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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

	private String categoryId;
	private String productId;
	private String productName;
	private String productNameKana;
	private String imageFileName;
	private String imageFilePath;
	private String price;
	private String releaseCompany;
	private String releaseDate;
	private String productCount;
	private String subtotal;
	private Collection<String>cheakList;
	private Map<String,Object>session;

	public String execute() 
	{
		String result = ERROR;
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
		    } catch (SQLException e) {
				e.printStackTrace();
		    }
		}
    //商品購入履歴
     if(!session.containsKey("purchaseHistoryInfoDtoList"))
     {
    	List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList=new ArrayList<PurchaseHistoryInfoDTO>();

    	CommonUtility cou=new CommonUtility();
    	String[] productIdList= cou.parseArrayList(productId);
    	String[] productNameList = cou.parseArrayList(productName);
		String[] productNameKanaList = cou.parseArrayList(productNameKana);
		String[] imageFilePathList = cou.parseArrayList(imageFilePath);
		String[] imageFileNameList = cou.parseArrayList(imageFileName);
		String[] priceList = cou.parseArrayList(price);
		String[] releaseCompanyList = cou.parseArrayList(releaseCompany);
		String[] releaseDateList = cou.parseArrayList(releaseDate);
		String[] productCountList = cou.parseArrayList(productCount);

	 for(int i=0;i<productIdList.length;i++)
	 {
		PurchaseHistoryInfoDTO HIDTO=new PurchaseHistoryInfoDTO();
		HIDTO.setUserId(String.valueOf(session.get("loginId")));
		HIDTO.setProductId(Integer.parseInt(String.valueOf(productIdList[i])));
		HIDTO.setProductName(String.valueOf(productNameList[i]));
		HIDTO.setProductNameKana(String.valueOf(productNameKanaList[i]));
		HIDTO.setImageFilePath(String.valueOf(imageFilePathList[i]));
		HIDTO.setImageFileName(String.valueOf(imageFileNameList[i]));
		HIDTO.setPrice(Integer.parseInt(String.valueOf(priceList[i])));
		HIDTO.setReleaseCompany(String.valueOf(releaseCompanyList[i]));
			try{
				SimpleDateFormat SDF= new SimpleDateFormat("yyyy/MM/dd");
				HIDTO.setReleaseDate(SDF.parse(String.valueOf(releaseDateList[i])));
			}catch(ParseException e){
				e.printStackTrace();
			}
			HIDTO.setProductCount(Integer.parseInt(String.valueOf(productCountList[i])));
			
			purchaseHistoryInfoDtoList.add(HIDTO);
	  }
		session.put("purchaseHistoryInfoDtoList",purchaseHistoryInfoDtoList );
    }
    if(!session.containsKey("loginId"))
    {
    	result=ERROR;
    }else
    {
    	result=SUCCESS;
    }
    return result;
	}





	public String getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public Collection<String> getCheakList() {
		return cheakList;
	}

	public void setCheakList(Collection<String> cheakList) {
		this.cheakList = cheakList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}






}
