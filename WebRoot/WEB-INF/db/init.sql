-- 步骤：1、创建数据库；2、创建以下三张表；3、启动工程，会自动生成activiti相关的23张表；4、执行以下的插入脚本到activiti几个表中初始化数据

-- 初始化数据脚本，也可以用hibernate制动生成(这三张表为手工创建的表，用来存储activiti之外的业务数据)
CREATE TABLE ACT_QINGJIA (
        QID BIGINT NOT NULL AUTO_INCREMENT,
        DAYS BIGINT,
        USER_NAME VARCHAR(100),
        REMARK VARCHAR(100),
        DATA_TM DATETIME,
        FLOW_ID VARCHAR(100),
        PRIMARY KEY (QID)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;
    
     CREATE TABLE ACT_QINGJIA_HIS (
        HID BIGINT NOT NULL AUTO_INCREMENT,
        QID BIGINT,
        SHENPI_ACTION VARCHAR(100),
        SHENPI_REMARK VARCHAR(100),
        DATA_TM DATETIME,
        EXECUTOR VARCHAR(100),
        PRIMARY KEY (HID)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;
    
    CREATE TABLE TS_CHARTS_INFO (
        CHART_ID BIGINT NOT NULL AUTO_INCREMENT,
        CHART_NAME LONGTEXT,
        CHART_TYPE DECIMAL(22,0),
        CHART_DESC LONGTEXT,
        BELONG_LINK DECIMAL(22,0),
        CHART_USE LONGTEXT,
        KEY_FIELD LONGTEXT,
        FREQUENCE_TM DECIMAL(22,0),
        JS_NAME VARCHAR(100),
        XTYPE VARCHAR(100),
        PRIMARY KEY (CHART_ID)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;
    
    
    
-- activiti 表初始化用户角色数据    

/*Table structure for table `act_id_group` */

/*Data for the table `act_id_group` */

insert  into `act_id_group`(`ID_`,`REV_`,`NAME_`,`TYPE_`) values ('bumenjingli',NULL,'bumenjingli',NULL);
insert  into `act_id_group`(`ID_`,`REV_`,`NAME_`,`TYPE_`) values ('zongjinli',NULL,'zongjinli',NULL);
insert  into `act_id_group`(`ID_`,`REV_`,`NAME_`,`TYPE_`) values ('zuzhang',NULL,'zuzhang',NULL);
insert  into `act_id_group`(`ID_`,`REV_`,`NAME_`,`TYPE_`) values ('普通用户',NULL,'普通用户',NULL);

/*Table structure for table `act_id_membership` */


/*Data for the table `act_id_membership` */

insert  into `act_id_membership`(`USER_ID_`,`GROUP_ID_`) values ('6','bumenjingli');
insert  into `act_id_membership`(`USER_ID_`,`GROUP_ID_`) values ('7','zongjinli');
insert  into `act_id_membership`(`USER_ID_`,`GROUP_ID_`) values ('5','zuzhang');
insert  into `act_id_membership`(`USER_ID_`,`GROUP_ID_`) values ('1','普通用户');
insert  into `act_id_membership`(`USER_ID_`,`GROUP_ID_`) values ('2','普通用户');
insert  into `act_id_membership`(`USER_ID_`,`GROUP_ID_`) values ('3','普通用户');
insert  into `act_id_membership`(`USER_ID_`,`GROUP_ID_`) values ('4','普通用户');

/*Table structure for table `act_id_user` */

insert  into `act_id_user`(`ID_`,`REV_`,`FIRST_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values ('1',1,'张','三',NULL,'1',NULL);
insert  into `act_id_user`(`ID_`,`REV_`,`FIRST_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values ('2',1,'李','四',NULL,'1',NULL);
insert  into `act_id_user`(`ID_`,`REV_`,`FIRST_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values ('3',1,'王','五',NULL,'1',NULL);
insert  into `act_id_user`(`ID_`,`REV_`,`FIRST_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values ('4',1,'赵','六',NULL,NULL,NULL);
insert  into `act_id_user`(`ID_`,`REV_`,`FIRST_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values ('5',1,'组','长',NULL,NULL,NULL);
insert  into `act_id_user`(`ID_`,`REV_`,`FIRST_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values ('6',1,'经','理',NULL,NULL,NULL);
insert  into `act_id_user`(`ID_`,`REV_`,`FIRST_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values ('7',1,'总','总',NULL,NULL,NULL);

