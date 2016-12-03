# Host: 127.0.0.1  (Version: 5.6.24)
# Date: 2016-08-15 22:01:09
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "advise"
#

DROP TABLE IF EXISTS `advise`;
CREATE TABLE `advise` (
  `Id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `user_id` bigint(1) unsigned DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `describle` varchar(255) DEFAULT NULL,
  `tel` varchar(25) DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `enable` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "advise"
#

INSERT INTO `advise` VALUES (3,'gbq',NULL,'990924291@qq.com','还行','18060476885',NULL,NULL,NULL),(4,'高炳秋',NULL,'990924291@qq.com','这个系统不错','18060476885',NULL,NULL,NULL);

#
# Structure for table "errors"
#

DROP TABLE IF EXISTS `errors`;
CREATE TABLE `errors` (
  `Id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_Id` varchar(50) DEFAULT '',
  `type` tinyint(3) unsigned DEFAULT '0',
  `enable` tinyint(3) unsigned DEFAULT '0',
  `enable_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `now_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `current_count` int(11) unsigned DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='验证次数限制';

#
# Data for table "errors"
#

INSERT INTO `errors` VALUES (4,'',0,0,'0000-00-00 00:00:00','0000-00-00 00:00:00',0),(5,'15',1,1,'2016-05-27 13:23:52','2016-05-27 13:23:50',36),(6,'16',1,1,'2016-05-27 13:28:42','2016-05-27 13:28:40',6),(7,'18',1,1,'2016-05-28 21:34:43','2016-05-28 21:34:42',1),(8,NULL,1,1,'2016-07-21 21:02:42','2016-07-21 21:02:40',0),(9,NULL,1,1,'2016-08-11 14:14:29','2016-08-11 14:14:28',0);

#
# Structure for table "manager"
#

DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `Id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` tinyint(3) DEFAULT NULL,
  `password` varchar(1024) DEFAULT NULL,
  `last_pass_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pass_count` int(11) DEFAULT NULL,
  `enable` tinyint(3) unsigned DEFAULT '0',
  `private_key` longtext,
  `privateKey` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "manager"
#

INSERT INTO `manager` VALUES (1,'gbq',1,'123456','2016-05-27 14:31:51',22,1,NULL,NULL),(4,'lzs',1,'g951Mse66G9tBH5yOIVMsV7I+4fqqh7YRTUl6i4v6gHZ8naFJHzmOmJDT/ipogkKRznhRyaSA0wRpm4lvj1f/Ye+z1Zo+a7u0yCwXp/aB546+pGvwfKowoYTdPiguMWk5JFrv1+Xl1z4eZqbzGDLs6wSX5F6VJU0Uyf9oTGqDik=','2016-06-05 08:54:01',0,1,NULL,NULL),(5,'czx',1,'j8dEmcMergv+iS4AUvZC/nwGBBv1u3IsFD1uYbZYY/cpVA0NJJ5CVZXEyEPJ5SCAP7eY3OgMl97saTJlGEEBlxZgiEpPAeTSC+3+abtiDuMNYZu4TZfEi9QisMUTRm8c0+9ztbmQnsLBjeddKqjY3mxx2TZJQgLxeyffpybKKpQ=','2016-06-05 08:54:05',0,1,NULL,NULL),(6,'smj',1,'ToHn60vm6G+xOHV8+VQj3Hv4QPTfAADP4yPWIkAnMcMcMj1MWHTm0uQIZHP1pbJ9XSDXEcBZBlEgTQqT0Q6WoQS+5EZDG+edbCmYD6szBuQ5A0jPSqbPlkZyflGcihcwprlJ1kVJQR1UzIFndO/Hs32IUj+2+RGe9pPvx3cLOnk=','2016-06-06 11:27:24',12,1,NULL,'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAInEG4fJvyJa3oxCgklvfMs02To81L30W3KAxP02MZPlmRyVAMv/rfA/2ktEeLzp9LS1PNRRVJacizb3WjmwgvRyk70TcU5a+KJz+m8MqNZGnOIMXmFPB1+DJI39ZT0jpz0Kzaa+SeVsoc/+7A9Bhg38UJfnLg0+XKau3lhzio/tAgMBAAECgYANP+Yh7h5K0JB8zty28VkANin6NuWDC2BMJ+RKMg8cmUVbgV0dQY7Z/1ogSQqPqLSXOKMMDHJlNdMRy0JLMBW4Bpf4CCuoKyuRqeyk/OBD1yqK6fE89InACp+oFzc3wCJesstMmmB6sdUlIEm5xrlynrdjAjvPePk9DrtKSF9kRQJBAMx11U0Tro2mmFImA7ruD8SImLYMqtDdJ8HI3aW7Bm8/WwqZ/amAa7HO1XzDdOw4xI+QGmw8/A4bEP4slVx9cBcCQQCsfmHw3Ly6M4DfP5ESEyA13m1NikEARMHAehS1bwnFAz6EM9jwg5K8fvJTvbM2V/7OrQF/DRqUWD84ihR/Ux6bAkEAjTAxg3A78YVBWjj2CneqW8UX717/9gtCEQdsR63G27ucWs3L5OjRdY+mzOkMLLNmyNxx4pyaUgNO0LZozd2xMQJBAJF5ONJw5z3GB+6/5nIu7SBhSR929ODsG9dfXi2xyrR7yvKl41TcMhmEmoxjVUwEpGHbaIKlI4BueEsPsL+88vcCQAdfAOBz24g38RqF8WwI7bODB8HU/32v52t8rILwwBYvmYIEu0yeg1IOo4x6IgIdTC/5tFWzuW9cOJbbD2VWb4U=');

#
# Structure for table "records"
#

DROP TABLE IF EXISTS `records`;
CREATE TABLE `records` (
  `Id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(1) unsigned DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `link` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `pass_count` int(11) DEFAULT NULL,
  `last_pass_time` timestamp NULL DEFAULT NULL,
  `web_id` varchar(50) DEFAULT NULL,
  `createTime` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "records"
#

INSERT INTO `records` VALUES (8,15,'baidu','FyAA6E1kow9VmYmmufYILQ==','www.baidu.com','2016-06-06 16:33:15',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "security_code"
#

DROP TABLE IF EXISTS `security_code`;
CREATE TABLE `security_code` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT '',
  `code` int(11) unsigned NOT NULL DEFAULT '0',
  `tel` varchar(25) DEFAULT NULL,
  `email` varchar(50) DEFAULT '',
  `type` tinyint(3) unsigned DEFAULT NULL,
  `enable` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

#
# Data for table "security_code"
#

INSERT INTO `security_code` VALUES (1,'15',874533,'18060476885',NULL,1,0),(2,'15',833534,'18060476885',NULL,1,0),(3,'15',96487,NULL,'990924291@qq.com',1,0),(4,'15',562969,'18060476885',NULL,1,0),(5,'15',225548,'18060476885',NULL,1,0),(6,'15',231771,NULL,'990924291@qq.com',1,0),(7,NULL,419928,'18860115386',NULL,1,0),(8,NULL,43419,'18860115316',NULL,1,0),(9,'1',1,'1','1',1,1),(10,'1',1,'1','1',1,1),(11,NULL,558355,'koj',NULL,1,0),(12,NULL,7170,NULL,NULL,1,0);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL,
  `age` int(4) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enable` tinyint(3) NOT NULL DEFAULT '0' COMMENT '有效位',
  `tel` varchar(25) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `image` varchar(50) DEFAULT NULL COMMENT '头像',
  `location` varchar(50) DEFAULT NULL COMMENT '地址',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `id_num` varchar(25) DEFAULT NULL COMMENT '身份证',
  `birdate` varchar(25) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (13,'hello','DGxlRtZs+sZkTGKoRQfgKg==',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'tiantian','FyAA6E1kow9VmYmmufYILQ==',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'gbq','AxDU8mW+eKzU4TcjcULJWg==',0,NULL,1,'18060476885','990924291@qq.com','/images/upload/images/151465042964745.png',NULL,NULL,'350524199311153813',NULL,'高炳秋'),(16,'林志胜','FyAA6E1kow9VmYmmufYILQ==',0,NULL,1,'18860115386',NULL,NULL,NULL,NULL,'350524199311153813',NULL,'林志胜'),(17,'shenmj','Wwj/V4Z22PwS43eLqp2u1A==',0,NULL,1,'18860115316',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'86868','Glbu96+ufRqIQ6AbU1MSMw==',0,NULL,1,'koj',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "web"
#

DROP TABLE IF EXISTS `web`;
CREATE TABLE `web` (
  `Id` bigint(5) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL DEFAULT '',
  `describle` varchar(255) NOT NULL DEFAULT '',
  `link` varchar(50) NOT NULL DEFAULT '',
  `score` tinyint(3) NOT NULL DEFAULT '0',
  `member` bigint(20) unsigned NOT NULL DEFAULT '0',
  `code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "web"
#

INSERT INTO `web` VALUES (1,'百度','中国最大的搜索引擎','www.baidu.com',8,8888888,NULL),(2,'测试','接口测试网站','127.0.0.1/test',6,20,NULL);
