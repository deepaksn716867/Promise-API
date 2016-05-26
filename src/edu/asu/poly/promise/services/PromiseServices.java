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
import edu.asu.poly.promise.helper.APIConstants.SrvyState;
import edu.asu.poly.promise.model.*;

public class PromiseServices {

	public static final int BODYPAIN_ID=31;
	public static final String SUCCESS="SUCCESS";
	public static final String FAILURE="FAILURE";

	
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
		    
		    obj.put("surveyTitle", surveytemplate.getName());
		    obj.put("surveyInstanceID",surveyinstance.getId());
		    obj.put("nextDueAt",surveyinstance.getStartTime().toString());
		    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
		    
		    if(timeStamp.compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(surveyinstance.getStartTime()))>0 && timeStamp.compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(surveyinstance.getEndTime()))<0)
		        obj.put("okayToStart",new Boolean(true));
		    else
		        obj.put("okayToStart",new Boolean(false));
		    surveyarray.add(obj);        
		 }
        
	    JSONObject checksurveyreply = new JSONObject();
	    checksurveyreply.put("message", SUCCESS);
	    checksurveyreply.put("surveys", surveyarray);

	    

        return checksurveyreply.toJSONString().replace("\\","");

	}
	
	public String getsurveyservice(Integer survey_instance_id) throws Exception
	{
		
	    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    	DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
        GetSurveyDAO getsurvey  = factory.getSurveyDAO();
        ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> result = null;
        SurveyInstanceDAO surveyinstance= factory.getSurveyInstanceDAO();
        SurveyInstance survey_instance = surveyinstance.findSurveyInstance(survey_instance_id);
        if(survey_instance!=null){
        	if(timeStamp.compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(survey_instance.getStartTime()))<0)
        		return "Survey instance is not active";
        	else if (timeStamp.compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(survey_instance.getEndTime()))>0)
        		return "Survey instance has expired";
        	else if (survey_instance.getState().equals("completed"))
        		return "Survey instance has been completed";
        	else{
		try {
			if(surveyinstance.updateSurveyInstanceState(SrvyState.IN_PROGRESS,survey_instance))
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
	    QuestionOption qo=null;
	    QuestionTemplate qt=null;


	    System.out.println("SIZE"+result.size());
	    int count=0;
	    for(SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin instance:result)
	    {
	    	qo=instance.getQuestionoption();
			qt=instance.getQuestiontemplate();
			
	    	if(previous==qo.getQuestionTemplateId())
	    	{
			    JSONObject obj = new JSONObject();
			    obj.put("answerText", qo.getOptionText());
			    obj.put("answerID", qo.getId());
			    answerarray.add(obj);
			    count++;
			    System.out.println("ADDING"+count);
			    quesID=qo.getQuestionTemplateId();
			    questType=qt.getQuestionType();
			    questText=qt.getQuestionText();
	    	}
	    	else
	    	{
	    	    JSONObject questionoption = new JSONObject();
	    		questionoption.put("answerOptions", answerarray);
	    		questionoption.put("quesID",quesID );
	    		questionoption.put("questionType",questType);
	    		questionoption.put("questionText",questText);
	    		questionarray.add(questionoption);
	    		answerarray = new JSONArray();
	    		JSONObject obj = new JSONObject();
			    obj.put("answerText", qo.getOptionText());
			    obj.put("answerID", qo.getId());
			    count++;
			    System.out.println("ADDING"+count);
			    answerarray.add(obj);
			    previous=qo.getQuestionTemplateId();	
	    	}
	    }
	    JSONObject questionoption = new JSONObject();
		questionoption.put("answerOptions", answerarray);
		questionoption.put("quesID",qo.getQuestionTemplateId() );
		questionoption.put("questionType",qt.getQuestionType());
		questionoption.put("questionText",qt.getQuestionText());
		questionarray.add(questionoption);
		
	    JSONObject surveyreply = new JSONObject();
	    surveyreply.put("questions", questionarray);
	    surveyreply.put("message", SUCCESS);
	    surveyreply.put("surveyName", result.get(0).getSurveyTemplate().getName());
	    surveyreply.put("surveyInstanceID", survey_instance_id);
	    
		return surveyreply.toString().replace("\\","");
	}}
	else
	{
		return "Invalid survey instance ID";
	}
	}
	
	public String submitsurveyservice(String survey_result) throws ParseException, Exception
	{
		
		ArrayList<QuestionResult> questionResult= new ArrayList<QuestionResult>();
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(survey_result);
		SubmitSurveyDAO.SubmitSurvey survey_results = new SubmitSurveyDAO.SubmitSurvey();
		int survey_instance_id= Integer.parseInt(json.get("surveyInstanceID").toString()) ;
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);

		SurveyInstanceDAO surveyinstance= factory.getSurveyInstanceDAO();
        SurveyInstance survey_instance = surveyinstance.findSurveyInstance(survey_instance_id);
        
        if(survey_instance!=null)
        {
			JSONArray question_results=(JSONArray)json.get("surveyResults");
			for(int i=0;i<question_results.size();i++)
			{
				JSONObject result=(JSONObject) question_results.get(i);
				QuestionResult qr_instance_location = new QuestionResult();
				QuestionResult qr_instance_intensity = new QuestionResult();
				int quesid = Integer.parseInt(result.get("quesID").toString());
				if(quesid==BODYPAIN_ID)
				{
					JSONArray bodypain=(JSONArray)result.get("bodyPain");
					JSONObject bodypain_instance=(JSONObject) bodypain.get(0);
					String location= (String) bodypain_instance.get("location");
					int intensity= Integer.parseInt(bodypain_instance.get("intensity").toString());
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
					System.out.println("ARRAY SIZE"+selected_optionsarray.size());
					System.out.println("HERE"+selected_optionsarray.get(0).toString());
					qr_instance.setQuestionOptionId(Integer.parseInt(selected_optionsarray.get(0).toString()));
			        qr_instance.setSurveyInstanceId(survey_instance_id);
			        System.out.println("OPTION"+qr_instance.getQuestionOptionId());
			        questionResult.add(qr_instance);
					
				}			
				
			}
			survey_results.TimeStamp= new Timestamp((long)json.get("timeStamp"));
			survey_results.survey_instance_id= survey_instance_id;
			System.out.println("SIZE"+questionResult.size());
			survey_results.questionResult=questionResult;
			SubmitSurveyDAO submit_survey = factory.getSubmitSurveyDAO();
			Boolean success = submit_survey.SubmitSurvey(survey_results);
			System.out.println(survey_results.questionResult.size());
		    JSONObject reply = new JSONObject();
		    reply.put("statusCode", 500);
		    if(success)
		    	reply.put("message", SUCCESS);
		    else
		    	reply.put("message", FAILURE);
			return reply.toJSONString();
			}
		else
			return "Survey_instance does not exist";
	}
}
	

