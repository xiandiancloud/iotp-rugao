package com.verywell.iotp.admin.service.impl;

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
import com.verywell.iotp.admin.entity.car.CarOffline;
import com.verywell.iotp.admin.service.CarOfflineService;

@Service
public class CarOfflineServiceImpl extends BaseCrudServiceImpl<CarOffline, Integer> implements CarOfflineService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Override
	@Qualifier(value = "carOfflineDAO")
	public void setBaseDao(BaseHibernateDAO<CarOffline, Integer> baseDao)
	{
		this.baseDao = baseDao;

	}

	@Override
	public void exportFile(List<PropertyFilter> filters,HttpServletResponse response) throws Exception {
		StringBuffer str_time = new StringBuffer((new Date().getMonth() + 1) + "月" + new Date().getDate() + "日_");
		List<CarOffline> dataList = this.baseDao.findByFilters(filters);
		List<String[]> titleAndFieldList = new ArrayList<String[]>();
		titleAndFieldList.add(new String[]{"卡物理号","cardNo"});
		titleAndFieldList.add(new String[]{"卡片编号","cardId"});
		titleAndFieldList.add(new String[]{"卡片类型","cardType"});
		titleAndFieldList.add(new String[]{"卡片状态","cardstate"});
		titleAndFieldList.add(new String[]{"员工编号","empno"});
		titleAndFieldList.add(new String[]{"员工姓名","empname"});
		titleAndFieldList.add(new String[]{"车牌号码","carlicense"});
		titleAndFieldList.add(new String[]{"停车场名称","parkname"});
		titleAndFieldList.add(new String[]{"进出方向","inorout"});
		titleAndFieldList.add(new String[]{"入场时间","inouttime"});
		titleAndFieldList.add(new String[]{"入控制机号","machnum"});
		titleAndFieldList.add(new String[]{"入场地点","inoutposition"});
		
		String sheetName = "车辆出入记录";
		List<String> dateFieldList = new ArrayList<String>();
		dateFieldList.add(new String("inouttime"));
		
		OutputStream out = response.getOutputStream();
		
		String fileName = str_time.toString() +sheetName+ ".xls";
		ExcelUtils<CarOffline> exportUtils = new ExcelUtils<CarOffline>();
		exportUtils.exportExcel(titleAndFieldList, sheetName, dataList, null, dateFieldList, out);
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
	}

}
