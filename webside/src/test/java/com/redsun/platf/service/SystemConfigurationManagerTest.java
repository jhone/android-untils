package com.redsun.platf.service;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.redsun.platf.dao.DataAccessObjectFactory;
import com.redsun.platf.service.sys.impl.ApplicationConfigLoader;
import com.redsun.platf.service.sys.impl.MainManager_old;
import com.redsun.platf.sys.SystemConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class SystemConfigurationManagerTest implements ApplicationContextAware {

	private static MainManager_old mainManager;
	
	@Resource
	  ApplicationConfigLoader configManager;
	
	@Resource
	 ApplicationContext applicationContext;
	
	private DataAccessObjectFactory daoFactory;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		this.applicationContext = applicationContext;
		 assertNotNull(applicationContext);
//		 System.out.println(applicationContext);
	}

	@Before
	public void setUp() throws Exception {

	    mainManager = (MainManager_old) applicationContext
				.getBean("mainManager");
		
		 assertNotNull(mainManager);
		
//		 udao = (IBaseDao) applicationContext.getBean("dao");
//		System.out.println(udao);
//		assertNotNull(udao);

//		models = new ArrayList<Themes>();

//		// stub up a list of users
//		User user = new User();
//		user.setUsername("username1");
//		user.setPassword("password");
//		user.setEmail("email1");
//		user.setGrade("grade1");
//		models.add(user);
//
//		User user2 = new User();
//		user2.setUsername("username2");
//		user2.setPassword("password2");
//		user2.setEmail("email2");
//		user2.setGrade("grade2");
//
//		models.add(user2);
//		System.out.println("userlist  init:" + models);
//		userManager.setUsers(models);

	}

	



	@Test
	@Transactional
	public void testFindAll() {
//		String hql = "From User a where a.username='"
//				+ userManager.getUsers().get(0).getUsername() + "'";
//		assertEquals(userManager.getUsers(), udao.query(hql));
	    
	    daoFactory = mainManager.getDaoFactory();
		assertNotNull(daoFactory);
		System.out.println(daoFactory.getSystemConfigurationDao().getAll());

	}

	@Test
	@Transactional
	public void testSave() {
	    SystemConfiguration entity =new SystemConfiguration();
	    entity.setAttachmentPath("path\\\\aa");
	    entity.setDeveloperMode(false);
	    entity.setMailGeneratorUrl("/mailService.do");
	    daoFactory.getSystemConfigurationDao().save(entity);
	    
//	    assertEquals(ok, userManager.SAVED_OK);
	    
		// delete it first
//		for (User u1 : userManager.getUsers()) {
//			List<User> currentList = userManager.findByUsername(u1
//					.getUsername());
//			
//				for (User u2 : currentList) {
//					userManager.delById(u2, u2.getId());
//				}
//			
//		}
//
//		for (User user : userManager.getUsers()) {
//
//			String ok = userManager.save(user);
//			assertEquals(ok, userManager.SAVED_OK);
//			System.out.println("update :" + user.getUsername());
//
//		}

	}

	
}
