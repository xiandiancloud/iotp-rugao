prompt PL/SQL Developer import file
prompt Created on 2014年5月7日 by yao
set feedback off
set define off
prompt Disabling triggers for DEV_INFO...
alter table DEV_INFO disable all triggers;
prompt Disabling triggers for DEV_ALARM_CONFIG_INFO...
alter table DEV_ALARM_CONFIG_INFO disable all triggers;
prompt Disabling triggers for DEV_ALARM_LOG...
alter table DEV_ALARM_LOG disable all triggers;
prompt Disabling triggers for DEV_CLASS_GROUP_INFO...
alter table DEV_CLASS_GROUP_INFO disable all triggers;
prompt Disabling triggers for DEV_CLASS_INFO...
alter table DEV_CLASS_INFO disable all triggers;
prompt Disabling triggers for DEV_ATTRIBUTES...
alter table DEV_ATTRIBUTES disable all triggers;
prompt Disabling triggers for DEV_ATTR_HISTORY_INFO...
alter table DEV_ATTR_HISTORY_INFO disable all triggers;
prompt Disabling triggers for DEV_ATTR_INFO...
alter table DEV_ATTR_INFO disable all triggers;
prompt Disabling triggers for DEV_STATUS...
alter table DEV_STATUS disable all triggers;
prompt Disabling triggers for MAP_BUILDING_INFO...
alter table MAP_BUILDING_INFO disable all triggers;
prompt Disabling triggers for MAP_ROOM_INFO...
alter table MAP_ROOM_INFO disable all triggers;
prompt Disabling triggers for MEETING_INFO...
alter table MEETING_INFO disable all triggers;
prompt Disabling triggers for MEETING_TARGET_INFO...
alter table MEETING_TARGET_INFO disable all triggers;
prompt Disabling triggers for SCENE_INFO...
alter table SCENE_INFO disable all triggers;
prompt Disabling triggers for SCENE_CONFIG_INFO...
alter table SCENE_CONFIG_INFO disable all triggers;
prompt Disabling triggers for SMS_TASK...
alter table SMS_TASK disable all triggers;
prompt Disabling triggers for SYS_CORP...
alter table SYS_CORP disable all triggers;
prompt Disabling triggers for SYS_DEPT...
alter table SYS_DEPT disable all triggers;
prompt Disabling triggers for SYS_LOG...
alter table SYS_LOG disable all triggers;
prompt Disabling triggers for SYS_LOGIN...
alter table SYS_LOGIN disable all triggers;
prompt Disabling triggers for SYS_ROLE...
alter table SYS_ROLE disable all triggers;
prompt Disabling triggers for SYS_LOGIN_ROLE...
alter table SYS_LOGIN_ROLE disable all triggers;
prompt Disabling triggers for SYS_PERMISSION...
alter table SYS_PERMISSION disable all triggers;
prompt Disabling triggers for SYS_ROLE_PERMISSION...
alter table SYS_ROLE_PERMISSION disable all triggers;
prompt Disabling triggers for SYS_ROLE_ROOM...
alter table SYS_ROLE_ROOM disable all triggers;
prompt Disabling foreign key constraints for DEV_ALARM_CONFIG_INFO...
alter table DEV_ALARM_CONFIG_INFO disable constraint FK_DEV_ALAR_REFERENCE_DEV_INFO;
prompt Disabling foreign key constraints for DEV_CLASS_INFO...
alter table DEV_CLASS_INFO disable constraint FK_DEV_CLAS_REFERENCE_DEV_CLAS;
prompt Disabling foreign key constraints for DEV_ATTRIBUTES...
alter table DEV_ATTRIBUTES disable constraint FK_DEV_ATTR_REFERENCE_DEV_CLAS;
prompt Disabling foreign key constraints for DEV_ATTR_HISTORY_INFO...
alter table DEV_ATTR_HISTORY_INFO disable constraint FK_DEV_ATTR_HIS_REF_DEV_INFO;
prompt Disabling foreign key constraints for DEV_ATTR_INFO...
alter table DEV_ATTR_INFO disable constraint FK_DEV_ATTR_REFERENCE_DEV_INFO;
prompt Disabling foreign key constraints for DEV_STATUS...
alter table DEV_STATUS disable constraint FK_DEV_STAT_REFERENCE_DEV_CLAS;
prompt Disabling foreign key constraints for MAP_ROOM_INFO...
alter table MAP_ROOM_INFO disable constraint FK_MAP_ROOM_REFERENCE_MAP_BUIL;
prompt Disabling foreign key constraints for SCENE_CONFIG_INFO...
alter table SCENE_CONFIG_INFO disable constraint FK_SCENE_CO_REFERENCE_SCENE_IN;
prompt Disabling foreign key constraints for SYS_LOGIN...
alter table SYS_LOGIN disable constraint FK_SYS_LOGI_REFERENCE_SYS_CORP;
alter table SYS_LOGIN disable constraint FK_SYS_LOGI_REFERENCE_SYS_DEPT;
prompt Disabling foreign key constraints for SYS_LOGIN_ROLE...
alter table SYS_LOGIN_ROLE disable constraint FK_SYS_LOGI_REFERENCE_SYS_LOGI;
alter table SYS_LOGIN_ROLE disable constraint FK_SYS_LOGI_REFERENCE_SYS_ROLE;
prompt Disabling foreign key constraints for SYS_ROLE_PERMISSION...
alter table SYS_ROLE_PERMISSION disable constraint FK_SYS_ROLE_REFERENCE_SYS_PERM;
alter table SYS_ROLE_PERMISSION disable constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE;
prompt Disabling foreign key constraints for SYS_ROLE_ROOM...
alter table SYS_ROLE_ROOM disable constraint FK_SYS_ROLE_REF_ROLE_ROOM;
prompt Loading DEV_INFO...
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (1, '窗帘电机1', null, 5614083, 1001, null, '192.168.1.1', '1', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (2, '窗帘电机2', null, 5614083, 1001, null, '192.168.1.1', '2', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (3, '窗帘电机3', null, 5614083, 1001, null, '192.168.1.1', '3', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (4, '窗帘电机4', null, 5614083, 1001, null, '192.168.1.1', '4', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (5, '窗帘电机5', null, 5614083, 1001, null, '192.168.1.1', '5', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (6, '窗帘电机6', null, 5614083, 1001, null, '192.168.1.1', '6', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (7, '窗帘电机7', null, 5614083, 1001, null, '192.168.1.1', '7', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (8, '窗帘电机8', null, 5614083, 1001, null, '192.168.1.1', '8', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (9, '灯控1', null, 66816, 1001, null, '192.168.1.1', '19', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (10, '灯控2', null, 66816, 1001, null, '192.168.1.1', '18', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (11, '门禁1', null, 132352, 1001, null, '192.168.1.1', '16', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (12, '灯控1', null, 66816, 1002, null, '192.168.1.1', '17', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
insert into DEV_INFO (dev_id, dev_name, dev_desc, dev_class_id, room_id, dev_role, network_addr, mac_addr, dev_status, position_x, position_y, alarm_status, alarm_receiver, alaram_switch, create_by, create_time, last_modify_by, last_modify_time)
values (13, '门禁1', null, 132352, 1002, null, '192.168.1.1', '17', 1, 3950, 371, 0, 'admin', 1, null, null, null, null);
commit;
prompt 3 records loaded
prompt Loading DEV_ALARM_CONFIG_INFO...
prompt Table is empty
prompt Loading DEV_ALARM_LOG...
prompt Table is empty
prompt Loading DEV_CLASS_GROUP_INFO...
insert into DEV_CLASS_GROUP_INFO (group_id, group_name)
values (1, '灯具');
insert into DEV_CLASS_GROUP_INFO (group_id, group_name)
values (2, '空调');
insert into DEV_CLASS_GROUP_INFO (group_id, group_name)
values (3, '传感器');
insert into DEV_CLASS_GROUP_INFO (group_id, group_name)
values (4, '窗帘');
insert into DEV_CLASS_GROUP_INFO (group_id, group_name)
values (5, '会议中控');
insert into DEV_CLASS_GROUP_INFO (group_id, group_name)
values (6, '显示屏');
insert into DEV_CLASS_GROUP_INFO (group_id, group_name)
values (7, '门禁');
insert into DEV_CLASS_GROUP_INFO (group_id, group_name)
values (9, '家电');
commit;
prompt 8 records loaded
prompt Loading DEV_CLASS_INFO...
insert into DEV_CLASS_INFO (dev_class_id, group_id, class_name, class_desc, create_by, create_time, last_modify_by, last_modify_time, open_class, close_class, alarm_class, invalid_class)
values (66816, 1, '灯控', '灯控', null, null, null, null, 'deng_open_01', 'deng_close', 'deng_alarm', 'deng_invalid');
insert into DEV_CLASS_INFO (dev_class_id, group_id, class_name, class_desc, create_by, create_time, last_modify_by, last_modify_time, open_class, close_class, alarm_class, invalid_class)
values (21, 2, '空调', '空调', null, null, null, null, 'kongtiao_open_01', 'kongtiao_close_01', 'kongtiao_alarm_01', 'kongtiao_invalid');
insert into DEV_CLASS_INFO (dev_class_id, group_id, class_name, class_desc, create_by, create_time, last_modify_by, last_modify_time, open_class, close_class, alarm_class, invalid_class)
values (31, 3, '温湿度感应', '温度感应', null, null, null, null, 'action_green_01', 'action_hui_01', 'action_red_01', 'action_hui_01');
insert into DEV_CLASS_INFO (dev_class_id, group_id, class_name, class_desc, create_by, create_time, last_modify_by, last_modify_time, open_class, close_class, alarm_class, invalid_class)
values (5614083, 4, '电动窗帘', '电动窗帘', null, null, null, null, null, null, null, null);
insert into DEV_CLASS_INFO (dev_class_id, group_id, class_name, class_desc, create_by, create_time, last_modify_by, last_modify_time, open_class, close_class, alarm_class, invalid_class)
values (51, 5, '会议中控', '会议中控设备', null, null, null, null, null, null, null, null);
insert into DEV_CLASS_INFO (dev_class_id, group_id, class_name, class_desc, create_by, create_time, last_modify_by, last_modify_time, open_class, close_class, alarm_class, invalid_class)
values (61, 6, '显示屏', '会议室门前显示屏', null, null, null, null, null, null, null, null);
insert into DEV_CLASS_INFO (dev_class_id, group_id, class_name, class_desc, create_by, create_time, last_modify_by, last_modify_time, open_class, close_class, alarm_class, invalid_class)
values (132352, 7, '门禁', '门禁', null, null, null, null, 'door_open_01', 'door_close', null, null);
insert into DEV_CLASS_INFO (dev_class_id, group_id, class_name, class_desc, create_by, create_time, last_modify_by, last_modify_time, open_class, close_class, alarm_class, invalid_class)
values (91, 9, '电视机', null, null, null, null, null, null, null, null, null);
commit;
prompt 8 records loaded
prompt Loading DEV_ATTRIBUTES...
prompt Table is empty
prompt Loading DEV_ATTR_HISTORY_INFO...
prompt Table is empty
prompt Loading DEV_ATTR_INFO...
commit;
prompt 0 records loaded
prompt Loading DEV_STATUS...
prompt Table is empty
prompt Loading MAP_BUILDING_INFO...
insert into MAP_BUILDING_INFO (building_id, parent_id, building_name, building_desc, position, building_type, floor_num, status, create_by, create_time, last_modify_by, last_modify_time)
values (1001, 0, '综合楼', '综合楼', '615,206,615,231,635,268,656,278,678,279,708,259,699,231,663,175', 2, 10, 1, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading MAP_ROOM_INFO...
insert into MAP_ROOM_INFO (room_id, buliding_id, room_name, room_desc, room_type, room_image, floor_no, position, status, scene_id, create_by, create_time, last_modify_by, last_modify_time)
values (1001, 1001, '大会议室', '大会议室', 2, 'big_meeting.jpg', 1, null, 1, null, 'admin', '20120906001847', 'admin', '20120906001847');
insert into MAP_ROOM_INFO (room_id, buliding_id, room_name, room_desc, room_type, room_image, floor_no, position, status, scene_id, create_by, create_time, last_modify_by, last_modify_time)
values (1002, 1001, '小会议室', '小会议室', 2, 'small_meeting.jpg', 1, null, 1, null, 'admin', '20120906001847', 'admin', '20120906001847');
commit;
prompt 5 records loaded
prompt Loading MEETING_INFO...
commit;
prompt 0 records loaded
prompt Loading MEETING_TARGET_INFO...
commit;
prompt 0 records loaded
prompt Loading SCENE_INFO...
insert into SCENE_INFO (scene_id, room_id, scene_name, scene_desc, scene_type, scene_start_time, scene_end_time, valid_start_date, valid_end_date, trigger_type, trigger_condition, trigger_value, scene_status)
values (1, 1001, '全开', null, 1, '0800', '0830', null, null, 1, 1, null, 1);
insert into SCENE_INFO (scene_id, room_id, scene_name, scene_desc, scene_type, scene_start_time, scene_end_time, valid_start_date, valid_end_date, trigger_type, trigger_condition, trigger_value, scene_status)
values (2, 1002, '全开', null, 1, '0800', '0830', null, null, 0, 0, null, 1);
commit;
prompt 2 records loaded
prompt Loading SCENE_CONFIG_INFO...
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (1, 2, 13, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (2, 2, 12, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (3, 1, 11, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (4, 1, 10, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (5, 1, 9, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (6, 1, 8, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (7, 1, 7, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (8, 1, 6, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (9, 1, 5, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (10, 1, 4, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (11, 1, 3, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (12, 1, 2, 'switch', '1');
insert into SCENE_CONFIG_INFO (id, scene_id, dev_id, attr_key, attr_value)
values (13, 1, 1, 'switch', '1');
commit;
prompt 13 records loaded
prompt Loading SMS_TASK...
commit;
prompt 0 records loaded
prompt Loading SYS_CORP...
insert into SYS_CORP (corp_id, parent_corp_id, corp_name, corp_type, corp_desc, status, create_by, create_time, last_modify_by, last_modify_time)
values (0, -1, '南京高职', 1, '南京高职', 1, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading SYS_DEPT...
insert into SYS_DEPT (dept_id, dept_name, corp_id, status, create_by, create_time, last_modify_by, last_modify_time, parent_dept_id)
values (1, '计算机技术', 0, 1, null, null, null, null, 0);
commit;
prompt 1 records loaded
prompt Loading SYS_LOG...
commit;
prompt 0 records loaded
prompt Loading SYS_LOGIN...
insert into SYS_LOGIN (login_id, login_name, login_pwd, user_name, user_type, corp_id, dept_id, system_id, email, tel, valid_tag, valid_date, user_remark, record_corp_id, login_last_time, status, create_by, create_time, last_modify_by, last_modify_time, car_no, user_code)
values (1, 'admin', '96e79218965eb72c92a549dd5a330112', '超级管理员', 0, 0, 1, 1, null, '13813388602', null, null, null, 0, '20140225140409', 1, null, null, null, null, null, null);
insert into SYS_LOGIN (login_id, login_name, login_pwd, user_name, user_type, corp_id, dept_id, system_id, email, tel, valid_tag, valid_date, user_remark, record_corp_id, login_last_time, status, create_by, create_time, last_modify_by, last_modify_time, car_no, user_code)
values (2, 'xiandian', 'e10adc3949ba59abbe56e057f20f883e', '先电', 1, 0, 1, 1, null, null, '1', null, null, 0, null, 1, null, null, null, null, null, '1679');
commit;
prompt 2 records loaded
prompt Loading SYS_ROLE...
insert into SYS_ROLE (role_id, role_name, role_desc, corp_id, system_id, status, create_by, create_time, last_modify_by, last_modify_time)
values (1002, '管理员', '1', 0, 1, 1, null, null, null, null);
insert into SYS_ROLE (role_id, role_name, role_desc, corp_id, system_id, status, create_by, create_time, last_modify_by, last_modify_time)
values (1, '操作员', null, 0, 1, 1, null, null, null, null);
commit;
prompt 2 records loaded
prompt Loading SYS_LOGIN_ROLE...
insert into SYS_LOGIN_ROLE (id, role_id, login_id)
values (1, 1002, 1);
insert into SYS_LOGIN_ROLE (id, role_id, login_id)
values (2, 1, 2);
commit;
prompt 2 records loaded
prompt Loading SYS_PERMISSION...
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (23, '设备类型分组管理', 1, '/devClassGroupInfoMgr', 23, 2, 1, 1, null);
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (2, '设备管理', 1, null, 1, 0, 1, 1, 'menu_dev.png');
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (3, '配置管理', 1, null, 3, 0, 1, 1, 'menu_setting.png');
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (6, '系统管理', 1, null, 7, 0, 1, 1, 'menu_system.png');
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (21, '设备管理', 1, '/devMgr/roomDetail', 21, 2, 1, 1, null);
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (22, '设备类型管理', 1, '/devClassInfoMgr', 22, 2, 1, 1, null);
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (31, '场景配置', 1, '/sceneMgr/1001', 31, 3, 1, 1, null);
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (61, '部门用户管理', 1, '/deptMgr', 61, 6, 1, 1, null);
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (62, '角色管理', 1, '/roleMgr', 62, 6, 1, 1, null);
insert into SYS_PERMISSION (permission_id, permission_name, permission_type, permission_url, permission_sort, parent_permission_id, system_id, status, permission_ico)
values (63, '系统日志', 1, '/logMgr', 63, 6, 1, 1, null);
commit;
prompt 17 records loaded
prompt Loading SYS_ROLE_PERMISSION...
insert into SYS_ROLE_PERMISSION (id, permission_id, role_id)
values (1021, 21, 1002);
insert into SYS_ROLE_PERMISSION (id, permission_id, role_id)
values (1022, 22, 1002);
insert into SYS_ROLE_PERMISSION (id, permission_id, role_id)
values (1024, 63, 1002);
insert into SYS_ROLE_PERMISSION (id, permission_id, role_id)
values (1025, 62, 1002);
commit;
prompt 5 records loaded
prompt Loading SYS_ROLE_ROOM...
insert into SYS_ROLE_ROOM (id, role_id, room_id, controll_flag)
values (1, 1, 1001, 0);
insert into SYS_ROLE_ROOM (id, role_id, room_id, controll_flag)
values (2, 1, 1002, 0);
commit;
prompt 2 records loaded
prompt Enabling foreign key constraints for DEV_ALARM_CONFIG_INFO...
alter table DEV_ALARM_CONFIG_INFO enable constraint FK_DEV_ALAR_REFERENCE_DEV_INFO;
prompt Enabling foreign key constraints for DEV_CLASS_INFO...
alter table DEV_CLASS_INFO enable constraint FK_DEV_CLAS_REFERENCE_DEV_CLAS;
prompt Enabling foreign key constraints for DEV_ATTRIBUTES...
alter table DEV_ATTRIBUTES enable constraint FK_DEV_ATTR_REFERENCE_DEV_CLAS;
prompt Enabling foreign key constraints for DEV_ATTR_HISTORY_INFO...
alter table DEV_ATTR_HISTORY_INFO enable constraint FK_DEV_ATTR_HIS_REF_DEV_INFO;
prompt Enabling foreign key constraints for DEV_ATTR_INFO...
alter table DEV_ATTR_INFO enable constraint FK_DEV_ATTR_REFERENCE_DEV_INFO;
prompt Enabling foreign key constraints for DEV_STATUS...
alter table DEV_STATUS enable constraint FK_DEV_STAT_REFERENCE_DEV_CLAS;
prompt Enabling foreign key constraints for MAP_ROOM_INFO...
alter table MAP_ROOM_INFO enable constraint FK_MAP_ROOM_REFERENCE_MAP_BUIL;
prompt Enabling foreign key constraints for SCENE_CONFIG_INFO...
alter table SCENE_CONFIG_INFO enable constraint FK_SCENE_CO_REFERENCE_SCENE_IN;
prompt Enabling foreign key constraints for SYS_LOGIN...
alter table SYS_LOGIN enable constraint FK_SYS_LOGI_REFERENCE_SYS_CORP;
alter table SYS_LOGIN enable constraint FK_SYS_LOGI_REFERENCE_SYS_DEPT;
prompt Enabling foreign key constraints for SYS_LOGIN_ROLE...
alter table SYS_LOGIN_ROLE enable constraint FK_SYS_LOGI_REFERENCE_SYS_LOGI;
alter table SYS_LOGIN_ROLE enable constraint FK_SYS_LOGI_REFERENCE_SYS_ROLE;
prompt Enabling foreign key constraints for SYS_ROLE_PERMISSION...
alter table SYS_ROLE_PERMISSION enable constraint FK_SYS_ROLE_REFERENCE_SYS_PERM;
alter table SYS_ROLE_PERMISSION enable constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE;
prompt Enabling foreign key constraints for SYS_ROLE_ROOM...
alter table SYS_ROLE_ROOM enable constraint FK_SYS_ROLE_REF_ROLE_ROOM;
prompt Enabling triggers for DEV_INFO...
alter table DEV_INFO enable all triggers;
prompt Enabling triggers for DEV_ALARM_CONFIG_INFO...
alter table DEV_ALARM_CONFIG_INFO enable all triggers;
prompt Enabling triggers for DEV_ALARM_LOG...
alter table DEV_ALARM_LOG enable all triggers;
prompt Enabling triggers for DEV_CLASS_GROUP_INFO...
alter table DEV_CLASS_GROUP_INFO enable all triggers;
prompt Enabling triggers for DEV_CLASS_INFO...
alter table DEV_CLASS_INFO enable all triggers;
prompt Enabling triggers for DEV_ATTRIBUTES...
alter table DEV_ATTRIBUTES enable all triggers;
prompt Enabling triggers for DEV_ATTR_HISTORY_INFO...
alter table DEV_ATTR_HISTORY_INFO enable all triggers;
prompt Enabling triggers for DEV_ATTR_INFO...
alter table DEV_ATTR_INFO enable all triggers;
prompt Enabling triggers for DEV_STATUS...
alter table DEV_STATUS enable all triggers;
prompt Enabling triggers for MAP_BUILDING_INFO...
alter table MAP_BUILDING_INFO enable all triggers;
prompt Enabling triggers for MAP_ROOM_INFO...
alter table MAP_ROOM_INFO enable all triggers;
prompt Enabling triggers for MEETING_INFO...
alter table MEETING_INFO enable all triggers;
prompt Enabling triggers for MEETING_TARGET_INFO...
alter table MEETING_TARGET_INFO enable all triggers;
prompt Enabling triggers for SCENE_INFO...
alter table SCENE_INFO enable all triggers;
prompt Enabling triggers for SCENE_CONFIG_INFO...
alter table SCENE_CONFIG_INFO enable all triggers;
prompt Enabling triggers for SMS_TASK...
alter table SMS_TASK enable all triggers;
prompt Enabling triggers for SYS_CORP...
alter table SYS_CORP enable all triggers;
prompt Enabling triggers for SYS_DEPT...
alter table SYS_DEPT enable all triggers;
prompt Enabling triggers for SYS_LOG...
alter table SYS_LOG enable all triggers;
prompt Enabling triggers for SYS_LOGIN...
alter table SYS_LOGIN enable all triggers;
prompt Enabling triggers for SYS_ROLE...
alter table SYS_ROLE enable all triggers;
prompt Enabling triggers for SYS_LOGIN_ROLE...
alter table SYS_LOGIN_ROLE enable all triggers;
prompt Enabling triggers for SYS_PERMISSION...
alter table SYS_PERMISSION enable all triggers;
prompt Enabling triggers for SYS_ROLE_PERMISSION...
alter table SYS_ROLE_PERMISSION enable all triggers;
prompt Enabling triggers for SYS_ROLE_ROOM...
alter table SYS_ROLE_ROOM enable all triggers;
set feedback on
set define on
prompt Done.
