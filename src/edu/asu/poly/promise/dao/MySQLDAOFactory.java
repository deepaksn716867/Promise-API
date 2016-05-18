package edu.asu.poly.promise.dao;

import edu.asu.poly.promise.dao.impl.MySQLCheckSurveyDAOImpl;

public class MySQLDAOFactory extends DAOFactory {
	
	public CheckSurveyDAO getCheckSurveyDAO()
	{
		return new MySQLCheckSurveyDAOImpl();
	}

}
