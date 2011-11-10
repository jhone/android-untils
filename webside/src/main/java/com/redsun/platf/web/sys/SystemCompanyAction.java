package com.redsun.platf.web.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.redsun.platf.entity.sys.SystemCompany;
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
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "system-company.action", type = "redirect") })
public class SystemCompanyAction extends CrudActionSupport<SystemCompany> {

    private static final long serialVersionUID = 8683878162525847072L;

    @Resource(name = "systemCompanyManagaer")
    IBaseEntityManager mainManager;

    // -- 页面属性 --//
    private Long id;
    private SystemCompany entity = new SystemCompany();
    private Page<SystemCompany> page = new Page<SystemCompany>(5);// 每页5条记录

    // -- ModelDriven 与 Preparable函数 --//
    public void setId(Long id) {
	this.id = id;
    }

    public SystemCompany getModel() {
	return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
	if (id != null) {
	    entity = (SystemCompany) mainManager.getDao().get(id);
	} else {
	    entity = new SystemCompany();
	}
    }

    // -- CRUD Action 函数 --//
    @SuppressWarnings("unchecked")
    @Override
    public String list() throws Exception {
	List<PropertyFilter> filters = PropertyFilter
		.buildFromHttpRequest(Struts2Utils.getRequest());
	// 设置默认排序方式
	if (!page.isOrderBySetted()) {
	    page.setOrderBy("id");
	    page.setOrder(Page.ASC);
	}
	page = mainManager.find(page, filters);
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
	    mainManager.delete(id);
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
    public String checkCompanyNo() {
	return checkFieldUnique("companyNo");
    }

    public String checkCompanyName() {
	return checkFieldUnique("companyName");
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
    public Page<SystemCompany> getPage() {
	return page;
    }

}
