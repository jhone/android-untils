package com.redsun.platf.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.redsun.platf.dao.base.IPagedDao;
import com.redsun.platf.dao.sys.SystemAuthorityDao;
import com.redsun.platf.dao.sys.SystemCompanyDao;
import com.redsun.platf.dao.sys.SystemConfigurationDao;
import com.redsun.platf.dao.sys.SystemRoleDao;
import com.redsun.platf.dao.sys.SystemUserDao;
import com.redsun.platf.entity.sys.StorageStation;
import com.redsun.platf.entity.sys.SystemLanguage;
import com.redsun.platf.entity.sys.SystemTheme;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :   所有DAO的集合                </p>
 * <p>Copyright    : Copyright (c) 2011</p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author dick pan
 * @version 1.0
 * @since 1.0
 *        <p>
 *        <H3>Change history</H3>
 *        </p>
 *        <p>
 *        2011/1/25 : Created
 *        </p>
 * 
 */
@Component
//@Transactional //need't just in dao
public class DataAccessObjectFactory implements Serializable {

	private static final long serialVersionUID = -5450000925993172262L;
	private static DataAccessObjectFactory instance;

	public static DataAccessObjectFactory getInstance() {
		if (instance == null)
			instance = new DataAccessObjectFactory();
		return instance;
	}

	// authority
	@Resource(name="systemLanguageDao")
	private IPagedDao<SystemLanguage,Long> systemLanguageDao;

	// authority
	@Resource(name="systemThemeDao")
	private IPagedDao<SystemTheme,Long> systemThemeDao;

	// authority
//	@Resource
	private SystemAuthorityDao systemAuthorityDao;
	// role
//	@Resource
	private SystemRoleDao systemRoleDao;
	// user
//	@Resource
	private SystemUserDao systemUserDao;

//	@Resource
	private SystemConfigurationDao systemConfigurationDao;

//	@Resource
	private SystemCompanyDao systemCompanyDao;

	@Resource(name = "storageStationDao")
	private IPagedDao<StorageStation, Long> storageStationDao;

	public SystemAuthorityDao getSystemAuthorityDao() {
		return systemAuthorityDao;
	}

	public SystemCompanyDao getSystemCompanyDao() {
		return systemCompanyDao;
	}

	public SystemConfigurationDao getSystemConfigurationDao() {
		return systemConfigurationDao;
	}



	public SystemRoleDao getSystemRoleDao() {
		return systemRoleDao;
	}



	public SystemUserDao getSystemUserDao() {
		return systemUserDao;
	}

	public IPagedDao<SystemLanguage, Long> getSystemLanguageDao() {
		return systemLanguageDao;
	}

	public IPagedDao<SystemTheme, Long> getSystemThemeDao() {
		return systemThemeDao;
	}

	public IPagedDao<StorageStation, Long> getStorageStationDao() {
		return storageStationDao;
	}

}
