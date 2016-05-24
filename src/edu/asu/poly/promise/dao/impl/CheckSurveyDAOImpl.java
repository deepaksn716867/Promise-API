package edu.asu.poly.promise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import edu.asu.poly.promise.model.*;
import edu.asu.poly.promise.dao.CheckSurveyDAO;
import edu.asu.poly.promise.dao.ConnectionFactory;

/**
 * This class is the DAO Impl for the CheckSurvey endpoint.
 * It checks the pending and in-progress surveys for the particular userpin
 * @author Deepak S N
 * @author Raisa bla
 */

public class CheckSurveyDAOImpl implements CheckSurveyDAO {
	/**
	 * This method is return the an arrayList of the currently available surveys for the particular user pin.
	 * @return an ArrayList which contains each instance of the selected SrvyInstActivePatientSrvyTempJoin. 
	 * @param the user pin of the patient
	 * @throws Exception 
	 */
	public ArrayList<SrvyInstActivePatientSrvyTempJoin> checkSurveys(Integer userPin) throws Exception
	{
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.Get_Connection();
		ArrayList<SrvyInstActivePatientSrvyTempJoin> sryInsActivePtntSrvyTemList = new ArrayList<SrvyInstActivePatientSrvyTempJoin>();
		try
		{
			String query = "SELECT *, si.id FROM survey_instance AS si JOIN active_patients AS pa ON si.patientId = pa.id JOIN survey_template AS st ON si.surveyTemplateId = st.id WHERE pa.pin = ? AND ? BETWEEN si.startTime AND si.endTime AND (si.state = 'pending'OR si.state = 'in progress')ORDER BY si.startTime"; 
			PreparedStatement ps = connection.prepareStatement(query);
			String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//System.out.println("The currentTime is::"+currentTime);
			ps.setInt(1,userPin);
			ps.setString(2, currentTime);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				SrvyInstActivePatientSrvyTempJoin srvyInsActivePtntSrvyTemp = new SrvyInstActivePatientSrvyTempJoin();
				SurveyInstance srvyIns = srvyInsActivePtntSrvyTemp.getSurveyInstance();
				ActivePatients activePtnts = srvyInsActivePtntSrvyTemp.getActivePatients();
				SurveyTemplate srvyTemp = srvyInsActivePtntSrvyTemp.getSurveyTemplate();
				//Since it is join and since we have more than one column having the same name(id),we are using column index instead of column name.
					int i = 1;
					srvyIns.setId(rs.getInt(i++));
					srvyIns.setStartTime(rs.getString(i++));
					srvyIns.setEndTime(rs.getString(i++));
					srvyIns.setUserSubmissionTime(rs.getString(i++));
					srvyIns.setActualSubmissionTime(rs.getString(i++));
					srvyIns.setState(rs.getString(i++));
					srvyIns.setCreatedAt(rs.getString(i++));
					srvyIns.setUpdatedAt(rs.getString(i++));
					srvyIns.setDeletedAt(rs.getString(i++));
					srvyIns.setPatientId(rs.getInt(i++));
					srvyIns.setSurveyTemplateId(rs.getInt(i++));
					activePtnts.setId(rs.getInt(i++));
					activePtnts.setPin(rs.getInt(i++));
					activePtnts.setDeviceType(rs.getString(i++));
					activePtnts.setDeviceVersion(rs.getString(i++));
					activePtnts.setDateStarted(rs.getString(i++));
					activePtnts.setDateCompleted(rs.getString(i++));
					activePtnts.setCreatedAt(rs.getString(i++));
					activePtnts.setUpdatedAt(rs.getString(i++));
					activePtnts.setDeletedAt(rs.getString(i++));
					activePtnts.setStageId(rs.getString(i++));
					srvyTemp.setId(rs.getInt(i++));
					srvyTemp.setName(rs.getString(i++));
					srvyTemp.setCreatedAt(rs.getString(i++));
					srvyTemp.setUpdatedAt(rs.getString(i++));
					srvyTemp.setDeletedAt(rs.getString(i++));
					srvyInsActivePtntSrvyTemp.setActivePatients(activePtnts);
					srvyInsActivePtntSrvyTemp.setSurveyInstance(srvyIns);
					srvyInsActivePtntSrvyTemp.setSurveyTemplate(srvyTemp);
					sryInsActivePtntSrvyTemList.add(srvyInsActivePtntSrvyTemp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		}
			return sryInsActivePtntSrvyTemList;
	}
	public static void main(String args[]) throws Exception
	{
		
		ArrayList<SrvyInstActivePatientSrvyTempJoin> result=new MySQLCheckSurveyDAOImpl().checkSurveys(2002);
		JSONArray surveyarray = new JSONArray();

        for(SrvyInstActivePatientSrvyTempJoin instance:result){
        	
		    SurveyInstance surveyinstance=instance.getSurveyInstance();
		    SurveyTemplate surveytemplate=instance.getSurveyTemplate();        
		    JSONObject obj = new JSONObject();
		    System.out.println(surveytemplate.getName());
		    System.out.println(surveyinstance.getId());
		    System.out.println(surveyinstance.getStartTime());

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
	    checksurveyreply.put("surveys", surveyarray);

		
	    System.out.println("CHECKSURVEY"+checksurveyreply.toJSONString().replace("\\",""));

		ArrayList<SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin> list = new MySQLGetSurveyDAOImpl().getSurveys(1);
		//System.out.println("Length:"+list.size());
	    int previous=list.get(0).getQuestionoption().getQuestionTemplateId();
		JSONArray answerarray = new JSONArray();
		JSONArray questionarray = new JSONArray();
		int quesID=0;
	    String questType="";
	    String questText="";



	    for(SrvyInstSrvyTempJoinSrvyQuestTempQuestOptJoin instance:list)
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
	    surveyreply.put("message", "SUCCESS");
	    surveyreply.put("SurveyName", list.get(0).getSurveyTemplate().getName());
	    surveyreply.put("surveyInstanceID", list.get(0).getSurveyTemplate().getId());
	    surveyreply.put("questions", questionarray);

	    
	    String finalvalue=surveyreply.toString().replace("\\","");
	    System.out.println("GETSURVEY"+finalvalue);
	    
	}
}
