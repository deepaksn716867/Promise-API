package edu.asu.poly.promise.model;

public class QuestionOption {
	
	private int id;
	private String optionText;
	private int order;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;
	private int questionTemplateId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOptionText() {
		return optionText;
	}
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
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
	

	
	
}
