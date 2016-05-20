package edu.asu.poly.promise.dao;

import java.util.ArrayList;

import edu.asu.poly.promise.model.QuestionResult;

public interface SubmitSurveyDAO {

	boolean SubmitSurvey(SubmitSurvey subsurvey);
	
	class SubmitSurvey
	{
		public ArrayList<QuestionResult> questionResult;
		public String TimeStamp;
	}

}