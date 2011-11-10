package com.redsun.platf.web;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.redsun.platf.entity.IdEntity;
import com.redsun.platf.exception.ServiceException;
import com.redsun.platf.service.sys.IBaseEntityManager;
import com.redsun.platf.util.PropertyFilterUtil;
import com.redsun.platf.util.StringUtil;

/**
 * company管理Action.
 * 
 * 使用Struts2 jquery-grid-plugin annotation定义Action参数 1. extend class 必須提供
 * manager set方法
 * <p>
 * 
 * @Resource(name = "systemValueManagaer")<br>
 *                protected BaseEntityManager mainManager;<br>
 * 
 *                2.model必須為new entity<br>
 *                protected SystemValue model= new SystemValue();<br>
 * 
 * @author joker pan
 */

public abstract class JqEditActionSupport<T> extends CrudActionSupport<T>  {

    private static final long serialVersionUID = 8683878162525847072L;

    @Resource(name = "systemValueManagaer")
    public IBaseEntityManager mainManager;

    // -- 页面属性 --//

    public T model;

    public Page<T> gridModel = new Page<T>();// 每页5条记录

    /**************************** jquery grid ***********************************************/
    // get how many rows we want to have into the grid - rowNum attribute in the
    // grid
    public Integer rows = 5;// 每页5条记录

    // Get the requested page. By default grid sets this to 1.
    public Integer page = 0;

    // sorting order - asc or desc
    public String sord;

    // get index row - i.e. user click to sort.
    public String sidx;

    // Search Field
    public String searchField;

    // The Search String
    public String searchString;

    // he Search Operation
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    public String searchOper;

    // Your Total Pages
    public Integer total = 0;

    // All Records
    public Integer records = 0;

    public boolean loadonce = false;

    /**** edit *********************************************************/
    // selected id list: 2,4,6
    public String id;
    public String oper = "";

    public Long currentId;

    public List<String> idList = new ArrayList<String>();

    // -- ModelDriven 与 Preparable函数 --//
    public void setId(String id) {
	this.id = id;
    }

    // @Override
    // public void prepareModel() throws Exception {
    //	
    // getIdList();
    // // change to currentID
    // if (currentId != null) {
    // entity = (SystemValue) mainManager.getDao().get(currentId);
    // } else {
    // entity = new SystemValue();
    // }
    // }

    /**
     * get current id and idList
     * 
     * @return
     */
    public List<String> getIdList() {

	idList.clear();

	if (id != null && id.contains(",")) {
	    // multi
	    StringTokenizer ids = new StringTokenizer(id, ",");

	    while (ids.hasMoreTokens()) {
		idList.add(ids.nextToken());
	    }
	    currentId = Long.parseLong(idList.get(0));
	} else {
	    // one
	    idList.add(id);
	    if (StringUtil.isNotEmpty(id)) {
		currentId = Long.parseLong(id);
	    } else {
		currentId = null;
	    }

	}
	return idList;
    }

    /**
     * 显示
     */

    @SuppressWarnings("unchecked")
//    @Override
    public String list() throws Exception {
	     if (mainManager==null){
		 throw new IllegalArgumentException("沒有指定super.manager=this.manager!!!");
	     }
	List<PropertyFilter> filters = null;

	logger.debug("Page " + getPage() + " Rows " + getRows()
		+ " Sorting Order " + getSord() + " Index Row :" + getSidx());
	logger.debug(String.format("Search field:%s ,oper:%s ,value:%s ",
		searchField, searchOper, searchString));
	
	
	/************************************************************/
	// must change page size to rows of view

	int pageSize = (rows == null || rows == 0) ? 5 : rows;
	int currentPageno = (page == null || page == 0) ? 1 : page;

	gridModel.setPageSize(pageSize);

	gridModel.setPageNo(currentPageno);

	// 设置默认排序方式
	if (!gridModel.isOrderBySetted()) {
	    gridModel.setOrderBy("id");
	    gridModel.setOrder(Page.ASC);
	}

	// 1.set sord to page

	if (StringUtil.isNotEmpty(sord) && StringUtil.isNotEmpty(sidx)) {
	    sord = sord.toUpperCase();
	    logger.info(String.format("Sorted field:%s ,by %s", sidx, sord));

	    gridModel.setOrderBy(sidx);

	    gridModel.setOrder(sord);
	}

	// 2.filter Search
	if (StringUtil.isNotEmpty(searchString)
		&& StringUtil.isNotEmpty(searchOper)) {

	    filters = PropertyFilterUtil.buildSearchFilter(model.getClass(),
		    searchOper, searchField, searchString);
	} else {
	    filters = PropertyFilter.buildFromHttpRequest(Struts2Utils
		    .getRequest());

	}


	gridModel = mainManager.find(gridModel, filters);

	records = new Integer(Math.round(gridModel.getTotalCount()));
	total = new Integer(Math.round(gridModel.getTotalPages()));

	return SUCCESS;
      
     
    }

    /**
     * 编辑edit operation
     */
    public String edit() throws Exception {

	logger.debug("operation " + oper);
	if (oper != null) {
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
	}

	return SUCCESS;

    }

//    @Override
    public String input() throws Exception {

	return INPUT;
    }

//    @Override
    public String save() throws Exception {

	mainManager.save((IdEntity) model);
	addActionMessage("保存成功");
	return RELOAD;
    }

//    @Override
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
     * 
     * public String checkSysNo() { return checkFieldUnique("sysNo"); }
     * 
     * public String checkSysKey() { return checkFieldUnique("sysKey"); }
     */

    public String checkFieldUnique(String fieldName) {
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

   

    public boolean isLoadonce() {
	return loadonce;
    }

    public String getId() {
	return id;
    }

    /**
     * @return how many rows we want to have into the grid
     */
    public Integer getRows() {
	return rows;
    }

    /**
     * @param rows
     *            how many rows we want to have into the grid
     */
    public void setRows(Integer rows) {
	this.rows = rows;
    }

    /**
     * @return current page of the query
     */
    public Integer getPage() {
	return page;
    }

    /**
     * @param page
     *            current page of the query
     */
    public void setPage(Integer page) {
	this.page = page;
    }

    /**
     * @return total pages for the query
     */
    public Integer getTotal() {
	return total;
    }

    /**
     * @param total
     *            total pages for the query
     */
    public void setTotal(Integer total) {
	this.total = total;
    }

    /**
     * @return total number of records for the query. e.g. select count(*) from
     *         table
     */
    public Integer getRecords() {
	return records;
    }

    /**
     * @param record
     *            total number of records for the query. e.g. select count(*)
     *            from table
     */
    public void setRecords(Integer records) {

	this.records = records;
	if (this.records > 0 && this.rows > 0) {
	    this.total = (int) Math.ceil((double) this.records
		    / (double) this.rows);
	} else {
	    this.total = 0;
	}
    }

    /**
     * @return an collection that contains the actual data
     */
    public Page<T> getGridModel() {
	return gridModel;
    }

    /**
     * @param gridModel
     *            an collection that contains the actual data
     */
    public void setGridModel(Page<T> gridModel) {
	
	System.out.println(gridModel.getResult().get(0).getClass());

	this.gridModel = gridModel;
    }

    /**
     * @return sorting order
     */
    public String getSord() {
	return sord;
    }

    /**
     * @param sord
     *            sorting order
     */
    public void setSord(String sord) {
	this.sord = sord;
    }

    /**
     * @return get index row - i.e. user click to sort.
     */
    public String getSidx() {
	return sidx;
    }

    /**
     * @param sidx
     *            get index row - i.e. user click to sort.
     */
    public void setSidx(String sidx) {
	this.sidx = sidx;
    }

    public void setSearchField(String searchField) {
	this.searchField = searchField;
    }

    public void setSearchString(String searchString) {
	this.searchString = searchString;
    }

    public void setSearchOper(String searchOper) {
	this.searchOper = searchOper;
    }

    public void setLoadonce(boolean loadonce) {
	this.loadonce = loadonce;
    }

    public String getOper() {
	return oper;
    }

    public void setOper(String oper) {
	this.oper = oper;
    }
/**
 * 必須在此將 
 *       super.mainManager = mainManager;
 * @return
 */
    public abstract IBaseEntityManager getManager();
    


  

}
