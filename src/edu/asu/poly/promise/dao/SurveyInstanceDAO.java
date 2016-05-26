package edu.asu.poly.promise.dao;

import edu.asu.poly.promise.model.SurveyInstance;
import edu.asu.poly.promise.helper.APIConstants.SrvyState;

public interface SurveyInstanceDAO {

	SurveyInstance findSurveyInstance(Integer surveyInstanceId) throws Exception;
	boolean updateSurveyInstance(SurveyInstance srvy) throws Exception;
	boolean updateSurveyInstanceState(SrvyState state,SurveyInstance srvyIns) throws Exception;
	
}