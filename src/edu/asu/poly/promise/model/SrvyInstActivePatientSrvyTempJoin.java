package edu.asu.poly.promise.model;

/**
 * This class is the pojo for the join for the tables survey_instance,active_patients and survey_template.
 * @author Deepak S N
 */

public class SrvyInstActivePatientSrvyTempJoin {
	private SurveyInstance surveyInstance = null;
	private ActivePatients activePatients = null;
	private SurveyTemplate surveyTemplate = null;
	
	public SrvyInstActivePatientSrvyTempJoin()
	{
		surveyInstance = new SurveyInstance();
		activePatients = new ActivePatients();
		surveyTemplate = new SurveyTemplate();
	}
	
	public SurveyInstance getSurveyInstance() {
		return surveyInstance;
	}
	public void setSurveyInstance(SurveyInstance surveyInstance) {
		this.surveyInstance = surveyInstance;
	}
	public ActivePatients getActivePatients() {
		return activePatients;
	}
	public void setActivePatients(ActivePatients activePatients) {
		this.activePatients = activePatients;
	}
	public SurveyTemplate getSurveyTemplate() {
		return surveyTemplate;
	}
	public void setSurveyTemplate(SurveyTemplate surveyTemplate) {
		this.surveyTemplate = surveyTemplate;
	}	
}
