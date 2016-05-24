package edu.asu.poly.promise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.asu.poly.promise.dao.ConnectionFactory;
import edu.asu.poly.promise.dao.SubmitSurveyDAO;
import edu.asu.poly.promise.model.QuestionResult;
import edu.asu.poly.promise.model.SurveyInstance;

/**
 * This class is the Impl for the SubmitSurvey DAO.
 * @author Deepak S N
 * @Seealso SubmitSurveyDAO
 */
public class MySQLSubmitSurveyDAOImpl implements SubmitSurveyDAO {
	
	@Override
	public boolean SubmitSurvey(SubmitSurvey subSurvey) throws Exception
	{
		ArrayList<QuestionResult> questionResults = null;
		int updateCount = -1;
		if(subSurvey != null)
		{
			questionResults = subSurvey.questionResult;
			ConnectionFactory database= new ConnectionFactory();
			Connection connection = database.Get_Connection();
			
			try
			{
				//This boolean is set to false for performing the SQL transactions semantics.
				connection.setAutoCommit(false);
				
				String query = "insert into question_result (surveyInstanceId,questionOptionId,createdAt,updatedAt) values(?,?,?,?)";
				
				PreparedStatement ps = connection.prepareStatement(query);
				for(QuestionResult questionresult : questionResults)
				{
					try 
					{
						ps.setInt(1,questionresult.getSurveyInstanceId());
						ps.setInt(2, questionresult.getQuestionOptionId());
						ps.setTimestamp(3, new Timestamp(new Date().getTime()));
						ps.setTimestamp(4, new Timestamp(new Date().getTime()));
						ps.addBatch();
					} 
					catch(Exception e)
					{
						e.printStackTrace();
						if(ps != null)
							ps.close();
						connection.rollback();
						throw e;
					}
				}
				int[] executeResult = ps.executeBatch();
				ps.close();
				//If the Question Result Table is not inserted then we have to abort the transaction. 
				System.out.println("The response is::"+"The length is::"+executeResult.length+"::"+executeResult[0]);
				if(executeResult.length <= 0 || (executeResult.length > 0 && executeResult[0] < 0))
				{
					connection.rollback();
					System.out.println("The batch processing failed::Rollingback Transactions");
					return false;
				}
				ps = null;
				Date date = new Date();
				Timestamp currentTime = new Timestamp(date.getTime());
				try
				{
					query = "update survey_instance set userSubmissionTime = ?,actualSubmissionTime = ?,state =? where  id=? and state = 'in progress'"; 
					ps = connection.prepareStatement(query);
					ps.setTimestamp(1,subSurvey.TimeStamp);
					ps.setTimestamp(2,currentTime);
					ps.setString(3,"completed");
					ps.setInt(4,subSurvey.survey_instance_id);
					
					updateCount = ps.executeUpdate();
					System.out.println("The update count is::"+updateCount);
					
					ps.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					if(ps != null)
						ps.close();
					connection.rollback();
					throw e;
				}
				//Only if the insert into question_result and the update in the survey_instance succeed we have to commit the transaction.
				if(updateCount > 0)
					connection.commit();
				else
					connection.rollback();
			}
			catch(Exception e)
			{
				connection.rollback();
				e.printStackTrace();
				throw e;
			}
			finally
			{
				try 
				{
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw e;
				}
			}
		}
		return (updateCount>0) ? true:false;
	}
}
