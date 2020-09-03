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

 Date: 03/09/2020 17:25:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tool_account
-- ----------------------------
DROP TABLE IF EXISTS `tool_account`;
CREATE TABLE `tool_account`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '账号ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `open_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `category` int NULL DEFAULT NULL COMMENT '账号类别\r\n0 普通账号\r\n1 手机号\r\n2 邮箱',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`, `open_code`) USING BTREE,
  INDEX `idx_member_id`(`user_id`) USING BTREE COMMENT '普通索引'
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账号' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_account
-- ----------------------------
INSERT INTO `tool_account` VALUES (1, 1, 'admin', 0, '2020-08-29 08:14:15', 'ZHL', '2020-09-02 09:24:36', '注册测试', 0);
INSERT INTO `tool_account` VALUES (2, 1, '18257116876', 1, '2020-08-29 08:17:18', 'ZHL', '2020-09-02 09:24:36', '注册测试', 0);
INSERT INTO `tool_account` VALUES (3, 2, '979736189@qq.com', 2, '2020-08-30 08:23:01', 'ZHL', '2020-09-03 05:37:39', '注册测试', 0);
INSERT INTO `tool_account` VALUES (32, 3, 'grey', 0, '2020-09-02 09:18:28', '测试管理员', '2020-09-03 05:08:00', '测试管理员', 0);
INSERT INTO `tool_account` VALUES (33, 4, 'testADD', 0, '2020-09-03 02:16:18', NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for tool_permission
-- ----------------------------
DROP TABLE IF EXISTS `tool_permission`;
CREATE TABLE `tool_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '所属父级权限ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限唯一CODE代码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限介绍',
  `category` tinyint(1) NULL DEFAULT NULL COMMENT '权限类别\r\n0 ：根目录\r\n1 ： 一级目录\r\n2： 二级目录\r\n3： 按钮',
  `uri` bigint NULL DEFAULT 0 COMMENT 'URL规则 \r\n1：GET\r\n2:  POST\r\n3:  PUT\r\n4:  DELETE\r\n0：未定义\r\n',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE COMMENT '父级权限ID',
  INDEX `code`(`code`) USING BTREE COMMENT '权限CODE代码'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_permission
-- ----------------------------
INSERT INTO `tool_permission` VALUES (1, 0, NULL, '系统设置', NULL, 0, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_permission` VALUES (2, 1, NULL, '权限设置', NULL, 1, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_permission` VALUES (3, 2, NULL, '用户管理', '/tool/user', 2, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_permission` VALUES (4, 2, NULL, '角色管理', NULL, 2, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_permission` VALUES (5, 3, '', '增加用户', '/tool/user/add', 3, 2, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_permission` VALUES (6, 3, '', '删除用户', '/tool/user/delete', 3, 4, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_permission` VALUES (7, 3, NULL, '修改用户', '/tool/user/update', 3, 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_permission` VALUES (8, 3, NULL, '恢复用户', '/tool/user/recover', 3, 3, NULL, NULL, NULL, NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_role
-- ----------------------------
INSERT INTO `tool_role` VALUES (1, 0, NULL, '超级管理员', '最高权限', NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_role` VALUES (2, 1, NULL, '系统管理员', '维护', NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_role` VALUES (3, 0, NULL, 'CEO', '执行人', NULL, NULL, NULL, NULL, 0);

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
  INDEX `permission_id`(`permission_id`) USING BTREE COMMENT '权限ID'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_role_permission
-- ----------------------------
INSERT INTO `tool_role_permission` VALUES (1, 1, 1, '2020-08-30 07:50:02', '', NULL, NULL, 0);
INSERT INTO `tool_role_permission` VALUES (2, 1, 2, '2020-08-30 07:50:05', NULL, NULL, NULL, 0);
INSERT INTO `tool_role_permission` VALUES (3, 1, 3, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_role_permission` VALUES (4, 1, 4, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_role_permission` VALUES (5, 1, 5, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_role_permission` VALUES (6, 1, 6, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_role_permission` VALUES (7, 1, 7, NULL, NULL, NULL, NULL, 0);
INSERT INTO `tool_role_permission` VALUES (8, 1, 8, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for tool_user
-- ----------------------------
DROP TABLE IF EXISTS `tool_user`;
CREATE TABLE `tool_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `state` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '用户状态:0=正常,1=禁用',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `head_img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像图片地址',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加盐',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `edited` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除:0=未删除,1=已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_user
-- ----------------------------
INSERT INTO `tool_user` VALUES (1, 0, '测试管理员', NULL, '18257116876', '94adf0180cd98862bcc98dc5c0b143a1', '31e655584af0192e5518ea3fd52d3c03', '2020-08-29 08:12:30', 'ZHL', '2020-09-02 09:24:36', '注册测试', 0);
INSERT INTO `tool_user` VALUES (2, 0, 'GOD', NULL, '18888888888', '94adf0180cd98862bcc98dc5c0b143a1', '31e655584af0192e5518ea3fd52d3c03', '2020-08-30 08:22:27', 'ZHL', '2020-09-03 05:37:39', '注册测试', 0);
INSERT INTO `tool_user` VALUES (3, 0, '注册测试', NULL, NULL, '78f53ccac8b53333d6b8903859b13ab7', 'b656cea5cff22b48e96570ab9f5a2100', '2020-09-02 09:18:28', '测试管理员', '2020-09-03 05:08:00', '测试管理员', 0);
INSERT INTO `tool_user` VALUES (4, 0, '测试注册ADD', NULL, NULL, '9a85b0771d8f7d57acd4ee2251ebfd50', 'bc3d905615c115ed0baf66bead776d4d', '2020-09-03 02:16:18', NULL, NULL, NULL, 0);

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
  INDEX `role_id`(`role_id`) USING BTREE COMMENT '角色ID'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tool_user_role
-- ----------------------------
INSERT INTO `tool_user_role` VALUES (1, 1, 1, '2020-09-02 09:45:27', NULL, '2020-09-02 09:45:31', NULL, 0);
INSERT INTO `tool_user_role` VALUES (2, 3, 2, '2020-09-02 09:45:52', NULL, '2020-09-02 09:45:55', NULL, 0);
INSERT INTO `tool_user_role` VALUES (3, 3, 1, '2020-09-02 09:46:05', NULL, '2020-09-02 09:46:09', NULL, 0);
INSERT INTO `tool_user_role` VALUES (4, 3, 3, '2020-09-02 09:46:51', NULL, '2020-09-02 09:46:53', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
