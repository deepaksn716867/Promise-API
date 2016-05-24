package edu.asu.poly.promise.dao;

/**
 * This is an abstract factory class for instantiating objects for the DAO.
 * @author Deepak S N
 */
public abstract class DAOFactory {
	public static final int MYSQL = 0;
	public abstract CheckSurveyDAO getCheckSurveyDAO();
	public abstract GetSurveyDAO getSurveyDAO();
	public abstract SurveyInstanceDAO getSurveyInstanceDAO();
	public abstract QuestionOptionDAO getQuestionOptionDAO();
	public abstract SubmitSurveyDAO getSubmitSurveyDAO();

	/**
	 * This method is a static method which will return the corresponding factory object depending on the type.
	 * @param type The type to get appropriate factory method.
	 * @return The corresponding implementation for the DAO Factory. 
	 */
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
