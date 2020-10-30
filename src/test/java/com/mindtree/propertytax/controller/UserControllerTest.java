package com.mindtree.propertytax.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.project.propertytax.controller.UserController;
import com.project.propertytax.entity.Description;
import com.project.propertytax.entity.Status;
import com.project.propertytax.entity.Zone;
import com.project.propertytax.repository.DescriptionRepository;
import com.project.propertytax.repository.UavRepository;
import com.project.propertytax.repository.UserRepository;
import com.project.propertytax.repository.ZoneRepository;
import com.project.propertytax.service.DetailsService;
import com.project.propertytax.service.UserService;
import com.project.propertytax.service.impl.DetailsImpl;
import com.project.propertytax.service.impl.UserImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { UserImpl.class, DetailsImpl.class, UserController.class })
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UserController userController;

	@Autowired
	DetailsService detailsService;

	@Autowired
	UserService userService;

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

		mockMvc = MockMvcBuilders.standaloneSetup(userController).setViewResolvers(viewResolver).build();
	}

	@Test
	public void testForm() throws Exception {
		when(zoneRepository.findAll()).thenReturn(getZoneList());
		when(descriptionRepository.findAll()).thenReturn(getDescriptionList());

		this.mockMvc.perform(get("/user")).andExpect(status().isOk()).andExpect(view().name("user"))
				.andExpect(forwardedUrl("/WEB-INF/jsp/user.jsp"));
	}

	@Test
	public void testFormExceptionFlow() throws Exception {
		this.mockMvc.perform(get("/user")).andExpect(redirectedUrl("error")).andExpect(view().name("redirect:error"));
	}


	public List<Zone> getZoneList() {
		List<Zone> zoneList = new ArrayList();
		Zone zone = new Zone();
		zone.setId(1);
		zone.setName("Zone A");
		zoneList.add(zone);
		return zoneList;
	}

	public List<Description> getDescriptionList() {
		List<Description> descList = new ArrayList();
		Description desc = new Description();
		desc.setDescription("RCC Building");
		desc.setStatus(Status.Owner);
		descList.add(desc);
		return descList;
	}
}
