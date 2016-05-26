package edu.asu.poly.promise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.asu.poly.promise.dao.ActivePatientsDAO;
import edu.asu.poly.promise.dao.ConnectionFactory;
import edu.asu.poly.promise.model.ActivePatients;

public class MySQLActivePatientsDAOImpl implements ActivePatientsDAO {
	
	/* (non-Javadoc)
	 * @see edu.asu.poly.promise.dao.impl.ActivePatientsDAO#findByUserPin(java.lang.Integer)
	 */
	@Override
	public ActivePatients findByUserPin(Integer userPin) throws Exception
	{
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.Get_Connection();
		ActivePatients activePatient = null;
		try
		{
			String query = "SELECT * FROM active_patients where pin = ?"; 
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,userPin);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				int i = 1;
				activePatient = new ActivePatients();
				activePatient.setId(rs.getInt(i++));
				activePatient.setPin(rs.getInt(i++));
				activePatient.setDeviceType(rs.getString(i++));
				activePatient.setDeviceVersion(rs.getString(i++));
				activePatient.setDateStarted(rs.getTimestamp(i++));
				activePatient.setDateCompleted(rs.getTimestamp(i++));
				activePatient.setCreatedAt(rs.getTimestamp(i++));
				activePatient.setUpdatedAt(rs.getTimestamp(i++));
				activePatient.setDeletedAt(rs.getTimestamp(i++));
				activePatient.setStageId(rs.getInt(i++));
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
		return activePatient;
	}
}
