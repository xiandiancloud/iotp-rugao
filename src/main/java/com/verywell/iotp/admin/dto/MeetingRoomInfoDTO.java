package com.verywell.iotp.admin.dto;

import java.util.ArrayList;
import java.util.List;

import com.verywell.iotp.admin.entity.meeting.MeetingInfo;

/**
 * 会议室信息对象
 * 
 * 存放会议室基本信息和会议室在某个日期的预定情况
 * 
 * @author dl
 * 
 */
public class MeetingRoomInfoDTO
{
	// 会议预订时间定义
	public static final List<String> METTING_TIME_ARRAY = new ArrayList<String>();
	{
		METTING_TIME_ARRAY.add("0800");
		METTING_TIME_ARRAY.add("0830");
		METTING_TIME_ARRAY.add("0900");
		METTING_TIME_ARRAY.add("0930");
		METTING_TIME_ARRAY.add("1000");
		METTING_TIME_ARRAY.add("1030");
		METTING_TIME_ARRAY.add("1100");
		METTING_TIME_ARRAY.add("1130");
		METTING_TIME_ARRAY.add("1200");
		METTING_TIME_ARRAY.add("1230");
		METTING_TIME_ARRAY.add("1300");
		METTING_TIME_ARRAY.add("1330");
		METTING_TIME_ARRAY.add("1400");
		METTING_TIME_ARRAY.add("1430");
		METTING_TIME_ARRAY.add("1500");
		METTING_TIME_ARRAY.add("1530");
		METTING_TIME_ARRAY.add("1600");
		METTING_TIME_ARRAY.add("1630");
		METTING_TIME_ARRAY.add("1700");
		METTING_TIME_ARRAY.add("1730");
		METTING_TIME_ARRAY.add("1800");
		METTING_TIME_ARRAY.add("1830");
		METTING_TIME_ARRAY.add("1900");
		METTING_TIME_ARRAY.add("1930");
		METTING_TIME_ARRAY.add("2000");
		METTING_TIME_ARRAY.add("2030");
		METTING_TIME_ARRAY.add("2100");
	}

	private Long roomId;// 会议室ID
	private String roomName; // 会议室名称
	private String mettingDate;// 会议时间
	private List<MeetingInfo> mettingOrderList = new ArrayList<MeetingInfo>();

	public MeetingRoomInfoDTO()
	{

		// 初始化会议预定信息，每个元素对应一个时间段
		for (int i = 0; i < 26; i++)
		{
			mettingOrderList.add(null);
		}
	}

	public String getRoomName()
	{
		return roomName;
	}

	public Long getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Long roomId)
	{
		this.roomId = roomId;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	public String getMettingDate()
	{
		return mettingDate;
	}

	public void setMettingDate(String mettingDate)
	{
		this.mettingDate = mettingDate;
	}

	public List<MeetingInfo> getMettingOrderList()
	{
		return mettingOrderList;
	}

	// 根据开始时间接结束时间，找出预订会议室，并获得会议室ID
	public void addOrderInfo(MeetingInfo mettingInfo)
	{
		// 根据会议开始时间和结束时间更新相应时间段位置的会议信息
		String startTime = mettingInfo.getStartTime();
		String endTime = mettingInfo.getEndTime();
		int startTimeLoaction = METTING_TIME_ARRAY.indexOf(startTime);
		int endTimeLoaction = METTING_TIME_ARRAY.indexOf(endTime);
		if (startTimeLoaction != -1 && endTimeLoaction != -1)
		{
			for (int i = startTimeLoaction; i <= endTimeLoaction - 1; i++)
			{
				mettingOrderList.set(i, mettingInfo);
			}
		}
	}

}
