package edu.asu.poly.promise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.asu.poly.promise.dao.ConnectionFactory;
import edu.asu.poly.promise.dao.QuestionOptionDAO;
import edu.asu.poly.promise.model.QuestionOption;

/**
 * This class is the implementation for the DAO QuestionOption.
 * @author Deepak S N
 * @see Also QuestionOptionDAO
 */
public class MySQLQuestionOptionDAOImpl implements QuestionOptionDAO {
	
	/* (non-Javadoc)
	 * @see edu.asu.poly.promise.dao.impl.QuestionOptionDAO#findByOptionText(java.lang.String)
	 */
	@Override
	public QuestionOption findByOptionText(String optionText) throws Exception
	{
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.Get_Connection();
		QuestionOption questionOption = null;
		try
		{
			String query = "SELECT * FROM question_option where optionText = ?"; 
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,optionText);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				int i = 1;
				questionOption = new QuestionOption();
				questionOption.setId(rs.getInt(i++));
				questionOption.setOptionText(rs.getString(i++));
				questionOption.setOrder(rs.getInt(i++));
				questionOption.setCreatedAt(rs.getString(i++));
				questionOption.setUpdatedAt(rs.getString(i++));
				questionOption.setDeletedAt(rs.getString(i++));
				questionOption.setQuestionTemplateId(rs.getInt(i++));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		}
		return questionOption;
	}
}
