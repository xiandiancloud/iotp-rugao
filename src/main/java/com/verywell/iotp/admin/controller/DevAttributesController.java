package com.verywell.iotp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.verywell.framework.commons.ResultInfo;
import com.verywell.framework.controller.BaseController;
import com.verywell.framework.dao.HibernateWebUtils;
import com.verywell.framework.dao.Page;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.iotp.admin.constants.RequestNameConstants;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.constants.UrlConstants;
import com.verywell.iotp.admin.entity.dev.DevAttributes;
import com.verywell.iotp.admin.entity.dev.DevClassInfo;
import com.verywell.iotp.admin.service.DevAttributesService;
import com.verywell.iotp.admin.service.DevClassInfoService;

@Controller
@RequestMapping("/devAttributesMgr")
public class DevAttributesController extends BaseController {

	private final String[] INFORMATION_PARAMAS = { "设备属性定义","设备属性名称"};
	// 基础目录
	private final String BASE_DIR = "/dev_mgr/dev_attributes_mgr/";

	private final String REDIRECT_PATH = "/devAttributesMgr";
	
	private final String REDIRECT_PATH_OTHER = "/devClassInfoMgr";
	
	@Autowired
	private DevClassInfoService devClassInfoService;
	
	@Autowired
	private DevAttributesService devAttributesService;
	
	/** 列表查询 */
	@RequestMapping
	public String index(HttpServletRequest request, Page page, Model model) throws Exception
	{
		// 设置默认排序方式
		if (!page.isOrderBySetted())
		{
			page.setOrder(Page.DESC);
			page.setOrderBy("groupId");
		}
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);
		Page pageResult = devAttributesService.query(page, filters);
		model.addAttribute(RequestNameConstants.PAGE_OBJECT, pageResult);
		return BASE_DIR + "list";
	}
	
	/** 进入新增 */
	@RequestMapping(value = "/add/{id}")
	public String add(HttpServletRequest request, Model model,@PathVariable("id") Long devClassId) throws Exception
	{
		DevClassInfo devClassInfo = this.devClassInfoService.findById(devClassId);
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, new DevAttributes());
		model.addAttribute("classInfo", devClassInfo);
		return BASE_DIR + "attributes_add";
	}
	
	/** 保存新增 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, DevAttributes entity,Long devClassId) throws Exception
	{
		DevClassInfo devClassInfo = this.devClassInfoService.findById(devClassId);
		entity.setDevClassInfo(devClassInfo);
		int resultTag = devAttributesService.save(entity);
		if (resultTag == ResultConstants.SAVE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH_OTHER+"/view/"+devClassId);
		}
		else
		{
			model.addAttribute(RequestNameConstants.RESULT_OBJECT, entity);
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}
	
	/** 删除 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, Model model, @PathVariable("id") String id) throws Exception
	{
		DevAttributes devAttributes = devAttributesService.findById(id);
		Long devClassId = devAttributes.getDevClassInfo().getDevClassId();
		int resultTag = devAttributesService.delete(id);
		if (resultTag == ResultConstants.DELETE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH_OTHER+"/view/"+devClassId);
		}
		else
		{
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}

	/** 批量删除 */
	@RequestMapping(value = "/batchDelete")
	public String batchDelete(HttpServletRequest request, Model model, String[] id) throws Exception
	{

		DevAttributes devAttributes = devAttributesService.findById(id[0]);
		Long devClassId = devAttributes.getDevClassInfo().getDevClassId();
		int resultTag = devAttributesService.batchDelete(id);
		if (resultTag == ResultConstants.DELETE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH_OTHER+"/view/"+devClassId);
		}
		else
		{
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}

	/** 进入编辑 */
	@RequestMapping(value = "/edit/{id}")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") String id) throws Exception
	{

		DevAttributes entity = devAttributesService.findById(id);
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, entity);

		return BASE_DIR + "attributes_edit";
	}

	/** 修改保存 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, Model model, DevAttributes entity,Long devClassId) throws Exception
	{
		DevClassInfo devClassInfo = this.devClassInfoService.findById(devClassId);
		entity.setDevClassInfo(devClassInfo);
		int resultTag = devAttributesService.update(entity);
		if (resultTag == ResultConstants.UPDATE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request,REDIRECT_PATH_OTHER+"/view/"+devClassId);
		}
		else
		{
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}
}
