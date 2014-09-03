package com.verywell.iotp.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verywell.framework.commons.Tree;
import com.verywell.framework.controller.BaseController;
import com.verywell.framework.dao.HibernateWebUtils;
import com.verywell.framework.dao.Page;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.iotp.admin.constants.RequestNameConstants;
import com.verywell.iotp.admin.entity.dev.DevAlarmLog;
import com.verywell.iotp.admin.entity.dev.DevClassInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.service.DevAlarmLogService;
import com.verywell.iotp.admin.service.DevClassInfoService;
import com.verywell.iotp.admin.service.MapRoomService;

@Controller
@RequestMapping("/devAlarmLogMgr")
public class DevAlarmLogController extends BaseController
{

	private final String[] INFORMATION_PARAMAS = { "设备告警" };
	// 基础目录
	private final String BASE_DIR = "/alarm_mgr/alarm_list/";

	private final String REDIRECT_PATH = "/devAlarmLogMgr";

	@Autowired
	private DevAlarmLogService devAlarmLogService;

	@Autowired
	private DevClassInfoService devClassInfoService;

	@Autowired
	private MapRoomService roomService;

	/** 列表查询 */
	@RequestMapping
	public String index(HttpServletRequest request, Page page, Model model) throws Exception
	{
		// 设置默认排序方式
		if (!page.isOrderBySetted())
		{
			page.setOrder(Page.DESC);
			page.setOrderBy("disposeTime");
		}

		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);
		Map<Long, MapRoomInfo> roomMap = this.getLoginToken().getRoomPermissions();
		Page pageResult = devAlarmLogService.query(page, filters, roomMap);
		List<DevClassInfo> list = devClassInfoService.findAll();
		Tree tree = this.getLoginToken().getRoomTree(request);

		model.addAttribute(RequestNameConstants.PAGE_OBJECT, pageResult);
		model.addAttribute("tree", tree);
		model.addAttribute("classList", list);
		model.addAttribute("classListSize", list.size());
		return BASE_DIR + "list";
	}

	@RequestMapping(value = "/refreshAlarmLog")
	@ResponseBody
	public DevAlarmLog refreshAlarmLog(HttpServletRequest request) throws Exception
	{
		Map<Long, MapRoomInfo> map = this.getLoginToken().getRoomPermissions();
		DevAlarmLog devAlarmLog = this.devAlarmLogService.queryLastTime(map);
		return devAlarmLog;
	}

	@RequestMapping(value = "/precessAlarm")
	@ResponseBody
	public boolean precessAlarm(HttpServletRequest request, Long alarmId) throws Exception
	{
		boolean flag = this.devAlarmLogService.precessAlarm(alarmId);
		return flag;
	}

	@RequestMapping("/alarmList/{roomId}")
	public String alarmList(HttpServletRequest request, Page page, Model model, @PathVariable("roomId") Long roomId) throws Exception
	{
		// 设置默认排序方式
		if (!page.isOrderBySetted())
		{
			page.setOrder(Page.DESC);
			page.setOrderBy("disposeTime");
		}

		Map<Long, MapRoomInfo> roomMap = new HashMap<Long, MapRoomInfo>();
		MapRoomInfo room = roomService.findById(roomId);
		roomMap.put(roomId, room);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		Page pageResult = devAlarmLogService.query(page, filters, roomMap);
		model.addAttribute(RequestNameConstants.PAGE_OBJECT, pageResult);
		return "/iot_mgr/alarm_list";
	}
}
