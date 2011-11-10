package com.redsun.platf.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.orm.Page;

import com.redsun.platf.dao.DataAccessObjectFactory;
import com.redsun.platf.entity.sys.StorageStation;
import com.redsun.platf.web.framework.StandardController;

/**
 * @RequestMapping("/userinfo") 具有层次关系，方法级的将在类一级@RequestMapping之一, 如下面示例,
 *                              访问方法级别的@RequestMapping("/new"),则URL为
 *                              /userinfo/new
 * 
 *                              /userinfo => index() /userinfo/new => _new()
 *                              /userinfo/{id} => show() /userinfo/{id}/edit =>
 *                              edit() /userinfo POST => create() /userinfo/{id}
 *                              PUT => update() /userinfo/{id} DELETE =>
 *                              delete() /userinfo DELETE => batchDelete()
 * 
 */
@Controller
@RequestMapping("/storage")
@Transactional
public class StorageManagerController extends StandardController{
	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null;

	@Resource
	private DataAccessObjectFactory dataFactory;

	private final String LIST_ACTION = "redirect:/storage";

	ModelMap modelMap = new ModelMap();

	HashMap<String, Object> model = new HashMap<String, Object>();

	{
		System.out.println("...created manager");
	}

	/** 所有列表 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexAll() {
		System.out.println("get all from:" + StorageStation.class);
		model.put("test", "1111");
		List<StorageStation> companies = dataFactory.getStorageStationDao().loadAll();
		model.put("companies", companies);
		return new ModelAndView("user/user-list", model);
	}

	/** 所有列表 */
	@RequestMapping(method = RequestMethod.GET, value = "/{pageNumber}/{pageSize}")
	public ModelAndView index(@PathVariable("pageNumber") int pageNumber,
			@PathVariable("pageSize") int pageSize) {

		Page<StorageStation> page = new Page<StorageStation>(pageSize);// 20p
		page.setPageNo(pageNumber);

		// page.setOrder("ck_type ASC");

		page.autoCount(true);

		page =dataFactory.getStorageStationDao().getAll(page);
		System.out.println("index runed from:" + pageNumber);
		System.out.println("index runed to: " + pageSize);
		System.out.println(page.getTotalCount());

		System.out.println(dataFactory.getStorageStationDao());
		List<StorageStation> companies = page.getResult();

		for (StorageStation m : companies) {
		
			System.out.println(m.getName());
		}
		model.put("success", "true");
		 model.put("companies", companies);

		return new ModelAndView("user/user-list",model);// 不要帶/
	}
		
	/** 进入新增 */
	@RequestMapping(value = "/new")
	public ModelAndView _new(HttpServletRequest request,
			HttpServletResponse response, StorageStation model) throws Exception {
		System.out.println("new!! ");

		return new ModelAndView("/new", "userInfo", model);
	}

	/** 显示 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView show(@PathVariable Long entryId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("show .... " + entryId);

		List<StorageStation> users = new ArrayList<StorageStation>();
		// users=(List<User>)userManager.findByName();
		modelMap.put("users", users);
		modelMap.put("msg", "success");
		return new ModelAndView("show", model);
	}

	/** 编辑 */
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView(LIST_ACTION, model);
	}

	/** 保存新增 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response, StorageStation info) throws Exception {
		// userManager.save((IdEntity) userInfo);
		return new ModelAndView(LIST_ACTION);
	}

	/** 保存更新 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ModelAndView update(@PathVariable Long id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// userManager.update(user);
		return new ModelAndView(LIST_ACTION);
	}

	/** 删除 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("deleted runed ");

		return new ModelAndView(LIST_ACTION);
	}

	/** 批量删除 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ModelAndView batchDelete(HttpServletRequest request,
			HttpServletResponse response) {
		// String[] items = request.getParameterValues("items");
		// for(int i = 0; i < items.length; i++) {
		// java.lang.Long id = new java.lang.Long(items[i]);
		// userManager.removeById(id);
		// }
		return new ModelAndView(LIST_ACTION);
	}
}
