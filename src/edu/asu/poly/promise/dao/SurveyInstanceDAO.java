package edu.asu.poly.promise.dao;

import edu.asu.poly.promise.model.SurveyInstance;

public interface SurveyInstanceDAO {

	SurveyInstance findSurveyInstance(Integer surveyInstanceId) throws Exception;

}