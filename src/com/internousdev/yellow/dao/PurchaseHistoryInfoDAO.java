package com.internousdev.yellow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.yellow.dto.PurchaseHistoryInfoDTO;
import com.internousdev.yellow.util.DBConnector;

public class PurchaseHistoryInfoDAO {

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryList(String loginId)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = new ArrayList<PurchaseHistoryInfoDTO>();

		String sql = "SELECT"
				// ID ユーザーID 個数
				+ " phi.id AS id,"
				+ " phi.user_id AS user_id,"
				+ " phi.product_count AS product_count,"
				// 商品ID 商品名 商品名かな 商品詳細
				+ " pi.product_id AS product_id,"
				+ " pi.product_name AS product_name,"
				+ " pi.product_name_kana AS product_name_kana,"
				+ " pi.product_description AS product_description,"
				// カテゴリID 価格 画像ファイルパス 画像ファイル名 発売会社名 発売年月日
				+ " pi.category_id AS category_id,"
				+ " pi.price,"
				+ " pi.image_file_name AS image_file_name,"
				+ " pi.image_file_path AS image_file_path,"
				+ " pi.release_company,"
				+ " pi.release_date,"
				// 値段 登録日 更新日
				+ " phi.price AS price,"
				+ " phi.regist_date AS regist_date,"
				+ " phi.update_date AS update_date,"
				// 姓 名 姓かな 名かな メールアドレス 電話番号 住所
				+ " di.family_name AS family_name,"
				+ " di.first_name AS first_name,"
				+ " di.family_name_kana AS family_name_kana,"
				+ " di.first_name_kana AS first_name_kana,"
				+ " di.email AS email,"
				+ " di.tel_number AS tel_number,"
				+ " di.user_address AS user_address"

				// purchase_history_info(購入履歴)をphiと定義
				+ " FROM purchase_history_info AS phi"
				// product_info(商品情報)をpiと定義
				+ " LEFT JOIN product_info AS pi"
				// phiとpiのproduct_idをつなげる
				+ " ON phi.product_id = pi.product_id"

				// destination_info（宛先情報）をdiと定義
				+ " LEFT JOIN destination_info AS di"
				// 上記のdiとphiのidをつなげる
				+ " ON phi.destination_id = di.id"

				// phi.userが？の時
				+ " WHERE phi.user_id = ?"
				// regist_dateを降順に並べる
				+ " ORDER BY regist_date DESC";

		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				PurchaseHistoryInfoDTO purchaseHistoryInfoDto = new PurchaseHistoryInfoDTO();
				purchaseHistoryInfoDto.setId(resultSet.getInt("id"));
				purchaseHistoryInfoDto.setUserId(resultSet.getString("user_id"));
				purchaseHistoryInfoDto.setProductId(resultSet.getInt("product_id"));
				purchaseHistoryInfoDto.setProductCount(resultSet.getInt("product_count"));
				purchaseHistoryInfoDto.setPrice(resultSet.getInt("price"));
				purchaseHistoryInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				purchaseHistoryInfoDto.setUpdateDate(resultSet.getDate("update_date"));
				purchaseHistoryInfoDto.setProductName(resultSet.getString("product_name"));
				purchaseHistoryInfoDto.setProductNameKana(resultSet.getString("product_name_kana"));
				purchaseHistoryInfoDto.setProductDescription(resultSet.getString("product_description"));
				purchaseHistoryInfoDto.setCategoryId(resultSet.getInt("category_id"));
				purchaseHistoryInfoDto.setImageFileName(resultSet.getString("image_file_name"));
				purchaseHistoryInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
				purchaseHistoryInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				purchaseHistoryInfoDto.setReleaseDate(resultSet.getDate("release_date"));
				purchaseHistoryInfoDto.setFamilyName(resultSet.getString("family_name"));
				purchaseHistoryInfoDto.setFirstName(resultSet.getString("first_name"));
				purchaseHistoryInfoDto.setFamilyNameKana(resultSet.getString("family_name_kana"));
				purchaseHistoryInfoDto.setFirstNameKana(resultSet.getString("first_name_kana"));
				purchaseHistoryInfoDto.setEmail(resultSet.getString("email"));
				purchaseHistoryInfoDto.setTelNumber(resultSet.getString("tel_number"));
				purchaseHistoryInfoDto.setUserAddress(resultSet.getString("user_address"));
				purchaseHistoryInfoDto.setSubtotal(resultSet.getInt("price") * resultSet.getInt("product_count"));
				purchaseHistoryInfoDTOList.add(purchaseHistoryInfoDto);
			}
		}
		catch (SQLException e)
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

		return purchaseHistoryInfoDTOList;
	}

	public boolean regist(String loginId, int productId, int productCount, int destinationId, int price)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		// phiに()の中の値を入れる
		String sql = "INSERT INTO purchase_history_info(user_id, product_id, product_count, price, destination_id, regist_date, update_date) VALUES (?, ?, ?, ?, ?, now(), now())";
		int count = 0;

		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, productCount);
			preparedStatement.setInt(4, price);
			preparedStatement.setInt(5, destinationId);
			count = preparedStatement.executeUpdate();
		}
		catch (SQLException e)
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

		return count > 0;
	}

	public int deleteAll(String loginId)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		// phi内の指定のuser_idの情報をDELETE（消す）
		String sql = "DELETE FROM purchase_history_info WHERE user_id = ?";
		int count = 0;

		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			count = preparedStatement.executeUpdate();
		}
		catch (SQLException e)
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

		return count;
	}
}
