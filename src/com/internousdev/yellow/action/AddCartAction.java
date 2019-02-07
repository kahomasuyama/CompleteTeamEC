package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.CartInfoDAO;
import com.internousdev.yellow.dto.CartInfoDTO;
import com.internousdev.yellow.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware
{

	private int productId;
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private int price;
	private String productCount;
	private String releaseCompany;
	private Date releaseDate;
	private String productDescription;

	private Map<String,Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		String result=ERROR;
		String userId=null;
		String tempUserId=null;

		session.remove("checkListErrorMessageList");

		//未ログイン時のときにtempUserIdの発行
		if(!(session.containsKey("loginId"))&& !(session.containsKey("tempUserId")))
		{
			CommonUtility commonUtility=new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}


		if(session.containsKey("loginId"))
		{
			userId=String.valueOf(session.get("loginId"));
		}
		else
		{
			userId=String.valueOf(session.get("tempUserId"));
			tempUserId=String.valueOf(session.get("tempUserId"));
		}


		int intProductCount=Integer.parseInt(productCount);

		CartInfoDAO cartInfoDao=new CartInfoDAO();
		CartInfoDAO cartInfoDAO=new CartInfoDAO();

		int count=0;

		if(cartInfoDAO.isExistsCartInfo(userId,productId))
		{
			count=cartInfoDAO.update(userId,productId,intProductCount);
		}
		else
		{
			count=cartInfoDAO.regist(userId,tempUserId,productId,intProductCount,price);
		}

		if(count>0)
		{
			result=SUCCESS;
		}

		List<CartInfoDTO> cartInfoDtoList=new ArrayList<CartInfoDTO>();
		cartInfoDtoList=cartInfoDao.getCartInfoDtoList(userId);
		Iterator<CartInfoDTO> iterator=cartInfoDtoList.iterator();

		if(!(iterator.hasNext()))
		{
			cartInfoDtoList=null;
		}
		session.put("cartInfoDtoList",cartInfoDtoList);
		int totalPrice=Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(userId)));
		session.put("totalPrice", totalPrice);
		return result;

	}
	public int getProductId(){
		return productId;
	}
	public void setProductId(int productId){
		this.productId=productId;
	}
	public String getProducName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName=productName;
	}
	public String getProductNameKana(){
		return productNameKana;
	}
	public void setProductNameKana(String productNameKana){
		this.productNameKana=productNameKana;
	}
	public String getImageFilePath(){
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath){
		this.imageFilePath=imageFilePath;
	}
	public String getImageFileName(){
		return imageFileName;
	}
	public void setImageFileName(String imageFileName){
		this.imageFileName=imageFileName;
	}
	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public String getProductCount(){
		return productCount;
	}
	public void setProductCount(String productCount){
		this.productCount=productCount;
	}
	public String getReleaseCompany(){
		return releaseCompany;
	}
	public void setReleaseCompanny(String releaseCompany){
		this.releaseCompany=releaseCompany;
	}
	public Date getReleaseDate(){
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate){
		this.releaseDate=releaseDate;
	}
	public String getProductDescription(){
		return productDescription;
	}
	public void setProductDescription(String productDescription){
		this.productDescription=productDescription;
	}
	public Map<String,Object> getSession(){
		return session;
	}
	public void setSession(Map<String,Object> session){
		this.session=session;
	}
}
