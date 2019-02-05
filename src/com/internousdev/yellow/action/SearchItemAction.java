package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.ProductInfoDAO;
import com.internousdev.yellow.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware
{
	//	Get
	private String searchWord;
	private int categoryId;

	// Send
	private List<ProductInfoDTO> productInfoList;
	private String errorMsg;

	// Session
	private Map<String, Object> session;

	public String execute()
	{
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		List<String> searchWordList = null;

		//	検索ワードが指定されているならば
		if(searchWord != null && !searchWord.isEmpty())
		{
			//	TODO 文字種チェック

			//	文字数チェック
			if(searchWord.length() > 50)
			{
				errorMsg = "検索ワードは50文字以内でなければなりません。";
				return ERROR;
			}

			//	検索ワードをリストへ
			searchWord = searchWord.replaceAll("　", " ");
			searchWordList = Arrays.asList(searchWord.split(" "));
		}
		//	検索ワードが指定されていないのならば
		else
		{
			searchWordList = new ArrayList<String>();
		}

		//	データベースよりデータを取得
		productInfoList = productInfoDAO.searchProductInfoList(categoryId, searchWordList);

		return SUCCESS;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<ProductInfoDTO> getProductInfoList() {
		return productInfoList;
	}

	public void setProductInfoList(List<ProductInfoDTO> productInfoList) {
		this.productInfoList = productInfoList;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
