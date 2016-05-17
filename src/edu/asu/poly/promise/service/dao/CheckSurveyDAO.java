package edu.asu.poly.promise.service.dao;

import java.util.ArrayList;
import edu.asu.poly.promise.model.SrvyInstActivePatientSrvyTempJoin;

/**
 * This Interface is the DAO for the checkSurveys service endpoint
 * @author Deepak S N
 * @see {@link}CheckSurveyDAOImpl
 */

public interface CheckSurveyDAO {
	
	public ArrayList<SrvyInstActivePatientSrvyTempJoin> checkSurveys(Integer userPin) throws Exception;
}
