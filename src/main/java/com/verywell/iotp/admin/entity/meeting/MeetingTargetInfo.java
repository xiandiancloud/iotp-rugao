package com.verywell.iotp.admin.entity.meeting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the MEETING_TARGET_INFO database table.
 * 
 */
@Entity
@Table(name = "MEETING_TARGET_INFO")
public class MeetingTargetInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long loginId;
	private MeetingInfo meetingInfo;

	public MeetingTargetInfo()
	{
	}

	@SequenceGenerator(name = "SEQ_TARGETINFO_GENERATOR", sequenceName = "SEQ_MEETING_TARGET", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TARGETINFO_GENERATOR")
	@Id
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "LOGIN_ID")
	public Long getLoginId()
	{
		return loginId;
	}

	public void setLoginId(Long loginId)
	{
		this.loginId = loginId;
	}

	// bi-directional many-to-one association to MeetingInfo
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEETING_ID")
	public MeetingInfo getMeetingInfo()
	{
		return meetingInfo;
	}

	public void setMeetingInfo(MeetingInfo meetingInfo)
	{
		this.meetingInfo = meetingInfo;
	}

}