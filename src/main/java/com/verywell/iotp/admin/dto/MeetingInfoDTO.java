package com.verywell.iotp.admin.dto;

/**
 * 会议室信息对象
 * 
 * 存放会议室基本信息和会议室在某个日期的预定情况
 * 
 * @author dl
 * 
 */
public class MeetingInfoDTO
{
	private String start_date;// 会议时间
	private String end_date;// 会议时间
	private String text;
	private Long meetingId;// 会议ID
	private String meetingName; // 会议室名称
	private Long roomId;
	private String roomName;
	private String meetingDesc;
	private String targetIds;
	private String subscribeBy;
	private Integer noticeFlag;
	private String noticeContent;
	private String color = "";
	private String textColor = "";
	private String subscribeByName;
	private Integer sceneFlag;
	private Long sceneId;
	private Integer sceneEarlyTime;
	private Integer sceneStatus;
	private Integer screenFlag;
	private Integer screenEarlyTime;
	private Integer screenStatus;
	private String screenFilePath;
	private String screenIds;
	private Integer prepareEarlyTime;
	private String prepareBy;

	public Long getMeetingId()
	{
		return meetingId;
	}

	public void setMeetingId(Long meetingId)
	{
		this.meetingId = meetingId;
	}

	public String getMeetingName()
	{
		return meetingName;
	}

	public void setMeetingName(String meetingName)
	{
		this.meetingName = meetingName;
	}

	public String getStart_date()
	{
		return start_date;
	}

	public void setStart_date(String start_date)
	{
		this.start_date = start_date;
	}

	public String getEnd_date()
	{
		return end_date;
	}

	public void setEnd_date(String end_date)
	{
		this.end_date = end_date;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}

	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	public String getTargetIds()
	{
		return targetIds;
	}

	public void setTargetIds(String targetIds)
	{
		this.targetIds = targetIds;
	}

	public String getMeetingDesc()
	{
		return meetingDesc;
	}

	public void setMeetingDesc(String meetingDesc)
	{
		this.meetingDesc = meetingDesc;
	}

	public String getSubscribeBy()
	{
		return subscribeBy;
	}

	public void setSubscribeBy(String subscribeBy)
	{
		this.subscribeBy = subscribeBy;
	}

	public Integer getNoticeFlag()
	{
		return noticeFlag;
	}

	public void setNoticeFlag(Integer noticeFlag)
	{
		this.noticeFlag = noticeFlag;
	}

	public String getNoticeContent()
	{
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent)
	{
		this.noticeContent = noticeContent;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getTextColor()
	{
		return textColor;
	}

	public void setTextColor(String textColor)
	{
		this.textColor = textColor;
	}

	public Integer getSceneFlag()
	{
		return sceneFlag;
	}

	public void setSceneFlag(Integer sceneFlag)
	{
		this.sceneFlag = sceneFlag;
	}

	public Long getSceneId()
	{
		return sceneId;
	}

	public void setSceneId(Long sceneId)
	{
		this.sceneId = sceneId;
	}

	public Integer getSceneEarlyTime()
	{
		return sceneEarlyTime;
	}

	public void setSceneEarlyTime(Integer sceneEarlyTime)
	{
		this.sceneEarlyTime = sceneEarlyTime;
	}

	public String getSubscribeByName()
	{
		if (subscribeByName != null)
			return subscribeByName;
		return "未知";
	}

	public void setSubscribeByName(String subscribeByName)
	{
		this.subscribeByName = subscribeByName;
	}

	public Integer getSceneStatus()
	{
		return sceneStatus;
	}

	public void setSceneStatus(Integer sceneStatus)
	{
		this.sceneStatus = sceneStatus;
	}

	public Integer getScreenFlag()
	{
		return screenFlag;
	}

	public void setScreenFlag(Integer screenFlag)
	{
		this.screenFlag = screenFlag;
	}

	public Integer getScreenEarlyTime()
	{
		return screenEarlyTime;
	}

	public void setScreenEarlyTime(Integer screenEarlyTime)
	{
		this.screenEarlyTime = screenEarlyTime;
	}

	public Integer getScreenStatus()
	{
		return screenStatus;
	}

	public void setScreenStatus(Integer screenStatus)
	{
		this.screenStatus = screenStatus;
	}

	public String getScreenFilePath()
	{
		return screenFilePath;
	}

	public void setScreenFilePath(String screenFilePath)
	{
		this.screenFilePath = screenFilePath;
	}

	public String getScreenIds()
	{
		return screenIds;
	}

	public void setScreenIds(String screenIds)
	{
		this.screenIds = screenIds;
	}

	public Integer getPrepareEarlyTime()
	{
		return prepareEarlyTime;
	}

	public void setPrepareEarlyTime(Integer prepareEarlyTime)
	{
		this.prepareEarlyTime = prepareEarlyTime;
	}

	public String getPrepareBy()
	{
		return prepareBy;
	}

	public void setPrepareBy(String prepareBy)
	{
		this.prepareBy = prepareBy;
	}

}
