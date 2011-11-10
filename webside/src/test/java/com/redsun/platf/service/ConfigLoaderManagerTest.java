package com.redsun.platf.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.redsun.platf.dao.DataAccessObjectFactory;
import com.redsun.platf.entity.sys.SystemLanguage;
import com.redsun.platf.entity.sys.SystemTheme;
import com.redsun.platf.service.sys.impl.ApplicationConfigLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:applicationContext.xml"
		})
public class ConfigLoaderManagerTest implements ApplicationContextAware {

	@Resource
	ApplicationConfigLoader configLoader;

	@Resource
	ApplicationContext applicationContext;

	@Resource
	DataAccessObjectFactory dataAccess;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		this.applicationContext = applicationContext;
		System.out.println(applicationContext);
	}

	@Before
	public void setUp() throws Exception {

		assertNotNull(configLoader);

	}

	@Test
	public void listAll() {
		List<SystemLanguage> languages = new ArrayList<SystemLanguage>();
		List<SystemTheme> themes = new ArrayList<SystemTheme>();
		
		languages = configLoader.getLanguages();
		
		themes = configLoader.getThemes();
		
		for (SystemTheme theme : themes) {
			System.out.println("exists data:" + theme);
			
		}
		
		for (SystemLanguage language : languages) {
			System.out.println("exists language:" + language);
			
		}
	}
	@Test
	public void addTheme() {
		SystemTheme s =new SystemTheme();
		s.setTheme("start");
		s.setDescription("start desc ");
		
		dataAccess.getSystemThemeDao().save(s);
		
		
	}
	@Test
	public void addLanguage() {
		SystemLanguage l =new SystemLanguage();
		l.setCreatedBy("tw");
		l.setDescription("zh_TW");
		
		dataAccess.getSystemLanguageDao().save(l);
		
		
	}

}
