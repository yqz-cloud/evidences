/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 127.0.0.1:3306
 Source Schema         : evidence

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 11/02/2019 17:13:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for region_amap
-- ----------------------------
DROP TABLE IF EXISTS `region_amap`;
CREATE TABLE `region_amap`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `citycode` int(11) NOT NULL,
  `center` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `level` int(11) NOT NULL,
  `merger_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `pinyin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `short_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `zip_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3729 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for region_order
-- ----------------------------
DROP TABLE IF EXISTS `region_order`;
CREATE TABLE `region_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `citycode` int(11) NOT NULL,
  `center` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `level` int(11) NOT NULL,
  `merger_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `pinyin` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `short_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `zip_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3729 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;


