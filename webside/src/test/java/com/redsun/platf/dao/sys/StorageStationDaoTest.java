package com.redsun.platf.dao.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springside.modules.test.spring.SpringTxTestCase;

import com.redsun.platf.dao.base.IBaseDao;
import com.redsun.platf.entity.sys.StorageStation;

/**
 * storageStationDao的测试用例, 测试ORM映射及特殊的DAO操作.
 * 
 * 默认在每个测试函数后进行回滚.
 * 
 * @author calvin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class StorageStationDaoTest extends SpringTxTestCase {

	@Resource(name = "storageStationDao")
	private IBaseDao<StorageStation, Long> entityDao;

	StorageStation l = new StorageStation();

	@Before
	public void initEntity() {
		l.setNo("D1");
		l.setName("product station");
		l.setDescription("china beijing");
		saveEntity();
	}
	private void saveEntity() {
		// 新建并保存
		entityDao.save(l);
		// 强制执行sql语句
		entityDao.flush();
		System.out.println(" saved ok");
	}

	
	@Test
	// 如果你需要真正插入数据库,将Rollback设为false
	// @Rollback(false)
	public void queryEntity() {

		// 获取all
		List<StorageStation> dataList = entityDao.loadAll();
		System.out.println(dataList);
		assertEquals(1, dataList.size());

	}

	@Test
	public void deleteEntity() {

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
		System.out.println(" deleted ok");
		assertNull(l);
	}

	@Test
	public void findEntity() {
		List<StorageStation> dataList;
		// 获取1
		String hql = "from StorageStation a where  a.no=?";
	
		dataList = entityDao.find(hql, "D1");
		System.out.print("find entity:");
		System.out.println(dataList);
		assertNotNull(dataList);
	}

	

	// 期望抛出ConstraintViolationException的异常.
	@Test(expected = org.hibernate.exception.ConstraintViolationException.class)
	public void savenUserNotUnique() {
		entityDao.save(l);
		entityDao.flush();
	}
}