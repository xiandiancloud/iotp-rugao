package com.verywell.iotp.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.verywell.framework.dao.PropertyFilter;
import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.car.CarPark;

public interface CarParkService extends BaseCrudService<CarPark, Integer> {

	public void exportFile(List<PropertyFilter> filters,HttpServletResponse response) throws Exception;
}
