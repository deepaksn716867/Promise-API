package edu.asu.poly.promise.model;

public class JoinSurveysAndQuestions {

	private int questionOrder;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;
	private int questionTemplateId;
	private int surveyTemplateId;
	
	public int getQuestionOrder() {
		return questionOrder;
	}
	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
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
	public int getQuestionTemplateId() {
		return questionTemplateId;
	}
	public void setQuestionTemplateId(int questionTemplateId) {
		this.questionTemplateId = questionTemplateId;
	}
	public int getSurveyTemplateId() {
		return surveyTemplateId;
	}
	public void setSurveyTemplateId(int surveyTemplateId) {
		this.surveyTemplateId = surveyTemplateId;
	}
	
}
