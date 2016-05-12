package edu.asu.poly.promise.model;

/**
 * This class is the model for the table survey_instance
 * @author Deepak S N
 *
 */
public class SurveyInstance {
	private int id;
	private String StartTime;
	private String endTime;
	private String userSubmissionTime;
	private String actualSubmissionTime;
	private String state;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;
	private int patientId;
	private int surveyTemplateId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserSubmissionTime() {
		return userSubmissionTime;
	}
	public void setUserSubmissionTime(String userSubmissionTime) {
		this.userSubmissionTime = userSubmissionTime;
	}
	public String getActualSubmissionTime() {
		return actualSubmissionTime;
	}
	public void setActualSubmissionTime(String actualSubmissionTime) {
		this.actualSubmissionTime = actualSubmissionTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getSurveyTemplateId() {
		return surveyTemplateId;
	}
	public void setSurveyTemplateId(int surveyTemplateId) {
		this.surveyTemplateId = surveyTemplateId;
	}
}
