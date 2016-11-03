/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/3 16:58:43                           */
/*==============================================================*/


drop table if exists ac_config_datasource;

drop table if exists ac_config_db;

drop table if exists ac_config_package_file;

drop table if exists ac_config_template;

drop table if exists ac_config_template_file;

drop table if exists ac_config_template_package;

drop table if exists ac_meta_application;

drop table if exists ac_meta_application_table;

drop table if exists ac_meta_module;

/*==============================================================*/
/* Table: ac_config_datasource                                  */
/*==============================================================*/
create table ac_config_datasource
(
   datasource_id        int not null comment '数据源编码',
   datasource_name      varchar(30) not null comment '数据源名称',
   datasource_ip        varchar(15) not null comment '数据源ip',
   datasource_port      varchar(8) not null comment '数据源端口',
   db_name              varchar(35) not null comment '数据库名称',
   db_connect_name      varchar(30) not null comment '数据库用户名',
   db_connect_pwd       varchar(40) not null comment '数据库密码',
   database_id          varchar(25) comment '数据库编码',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (datasource_id)
);

alter table ac_config_datasource comment '应用配置/数据源配置';

/*==============================================================*/
/* Table: ac_config_db                                          */
/*==============================================================*/
create table ac_config_db
(
   database_id          varchar(25) not null comment '数据库编码',
   database_desc        varchar(100) comment '数据库描述',
   database_version     varchar(10) not null comment '版本:V2.3.67',
   database_driver      varchar(50) comment '数据源驱动',
   is_available         tinyint(1) not null comment '是否可用',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (database_id)
);

alter table ac_config_db comment '应用配置/数据库信息';

/*==============================================================*/
/* Table: ac_config_package_file                                */
/*==============================================================*/
create table ac_config_package_file
(
   package_id           int not null comment '套餐编码',
   template_id          varchar(25) not null comment '模板编码',
   template_version     varchar(10) not null comment '模板版本',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (package_id, template_id, template_version)
);

alter table ac_config_package_file comment '应用配置/模板套餐关系表';

/*==============================================================*/
/* Table: ac_config_template                                    */
/*==============================================================*/
create table ac_config_template
(
   template_id          varchar(25) not null comment '模板编码',
   template_desc        varchar(100) comment '模板描述',
   template_version     varchar(10) not null comment '模板版本:V2.3.67',
   template_type        tinyint(1) not null comment '模板类型：前端、control、dao',
   is_available         tinyint(1) not null comment '是否可用',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (template_id, template_version)
);

alter table ac_config_template comment '应用配置/模板信息';

/*==============================================================*/
/* Table: ac_config_template_file                               */
/*==============================================================*/
create table ac_config_template_file
(
   template_file_id     int not null comment '模板文件编码',
   template_id          varchar(25) comment '模板编码',
   template_version     varchar(10) comment '模板版本',
   template_file_name   varchar(50) not null comment '模板文件名：ACTemplateController.ftl',
   process_file_name    varchar(50) not null comment '输出文件名：${}Controller.java，${}为替换字符',
   process_path         varchar(100) not null comment '输出相对路径：相对生成应用的根目录',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (template_file_id)
);

alter table ac_config_template_file comment '应用配置/模板文件信息';

/*==============================================================*/
/* Table: ac_config_template_package                            */
/*==============================================================*/
create table ac_config_template_package
(
   package_id           int not null comment '套餐编码',
   package_name         varchar(30) not null comment '套餐名称',
   package_desc         varchar(150) comment '套餐描述',
   is_available         tinyint(1) not null comment '是否可用',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (package_id)
);

alter table ac_config_template_package comment '应用配置/模板套餐';

/*==============================================================*/
/* Table: ac_meta_application                                   */
/*==============================================================*/
create table ac_meta_application
(
   app_id               int not null comment '应用编码',
   app_en_name          varchar(10) not null comment '系统英文名称',
   app_zh_name          varchar(10) not null comment '应用中文名称',
   group_id             varchar(35) not null comment '组织标识',
   app_db_script        varchar(30) not null comment '数据库脚本文件',
   package_id           int not null comment '模板套餐编码',
   datasource_id        int not null comment '数据源编码',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (app_id)
);

alter table ac_meta_application comment '元数据管理/应用信息';

/*==============================================================*/
/* Table: ac_meta_application_table                             */
/*==============================================================*/
create table ac_meta_application_table
(
   app_table_id         int not null comment '应用表编码',
   app_id               int not null comment '应用编码',
   app_table_name       varchar(30) not null comment '应用表名称',
   app_table_remarks    varchar(30) not null comment '应用表备注',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (app_table_id)
);

alter table ac_meta_application_table comment '元数据管理/应用信息';

/*==============================================================*/
/* Table: ac_meta_module                                        */
/*==============================================================*/
create table ac_meta_module
(
   app_module_id        int not null comment '应用模块编码',
   app_id               int not null comment '应用编码',
   app_module_name      varchar(20) not null comment '应用模块名称',
   app_module_remark    varchar(15) comment '应用模块备注',
   create_time          datetime not null comment '创建时间',
   create_by            varchar(7) not null comment '创建者',
   update_time          timestamp comment '更新时间',
   update_by            varchar(7) comment '更新者',
   primary key (app_module_id)
);

alter table ac_meta_module comment '元数据管理/应用模块信息';

