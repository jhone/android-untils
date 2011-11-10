package com.redsun.platf.dao.sys;

import java.util.List;

import org.springside.modules.test.utils.DataUtils;

import com.google.common.collect.Lists;
import com.redsun.platf.entity.account.Authority;
import com.redsun.platf.entity.sys.SystemValue;

/**
 * Account相关实体测试数据生成.
 * 
 * @author calvin
 */
public class SystemValueDataTest {

	public static final String DEFAULT_PASSWORD = "123456";

	private static List<SystemValue> defaultAuthorityList = null;

	/**
	 * 生成随机entity
	 * 
	 * @return
	 */
	public static SystemValue getRandomEntity() {
		String name = DataUtils.randomName("User");

		SystemValue entity = new SystemValue();
		entity.setSysNo(name);
		entity.setDescription(DEFAULT_PASSWORD);
		entity.setSysKey(name + "@org.cn");

		return entity;
	}

	/**
	 * 生成随机entity list
	 * 
	 * @return
	 */

	public static List<SystemValue> getDefaultEntityList() {
		if (defaultAuthorityList == null) {
			defaultAuthorityList = Lists.newArrayList();
			defaultAuthorityList.add(getRandomEntity());
			defaultAuthorityList.add(getRandomEntity());
			defaultAuthorityList.add(getRandomEntity());
			defaultAuthorityList.add(getRandomEntity());
		}
		return defaultAuthorityList;
	}

	/**
	 * 从随机entity list里取一个
	 */
	public static List<SystemValue> getRandomDefaultEntityList() {
		return DataUtils.randomSome(getDefaultEntityList());
	}
}
