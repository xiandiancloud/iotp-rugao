package com.verywell.iotp.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.framework.utils.DateTimeUtil;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.dao.MapRoomInfoDAO;
import com.verywell.iotp.admin.dao.MeetingDAO;
import com.verywell.iotp.admin.entity.meeting.MeetingInfo;
import com.verywell.iotp.admin.service.MeetingService;

/**
 * @title: MapRoomServiceImpl.java
 * @description: 预订信息管理操作类
 * @author: dl
 */
@Service
@Transactional(readOnly = true)
public class MeetingServiceImpl extends BaseCrudServiceImpl<MeetingInfo, Long> implements MeetingService
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 会议室预订数据访问对象
	 */
	@Autowired
	private MeetingDAO meetingDAO;

	/**
	 * 房间数据访问对象
	 */
	@Autowired
	private MapRoomInfoDAO mapRoomInfoDAO;

	@Autowired
	@Override
	@Qualifier(value = "meetingDAO")
	public void setBaseDao(BaseHibernateDAO<MeetingInfo, Long> baseDao)
	{
		this.baseDao = baseDao;

	}

	/**
	 * //根据日期获得当天预订所有会议室的信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MeetingInfo> findByMettingDate(String viewDate, String viewMode) throws Exception
	{
		Date date = DateTimeUtil.parse(viewDate, "yyyy-MM-dd");
		DateTime dateTime = new DateTime(date.getTime());
		String startDate = "";
		String endDate = "";
		if (viewMode.equals(MEETING_VIEW_MODE_TIMELINE))
		{
			startDate = viewDate.replaceAll("-", "");
			endDate = viewDate.replaceAll("-", "");
		}
		else if (viewMode.equals(MEETING_VIEW_MODE_WEEK))
		{
			startDate = dateTime.dayOfWeek().withMinimumValue().toString("yyyyMMdd");
			endDate = dateTime.dayOfWeek().withMaximumValue().toString("yyyyMMdd");
		}
		else if (viewMode.equals(MEETING_VIEW_MODE_MONTH))
		{
			startDate = dateTime.dayOfMonth().withMinimumValue().toString("yyyyMMdd");
			endDate = dateTime.dayOfMonth().withMaximumValue().toString("yyyyMMdd");
		}

		Object[] values = new Object[] { startDate, endDate, startDate, endDate };
		String hql = "from MeetingInfo as model where ((substr(model.startTime,0,8)>= ? and substr(model.startTime,0,8)<=?) or (substr(model.endTime,0,8)>=? and substr(model.endTime,0,8)<=?)) and model.meetingStatus=1 order by meetingId";
		return meetingDAO.findByHql(hql, values);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public int save(MeetingInfo meetingInfo)
	{
		Object[] params = { meetingInfo.getMapRoomInfo().getRoomId(), meetingInfo.getStartTime(), meetingInfo.getStartTime(), meetingInfo.getEndTime(),
				meetingInfo.getEndTime(), meetingInfo.getStartTime(), meetingInfo.getEndTime() };
		String hql = "select count(*) from MeetingInfo as model where model.meetingStatus=1 and model.mapRoomInfo.roomId=? and  ((model.startTime<= ? and model.endTime > ?) or (model.startTime< ? and model.endTime >= ?) or (model.startTime>= ? and model.endTime <= ?) )";
		long count = baseDao.findLong(hql, params);
		if (count > 0)
		{
			return ResultConstants.SAVE_FAILED;
		}
		else
		{
			baseDao.saveOrUpdate(meetingInfo);
			return ResultConstants.SAVE_SUCCEED;
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public int update(MeetingInfo meetingInfo)
	{
		Object[] params = { meetingInfo.getMapRoomInfo().getRoomId(), meetingInfo.getStartTime(), meetingInfo.getStartTime(), meetingInfo.getEndTime(),
				meetingInfo.getEndTime(), meetingInfo.getStartTime(), meetingInfo.getEndTime() };
		String hql = "select count(*) from MeetingInfo as model where model.meetingId!="
				+ meetingInfo.getMeetingId()
				+ " and model.meetingStatus=1 and model.mapRoomInfo.roomId=? and  ((model.startTime<= ? and model.endTime > ?) or (model.startTime< ? and model.endTime >= ?) or (model.startTime>= ? and model.endTime <= ?) )";
		long count = baseDao.findLong(hql, params);
		if (count > 0)
		{
			return ResultConstants.UPDATE_FAILED;

		}
		else
		{
			baseDao.executeHQL("delete from MeetingTargetInfo where meetingInfo.meetingId=?", meetingInfo.getMeetingId());
			baseDao.saveOrUpdate(meetingInfo);

			return ResultConstants.UPDATE_SUCCEED;
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public int cancelMeeting(Long meetingId) throws Exception
	{
		MeetingInfo meetingInfo = baseDao.findById(meetingId);
		meetingInfo.setMeetingStatus(0);
		baseDao.update(meetingInfo);
		return ResultConstants.UPDATE_SUCCEED;
	}

	/**
	 * 根据日期以及当前用户ID,获得预订会议室信息
	 */
	@Override
	public List<MeetingInfo> findByLoginName(String loginName) throws Exception
	{
		Object[] values = new Object[] { loginName };
		String hql = "from MeetingInfo as model where model.subscribeBy=? and model.meetingStatus=1 order by subscribeTime";
		return baseDao.findByHql(hql, values);
	}
}
