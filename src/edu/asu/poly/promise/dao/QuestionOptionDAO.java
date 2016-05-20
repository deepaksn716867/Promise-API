package edu.asu.poly.promise.dao;

import edu.asu.poly.promise.model.QuestionOption;

public interface QuestionOptionDAO {

	/**
	 * This method returns the QuestionOption object for the corresponding option text
	 * @param optionText The option text of the answer
	 * @return The QuestionOption model object 
	 * @throws Exception
	 */
	QuestionOption findByOptionText(String optionText) throws Exception;

}