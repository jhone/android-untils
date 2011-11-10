package com.redsun.platf.temp;

import org.springside.modules.orm.Page;

import com.redsun.platf.entity.sys.SystemValue;
import com.redsun.platf.service.sys.IBaseEntityManager;
import com.redsun.platf.web.JqEditActionSupport;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
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
 *        2011/3/18 : Created
 *        </p>
 * 
 */

@SuppressWarnings("unchecked")
//@ParentPackage(value = "webmain")
//@Results( { @Result(name = CrudActionSupport.RELOAD, location = "jqsystem-value" ) })
public class JqSystemValueAction extends JqEditActionSupport {

    private static final long serialVersionUID = 3777493451024919986L;
   
    
//    @Resource(name = "systemValueManagaer" )
//      BaseEntityManagerImpl mainManager;
//    
   

   
    // -- 页面属性 --//
    // override
    private SystemValue model = new SystemValue();

    private Page<SystemValue> gridModel = new Page<SystemValue>();// 每页5条记录


    /*
     * (non-Javadoc)
     * 
     * @see com.batchcard.web.CrudActionSupport#prepareModel()
     */
    @Override
    public void prepareModel() {
	// TODO Auto-generated method stub
	getIdList();

	// change to currentID
	if (currentId != null) {

	    model = (SystemValue) mainManager.getDao().get(currentId);
	} else {
	    model = new SystemValue();
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public SystemValue getModel() {
	// TODO Auto-generated method stub
	return model;
    }

   
    /**
     * 显示
     */
    
   
//    @Actions( { @Action(value = "/jqsystem-value-json", results = { @Result(name = "success", type = "json") }) })
    public String whoelist() throws Exception {

	System.out.println(mainManager);
	return  list();
    }

  
    public Page<SystemValue> getGridModel() {
        return gridModel;
    }

    /* (non-Javadoc)
     * @see com.batchcard.web.JqEditActionSupport#getManager()
     */
    @Override
    public IBaseEntityManager getManager() {
	
	
	return mainManager;
    }

//    public BaseEntityManagerImpl getMainManager() {
//        return mainManager;
//    }
//
//    public void setMainManager(BaseEntityManagerImpl mainManager) {
//        this.mainManager = mainManager;
//    }



}
