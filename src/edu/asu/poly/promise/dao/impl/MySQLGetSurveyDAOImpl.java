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
import edu.asu.poly.promise.model.JoinSurveysAndQuestions;
import edu.asu.poly.promise.model.QuestionOption;
import edu.asu.poly.promise.model.QuestionTemplate;
import edu.asu.poly.promise.model.SrvyInstActivePatientSrvyTempJoin;
import edu.asu.poly.promise.model.SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin;
import edu.asu.poly.promise.model.SurveyInstance;
import edu.asu.poly.promise.model.SurveyTemplate;

public class MySQLGetSurveyDAOImpl implements GetSurveyDAO
{
	/**
	 * This method returns the survey for the particular surveyInstance Id.
	 * @param surveyInstanceId The survey instance id for the survey to be retreived.
	 * @return ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> An arrayList of all the survey questions  
	 */
	public ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> getSurveys(Integer surveyInstanceId) throws Exception
	{
			ConnectionFactory connectionFactory= new ConnectionFactory();
			Connection connection = connectionFactory.Get_Connection();
			ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> getSurveysList = new ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin>();
			try
			{
				String query = "SELECT * , qo.id AS qoid, si.id AS sid FROM survey_instance AS si JOIN survey_template AS st ON st.id = si.surveyTemplateId JOIN join_surveys_and_questions AS jsq ON jsq.surveyTemplateId = st.id JOIN question_template AS qt ON qt.id = jsq.questionTemplateId JOIN question_option AS qo ON qo.questionTemplateId = qt.id WHERE si.id = ? ORDER BY jsq.questionOrder, qo.order"; 
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1,surveyInstanceId);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin srvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin = new SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin();
					JoinSurveysAndQuestions joinSurveyQuestions = srvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin.getJoinSurveysAndQuestions();
					QuestionOption questionOptions = srvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin.getQuestionoption();
					QuestionTemplate questnTemplate = srvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin.getQuestiontemplate();
					SurveyInstance srvyIns  = srvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin.getSurveyInstance();
					SurveyTemplate srvyTemp = srvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin.getSurveyTemplate();

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
						
						srvyTemp.setId(rs.getInt(i++));
						srvyTemp.setName(rs.getString(i++));
						srvyTemp.setCreatedAt(rs.getString(i++));
						srvyTemp.setUpdatedAt(rs.getString(i++));
						srvyTemp.setDeletedAt(rs.getString(i++));
						
						joinSurveyQuestions.setQuestionOrder(rs.getInt(i++));
						joinSurveyQuestions.setCreatedAt(rs.getString(i++));
						joinSurveyQuestions.setUpdatedAt(rs.getString(i++));
						joinSurveyQuestions.setDeletedAt(rs.getString(i++));
						joinSurveyQuestions.setQuestionTemplateId(rs.getInt(i++));
						joinSurveyQuestions.setSurveyTemplateId(rs.getInt(i++));
						
						questnTemplate.setId(rs.getInt(i++));
						questnTemplate.setQuestionText(rs.getString(i++));
						questnTemplate.setQuestionType(rs.getString(i++));
						questnTemplate.setCreatedAt(rs.getString(i++));
						questnTemplate.setUpdatedAt(rs.getString(i++));
						questnTemplate.setDeletedAt(rs.getString(i++));
						
						questionOptions.setId(rs.getInt(i++));
						questionOptions.setOptionText(rs.getString(i++));
						questionOptions.setOrder(rs.getInt(i++));
						questionOptions.setCreatedAt(rs.getString(i++));
						questionOptions.setUpdatedAt(rs.getString(i++));
						questionOptions.setDeletedAt(rs.getString(i++));
						questionOptions.setQuestionTemplateId(rs.getInt(i++));
						
						getSurveysList.add(srvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin);
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
				return getSurveysList;
		}
	}
