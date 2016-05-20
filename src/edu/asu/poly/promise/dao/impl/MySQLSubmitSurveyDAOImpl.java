package edu.asu.poly.promise.dao.impl;

import java.util.ArrayList;

import edu.asu.poly.promise.dao.SubmitSurveyDAO;
import edu.asu.poly.promise.model.QuestionResult;

/**
 * This class is the Impl for the SubmitSurvey DAO.
 * @author Deepak S N
 * @Seealso SubmitSurveyDAO
 */
public class MySQLSubmitSurveyDAOImpl implements SubmitSurveyDAO {
	
	@Override
	public boolean SubmitSurvey(SubmitSurvey subSurvey)
	{
		ArrayList<QuestionResult> questionResult = null;
		if(subSurvey != null)
		{
			questionResult = subSurvey.questionResult;
		}
		
		return false;
	}
}
