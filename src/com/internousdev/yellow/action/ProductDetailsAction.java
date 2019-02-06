package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.ProductInfoDAO;
import com.internousdev.yellow.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware {

	//	Receive
	private int productId;

	//	Send
	private ProductInfoDTO productInfoDTO;
	private List<ProductInfoDTO> productInfoDtoList;
	private List<Integer> productCountList = new ArrayList<Integer>();

	//	Session
	private Map<String, Object> session;

	public String execute()
	{
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		//	商品情報を取得
		productInfoDTO = productInfoDAO.getProductInfo(productId);

		//	商品情報を取得できているのならば、商品のカテゴリIDを使用する。
		int categoryId = (productInfoDTO == null) ? 1 : productInfoDTO.getCategoryId();

		//	関連商品情報を取得
		productInfoDtoList =  productInfoDAO.getRandomProductInfoListByCategoryId(categoryId, productId);

		for (int i=1; i<=5; i++) {
			productCountList.add(i);
		}

		return SUCCESS;
	}

	public List<ProductInfoDTO> getProductInfoDtoList() {
		return productInfoDtoList;
	}
	public void setProductInfoDtoList(List<ProductInfoDTO> productInfoDtoList) {
		this.productInfoDtoList = productInfoDtoList;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;
	}
	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}
	public List<Integer> getProductCountList() {
		return productCountList;
	}

	public void setProductCountList(List<Integer> productCountList) {
		this.productCountList = productCountList;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
