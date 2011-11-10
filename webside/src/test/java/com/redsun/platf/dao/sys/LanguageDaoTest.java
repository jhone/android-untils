package com.redsun.platf.dao.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springside.modules.test.spring.SpringTxTestCase;

import com.redsun.platf.dao.base.IBaseDao;
import com.redsun.platf.entity.sys.SystemLanguage;

/**
 * UserDao的测试用例, 测试ORM映射及特殊的DAO操作.
 * 
 * 默认在每个测试函数后进行回滚.
 * 
 * @author calvin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml"
/*
 * , "classpath*:resources/config/db-datasource-config.xml",
 * "classpath*:resources/config/db-config.xml",
 * "classpath*:resources/config/data-access-objects.xml",
 * "classpath*:resources/config/system-config.xml"
 */
})
public class LanguageDaoTest extends SpringTxTestCase {

	@Resource(name = "languageDao")
	private IBaseDao<SystemLanguage, Long> entityDao;

	// private static DataSource dataSourceHolder = null;
	// @Before
	// public void loadDefaultData() throws Exception {
	// if (dataSourceHolder == null) {
	// DbUnitUtils.loadData(dataSource, "/data/default-data.xml");
	// dataSourceHolder = dataSource;
	// }
	// System.out.println("systemLanguageDao=");
	// System.out
	// .println(this.applicationContext.getBean("systemLanguageDao"));
	// }
	//
	// @AfterClass
	// public static void cleanDefaultData() throws Exception {
	// DbUnitUtils.removeData(dataSourceHolder, "/data/default-data.xml");
	// }

	@Test
	// 如果你需要真正插入数据库,将Rollback设为false
	// @Rollback(false)
	public void crudEntityWithRole() {
		// 新建并保存
		SystemLanguage l = new SystemLanguage();
		l.setDescription("demo2");
		l.setLanguage("en_US");

		entityDao.save(l);
		// // 强制执行sql语句
		entityDao.flush();

		// System.out.println("language saved ok");
		// 获取用户
		List<SystemLanguage> list = entityDao.loadAll();
		assertEquals(1, list.size());
		System.out.println(list);

		// query
		String hql = "from Language a where  a.language=?";
		List<SystemLanguage> s = entityDao.find(hql, "en_US");
		System.out.print("find entity:");
		System.out.println(s);
		assertNotNull(s);

		// 删除用户的角色
		// user.getRoleList().remove(0);
		// entityDao.flush();
		// user = entityDao.findUniqueBy("id", user.getId());
		// assertEquals(0, user.getRoleList().size());
		//
		// 删除
		entityDao.delete(l);
		entityDao.flush();
		l = entityDao.findById(l.getId());
		assertNull(l);

	}

	// 期望抛出ConstraintViolationException的异常.
	// @Test(expected =
	// org.hibernate.exception.ConstraintViolationException.class)
	// public void savenUserNotUnique() {
	// User user = AccountData.getRandomUser();
	// user.setLoginName("admin");
	// entityDao.save(user);
	// entityDao.flush();
	// }

	
}