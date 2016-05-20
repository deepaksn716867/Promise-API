package edu.asu.poly.promise.model;

/**
 * This class is the model for the question_result
 * @author Deepak S N
 */
public class QuestionResult {
	 private int id;
	 private String  createdAt;
	 private String  updatedAt;
	 private String deletedAt;
	 private int surveyInstanceId;
	 private int questionOptionId;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}
	public int getSurveyInstanceId() {
		return surveyInstanceId;
	}
	public void setSurveyInstanceId(int surveyInstanceId) {
		this.surveyInstanceId = surveyInstanceId;
	}
	public int getQuestionOptionId() {
		return questionOptionId;
	}
	public void setQuestionOptionId(int questionOptionId) {
		this.questionOptionId = questionOptionId;
	}
}
