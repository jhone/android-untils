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

import com.redsun.platf.entity.stock.type.ProductCategory;
import com.redsun.platf.entity.stock.type.StoragePrice;
import com.redsun.platf.entity.sys.StorageStation;
import com.redsun.platf.service.sys.IBaseEntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class StorageManagerTest implements ApplicationContextAware {



    @Resource
    ApplicationContext applicationContext;
   
    @Resource(name ="storageManagaer")
    IBaseEntityManager systemManager;


    public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {

	this.applicationContext = applicationContext;
	assertNotNull(applicationContext);
	// System.out.println(applicationContext);
    }

    @Before
    public void setUp() throws Exception {

	systemManager = (IBaseEntityManager) applicationContext.getBean("storageManagaer");

	assertNotNull(systemManager);

	
	

    }

    @Test
    @Transactional
    public void testFindAll() {
	// String hql = "From User a where a.username='"
	// + userManager.getUsers().get(0).getUsername() + "'";
	// assertEquals(userManager.getUsers(), udao.query(hql));

	System.out.println("list of entity:"
		+ systemManager.getAll());

    }

    @Test
    @Transactional
    public void testSave() {

	StorageStation  entity = new StorageStation();

	entity.setDescription("aaa");
	entity.setCategoryType(ProductCategory.CENTER);
	entity.setPriceType(StoragePrice.SALE);
	entity.setNo("D1");
	entity.setName("D1");

	assert entity != null : "entity is empty";

	systemManager.save(entity);
	
	System.out.println("list of entity:"
		+ systemManager.getAll());
	

    }

  
}
