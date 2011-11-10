package com.redsun.platf.unit.action;

/**
 * 
 * to run  test 
 * 1.spring.jar .spring-test.jar 
 * 2.struts-junit-plugin.jar, struts-spring-plugin.jar
 * 3.in springContext.xml define action bean like:json_2.action
 * 4.junit4.4.jar (don't user more than 4.5 for spring2.56-test)
 * 
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.orm.Page;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.redsun.platf.entity.sys.SystemValue;
import com.redsun.platf.service.sys.IBaseEntityManager;
import com.redsun.platf.service.sys.impl.BaseEntityManagerImpl;
import com.redsun.platf.temp.JqSystemValueAction;

@ContextConfiguration(locations = "classpath*:resources/applicationContext*.xml")
//@ContextConfiguration(locations = "applicationContext.xml")
public class SystemValueActionTest extends BaseStrutsSpringTestCase {
//	@Resource(name = "systemValueManagaer")
	IBaseEntityManager mainManager;
	private ActionProxy proxy;

	private JqSystemValueAction action;


	
	

	private String actionURL = "";
	private String actionBeanName = "jqSystemValue";
	private String actionResult = ActionSupport.SUCCESS;

//	@Before
	public void setbeforeUp() throws Exception {

		System.out.println("ctx of execute:" + applicationContext);
		// System.out.println("reqest:" + request);
		IBaseEntityManager mainManager=(IBaseEntityManager)applicationContext
		.getBean("abstractManagaer");
		System.out.println(" m bean:" + mainManager);
//		JqSystemValueAction userAction = (JqSystemValueAction) applicationContext
//				.getBean(actionBeanName);
//		System.out.println(" action bean:" + userAction);

	}

	@Test
	public void testList() throws Exception {
		 setbeforeUp();
//		actionURL = "/webmain/jq_sytem_value_list.action";
//
//		Map<String, Object> mpara = new HashMap<String, Object>();
//
//		// mpara.put("language", "zh_CN");
//		mpara.put("request_locale", "zh_CN");
//		// add
//		mpara.put("oper", "");
//		mpara.put("id", "1");
//		mpara.put("sysKey", "demo-01");
//
//		request.setParameters(mpara);
//
//		// set session attribute
//
//		// request.getSession().setAttribute(session_attribute, "1234");
//		System.out.println("step 1-----------------------------");
//		System.out.println("start go url");
//
//		proxy = getActionProxy(actionURL);
//
//		action = (JqSystemValueAction) proxy.getAction();
//		
//		
//				
//		System.out.println(action);
//
//		String result = proxy.execute();
//
//		/*
//		 * Enumeration list =action.getSession().getAttributeNames(); while
//		 * (list.hasMoreElements()){ System.out.println(list.nextElement()); }
//		 */
//		System.out.println("step 2-----------------------------");
//
//		// action.getServletContext().getContext(request);
//		System.out.println("request.locale:" + request.getLocale());
//		System.out.println("response.locale:" + response.getLocale());
//		System.out.println("session.language:"
//				+ request.getAttribute("language"));
//
//		System.out.println(result);
//
//		Page<SystemValue> model = action.getGridModel();
//
//		System.out.println("step 3-----------------------------");
//		System.out.println("action list size  :" + model.getResult().size());
//
//		List<SystemValue> results = model.getResult();
//		for (SystemValue entity : results) {
//			System.out.println("entity  of  :" + entity.getId()
//					+ entity.getSysNo());
//		}
//
//		assertTrue(
//				"Problem There were errors present in fieldErrors but there should not have been any errors present",
//				action.getFieldErrors().size() == 0);
//		assertEquals("result ok", actionResult, result);
//
	}

	//
	// public void testUseridErrorMessage() { //
	// request.setParameter("userid", "aa");
	// request.setParameter("password", "aa");
	// request.setParameter("txt_code", "1234");
	// request.getSession().setAttribute(session_attribute, "1234");
	//
	// proxy = getActionProxy(actionURL);
	//
	// action = (RegisterAction) proxy.getAction();
	//
	// try {
	//
	// proxy.execute();
	//
	// } catch (Exception e) { // TODO Auto-generated catch block
	// e.printStackTrace();
	// } // fail("Not yet implemented");
	// assertTrue("problem field userid not present", action.getFieldErrors()
	// .containsKey("userid"));
	//
	// }
	//
	// @Test
	// public void testCodeErrorMessage() {
	// request.setParameter("userid", "aa");
	// request.setParameter("password", "aa");
	// request.setParameter("txt_code", "11");
	// request.getSession().setAttribute(session_attribute, "1234");
	//
	// proxy = getActionProxy(actionURL);
	//
	// action = (RegisterAction) proxy.getAction();
	//
	// try {
	//
	// proxy.execute();
	//
	// } catch (Exception e) { // TODO Auto-generated catch block
	// e.printStackTrace();
	// } // fail("Not yet implemented");
	// assertTrue("problem field userid not present", action.getFieldErrors()
	// .containsKey("txt_code"));
	//
	// }

}
