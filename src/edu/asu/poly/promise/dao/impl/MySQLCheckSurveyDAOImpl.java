package edu.asu.poly.promise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.asu.poly.promise.dao.CheckSurveyDAO;
import edu.asu.poly.promise.dao.ConnectionFactory;
import edu.asu.poly.promise.model.ActivePatients;
import edu.asu.poly.promise.model.SrvyInstActivePatientSrvyTempJoin;
import edu.asu.poly.promise.model.SurveyInstance;
import edu.asu.poly.promise.model.SurveyTemplate;

/**
 * This class is the DAO Impl for the CheckSurvey endpoint.
 * It checks the pending and in-progress surveys for the particular userpin
 * @author Deepak S N
 */

public class MySQLCheckSurveyDAOImpl implements CheckSurveyDAO {
	/**
	 * This method is return the an arrayList of the currently available surveys for the particular user pin.
	 * @return an ArrayList which contains each instance of the selected SrvyInstActivePatientSrvyTempJoin. 
	 * @param the user pin of the patient
	 * @throws Exception 
	 */
	public ArrayList<SrvyInstActivePatientSrvyTempJoin> checkSurveys(Integer userPin) throws Exception
	{
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.Get_Connection();
		ArrayList<SrvyInstActivePatientSrvyTempJoin> sryInsActivePtntSrvyTemList = new ArrayList<SrvyInstActivePatientSrvyTempJoin>();
		try
		{
			String query = "SELECT *, si.id FROM survey_instance AS si JOIN active_patients AS pa ON si.patientId = pa.id JOIN survey_template AS st ON si.surveyTemplateId = st.id WHERE pa.pin = ? AND ? BETWEEN si.startTime AND si.endTime AND (si.state = 'pending'OR si.state = 'in progress')ORDER BY si.startTime"; 
			PreparedStatement ps = connection.prepareStatement(query);
			String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			System.out.println("The currentTime is::"+currentTime);
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
	public static void main(String args[])
	{
		SurveyInstance srvt = null;
		try {
			/*//srvt = new MySQLSurveyInstanceDAOImpl().findSurveyInstance(1);
			Date date = new Date();
			Timestamp timeStamp = new Timestamp(date.getDate());
			System.out.println("The current time is::"+timeStamp);*/
			/*ArrayList<QuestionResult> list = new ArrayList<QuestionResult>();
			QuestionResult qr = new QuestionResult();
			qr.setSurveyInstanceId(5);
			qr.setQuestionOptionId(90);
			QuestionResult qr1 = new QuestionResult();
			qr1.setSurveyInstanceId(5);
			qr1.setQuestionOptionId(91);
			list.add(qr);
			list.add(qr1);
			Timestamp currentTime = new Timestamp(new Date().getTime());
			SubmitSurveyDAO.SubmitSurvey sb = new SubmitSurveyDAO.SubmitSurvey();
			sb.questionResult = list;
			sb.TimeStamp = currentTime;
			sb.SurveyInstanceId = 5;
			boolean result = new MySQLSubmitSurveyDAOImpl().SubmitSurvey(sb);
			System.out.println("result is::"+result);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("The id id::"+srvt.getEndTime());
	}
}
