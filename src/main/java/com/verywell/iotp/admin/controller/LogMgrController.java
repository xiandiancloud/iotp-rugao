package com.verywell.iotp.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.verywell.framework.controller.BaseController;
import com.verywell.framework.dao.HibernateWebUtils;
import com.verywell.framework.dao.Page;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.framework.dao.PropertyFilter.MatchType;
import com.verywell.iotp.admin.constants.RequestNameConstants;
import com.verywell.iotp.admin.service.SysLogService;

@Controller
@RequestMapping("/logMgr")
public class LogMgrController extends BaseController {

	private final String[] INFORMATION_PARAMAS = { "系统日志" };
	// 基础目录
	private final String BASE_DIR = "/sys_mgr/log_mgr/";

	private final String REDIRECT_PATH = "/logMgr";

	@Autowired
	private SysLogService sysLogService;
	
	/** 列表查询 */
	@RequestMapping
	public String index(HttpServletRequest request, Page page, Model model) throws Exception
	{
		// 设置默认排序方式
		if (!page.isOrderBySetted())
		{
			page.setOrder(Page.DESC);
			page.setOrderBy("operTime");
		}
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);
//		PropertyFilter pf = new PropertyFilter("systemId", MatchType.EQ, this.getLoginToken().getSysCorp().getCorpId());
//		filters.add(pf);
		Page pageResult = sysLogService.query(page, filters);
		model.addAttribute(RequestNameConstants.PAGE_OBJECT, pageResult);
		return BASE_DIR + "log_list";
	}
}
