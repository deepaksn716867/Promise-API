package edu.asu.poly.promise.dao;

import java.util.ArrayList;
import edu.asu.poly.promise.model.SrvyInstActivePatientSrvyTempJoin;
import edu.asu.poly.promise.model.SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin;

/**
 * This Interface is the DAO for the GetSurveys service endpoint
 * @author Deepak S N
 * @see {@link}GetSurveyDAOImpl
 */

public interface GetSurveyDAO {
	
	public ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> getSurveys(Integer surveyInstanceId) throws Exception;
}
