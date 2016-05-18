package edu.asu.poly.promise.model;

public class SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin {
	
	private SurveyInstance surveyInstance = null;
	private SurveyTemplate surveyTemplate = null;
	private JoinSurveysAndQuestions joinSurveysAndQuestions = null;
	private QuestionOption questionoption = null;
	private QuestionTemplate questiontemplate = null;
	
	public SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin() 
	{
		surveyInstance = new SurveyInstance();
		surveyTemplate = new SurveyTemplate();
		joinSurveysAndQuestions = new JoinSurveysAndQuestions();
		questionoption = new QuestionOption();
		questiontemplate = new QuestionTemplate();
	}
	
	public QuestionOption getQuestionoption() {
		return questionoption;
	}
	public void setQuestionoption(QuestionOption questionoption) {
		this.questionoption = questionoption;
	}
	public QuestionTemplate getQuestiontemplate() {
		return questiontemplate;
	}
	public void setQuestiontemplate(QuestionTemplate questiontemplate) {
		this.questiontemplate = questiontemplate;
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
