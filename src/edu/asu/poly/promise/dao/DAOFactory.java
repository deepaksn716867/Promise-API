package edu.asu.poly.promise.dao;

public abstract class DAOFactory {
	public static final int MYSQL = 0;
	public abstract CheckSurveyDAO getCheckSurveyDAO();
	public static DAOFactory getFactory(int type)
	{
		switch(type)
		{
			case MYSQL :
				return new MySQLDAOFactory();
		}
		
		return null;
	}
}
