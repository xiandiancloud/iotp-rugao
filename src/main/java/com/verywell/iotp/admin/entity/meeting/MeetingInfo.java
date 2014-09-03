package com.verywell.iotp.admin.entity.meeting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;

/**
 * The persistent class for the MEETING_INFO database table.
 * 
 */
@Entity
@Table(name = "MEETING_INFO")
public class MeetingInfo implements Serializable
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
	// private Long roomId;
	private String startTime;
	private String subscribeBy;
	private String subscribeTime;
	private List<MeetingTargetInfo> meetingTargetInfos;
	private MapRoomInfo mapRoomInfo;
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
	private Long subscribeId;
	private Integer prepareStatus;

	/**** 非实体化属性 *********/
	// 会议室名称

	// private String roomName;
	private String targetUserIds;

	private Long roomId;
	private String[] screenRoomIds;

	private String subscribeByName;
	private String startDate;
	private String startTimeHour;
	private String startTimeMin;
	private String endDate;
	private String endTimeHour;
	private String endTimeMin;

	private String targetUserNames;
	private String prepareByNames;

	public MeetingInfo()
	{
	}

	@Id
	@SequenceGenerator(name = "MEETING_ID_GENERATOR", sequenceName = "SEQ_MEETING", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEETING_ID_GENERATOR")
	@Column(name = "MEETING_ID")
	public Long getMeetingId()
	{
		return meetingId;
	}

	public void setMeetingId(Long meetingId)
	{
		this.meetingId = meetingId;
	}

	@Column(name = "END_TIME")
	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	@Column(name = "MEETING_DESC")
	public String getMeetingDesc()
	{
		return meetingDesc;
	}

	public void setMeetingDesc(String meetingDesc)
	{
		this.meetingDesc = meetingDesc;
	}

	@Column(name = "MEETING_NAME")
	public String getMeetingName()
	{
		return meetingName;
	}

	public void setMeetingName(String meetingName)
	{
		this.meetingName = meetingName;
	}

	@Column(name = "MEETING_STATUS")
	public Integer getMeetingStatus()
	{
		return meetingStatus;
	}

	public void setMeetingStatus(Integer meetingStatus)
	{
		this.meetingStatus = meetingStatus;
	}

	@Column(name = "NOTICE_CONTENT")
	public String getNoticeContent()
	{
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent)
	{
		this.noticeContent = noticeContent;
	}

	@Column(name = "NOTICE_FLAG")
	public Integer getNoticeFlag()
	{
		return noticeFlag;
	}

	public void setNoticeFlag(Integer noticeFlag)
	{
		this.noticeFlag = noticeFlag;
	}

	@Column(name = "NOTICE_STATUS")
	public Integer getNoticeStatus()
	{
		return noticeStatus;
	}

	public void setNoticeStatus(Integer noticeStatus)
	{
		this.noticeStatus = noticeStatus;
	}

	/*
	 * public void setRoomId(Long roomId) { mapRoomInfo = new
	 * MapRoomInfo(roomId); }
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROOM_ID")
	public MapRoomInfo getMapRoomInfo()
	{
		return mapRoomInfo;
	}

	public void setMapRoomInfo(MapRoomInfo mapRoomInfo)
	{
		this.mapRoomInfo = mapRoomInfo;
	}

	@Column(name = "START_TIME")
	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	@Column(name = "SUBSCRIBE_BY")
	public String getSubscribeBy()
	{
		return subscribeBy;
	}

	public void setSubscribeBy(String subscribeBy)
	{
		this.subscribeBy = subscribeBy;
	}

	@Column(name = "SUBSCRIBE_TIME")
	public String getSubscribeTime()
	{
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime)
	{
		this.subscribeTime = subscribeTime;
	}

	@Column(name = "SCENE_FLAG")
	public Integer getSceneFlag()
	{
		return sceneFlag;
	}

	public void setSceneFlag(Integer sceneFlag)
	{
		this.sceneFlag = sceneFlag;
	}

	@Column(name = "SCENE_ID")
	public Long getSceneId()
	{
		return sceneId;
	}

	public void setSceneId(Long sceneId)
	{
		this.sceneId = sceneId;
	}

	@Column(name = "SCENE_EARLY_TIME")
	public Integer getSceneEarlyTime()
	{
		return sceneEarlyTime;
	}

	public void setSceneEarlyTime(Integer sceneEarlyTime)
	{
		this.sceneEarlyTime = sceneEarlyTime;
	}

	@Column(name = "SCENE_STATUS")
	public Integer getSceneStatus()
	{
		return sceneStatus;
	}

	public void setSceneStatus(Integer sceneStatus)
	{
		this.sceneStatus = sceneStatus;
	}

	@Column(name = "SCREEN_FLAG")
	public Integer getScreenFlag()
	{
		return screenFlag;
	}

	public void setScreenFlag(Integer screenFlag)
	{
		this.screenFlag = screenFlag;
	}

	@Column(name = "SCREEN_EARLY_TIME")
	public Integer getScreenEarlyTime()
	{
		return screenEarlyTime;
	}

	public void setScreenEarlyTime(Integer screenEarlyTime)
	{
		this.screenEarlyTime = screenEarlyTime;
	}

	@Column(name = "SCREEN_STATUS")
	public Integer getScreenStatus()
	{
		return screenStatus;
	}

	public void setScreenStatus(Integer screenStatus)
	{
		this.screenStatus = screenStatus;
	}

	@Column(name = "SCREEN_FILE_PATH")
	public String getScreenFilePath()
	{
		return screenFilePath;
	}

	public void setScreenFilePath(String screenFilePath)
	{
		this.screenFilePath = screenFilePath;
	}

	@Column(name = "SCREEN_IDS")
	public String getScreenIds()
	{
		return screenIds;
	}

	public void setScreenIds(String screenIds)
	{
		this.screenIds = screenIds;
	}

	@Column(name = "SUBSCRIBE_ID")
	public Long getSubscribeId()
	{
		return subscribeId;
	}

	public void setSubscribeId(Long subscribeId)
	{
		this.subscribeId = subscribeId;
	}

	// bi-directional many-to-one association to MeetingTargetInfo
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "meetingInfo")
	public List<MeetingTargetInfo> getMeetingTargetInfos()
	{
		return meetingTargetInfos;
	}

	public void setMeetingTargetInfos(List<MeetingTargetInfo> meetingTargetInfos)
	{
		this.meetingTargetInfos = meetingTargetInfos;
	}

	@Column(name = "PREPARE_EARLY_TIME")
	public Integer getPrepareEarlyTime()
	{
		return prepareEarlyTime;
	}

	public void setPrepareEarlyTime(Integer prepareEarlyTime)
	{
		this.prepareEarlyTime = prepareEarlyTime;
	}

	@Column(name = "PREPARE_BY")
	public String getPrepareBy()
	{
		return prepareBy;
	}

	public void setPrepareBy(String prepareBy)
	{
		this.prepareBy = prepareBy;
	}

	@Column(name = "PREPARE_STATUS")
	public Integer getPrepareStatus()
	{
		return prepareStatus;
	}

	public void setPrepareStatus(Integer prepareStatus)
	{
		this.prepareStatus = prepareStatus;
	}

	/*
	 * @Transient public String getRoomName() { return roomName; }
	 * 
	 * public void setRoomName(String roomName) { this.roomName = roomName; }
	 */
	@Transient
	public String getTargetUserIds()
	{
		return targetUserIds;
	}

	public void setTargetUserIds(String targetUserIds)
	{
		this.targetUserIds = targetUserIds;
		if (targetUserIds != null && !targetUserIds.equals(""))
		{
			meetingTargetInfos = new ArrayList<MeetingTargetInfo>();
			String[] targetIdArray = targetUserIds.split(",");
			for (int i = 0; i < targetIdArray.length; i++)
			{
				MeetingTargetInfo targetInfo = new MeetingTargetInfo();
				targetInfo.setLoginId(new Long(targetIdArray[i]));
				targetInfo.setMeetingInfo(this);
				meetingTargetInfos.add(targetInfo);
			}
		}
	}

	@Transient
	public Long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
		mapRoomInfo = new MapRoomInfo(roomId);
	}

	@Transient
	public String[] getScreenRoomIds()
	{
		return screenRoomIds;
	}

	public void setScreenRoomIds(String[] screenRoomIds)
	{
		this.screenRoomIds = screenRoomIds;
	}

	@Transient
	public String getSubscribeByName()
	{
		return subscribeByName;
	}

	public void setSubscribeByName(String subscribeByName)
	{
		this.subscribeByName = subscribeByName;
	}

	@Transient
	public String getStartTimeHour()
	{
		return startTimeHour;
	}

	public void setStartTimeHour(String startTimeHour)
	{
		this.startTimeHour = startTimeHour;
	}

	@Transient
	public String getStartTimeMin()
	{
		return startTimeMin;
	}

	public void setStartTimeMin(String startTimeMin)
	{
		this.startTimeMin = startTimeMin;
	}

	@Transient
	public String getEndTimeHour()
	{
		return endTimeHour;
	}

	public void setEndTimeHour(String endTimeHour)
	{
		this.endTimeHour = endTimeHour;
	}

	@Transient
	public String getEndTimeMin()
	{
		return endTimeMin;
	}

	public void setEndTimeMin(String endTimeMin)
	{
		this.endTimeMin = endTimeMin;
	}

	@Transient
	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	@Transient
	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	@Transient
	public String getTargetUserNames()
	{
		return targetUserNames;
	}

	public void setTargetUserNames(String targetUserNames)
	{
		this.targetUserNames = targetUserNames;
	}

	@Transient
	public String getPrepareByNames()
	{
		return prepareByNames;
	}

	public void setPrepareByNames(String prepareByNames)
	{
		this.prepareByNames = prepareByNames;
	}

}