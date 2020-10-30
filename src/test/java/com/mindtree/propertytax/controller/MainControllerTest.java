package com.mindtree.propertytax.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.project.propertytax.controller.MainController;
import com.project.propertytax.entity.Description;
import com.project.propertytax.entity.Status;
import com.project.propertytax.entity.User;
import com.project.propertytax.entity.Zone;
import com.project.propertytax.repository.DescriptionRepository;
import com.project.propertytax.repository.UavRepository;
import com.project.propertytax.repository.UserRepository;
import com.project.propertytax.repository.ZoneRepository;
import com.project.propertytax.service.DetailsService;
import com.project.propertytax.service.impl.DetailsImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { DetailsImpl.class, MainController.class })
public class MainControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private MainController mainController;

	@Autowired
	DetailsService detailsService;

	@MockBean
	ZoneRepository zoneRepository;

	@MockBean
	DescriptionRepository descriptionRepository;

	@MockBean
	UserRepository userRepository;

	@MockBean
	UavRepository uavRespository;

	@Before
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(mainController).setViewResolvers(viewResolver).build();
	}

	@Test
	public void testError() throws Exception {
		this.mockMvc.perform(get("/error")).andExpect(status().isOk()).andExpect(view().name("error"))
				.andExpect(forwardedUrl("/WEB-INF/jsp/error.jsp"));
	}

	@Test
	public void testIndex() throws Exception {
		this.mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
	}

	@Test
	public void testRedirect() throws Exception {
		this.mockMvc.perform(get("/anyurl")).andExpect(redirectedUrl("index")).andExpect(view().name("redirect:index"));

	}

	@Test
	public void testReport() throws Exception {
		List<Zone> zoneList = new ArrayList<Zone>();
		Zone zone = new Zone();
		zone.setId(1);
		zone.setName("Zone A");
		zoneList.add(zone);
		List userList = setDetails();
		when(userRepository.findAll()).thenReturn(userList);
		when(zoneRepository.findAll()).thenReturn(zoneList);
		this.mockMvc.perform(get("/report")).andExpect(view().name("report"))
				.andExpect(forwardedUrl("/WEB-INF/jsp/report.jsp"));
	}

	@Test
	public void testReportExceptionFlow() throws Exception {
		Map<String, Map<String, Long>> map = new HashMap();
		when(userRepository.findAll()).thenReturn(new ArrayList<User>());
		this.mockMvc.perform(get("/report")).andExpect(redirectedUrl("error")).andExpect(view().name("redirect:error"));
	}
	
	
	

	public List<User> setDetails() {
		Zone zone = new Zone();
		zone.setId(1);
		zone.setName("Zone A");
		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setId(1);
		user.setName("Name");
		user.setZone(zone);
		Description desc = new Description();
		desc.setStatus(Status.Owner);

		user.setDescription(desc);
		user.setTotalTax((long) 1000);
		userList.add(user);

		User user1 = new User();
		user1.setId(2);
		user1.setName("Name");
		user1.setZone(zone);
		Description desc1 = new Description();
		desc1.setStatus(Status.Owner);
		user1.setDescription(desc1);
		user1.setTotalTax((long) 21000);
		userList.add(user1);

		return userList;

	}

}
