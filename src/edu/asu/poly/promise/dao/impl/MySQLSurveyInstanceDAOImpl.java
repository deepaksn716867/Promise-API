package edu.asu.poly.promise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.asu.poly.promise.dao.ConnectionFactory;
import edu.asu.poly.promise.dao.SurveyInstanceDAO;
import edu.asu.poly.promise.helper.APIConstants.SrvyState;
import edu.asu.poly.promise.model.SurveyInstance;

/**
 * This class is the Mysql Impl for the SurveyInstance DAO.
 * @author Deepak S N
 */
public class MySQLSurveyInstanceDAOImpl implements SurveyInstanceDAO  {

	/**
	 * This method find the particular Survey Instance record for the corresponding surveyInstance Id.
	 * @param Integer - SurveyInstance Id
	 * @return SurveyInstance - The Survey Instance model object for the matching Survey Instance Id
	 * @see SurveyInstanceDAO 
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
					srvyIns.setStartTime(rs.getTimestamp(i++));
					srvyIns.setEndTime(rs.getTimestamp(i++));
					srvyIns.setUserSubmissionTime(rs.getTimestamp(i++));
					srvyIns.setActualSubmissionTime(rs.getTimestamp(i++));
					srvyIns.setState(rs.getString(i++));
					srvyIns.setCreatedAt(rs.getTimestamp(i++));
					srvyIns.setUpdatedAt(rs.getTimestamp(i++));
					srvyIns.setDeletedAt(rs.getTimestamp(i++));
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
	
	/**
	 * This method is used to update the survey instance 
	 * @param SurveyInstance -  The survey instance model Obj.
	 * @return Boolean - If the update was successful or not. 
	 */
	@Override
	public boolean updateSurveyInstance(SurveyInstance srvyIns) throws Exception
	{
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.Get_Connection();
		int updateCount = -1;
		PreparedStatement ps = null;
		try
		{
			String query = "update survey_instance set userSubmissionTime = ?,actualSubmissionTime = ? and state =? where  id=? and state = 'in progress'"; 
			ps = connection.prepareStatement(query);
			ps.setTimestamp(1,srvyIns.getUserSubmissionTime());
			ps.setTimestamp(2,srvyIns.getActualSubmissionTime());
			ps.setString(3,srvyIns.getState());
			ps.setInt(4,srvyIns.getId());
			
			updateCount = ps.executeUpdate();
			
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(ps != null)
				ps.close();
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
		
		return updateCount>0? true : false;
	}
	
	/**
	 * This method is used to update the survey instance state.
	 * @param SurveyInstance -  The survey instance model Obj.
	 * @param SrvyState - An Enum with state for the surveyInstance State.
	 * @return Boolean - If the update was successful or not. 
	 */
	@Override
	public boolean updateSurveyInstanceState(SrvyState state,SurveyInstance srvyIns) throws Exception
	{
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.Get_Connection();
		int updateCount = -1;
		PreparedStatement ps = null;
		try
		{
			String query = "update survey_instance set state = ? where  id=?";
			ps = connection.prepareStatement(query);
			ps.setString(1,state.getValue());
			ps.setInt(2,srvyIns.getId());
			
			updateCount = ps.executeUpdate();
			
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(ps != null)
				ps.close();
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
		
		return updateCount>0? true : false;
	}

}