package com.redsun.platf.service.entitybase;

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

import com.redsun.platf.entity.sys.SystemCompany;
import com.redsun.platf.service.sys.IBaseEntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class CompanyManagerTest implements ApplicationContextAware {



    @Resource
    ApplicationContext applicationContext;
   
    @Resource(name="systemCompanyManagaer")
    IBaseEntityManager systemManager;


    public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {

	this.applicationContext = applicationContext;
	assertNotNull(applicationContext);
	// System.out.println(applicationContext);
    }

    @Before
    public void setUp() throws Exception {

	systemManager = (IBaseEntityManager) applicationContext.getBean("systemCompanyManagaer");

	assertNotNull(systemManager);

	
	

    }

    @Test
    @Transactional
    public void testFindAll() {
	// String hql = "From User a where a.username='"
	// + userManager.getUsers().get(0).getUsername() + "'";
	// assertEquals(userManager.getUsers(), udao.query(hql));

	System.out.println("list of company:"
		+ systemManager.getAll());

    }

    @Test
    @Transactional
    public void testSave() {

	SystemCompany entity = new SystemCompany();

	entity.setCompanyNo("aaa");
	entity.setCompanyName("aaaaaaa");
	entity.setBankName("aaaaa");
	entity.setEmail("aaaaaaa");
	entity.setFax("aaaaaaa");
	entity.setTaxNo("aaaa");
	entity.setTel("tel");

	assert entity != null : "entity is empty";

//	daoFactory.getSystemCompanyDao().save(entity);
	systemManager.getDao().getSession().saveOrUpdate(entity);
	
	System.out.println("list of company:"
		+ systemManager.getAll());
	// assertEquals(ok, userManager.SAVED_OK);

	// delete it first
	// for (User u1 : userManager.getUsers()) {
	// List<User> currentList = userManager.findByUsername(u1
	// .getUsername());
	//			
	// for (User u2 : currentList) {
	// userManager.delById(u2, u2.getId());
	// }
	//			
	// }
	//
	// for (User user : userManager.getUsers()) {
	//
	// String ok = userManager.save(user);
	// assertEquals(ok, userManager.SAVED_OK);
	// System.out.println("update :" + user.getUsername());
	//
	// }

    }

  

}
