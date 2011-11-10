package com.redsun.platf.web.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.redsun.platf.entity.sys.SystemValue;
import com.redsun.platf.exception.ServiceException;
import com.redsun.platf.service.sys.IBaseEntityManager;
import com.redsun.platf.web.CrudActionSupport;

/**
 * company管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数. 演示带分页的管理界面.
 * 
 * @author calvin
 */
// 定义URL映射对应/webmain/system-company.action,system-company!list.action

@ParentPackage(value = "webmain")
// 定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "system-value", type = "redirect") })
public class SystemValueEditAction extends CrudActionSupport<SystemValue> // implements
// SessionAware
{

    private static final long serialVersionUID = 8683878162525847072L;

    @Resource(name = "systemValueManagaer")
    IBaseEntityManager mainManager;
    // 如果是多選時，id 是多個
    private String id;
    private String oper = "";

    private Long currentId;

    private List<String> idList = new ArrayList<String>();

    // -- 页面属性 --//
    private SystemValue entity = new SystemValue();

    @Override
    protected void prepareModel() throws Exception {
	getIdList();
	// change to currentID
	if (currentId != null) {
	    entity = (SystemValue) mainManager.getDao().get(currentId);
	} else {
	    entity = new SystemValue();
	}
    }

    /**
     * get current id and idList
     * 
     * @return
     */
    private List<String> getIdList() {

	idList.clear();

	if (id != null && id.contains(",")) {
	    // mulit
	    StringTokenizer ids = new StringTokenizer(id, ",");

	    while (ids.hasMoreTokens()) {
		idList.add(ids.nextToken());
	    }

	} else {
	    // one
	    idList.add(id);
	}
	currentId = Long.parseLong(idList.get(0));

	return idList;
    }

    // -- CRUD Action 函数 --//

    /**
     * edit operator
     */
    @Actions( { @Action(value = "/system-value-entry", results = {
	    @Result(location = "webmain/simpleecho.jsp", name = "success"),
	    @Result(location = "webmain/system-value-input", name = "input") }) })
    public String execute() throws Exception {

	if (oper.equalsIgnoreCase("add")) {
	    logger.info("Add Entity");
             save();
	} else if (oper.equalsIgnoreCase("edit")) {
	    logger.info("Edit Entity");
	    save();

	} else if (oper.equalsIgnoreCase("del")) {
	  
	    logger.info("delete Entity");
	    delete();

	}

	return SUCCESS;
    }

    @Override
    public String input() throws Exception {

	 return INPUT;
    }

    @Override
    public String save() throws Exception {

	mainManager.save(entity);
	addActionMessage("保存成功");
	return RELOAD;
    }

    @Override
    public String delete() throws Exception {
	try {
	    getIdList();
	    for (String removeId : idList)
		mainManager.delete(Long.parseLong(removeId));

	    addActionMessage("删除成功");
	} catch (ServiceException e) {
	    logger.error(e.getMessage(), e);
	    addActionMessage("删除失败");
	}
	return RELOAD;
    }

    // -- 其他Action函数 --//
    /**
     * 支持使用Jquery.validate Ajax检验用户名是否重复.
     */
    public String checkSysNo() {
	return checkFieldUnique("sysNo");
    }

    public String checkSysKey() {
	return checkFieldUnique("sysKey");
    }

    private String checkFieldUnique(String fieldName) {
	HttpServletRequest request = ServletActionContext.getRequest();
	String newLoginName = request.getParameter(fieldName);
	String oldLoginName = request.getParameter("old_" + fieldName);
	logger.info(fieldName + " unique check");

	if (mainManager.isPropertyUnique(fieldName, newLoginName, oldLoginName)) {
	    Struts2Utils.renderText("true");
	} else {
	    Struts2Utils.renderText("false");
	}
	// 因为直接输出内容而不经过jsp,因此返回null.
	return null;
    }

    // -- 页面属性访问函数 --//
    /**
     * list页面显示用户分页列表.
     */
    // public Page<SystemValue> getPage() {
    // return pageModel;
    // }

    public SystemValue getEntity() {
	return entity;
    }

    public void setEntity(SystemValue entity) {
	this.entity = entity;
    }

    public void setOper(String oper) {
	this.oper = oper;
    }

    public String getId() {
	// System.out.println("id:=" + id);
	return id;
    }

    public void setId(String id) {
//	System.out.println("set id:=" + id);
	this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    @Override
    public SystemValue getModel() {
	// TODO Auto-generated method stub
	return entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.batchcard.web.CrudActionSupport#list()
     */
    @Override
    public String list() throws Exception {
	// TODO Auto-generated method stub
	return SUCCESS;
    }

}
