package com.redsun.platf.unit.action;

import com.redsun.platf.web.sys.SystemValueAction;

public  class TestBaseCase extends  BaseCase<SystemValueAction> {
		 
	private String nameSpace ="/webmain";
	private String actionName ="list"; 
	private String methodName ="list"; 
	
		 public TestBaseCase(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

		/**
		  * Invoke all interceptors and specify value of the action
		  * class' domain objects directly.
		  * @throws Exception Exception
		  */
		 public void testInterceptorsBySettingDomainObjects()
		         throws Exception {
			 actionName ="system-value-json";
			 methodName ="list";
				 
			 SystemValueAction action = createAction(SystemValueAction.class,
					 nameSpace, actionName,methodName);
		  action.setId(1L);
		  String result = proxy.execute();
		  assertEquals(result, "success");
		 }
		 
//		 /**
//		  * Invoke all interceptors and specify value of action class'
//		  * domain objects through request parameters.
//		  * @throws Exception Exception
//		  */
//		 public void testInterceptorsBySettingRequestParameters()
//		                     throws Exception {
//		  createAction(SystemValueAction.class, "/site", "deletePerson","delete");
//		  request.addParameter("id", "1");
//		  String result = proxy.execute();
//		  assertEquals(result, "success");
//		 }
//		 
//		 /**
//		  * Skip interceptors and specify value of action class'
//		  * domain objects by setting them directly.
//		  * @throws Exception Exception
//		  */
//		 public void testActionAndSkipInterceptors() throws Exception {
//			 SystemValueAction action = createAction(SystemValueAction.class,
//		                  "/site", "deletePerson","list");
//		  action.setId(1l);
//		  String result = action.delete();
//		  assertEquals(result, "success");
//		 }
		

}
