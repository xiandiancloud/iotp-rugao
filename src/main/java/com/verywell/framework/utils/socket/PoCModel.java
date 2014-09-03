package com.verywell.framework.utils.socket;

import java.util.HashMap;

/**
 * 
 * @author Song XueYong
 * 
 */
public class PoCModel {

	public static PoCModel getPocModel() {
		if (model == null) {
			model = new PoCModel();
		}
		return model;
	}

	private static PoCModel model = new PoCModel();
	private HashMap<String, PocDev> data = new HashMap<String, PocDev>();
	
	private String Dev_Name_Light = "0x01";
	private String Dev_Name_Swith = "0x05";

	private PoCModel() {
		// light 1-4#
		PocDev pd1 = new PocDev();
		pd1.devId = 0x01;
		pd1.pocDevId = 0x03;
		pd1.value = new int[4];
		this.data.put("0x01", pd1);

		// swith 2#
		PocDev pd2 = new PocDev();
		pd2.devId = 0x05;
		pd2.pocDevId = 0x07;
		pd2.value = new int[4];
		pd2.value[0] = 0;
		pd2.value[1] = 0;
		pd2.value[2] = 0;
		this.data.put("0x05", pd2);

	}

	/**
	 * 
	 * @param name
	 */
	public void addDev(String name, PocDev dev) {
		data.put(name, dev);
	}

	/**
	 * 
	 * @param name
	 */
	public void removeDev(String name, PocDev dev) {
		data.remove(name);
	}

	/**
	 * 
	 * @param name
	 */
	public PocDev getDev(String name) {
		return data.get(name);
	}
	
	
	/**
	 * 
	 * @param name
	 */
	public void changeDevValue(String name, int[] values) {		
		PocDev dev = getDev(name);
		dev.value = values;
	}
	
	public float getVoltage() {
		try {
			// fe ae 7a 07 07 00 00
			PocDevService.swithQueryAll(Dev_Name_Swith);
			PocDev dev = getDev(Dev_Name_Swith);
			return dev.value[0] / 100f;
		} catch (Exception exp) {
			exp.printStackTrace();
			return 0;
		}
	}

	public float getCurrent() {
		try {
			PocDev dev = getDev(Dev_Name_Swith);
			return dev.value[1] / 100f;
		} catch (Exception exp) {
			exp.printStackTrace();
			return 0;
		}
	} 

	public float getPower() {
		try {
			PocDev dev = getDev(Dev_Name_Swith);
			return dev.value[2] / 10;
		} catch (Exception exp) {
			exp.printStackTrace();
			return 0;
		}
	}

}