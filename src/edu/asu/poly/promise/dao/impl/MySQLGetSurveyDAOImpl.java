package edu.asu.poly.promise.dao.impl;
/**
 * The class is the implementation for the DAO getsurvey service endpoint.
 * It returns the survey for the particular survey instance id.
 * @see GetSurveyDAO
 * @author Deepak S N
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.asu.poly.promise.dao.ConnectionFactory;
import edu.asu.poly.promise.dao.GetSurveyDAO;
import edu.asu.poly.promise.model.ActivePatients;
import edu.asu.poly.promise.model.SrvyInstActivePatientSrvyTempJoin;
import edu.asu.poly.promise.model.SurveyInstance;
import edu.asu.poly.promise.model.SurveyTemplate;

public class MySQLGetSurveyDAOImpl implements GetSurveyDAO
{
	/**
	 * This method returns the survey for the particular surveyInstance Id.
	 * @param surveyInstanceId The survey instance id for the survey to be retreived.
	 * @return ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> An arrayList of all the survey questions  
	 */
	public ArrayList<SrvyInstActivePatientSrvyTempJoin> getSurvey(Integer surveyInstanceId) throws Exception
	{
			ConnectionFactory connectionFactory= new ConnectionFactory();
			Connection connection = connectionFactory.Get_Connection();
			ArrayList<SrvyInstActivePatientSrvyTempJoin> sryInsActivePtntSrvyTemList = new ArrayList<SrvyInstActivePatientSrvyTempJoin>();
			try
			{
				String query = "SELECT * , qo.id AS qoid, si.id AS sid FROM survey_instance AS si JOIN survey_template AS st ON st.id = si.surveyTemplateId JOIN join_surveys_and_questions AS jsq ON jsq.surveyTemplateId = st.id JOIN question_template AS qt ON qt.id = jsq.questionTemplateId JOIN question_option AS qo ON qo.questionTemplateId = qt.id WHERE si.id = ? ORDER BY jsq.questionOrder, qo.order"; 
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1,surveyInstanceId);
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
}
