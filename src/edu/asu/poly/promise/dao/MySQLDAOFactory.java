package edu.asu.poly.promise.dao;
import edu.asu.poly.promise.dao.impl.MySQLCheckSurveyDAOImpl;
import edu.asu.poly.promise.dao.impl.MySQLGetSurveyDAOImpl;
import edu.asu.poly.promise.dao.impl.MySQLQuestionOptionDAOImpl;
import edu.asu.poly.promise.dao.impl.MySQLSurveyInstanceDAOImpl;
import edu.asu.poly.promise.model.SurveyInstance;

/**
 * This class is the DAO Factory implementation for MySQL.
 * @author Deepak S N
 * @see DAOFactory
 */
public class MySQLDAOFactory extends DAOFactory {
	
	public CheckSurveyDAO getCheckSurveyDAO()
	{
		return new MySQLCheckSurveyDAOImpl();
	}
	
	public GetSurveyDAO getSurveyDAO()
	{
		return new MySQLGetSurveyDAOImpl();
	}

	@Override
	public SurveyInstanceDAO getSurveyInstanceDAO() {
		return new MySQLSurveyInstanceDAOImpl();
	}
	
	@Override
	public QuestionOptionDAO getQuestionOptionDAO()
	{
		return new MySQLQuestionOptionDAOImpl();
	}
	

}
