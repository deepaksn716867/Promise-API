package edu.asu.poly.promise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.asu.poly.promise.dao.ConnectionFactory;
import edu.asu.poly.promise.model.JoinSurveysAndQuestions;
import edu.asu.poly.promise.model.QuestionOption;
import edu.asu.poly.promise.model.QuestionTemplate;
import edu.asu.poly.promise.model.SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin;
import edu.asu.poly.promise.model.SurveyInstance;
import edu.asu.poly.promise.model.SurveyTemplate;

public class MySQLSurveyInstanceDAO {
	
	public SurveyInstance findSurveyInstance(Integer surveyInstanceId)
	{
		{
			ConnectionFactory connectionFactory= new ConnectionFactory();
			Connection connection = connectionFactory.Get_Connection();
			ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> getSurveysList = new ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin>();
			try
			{
				String query = "SELECT * FROM survey_instance where id = ?"; 
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1,surveyInstanceId);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					SurveyInstance srvyIns = new SurveyInstance();
				}
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

}
