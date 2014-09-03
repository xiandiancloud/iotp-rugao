package com.verywell.iotp.admin.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.framework.utils.io.ExcelUtils;
import com.verywell.iotp.admin.entity.car.CarPark;
import com.verywell.iotp.admin.service.CarParkService;

@Service
public class CarParkServiceImpl extends BaseCrudServiceImpl<CarPark, Integer> implements CarParkService
{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Override
	@Qualifier(value = "carParkDAO")
	public void setBaseDao(BaseHibernateDAO<CarPark, Integer> baseDao)
	{
		this.baseDao = baseDao;

	}

	@Override
	public void exportFile(List<PropertyFilter> filters, HttpServletResponse response) throws Exception
	{
		StringBuffer str_time = new StringBuffer((new Date().getMonth() + 1) + "月" + new Date().getDate() + "日_");
		List<CarPark> dataList = this.baseDao.findByFilters(filters);
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

		String fileName = str_time.toString() + sheetName + ".xls";
		ExcelUtils<CarPark> exportUtils = new ExcelUtils<CarPark>();
		exportUtils.exportExcel(titleAndFieldList, sheetName, dataList, null, dateFieldList, out);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
	}

}
