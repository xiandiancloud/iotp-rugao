package com.verywell.iotp.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verywell.framework.commons.ResultInfo;
import com.verywell.framework.controller.BaseController;
import com.verywell.framework.dao.Page;
import com.verywell.iotp.admin.service.DataSyncService;

/**
 * 部门用户数据同步控制器
 * 
 * @author Yao
 * 
 */
@Controller
@RequestMapping("/dataSync")
public class DataSyncController extends BaseController
{

	@Autowired
	private DataSyncService dataSyncService;

	/** 列表查询 */
	@RequestMapping
	@ResponseBody
	public String startSync(HttpServletRequest request, Page page, Model model) throws Exception
	{
		dataSyncService.startSync();
		return "1";
	}
}
