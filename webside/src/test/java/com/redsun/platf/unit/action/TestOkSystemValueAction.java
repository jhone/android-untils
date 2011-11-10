package com.redsun.platf.unit.action;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.redsun.platf.web.sys.SystemValueAction;


public class TestOkSystemValueAction extends StrutsSpringTestCase {
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public String getContextLocations() {
		
		return "classpath*:resources/applicationContext*.xml";
		
	}
	
	
    public void testGetActionMapping() {
        ActionMapping mapping = getActionMapping("/webmain/system-value-json.action");
        assertNotNull(mapping);
        assertEquals("/webmain", mapping.getNamespace());
        assertEquals("system-value-json", mapping.getName());
    }

    
    public void testGetActionProxy() throws Exception {
    	
        //set parameters before calling getActionProxy
        request.setParameter("answer", "3");

        ActionProxy proxy = getActionProxy("/webmain/system-value-json");
        assertNotNull(proxy);

       SystemValueAction action = (SystemValueAction) proxy.getAction();
        assertNotNull(action);
        

        String result = proxy.execute();
        assertEquals("success not returned from calling action class", Action.SUCCESS, result);
//        assertEquals("3 not value of answer", 3, action.getAnswer() );
    
    }
    
    public void testIllegalAnswerValue() throws Exception {
    	
    	request.setParameter("answer", "4");

    	ActionProxy proxy = getActionProxy("/saveAnswer");
    	SystemValueAction action = (SystemValueAction) proxy.getAction();

        String result = proxy.execute();
        assertEquals("input not returned from calling action class", Action.INPUT, result);
        assertTrue("Problem There were no errors present in fieldErrors but there should have been one error present", action.getFieldErrors().size() == 1);
		assertTrue("Problem field answer not present in fieldErrors but it should have been",
				action.getFieldErrors().containsKey("answer") );
   
    }
    
    public void testNoAnswerValue() throws Exception {
    	
       //do not set a value for answer in the request scope    	
    	
    	ActionProxy proxy = getActionProxy("/saveAnswer");
    	SystemValueAction action = (SystemValueAction) proxy.getAction();
    	
    	log.info("key value is " + action.getModel().getSysKey() );

        String result = proxy.execute();
        assertEquals("input not returned from calling action class", Action.INPUT, result);
        assertTrue("Problem There were no errors present in fieldErrors but there should have been one error present", action.getFieldErrors().size() == 1);
		assertTrue("Problem field answer not present in fieldErrors but it should have been",
				action.getFieldErrors().containsKey("answer") );
 
    	
    }
    
    
    public void testExecute() throws Exception {
    	
    	request.setParameter("sysKey", "3");
    	
       	ActionProxy proxy = getActionProxy("/saveAnswer");

        SystemValueAction action = (SystemValueAction) proxy.getAction();
        assertNotNull(action);

        String result = proxy.execute();
        assertEquals("success not returned from calling action class", Action.SUCCESS, result);
    assertEquals("3 not value of key", 3,  action.getModel().getSysKey() );
        
//        log.info("Yes percentage is " + action.getYesPercentage() );
//        log.info("No percentage is " + action.getNoPercentage() );
//        log.info("Not sure percentage is " + action.getNotSurePercentage() );
//        
//        assertEquals("yesPercentage not equal to 70, but it should be", 70, action.getYesPercentage() );
//        assertEquals("noPercentage not equal to 20, but it should be", 20, action.getNoPercentage() );
//        assertEquals("notSurePercentage not equal to 10, but it should be", 10, action.getNotSurePercentage() );
    	
    }
    
   


}
