package edu.asu.poly.promise.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import edu.asu.poly.promise.model.QuestionResult;

public interface SubmitSurveyDAO {

	boolean SubmitSurvey(SubmitSurvey subsurvey) throws Exception;
	
	class SubmitSurvey
	{
		public ArrayList<QuestionResult> questionResult;
		public Timestamp TimeStamp;
		public int survey_instance_id;
	}

}