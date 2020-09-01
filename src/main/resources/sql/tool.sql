/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3307
 Source Schema         : tool

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 01/09/2020 09:15:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tool_account
-- ----------------------------
DROP TABLE IF EXISTS `tool_account`;
CREATE TABLE `tool_account`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '账号ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `open_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号,如手机号等\r\n1.普通账号\r\n2.手机号\r\n3.邮箱\r\n4.其他',
  `category` tinyint(1) NULL DEFAULT NULL COMMENT '账号类别',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` double(1, 0) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_member_id`(`user_id`) USING BTREE COMMENT '普通索引',
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tool_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账号' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_account
-- ----------------------------
INSERT INTO `tool_account` VALUES (1, 1, 'admin', 0, '2020-08-29 08:14:15', 'ZHL', '2020-08-29 08:14:24', 'ZHL', 0);
INSERT INTO `tool_account` VALUES (2, 1, '18257116876', 1, '2020-08-29 08:17:18', 'ZHL', '2020-08-29 08:17:24', 'ZHL', 0);
INSERT INTO `tool_account` VALUES (3, 2, '979736189@qq.com', 2, '2020-08-30 08:23:01', 'ZHL', '2020-08-30 08:23:09', 'ZHL', 0);

-- ----------------------------
-- Table structure for tool_permission
-- ----------------------------
DROP TABLE IF EXISTS `tool_permission`;
CREATE TABLE `tool_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '所属父级权限ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限唯一CODE代码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限介绍',
  `category` tinyint(1) NULL DEFAULT NULL COMMENT '权限类别',
  `uri` bigint NULL DEFAULT NULL COMMENT 'URL规则',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE COMMENT '父级权限ID',
  INDEX `code`(`code`) USING BTREE COMMENT '权限CODE代码'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_permission
-- ----------------------------
INSERT INTO `tool_permission` VALUES (1, NULL, 'root', '用户管理', 'tool/user', 1, NULL, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for tool_role
-- ----------------------------
DROP TABLE IF EXISTS `tool_role`;
CREATE TABLE `tool_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '所属父级角色ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色唯一CODE代码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色介绍',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE COMMENT '父级权限ID',
  INDEX `code`(`code`) USING BTREE COMMENT '权限CODE代码'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_role
-- ----------------------------
INSERT INTO `tool_role` VALUES (1, 0, NULL, '超级管理员', '最高权限', NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_role` VALUES (2, 1, NULL, '系统管理员', '维护', NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for tool_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tool_role_permission`;
CREATE TABLE `tool_role_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint NULL DEFAULT NULL COMMENT '权限ID',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE COMMENT '角色ID',
  INDEX `permission_id`(`permission_id`) USING BTREE COMMENT '权限ID',
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tool_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `tool_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_role_permission
-- ----------------------------
INSERT INTO `tool_role_permission` VALUES (1, 1, 1, '2020-08-30 07:50:02', '', NULL, NULL, 0);
INSERT INTO `tool_role_permission` VALUES (2, 2, 1, '2020-08-30 07:50:05', NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for tool_user
-- ----------------------------
DROP TABLE IF EXISTS `tool_user`;
CREATE TABLE `tool_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '用户状态:0=正常,1=禁用',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `head_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像图片地址',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加盐',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_user
-- ----------------------------
INSERT INTO `tool_user` VALUES (1, 0, 'ZHL', NULL, '18257116876', '1', '123', '2020-08-29 08:12:30', 'ZHL', '2020-08-29 08:12:41', 'ZHL', 0);
INSERT INTO `tool_user` VALUES (2, 0, 'GG', NULL, '18257116876', '1', '123', '2020-08-30 08:22:27', 'ZHL', '2020-08-30 08:22:32', 'ZHL', 0);

-- ----------------------------
-- Table structure for tool_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tool_user_role`;
CREATE TABLE `tool_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `member_id`(`user_id`) USING BTREE COMMENT '用户ID',
  INDEX `role_id`(`role_id`) USING BTREE COMMENT '角色ID',
  CONSTRAINT `tool_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tool_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tool_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `tool_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
