package com.redsun.platf.temp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.redsun.platf.entity.sys.SystemCompany;
import com.redsun.platf.exception.ServiceException;
import com.redsun.platf.service.sys.impl.SystemManager;
import com.redsun.platf.web.CrudActionSupport;

/**
 * company管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author calvin
 */
//定义URL映射对应/account/user.action
@Namespace("/webmain")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "system-company.action", type = "redirect") })
public class CopyOfSystemCompanyAction extends CrudActionSupport<SystemCompany> {

	private static final long serialVersionUID = 8683878162525847072L;

	@Autowired  SystemManager mainManager;

	//-- 页面属性 --//
	private Long id;
	private SystemCompany entity=new SystemCompany() ;
	private Page<SystemCompany> page = new Page<SystemCompany>(5);//每页5条记录

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	public SystemCompany getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = mainManager.getSystemCompany(id);
		} else {
			entity = new SystemCompany();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = mainManager.searchSystemCompany(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		
		return INPUT;
	}

	@Override
	
	public String save() throws Exception {
		//根据页面上的checkbox选择 整合User的Roles Set
//		HibernateUtils.mergeByCheckedIds(entity.getRoleList(), checkedRoleIds, Role.class);

//	    mainManager.getDaoFactory().getDaoFactory().getSystemCompanyDao().save(entity);
	    mainManager.saveSystemCompany(entity);
	    System.out.println(entity);
	    
		addActionMessage("保存用户成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
//		    mainManager.getDaoFactory().getSystemCompanyDao().delete(id);
		    mainManager.deleteSystemCompany(id)   ;
			addActionMessage("删除用户成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除用户失败");
		}
		return RELOAD;
	}

	//-- 其他Action函数 --//
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
	    String oldLoginName = request.getParameter("old_"+fieldName);
	    logger.info(fieldName+" unique check");
	    
	    if (mainManager.isPropertyUnique(fieldName, newLoginName, oldLoginName)) {
		Struts2Utils.renderText("true");
	    } else {
		Struts2Utils.renderText("false");
	    }
	    //因为直接输出内容而不经过jsp,因此返回null.
	    return null;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<SystemCompany> getPage() {
		return page;
	}

	



	

//	@Autowired
//	public void setAccountManager(MainManager mainManager) {
//		this.mainManager = mainManager;
//	}
}
