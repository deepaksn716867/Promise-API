package edu.asu.poly.promise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.asu.poly.promise.dao.ConnectionFactory;
import edu.asu.poly.promise.dao.SurveyInstanceDAO;
import edu.asu.poly.promise.model.SurveyInstance;

public class MySQLSurveyInstanceDAOImpl implements SurveyInstanceDAO  {
	
	/* (non-Javadoc)
	 * @see edu.asu.poly.promise.dao.impl.SurveyInstanceDAO#findSurveyInstance(java.lang.Integer)
	 */
	@Override
	public SurveyInstance findSurveyInstance(Integer surveyInstanceId) throws Exception
	{
			ConnectionFactory connectionFactory= new ConnectionFactory();
			Connection connection = connectionFactory.Get_Connection();
			SurveyInstance srvyIns = null;
			try
			{
				String query = "SELECT * FROM survey_instance where id = ?"; 
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1,surveyInstanceId);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					int i = 1;
					srvyIns = new SurveyInstance();
					srvyIns.setId(rs.getInt(i++));
					srvyIns.setStartTime(rs.getString(i++));
					srvyIns.setEndTime(rs.getString(i++));
					srvyIns.setUserSubmissionTime(rs.getString(i++));
					srvyIns.setActualSubmissionTime(rs.getString(i++));
					srvyIns.setState(rs.getString(i++));
					srvyIns.setCreatedAt(rs.getString(i++));
					srvyIns.setUpdatedAt(rs.getString(i++));
					srvyIns.setDeletedAt(rs.getString(i++));
					srvyIns.setPatientId(rs.getInt(i++));
					srvyIns.setSurveyTemplateId(rs.getInt(i++));
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
			return srvyIns;
	}
}
