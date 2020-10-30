package com.project.propertytax.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.propertytax.entity.Description;
import com.project.propertytax.entity.Uav;
import com.project.propertytax.entity.User;
import com.project.propertytax.entity.Zone;
import com.project.propertytax.exception.ContructionYearException;
import com.project.propertytax.exception.UnableRetrieveException;
import com.project.propertytax.repository.DescriptionRepository;
import com.project.propertytax.repository.UavRepository;
import com.project.propertytax.repository.UserRepository;
import com.project.propertytax.repository.ZoneRepository;
import com.project.propertytax.service.UserService;


@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class UserImpl implements UserService {
 
	private static final Logger LOGGER = LoggerFactory.getLogger(UserImpl.class);

	@Autowired
	ZoneRepository zoneRepository;

	@Autowired
	DescriptionRepository descriptionRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UavRepository uavRespository;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public String insertUser(User user) throws UnableRetrieveException {
		LOGGER.info("To insert user in db");
		Zone zone = zoneRepository.findByName(user.getZone().getName());
		Description description = descriptionRepository
				.findByDescriptionAndStatus(user.getDescription().getDescription(), user.getDescription().getStatus());
		if(zone==null||description==null) {
			throw new UnableRetrieveException("Unable to retreive data from db");
		}
		user.setZone(zone);
		user.setDescription(description);
		
		userRepository.save(user);
		
		return "Successfully Saved";
	}

	@Override
	public float calculateTax(User user) throws ContructionYearException, UnableRetrieveException {
		LOGGER.info("To calculate Total Tax in AJAX call");
		float totalTax=0;
		int squareFeet=Integer.valueOf(user.getArea());
		int constructionYear=Integer.valueOf(user.getConstructedYear());
		
		//throw exception for negative 
		int ageOfBuilding=Integer.valueOf(user.getYearAssessment())-constructionYear;
		if(ageOfBuilding<0) 
		{
			LOGGER.info("consruction year exceeding year of Assessment");
			throw new ContructionYearException();
		}
		Zone zone = zoneRepository.findByName(user.getZone().getName());
		Description description = descriptionRepository
				.findByDescriptionAndStatus(user.getDescription().getDescription(), user.getDescription().getStatus());
		Uav uav=uavRespository.findByDescriptionAndStatus(description.getId(),zone.getId());
		
		if(zone==null||description==null||uav==null) {
			throw new UnableRetrieveException("Unable to retreive data from db");
		}
		//calculate tax
		
		float unitAreaValue=uav.getValue();
		float total1=squareFeet*unitAreaValue*10;
		float total2=0;
		float applicableDepression=ageOfBuilding/100;
		if(ageOfBuilding<60) 
			total2=total1-applicableDepression;
		else
			total2=total1-(float)0.6;
		float total3=(float)(total2*0.20);
		float total4=(float)(total3*0.40);
		totalTax=total3+total4;
		return totalTax;
	}

}
