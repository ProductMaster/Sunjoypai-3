package com.taoists.sys.dataDict.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.common.Const;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.CommonFrameworkController;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.sys.common.Module;
import com.taoists.sys.common.controller.path.ResultPath;
import com.taoists.sys.dataDict.entity.DictCategory;
import com.taoists.sys.dataDict.service.DataDictService;
import com.taoists.sys.dataDict.service.DictCategoryService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Controller
@RequestMapping(ResultPath.sys)
public class DictCategoryController extends CommonFrameworkController {

	/** ----------------------------  menu ------------------------------ **/
	/** 菜单：系统属性类别列表 **/
	@RequestMapping("/listDictCategory")
	public String listDictCategory(HttpServletRequest request, Page page) {
		logger.debug("listDictCategory");
		
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(request);
		PropertyFilter merchantTypeFilter = new PropertyFilter("GTL_id", new Long(Const.ZERO_VALUE_LONG).toString());
		filters.add(merchantTypeFilter);
		
		dictCategoryService.findPage(page, filters);
		extractParams(request);
		return "sys/datadict/listDictCategory";
	}

	/**   ：新增系统属性类别(Get) **/
	@RequestMapping("/newDictCategory")
	public String newDictCategory() {
		return "sys/datadict/newDictCategory";
	}

	/**   ：新增系统属性类别(Post) **/
	@RequestMapping(value = "/addDictCategory", method = RequestMethod.POST)
	public String addDictCategory(DictCategory dictCategory) {
		logger.debug("create: dictCategory[{}]", dictCategory);

		dictCategoryService.save(dictCategory);
		return redirect("/sys/listDictCategory");
	}

	/**   ：编辑系统属性类别(Get) **/
	@RequestMapping("/editDictCategory/{id}")
	public String editDictCategory(@PathVariable long id) {
		logger.debug("edit: id[{}]", id);
		return "sys/datadict/editDictCategory";
	}

	/**   ：编辑系统属性类别(Post) **/
	@RequestMapping(value = "/updateDictCategory/{dictCategory.id}", method = RequestMethod.POST)
	public String updateDictCategory(DictCategory dictCategory) {
		logger.debug("update: dictCategory[{}]", dictCategory);

		dictCategoryService.saveOrUpdate(dictCategory);
		return redirect("/sys/listDictCategory");
	}

	/**   ：删除系统属性类别 **/
	@RequestMapping("/destroyDictCategory/{id}")
	public String destroyDictCategory(@PathVariable long id) {
		logger.debug("remove: id[{}]", id);

		dictCategoryService.delete(id);
		return redirect("/sys/listDictCategory");
	}

	/** ----------------------------  helper ------------------------------ **/
		
	@ModelAttribute("dictCategory")
	public DictCategory getDictCategory(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getDictCategory: request.getRequestURI[{}], id[{}]",
				requestURI, id);

		if (id == null) {
			return new DictCategory();
		}
		return dictCategoryService.get(id);
	}

	@Override
	protected String getModule() {
		return Module.sys.getName();
	}
	
	/** ----------------------------  Autowired ------------------------------ **/
	
	@Autowired
	protected DataDictService dataDictService;
	@Autowired
	protected DictCategoryService dictCategoryService;


}
