package edu.asu.poly.promise.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import edu.asu.poly.promise.dao.*;
import edu.asu.poly.promise.dao.DAOFactory;
import edu.asu.poly.promise.model.*;
import edu.asu.poly.promise.model.SurveyInstance;
import edu.asu.poly.promise.model.SurveyTemplate;

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
        return surveyarray.toJSONString();

	}
	
	public String getsurveyservice(Integer survey_instance_id)
	{
		

    	DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
        GetSurveyDAO getsurvey  = factory.getGetSurveyDAO();
        ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> result=getsurvey.getSurvey(survey_instance_id);
        
        for(SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin instance:result)
        {
        	
        }
        
		return "he";
	}
	
}

