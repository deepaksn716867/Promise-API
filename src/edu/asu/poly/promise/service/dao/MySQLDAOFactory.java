package edu.asu.poly.promise.service.dao;

import edu.asu.poly.promise.service.dao.impl.MySQLCheckSurveyDAOImpl;

public class MySQLDAOFactory extends DAOFactory {
	
	public CheckSurveyDAO getCheckSurveyDAO()
	{
		return new MySQLCheckSurveyDAOImpl();
	}

}
