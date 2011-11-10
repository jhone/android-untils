package com.redsun.platf.service;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.redsun.platf.entity.IdEntity;
import com.redsun.platf.entity.sys.SystemValue;
import com.redsun.platf.service.sys.impl.BaseEntityManagerImpl;

/**
 * SecurityEntityManager的测试用例, 测试Service层的业务逻辑.
 * 
 * 调用实际的DAO类进行操作,亦可使用MockDAO对象将本用例改为单元测试.
 * 
 * @author calvin
 */
public class SystemValueManagerMockTest {

	private IMocksControl control = EasyMock.createControl();

//	private AccountManager accountManager;
//	private SystemValueDao mockDao;
	private HibernateDao<? extends IdEntity, Long> mockDao;
	private SessionFactory sessionFactory;

	private BaseEntityManagerImpl entityManager;
	
	@Before
	public void setUp() {
//		accountManager = new AccountManager();
		entityManager=new BaseEntityManagerImpl();
		
		sessionFactory = control.createMock(SessionFactory.class);
//		mockDao = control.createMock(SystemValueDao.class);
		mockDao =  new HibernateDao(sessionFactory, SystemValue.class);
	
		entityManager.setSessionFactory(sessionFactory);
		entityManager.setDao(mockDao);
//		entityManager.init();
		System.out.println(entityManager);
//		accountManager.setUserDao(mockDao);
	}

	@After
	public void tearDown() {
		control.verify();
	}

	@Test
	public void deleteEntity() {
		mockDao.delete(2L);
		control.replay();

		//正常删除用户.
		entityManager.delete(1L);
		
//		//删除超级管理用户抛出异常.
//		try {
//			entityManager.delete(1L);
//			fail("expected ServicExcepton not be thrown");
//		} catch (ServiceException e) {
//			//expected exception
//		}
	}
}
