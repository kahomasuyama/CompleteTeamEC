package com.internousdev.yellow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.yellow.dto.ProductInfoDTO;
import com.internousdev.yellow.util.DBConnector;

/**
 * 商品情報に関するDB操作
 *
 * @author internousdev
 */
public class ProductInfoDAO
{
	/**
	 * 商品リストをランダムな順番でDBより取得する<br/>
	 * 商品カテゴリを指定可能、ひとつだけ商品を除外できる
	 *
	 * @param categoryId
	 * 指定のカテゴリID (1 : 指定しない)
	 *
	 * @param ignoreProductId
	 * 除外する商品ID (0 : 指定しない)
	 *
	 * @return
	 * 商品リスト
	 */
	public List<ProductInfoDTO> getRandomProductInfoListByCategoryId(int categoryId, int ignoreProductId)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		List<ProductInfoDTO> productInfoList = new ArrayList<ProductInfoDTO>();

		//	SQL作成
		String sql = "SELECT * FROM product_info WHERE ";

		//	カテゴリが指定されているのならば条件を追加
		if(categoryId != 1)
		{
			sql += "category_id = " + categoryId + " AND ";
		}

		sql += "product_id NOT IN(?) ORDER BY rand() LIMIT 0, 3";

		//	SQL実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ignoreProductId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
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
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return productInfoList;
	}

	/**
	 * 商品のリストをDBより取得する
	 *
	 * @return
	 * 商品のリスト
	 */
	public List<ProductInfoDTO> getProductInfoList()
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		List<ProductInfoDTO> productInfoList = new ArrayList<ProductInfoDTO>();

		//	SQL作成
		String sql = "SELECT * FROM product_info";

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
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
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
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return productInfoList;
	}

	/**
	 * 指定の商品IDの商品情報をDBより取得する
	 *
	 * @param productId
	 * 指定の商品ID
	 *
	 * @return
	 * 商品情報
	 */
	public ProductInfoDTO getProductInfo(int productId)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		ProductInfoDTO productInfoDTO = new ProductInfoDTO();

		//	SQL作成
		String sql = "SELECT * FROM product_info WHERE product_id = ?";

		//	SQL実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,  productId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
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
			else
			{
				productInfoDTO = null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return productInfoDTO;
	}

	/**
	 * 検索ワードと商品をカテゴリを指定して、DBより商品リストを取得する
	 *
	 * @param category_id
	 * 指定の商品カテゴリ
	 *
	 * @param searchWordList
	 * 指定の検索ワードのリスト
	 *
	 * @return
	 * 商品リスト
	 */
	public List<ProductInfoDTO> searchProductInfoList(int category_id, List<String> searchWordList)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		List<ProductInfoDTO> productInfoList = new ArrayList<ProductInfoDTO>();

		//	SQL作成
		String sql = "SELECT * FROM product_info";

		//	カテゴリを検索条件に含むか
		boolean category_search = (category_id != 1);
		//	ワードを指定して検索するか
		boolean word_search = (searchWordList != null && !searchWordList.isEmpty());

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
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
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
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return productInfoList;
	}
}
