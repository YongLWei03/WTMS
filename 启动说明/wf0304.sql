/*
Navicat MySQL Data Transfer

Source Server         : wf
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : wf

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-04 17:38:59
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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业务角色表ID',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `fstateIds` varchar(100) DEFAULT NULL COMMENT '缺陷状态，逗号分隔ID',
  `workTicketStatesIds` varchar(100) DEFAULT NULL COMMENT '工作票状态，逗号分隔的ID',
  `operateTicketStatesIds` varchar(100) DEFAULT NULL COMMENT '操作票状态，逗号分隔的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_brole
-- ----------------------------
INSERT INTO `wf_brole` VALUES ('1', '值长', null, null, null);
INSERT INTO `wf_brole` VALUES ('2', '操作人', null, null, null);
INSERT INTO `wf_brole` VALUES ('3', '监护人', null, null, null);
INSERT INTO `wf_brole` VALUES ('4', '值班负责人', null, null, null);
INSERT INTO `wf_brole` VALUES ('5', '工作票签收人', null, null, null);
INSERT INTO `wf_brole` VALUES ('6', '工作票许可人', null, null, null);
INSERT INTO `wf_brole` VALUES ('7', '签发人', null, null, null);
INSERT INTO `wf_brole` VALUES ('8', '工作负责人', null, null, null);
INSERT INTO `wf_brole` VALUES ('9', '工作班成员', null, null, null);
INSERT INTO `wf_brole` VALUES ('10', '工作联系人', null, null, null);

-- ----------------------------
-- Table structure for `wf_department`
-- ----------------------------
DROP TABLE IF EXISTS `wf_department`;
CREATE TABLE `wf_department` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '部门表ID',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `type` varchar(200) NOT NULL COMMENT '类型，如部门，专业，值别，班组',
  `parentId` int(10) NOT NULL COMMENT '父ID',
  `hasChildren` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有子节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_department
-- ----------------------------
INSERT INTO `wf_department` VALUES ('1', '运行部', '运行部', '0', '\0');
INSERT INTO `wf_department` VALUES ('2', '检修部', '检修部', '0', '\0');
INSERT INTO `wf_department` VALUES ('3', '燃机一值', '燃机一值', '1', '\0');
INSERT INTO `wf_department` VALUES ('4', '燃机二值', '燃机二值', '1', '\0');
INSERT INTO `wf_department` VALUES ('5', '燃机三值', '燃机三值', '1', '\0');
INSERT INTO `wf_department` VALUES ('6', '燃机四值', '燃机四值', '1', '\0');
INSERT INTO `wf_department` VALUES ('7', '燃机五值', '燃机五值', '1', '\0');
INSERT INTO `wf_department` VALUES ('8', '检修部电气专业', '检修部电气专业', '2', '\0');

-- ----------------------------
-- Table structure for `wf_equipments`
-- ----------------------------
DROP TABLE IF EXISTS `wf_equipments`;
CREATE TABLE `wf_equipments` (
  `id` int(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `parentid` int(5) DEFAULT NULL,
  `hasChildren` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_equipments
-- ----------------------------

-- ----------------------------
-- Table structure for `wf_fault`
-- ----------------------------
DROP TABLE IF EXISTS `wf_fault`;
CREATE TABLE `wf_fault` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fnumber` varchar(50) NOT NULL COMMENT '缺陷单编号',
  `fstate` int(2) NOT NULL COMMENT '缺陷单状态',
  `flevelId` int(2) NOT NULL COMMENT '缺陷级别ID',
  `userId` int(10) NOT NULL COMMENT '缺陷发现人ID',
  `groupIds` varchar(50) NOT NULL COMMENT '运行值别，ID逗号分隔',
  `teamIds` varchar(50) NOT NULL COMMENT '消缺班组，ID逗号分隔',
  `phone` varchar(20) DEFAULT NULL COMMENT '缺陷发现人的联系电话',
  `desc` varchar(200) DEFAULT NULL COMMENT '缺陷描述',
  `comments` varchar(200) DEFAULT NULL COMMENT '备注',
  `tjDate` datetime NOT NULL COMMENT '提交时间',
  `yqjsDate` datetime NOT NULL COMMENT '要求结束时间',
  `createTime` datetime DEFAULT NULL,
  `kksId` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_fault
-- ----------------------------

-- ----------------------------
-- Table structure for `wf_flevel`
-- ----------------------------
DROP TABLE IF EXISTS `wf_flevel`;
CREATE TABLE `wf_flevel` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '缺陷级别ID',
  `name` varchar(50) NOT NULL COMMENT '缺陷级别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_flevel
-- ----------------------------
INSERT INTO `wf_flevel` VALUES ('1', '一类缺陷');
INSERT INTO `wf_flevel` VALUES ('2', '二类缺陷');
INSERT INTO `wf_flevel` VALUES ('3', '三类缺陷');
INSERT INTO `wf_flevel` VALUES ('4', '其它缺陷');

-- ----------------------------
-- Table structure for `wf_fstate`
-- ----------------------------
DROP TABLE IF EXISTS `wf_fstate`;
CREATE TABLE `wf_fstate` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '缺陷状态表ID',
  `name` varchar(100) DEFAULT NULL COMMENT '缺陷状态名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_fstate
-- ----------------------------
INSERT INTO `wf_fstate` VALUES ('1', '已保存');
INSERT INTO `wf_fstate` VALUES ('2', '已提交');
INSERT INTO `wf_fstate` VALUES ('3', '已审核');

-- ----------------------------
-- Table structure for `wf_kks`
-- ----------------------------
DROP TABLE IF EXISTS `wf_kks`;
CREATE TABLE `wf_kks` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'KKS表ID',
  `name` varchar(100) NOT NULL COMMENT 'KKS名称',
  `desc` varchar(200) NOT NULL DEFAULT '北京电厂' COMMENT 'KKS码描述',
  `sid` varchar(50) NOT NULL DEFAULT 'HNJT' COMMENT '结构标识',
  `parentId` int(10) NOT NULL COMMENT '父节点ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_kks
-- ----------------------------
INSERT INTO `wf_kks` VALUES ('1', 'W025', '北京电厂', 'HNJT', '0');
INSERT INTO `wf_kks` VALUES ('2', 'W025 6', '#6机组', 'HNJT', '1');
INSERT INTO `wf_kks` VALUES ('3', 'W025 4 0QEB10 AA202', '#4炉尿素喷枪无油空压机来雾化空气逆止阀', 'HNJT', '2');
INSERT INTO `wf_kks` VALUES ('4', 'W025 6 0AEA01', '#6主变压器6B高压侧开关间隔', 'HNJT', '2');
INSERT INTO `wf_kks` VALUES ('5', 'W025 5', '#5机组', 'HNJT', '1');
INSERT INTO `wf_kks` VALUES ('6', 'W025 4', '#4机组', 'HNJT', '1');

-- ----------------------------
-- Table structure for `wf_menu`
-- ----------------------------
DROP TABLE IF EXISTS `wf_menu`;
CREATE TABLE `wf_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单表ID',
  `name` varchar(50) NOT NULL COMMENT '菜单名称英文标识，前端路由name值',
  `parentid` int(10) NOT NULL COMMENT '父级菜单ID，没有父级置0',
  `orderid` int(5) NOT NULL COMMENT '菜单排序字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_menu
-- ----------------------------
INSERT INTO `wf_menu` VALUES ('1', 'home', '0', '1');
INSERT INTO `wf_menu` VALUES ('2', 'manager', '0', '1');
INSERT INTO `wf_menu` VALUES ('3', 'fault', '0', '1');
INSERT INTO `wf_menu` VALUES ('4', 'state', '0', '1');
INSERT INTO `wf_menu` VALUES ('5', 'operateManage', '0', '1');
INSERT INTO `wf_menu` VALUES ('6', 'home_index', '1', '1');
INSERT INTO `wf_menu` VALUES ('7', 'department', '2', '1');
INSERT INTO `wf_menu` VALUES ('8', 'brole', '2', '2');
INSERT INTO `wf_menu` VALUES ('9', 'role', '2', '3');
INSERT INTO `wf_menu` VALUES ('10', 'dep', '2', '4');
INSERT INTO `wf_menu` VALUES ('11', 'kks', '2', '5');
INSERT INTO `wf_menu` VALUES ('12', 'equipment', '2', '6');
INSERT INTO `wf_menu` VALUES ('13', 'employee', '2', '7');
INSERT INTO `wf_menu` VALUES ('14', 'fstate', '4', '1');
INSERT INTO `wf_menu` VALUES ('15', 'workTicketState', '4', '2');
INSERT INTO `wf_menu` VALUES ('16', 'operateTicketState', '4', '3');
INSERT INTO `wf_menu` VALUES ('17', 'manage', '3', '1');
INSERT INTO `wf_menu` VALUES ('18', 'operateTicket', '5', '1');

-- ----------------------------
-- Table structure for `wf_operatecontent`
-- ----------------------------
DROP TABLE IF EXISTS `wf_operatecontent`;
CREATE TABLE `wf_operatecontent` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `operateTicketId` varchar(20) NOT NULL,
  `optitemid` int(5) DEFAULT NULL,
  `optItemContent` varchar(500) DEFAULT NULL,
  `dangerPointIds` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_operatecontent
-- ----------------------------

-- ----------------------------
-- Table structure for `wf_operateticketstate`
-- ----------------------------
DROP TABLE IF EXISTS `wf_operateticketstate`;
CREATE TABLE `wf_operateticketstate` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '缺陷状态表ID',
  `name` varchar(100) DEFAULT NULL COMMENT '缺陷状态名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_operateticketstate
-- ----------------------------

-- ----------------------------
-- Table structure for `wf_permission`
-- ----------------------------
DROP TABLE IF EXISTS `wf_permission`;
CREATE TABLE `wf_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表ID',
  `permission` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL COMMENT '权限中文描述',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，是否启用该权限',
  `category` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `url` varchar(50) NOT NULL COMMENT '权限标识',
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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位表ID',
  `name` varchar(50) NOT NULL COMMENT '岗位名称',
  `description` varchar(200) NOT NULL COMMENT '岗位描述',
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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表ID',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_role
-- ----------------------------
INSERT INTO `wf_role` VALUES ('1', '值长', null, '1', '2017-12-28 14:01:02', null, null);
INSERT INTO `wf_role` VALUES ('2', '操作人', null, '1', '2017-12-28 14:01:03', null, null);
INSERT INTO `wf_role` VALUES ('3', '监护人', null, '1', '2017-12-28 14:01:03', null, null);
INSERT INTO `wf_role` VALUES ('4', '值班负责人', null, '1', '2017-12-28 14:01:04', null, null);
INSERT INTO `wf_role` VALUES ('5', '工作票签收人', null, '1', '2017-12-28 14:01:05', null, null);
INSERT INTO `wf_role` VALUES ('6', '工作票许可人', null, '1', '2017-12-28 14:01:05', null, null);
INSERT INTO `wf_role` VALUES ('7', '签发人', null, '1', '2017-12-28 14:01:06', null, null);
INSERT INTO `wf_role` VALUES ('8', '工作负责人', null, '1', '2017-12-28 14:01:06', null, null);
INSERT INTO `wf_role` VALUES ('9', '工作班成员', null, '1', '2017-12-28 14:01:07', null, null);
INSERT INTO `wf_role` VALUES ('10', '工作联系人', null, '1', '2017-12-28 14:02:42', null, null);

-- ----------------------------
-- Table structure for `wf_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `wf_role_menu`;
CREATE TABLE `wf_role_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) NOT NULL,
  `menu_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表ID',
  `username` varchar(50) NOT NULL COMMENT '登录用户名',
  `pwd` varchar(50) NOT NULL COMMENT '登录密码',
  `worknum` varchar(50) NOT NULL COMMENT '工号',
  `email` varchar(100) DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `avatar` varchar(200) DEFAULT '' COMMENT '用户头像',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `sex` char(1) NOT NULL COMMENT '性别',
  `departmentIds` varchar(50) NOT NULL COMMENT '部门多个ID逗号连接',
  `broleIds` varchar(50) NOT NULL COMMENT '业务角色多个ID逗号连接',
  `roleIds` varchar(50) NOT NULL COMMENT '系统角色多个ID逗号连接',
  `status` int(11) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_user
-- ----------------------------
INSERT INTO `wf_user` VALUES ('1', 'admin', '111', '111', null, '15010801461', null, 'admin', '1', '1', '', '1', '1', '2017-12-27 10:20:40', '2017-12-27 10:20:43', '2017-12-27 10:20:47');
INSERT INTO `wf_user` VALUES ('2', '李白', '111', '9527', '', null, '', '李白', '1', '1', '', '2', '1', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `wf_user` VALUES ('3', '杜甫', '1', '87', '', null, '', '杜甫', '1', '1', '', '4', '1', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `wf_user` VALUES ('4', '白居易', '111', '888', '', null, '', '白居易', '1', '1', '', '6', '1', '2017-12-28 18:42:31', '2017-12-28 18:42:31', '2017-12-28 18:42:31');

-- ----------------------------
-- Table structure for `wf_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `wf_user_role`;
CREATE TABLE `wf_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_user_role
-- ----------------------------
INSERT INTO `wf_user_role` VALUES ('1', '1', '1');
INSERT INTO `wf_user_role` VALUES ('2', '2', '2');
INSERT INTO `wf_user_role` VALUES ('3', '2', '3');
INSERT INTO `wf_user_role` VALUES ('4', '2', '4');
INSERT INTO `wf_user_role` VALUES ('5', '2', '5');
INSERT INTO `wf_user_role` VALUES ('6', '3', '5');
INSERT INTO `wf_user_role` VALUES ('7', '3', '4');
INSERT INTO `wf_user_role` VALUES ('8', '3', '3');
INSERT INTO `wf_user_role` VALUES ('13', '4', '3');
INSERT INTO `wf_user_role` VALUES ('14', '4', '4');
INSERT INTO `wf_user_role` VALUES ('15', '4', '5');

-- ----------------------------
-- Table structure for `wf_workticketstate`
-- ----------------------------
DROP TABLE IF EXISTS `wf_workticketstate`;
CREATE TABLE `wf_workticketstate` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '缺陷状态表ID',
  `name` varchar(100) DEFAULT NULL COMMENT '缺陷状态名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_workticketstate
-- ----------------------------
