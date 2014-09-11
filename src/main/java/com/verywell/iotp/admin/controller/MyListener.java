package com.verywell.iotp.admin.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.verywell.framework.utils.socket.SocketClientThread;

public class MyListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SocketClientThread sct = SocketClientThread.handle();
		
		MyThread tt = MyThread.getInstance();
		tt.start();
		System.err.println("MyListener Startup---------------!");		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}