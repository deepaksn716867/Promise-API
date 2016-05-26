package edu.asu.poly.promise.dao;

import edu.asu.poly.promise.model.ActivePatients;

public interface ActivePatientsDAO {

	ActivePatients findByUserPin(Integer userPin) throws Exception;

}