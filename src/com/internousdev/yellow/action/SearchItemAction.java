package com.internousdev.yellow.action;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.MCategoryDAO;
import com.internousdev.yellow.dao.ProductInfoDAO;
import com.internousdev.yellow.dto.MCategoryDTO;
import com.internousdev.yellow.dto.ProductInfoDTO;
import com.internousdev.yellow.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware
{
	//	Receive
	private String searchWord;
	private int categoryId;

	//	Send
	private List<ProductInfoDTO> productInfoList;
	private List<String> errorMsgList;

	//	Session
	private Map<String, Object> session;

	public String execute()
	{
		//	商品カテゴリがないなら取得
		if(!session.containsKey("mCategoryDtoList"))
		{
			MCategoryDAO mcategoryDAO = new MCategoryDAO();
			List<MCategoryDTO> mCategoryDtoList = mcategoryDAO.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}


		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		List<String> searchWordList = null;

		//	検索ワードが指定されているならば
		if(searchWord != null && !searchWord.isEmpty())
		{
			System.out.println(searchWord);
			System.out.println(categoryId);

			//	文字チェック
			InputChecker inputChecker = new InputChecker();
			errorMsgList = inputChecker.doCheck("検索ワード", searchWord, 0, 50, true, true, true, true, false, true, false, true, true);

			//	エラーメッセージがあるならば、エラーを返す
			if(!errorMsgList.isEmpty())
			{
				return ERROR;
			}

			//	検索ワードをリストへ
			searchWord = searchWord.replaceAll("　", " ");
			searchWordList = Arrays.asList(searchWord.split(" "));
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
	public List<String> getErrorMsgList() {
		return errorMsgList;
	}
	public void setErrorMsgList(List<String> errorMsgList) {
		this.errorMsgList = errorMsgList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
