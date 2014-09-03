package com.verywell.iotp.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.verywell.framework.controller.BaseController;
import com.verywell.framework.dao.HibernateWebUtils;
import com.verywell.framework.dao.Page;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.framework.utils.io.ExcelUtils;
import com.verywell.iotp.admin.constants.RequestNameConstants;
import com.verywell.iotp.admin.dao.CarParkDAO;
import com.verywell.iotp.admin.entity.car.CarPark;
import com.verywell.iotp.admin.service.CarParkService;

@Controller
@RequestMapping("/carParkMgr")
public class CarParkMgrController extends BaseController
{

	// 基础目录
	private final String BASE_DIR = "/car_mgr/";

	@Autowired
	private CarParkService carParkService;

	@Autowired
	private CarParkDAO carParkDao;

	/** 列表查询 */
	@RequestMapping
	public String index(HttpServletRequest request, Page page, Model model) throws Exception
	{
		// 设置默认排序方式
		if (!page.isOrderBySetted())
		{
			page.setOrder(Page.DESC);
			page.setOrderBy("intime");
		}

		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);
		Page pageResult = carParkService.query(page, filters);

		model.addAttribute(RequestNameConstants.PAGE_OBJECT, pageResult);
		return BASE_DIR + "carParkList";
	}

	@RequestMapping(value = "/exportFile")
	public String exportFile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);
		StringBuffer str_time = new StringBuffer((new Date().getMonth() + 1) + "月" + new Date().getDate() + "日_");
		List<CarPark> dataList = this.carParkDao.findByFilters(filters);
		List<String[]> titleAndFieldList = new ArrayList<String[]>();
		titleAndFieldList.add(new String[] { "卡物理号", "cardno" });
		titleAndFieldList.add(new String[] { "卡片编号", "cardid" });
		titleAndFieldList.add(new String[] { "卡片类型", "cardtype" });
		titleAndFieldList.add(new String[] { "卡片状态", "cardstate" });
		titleAndFieldList.add(new String[] { "员工编号", "empno" });
		titleAndFieldList.add(new String[] { "员工姓名", "empname" });
		titleAndFieldList.add(new String[] { "车牌号码", "carlicense" });
		titleAndFieldList.add(new String[] { "入场时间", "intime" });
		titleAndFieldList.add(new String[] { "入场机号", "inmachnum" });
		titleAndFieldList.add(new String[] { "入场地点", "inposition" });
		titleAndFieldList.add(new String[] { "出场时间", "outtime" });
		titleAndFieldList.add(new String[] { "出场机号", "outmachnum" });
		titleAndFieldList.add(new String[] { "出场地点", "outposition" });
		titleAndFieldList.add(new String[] { "停车场名称", "parkname" });
		titleAndFieldList.add(new String[] { "备注", "memo" });

		String sheetName = "场内车辆";
		List<String> dateFieldList = new ArrayList<String>();
		// dateFieldList.add(new String("intime"));
		// dateFieldList.add(new String("outtime"));

		String tempPath = "temp/carParkExport";
		if (!new File(tempPath).exists())
		{
			new File(tempPath).mkdirs();
		}
		String filePath = tempPath + "/" + System.currentTimeMillis() + ".xls";

		OutputStream out = new FileOutputStream(filePath);

		ExcelUtils<CarPark> exportUtils = new ExcelUtils<CarPark>();
		exportUtils.exportExcel(titleAndFieldList, sheetName, dataList, null, dateFieldList, out);
		return "redirect:/servlet/downloadFile?sourceFilePath=" + filePath + "&isDel=true";
	}
}
