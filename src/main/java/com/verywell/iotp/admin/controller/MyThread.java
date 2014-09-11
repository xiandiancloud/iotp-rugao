package com.verywell.iotp.admin.controller;

import com.verywell.framework.utils.socket.rugao.DeviceControl;
import com.verywell.iotp.admin.constants.CommonConstants;
import com.verywell.iotp.admin.service.DevInfoService;

class MyThread extends Thread {

	boolean isflag;
	private DevInfoService devInfoService;
	private String devIds;

	private static MyThread uniqueInstance = null;

	private MyThread() {
	}

	public static MyThread getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MyThread();
		}
		return uniqueInstance;
	}

	public void setFlag(boolean flag) {
		this.isflag = flag;
	}

	public void setDevInfoService(DevInfoService devInfoService, String devIds) {
		this.devInfoService = devInfoService;
		this.devIds = devIds;
	}

	public void run() {
//		System.out.println("thread ---------------000----------------"+this.hashCode());
		while (true) {
//			System.out.println("thread ---------------111----------------"+this.hashCode());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (isflag) {
				try {
					int[] value = DeviceControl.lightvalue;
//					System.out.println("value --------------------------"+value);
					if (value != null && value.length > 4) {
						int a = value[0];
						int b = value[1];
						int c = value[2];
						int d = value[3];
						if (a == 0x55 && b == 0x66 && c == 0x01 && d == 0x01) {
							if (devInfoService != null) {
								System.out.println("--------亮亮亮----------------- ");
								devInfoService.lightcontrollDev(devIds,
										CommonConstants.CMD_SWITCH, "1");// 亮
								// devInfoService.controllDev(devIds,
								// CommonConstants.CMD_SWITCH, "0");
								Thread.sleep(10000);
							}
						} else if (a == 0x55 && b == 0x66 && c == 0x01
								&& d == 0x00) {
							if (devInfoService != null) {
								System.out.println("--------暗暗暗----------------- ");
								devInfoService.lightcontrollDev(devIds,
										CommonConstants.CMD_SWITCH, "0");// 暗
								// devInfoService.controllDev(devIds,
								// CommonConstants.CMD_SWITCH, "1");
								Thread.sleep(10000);
							}
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}