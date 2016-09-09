package com.taoists.sys.dataDict.controller;
 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.common.Const;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonFrameworkController;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.sys.common.controller.path.ResultPath;
import com.taoists.sys.dataDict.entity.DataDict;
import com.taoists.sys.dataDict.entity.DictCategory;
import com.taoists.sys.dataDict.service.DataDictService;
import com.taoists.sys.dataDict.service.DictCategoryService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Controller
@RequestMapping(ResultPath.sys)
public class DataDictController extends CommonFrameworkController {

	/** ----------------------------  menu ------------------------------ **/	
	/** 菜单：系统属性值列表 **/
	@RequestMapping("/listDataDict/{categoryId}")
	public String list(@PathVariable Long categoryId, Page page, Model model) {
		logger.debug("list: dictCategory.id[{}], page[{}]", categoryId, page);
		
		DictCategory dictCategory = dictCategoryService.get(categoryId);

		DataDict dataDict = new DataDict();
		dataDict.setDictCategory(dictCategory);
		page.setDatas(dataDictService.findDataDictByCategoryId(categoryId));
		model.addAttribute("dictCategory", dictCategory);
		return "sys/datadict/listDataDict";
	}

	/**    ： **/
	@RequestMapping("/category/{categoryCode}")
	public String category(@PathVariable String categoryCode, Page page,
			Model model) {
		logger.debug("list: dictCategory.categoryCode[{}], page[{}]",
				categoryCode, page);

		DictCategory dictCategory = dictCategoryService.getByCode(categoryCode);

		DataDict dataDict = new DataDict();
		dataDict.setDictCategory(dictCategory);
		dataDictService.findDataDictByCategoryCode(categoryCode, page);
		model.addAttribute("dictCategory", dictCategory);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletRequest request, DataDict dataDict, Page page) {
		logger.debug("list: dataDict[{}], page[{}]", dataDict, page);
		dataDictService.findPage(page,
				PropertyFilter.buildFromHttpRequest(request));
		return forward(ViewName.list);
	}
	/**   ：新增属性值(Get) **/
	@RequestMapping("/newDataDict/{categoryId}")
	public String newDataDict(@PathVariable Long categoryId, Model model) {
		logger.debug("editNew: categoryId[{}]", categoryId);
		model.addAttribute("dictCategory", dictCategoryService.get(categoryId));
		return "sys/datadict/newDataDict";
	}

	/**   ：新增属性值(Post) **/
	@RequestMapping(value = "/addDataDict", method = RequestMethod.POST)
	public String addDataDict(Model model, DataDict dataDict) {
		logger.debug("create: dataDict[{}]", dataDict);
		//dataDict.setCompany(getEmployee(model).getCompany());
		dataDictService.save(dataDict);
		return redirect("/sys/listDataDict/" + dataDict.getDictCategory().getId());
	}

	/**   ：编辑属性值(Get) **/
	@RequestMapping("/editDataDict/{id}")
	public String editDataDict(@PathVariable Long id) {
		logger.debug("edit: dataDict.id[{}]", id);
		return "sys/datadict/editDataDict";
	}
	
	/**   ：编辑属性值(Post) **/
	@RequestMapping(value = "/updateDataDict/{dataDict.id}", method = RequestMethod.POST)
	public String updateDataDict(DataDict dataDict) {
		logger.debug("dataDict[{}]", dataDict);
		dataDictService.saveOrUpdate(dataDict);
		return redirect("/sys/listDataDict/" + dataDict.getDictCategory().getId());
	}

	/**   ：删除属性值 **/
	@RequestMapping("/destroyDataDict/{categoryId}/{id}")
	public String destroyDataDict(@PathVariable Long categoryId, @PathVariable Long id) {
		dataDictService.delete(id);
		return redirect("/sys/listDataDict/" + categoryId);
	}

	/** ----------------------------  helper ------------------------------ **/
	
	@ModelAttribute("dataDict")
	public DataDict getDataDict(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getDataDict: request.getRequestURI[{}], id[{}]",
				requestURI, id);

		if (id == null) {
			return new DataDict();
		}
		return dataDictService.get(id);
	}

	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}

	/** ----------------------------  Autowired ------------------------------ **/
	@Autowired
	protected DataDictService dataDictService;
	@Autowired
	protected DictCategoryService dictCategoryService;

}
