package edu.asu.poly.promise.services;

import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	    checksurveyreply.put("questions ", surveyarray.toString());

	    

        return checksurveyreply.toJSONString().replace("\\","");

	}
	
	public String getsurveyservice(Integer survey_instance_id)
	{
		

    	DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
        GetSurveyDAO getsurvey  = factory.getSurveyDAO();
        ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> result = null;
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
	    		questionoption.put("answerOptions", answerarray.toString());
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
	    surveyreply.put("questions ", questionarray.toString());
	    surveyreply.put("message", "SUCCESS");
	    surveyreply.put("SurveyName", result.get(0).getSurveyTemplate().getName());
	    surveyreply.put("surveyInstanceID", result.get(0).getSurveyTemplate().getId());
	    
		return surveyreply.toString().replace("\\","");
	}
	
}

