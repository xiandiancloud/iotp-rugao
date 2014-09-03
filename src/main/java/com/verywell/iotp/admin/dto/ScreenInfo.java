package com.verywell.iotp.admin.dto;

import java.util.ArrayList;
import java.util.List;

public class ScreenInfo
{
	private String titleTextSize = "120";
	private String titleTextColor;
	private String contentTextSize = "120";
	private String contentTextColor;
	private String backgroundColor;
	private String intervalTime;
	private String title;
	private List<String> messages = new ArrayList<String>();

	public String getTitleTextSize()
	{
		return titleTextSize;
	}

	public void setTitleTextSize(String titleTextSize)
	{
		this.titleTextSize = titleTextSize;
	}

	public String getTitleTextColor()
	{
		return titleTextColor;
	}

	public void setTitleTextColor(String titleTextColor)
	{
		this.titleTextColor = titleTextColor;
	}

	public String getContentTextSize()
	{
		return contentTextSize;
	}

	public void setContentTextSize(String contentTextSize)
	{
		this.contentTextSize = contentTextSize;
	}

	public String getContentTextColor()
	{
		return contentTextColor;
	}

	public void setContentTextColor(String contentTextColor)
	{
		this.contentTextColor = contentTextColor;
	}

	public String getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}

	public String getIntervalTime()
	{
		return intervalTime;
	}

	public void setIntervalTime(String intervalTime)
	{
		this.intervalTime = intervalTime;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public List<String> getMessages()
	{
		return messages;
	}

	public void setMessages(List<String> messages)
	{
		this.messages = messages;
	}

	public void addMessage(String message)
	{
		this.messages.add(message);
	}

	public Integer getScreenNum()
	{
		if (this.messages.size() == 0)
			return 1;
		return Integer.valueOf(this.messages.size() / 7);
	}
}
