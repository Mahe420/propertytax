	package com.project.propertytax.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.propertytax.controller.MainController;
import com.project.propertytax.entity.Description;
import com.project.propertytax.entity.Status;
import com.project.propertytax.entity.User;
import com.project.propertytax.entity.Zone;
import com.project.propertytax.exception.EmptyListException;
import com.project.propertytax.repository.DescriptionRepository;
import com.project.propertytax.repository.UavRepository;
import com.project.propertytax.repository.UserRepository;
import com.project.propertytax.repository.ZoneRepository;
import com.project.propertytax.service.DetailsService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DetailsImpl implements DetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DetailsImpl.class);

	@Autowired
	ZoneRepository zoneRepository;

	@Autowired
	DescriptionRepository descriptionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UavRepository uavRespository;

	@Override
	public List<String> allZoneNames() throws EmptyListException {
		LOGGER.info("Get all zone names");
		List<Zone> zoneList = zoneRepository.findAll();
		if(zoneList==null||zoneList.isEmpty()) {
			throw new EmptyListException("Unable to retrieve Zone list from db");
		}
		List<String> zoneNameList = zoneList.stream().map(zone -> zone.getName()).collect(Collectors.toList());
		return zoneNameList;
	}

	@Override
	public List<String> allDescription() throws EmptyListException {
		LOGGER.info("Get all description names");
		List<Description> descriptionList = descriptionRepository.findAll();
		if(descriptionList==null||descriptionList.isEmpty()) {
			throw new EmptyListException("Unable to retrieve Description list from db");
		}
		List<String> descriptionNameList = descriptionList.stream().map(description -> description.getDescription())
				.distinct().collect(Collectors.toList());
		return descriptionNameList;
	}

	@Override
	public List<String> allStatus() throws EmptyListException {
		LOGGER.info("Get all status");
		List<Description> descriptionList = descriptionRepository.findAll();
		
		if(descriptionList==null||descriptionList.isEmpty()) {
			throw new EmptyListException("Unable to retrieve Description list from db");
		}
		
		List<String> statusList = descriptionList.stream().map(description -> description.getStatus().toString())
				.distinct().collect(Collectors.toList());
		return statusList;
	}

	@Override
	public Map<String, Map<String, Long>> reportDetails() throws EmptyListException {
		LOGGER.info("To find the Total cost in all Zones");
		List<User> userList = userRepository.findAll();
		if(userList==null||userList.isEmpty()) {
			throw new EmptyListException("Unable to retrieve User List from db");
		}
		List<Zone> zoneList = zoneRepository.findAll();
		if(zoneList==null||zoneList.isEmpty()) {
			throw new EmptyListException("Unable to retrieve Zone List from db");
		}
		Map<String, Map<String, Long>> map = new TreeMap();
		zoneList.forEach(zone -> {
			Map<String, Long> innerMap = new TreeMap();
			Long sum = (long) 0;
			sum = userList.stream().filter(user -> user.getDescription().getStatus().equals(Status.Owner))
					.filter(user -> user.getZone().getName().equals(zone.getName())).map(x -> x.getTotalTax())
					.reduce((long) 0, (a, b) -> a + b);
			innerMap.put("Owner", sum);

			sum = (long) 0;
			sum = userList.stream().filter(user -> user.getDescription().getStatus().equals(Status.Tenanted))
					.filter(user -> user.getZone().getName().equals(zone.getName())).map(x -> x.getTotalTax())
					.reduce((long) 0, (a, b) -> a + b);

			innerMap.put("Tenanted", sum);

			map.put(zone.getName(), innerMap);
		});

		return map;
	}

}
