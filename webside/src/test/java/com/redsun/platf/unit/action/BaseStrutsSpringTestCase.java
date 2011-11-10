package com.redsun.platf.unit.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
@ContextConfiguration(locations = "classpath*:resources/applicationContext*.xml")

public class BaseStrutsSpringTestCase extends StrutsSpringTestCase implements
		ApplicationContextAware {

	private static final Log log = LogFactory
			.getLog(BaseStrutsSpringTestCase.class);

	// private ActionProxy proxy;

	/**
	 * implement method
	 */
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		applicationContext = appContext;

	}

	/**
	 * set context attribute
	 * */
	@Override
	protected void setupBeforeInitDispatcher() throws Exception {
		// init context

		if (applicationContext == null) {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"classpath*:resources/applicationContext*.xml"); //
			System.out.println("empty context init:" + ctx);

			log.info("empty context init:" + ctx);
			applicationContext = ctx;
		}

		servletContext.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				applicationContext);

		// System.out.println("ctx of execute:" + applicationContext);
		// System.out.println("reqest:" + request);
		log.info("ctx of execute:" + applicationContext);

	}

	@Override
	public String getContextLocations() {

		return "classpath*:resources/applicationContext*.xml";

	}

	@Test
	public void test() throws Exception {
		HibernateTransactionManager transactionManager=(HibernateTransactionManager)applicationContext
		.getBean("transactionManager");
		System.out.println(" transactionManager bean:" + transactionManager);
	}
	/*
	 * @Test public void testExecute() throws Exception { // getBean();
	 * 
	 * System.out.println("ctx of execute:" + applicationContext);
	 * System.out.println("reqest:" + request);
	 * 
	 * Map<String, Object> mpara = new HashMap<String, Object>();
	 * mpara.put("username", "aa"); // mpara.put("password", "pp");
	 * 
	 * request.setParameters(mpara);
	 * 
	 * // set session attribute
	 * 
	 * // request.getSession().setAttribute(session_attribute, "1234");
	 * System.out.println("...."); proxy = getActionProxy("/listUser.action");
	 * // action = (ListUserAction) proxy.getAction();
	 * System.out.println(action); // String result = proxy.execute();
	 * System.out.println(result);
	 * 
	 * assertTrue(
	 * "Problem There were errors present in fieldErrors but there should not have been any errors present"
	 * , action.getFieldErrors().size() == 0);
	 * 
	 * assertEquals("result ok", "success", result);
	 * 
	 * }
	 */

	/*
	 * demo get bean
	 */
	/*
	 * public void getBean() throws Exception {
	 * 
	 * userManager = (UserManagerService) applicationContext
	 * .getBean("userManager"); System.out.println("manager bean:" +
	 * userManager); // assertNotNull(userManager); // udao = (IUserDao)
	 * applicationContext.getBean("userDao"); System.out.println(udao);
	 * assertNotNull(udao);
	 * 
	 * users = new ArrayList<User>();
	 * 
	 * // stub up a list of users User user = new User();
	 * user.setUsername("username1"); user.setPassword("password");
	 * user.setEmail("email1"); user.setGrade("grade1"); users.add(user);
	 * 
	 * User user2 = new User(); user2.setUsername("username2");
	 * user2.setPassword("password2"); user2.setEmail("email2");
	 * user2.setGrade("grade2");
	 * 
	 * users.add(user2); System.out.println("userlist  init:" + users);
	 * userManager.setUsers(users); }
	 */

	// public String executeAction(Class<?> obj,String actionURL) throws
	// Exception{
	// proxy = getActionProxy(actionURL);
	// //
	// Object action = (obj) proxy.getAction();
	// System.out.println(action);
	// //
	// String result = proxy.execute();
	// return result;
	// }

}
