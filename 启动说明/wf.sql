/*
Navicat MySQL Data Transfer

Source Server         : wf
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : wf

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-25 10:11:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', 'JFinal Demo Title here', 'JFinal Demo Content here');
INSERT INTO `blog` VALUES ('2', 'test 1', 'test 1');
INSERT INTO `blog` VALUES ('3', 'test 2', 'test 2');
INSERT INTO `blog` VALUES ('4', 'test 3', 'test 3');
INSERT INTO `blog` VALUES ('5', 'test 4', 'test 4');

-- ----------------------------
-- Table structure for `wf_brole`
-- ----------------------------
DROP TABLE IF EXISTS `wf_brole`;
CREATE TABLE `wf_brole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_brole
-- ----------------------------

-- ----------------------------
-- Table structure for `wf_department`
-- ----------------------------
DROP TABLE IF EXISTS `wf_department`;
CREATE TABLE `wf_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_department
-- ----------------------------

-- ----------------------------
-- Table structure for `wf_menu`
-- ----------------------------
DROP TABLE IF EXISTS `wf_menu`;
CREATE TABLE `wf_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parentid` int(10) DEFAULT NULL,
  `orderid` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_menu
-- ----------------------------
INSERT INTO `wf_menu` VALUES ('1', 'home', '0', '1');
INSERT INTO `wf_menu` VALUES ('2', 'manager', '0', '1');
INSERT INTO `wf_menu` VALUES ('3', 'fault', '0', '1');
INSERT INTO `wf_menu` VALUES ('4', 'role', '2', '3');
INSERT INTO `wf_menu` VALUES ('5', 'position', '2', '2');
INSERT INTO `wf_menu` VALUES ('6', 'employee', '2', '4');
INSERT INTO `wf_menu` VALUES ('7', 'department', '2', '1');
INSERT INTO `wf_menu` VALUES ('8', 'brole', '2', '5');
INSERT INTO `wf_menu` VALUES ('9', 'home_index', '1', '1');
INSERT INTO `wf_menu` VALUES ('10', 'manage', '3', '1');

-- ----------------------------
-- Table structure for `wf_permission`
-- ----------------------------
DROP TABLE IF EXISTS `wf_permission`;
CREATE TABLE `wf_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `category` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_permission
-- ----------------------------
INSERT INTO `wf_permission` VALUES ('1', 'a.uer.edit', '所有权限', '1', '所有', '全部', '/**');
INSERT INTO `wf_permission` VALUES ('2', 'login', '登录页', '1', '登录功能', '登录', '/auth/**');
INSERT INTO `wf_permission` VALUES ('3', 'home', '登录后框架页', '1', '用户框架页', '框架页', '/index/**');
INSERT INTO `wf_permission` VALUES ('4', 'permission', '权限管理', '1', '权限管理', '所有', '/permission/**');
INSERT INTO `wf_permission` VALUES ('5', 'user', '用户管理', '1', '人员管理', '所有', '/user/**');
INSERT INTO `wf_permission` VALUES ('6', 'role', '角色管理', '1', '角色管理', '所有', '/role/**');
INSERT INTO `wf_permission` VALUES ('7', 'department', '部门管理', '1', '部门管理', '所有', '/department/**');
INSERT INTO `wf_permission` VALUES ('8', 'position', '岗位管理', '1', '岗位管理', '所有', '/position/**');
INSERT INTO `wf_permission` VALUES ('9', 'permission_add', '权限管理-新增', '1', '权限管理', '新增权限', '/permission/add');

-- ----------------------------
-- Table structure for `wf_position`
-- ----------------------------
DROP TABLE IF EXISTS `wf_position`;
CREATE TABLE `wf_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_position
-- ----------------------------
INSERT INTO `wf_position` VALUES ('1', '值长', '值长');
INSERT INTO `wf_position` VALUES ('2', '副值长', '副值长');
INSERT INTO `wf_position` VALUES ('3', '集控主值', '集控主值');
INSERT INTO `wf_position` VALUES ('4', '集控副值', '集控副值');
INSERT INTO `wf_position` VALUES ('5', '巡操员', '巡操员');
INSERT INTO `wf_position` VALUES ('6', '学员', '学员');
INSERT INTO `wf_position` VALUES ('7', '机务主管', '机务主管');
INSERT INTO `wf_position` VALUES ('8', '机务班长', '机务班长');
INSERT INTO `wf_position` VALUES ('9', '机务主责', '机务主责');
INSERT INTO `wf_position` VALUES ('10', '机务技术员', '机务技术员');
INSERT INTO `wf_position` VALUES ('11', '热控主管', '热控主管');
INSERT INTO `wf_position` VALUES ('12', '热控班长', '热控班长');
INSERT INTO `wf_position` VALUES ('13', '热控主责', '热控主责');
INSERT INTO `wf_position` VALUES ('14', '热控技术员', '热控技术员');
INSERT INTO `wf_position` VALUES ('15', '电气主管', '电气主管');
INSERT INTO `wf_position` VALUES ('16', '电气班长', '电气班长');
INSERT INTO `wf_position` VALUES ('17', '电气主责', '电气主责');
INSERT INTO `wf_position` VALUES ('18', '电气技术员', '电气技术员');

-- ----------------------------
-- Table structure for `wf_role`
-- ----------------------------
DROP TABLE IF EXISTS `wf_role`;
CREATE TABLE `wf_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_role
-- ----------------------------
INSERT INTO `wf_role` VALUES ('1', '操作员', null, '1', '2017-12-17 19:27:40', null, null);

-- ----------------------------
-- Table structure for `wf_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `wf_role_menu`;
CREATE TABLE `wf_role_menu` (
  `id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  `menu_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_role_menu
-- ----------------------------
INSERT INTO `wf_role_menu` VALUES ('1', '1', '1');
INSERT INTO `wf_role_menu` VALUES ('2', '1', '2');
INSERT INTO `wf_role_menu` VALUES ('3', '1', '3');
INSERT INTO `wf_role_menu` VALUES ('4', '1', '4');
INSERT INTO `wf_role_menu` VALUES ('5', '1', '5');
INSERT INTO `wf_role_menu` VALUES ('6', '1', '6');
INSERT INTO `wf_role_menu` VALUES ('7', '1', '7');
INSERT INTO `wf_role_menu` VALUES ('8', '1', '8');
INSERT INTO `wf_role_menu` VALUES ('9', '1', '9');
INSERT INTO `wf_role_menu` VALUES ('10', '1', '10');

-- ----------------------------
-- Table structure for `wf_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `wf_role_permission`;
CREATE TABLE `wf_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_role_permission
-- ----------------------------
INSERT INTO `wf_role_permission` VALUES ('1', '1', '3');

-- ----------------------------
-- Table structure for `wf_user`
-- ----------------------------
DROP TABLE IF EXISTS `wf_user`;
CREATE TABLE `wf_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `worknum` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `departmentId` char(1) DEFAULT NULL,
  `positionId` varchar(10) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_user
-- ----------------------------
INSERT INTO `wf_user` VALUES ('1', 'admin', '111', null, null, null, null, null, null, null, null, '1', null, null, null);

-- ----------------------------
-- Table structure for `wf_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `wf_user_role`;
CREATE TABLE `wf_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_user_role
-- ----------------------------
INSERT INTO `wf_user_role` VALUES ('1', '1', '1');
