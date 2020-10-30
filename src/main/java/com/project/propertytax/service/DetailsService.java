package com.project.propertytax.service;

import java.util.List;
import java.util.Map;

import com.project.propertytax.exception.EmptyListException;

public interface DetailsService {

	public List<String> allZoneNames() throws EmptyListException;

	public List<String> allDescription() throws EmptyListException;

	public List<String> allStatus() throws EmptyListException;
	
	public Map<String,Map<String,Long>> reportDetails() throws EmptyListException;

}
