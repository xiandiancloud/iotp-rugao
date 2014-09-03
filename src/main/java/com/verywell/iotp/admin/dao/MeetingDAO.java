package com.verywell.iotp.admin.dao;

import org.springframework.stereotype.Repository;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.iotp.admin.entity.meeting.MeetingInfo;

@Repository
public class MeetingDAO extends BaseHibernateDAO<MeetingInfo, Long>
{

}
