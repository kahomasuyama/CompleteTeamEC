package com.internousdev.yellow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.yellow.dto.MCategoryDTO;
import com.internousdev.yellow.util.DBConnector;

public class MCategoryDAO
{

	public List<MCategoryDTO> getMCategoryList()
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		List<MCategoryDTO> mCategoryList = new ArrayList<MCategoryDTO>();

		//	SQL作成
		String sql = "SELECT * FROM m_category";

		//	SQL実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(resultSet.getInt("id"));
				mCategoryDTO.setCategoryName(resultSet.getString("category_name"));
				mCategoryDTO.setCategoryDescription(resultSet.getString("category_description"));
				mCategoryDTO.setInsertDate(resultSet.getDate("insert_date"));
				mCategoryDTO.setUpdateDate(resultSet.getDate("update_date"));

				mCategoryList.add(mCategoryDTO);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		//	Close
		try
		{
			connection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return mCategoryList;
	}
}
