package edu.asu.poly.promise.model;

public class SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin {
	
	private SurveyInstance surveyInstance = null;
	private SurveyTemplate surveyTemplate = null;
	private JoinSurveysAndQuestions joinSurveysAndQuestions = null;
	public SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin() {
		surveyInstance = new SurveyInstance();
		surveyTemplate = new SurveyTemplate();
		joinSurveysAndQuestions = new JoinSurveysAndQuestions();
	}
	public SurveyInstance getSurveyInstance() {
		return surveyInstance;
	}
	public void setSurveyInstance(SurveyInstance surveyInstance) {
		this.surveyInstance = surveyInstance;
	}
	public SurveyTemplate getSurveyTemplate() {
		return surveyTemplate;
	}
	public void setSurveyTemplate(SurveyTemplate surveyTemplate) {
		this.surveyTemplate = surveyTemplate;
	}
	public JoinSurveysAndQuestions getJoinSurveysAndQuestions() {
		return joinSurveysAndQuestions;
	}
	public void setJoinSurveysAndQuestions(JoinSurveysAndQuestions joinSurveysAndQuestions) {
		this.joinSurveysAndQuestions = joinSurveysAndQuestions;
	}
	
	
	

}
