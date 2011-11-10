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

import com.redsun.platf.entity.sys.SystemTxn;
import com.redsun.platf.service.sys.IBaseEntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class SystemTxnManagerTest implements ApplicationContextAware {

    @Resource
    ApplicationContext applicationContext;

    @Resource(name ="systemTxnManagaer")
    IBaseEntityManager systemManager;

    public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {

	this.applicationContext = applicationContext;
	assertNotNull(applicationContext);
	// System.out.println(applicationContext);
    }

    @Before
    public void setUp() throws Exception {

	systemManager = (IBaseEntityManager) applicationContext
		.getBean("systemTxnManagaer");

	assertNotNull(systemManager);

    }

    @Test
    @Transactional
    public void testFindAll() {
	// String hql = "From User a where a.username='"
	// + userManager.getUsers().get(0).getUsername() + "'";
	// assertEquals(userManager.getUsers(), udao.query(hql));

	System.out.println("list of entity:" + systemManager.getAll());

    }

    @Test
    @Transactional
    public void testSave() {

	SystemTxn entity = new SystemTxn();

	entity.setTxnDesc("txnDesc aaa");
	entity.setTxnNo("kb");
	entity.setTxnName("asfffd");

	
	assert entity != null : "entity is empty";

	systemManager.save(entity);
	
	SystemTxn entity2 = new SystemTxn();

	entity2.setTxnDesc("txnDesc aaa3");
	entity2.setTxnNo("kb3");
	entity2.setTxnName("asfffd3");
	entity2.setParentId(entity);
	
	systemManager.save(entity2);

	System.out.println("----list of entity:" + systemManager.getAll());

    }

    

}
