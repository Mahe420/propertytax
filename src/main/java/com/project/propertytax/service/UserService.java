package com.project.propertytax.service;

import com.project.propertytax.entity.User;
import com.project.propertytax.exception.ContructionYearException;
import com.project.propertytax.exception.UnableRetrieveException;

public interface UserService {

	public String insertUser(User user) throws UnableRetrieveException;

	public float calculateTax(User user) throws ContructionYearException,UnableRetrieveException;

}
