package com.verywell.framework.web.servlet;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * 
 * @author Yao
 * 
 */
public class InitServlet extends HttpServlet
{
	protected static final Logger logger = Logger.getLogger(InitServlet.class);
	/**
	 * ServletContext
	 */
	private ServletContext context = null;

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

		logger.info("系统程序初始化...");
		try
		{
			context = config.getServletContext();
			System.setProperty("webRootPath", context.getRealPath("/") + File.separator);
		}
		catch (RuntimeException e)
		{
			logger.error("系统程序初始化失败", e);
		}
	}
}
