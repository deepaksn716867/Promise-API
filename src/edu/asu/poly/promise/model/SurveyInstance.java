package edu.asu.poly.promise.model;

import java.sql.Timestamp;

/**
 * This class is the model for the table survey_instance
 * @author Deepak S N
 *
 */
public class SurveyInstance {
	private int id;
	private Timestamp StartTime;
	private Timestamp endTime;
	private Timestamp userSubmissionTime;
	private Timestamp actualSubmissionTime;
	private String state;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;
	private int patientId;
	private int surveyTemplateId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getStartTime() {
		return StartTime;
	}
	public void setStartTime(Timestamp startTime) {
		StartTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getUserSubmissionTime() {
		return userSubmissionTime;
	}
	public void setUserSubmissionTime(Timestamp userSubmissionTime) {
		this.userSubmissionTime = userSubmissionTime;
	}
	public Timestamp getActualSubmissionTime() {
		return actualSubmissionTime;
	}
	public void setActualSubmissionTime(Timestamp actualSubmissionTime) {
		this.actualSubmissionTime = actualSubmissionTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Timestamp getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Timestamp deletedAt) {
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
