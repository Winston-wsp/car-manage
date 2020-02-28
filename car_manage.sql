/*
 Navicat Premium Data Transfer

 Source Server         : connection
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : car_manage

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 28/02/2020 15:09:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_card
-- ----------------------------
DROP TABLE IF EXISTS `car_card`;
CREATE TABLE `car_card`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `card_number` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡号',
  `user_id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `card_type` int(1) NULL DEFAULT NULL COMMENT '(开卡的类型，0为临时用户 1为月租用户 2为包年用户)',
  `start_time` datetime(0) NOT NULL COMMENT '激活日期',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '有效期到什么时候只针对1、2卡类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_card
-- ----------------------------
INSERT INTO `car_card` VALUES ('392176652430e39f3d09acc8019f3c05', 'ZZ1221715216', '4d3e6c9cbec284d6c5bc1311923e68f0', 1, '2020-02-20 20:24:19', '2022-11-20 20:24:19');
INSERT INTO `car_card` VALUES ('953f9160eff8e0a7818eb8f80d3076b3', 'ZZ6766264098', '0da744141990b3808eebd9f03a3098f9', 1, '2020-02-20 22:44:22', '2021-03-20 22:44:22');

-- ----------------------------
-- Table structure for car_carparks
-- ----------------------------
DROP TABLE IF EXISTS `car_carparks`;
CREATE TABLE `car_carparks`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域号',
  `park_number` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车位号',
  `remark` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '(0表示不能用，1表示能用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_carparks
-- ----------------------------
INSERT INTO `car_carparks` VALUES ('0183d7ae3aeab2c1e1c36cb8c0f59f54', 'C区', 'C03', '', '1');
INSERT INTO `car_carparks` VALUES ('1246f8d2184ed1fd11337791583377a4', 'A区', 'A04', '', '0');
INSERT INTO `car_carparks` VALUES ('2782c8418cee75eafdc93daa9bd32379', 'A区', 'A01', '私人车位', '1');
INSERT INTO `car_carparks` VALUES ('305ad15c929a31309f93f9daa4f566e9', 'A区', 'A03', '临时车位', '0');
INSERT INTO `car_carparks` VALUES ('3596c5a9af93e919510e5e35027ad6f1', 'A区', 'A02', '临时车位', '1');
INSERT INTO `car_carparks` VALUES ('468475eba8c5ea4c8352584420fba1e2', 'B区', 'B06', '', '1');
INSERT INTO `car_carparks` VALUES ('4aa63256b64c6046a3a72521b335e356', 'B区', 'B05', '可用车位', '1');
INSERT INTO `car_carparks` VALUES ('4e8c70c5222a0683f3d03793cc4d3e1a', 'B区', 'B01', '', '1');
INSERT INTO `car_carparks` VALUES ('574f400819a7e0e92d9656470f264766', 'C区', 'C02', '', '1');
INSERT INTO `car_carparks` VALUES ('5d983907ecebda9d58ef925b8b92c31d', 'B区', 'B02', '', '1');
INSERT INTO `car_carparks` VALUES ('5fd5e79efbf765fee2a871d6f8bd8fb3', 'B区', 'B04', '', '1');
INSERT INTO `car_carparks` VALUES ('724355ad4838fd3b7bbce7980403e559', 'A区', 'A00', '临时车位', '1');
INSERT INTO `car_carparks` VALUES ('95e83131f95f4940b7e045e6a2ffe7f0', 'A区', 'A05', '', '0');
INSERT INTO `car_carparks` VALUES ('a7e07bbc52fdb4a19b95b84b39751265', 'C区', 'C01', '可用车位', '1');
INSERT INTO `car_carparks` VALUES ('a8105d58e9905ec2bdc952ea077c8fe5', 'B区', 'B03', '', '1');
INSERT INTO `car_carparks` VALUES ('c52b7db5ec4142924939d905e6d03be7', 'C区', 'C04', '可用车位', '1');
INSERT INTO `car_carparks` VALUES ('cd8f7da66590579291daa3f879054473', 'C区', 'C05', '', '1');
INSERT INTO `car_carparks` VALUES ('e5852c101f8a8a6a877211a044f9b72a', 'A区', 'A06', '临时车位', '0');

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `plate_number` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `user_id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for car_parking
-- ----------------------------
DROP TABLE IF EXISTS `car_parking`;
CREATE TABLE `car_parking`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `carparks_id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车位号id',
  `plate_number` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '入场时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '出场时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_parking
-- ----------------------------
INSERT INTO `car_parking` VALUES ('04168ead8b151ecfe261c6e792e451b6', '748478b433aa3987747602f10fc7ad1a', '305ad15c929a31309f93f9daa4f566e9', '沪AJJ846', '2020-02-28 14:29:39', NULL);
INSERT INTO `car_parking` VALUES ('11b0a85a2b743df54837900f065b9f6d', '0da744141990b3808eebd9f03a3098f9', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-20 19:38:59', '2020-02-20 21:40:04');
INSERT INTO `car_parking` VALUES ('1afad926e3bf45119600d85b5ee61a87', '0da744141990b3808eebd9f03a3098f9', '26e2f9924fd3cd8db88de5ebf8e4e727', '沪AJJ846', '2020-02-20 21:08:57', '2020-02-20 21:09:22');
INSERT INTO `car_parking` VALUES ('1c91b03387b72a73fee2e552e792fa6a', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AAB273', '2020-02-20 20:21:20', '2020-02-20 20:21:41');
INSERT INTO `car_parking` VALUES ('1e3af7972e708288095fb27bd65d3c6e', '4d3e6c9cbec284d6c5bc1311923e68f0', 'e5852c101f8a8a6a877211a044f9b72a', '沪AJJ846', '2020-02-28 14:22:46', NULL);
INSERT INTO `car_parking` VALUES ('20869297cf6137344e75a0e850911475', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-01-22 14:53:02', '2020-02-19 20:49:57');
INSERT INTO `car_parking` VALUES ('2b160fa06c992494ef1cdc993698c2cc', '4d3e6c9cbec284d6c5bc1311923e68f0', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-21 10:31:45', '2020-02-21 10:31:49');
INSERT INTO `car_parking` VALUES ('2e769400871db6f5f0fad28bfa22488d', 'a48075877ea90d8c14dae3833641815c', '1246f8d2184ed1fd11337791583377a4', '京A88888', '2020-02-23 22:07:49', NULL);
INSERT INTO `car_parking` VALUES ('2fca92e3657fda8794e29daccc4b4918', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '闵HJJ32h', '2020-02-23 21:46:00', '2020-02-27 19:33:41');
INSERT INTO `car_parking` VALUES ('36b5d4ffbd63fb9d67fbfd70248c9b43', '6dfef63c1615f8a93a7331accec38cf4', '95e83131f95f4940b7e045e6a2ffe7f0', '沪AJJ846', '2020-02-24 12:01:41', NULL);
INSERT INTO `car_parking` VALUES ('426845e7c75b68453a51d2c9165a4dff', '0da744141990b3808eebd9f03a3098f9', '26e2f9924fd3cd8db88de5ebf8e4e727', '闵HI8832', '2020-02-20 21:08:25', '2020-02-20 21:08:32');
INSERT INTO `car_parking` VALUES ('56f45c439c09d0f8adcc811514ecc337', '4d3e6c9cbec284d6c5bc1311923e68f0', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-19 20:57:16', '2020-02-19 21:00:26');
INSERT INTO `car_parking` VALUES ('63ab0c814794d7f4a4b8961504345430', '748478b433aa3987747602f10fc7ad1a', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-19 21:42:10', '2020-02-19 21:43:11');
INSERT INTO `car_parking` VALUES ('69e5782fc1d63abad990d2b883b5212f', '4d3e6c9cbec284d6c5bc1311923e68f0', '1246f8d2184ed1fd11337791583377a4', '沪AJJ846', '2020-02-21 13:20:23', '2020-02-21 22:09:31');
INSERT INTO `car_parking` VALUES ('73b8fa665d9a3174ee42de95da8bc697', '4d3e6c9cbec284d6c5bc1311923e68f0', '3596c5a9af93e919510e5e35027ad6f1', '沪AJJ846', '2020-02-20 21:46:51', '2020-02-20 22:55:29');
INSERT INTO `car_parking` VALUES ('8a8eb84fb458755827dc64c2b83aadb3', '4d3e6c9cbec284d6c5bc1311923e68f0', '2782c8418cee75eafdc93daa9bd32379', '沪AJJ846', '2020-02-20 19:46:59', '2020-02-20 20:21:37');
INSERT INTO `car_parking` VALUES ('910b81b1f1bed155a857033b302a82e5', '4d3e6c9cbec284d6c5bc1311923e68f0', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-19 21:01:48', '2020-02-19 21:02:09');
INSERT INTO `car_parking` VALUES ('969a0c95e7d758caf21ffb9a9d8d4e13', '4d3e6c9cbec284d6c5bc1311923e68f0', '468475eba8c5ea4c8352584420fba1e2', '沪AJ9999', '2020-02-21 22:13:09', '2020-02-21 22:13:34');
INSERT INTO `car_parking` VALUES ('9c2a6fa1efdf7284d2d0d97557fdbcda', 'a48075877ea90d8c14dae3833641815c', '95e83131f95f4940b7e045e6a2ffe7f0', '京A88888', '2020-02-23 22:12:47', '2020-02-23 23:04:03');
INSERT INTO `car_parking` VALUES ('a7d0ee74e859e705a2709f2bb790e5d9', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-02-19 23:13:45', '2020-02-20 17:27:44');
INSERT INTO `car_parking` VALUES ('c0da291c5a355f57ea5c069c521c6663', 'eaf296e46448a27fa2eba609deece2312', '2782c8418cee75eafdc93daa9bd32379', '浙B7EW82', '2020-02-23 21:47:44', '2020-02-23 22:13:55');
INSERT INTO `car_parking` VALUES ('c247a9e2ff4568fd3102ed83cd18437d', '0da744141990b3808eebd9f03a3098f9', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-02-20 19:47:46', '2020-02-20 21:51:15');
INSERT INTO `car_parking` VALUES ('cc5ab109d367716cc3eb887c5b8e0bd8', '0da744141990b3808eebd9f03a3098f9', '3596c5a9af93e919510e5e35027ad6f1', '黑BDS838', '2020-02-23 21:50:45', '2020-02-23 23:09:57');
INSERT INTO `car_parking` VALUES ('d2d2e368b53b6461a83fd33ec5ee45ee', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-02-20 21:07:34', '2020-02-20 21:36:58');
INSERT INTO `car_parking` VALUES ('e328320b2ba2161af5d246956f087ef5', '4d3e6c9cbec284d6c5bc1311923e68f0', '26e2f9924fd3cd8db88de5ebf8e4e727', '沪AJJ846', '2020-02-19 20:51:53', '2020-02-19 20:51:59');
INSERT INTO `car_parking` VALUES ('ec110f7df270d24c27668222e7a378ca', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-02-20 17:34:49', '2020-02-20 19:21:23');

-- ----------------------------
-- Table structure for car_parking_cost
-- ----------------------------
DROP TABLE IF EXISTS `car_parking_cost`;
CREATE TABLE `car_parking_cost`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `month_cost` int(10) NULL DEFAULT NULL COMMENT '月租费用',
  `year_cost` int(10) NULL DEFAULT NULL COMMENT '年租费用',
  `level_one` int(10) NULL DEFAULT NULL COMMENT '第一梯度费用',
  `level_two` int(10) NULL DEFAULT NULL COMMENT '第二梯度费用',
  `level_three` int(10) NULL DEFAULT NULL COMMENT '第三梯度费用',
  `level_one_time` int(11) NULL DEFAULT NULL COMMENT '第一梯度时长',
  `level_two_time` int(11) NULL DEFAULT NULL COMMENT '第二梯度时长',
  `level_three_time` int(11) NULL DEFAULT NULL COMMENT '第三梯度时长',
  `day_cost` int(10) NULL DEFAULT NULL COMMENT '一天费用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_parking_cost
-- ----------------------------
INSERT INTO `car_parking_cost` VALUES (1, 300, 1800, 2, 5, 8, 1, 3, 8, 10);

-- ----------------------------
-- Table structure for user_admin
-- ----------------------------
DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_admin
-- ----------------------------
INSERT INTO `user_admin` VALUES ('b80f76228e8d8ec8f5ecf93450809ef5', 'admin', 'admin');

-- ----------------------------
-- Table structure for user_announcement
-- ----------------------------
DROP TABLE IF EXISTS `user_announcement`;
CREATE TABLE `user_announcement`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公告内容',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '发布公告的日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_announcement
-- ----------------------------
INSERT INTO `user_announcement` VALUES ('e158dfe1639e3458046e0a97c956e8da', '关于停车问题', '所有使用本系统停车的用户需要注意，不能够将车随意停放。违者将不能使用本系统停车并且需要罚款。希望各位用户注意。', '2020-02-15 05:44:44');
INSERT INTO `user_announcement` VALUES ('eeda4d25657fed4c87e241db5b5ae535', '小区公告', '请大家爱护公物，不要随意破坏小区内的绿化。谢谢大家配合', '2020-02-17 04:39:35');

-- ----------------------------
-- Table structure for user_order_alipay
-- ----------------------------
DROP TABLE IF EXISTS `user_order_alipay`;
CREATE TABLE `user_order_alipay`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `out_trade_no` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户订单号',
  `total_amount` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款金额',
  `subject` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单名称',
  `body` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `creatTime` datetime(0) NULL DEFAULT NULL COMMENT '订单创建日期',
  `status` int(1) NULL DEFAULT 0 COMMENT '订单是否成功（0：失败，1：成功）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order_alipay
-- ----------------------------
INSERT INTO `user_order_alipay` VALUES ('0ea389e73425824caef1b9e679596daa', '4d3e6c9cbec284d6c5bc1311923e68f0', 'DDH5173324357', '100', '个人充值', '余额充值', '2020-02-26 22:50:17', 0);
INSERT INTO `user_order_alipay` VALUES ('152dcba0dfc067815f11b51d0422b158', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1493033957', '3000', '个人充值', '余额充值', '2020-02-28 13:32:15', 1);
INSERT INTO `user_order_alipay` VALUES ('158224108d300c7b3366a905034f70df', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1637550822', '100', '个人充值', '余额充值', '2020-02-27 19:29:23', 1);
INSERT INTO `user_order_alipay` VALUES ('26a0105c4157308df6700a5d6b20e6c9', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1633959799', '2000', '个人充值', '余额充值', '2020-02-27 19:38:12', 0);
INSERT INTO `user_order_alipay` VALUES ('28a22f121f2cab37eb0d841fa8a6dd28', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD5005039555', '40', '个人充值', '余额充值', '2020-02-27 11:04:10', 1);
INSERT INTO `user_order_alipay` VALUES ('299542a52c0baff4db5aab399b51ff13', '748478b433aa3987747602f10fc7ad1a', 'JFDD1848360727', '100', '个人充值', '余额充值', '2020-02-28 14:29:57', 0);
INSERT INTO `user_order_alipay` VALUES ('3d43ef69543975a246190e8e55b070df', '4d3e6c9cbec284d6c5bc1311923e68f0', 'DDH1122023788', '100', '个人充值', '余额充值', '2020-02-26 22:52:51', 0);
INSERT INTO `user_order_alipay` VALUES ('594ce13bb24032dffdaf810fe4cff48c', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1924735382', '50', '个人充值', '余额充值', '2020-02-27 11:07:25', 1);
INSERT INTO `user_order_alipay` VALUES ('77eb856ae13cff229f4b6cd4b1443242', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD4661276306', '70', '个人充值', '余额充值', '2020-02-27 11:15:22', 1);
INSERT INTO `user_order_alipay` VALUES ('77fbfdcfa6ed41c586030849c08ebd3e', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1086797033', '100', '个人充值', '余额充值', '2020-02-28 13:37:55', 1);
INSERT INTO `user_order_alipay` VALUES ('8d8ec8728ec53159e382bef543848654', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1321187205', '80', '个人充值', '余额充值', '2020-02-27 17:34:38', 1);
INSERT INTO `user_order_alipay` VALUES ('a324c479d559fe9904aeb1b8e451d33a', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1791421447', '1', '个人充值', '余额充值', '2020-02-27 11:17:09', 1);
INSERT INTO `user_order_alipay` VALUES ('a829a4b3f6eed3d31337a750e61d6c1d', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD9508374937', '30', '个人充值', '余额充值', '2020-02-27 10:56:09', 0);
INSERT INTO `user_order_alipay` VALUES ('ca426aff3f08f6cfa0fad5baecadb166', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD7555459665', '100', '个人充值', '余额充值', '2020-02-27 17:41:51', 1);
INSERT INTO `user_order_alipay` VALUES ('ef264d04fa207eb3de39f40d2dcb4c1e', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD5431334400', '3000', '个人充值', '余额充值', '2020-02-27 19:38:02', 0);

-- ----------------------------
-- Table structure for user_pay
-- ----------------------------
DROP TABLE IF EXISTS `user_pay`;
CREATE TABLE `user_pay`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `pay` double NULL DEFAULT NULL COMMENT '费用',
  `order_number` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '交易日期',
  `context` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_pay
-- ----------------------------
INSERT INTO `user_pay` VALUES ('072729b05b215960d0871605a3ed8140', '4d3e6c9cbec284d6c5bc1311923e68f0', -1800, 'JFDD6412529840', '2020-02-14 22:55:38', '年卡办理');
INSERT INTO `user_pay` VALUES ('165cdb71129f2c14540d74406b3381e7', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD4479788351', '2020-02-13 20:25:53', '月卡办理');
INSERT INTO `user_pay` VALUES ('1990354b19573ad8ffa46584b674ecdb', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD2084666572', '2020-02-19 20:25:23', '月卡办理');
INSERT INTO `user_pay` VALUES ('1c7a3cc88e4baf336b7d8b911da72264', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD1629399078', '2020-02-26 20:46:08', '账户充值');
INSERT INTO `user_pay` VALUES ('241208c5d0903580078f290331f29a33', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD2026779258', '2020-02-27 19:31:18', '余额充值');
INSERT INTO `user_pay` VALUES ('2f11885b77a494d7607ae79d14cbf932', '4d3e6c9cbec284d6c5bc1311923e68f0', -200, 'JFDD6419637382', '2020-02-19 22:56:11', '月卡办理');
INSERT INTO `user_pay` VALUES ('326fc570eb4093e43178436ffb3fec2f', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD2141715996', '2020-02-20 19:21:41', '月卡办理');
INSERT INTO `user_pay` VALUES ('3a7baf5bab2e5b9c7e1b4ac614694806', '1d3a17f0c523ecb4ab3ac955c23070c4', 20, 'JFDD2101125884', '2020-02-23 23:23:23', '账户充值');
INSERT INTO `user_pay` VALUES ('3c270dc5fcb90557bd13cf3cc919e072', '4d3e6c9cbec284d6c5bc1311923e68f0', 3000, 'JFDD1346676385', '2020-02-20 20:25:01', '账户充值');
INSERT INTO `user_pay` VALUES ('4acf45da207ac176619bad6bca4f6bfa', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD2709924557', '2020-02-20 20:23:03', '月卡办理');
INSERT INTO `user_pay` VALUES ('4c0e806fc89a0900313599a5674c57b8', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD6275857789', '2020-02-20 17:31:08', '月卡办理');
INSERT INTO `user_pay` VALUES ('4e04a401bd489087ba0124ca641101d7', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD4975073395', '2020-02-20 20:24:19', '月卡办理');
INSERT INTO `user_pay` VALUES ('563d701bab6be3736dd584599085bede', '0da744141990b3808eebd9f03a3098f9', 500, 'JFDD1069120874', '2020-02-20 22:44:32', '账户充值');
INSERT INTO `user_pay` VALUES ('625e355cb4457db1b24f2fcc54ae8021', '4d3e6c9cbec284d6c5bc1311923e68f0', -300, 'JFDD1634303962', '2020-02-27 19:34:18', '月卡办理');
INSERT INTO `user_pay` VALUES ('648183fb21972420dfb0b6d62161f88e', '0da744141990b3808eebd9f03a3098f9', 20, 'JFDD1503846068', '2020-02-20 21:51:08', '账户充值');
INSERT INTO `user_pay` VALUES ('73a99293203f9afae65bb1eab605f00c', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD4309876349', '2020-02-20 22:42:47', '账户充值');
INSERT INTO `user_pay` VALUES ('744d79b6e87a0f8f280a6bd81f2a3136', '0da744141990b3808eebd9f03a3098f9', 200, 'JFDD5283757641', '2020-02-20 22:44:56', '月卡办理');
INSERT INTO `user_pay` VALUES ('8074b216f7f0b7c4949c51ea201b2eab', '4d3e6c9cbec284d6c5bc1311923e68f0', -200, 'JFDD1606916245', '2020-02-22 10:44:24', '月卡办理');
INSERT INTO `user_pay` VALUES ('81ec42edcac76f176d372fa31ae737f6', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD3869404422', '2020-02-28 13:38:29', '余额充值');
INSERT INTO `user_pay` VALUES ('920d1d2acc05a74c8e40a81d242e28af', '4d3e6c9cbec284d6c5bc1311923e68f0', 80, 'JFDD3225390861', '2020-02-20 16:43:08', '账户充值');
INSERT INTO `user_pay` VALUES ('9c0ea523458703f504a9d52d06a8dccc', '4d3e6c9cbec284d6c5bc1311923e68f0', 3000, 'JFDD1886765240', '2020-02-28 13:32:57', '余额充值');
INSERT INTO `user_pay` VALUES ('a399e4d4c429c041c07b2c0cc96dc943', '0da744141990b3808eebd9f03a3098f9', 10, 'JFDD6298778081', '2020-02-23 23:22:35', '账户充值');
INSERT INTO `user_pay` VALUES ('a9b4127c2fddafb9fb3445ad2f378011', NULL, 200, 'JFDD2084666572', '2020-02-25 11:28:10', NULL);
INSERT INTO `user_pay` VALUES ('ae00c26cae31f18a9cd2c7563d8ecea0', '4d3e6c9cbec284d6c5bc1311923e68f0', 500, 'JFDD4485425572', '2020-02-20 16:42:30', '账户充值');
INSERT INTO `user_pay` VALUES ('c144bca85ca99922bb74de6a43137517', '0da744141990b3808eebd9f03a3098f9', 5, 'JFDD2412176642', '2020-02-20 21:51:20', '停车缴费');
INSERT INTO `user_pay` VALUES ('c35d04a6f1ced562d722c96306b17b51', '4d3e6c9cbec284d6c5bc1311923e68f0', 0, 'JFDD1555172414', '2020-02-20 22:55:32', '停车缴费');
INSERT INTO `user_pay` VALUES ('c6ec6a0e17c86314b17a24adac5a6ea8', '4d3e6c9cbec284d6c5bc1311923e68f0', 3000, 'JFDD1806907340', '2020-02-20 16:42:53', '账户充值');
INSERT INTO `user_pay` VALUES ('e2b2fe2a43b34a8c2a1117ddfaeac42e', '4d3e6c9cbec284d6c5bc1311923e68f0', 500, 'JFDD4976332173', '2020-02-20 20:22:46', '账户充值');
INSERT INTO `user_pay` VALUES ('e383d60ad9b7038b433c6993026fcea6', '4d3e6c9cbec284d6c5bc1311923e68f0', 1800, 'JFDD1227123803', '2020-02-20 17:28:30', '年卡办理');
INSERT INTO `user_pay` VALUES ('e4afa6dc67dfdc033d4efd962721288c', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD2031691966', '2020-02-20 19:14:45', '账户充值');
INSERT INTO `user_pay` VALUES ('e8a49460f0df29f235e9ace33544c8a6', '4d3e6c9cbec284d6c5bc1311923e68f0', 1800, 'JFDD9932123829', '2020-02-20 17:30:04', '年卡办理');
INSERT INTO `user_pay` VALUES ('fef4303cc182195e00aff0fe929b9fff', '4d3e6c9cbec284d6c5bc1311923e68f0', 0, 'JFDD5097925182', '2020-02-21 10:31:52', '停车缴费');

-- ----------------------------
-- Table structure for user_user
-- ----------------------------
DROP TABLE IF EXISTS `user_user`;
CREATE TABLE `user_user`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `nicheng` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `money` double NULL DEFAULT 0 COMMENT '用户账户余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_user
-- ----------------------------
INSERT INTO `user_user` VALUES ('0da744141990b3808eebd9f03a3098f9', 'james', 'bbbbbb', '13816422218', '詹姆斯', 320);
INSERT INTO `user_user` VALUES ('1d3a17f0c523ecb4ab3ac955c23070c4', 'adminaa', 'aaaaaa', '17748373732', '大力', 20);
INSERT INTO `user_user` VALUES ('4d3e6c9cbec284d6c5bc1311923e68f0', 'aaaaaa', 'admina', '17749707028', '赫乙正方', 3680);
INSERT INTO `user_user` VALUES ('6dfef63c1615f8a93a7331accec38cf4', 'guestc', 'aaaaaa', '13912345600', '保罗', 0);
INSERT INTO `user_user` VALUES ('748478b433aa3987747602f10fc7ad1a', 'admina', 'aaaaaa', '17748373732', '大力', 0);
INSERT INTO `user_user` VALUES ('765dd4600cc15cd0b936fd18a3001997', 'guestcr', 'cccccc', '13912345671', '安东尼', 0);
INSERT INTO `user_user` VALUES ('a48075877ea90d8c14dae3833641815c', 'test01', 'aaaaaa', '13912345634', 'test01', 0);
INSERT INTO `user_user` VALUES ('b0a895365eab35af00f88434d2fe7133', 'aaaaab', 'aaaaaa', '13912345678', 'aaaaaa', 0);
INSERT INTO `user_user` VALUES ('eaf296e46448a27fa2eba609deece1sa', 'acd', 'aaaaaa', '13912345678', '李宝蓝', 0);
INSERT INTO `user_user` VALUES ('eaf296e46448a27fa2eba609deece2312', 'admin', 'aaaaaa', '13912345678', '第三方', 0);

SET FOREIGN_KEY_CHECKS = 1;
