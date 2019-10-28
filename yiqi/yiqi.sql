/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : yiqi

Target Server Type    : MYSQL
Target Server Version : 50720   恢复
File Encoding         : 65001

Date: 2019-10-12 09:21:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(4) NOT NULL,
  `brand` varchar(20) DEFAULT NULL COMMENT '品牌',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(4) NOT NULL,
  `brandName` varchar(20) DEFAULT NULL,
  `productName` varchar(20) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL COMMENT '商品详情',
  `productImage` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
