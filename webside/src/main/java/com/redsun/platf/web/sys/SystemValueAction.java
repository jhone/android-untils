package com.redsun.platf.web.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.redsun.platf.entity.sys.SystemValue;
import com.redsun.platf.exception.ServiceException;
import com.redsun.platf.service.sys.IBaseEntityManager;
import com.redsun.platf.util.PropertyFilterUtil;
import com.redsun.platf.util.StringUtil;
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
public class SystemValueAction extends CrudActionSupport<SystemValue> {

	private static final long serialVersionUID = 8683878162525847072L;

	@Resource(name = "systemValueManagaer")
	IBaseEntityManager mainManager;

	// -- 页面属性 --//
	private Long id;

	private SystemValue entity = new SystemValue();

	private Page<SystemValue> gridModel = new Page<SystemValue>();// 每页5条记录
	/**************************** jquery grid ***********************************************/
	// get how many rows we want to have into the grid - rowNum attribute in the
	// grid
	private Integer rows = 0;

	// Get the requested page. By default grid sets this to 1.
	private Integer page = 0;

	// sorting order - asc or desc
	private String sord;

	// get index row - i.e. user click to sort.
	private String sidx;

	// Search Field
	private String searchField;

	// The Search String
	private String searchString;

	// he Search Operation
	// ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String searchOper;

	// Your Total Pages
	private Integer total = 0;

	// All Records
	private Integer records = 0;

	private boolean loadonce = false;

	// -- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	public SystemValue getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = (SystemValue) mainManager.getDao().get(id);
		} else {
			entity = new SystemValue();
		}
	}

	// -- CRUD Action 函数 --//

	@Actions( { @Action(value = "/system-value-json", results = { @Result(name = "success", type = "json"

	) }) })
	// @Actions( { @Action(value = "/sys_value_json", results = { @Result(name =
	// "success", type = "json", params = {
	// "root", "pageModel" }) }) })
	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {

		List<PropertyFilter> filters = null;

		logger.debug("Page " + getPage() + " Rows " + getRows()
				+ " Sorting Order " + getSord() + " Index Row :" + getSidx());
		logger.debug(String.format("Search field:%s ,oper:%s ,value:%s ",
				searchField, searchOper, searchString));
		/************************************************************/
		// must change page size to rows of view

		gridModel.setPageSize(rows);
		gridModel.setPageNo(page);

		// 设置默认排序方式
		if (!gridModel.isOrderBySetted()) {
			gridModel.setOrderBy("id");
			gridModel.setOrder(Page.ASC);
		}

		// 1.set sord to page
		sord = sord.toUpperCase();
		if (StringUtil.isNotEmpty(sord) && StringUtil.isNotEmpty(sidx)) {

			logger.info(String.format("Sorted field:%s ,by %s", sidx, sord));

			gridModel.setOrderBy(sidx);

			gridModel.setOrder(sord);
		}

		// 2.filter Search
		if (StringUtil.isNotEmpty(searchString)
				&& StringUtil.isNotEmpty(searchOper)) {

			filters = PropertyFilterUtil.buildSearchFilter(entity.getClass(),
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

	@Override
	public String input() throws Exception {

		// return INPUT;
		return null;
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

	public Page<SystemValue> getPageModel() {
		return gridModel;
	}

	public void setPageModel(Page<SystemValue> pageModel) {
		this.gridModel = pageModel;
	}

	public boolean isLoadonce() {
		return loadonce;
	}

	public Long getId() {
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
	public Page<SystemValue> getGridModel() {
		return gridModel;
	}

	/**
	 * @param gridModel
	 *            an collection that contains the actual data
	 */
	public void setGridModel(Page<SystemValue> gridModel) {
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

}
