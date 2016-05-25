package edu.asu.poly.promise.model;

import java.sql.Timestamp;

/**
 * This class is the model for the table Active_Patients.
 * @author Deepak S N
 */
public class ActivePatients {
	
	private int id;
	private int pin;
	private String deviceType;
	private String deviceVersion;
	private Timestamp dateStarted;
	private Timestamp dateCompleted;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;
	private int stageId;
	
	public void setDateStarted(Timestamp dateStarted) {
		this.dateStarted = dateStarted;
	}
	public Timestamp getDateStarted() {
		return dateStarted;
	}
	public Timestamp getDateCompleted() {
		return dateCompleted;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public Timestamp getDeletedAt() {
		return deletedAt;
	}
	public int getStageId() {
		return stageId;
	}
	public void setDateCompleted(Timestamp dateCompleted) {
		this.dateCompleted = dateCompleted;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
}
