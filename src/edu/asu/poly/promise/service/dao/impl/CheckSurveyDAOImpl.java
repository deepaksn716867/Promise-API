package edu.asu.poly.promise.service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.ResultSetMetaData;

import edu.asu.poly.promise.model.ActivePatients;
import edu.asu.poly.promise.model.SrvyInstActivePatientSrvyTempJoin;
import edu.asu.poly.promise.model.SurveyInstance;
import edu.asu.poly.promise.model.SurveyTemplate;
import edu.asu.poly.promise.service.dao.CheckSurveyDAO;
import edu.asu.poly.promise.service.dao.ConnectionFactory;

/**
 * This class is the DAO Impl for the CheckSurvey endpoint.
 * It checks the pending and in-progress surveys for the particular userpin
 * @author Deepak S N
 */

public class CheckSurveyDAOImpl implements CheckSurveyDAO {
	/**
	 * This method is return the an arrayList of the currently available surveys for the particular user pin.
	 * @return an ArrayList which contains each instance of the selected SrvyInstActivePatientSrvyTempJoin. 
	 * @param the user pin of the patient
	 * @throws Exception 
	 */
	public ArrayList<SrvyInstActivePatientSrvyTempJoin> checkSurveysDAO(Integer userPin) throws Exception
	{
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.Get_Connection();
		ArrayList<SrvyInstActivePatientSrvyTempJoin> sryInsActivePtntSrvyTemList = new ArrayList<SrvyInstActivePatientSrvyTempJoin>();
		try
		{
			String query = "SELECT *, si.id FROM survey_instance AS si JOIN active_patients AS pa ON si.patientId = pa.id JOIN survey_template AS st ON si.surveyTemplateId = st.id WHERE pa.pin = ? AND ? BETWEEN si.startTime AND si.endTime AND (si.state = 'pending'OR si.state = 'in progress')ORDER BY si.startTime"; 
			PreparedStatement ps = connection.prepareStatement(query);
			String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//System.out.println("The currentTime is::"+currentTime);
			ps.setInt(1,userPin);
			ps.setString(2, currentTime);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				SrvyInstActivePatientSrvyTempJoin srvyInsActivePtntSrvyTemp = new SrvyInstActivePatientSrvyTempJoin();
				SurveyInstance srvyIns = srvyInsActivePtntSrvyTemp.getSurveyInstance();
				ActivePatients activePtnts = srvyInsActivePtntSrvyTemp.getActivePatients();
				SurveyTemplate srvyTemp = srvyInsActivePtntSrvyTemp.getSurveyTemplate();
				//Since it is join and since we have more than one column having the same name(id),we are using column index instead of column name.
					int i = 1;
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
					activePtnts.setId(rs.getInt(i++));
					activePtnts.setPin(rs.getInt(i++));
					activePtnts.setDeviceType(rs.getString(i++));
					activePtnts.setDeviceVersion(rs.getString(i++));
					activePtnts.setDateStarted(rs.getString(i++));
					activePtnts.setDateCompleted(rs.getString(i++));
					activePtnts.setCreatedAt(rs.getString(i++));
					activePtnts.setUpdatedAt(rs.getString(i++));
					activePtnts.setDeletedAt(rs.getString(i++));
					activePtnts.setStageId(rs.getString(i++));
					srvyTemp.setId(rs.getInt(i++));
					srvyTemp.setName(rs.getString(i++));
					srvyTemp.setCreatedAt(rs.getString(i++));
					srvyTemp.setUpdatedAt(rs.getString(i++));
					srvyTemp.setDeletedAt(rs.getString(i++));
					srvyInsActivePtntSrvyTemp.setActivePatients(activePtnts);
					srvyInsActivePtntSrvyTemp.setSurveyInstance(srvyIns);
					srvyInsActivePtntSrvyTemp.setSurveyTemplate(srvyTemp);
					sryInsActivePtntSrvyTemList.add(srvyInsActivePtntSrvyTemp);
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
			return sryInsActivePtntSrvyTemList;
	}
	/*public static void main(String args[])
	{
		ArrayList<SrvyInstActivePatientSrvyTempJoin> list = new CheckSurveyDAOImpl().checkSurveys(2000);
		for(SrvyInstActivePatientSrvyTempJoin eachList : list)
		{
			System.out.println(eachList.getActivePatients().getId());
			System.out.println(eachList.getSurveyInstance().getStartTime());
			System.out.println(eachList.getSurveyTemplate().getName());
		}
	}*/
}
