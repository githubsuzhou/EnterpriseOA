/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : oa

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-06-09 22:13:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for claim_voucher
-- ----------------------------
DROP TABLE IF EXISTS `claim_voucher`;
CREATE TABLE `claim_voucher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(100) DEFAULT NULL,
  `create_sn` char(5) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `next_deal_sn` char(5) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of claim_voucher
-- ----------------------------
INSERT INTO `claim_voucher` VALUES ('1', '请假21', '10001', '2019-06-09 16:52:53', '10002', '402', '已审核');
INSERT INTO `claim_voucher` VALUES ('2', '难受', '10004', '2019-06-09 18:53:15', '10004', '203', '已打款');
INSERT INTO `claim_voucher` VALUES ('3', '啦啦啦', '10004', '2019-06-09 19:45:13', null, '5500', '已打款');

-- ----------------------------
-- Table structure for claim_voucher_item
-- ----------------------------
DROP TABLE IF EXISTS `claim_voucher_item`;
CREATE TABLE `claim_voucher_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `claim_voucher_id` int(11) DEFAULT NULL,
  `item` varchar(20) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of claim_voucher_item
-- ----------------------------
INSERT INTO `claim_voucher_item` VALUES ('1', '1', '住宿', '400', '100元');
INSERT INTO `claim_voucher_item` VALUES ('3', '1', '办公', '2', '300元');
INSERT INTO `claim_voucher_item` VALUES ('4', '2', '住宿', '100', '下次再');
INSERT INTO `claim_voucher_item` VALUES ('5', '2', '交通', '103', '保从');
INSERT INTO `claim_voucher_item` VALUES ('6', '3', '交通', '500', '上的');
INSERT INTO `claim_voucher_item` VALUES ('7', '3', '餐饮', '5000', '的撒');

-- ----------------------------
-- Table structure for deal_record
-- ----------------------------
DROP TABLE IF EXISTS `deal_record`;
CREATE TABLE `deal_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `claim_voucher_id` int(11) DEFAULT NULL,
  `deal_sn` char(5) DEFAULT NULL,
  `deal_time` datetime DEFAULT NULL,
  `deal_way` varchar(20) DEFAULT NULL,
  `deal_result` varchar(20) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deal_record
-- ----------------------------
INSERT INTO `deal_record` VALUES ('1', '2', '10004', '2019-06-09 18:53:22', '提交', '已提交', '无');
INSERT INTO `deal_record` VALUES ('2', '3', '10004', '2019-06-09 19:45:17', '提交', '已提交', '无');
INSERT INTO `deal_record` VALUES ('3', '3', '10003', '2019-06-09 19:47:28', '通过', '待复审', '');
INSERT INTO `deal_record` VALUES ('4', '2', '10003', '2019-06-09 19:47:32', '打回', '打回', '');
INSERT INTO `deal_record` VALUES ('5', '3', '10001', '2019-06-09 19:49:36', '通过', '已审核', '');
INSERT INTO `deal_record` VALUES ('6', '3', '10002', '2019-06-09 19:50:13', '打款', '已打款', '');
INSERT INTO `deal_record` VALUES ('7', '1', '10001', '2019-06-09 21:25:57', '提交', '已提交', '无');
INSERT INTO `deal_record` VALUES ('8', '1', '10001', '2019-06-09 21:26:12', '通过', '已审核', '');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `sn` char(5) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('10001', '总经理办公室', '星星大厦E座1201');
INSERT INTO `department` VALUES ('10002', '财务部', '星星大厦E座1202');
INSERT INTO `department` VALUES ('10003', '事业部', '星星大厦E座1101');
INSERT INTO `department` VALUES ('1004', '体育部', '上海浦东');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `sn` char(5) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `department_sn` char(5) DEFAULT NULL,
  `post` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('10001', '0000', 'admin', '10001', '总经理');
INSERT INTO `employee` VALUES ('10002', '0000', 'money', '10002', '财务');
INSERT INTO `employee` VALUES ('10003', '0000', '张', '10003', '部门经理');
INSERT INTO `employee` VALUES ('10004', '0000', '小赵同学', '1004', '员工');
