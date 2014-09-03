------------------------------------------
-- Export file for user IOTP            --
-- Created by yao on 2014/5/7, 14:19:42 --
------------------------------------------

spool iotp_ddl.log

prompt
prompt Creating table BASEINFO
prompt =======================
prompt
create table BASEINFO
(
  id   INTEGER not null,
  name VARCHAR2(50) not null,
  sort INTEGER not null
)
;
comment on table BASEINFO
  is '部门基本资料';
comment on column BASEINFO.id
  is 'ID，主键';
comment on column BASEINFO.name
  is '资料项名称';
comment on column BASEINFO.sort
  is '类型
0-机构类别；1-婚姻状况；2-职务；
3-合同类型；4-文化程度；5-职称；6-政治面貌；7-设备型号；8-民族';
alter table BASEINFO
  add constraint PK_BASEINFO primary key (ID);

prompt
prompt Creating table CAR_OFFLINE
prompt ==========================
prompt
create table CAR_OFFLINE
(
  id            INTEGER not null,
  cardno        VARCHAR2(15),
  cardid        VARCHAR2(20),
  cardtype      VARCHAR2(20),
  cardstate     VARCHAR2(20),
  empno         VARCHAR2(20),
  empname       VARCHAR2(50),
  carlicense    VARCHAR2(50),
  machnum       VARCHAR2(6),
  inorout       VARCHAR2(10),
  parkname      VARCHAR2(100),
  inouttime     VARCHAR2(20),
  inoutposition VARCHAR2(200)
)
;
comment on table CAR_OFFLINE
  is '车辆进出记录表（场内车辆）';
comment on column CAR_OFFLINE.id
  is 'ID，主键';
comment on column CAR_OFFLINE.cardno
  is '卡物理号';
comment on column CAR_OFFLINE.cardid
  is '卡编号';
comment on column CAR_OFFLINE.cardtype
  is '卡类';
comment on column CAR_OFFLINE.cardstate
  is '卡状态';
comment on column CAR_OFFLINE.empno
  is '员工编号';
comment on column CAR_OFFLINE.empname
  is '员工姓名';
comment on column CAR_OFFLINE.carlicense
  is '车牌号码';
comment on column CAR_OFFLINE.machnum
  is '进出控制器机号';
comment on column CAR_OFFLINE.inorout
  is '进出方向（进；出）';
comment on column CAR_OFFLINE.parkname
  is '进出停车场名称';
comment on column CAR_OFFLINE.inouttime
  is '出入时间';
comment on column CAR_OFFLINE.inoutposition
  is '控制器位置';
alter table CAR_OFFLINE
  add constraint PK_CAR_OFFLINE primary key (ID);

prompt
prompt Creating table CAR_PARK
prompt =======================
prompt
create table CAR_PARK
(
  id          INTEGER not null,
  cardno      VARCHAR2(15),
  cardid      VARCHAR2(20),
  cardtype    VARCHAR2(20),
  cardstate   VARCHAR2(20),
  empno       VARCHAR2(20),
  empname     VARCHAR2(50),
  carlicense  VARCHAR2(50),
  intime      VARCHAR2(20),
  inmachnum   VARCHAR2(6),
  inposition  VARCHAR2(200),
  outtime     VARCHAR2(20),
  outmachnum  VARCHAR2(6),
  outposition VARCHAR2(200),
  parkname    VARCHAR2(200),
  memo        VARCHAR2(200)
)
;
comment on table CAR_PARK
  is '车辆出场统计表（车辆出入记录）';
comment on column CAR_PARK.id
  is 'ID，主键';
comment on column CAR_PARK.cardno
  is '卡物理号';
comment on column CAR_PARK.cardid
  is '卡编号';
comment on column CAR_PARK.cardtype
  is '卡类';
comment on column CAR_PARK.cardstate
  is '卡状态';
comment on column CAR_PARK.empno
  is '员工编号';
comment on column CAR_PARK.empname
  is '员工姓名';
comment on column CAR_PARK.carlicense
  is '车牌号码';
comment on column CAR_PARK.intime
  is '入场时间';
comment on column CAR_PARK.inmachnum
  is '入场机号';
comment on column CAR_PARK.inposition
  is '入场地点';
comment on column CAR_PARK.outtime
  is '出场时间';
comment on column CAR_PARK.outmachnum
  is '出场机号';
comment on column CAR_PARK.outposition
  is '出场地点';
comment on column CAR_PARK.parkname
  is '停车场名称';
comment on column CAR_PARK.memo
  is '备注';
alter table CAR_PARK
  add constraint PK_CAR_PARK primary key (ID);

prompt
prompt Creating table DEPARTMENT
prompt =========================
prompt
create table DEPARTMENT
(
  id         INTEGER not null,
  depid      VARCHAR2(20),
  depname    VARCHAR2(100),
  upid       INTEGER default 0,
  upname     VARCHAR2(100) default '机构',
  deptype    VARCHAR2(255),
  d_telphone VARCHAR2(30),
  d_fax      VARCHAR2(30),
  d_address  VARCHAR2(255),
  d_postcode VARCHAR2(20),
  d_memo     VARCHAR2(255)
)
;
comment on table DEPARTMENT
  is '部门表';
comment on column DEPARTMENT.id
  is 'ID,主键';
comment on column DEPARTMENT.depid
  is '部门编号';
comment on column DEPARTMENT.depname
  is '部门名称';
comment on column DEPARTMENT.upid
  is '从属部门ID（即：上级部门ID）';
comment on column DEPARTMENT.upname
  is '上级部门的名称';
comment on column DEPARTMENT.deptype
  is '机构类别';
comment on column DEPARTMENT.d_telphone
  is '部门电话号码';
comment on column DEPARTMENT.d_fax
  is '部门传真';
comment on column DEPARTMENT.d_address
  is '部门地址';
comment on column DEPARTMENT.d_postcode
  is '部门邮政编码';
comment on column DEPARTMENT.d_memo
  is '备注';
alter table DEPARTMENT
  add constraint PK_DEPARTMENT primary key (ID);

prompt
prompt Creating table DEV_INFO
prompt =======================
prompt
create table DEV_INFO
(
  dev_id           NUMBER(10) not null,
  dev_name         VARCHAR2(100),
  dev_desc         VARCHAR2(200),
  dev_class_id     NUMBER(10),
  room_id          NUMBER(10),
  dev_role         NUMBER(1),
  network_addr     VARCHAR2(50),
  mac_addr         VARCHAR2(50),
  dev_status       NUMBER(1),
  position_x       NUMBER(4),
  position_y       NUMBER(4),
  alarm_status     NUMBER(2),
  alarm_receiver   VARCHAR2(20),
  alaram_switch    NUMBER(2),
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14)
)
;
comment on table DEV_INFO
  is '存放设备的基本配置信息';
comment on column DEV_INFO.dev_id
  is '设备编号';
comment on column DEV_INFO.dev_name
  is '设备名称';
comment on column DEV_INFO.dev_desc
  is '设备详细描述';
comment on column DEV_INFO.dev_class_id
  is '设备类型，详见设备类型表';
comment on column DEV_INFO.room_id
  is '设备所在房间编号';
comment on column DEV_INFO.dev_role
  is '设备角色：0-协调器；1-路由；2-终端';
comment on column DEV_INFO.network_addr
  is '网络地址';
comment on column DEV_INFO.mac_addr
  is '物理地址';
comment on column DEV_INFO.dev_status
  is '0-开；1-关；2-故障；3-停用';
comment on column DEV_INFO.position_x
  is '位置X坐标';
comment on column DEV_INFO.position_y
  is '位置Y坐标';
comment on column DEV_INFO.alarm_status
  is '告警状态，1-正常 2-告警';
comment on column DEV_INFO.alarm_receiver
  is '告警接收人';
comment on column DEV_INFO.alaram_switch
  is '告警开关：0-关闭 1-开启';
comment on column DEV_INFO.create_by
  is '创建人
系统自动创建则此处为system';
comment on column DEV_INFO.create_time
  is '创建时间';
comment on column DEV_INFO.last_modify_by
  is '最新一次修改者';
comment on column DEV_INFO.last_modify_time
  is '最新一次修改时间';
alter table DEV_INFO
  add constraint PK_DEV_INFO primary key (DEV_ID);

prompt
prompt Creating table DEV_ALARM_CONFIG_INFO
prompt ====================================
prompt
create table DEV_ALARM_CONFIG_INFO
(
  id             NUMBER(10) not null,
  dev_id         NUMBER(10) not null,
  attr_key       VARCHAR2(50) not null,
  attr_min_value VARCHAR2(20),
  attr_max_value VARCHAR2(20)
)
;
comment on column DEV_ALARM_CONFIG_INFO.dev_id
  is '设备编号';
comment on column DEV_ALARM_CONFIG_INFO.attr_key
  is '属性键';
comment on column DEV_ALARM_CONFIG_INFO.attr_min_value
  is '属性最小值';
comment on column DEV_ALARM_CONFIG_INFO.attr_max_value
  is '属性最大值';
alter table DEV_ALARM_CONFIG_INFO
  add constraint PK_DEV_ALARM_CONFIG_INFO primary key (ID);
alter table DEV_ALARM_CONFIG_INFO
  add constraint FK_DEV_ALAR_REFERENCE_DEV_INFO foreign key (DEV_ID)
  references DEV_INFO (DEV_ID);

prompt
prompt Creating table DEV_ALARM_LOG
prompt ============================
prompt
create table DEV_ALARM_LOG
(
  id             NUMBER(10) not null,
  dev_id         NUMBER(10),
  dev_class_id   NUMBER(10),
  room_id        NUMBER(10),
  alarm_type     NUMBER(2),
  alarm_time     VARCHAR2(14),
  alarm_desc     VARCHAR2(100),
  status         NUMBER(1),
  receiver_name  VARCHAR2(50),
  receiver_phone CHAR(10),
  dispose_time   VARCHAR2(14)
)
;
comment on table DEV_ALARM_LOG
  is '存放设备的告警日志';
comment on column DEV_ALARM_LOG.id
  is '告警信息编号';
comment on column DEV_ALARM_LOG.dev_id
  is '设备名称';
comment on column DEV_ALARM_LOG.dev_class_id
  is '设备类型，详见设备类型表';
comment on column DEV_ALARM_LOG.room_id
  is '房间编号';
comment on column DEV_ALARM_LOG.alarm_type
  is '告警类型：
1-红外告警；
2-烟雾或可燃性气体告警；
3-电能告警；
4-温度告警；
5-湿度告警。';
comment on column DEV_ALARM_LOG.alarm_time
  is '告警时间(YYYYMMDDHHMISS)';
comment on column DEV_ALARM_LOG.alarm_desc
  is '告警描述';
comment on column DEV_ALARM_LOG.status
  is '告警信息状态：0-未处理；1-已处理';
comment on column DEV_ALARM_LOG.receiver_name
  is '告警接收者';
comment on column DEV_ALARM_LOG.receiver_phone
  is '告警接收者号码';
comment on column DEV_ALARM_LOG.dispose_time
  is '处理时间(YYYYMMDDHHMISS)';
alter table DEV_ALARM_LOG
  add constraint PK_DEV_ALARM_LOG primary key (ID);

prompt
prompt Creating table DEV_CLASS_GROUP_INFO
prompt ===================================
prompt
create table DEV_CLASS_GROUP_INFO
(
  group_id   NUMBER(10) not null,
  group_name VARCHAR2(50)
)
;
comment on column DEV_CLASS_GROUP_INFO.group_id
  is '主键，序列';
comment on column DEV_CLASS_GROUP_INFO.group_name
  is '设备类型分组名称';
alter table DEV_CLASS_GROUP_INFO
  add constraint PK_DEV_CLASS_GROUP_INFO primary key (GROUP_ID);

prompt
prompt Creating table DEV_CLASS_INFO
prompt =============================
prompt
create table DEV_CLASS_INFO
(
  dev_class_id     NUMBER(10) not null,
  group_id         NUMBER(10),
  class_name       VARCHAR2(100),
  class_desc       VARCHAR2(200),
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14),
  open_class       VARCHAR2(50),
  close_class      VARCHAR2(50),
  alarm_class      VARCHAR2(50),
  invalid_class    VARCHAR2(50)
)
;
comment on table DEV_CLASS_INFO
  is '存放设备的类型定义信息';
comment on column DEV_CLASS_INFO.group_id
  is '主键，序列';
comment on column DEV_CLASS_INFO.class_name
  is '设备类型名称';
comment on column DEV_CLASS_INFO.class_desc
  is '设备类型详细描述';
comment on column DEV_CLASS_INFO.create_by
  is '创建人
系统自动创建则此处为system';
comment on column DEV_CLASS_INFO.create_time
  is '创建时间';
comment on column DEV_CLASS_INFO.last_modify_by
  is '最新一次修改者';
comment on column DEV_CLASS_INFO.last_modify_time
  is '最新一次修改时间';
alter table DEV_CLASS_INFO
  add constraint PK_DEV_CLASS_INFO primary key (DEV_CLASS_ID);
alter table DEV_CLASS_INFO
  add constraint FK_DEV_CLAS_REFERENCE_DEV_CLAS foreign key (GROUP_ID)
  references DEV_CLASS_GROUP_INFO (GROUP_ID);

prompt
prompt Creating table DEV_ATTRIBUTES
prompt =============================
prompt
create table DEV_ATTRIBUTES
(
  attr_key         VARCHAR2(50) not null,
  dev_class_id     NUMBER(10),
  attr_name        VARCHAR2(100),
  attr_desc        VARCHAR2(200),
  attr_measurement VARCHAR2(20),
  allow_edit       NUMBER(1),
  attr_status      NUMBER(1),
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14)
)
;
comment on table DEV_ATTRIBUTES
  is '存放各类设备的属性定义信息';
comment on column DEV_ATTRIBUTES.attr_key
  is '属性编号，字符串';
comment on column DEV_ATTRIBUTES.dev_class_id
  is '设备类型ID';
comment on column DEV_ATTRIBUTES.attr_name
  is '设备类型名称';
comment on column DEV_ATTRIBUTES.attr_desc
  is '设备类型详细描述';
comment on column DEV_ATTRIBUTES.attr_measurement
  is '属性显示单位';
comment on column DEV_ATTRIBUTES.allow_edit
  is '是否可编辑：0-否；1-是';
comment on column DEV_ATTRIBUTES.attr_status
  is '属性状态：0-作废 ,1-使用；';
comment on column DEV_ATTRIBUTES.create_by
  is '创建人
系统自动创建则此处为system';
comment on column DEV_ATTRIBUTES.create_time
  is '创建时间';
comment on column DEV_ATTRIBUTES.last_modify_by
  is '最新一次修改者';
comment on column DEV_ATTRIBUTES.last_modify_time
  is '最新一次修改时间';
alter table DEV_ATTRIBUTES
  add constraint PK_DEV_ATTRIBUTES primary key (ATTR_KEY);
alter table DEV_ATTRIBUTES
  add constraint FK_DEV_ATTR_REFERENCE_DEV_CLAS foreign key (DEV_CLASS_ID)
  references DEV_CLASS_INFO (DEV_CLASS_ID);

prompt
prompt Creating table DEV_ATTR_HISTORY_INFO
prompt ====================================
prompt
create table DEV_ATTR_HISTORY_INFO
(
  id          NUMBER(10) not null,
  dev_id      NUMBER(10) not null,
  attr_key    VARCHAR2(50) not null,
  attr_value  VARCHAR2(20),
  update_time VARCHAR2(14)
)
;
comment on table DEV_ATTR_HISTORY_INFO
  is '存放设备的历史扩展信息';
comment on column DEV_ATTR_HISTORY_INFO.id
  is '扩展信息编号';
comment on column DEV_ATTR_HISTORY_INFO.dev_id
  is '设备编号';
comment on column DEV_ATTR_HISTORY_INFO.attr_key
  is '属性编号，字符串';
comment on column DEV_ATTR_HISTORY_INFO.attr_value
  is '属性值';
comment on column DEV_ATTR_HISTORY_INFO.update_time
  is '更新时间';
alter table DEV_ATTR_HISTORY_INFO
  add constraint PK_DEV_ATTR_HISTORY_INFO primary key (ID);
alter table DEV_ATTR_HISTORY_INFO
  add constraint FK_DEV_ATTR_HIS_REF_DEV_INFO foreign key (DEV_ID)
  references DEV_INFO (DEV_ID);

prompt
prompt Creating table DEV_ATTR_INFO
prompt ============================
prompt
create table DEV_ATTR_INFO
(
  id          NUMBER(10) not null,
  dev_id      NUMBER(10) not null,
  attr_key    VARCHAR2(50) not null,
  attr_value  VARCHAR2(20),
  update_time VARCHAR2(14)
)
;
comment on table DEV_ATTR_INFO
  is '存放设备的扩展信息';
comment on column DEV_ATTR_INFO.id
  is '扩展信息编号';
comment on column DEV_ATTR_INFO.dev_id
  is '设备编号';
comment on column DEV_ATTR_INFO.attr_key
  is '属性编号，字符串';
comment on column DEV_ATTR_INFO.attr_value
  is '属性值';
comment on column DEV_ATTR_INFO.update_time
  is '更新时间';
alter table DEV_ATTR_INFO
  add constraint PK_DEV_ATTR_INFO primary key (ID);
alter table DEV_ATTR_INFO
  add constraint FK_DEV_ATTR_REFERENCE_DEV_INFO foreign key (DEV_ID)
  references DEV_INFO (DEV_ID);

prompt
prompt Creating table DEV_STATUS
prompt =========================
prompt
create table DEV_STATUS
(
  id               NUMBER(10) not null,
  dev_class_id     NUMBER(10),
  dev_status       NUMBER(2),
  dev_status_name  VARCHAR2(50),
  icon             VARCHAR2(200),
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14)
)
;
comment on table DEV_STATUS
  is '存放设备在UI上根据状态显示的图标定义';
comment on column DEV_STATUS.dev_class_id
  is '设备类型';
comment on column DEV_STATUS.dev_status
  is '设备的状态';
comment on column DEV_STATUS.dev_status_name
  is '设备状态名称';
comment on column DEV_STATUS.icon
  is '对应状态显示的图标';
comment on column DEV_STATUS.create_by
  is '创建人
系统自动创建则此处为system';
comment on column DEV_STATUS.create_time
  is '创建时间';
comment on column DEV_STATUS.last_modify_by
  is '最新一次修改者';
comment on column DEV_STATUS.last_modify_time
  is '最新一次修改时间';
alter table DEV_STATUS
  add constraint PK_DEV_STATUS primary key (ID);
alter table DEV_STATUS
  add constraint FK_DEV_STAT_REFERENCE_DEV_CLAS foreign key (DEV_CLASS_ID)
  references DEV_CLASS_INFO (DEV_CLASS_ID);

prompt
prompt Creating table EMPLOYEE
prompt =======================
prompt
create table EMPLOYEE
(
  id             INTEGER not null,
  depid          INTEGER,
  empno          VARCHAR2(20),
  empname        VARCHAR2(20),
  e_photo        BLOB,
  e_sex          VARCHAR2(4),
  id_card        VARCHAR2(30),
  e_birthday     VARCHAR2(20),
  e_nationality  VARCHAR2(30),
  e_political    VARCHAR2(30),
  e_education    VARCHAR2(30),
  e_school       VARCHAR2(255),
  e_marital      VARCHAR2(30),
  family_place   VARCHAR2(255),
  badgeid        VARCHAR2(20),
  hukou          VARCHAR2(255),
  office_phone   VARCHAR2(30),
  e_mobile       VARCHAR2(30),
  homeaddress    VARCHAR2(255),
  e_postcode     VARCHAR2(20),
  hiredate       VARCHAR2(20),
  separatedate   VARCHAR2(20),
  separatereason VARCHAR2(255),
  zhiwu          VARCHAR2(30),
  e_title        VARCHAR2(30),
  e_contract     VARCHAR2(50),
  e_salary       VARCHAR2(50),
  e_state        VARCHAR2(20),
  e_profession   VARCHAR2(50),
  carno          VARCHAR2(20),
  driverno       VARCHAR2(50),
  e_memo         VARCHAR2(255)
)
;
comment on table EMPLOYEE
  is '人事表';
comment on column EMPLOYEE.id
  is 'ID，主键';
comment on column EMPLOYEE.depid
  is '所在部门';
comment on column EMPLOYEE.empno
  is '员工工号';
comment on column EMPLOYEE.empname
  is '姓名';
comment on column EMPLOYEE.e_photo
  is '员工照片';
comment on column EMPLOYEE.e_sex
  is '性别';
comment on column EMPLOYEE.id_card
  is '身份证号';
comment on column EMPLOYEE.e_birthday
  is '出生日期';
comment on column EMPLOYEE.e_nationality
  is '民族';
comment on column EMPLOYEE.e_political
  is '政治面貌';
comment on column EMPLOYEE.e_education
  is '文化程度';
comment on column EMPLOYEE.e_school
  is '毕业学校';
comment on column EMPLOYEE.e_marital
  is '婚姻状况';
comment on column EMPLOYEE.family_place
  is '家庭住址';
comment on column EMPLOYEE.badgeid
  is '员工持卡卡号';
comment on column EMPLOYEE.hukou
  is '户口所在地';
comment on column EMPLOYEE.office_phone
  is '办公室电话';
comment on column EMPLOYEE.e_mobile
  is '手机号码';
comment on column EMPLOYEE.homeaddress
  is '家庭住址2';
comment on column EMPLOYEE.e_postcode
  is '邮政编码';
comment on column EMPLOYEE.hiredate
  is '入职日期';
comment on column EMPLOYEE.separatedate
  is '离职日期';
comment on column EMPLOYEE.separatereason
  is '离职原因';
comment on column EMPLOYEE.zhiwu
  is '职务';
comment on column EMPLOYEE.e_title
  is '职称';
comment on column EMPLOYEE.e_contract
  is '合同类型';
comment on column EMPLOYEE.e_salary
  is '薪资类别';
comment on column EMPLOYEE.e_state
  is '员工状态：
在职人员；兼职人员；试用人员；
离职人员；返聘人员；退休人员';
comment on column EMPLOYEE.e_profession
  is '专业';
comment on column EMPLOYEE.carno
  is '车牌号码';
comment on column EMPLOYEE.driverno
  is '行驶证号码';
comment on column EMPLOYEE.e_memo
  is '备注';
alter table EMPLOYEE
  add constraint PK_EMPLOYEE primary key (ID);

prompt
prompt Creating table MAP_BUILDING_INFO
prompt ================================
prompt
create table MAP_BUILDING_INFO
(
  building_id      NUMBER(10) not null,
  parent_id        NUMBER(10),
  building_name    VARCHAR2(100),
  building_desc    VARCHAR2(200),
  position         VARCHAR2(200),
  building_type    NUMBER(1),
  floor_num        NUMBER(2),
  status           NUMBER(1),
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14)
)
;
comment on table MAP_BUILDING_INFO
  is '存放建筑物的信息';
comment on column MAP_BUILDING_INFO.building_id
  is '建筑物编号';
comment on column MAP_BUILDING_INFO.parent_id
  is '建筑物父节点编号';
comment on column MAP_BUILDING_INFO.building_name
  is '建筑物名称';
comment on column MAP_BUILDING_INFO.building_desc
  is '建筑物描述';
comment on column MAP_BUILDING_INFO.position
  is '建筑物GIS/热点位置';
comment on column MAP_BUILDING_INFO.building_type
  is '建筑物类型：
1-小区/学校
2-大楼';
comment on column MAP_BUILDING_INFO.floor_num
  is '建筑物楼层数量';
comment on column MAP_BUILDING_INFO.status
  is '状态：
0-无效
1-有效';
comment on column MAP_BUILDING_INFO.create_by
  is '创建人
系统自动创建则此处为system';
comment on column MAP_BUILDING_INFO.create_time
  is '创建时间';
comment on column MAP_BUILDING_INFO.last_modify_by
  is '最新一次修改者';
comment on column MAP_BUILDING_INFO.last_modify_time
  is '最新一次修改时间';
alter table MAP_BUILDING_INFO
  add constraint PK_MAP_BUILDING_INFO primary key (BUILDING_ID);

prompt
prompt Creating table MAP_ROOM_INFO
prompt ============================
prompt
create table MAP_ROOM_INFO
(
  room_id          NUMBER(10) not null,
  buliding_id      NUMBER(10),
  room_name        VARCHAR2(100),
  room_desc        VARCHAR2(200),
  room_type        NUMBER(2),
  room_image       VARCHAR2(250),
  floor_no         NUMBER(2),
  position         VARCHAR2(200),
  status           NUMBER(1),
  scene_id         NUMBER(10),
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14)
)
;
comment on table MAP_ROOM_INFO
  is '存放房间的信息';
comment on column MAP_ROOM_INFO.room_id
  is '房间编号';
comment on column MAP_ROOM_INFO.buliding_id
  is '房间所在建筑物';
comment on column MAP_ROOM_INFO.room_name
  is '房间名称';
comment on column MAP_ROOM_INFO.room_desc
  is '房间描述';
comment on column MAP_ROOM_INFO.room_type
  is '房间类型：
1-教室
2-会议室
3-实验室
4-校史馆
5-楼道
7-办公室
8-宿舍
9-食堂
10-超市';
comment on column MAP_ROOM_INFO.room_image
  is '房间图片位置，相对路径';
comment on column MAP_ROOM_INFO.floor_no
  is '所在楼层';
comment on column MAP_ROOM_INFO.position
  is '房间热点位置';
comment on column MAP_ROOM_INFO.status
  is '状态：
0-无效
1-有效';
comment on column MAP_ROOM_INFO.scene_id
  is '当前使用场景ID';
comment on column MAP_ROOM_INFO.create_by
  is '创建人
系统自动创建则此处为system';
comment on column MAP_ROOM_INFO.create_time
  is '创建时间';
comment on column MAP_ROOM_INFO.last_modify_by
  is '最新一次修改者';
comment on column MAP_ROOM_INFO.last_modify_time
  is '最新一次修改时间';
alter table MAP_ROOM_INFO
  add constraint PK_MAP_ROOM_INFO primary key (ROOM_ID);
alter table MAP_ROOM_INFO
  add constraint FK_MAP_ROOM_REFERENCE_MAP_BUIL foreign key (BULIDING_ID)
  references MAP_BUILDING_INFO (BUILDING_ID);

prompt
prompt Creating table MEETING_INFO
prompt ===========================
prompt
create table MEETING_INFO
(
  meeting_id         NUMBER(10) not null,
  room_id            NUMBER(10),
  meeting_name       VARCHAR2(100),
  meeting_desc       VARCHAR2(200),
  start_time         VARCHAR2(12),
  end_time           VARCHAR2(12),
  notice_flag        NUMBER(2),
  notice_content     VARCHAR2(200),
  notice_status      NUMBER(2),
  subscribe_by       VARCHAR2(50),
  subscribe_time     VARCHAR2(14),
  scene_flag         NUMBER(2),
  scene_id           NUMBER(10),
  scene_early_time   NUMBER(4),
  scene_status       NUMBER(2),
  screen_flag        NUMBER(2),
  screen_early_time  NUMBER(4),
  screen_status      NUMBER(2),
  screen_file_path   VARCHAR2(200),
  screen_ids         VARCHAR2(200),
  meeting_status     NUMBER(1),
  prepare_by         VARCHAR2(500),
  subscribe_id       NUMBER(10),
  prepare_early_time NUMBER(4),
  prepare_status     NUMBER(2)
)
;
comment on table MEETING_INFO
  is '存放会议预订信息';
comment on column MEETING_INFO.meeting_id
  is '会议编号';
comment on column MEETING_INFO.room_id
  is '房间编号';
comment on column MEETING_INFO.meeting_name
  is '会议名称';
comment on column MEETING_INFO.meeting_desc
  is '会议描述';
comment on column MEETING_INFO.start_time
  is '会议开始时间（YYYYMMDDHHMI）';
comment on column MEETING_INFO.end_time
  is '会议结束时间（YYYYMMDDHHMI）';
comment on column MEETING_INFO.notice_flag
  is '会议通知 0-不发送 1-发送';
comment on column MEETING_INFO.notice_content
  is '会议通知内容';
comment on column MEETING_INFO.notice_status
  is '会议通知状态 0-未发送 1-已发送';
comment on column MEETING_INFO.subscribe_by
  is '预订者';
comment on column MEETING_INFO.subscribe_time
  is '预订时间（YYYYMMDDHHMISS）';
comment on column MEETING_INFO.scene_flag
  is '是否应用场景0-应用 1-应用';
comment on column MEETING_INFO.scene_id
  is '应用场景ID';
comment on column MEETING_INFO.scene_early_time
  is '场景提前应用时间（单位：分钟）';
comment on column MEETING_INFO.scene_status
  is '场景应用状态 0-未应用 1-已应用';
comment on column MEETING_INFO.screen_flag
  is '是否应用显示屏 0-应用 1-应用';
comment on column MEETING_INFO.screen_early_time
  is '显示屏提前应用时间（单位：分钟）';
comment on column MEETING_INFO.screen_status
  is '显示屏应用状态 0-未应用 1-已应用';
comment on column MEETING_INFO.screen_file_path
  is '显示屏配置文件路径';
comment on column MEETING_INFO.screen_ids
  is '应用到哪些显示屏，多个之间以逗号分隔';
comment on column MEETING_INFO.meeting_status
  is '状态：
0-取消；
1-正常；
2-结束。';
comment on column MEETING_INFO.prepare_by
  is '会议筹备人';
comment on column MEETING_INFO.subscribe_id
  is '预订者ID';
comment on column MEETING_INFO.prepare_early_time
  is '提前筹备时间（单位：分钟）';
alter table MEETING_INFO
  add constraint PK_MEETING_INFO primary key (MEETING_ID);

prompt
prompt Creating table MEETING_TARGET_INFO
prompt ==================================
prompt
create table MEETING_TARGET_INFO
(
  id         NUMBER(10) not null,
  meeting_id NUMBER(10),
  login_id   NUMBER(10) not null
)
;
comment on table MEETING_TARGET_INFO
  is '存放会议参与者信息';
comment on column MEETING_TARGET_INFO.id
  is '主键，序列';
comment on column MEETING_TARGET_INFO.meeting_id
  is '会议预订编号';
comment on column MEETING_TARGET_INFO.login_id
  is '会议参与者';
alter table MEETING_TARGET_INFO
  add constraint PK_MEETING_TARGET_INFO primary key (ID);

prompt
prompt Creating table SCENE_INFO
prompt =========================
prompt
create table SCENE_INFO
(
  scene_id          NUMBER(10) not null,
  room_id           NUMBER(10),
  scene_name        VARCHAR2(100),
  scene_desc        VARCHAR2(200),
  scene_type        NUMBER(2),
  scene_start_time  VARCHAR2(14),
  scene_end_time    VARCHAR2(14),
  valid_start_date  VARCHAR2(14),
  valid_end_date    VARCHAR2(14),
  trigger_type      NUMBER(2),
  trigger_condition NUMBER(2),
  trigger_value     VARCHAR2(20),
  scene_status      NUMBER(2)
)
;
comment on table SCENE_INFO
  is '存放场景定义信息';
comment on column SCENE_INFO.room_id
  is '房间编号';
comment on column SCENE_INFO.scene_name
  is '场景名称';
comment on column SCENE_INFO.scene_desc
  is '场景描述';
comment on column SCENE_INFO.scene_type
  is '场景类型
1-手动场景
2-自动场景';
comment on column SCENE_INFO.scene_start_time
  is '场景触发开始时间 HHMM';
comment on column SCENE_INFO.scene_end_time
  is '场景触发结束时间 HHMM';
comment on column SCENE_INFO.valid_start_date
  is '场景有效期开始日期 YYYYMMDD';
comment on column SCENE_INFO.valid_end_date
  is '场景有效期结束日期 YYYYMMDD';
comment on column SCENE_INFO.trigger_type
  is '触发条件类型:1-温度 2-湿度';
comment on column SCENE_INFO.trigger_condition
  is '触发条件判断：1-大于 2-大于等于 3-小于 4-小于等于';
comment on column SCENE_INFO.trigger_value
  is '触发条件值';
comment on column SCENE_INFO.scene_status
  is '状态： 0-停用 1-有效';
alter table SCENE_INFO
  add constraint PK_SCENE_INFO primary key (SCENE_ID);

prompt
prompt Creating table SCENE_CONFIG_INFO
prompt ================================
prompt
create table SCENE_CONFIG_INFO
(
  id         NUMBER(10) not null,
  scene_id   NUMBER(10),
  dev_id     NUMBER(10),
  attr_key   VARCHAR2(50),
  attr_value VARCHAR2(20)
)
;
comment on table SCENE_CONFIG_INFO
  is '存放场景配置信息';
comment on column SCENE_CONFIG_INFO.id
  is 'ID';
comment on column SCENE_CONFIG_INFO.scene_id
  is '场景ID';
comment on column SCENE_CONFIG_INFO.dev_id
  is '设备ID';
comment on column SCENE_CONFIG_INFO.attr_key
  is '属性ID';
comment on column SCENE_CONFIG_INFO.attr_value
  is '属性值';
alter table SCENE_CONFIG_INFO
  add constraint PK_SCENE_CONFIG_INFO primary key (ID);
alter table SCENE_CONFIG_INFO
  add constraint FK_SCENE_CO_REFERENCE_SCENE_IN foreign key (SCENE_ID)
  references SCENE_INFO (SCENE_ID);

prompt
prompt Creating table SMS_TASK
prompt =======================
prompt
create table SMS_TASK
(
  id          NUMBER(10) not null,
  terminalids VARCHAR2(1000) not null,
  content     VARCHAR2(200),
  task_type   NUMBER(1) default 1,
  sign        VARCHAR2(100),
  status      NUMBER(1)
)
;
alter table SMS_TASK
  add constraint PK_SMS_TASK primary key (ID);

prompt
prompt Creating table SYS_CORP
prompt =======================
prompt
create table SYS_CORP
(
  corp_id          NUMBER(10) not null,
  parent_corp_id   NUMBER(10) not null,
  corp_name        VARCHAR2(50) not null,
  corp_type        NUMBER(2) not null,
  corp_desc        VARCHAR2(200),
  status           NUMBER(2) not null,
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14)
)
;
comment on table SYS_CORP
  is '存放单位信息';
comment on column SYS_CORP.corp_id
  is 'ID，主键';
comment on column SYS_CORP.parent_corp_id
  is '省公司上级单位编号为-1';
comment on column SYS_CORP.corp_name
  is '单位名称';
comment on column SYS_CORP.corp_type
  is '1 - 单位
2 - 部门
';
comment on column SYS_CORP.corp_desc
  is '单位描述';
comment on column SYS_CORP.status
  is '状态
0－无效
1－有效';
comment on column SYS_CORP.create_by
  is '创建人
系统自动创建则此处为system';
comment on column SYS_CORP.create_time
  is '创建时间';
comment on column SYS_CORP.last_modify_by
  is '最新一次修改者';
comment on column SYS_CORP.last_modify_time
  is '最新一次修改时间';
alter table SYS_CORP
  add constraint PK_SYS_CORP primary key (CORP_ID);

prompt
prompt Creating table SYS_DEPT
prompt =======================
prompt
create table SYS_DEPT
(
  dept_id          NUMBER(10) not null,
  dept_name        VARCHAR2(50),
  corp_id          NUMBER(10) not null,
  status           NUMBER(2) not null,
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14),
  parent_dept_id   NUMBER(10)
)
;
comment on column SYS_DEPT.dept_id
  is '部门编号';
comment on column SYS_DEPT.dept_name
  is '部门名称';
comment on column SYS_DEPT.corp_id
  is '单位编号';
comment on column SYS_DEPT.status
  is '状态
0－无效
1－有效';
comment on column SYS_DEPT.create_by
  is '创建人
系统自动创建则此处为system';
comment on column SYS_DEPT.create_time
  is '创建时间';
comment on column SYS_DEPT.last_modify_by
  is '最新一次修改者';
comment on column SYS_DEPT.last_modify_time
  is '最新一次修改时间';
comment on column SYS_DEPT.parent_dept_id
  is '上级部门编号';
alter table SYS_DEPT
  add constraint PK_SYS_DEPT primary key (DEPT_ID);

prompt
prompt Creating table SYS_LOG
prompt ======================
prompt
create table SYS_LOG
(
  log_id         NUMBER(10) not null,
  oper_user_id   NUMBER(10) not null,
  oper_user_name VARCHAR2(50) not null,
  oper_type      NUMBER(2) not null,
  oper_time      VARCHAR2(14) not null,
  oper_ip        VARCHAR2(20) not null,
  oper_desc      VARCHAR2(200),
  system_id      NUMBER(2) not null
)
;
comment on table SYS_LOG
  is '系统日志表-记录系统中的增删改查登录等操作';
comment on column SYS_LOG.log_id
  is '日志序号';
comment on column SYS_LOG.oper_user_id
  is '操作者ID';
comment on column SYS_LOG.oper_user_name
  is '操作者名称';
comment on column SYS_LOG.oper_type
  is '操作类型0：登录 ，1：增加，2：删除，3：修改';
comment on column SYS_LOG.oper_time
  is '操作时间：（YYYYMMDDHHMISS）';
comment on column SYS_LOG.oper_ip
  is '操作者ip：192.168.1.1';
comment on column SYS_LOG.oper_desc
  is '对当前操作做详细的描述';
comment on column SYS_LOG.system_id
  is '日志所属系统';
alter table SYS_LOG
  add constraint PK_SYS_LOG primary key (LOG_ID);

prompt
prompt Creating table SYS_LOGIN
prompt ========================
prompt
create table SYS_LOGIN
(
  login_id         NUMBER(10) not null,
  login_name       VARCHAR2(50) not null,
  login_pwd        VARCHAR2(32) not null,
  user_name        VARCHAR2(20),
  user_type        NUMBER(2) not null,
  corp_id          NUMBER(10),
  dept_id          NUMBER(10),
  system_id        NUMBER(2) not null,
  email            VARCHAR2(100),
  tel              VARCHAR2(21),
  valid_tag        CHAR(1),
  valid_date       VARCHAR2(14),
  user_remark      VARCHAR2(200),
  record_corp_id   NUMBER(10),
  login_last_time  VARCHAR2(14),
  status           NUMBER(2) not null,
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14),
  car_no           VARCHAR2(20),
  user_code        VARCHAR2(50)
)
;
comment on table SYS_LOGIN
  is '定义登录帐号信息';
comment on column SYS_LOGIN.login_id
  is 'ID，主键';
comment on column SYS_LOGIN.login_name
  is '登录用户名';
comment on column SYS_LOGIN.login_pwd
  is '登录密码';
comment on column SYS_LOGIN.user_name
  is ' 用户真实姓名';
comment on column SYS_LOGIN.user_type
  is '0－超级管理员
1－管理员
2－操作员';
comment on column SYS_LOGIN.corp_id
  is '用户所属单位编号';
comment on column SYS_LOGIN.dept_id
  is '用户所属部门编号';
comment on column SYS_LOGIN.system_id
  is '用户所属系统';
comment on column SYS_LOGIN.email
  is '用户email地址';
comment on column SYS_LOGIN.tel
  is '联系电话';
comment on column SYS_LOGIN.valid_tag
  is '是否永久有效
Y：永久有效
N：有时间限制';
comment on column SYS_LOGIN.valid_date
  is '有效日期，当是否永久有效为N时此字段才有值';
comment on column SYS_LOGIN.user_remark
  is '用户描述';
comment on column SYS_LOGIN.record_corp_id
  is '用户的创建单位，默认谁创建谁管理';
comment on column SYS_LOGIN.login_last_time
  is '最后一次登录时间';
comment on column SYS_LOGIN.status
  is '状态
0－无效
1－有效';
comment on column SYS_LOGIN.create_by
  is '创建人
系统自动创建则此处为system';
comment on column SYS_LOGIN.create_time
  is '创建时间';
comment on column SYS_LOGIN.last_modify_by
  is '最新一次修改者';
comment on column SYS_LOGIN.last_modify_time
  is '最新一次修改时间';
comment on column SYS_LOGIN.car_no
  is '车牌号';
comment on column SYS_LOGIN.user_code
  is '用户编号';
alter table SYS_LOGIN
  add constraint PK_SYS_LOGIN primary key (LOGIN_ID);
alter table SYS_LOGIN
  add constraint FK_SYS_LOGI_REFERENCE_SYS_CORP foreign key (CORP_ID)
  references SYS_CORP (CORP_ID);
alter table SYS_LOGIN
  add constraint FK_SYS_LOGI_REFERENCE_SYS_DEPT foreign key (DEPT_ID)
  references SYS_DEPT (DEPT_ID);

prompt
prompt Creating table SYS_ROLE
prompt =======================
prompt
create table SYS_ROLE
(
  role_id          NUMBER(10) not null,
  role_name        VARCHAR2(50) not null,
  role_desc        VARCHAR2(200),
  corp_id          NUMBER(10) not null,
  system_id        NUMBER(2) not null,
  status           NUMBER(2) not null,
  create_by        VARCHAR2(50),
  create_time      VARCHAR2(14),
  last_modify_by   VARCHAR2(50),
  last_modify_time VARCHAR2(14)
)
;
comment on table SYS_ROLE
  is '定义系统角色信息，角色是一组权限的集合';
comment on column SYS_ROLE.role_id
  is 'ID，主键';
comment on column SYS_ROLE.role_name
  is '角色名称';
comment on column SYS_ROLE.role_desc
  is '角色描述';
comment on column SYS_ROLE.corp_id
  is '角色所属单位编号';
comment on column SYS_ROLE.system_id
  is '角色所属系统
';
comment on column SYS_ROLE.status
  is '状态
0－无效
1－有效';
comment on column SYS_ROLE.create_by
  is '创建人
系统自动创建则此处为system';
comment on column SYS_ROLE.create_time
  is '创建时间';
comment on column SYS_ROLE.last_modify_by
  is '最新一次修改者';
comment on column SYS_ROLE.last_modify_time
  is '最新一次修改时间';
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ROLE_ID);

prompt
prompt Creating table SYS_LOGIN_ROLE
prompt =============================
prompt
create table SYS_LOGIN_ROLE
(
  id       NUMBER(10) not null,
  role_id  NUMBER(10),
  login_id NUMBER(10)
)
;
comment on table SYS_LOGIN_ROLE
  is '定义登录者与角色的关联关系，一个登录者可以有一个或多个角色';
comment on column SYS_LOGIN_ROLE.id
  is 'ID，主键';
comment on column SYS_LOGIN_ROLE.role_id
  is 'ID，主键';
comment on column SYS_LOGIN_ROLE.login_id
  is 'ID，主键';
alter table SYS_LOGIN_ROLE
  add constraint PK_SYS_LOGIN_ROLE primary key (ID);
alter table SYS_LOGIN_ROLE
  add constraint FK_SYS_LOGI_REFERENCE_SYS_LOGI foreign key (LOGIN_ID)
  references SYS_LOGIN (LOGIN_ID);
alter table SYS_LOGIN_ROLE
  add constraint FK_SYS_LOGI_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
  references SYS_ROLE (ROLE_ID);

prompt
prompt Creating table SYS_PERMISSION
prompt =============================
prompt
create table SYS_PERMISSION
(
  permission_id        NUMBER(10) not null,
  permission_name      VARCHAR2(50) not null,
  permission_type      NUMBER(2) not null,
  permission_url       VARCHAR2(250),
  permission_sort      NUMBER(10) not null,
  parent_permission_id NUMBER(10),
  system_id            NUMBER(2) not null,
  status               NUMBER(2) not null,
  permission_ico       VARCHAR2(100)
)
;
comment on column SYS_PERMISSION.permission_id
  is 'ID，主键';
comment on column SYS_PERMISSION.permission_name
  is '权限名称';
comment on column SYS_PERMISSION.permission_type
  is '权限类型
1－菜单
2－操作';
comment on column SYS_PERMISSION.permission_url
  is '当权限类型为菜单时，该处表示菜单的URL
当权限类型为操作时，该处表示操作对应的按钮名称';
comment on column SYS_PERMISSION.permission_sort
  is '树形展现的排列顺序，类似
1
   11
   12
2
   21
   22';
comment on column SYS_PERMISSION.parent_permission_id
  is '上级权限ID';
comment on column SYS_PERMISSION.system_id
  is '该菜单的所属系统';
comment on column SYS_PERMISSION.status
  is '状态
0－无效
1－有效';
alter table SYS_PERMISSION
  add constraint PK_SYS_PERMISSION primary key (PERMISSION_ID);

prompt
prompt Creating table SYS_ROLE_PERMISSION
prompt ==================================
prompt
create table SYS_ROLE_PERMISSION
(
  id            NUMBER(10) not null,
  permission_id NUMBER(10),
  role_id       NUMBER(10)
)
;
comment on table SYS_ROLE_PERMISSION
  is '定义角色与权限的关联关系，一个角色可以有一个或多个权限';
comment on column SYS_ROLE_PERMISSION.id
  is 'ID，主键';
comment on column SYS_ROLE_PERMISSION.permission_id
  is '菜单ID，主键';
comment on column SYS_ROLE_PERMISSION.role_id
  is 'ID，主键';
alter table SYS_ROLE_PERMISSION
  add constraint PK_SYS_ROLE_PERMISSION primary key (ID);
alter table SYS_ROLE_PERMISSION
  add constraint FK_SYS_ROLE_REFERENCE_SYS_PERM foreign key (PERMISSION_ID)
  references SYS_PERMISSION (PERMISSION_ID);
alter table SYS_ROLE_PERMISSION
  add constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
  references SYS_ROLE (ROLE_ID);

prompt
prompt Creating table SYS_ROLE_ROOM
prompt ============================
prompt
create table SYS_ROLE_ROOM
(
  id            NUMBER(10) not null,
  role_id       NUMBER(10),
  room_id       NUMBER(10),
  controll_flag NUMBER(2)
)
;
comment on column SYS_ROLE_ROOM.id
  is 'ID,主键';
comment on column SYS_ROLE_ROOM.role_id
  is 'ID，主键';
comment on column SYS_ROLE_ROOM.room_id
  is '房间ID';
comment on column SYS_ROLE_ROOM.controll_flag
  is '是否可控制 0-不可控制 1-可控制';
alter table SYS_ROLE_ROOM
  add constraint PK_SYS_ROLE_ROOM primary key (ID);
alter table SYS_ROLE_ROOM
  add constraint FK_SYS_ROLE_REF_ROLE_ROOM foreign key (ROLE_ID)
  references SYS_ROLE (ROLE_ID);

prompt
prompt Creating sequence SEQ_DEV_ALARM_CONFIG
prompt ======================================
prompt
create sequence SEQ_DEV_ALARM_CONFIG
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DEV_ALARM_LOG
prompt ===================================
prompt
create sequence SEQ_DEV_ALARM_LOG
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DEV_ATTRIBUTES
prompt ====================================
prompt
create sequence SEQ_DEV_ATTRIBUTES
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DEV_ATTR_HISTORY_INFO
prompt ===========================================
prompt
create sequence SEQ_DEV_ATTR_HISTORY_INFO
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DEV_ATTR_INFO
prompt ===================================
prompt
create sequence SEQ_DEV_ATTR_INFO
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DEV_CLASS_GROUP
prompt =====================================
prompt
create sequence SEQ_DEV_CLASS_GROUP
minvalue 1
maxvalue 9999999999
start with 1020
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DEV_CLASS_INFO
prompt ====================================
prompt
create sequence SEQ_DEV_CLASS_INFO
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DEV_INFO
prompt ==============================
prompt
create sequence SEQ_DEV_INFO
minvalue 1
maxvalue 9999999999
start with 1240
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_DEV_STATUS
prompt ================================
prompt
create sequence SEQ_DEV_STATUS
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MAP_BUILDING_INFO
prompt =======================================
prompt
create sequence SEQ_MAP_BUILDING_INFO
minvalue 1
maxvalue 9999999999
start with 1020
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MAP_ROOM_INFO
prompt ===================================
prompt
create sequence SEQ_MAP_ROOM_INFO
minvalue 1
maxvalue 9999999999
start with 1020
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MEETING
prompt =============================
prompt
create sequence SEQ_MEETING
minvalue 1
maxvalue 9999999999
start with 1280
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MEETING_TARGET
prompt ====================================
prompt
create sequence SEQ_MEETING_TARGET
minvalue 1
maxvalue 9999999999
start with 3040
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SCENE_CONFIG
prompt ==================================
prompt
create sequence SEQ_SCENE_CONFIG
minvalue 1
maxvalue 9999999999
start with 1240
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SCENE_INFO
prompt ================================
prompt
create sequence SEQ_SCENE_INFO
minvalue 1
maxvalue 9999999999
start with 1160
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_TASK
prompt ==============================
prompt
create sequence SEQ_SMS_TASK
minvalue 1
maxvalue 999999999999999999999999999
start with 1061
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_CORP
prompt ==============================
prompt
create sequence SEQ_SYS_CORP
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_DEPT
prompt ==============================
prompt
create sequence SEQ_SYS_DEPT
minvalue 1
maxvalue 9999999999
start with 1060
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_LOG
prompt =============================
prompt
create sequence SEQ_SYS_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 3480
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_LOGIN
prompt ===============================
prompt
create sequence SEQ_SYS_LOGIN
minvalue 1
maxvalue 9999999999
start with 9980
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_LOGIN_ROLE
prompt ====================================
prompt
create sequence SEQ_SYS_LOGIN_ROLE
minvalue 1
maxvalue 99999999999
start with 10020
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_PERMISSION
prompt ====================================
prompt
create sequence SEQ_SYS_PERMISSION
minvalue 1
maxvalue 9999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ROLE
prompt ==============================
prompt
create sequence SEQ_SYS_ROLE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1040
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ROLE_PERMISSION
prompt =========================================
prompt
create sequence SEQ_SYS_ROLE_PERMISSION
minvalue 1
maxvalue 999999999999999
start with 1060
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ROLE_ROOM
prompt ===================================
prompt
create sequence SEQ_SYS_ROLE_ROOM
minvalue 1
maxvalue 99999999999
start with 1060
increment by 1
cache 20;


spool off
