/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : malbox_admin

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2014-10-24 19:41:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_cluster`
-- ----------------------------
DROP TABLE IF EXISTS `t_cluster`;
CREATE TABLE `t_cluster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cluster_interval` varchar(255) DEFAULT NULL,
  `test` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cluster
-- ----------------------------
INSERT INTO `t_cluster` VALUES ('1', '13', null);
INSERT INTO `t_cluster` VALUES ('2', '13', null);
INSERT INTO `t_cluster` VALUES ('3', '13', null);
INSERT INTO `t_cluster` VALUES ('4', '12', null);

-- ----------------------------
-- Table structure for `t_dbinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_dbinfo`;
CREATE TABLE `t_dbinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `db_ip` varchar(15) DEFAULT NULL,
  `db_port` varchar(16) DEFAULT NULL,
  `db_name` varchar(255) DEFAULT NULL,
  `db_user` varchar(255) DEFAULT NULL,
  `db_passwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dbinfo
-- ----------------------------
INSERT INTO `t_dbinfo` VALUES ('2', '134.13.13.13', '12', 'zsy', '1', '2');
INSERT INTO `t_dbinfo` VALUES ('5', '12.12.12.12', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for `t_ftpinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_ftpinfo`;
CREATE TABLE `t_ftpinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) DEFAULT NULL,
  `port` varchar(16) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ftpinfo
-- ----------------------------
INSERT INTO `t_ftpinfo` VALUES ('6', '1.1.1.1', '11', '2', '1');

-- ----------------------------
-- Table structure for `t_license`
-- ----------------------------
DROP TABLE IF EXISTS `t_license`;
CREATE TABLE `t_license` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `license` varchar(255) DEFAULT NULL,
  `expires` timestamp NULL DEFAULT NULL,
  `isvalid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_license
-- ----------------------------
INSERT INTO `t_license` VALUES ('1', '1dafjdkajgfkldajflfdaf', null, '-1');
INSERT INTO `t_license` VALUES ('2', null, null, '-1');
INSERT INTO `t_license` VALUES ('3', '1dafjdkajgfkldajflfdaf', null, '-1');

-- ----------------------------
-- Table structure for `t_netconfinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_netconfinfo`;
CREATE TABLE `t_netconfinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `netip` varchar(15) DEFAULT NULL,
  `maskip` varchar(15) DEFAULT NULL,
  `gateip` varchar(15) DEFAULT NULL,
  `dns1ip` varchar(15) DEFAULT NULL,
  `dns2ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_netconfinfo
-- ----------------------------
INSERT INTO `t_netconfinfo` VALUES ('7', '2.2.1.1', '1.1.1.1', '1.1.1.1', '1.1.1.1', '1.1.1.1');

-- ----------------------------
-- Table structure for `t_pot`
-- ----------------------------
DROP TABLE IF EXISTS `t_pot`;
CREATE TABLE `t_pot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) DEFAULT NULL,
  `mac` char(17) DEFAULT '',
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pot
-- ----------------------------
INSERT INTO `t_pot` VALUES ('1', '192.168.1.2', '', '1');
INSERT INTO `t_pot` VALUES ('3', '2.2.2.2', '', '1');
INSERT INTO `t_pot` VALUES ('4', '3.3.3.3', '', '1');
INSERT INTO `t_pot` VALUES ('6', '121.211.121.26', '', '1');

-- ----------------------------
-- Table structure for `t_sample`
-- ----------------------------
DROP TABLE IF EXISTS `t_sample`;
CREATE TABLE `t_sample` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `filesize` bigint(11) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `analysis` varchar(255) DEFAULT NULL,
  `firsttime` datetime DEFAULT NULL,
  `lasttime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sample
-- ----------------------------
INSERT INTO `t_sample` VALUES ('1', '108.txt', 'uploadSample/null..txt', '.txt', '7', 'd92155abbbf1a81a1d29c197091a4b', '1', '2014-10-09 18:42:00', '2014-10-09 18:42:00', null);
INSERT INTO `t_sample` VALUES ('2', 'botwall功能点.xlsx', 'uploadSample/d31560a1185553e2b5b8d370546f621d.xlsx', '.xlsx', '11182', 'd31560a1185553e2b5b8d370546f621d', '0', '2014-10-09 18:52:42', '2014-10-09 18:52:42', null);
INSERT INTO `t_sample` VALUES ('3', 'dnsradaradmin.sql', 'uploadSample/d261d4d5e2b35a2fad0ad340db48520d.sql', '.sql', '10466', 'd261d4d5e2b35a2fad0ad340db48520d', '0', '2014-10-09 19:01:42', '2014-10-09 20:03:10', null);
INSERT INTO `t_sample` VALUES ('4', '截图.zip', 'uploadSample/2b4565ddd0bdd7a68829e248c6b3699b.zip', '.zip', '342776', '2b4565ddd0bdd7a68829e248c6b3699b', '0', '2014-10-09 19:05:06', '2014-10-09 19:05:06', null);
INSERT INTO `t_sample` VALUES ('5', 'note.txt', 'uploadSample/23fa76876eba1ff8fd51bb869a69034c.txt', '.txt', '369', '23fa76876eba1ff8fd51bb869a69034c', '0', '2014-10-09 19:06:35', '2014-10-09 19:06:35', null);
INSERT INTO `t_sample` VALUES ('6', 'dnsradaradmin.sql', 'uploadSample/d261d4d5e2b35a2fad0ad340db48520d.sql', '.sql', '10466', 'd261d4d5e2b35a2fad0ad340db48520d', '0', '2014-10-09 19:43:11', '2014-10-09 20:03:10', null);
INSERT INTO `t_sample` VALUES ('7', 'note.txt', 'uploadSample/23fa76876eba1ff8fd51bb869a69034c.txt', '.txt', '369', '23fa76876eba1ff8fd51bb869a69034c', '0', '2014-10-09 19:44:08', '2014-10-09 19:44:08', null);
INSERT INTO `t_sample` VALUES ('8', 'botwall功能点.xlsx', 'uploadSample/d31560a1185553e2b5b8d370546f621d.xlsx', '.xlsx', '11182', 'd31560a1185553e2b5b8d370546f621d', '0', '2014-10-09 19:44:27', '2014-10-09 19:44:27', null);
INSERT INTO `t_sample` VALUES ('10', 'botwall_admin4.sql', 'uploadSample/c8d0c5f0cb5cccec00817e04190217e5.sql', '.sql', '5892', 'c8d0c5f0cb5cccec00817e04190217e5', '1', '2014-10-09 22:35:47', '2014-10-09 22:35:47', null);
INSERT INTO `t_sample` VALUES ('11', 'note.txt', 'uploadSample/74de74b7c0ed212b6fc705f77cf90f6a.txt', '.txt', '42', '74de74b7c0ed212b6fc705f77cf90f6a', '0', '2014-10-10 19:52:30', '2014-10-10 19:54:21', null);
INSERT INTO `t_sample` VALUES ('12', 'XiaoduWiFi140730_M_3.0.8.exe', 'uploadSample/3b59b91f03c107fdb337c951c912f87e.exe', '.exe', '13798264', '3b59b91f03c107fdb337c951c912f87e', '0', '2014-10-10 20:03:00', '2014-10-10 20:03:00', null);
INSERT INTO `t_sample` VALUES ('13', 'wangan_oa.sql', 'uploadSample/af1437d553c3d1608eeda978a35ac2be.sql', '.sql', '292580', 'af1437d553c3d1608eeda978a35ac2be', '0', '2014-10-20 13:29:21', '2014-10-20 13:29:21', null);
INSERT INTO `t_sample` VALUES ('14', '周报.txt', 'uploadSample/7a49fa64df0f74cad8eaff6bc542cfd8.txt', '.txt', '703', '7a49fa64df0f74cad8eaff6bc542cfd8', '0', '2014-10-20 15:26:38', '2014-10-20 15:26:38', null);
INSERT INTO `t_sample` VALUES ('15', '党员关爱U盘目录.doc', 'uploadSample/3d62c834a9ee033a9ca76dad0e3c9137.doc', '.doc', '27648', '3d62c834a9ee033a9ca76dad0e3c9137', '0', '2014-10-20 15:29:21', '2014-10-20 15:29:21', null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Username` varchar(32) DEFAULT NULL,
  `Password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'ice', 'ice');

-- ----------------------------
-- Table structure for `t_whitedomain`
-- ----------------------------
DROP TABLE IF EXISTS `t_whitedomain`;
CREATE TABLE `t_whitedomain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_whitedomain
-- ----------------------------
INSERT INTO `t_whitedomain` VALUES ('1', 'ww', 'w');
INSERT INTO `t_whitedomain` VALUES ('2', '2', '1');

-- ----------------------------
-- Table structure for `t_whiteip`
-- ----------------------------
DROP TABLE IF EXISTS `t_whiteip`;
CREATE TABLE `t_whiteip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `ifrunning` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_whiteip
-- ----------------------------
INSERT INTO `t_whiteip` VALUES ('2', '1.1.1.1', '1', '1');
