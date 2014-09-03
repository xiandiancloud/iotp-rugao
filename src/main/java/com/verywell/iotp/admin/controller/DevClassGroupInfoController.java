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
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;
import com.verywell.iotp.admin.service.DevClassGroupInfoService;

@Controller
@RequestMapping("/devClassGroupInfoMgr")
public class DevClassGroupInfoController extends BaseController {

	private final String[] INFORMATION_PARAMAS = { "设备类型分组信息"};
	// 基础目录
	private final String BASE_DIR = "/dev_mgr/dev_class_group_info_mgr/";

	private final String REDIRECT_PATH = "/devClassGroupInfoMgr";
	
	@Autowired
	private DevClassGroupInfoService devClassGroupInfoService;
	
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
		Page pageResult = devClassGroupInfoService.query(page, filters);
		model.addAttribute(RequestNameConstants.PAGE_OBJECT, pageResult);
		return BASE_DIR + "list";
	}
	
	/** 进入新增 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, Model model) throws Exception
	{
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, new DevClassGroupInfo());
		return BASE_DIR + "group_info_add";
	}
	
	/** 保存新增 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, DevClassGroupInfo entity) throws Exception
	{
		int resultTag = devClassGroupInfoService.save(entity);
		if (resultTag == ResultConstants.SAVE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH);
		}
		else
		{
			model.addAttribute(RequestNameConstants.RESULT_OBJECT, entity);
			ResultInfo.saveErrorMessage("分组名称或者分组编号已存在", request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}
	
	/** 删除 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, Model model, @PathVariable("id") Long id) throws Exception
	{

		int resultTag = devClassGroupInfoService.delete(id);
		if (resultTag == ResultConstants.DELETE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH);
		}
		else
		{
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}

	/** 批量删除 */
	@RequestMapping(value = "/batchDelete")
	public String batchDelete(HttpServletRequest request, Model model, Long[] id) throws Exception
	{

		int resultTag = devClassGroupInfoService.batchDelete(id);
		if (resultTag == ResultConstants.DELETE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH);
		}
		else
		{
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}

	/** 进入编辑 */
	@RequestMapping(value = "/edit/{id}")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) throws Exception
	{

		DevClassGroupInfo entity = devClassGroupInfoService.findById(id);
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, entity);

		return BASE_DIR + "group_info_edit";
	}

	/** 修改保存 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, Model model, DevClassGroupInfo entity,Long newGroupId) throws Exception
	{
		int resultTag = devClassGroupInfoService.update(entity);
		if (resultTag == ResultConstants.UPDATE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH);
		}
		else
		{
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}
}
