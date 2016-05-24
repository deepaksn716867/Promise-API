package edu.asu.poly.promise.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.asu.poly.promise.dao.*;
import edu.asu.poly.promise.dao.DAOFactory;
import edu.asu.poly.promise.model.*;

public class PromiseServices {

	
	public String checksurveyservice(Integer pin) throws Exception
	{
		JSONArray surveyarray = new JSONArray();

    	DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
        CheckSurveyDAO checksurvey  = factory.getCheckSurveyDAO();
        ArrayList<SrvyInstActivePatientSrvyTempJoin> result=checksurvey.checkSurveys(pin);
        
        for(SrvyInstActivePatientSrvyTempJoin instance:result){
        	
		    SurveyInstance surveyinstance=instance.getSurveyInstance();
		    SurveyTemplate surveytemplate=instance.getSurveyTemplate();        
		    JSONObject obj = new JSONObject();
		    
		    obj.put("SurveyTitle", surveytemplate.getName());
		    obj.put("SurveyInstanceID",surveyinstance.getId());
		    obj.put("NextDueAt",surveyinstance.getStartTime());
		    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
		    
		    if(timeStamp.compareTo(surveyinstance.getStartTime())>0 && timeStamp.compareTo(surveyinstance.getEndTime())<0)
		        obj.put("okayToStart",new Boolean(true));
		    else
		        obj.put("okayToStart",new Boolean(false));
		    surveyarray.add(obj);        
		 }
        
	    JSONObject checksurveyreply = new JSONObject();
	    checksurveyreply.put("message", "SUCCESS");
	    checksurveyreply.put("questions ", surveyarray);

	    

        return checksurveyreply.toJSONString().replace("\\","");

	}
	
	public String getsurveyservice(Integer survey_instance_id) throws Exception
	{
		

    	DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
        GetSurveyDAO getsurvey  = factory.getSurveyDAO();
        ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> result = null;
        SurveyInstanceDAO surveyinstance= factory.getSurveyInstanceDAO();
        if(surveyinstance.findSurveyInstance(survey_instance_id)!=null){
		try {
			result = getsurvey.getSurveys(survey_instance_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    int previous=result.get(0).getQuestionoption().getQuestionTemplateId();
		JSONArray answerarray = new JSONArray();
		JSONArray questionarray = new JSONArray();
		int quesID=0;
	    String questType="";
	    String questText="";



	    for(SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin instance:result)
	    {
	    	QuestionOption qo=instance.getQuestionoption();
			QuestionTemplate qt=instance.getQuestiontemplate();
			
	    	if(previous==qo.getQuestionTemplateId())
	    	{
			    JSONObject obj = new JSONObject();
			    obj.put("answerText", qo.getOptionText());
			    obj.put("answerID", qo.getId());
			    answerarray.add(obj);
			    quesID=qo.getQuestionTemplateId();
			    questType=qt.getQuestionType();
			    questText=qt.getQuestionText();
	    	}
	    	else
	    	{
	    	    JSONObject questionoption = new JSONObject();
	    		questionoption.put("answerOptions", answerarray);
	    		questionoption.put("quesID",quesID );
	    		questionoption.put("quesType",questType);
	    		questionoption.put("quesText",questText);
	    		questionarray.add(questionoption);
	    		answerarray = new JSONArray();
	    		JSONObject obj = new JSONObject();
			    obj.put("answerText", qo.getOptionText());
			    obj.put("answerID", qo.getId());
			    answerarray.add(obj);
			    previous=qo.getQuestionTemplateId();
			    
	    		
	    		

	    	}
	    }
	    JSONObject surveyreply = new JSONObject();
	    surveyreply.put("questions ", questionarray);
	    surveyreply.put("message", "SUCCESS");
	    surveyreply.put("SurveyName", result.get(0).getSurveyTemplate().getName());
	    surveyreply.put("surveyInstanceID", result.get(0).getSurveyTemplate().getId());
	    
		return surveyreply.toString().replace("\\","");
	}
	else
	{
		return "ERROR";
	}
	}
	
	public String submitsurveyservice(String survey_result) throws ParseException, Exception
	{
		
		ArrayList<QuestionResult> questionResult= new ArrayList();
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(survey_result);
		SubmitSurveyDAO.SubmitSurvey survey_results = new SubmitSurveyDAO.SubmitSurvey();
		int survey_instance_id=(int) json.get("surveyInstanceID");
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);


		JSONArray question_results=(JSONArray)json.get("SurveyResults");
		for(int i=0;i<question_results.size();i++)
		{
			JSONObject result=(JSONObject) question_results.get(0);
			QuestionResult qr_instance_location = new QuestionResult();
			QuestionResult qr_instance_intensity = new QuestionResult();
			int quesid = (int) result.get("quesid");
			if(quesid==31)
			{
				JSONArray bodypain=(JSONArray)json.get("bodyPain");
				JSONObject bodypain_instance=(JSONObject) bodypain.get(0);
				String location= (String) bodypain_instance.get("location");
				int intensity= (int) bodypain_instance.get("intensity");
		        QuestionOptionDAO questionOption  = factory.getQuestionOptionDAO();
		        QuestionOption q_option = questionOption.findByOptionText(location);
		        qr_instance_location.setQuestionOptionId(q_option.getId());
		        qr_instance_location.setSurveyInstanceId(survey_instance_id);
		        q_option = questionOption.findByOptionText(Integer.toString(intensity));
		        qr_instance_intensity.setQuestionOptionId(q_option.getId());
		        qr_instance_intensity.setSurveyInstanceId(survey_instance_id);
		        questionResult.add(qr_instance_location);
		        questionResult.add(qr_instance_intensity);				
			
			}
			else
			{
				QuestionResult qr_instance = new QuestionResult();
				JSONArray selected_optionsarray = (JSONArray) result.get("selectedOptions");
				qr_instance.setQuestionOptionId((int) selected_optionsarray.get(0));
		        qr_instance.setSurveyInstanceId(survey_instance_id);
		        questionResult.add(qr_instance);
				
			}
			
			survey_results.TimeStamp=(Timestamp) json.get("timeStamp");
			survey_results.survey_instance_id= survey_instance_id;
			survey_results.questionResult.addAll(questionResult);
			SubmitSurveyDAO submit_survey = factory.getSubmitSurveyDAO();
			Boolean success = submit_survey.SubmitSurvey(survey_results);
			
		}
		
		return "hello";
	}
}

