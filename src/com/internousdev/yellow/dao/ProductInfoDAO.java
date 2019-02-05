package com.internousdev.yellow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.yellow.dto.ProductInfoDTO;
import com.internousdev.yellow.util.DBConnector;

public class ProductInfoDAO
{

	public List<ProductInfoDTO> getProductInfoList()
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		List<ProductInfoDTO> productInfoList = new ArrayList<ProductInfoDTO>();

		String sql = "SELECT * FROM product_info";

		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));

				productInfoList.add(productInfoDTO);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}


		return productInfoList;
	}

	public ProductInfoDTO getProductInfo(int productId)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		ProductInfoDTO productInfoDTO = new ProductInfoDTO();

		String sql = "SELECT * FROM product_info WHERE product_id = ?";

		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,  productId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return productInfoDTO;
	}

	public List<ProductInfoDTO> searchProductInfoList(int category_id, List<String> searchWordList)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		List<ProductInfoDTO> productInfoList = new ArrayList<ProductInfoDTO>();

		//	SQL文作成
		String sql = "SELECT * FROM product_info";

		boolean category_search = (category_id != 1);
		boolean word_search = (!searchWordList.isEmpty());

		//	どちらかでも、検索指定があるならば
		if(category_search || word_search)
		{
			sql += " WHERE";
		}

		//	カテゴリの検索指定があるならば、カテゴリの検索条件のSQLを追加
		if(category_search)
		{
			sql += "(category_id = '" + category_id + "')";
			//	検索ワード指定があるならば
			if(word_search)
			{
				sql +=" AND ";
			}
		}

		//	検索ワード指定があるならば、検索条件のSQLを追加
		if(word_search)
		{
			sql += "(";
			boolean isFirst = true;

			for(String searchWord : searchWordList)
			{
				//	最初以外、はじめにORをつける
				if(isFirst)
				{
					isFirst = false;
				}
				else
				{
					sql += " OR ";
				}

				//	product_name と product_name_kana の条件を追加
				sql += "(product_name LIKE '%" + searchWord + "%' OR product_name_kana LIKE '%" + searchWord + "%')";
			}

			sql += ")";
		}

		//	SQL実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));

				productInfoList.add(productInfoDTO);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}


		return productInfoList;
	}

}