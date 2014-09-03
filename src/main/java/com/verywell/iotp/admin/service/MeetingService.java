package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.meeting.MeetingInfo;

public interface MeetingService extends BaseCrudService<MeetingInfo, Long>
{
	/** 会议预定视图模式-时间线 */
	public static final String MEETING_VIEW_MODE_TIMELINE = "timeline";
	/** 会议预定视图模式-周 */
	public static final String MEETING_VIEW_MODE_WEEK = "week";
	/** 会议预定视图模式-月 */
	public static final String MEETING_VIEW_MODE_MONTH = "month";

	/**
	 * 根据日期获得当天预订所有会议室的信息
	 * 
	 * @return　预订列表集合
	 * @throws Exception
	 */
	List<MeetingInfo> findByMettingDate(String meetingDate, String viewMode) throws Exception;

	/**
	 * 会议取消
	 * 
	 * @param meetingId
	 * @return
	 * @throws Exception
	 */
	int cancelMeeting(Long meetingId) throws Exception;

	/**
	 * 根据用户名获得该用户的会议室预定信息列表
	 * 
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public List<MeetingInfo> findByLoginName(String loginName) throws Exception;
}
