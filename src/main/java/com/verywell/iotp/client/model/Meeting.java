package com.verywell.iotp.client.model;

import java.io.Serializable;

public class Meeting implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long meetingId;
	private String endTime;
	private String meetingDesc;
	private String meetingName;
	private Integer meetingStatus;
	private String noticeContent;
	private Integer noticeFlag;
	private Integer noticeStatus;
	private Long roomId;
	private String startTime;
	private String subscribeBy;
	private String subscribeTime;
	private Integer sceneFlag;
	private Long sceneId;
	private Integer sceneEarlyTime;

	public Long getMeetingId()
	{
		return meetingId;
	}

	public void setMeetingId(Long meetingId)
	{
		this.meetingId = meetingId;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getMeetingDesc()
	{
		return meetingDesc;
	}

	public void setMeetingDesc(String meetingDesc)
	{
		this.meetingDesc = meetingDesc;
	}

	public String getMeetingName()
	{
		return meetingName;
	}

	public void setMeetingName(String meetingName)
	{
		this.meetingName = meetingName;
	}

	public Integer getMeetingStatus()
	{
		return meetingStatus;
	}

	public void setMeetingStatus(Integer meetingStatus)
	{
		this.meetingStatus = meetingStatus;
	}

	public String getNoticeContent()
	{
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent)
	{
		this.noticeContent = noticeContent;
	}

	public Integer getNoticeFlag()
	{
		return noticeFlag;
	}

	public void setNoticeFlag(Integer noticeFlag)
	{
		this.noticeFlag = noticeFlag;
	}

	public Integer getNoticeStatus()
	{
		return noticeStatus;
	}

	public void setNoticeStatus(Integer noticeStatus)
	{
		this.noticeStatus = noticeStatus;
	}

	public Long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getSubscribeBy()
	{
		return subscribeBy;
	}

	public void setSubscribeBy(String subscribeBy)
	{
		this.subscribeBy = subscribeBy;
	}

	public String getSubscribeTime()
	{
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime)
	{
		this.subscribeTime = subscribeTime;
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

}
