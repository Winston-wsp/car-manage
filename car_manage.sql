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

 Date: 02/04/2020 20:10:39
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
INSERT INTO `car_card` VALUES ('5e15bec9f045f62d61cd993412d70386', 'ZZ1007709819', 'b0a895365eab35af00f88434d2fe7133', 2, '2020-03-14 15:03:13', '2022-03-14 15:03:13');
INSERT INTO `car_card` VALUES ('69905fc52d355713121be1710af03fff', 'ZZ1919761760', '49cf2dcc85009d259b3e598921576931', 1, '2020-03-14 15:30:25', '2020-04-14 15:30:25');
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
INSERT INTO `car_carparks` VALUES ('0183d7ae3aeab2c1e1c36cb8c0f59f54', 'C区', 'C03', '', '0');
INSERT INTO `car_carparks` VALUES ('1246f8d2184ed1fd11337791583377a4', 'A区', 'A04', '', '0');
INSERT INTO `car_carparks` VALUES ('2782c8418cee75eafdc93daa9bd32379', 'A区', 'A01', '私人车位', '0');
INSERT INTO `car_carparks` VALUES ('305ad15c929a31309f93f9daa4f566e9', 'A区', 'A03', '临时车位', '0');
INSERT INTO `car_carparks` VALUES ('3596c5a9af93e919510e5e35027ad6f1', 'A区', 'A02', '临时车位', '1');
INSERT INTO `car_carparks` VALUES ('468475eba8c5ea4c8352584420fba1e2', 'B区', 'B06', '', '0');
INSERT INTO `car_carparks` VALUES ('4aa63256b64c6046a3a72521b335e356', 'B区', 'B05', '可用车位', '1');
INSERT INTO `car_carparks` VALUES ('4e8c70c5222a0683f3d03793cc4d3e1a', 'B区', 'B01', '', '0');
INSERT INTO `car_carparks` VALUES ('574f400819a7e0e92d9656470f264766', 'C区', 'C02', '', '1');
INSERT INTO `car_carparks` VALUES ('5d983907ecebda9d58ef925b8b92c31d', 'B区', 'B02', '', '0');
INSERT INTO `car_carparks` VALUES ('5fd5e79efbf765fee2a871d6f8bd8fb3', 'B区', 'B04', '', '0');
INSERT INTO `car_carparks` VALUES ('724355ad4838fd3b7bbce7980403e559', 'A区', 'A00', '临时车位', '0');
INSERT INTO `car_carparks` VALUES ('95e83131f95f4940b7e045e6a2ffe7f0', 'A区', 'A05', '', '0');
INSERT INTO `car_carparks` VALUES ('a7e07bbc52fdb4a19b95b84b39751265', 'C区', 'C01', '可用车位', '0');
INSERT INTO `car_carparks` VALUES ('a8105d58e9905ec2bdc952ea077c8fe5', 'B区', 'B03', '', '0');
INSERT INTO `car_carparks` VALUES ('c52b7db5ec4142924939d905e6d03be7', 'C区', 'C04', '可用车位', '1');
INSERT INTO `car_carparks` VALUES ('cd8f7da66590579291daa3f879054473', 'C区', 'C05', '', '1');
INSERT INTO `car_carparks` VALUES ('e5852c101f8a8a6a877211a044f9b72a', 'A区', 'A06', '临时车位', '0');

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
  `sort` int(11) NULL DEFAULT 1 COMMENT '用户的第几辆车',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_parking
-- ----------------------------
INSERT INTO `car_parking` VALUES ('0408e62e2489ca43dea2839c7bee98d4', 'fa17844faba687c617bd4457fd0a3dbb', 'e5852c101f8a8a6a877211a044f9b72a', '沪AJ1111', '2020-03-01 17:11:53', NULL, -1);
INSERT INTO `car_parking` VALUES ('04168ead8b151ecfe261c6e792e451b6', '748478b433aa3987747602f10fc7ad1a', '305ad15c929a31309f93f9daa4f566e9', '沪AJJ846', '2020-02-28 14:29:39', NULL, NULL);
INSERT INTO `car_parking` VALUES ('0ef58378609b1b859edaa022049caffc', '4d3e6c9cbec284d6c5bc1311923e68f0', '2782c8418cee75eafdc93daa9bd32379', '黑AD8831', '2020-03-01 18:02:36', NULL, -2);
INSERT INTO `car_parking` VALUES ('11b0a85a2b743df54837900f065b9f6d', '0da744141990b3808eebd9f03a3098f9', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-20 19:38:59', '2020-02-20 21:40:04', NULL);
INSERT INTO `car_parking` VALUES ('1afad926e3bf45119600d85b5ee61a87', '0da744141990b3808eebd9f03a3098f9', '26e2f9924fd3cd8db88de5ebf8e4e727', '沪AJJ846', '2020-02-20 21:08:57', '2020-02-20 21:09:22', NULL);
INSERT INTO `car_parking` VALUES ('1c91b03387b72a73fee2e552e792fa6a', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AAB273', '2020-02-20 20:21:20', '2020-02-20 20:21:41', NULL);
INSERT INTO `car_parking` VALUES ('1e3af7972e708288095fb27bd65d3c6e', '4d3e6c9cbec284d6c5bc1311923e68f0', 'e5852c101f8a8a6a877211a044f9b72a', '沪AJJ846', '2020-02-28 14:22:46', '2020-03-01 10:13:39', NULL);
INSERT INTO `car_parking` VALUES ('20869297cf6137344e75a0e850911475', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-01-22 14:53:02', '2020-02-19 20:49:57', NULL);
INSERT INTO `car_parking` VALUES ('2b160fa06c992494ef1cdc993698c2cc', '4d3e6c9cbec284d6c5bc1311923e68f0', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-21 10:31:45', '2020-02-21 10:31:49', NULL);
INSERT INTO `car_parking` VALUES ('2e769400871db6f5f0fad28bfa22488d', 'a48075877ea90d8c14dae3833641815c', '1246f8d2184ed1fd11337791583377a4', '京A88888', '2020-02-23 22:07:49', NULL, NULL);
INSERT INTO `car_parking` VALUES ('2fca92e3657fda8794e29daccc4b4918', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '闵HJJ32h', '2020-02-23 21:46:00', '2020-02-27 19:33:41', NULL);
INSERT INTO `car_parking` VALUES ('36b5d4ffbd63fb9d67fbfd70248c9b43', '6dfef63c1615f8a93a7331accec38cf4', '95e83131f95f4940b7e045e6a2ffe7f0', '沪AJJ846', '2020-02-24 12:01:41', NULL, NULL);
INSERT INTO `car_parking` VALUES ('3a4a86427912f1dece813ee5f2f65802', '4d3e6c9cbec284d6c5bc1311923e68f0', '4e8c70c5222a0683f3d03793cc4d3e1a', '闵HJI229', '2020-03-01 11:27:06', NULL, -1);
INSERT INTO `car_parking` VALUES ('426845e7c75b68453a51d2c9165a4dff', '0da744141990b3808eebd9f03a3098f9', '26e2f9924fd3cd8db88de5ebf8e4e727', '闵HI8832', '2020-02-20 21:08:25', '2020-02-20 21:08:32', NULL);
INSERT INTO `car_parking` VALUES ('470f92b4fa5878c9e6fce3447b7fc9b9', 'b0a895365eab35af00f88434d2fe7133', '468475eba8c5ea4c8352584420fba1e2', '沪AJJ846', '2020-03-14 15:02:31', '2020-03-14 15:02:38', 1);
INSERT INTO `car_parking` VALUES ('56f45c439c09d0f8adcc811514ecc337', '4d3e6c9cbec284d6c5bc1311923e68f0', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-19 20:57:16', '2020-02-19 21:00:26', NULL);
INSERT INTO `car_parking` VALUES ('5c7e0214f62e952a3724792a97d09ea7', '49cf2dcc85009d259b3e598921576931', 'c52b7db5ec4142924939d905e6d03be7', '沪AJJ841', '2020-03-14 15:31:09', '2020-03-14 15:31:38', -1);
INSERT INTO `car_parking` VALUES ('63ab0c814794d7f4a4b8961504345430', '748478b433aa3987747602f10fc7ad1a', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-19 21:42:10', '2020-02-19 21:43:11', NULL);
INSERT INTO `car_parking` VALUES ('6698f48e27b20b77ac2436f1b72c897d', '49cf2dcc85009d259b3e598921576931', '468475eba8c5ea4c8352584420fba1e2', '沪AJJ841', '2020-03-14 15:20:52', '2020-03-14 15:22:13', -1);
INSERT INTO `car_parking` VALUES ('68e4ab24bb78566861fa27bf364ad5b0', 'b0a895365eab35af00f88434d2fe7133', '468475eba8c5ea4c8352584420fba1e2', '沪AJJ111', '2020-03-14 15:05:09', '2020-03-14 15:13:36', -1);
INSERT INTO `car_parking` VALUES ('69e5782fc1d63abad990d2b883b5212f', '4d3e6c9cbec284d6c5bc1311923e68f0', '1246f8d2184ed1fd11337791583377a4', '沪AJJ846', '2020-02-21 13:20:23', '2020-02-21 22:09:31', NULL);
INSERT INTO `car_parking` VALUES ('6f7401e927bd491ed863f3f579f9d6dd', '0da744141990b3808eebd9f03a3098f9', 'a8105d58e9905ec2bdc952ea077c8fe5', '黑B99999', '2020-03-01 17:05:53', NULL, -1);
INSERT INTO `car_parking` VALUES ('73b8fa665d9a3174ee42de95da8bc697', '4d3e6c9cbec284d6c5bc1311923e68f0', '3596c5a9af93e919510e5e35027ad6f1', '沪AJJ846', '2020-02-20 21:46:51', '2020-02-20 22:55:29', NULL);
INSERT INTO `car_parking` VALUES ('76a2841af25c134b246b84c0740063f4', '49cf2dcc85009d259b3e598921576931', '3596c5a9af93e919510e5e35027ad6f1', '沪AJJ846', '2020-03-14 15:20:36', '2020-03-14 15:22:10', 1);
INSERT INTO `car_parking` VALUES ('886613366ab9335a8d982af4785cd4cc', '49cf2dcc85009d259b3e598921576931', '3596c5a9af93e919510e5e35027ad6f1', '沪AJJ846', '2020-03-14 15:20:01', '2020-03-14 15:20:19', 1);
INSERT INTO `car_parking` VALUES ('8a8eb84fb458755827dc64c2b83aadb3', '4d3e6c9cbec284d6c5bc1311923e68f0', '2782c8418cee75eafdc93daa9bd32379', '沪AJJ846', '2020-02-20 19:46:59', '2020-02-20 20:21:37', NULL);
INSERT INTO `car_parking` VALUES ('8ed40ddd216e85ac555bc07c9d0ca6cb', '49cf2dcc85009d259b3e598921576931', 'cd8f7da66590579291daa3f879054473', '沪AJJ846', '2020-03-14 15:17:25', '2020-03-14 15:18:58', 1);
INSERT INTO `car_parking` VALUES ('8eec3658f50626da1b2ad5064c982c49', '49cf2dcc85009d259b3e598921576931', 'a7e07bbc52fdb4a19b95b84b39751265', '沪AJJ846', '2020-03-14 15:30:44', NULL, 1);
INSERT INTO `car_parking` VALUES ('910b81b1f1bed155a857033b302a82e5', '4d3e6c9cbec284d6c5bc1311923e68f0', '60f9cffd95983c8111f1e3a49d2de792', '沪AJJ846', '2020-02-19 21:01:48', '2020-02-19 21:02:09', NULL);
INSERT INTO `car_parking` VALUES ('969a0c95e7d758caf21ffb9a9d8d4e13', '4d3e6c9cbec284d6c5bc1311923e68f0', '468475eba8c5ea4c8352584420fba1e2', '沪AJ9999', '2020-02-21 22:13:09', '2020-02-21 22:13:34', NULL);
INSERT INTO `car_parking` VALUES ('9c2a6fa1efdf7284d2d0d97557fdbcda', 'a48075877ea90d8c14dae3833641815c', '95e83131f95f4940b7e045e6a2ffe7f0', '京A88888', '2020-02-23 22:12:47', '2020-02-23 23:04:03', NULL);
INSERT INTO `car_parking` VALUES ('a7d0ee74e859e705a2709f2bb790e5d9', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-02-19 23:13:45', '2020-02-20 17:27:44', NULL);
INSERT INTO `car_parking` VALUES ('a81407f10036aefa8c280bf38c7d0e31', '4d3e6c9cbec284d6c5bc1311923e68f0', 'a8105d58e9905ec2bdc952ea077c8fe5', '京A88888', '2020-03-01 16:15:27', '2020-03-01 16:17:56', NULL);
INSERT INTO `car_parking` VALUES ('a856a0f00a92c8519f7de78f9c21a175', '0da744141990b3808eebd9f03a3098f9', '5d983907ecebda9d58ef925b8b92c31d', '沪A33333', '2020-03-01 17:04:43', '2020-03-01 17:08:21', 1);
INSERT INTO `car_parking` VALUES ('b386b023b551985d7a0c0cd9479ebeab', '49cf2dcc85009d259b3e598921576931', '574f400819a7e0e92d9656470f264766', '沪AJJ846', '2020-03-14 15:28:20', '2020-03-14 15:29:48', 1);
INSERT INTO `car_parking` VALUES ('b61da6b9a5af428ce1495c72ea04711b', '49cf2dcc85009d259b3e598921576931', '3596c5a9af93e919510e5e35027ad6f1', '黑AD8831', '2020-03-14 15:33:39', '2020-03-14 15:33:49', -2);
INSERT INTO `car_parking` VALUES ('b96457d69746fa8edb9eb5164ef62184', 'b0a895365eab35af00f88434d2fe7133', 'a7e07bbc52fdb4a19b95b84b39751265', '沪AJJ846', '2020-03-14 15:04:38', '2020-03-14 15:13:32', 1);
INSERT INTO `car_parking` VALUES ('b9e990a6a1b2bd7cb38fac2323de31a6', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-03-01 11:18:44', NULL, 1);
INSERT INTO `car_parking` VALUES ('bf132241165e4e0a61d54f4a4bd6cc56', '4d3e6c9cbec284d6c5bc1311923e68f0', '5d983907ecebda9d58ef925b8b92c31d', '沪AJJ846', '2020-03-01 18:01:46', NULL, -1);
INSERT INTO `car_parking` VALUES ('c0da291c5a355f57ea5c069c521c6663', 'eaf296e46448a27fa2eba609deece2312', '2782c8418cee75eafdc93daa9bd32379', '浙B7EW82', '2020-02-23 21:47:44', '2020-02-23 22:13:55', NULL);
INSERT INTO `car_parking` VALUES ('c211de1dd90c1733d46fdaddf3e01fed', 'fa17844faba687c617bd4457fd0a3dbb', '0183d7ae3aeab2c1e1c36cb8c0f59f54', '沪AJJ846', '2020-03-01 17:11:37', NULL, 1);
INSERT INTO `car_parking` VALUES ('c247a9e2ff4568fd3102ed83cd18437d', '0da744141990b3808eebd9f03a3098f9', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-02-20 19:47:46', '2020-02-20 21:51:15', NULL);
INSERT INTO `car_parking` VALUES ('cc5ab109d367716cc3eb887c5b8e0bd8', '0da744141990b3808eebd9f03a3098f9', '3596c5a9af93e919510e5e35027ad6f1', '黑BDS838', '2020-02-23 21:50:45', '2020-02-23 23:09:57', NULL);
INSERT INTO `car_parking` VALUES ('d2d2e368b53b6461a83fd33ec5ee45ee', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-02-20 21:07:34', '2020-02-20 21:36:58', NULL);
INSERT INTO `car_parking` VALUES ('d86fd67977573afee6f3e2bc5e5a8100', 'b0a895365eab35af00f88434d2fe7133', '468475eba8c5ea4c8352584420fba1e2', '沪AJJ846', '2020-03-14 14:59:08', '2020-03-14 15:01:15', 1);
INSERT INTO `car_parking` VALUES ('e328320b2ba2161af5d246956f087ef5', '4d3e6c9cbec284d6c5bc1311923e68f0', '26e2f9924fd3cd8db88de5ebf8e4e727', '沪AJJ846', '2020-02-19 20:51:53', '2020-02-19 20:51:59', NULL);
INSERT INTO `car_parking` VALUES ('e7ba48e941972f954c1b8cca2eebea05', '49cf2dcc85009d259b3e598921576931', 'a7e07bbc52fdb4a19b95b84b39751265', '沪AJJ841', '2020-03-14 15:20:52', '2020-03-14 15:22:06', -1);
INSERT INTO `car_parking` VALUES ('ec110f7df270d24c27668222e7a378ca', '4d3e6c9cbec284d6c5bc1311923e68f0', '724355ad4838fd3b7bbce7980403e559', '沪AJJ846', '2020-02-20 17:34:49', '2020-02-20 19:21:23', NULL);

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
INSERT INTO `car_parking_cost` VALUES (1, 300, 1800, 3, 5, 8, 1, 3, 8, 10);

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
INSERT INTO `user_order_alipay` VALUES ('5b56250a2e82053439999b5a429a47fb', 'fa17844faba687c617bd4457fd0a3dbb', 'JFDD2389188586', '100', '个人充值', '余额充值', '2020-03-01 17:10:44', 1);
INSERT INTO `user_order_alipay` VALUES ('77eb856ae13cff229f4b6cd4b1443242', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD4661276306', '70', '个人充值', '余额充值', '2020-02-27 11:15:22', 1);
INSERT INTO `user_order_alipay` VALUES ('77fbfdcfa6ed41c586030849c08ebd3e', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1086797033', '100', '个人充值', '余额充值', '2020-02-28 13:37:55', 1);
INSERT INTO `user_order_alipay` VALUES ('8d8ec8728ec53159e382bef543848654', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1321187205', '80', '个人充值', '余额充值', '2020-02-27 17:34:38', 1);
INSERT INTO `user_order_alipay` VALUES ('a324c479d559fe9904aeb1b8e451d33a', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD1791421447', '1', '个人充值', '余额充值', '2020-02-27 11:17:09', 1);
INSERT INTO `user_order_alipay` VALUES ('a829a4b3f6eed3d31337a750e61d6c1d', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD9508374937', '30', '个人充值', '余额充值', '2020-02-27 10:56:09', 0);
INSERT INTO `user_order_alipay` VALUES ('a97d5457d1f9881f141799149e604a11', '49cf2dcc85009d259b3e598921576931', 'JFDD1649039387', '3400', '个人充值', '余额充值', '2020-03-14 15:18:04', 1);
INSERT INTO `user_order_alipay` VALUES ('b7d8cd76e6ea6824a591cd31ad316d7a', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD5306821666', '100', '个人充值', '余额充值', '2020-03-28 15:47:09', 0);
INSERT INTO `user_order_alipay` VALUES ('ca426aff3f08f6cfa0fad5baecadb166', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD7555459665', '100', '个人充值', '余额充值', '2020-02-27 17:41:51', 1);
INSERT INTO `user_order_alipay` VALUES ('de70a0899ee8809ccba79c2f24d9275d', '49cf2dcc85009d259b3e598921576931', 'JFDD1001016260', '2000', '个人充值', '余额充值', '2020-03-14 15:28:59', 1);
INSERT INTO `user_order_alipay` VALUES ('e719dc8aff409e2ff823227af0903865', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD2045380262', '100', '个人充值', '余额充值', '2020-03-17 20:50:07', 0);
INSERT INTO `user_order_alipay` VALUES ('e919e1270823fca491298f3a16be34ec', 'b0a895365eab35af00f88434d2fe7133', 'JFDD4622602693', '400', '个人充值', '余额充值', '2020-03-14 15:00:03', 1);
INSERT INTO `user_order_alipay` VALUES ('ef264d04fa207eb3de39f40d2dcb4c1e', '4d3e6c9cbec284d6c5bc1311923e68f0', 'JFDD5431334400', '3000', '个人充值', '余额充值', '2020-02-27 19:38:02', 0);
INSERT INTO `user_order_alipay` VALUES ('f64784c107a60debd543ac73d1f0775b', 'b0a895365eab35af00f88434d2fe7133', 'JFDD1433918636', '3500', '个人充值', '余额充值', '2020-03-14 15:03:29', 1);

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
INSERT INTO `user_pay` VALUES ('0b0a824937909841cfc0ac5fa5b7b28f', '49cf2dcc85009d259b3e598921576931', -3, 'JFDD1912603192', '2020-03-14 15:33:54', '停车缴费');
INSERT INTO `user_pay` VALUES ('165cdb71129f2c14540d74406b3381e7', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD4479788351', '2020-02-13 20:25:53', '月卡办理');
INSERT INTO `user_pay` VALUES ('1990354b19573ad8ffa46584b674ecdb', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD2084666572', '2020-02-19 20:25:23', '月卡办理');
INSERT INTO `user_pay` VALUES ('1c7a3cc88e4baf336b7d8b911da72264', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD1629399078', '2020-02-26 20:46:08', '账户充值');
INSERT INTO `user_pay` VALUES ('1e16bc36081cdf0669b43ef79d35e18a', '49cf2dcc85009d259b3e598921576931', 3400, 'JFDD1919686575', '2020-03-14 15:18:52', '余额充值');
INSERT INTO `user_pay` VALUES ('20ffcd75696d234f144a496586081c51', '49cf2dcc85009d259b3e598921576931', -3, 'JFDD1642539615', '2020-03-14 15:22:08', '停车缴费');
INSERT INTO `user_pay` VALUES ('241208c5d0903580078f290331f29a33', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD2026779258', '2020-02-27 19:31:18', '余额充值');
INSERT INTO `user_pay` VALUES ('2529fd51903f989bd7b75a7b599be470', '49cf2dcc85009d259b3e598921576931', -3, 'JFDD1648357098', '2020-03-14 15:31:41', '停车缴费');
INSERT INTO `user_pay` VALUES ('2f11885b77a494d7607ae79d14cbf932', '4d3e6c9cbec284d6c5bc1311923e68f0', -200, 'JFDD6419637382', '2020-02-19 22:56:11', '月卡办理');
INSERT INTO `user_pay` VALUES ('326fc570eb4093e43178436ffb3fec2f', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD2141715996', '2020-02-20 19:21:41', '月卡办理');
INSERT INTO `user_pay` VALUES ('3449249e89e4801da85cb695477386d7', '49cf2dcc85009d259b3e598921576931', -3, 'JFDD1779257794', '2020-03-14 15:19:02', '停车缴费');
INSERT INTO `user_pay` VALUES ('3a7baf5bab2e5b9c7e1b4ac614694806', '1d3a17f0c523ecb4ab3ac955c23070c4', 20, 'JFDD2101125884', '2020-02-23 23:23:23', '账户充值');
INSERT INTO `user_pay` VALUES ('3c270dc5fcb90557bd13cf3cc919e072', '4d3e6c9cbec284d6c5bc1311923e68f0', 3000, 'JFDD1346676385', '2020-02-20 20:25:01', '账户充值');
INSERT INTO `user_pay` VALUES ('3e2147bbd50455224fcbbd18305afeef', '49cf2dcc85009d259b3e598921576931', -300, 'JFDD4515146829', '2020-03-14 15:20:28', '月卡办理');
INSERT INTO `user_pay` VALUES ('43857aba4ffe314f012ee34a9ae7e721', '49cf2dcc85009d259b3e598921576931', -3, 'JFDD1617838701', '2020-03-14 15:22:15', '停车缴费');
INSERT INTO `user_pay` VALUES ('4acf45da207ac176619bad6bca4f6bfa', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD2709924557', '2020-02-20 20:23:03', '月卡办理');
INSERT INTO `user_pay` VALUES ('4c0e806fc89a0900313599a5674c57b8', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD6275857789', '2020-02-20 17:31:08', '月卡办理');
INSERT INTO `user_pay` VALUES ('4e04a401bd489087ba0124ca641101d7', '4d3e6c9cbec284d6c5bc1311923e68f0', 200, 'JFDD4975073395', '2020-02-20 20:24:19', '月卡办理');
INSERT INTO `user_pay` VALUES ('563d701bab6be3736dd584599085bede', '0da744141990b3808eebd9f03a3098f9', 500, 'JFDD1069120874', '2020-02-20 22:44:32', '账户充值');
INSERT INTO `user_pay` VALUES ('625e355cb4457db1b24f2fcc54ae8021', '4d3e6c9cbec284d6c5bc1311923e68f0', -300, 'JFDD1634303962', '2020-02-27 19:34:18', '月卡办理');
INSERT INTO `user_pay` VALUES ('648183fb21972420dfb0b6d62161f88e', '0da744141990b3808eebd9f03a3098f9', 20, 'JFDD1503846068', '2020-02-20 21:51:08', '账户充值');
INSERT INTO `user_pay` VALUES ('6a0ba36ff678dcd5c1b3f71840326003', 'b0a895365eab35af00f88434d2fe7133', -1800, 'JFDD1007515384', '2020-03-14 15:04:19', '年卡办理');
INSERT INTO `user_pay` VALUES ('73a99293203f9afae65bb1eab605f00c', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD4309876349', '2020-02-20 22:42:47', '账户充值');
INSERT INTO `user_pay` VALUES ('744d79b6e87a0f8f280a6bd81f2a3136', '0da744141990b3808eebd9f03a3098f9', 200, 'JFDD5283757641', '2020-02-20 22:44:56', '月卡办理');
INSERT INTO `user_pay` VALUES ('8074b216f7f0b7c4949c51ea201b2eab', '4d3e6c9cbec284d6c5bc1311923e68f0', -200, 'JFDD1606916245', '2020-02-22 10:44:24', '月卡办理');
INSERT INTO `user_pay` VALUES ('8119d0e8b220788c38c6d83ad6826236', '49cf2dcc85009d259b3e598921576931', 2000, 'JFDD1613996188', '2020-03-14 15:29:40', '余额充值');
INSERT INTO `user_pay` VALUES ('81ec42edcac76f176d372fa31ae737f6', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD3869404422', '2020-02-28 13:38:29', '余额充值');
INSERT INTO `user_pay` VALUES ('8333111c77d9427650607c8a48fcf3aa', 'b0a895365eab35af00f88434d2fe7133', -3, 'JFDD1915914520', '2020-03-14 15:02:49', '停车缴费');
INSERT INTO `user_pay` VALUES ('83cb8a2a7d556bb5c83e73ab4553df21', 'b0a895365eab35af00f88434d2fe7133', -3, 'JFDD1435518633', '2020-03-14 15:01:17', '停车缴费');
INSERT INTO `user_pay` VALUES ('920d1d2acc05a74c8e40a81d242e28af', '4d3e6c9cbec284d6c5bc1311923e68f0', 80, 'JFDD3225390861', '2020-02-20 16:43:08', '账户充值');
INSERT INTO `user_pay` VALUES ('9c0ea523458703f504a9d52d06a8dccc', '4d3e6c9cbec284d6c5bc1311923e68f0', 3000, 'JFDD1886765240', '2020-02-28 13:32:57', '余额充值');
INSERT INTO `user_pay` VALUES ('a399e4d4c429c041c07b2c0cc96dc943', '0da744141990b3808eebd9f03a3098f9', 10, 'JFDD6298778081', '2020-02-23 23:22:35', '账户充值');
INSERT INTO `user_pay` VALUES ('a9b4127c2fddafb9fb3445ad2f378011', NULL, 200, 'JFDD2084666572', '2020-02-25 11:28:10', NULL);
INSERT INTO `user_pay` VALUES ('ae00c26cae31f18a9cd2c7563d8ecea0', '4d3e6c9cbec284d6c5bc1311923e68f0', 500, 'JFDD4485425572', '2020-02-20 16:42:30', '账户充值');
INSERT INTO `user_pay` VALUES ('b9272605c8c0f64b7a1cf484b080bc72', '49cf2dcc85009d259b3e598921576931', -300, 'JFDD1914558587', '2020-03-14 15:30:25', '月卡办理');
INSERT INTO `user_pay` VALUES ('be500f857a65bd28bdce15c438737415', '49cf2dcc85009d259b3e598921576931', -3, 'JFDD1774102086', '2020-03-14 15:29:52', '停车缴费');
INSERT INTO `user_pay` VALUES ('c144bca85ca99922bb74de6a43137517', '0da744141990b3808eebd9f03a3098f9', 5, 'JFDD2412176642', '2020-02-20 21:51:20', '停车缴费');
INSERT INTO `user_pay` VALUES ('c35d04a6f1ced562d722c96306b17b51', '4d3e6c9cbec284d6c5bc1311923e68f0', 0, 'JFDD1555172414', '2020-02-20 22:55:32', '停车缴费');
INSERT INTO `user_pay` VALUES ('c6ec6a0e17c86314b17a24adac5a6ea8', '4d3e6c9cbec284d6c5bc1311923e68f0', 3000, 'JFDD1806907340', '2020-02-20 16:42:53', '账户充值');
INSERT INTO `user_pay` VALUES ('d00660461a71d7ed4d6f8f8febe09ce3', 'b0a895365eab35af00f88434d2fe7133', 3500, 'JFDD2863211054', '2020-03-14 15:04:09', '余额充值');
INSERT INTO `user_pay` VALUES ('d7b1d647797cd0d9171233976dd0fab6', 'fa17844faba687c617bd4457fd0a3dbb', 100, 'JFDD8126307816', '2020-03-01 17:11:26', '余额充值');
INSERT INTO `user_pay` VALUES ('e2b2fe2a43b34a8c2a1117ddfaeac42e', '4d3e6c9cbec284d6c5bc1311923e68f0', 500, 'JFDD4976332173', '2020-02-20 20:22:46', '账户充值');
INSERT INTO `user_pay` VALUES ('e383d60ad9b7038b433c6993026fcea6', '4d3e6c9cbec284d6c5bc1311923e68f0', 1800, 'JFDD1227123803', '2020-02-20 17:28:30', '年卡办理');
INSERT INTO `user_pay` VALUES ('e46814c2b686c3be819f8335558bb97f', 'b0a895365eab35af00f88434d2fe7133', -3, 'JFDD1615835141', '2020-03-14 15:13:38', '停车缴费');
INSERT INTO `user_pay` VALUES ('e4afa6dc67dfdc033d4efd962721288c', '4d3e6c9cbec284d6c5bc1311923e68f0', 100, 'JFDD2031691966', '2020-02-20 19:14:45', '账户充值');
INSERT INTO `user_pay` VALUES ('e8a49460f0df29f235e9ace33544c8a6', '4d3e6c9cbec284d6c5bc1311923e68f0', 1800, 'JFDD9932123829', '2020-02-20 17:30:04', '年卡办理');
INSERT INTO `user_pay` VALUES ('f8cd60dd90f2427ed72150389b6e829a', 'b0a895365eab35af00f88434d2fe7133', 400, 'JFDD1006680216', '2020-03-14 15:00:58', '余额充值');
INSERT INTO `user_pay` VALUES ('fef4303cc182195e00aff0fe929b9fff', '4d3e6c9cbec284d6c5bc1311923e68f0', 0, 'JFDD5097925182', '2020-02-21 10:31:52', '停车缴费');
INSERT INTO `user_pay` VALUES ('fef78155121074314eee72baf825c7c4', '49cf2dcc85009d259b3e598921576931', -3, 'JFDD4623309919', '2020-03-14 15:20:22', '停车缴费');

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type`  (
  `id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_type` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型',
  `type_describe` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES ('1', '普通用户', '1小时以内免费；1~4小时收费5元；5小时以上9小时以内收费8元；全天10元。');
INSERT INTO `user_type` VALUES ('2', '月卡用户', '自办卡开始30天内免费停车，仅限于一辆，超出的车辆则按普通用户方式计费');
INSERT INTO `user_type` VALUES ('3', '年卡用户', '自办卡开始365天内免费停车，仅限于一辆，超出的车辆则按普通用户方式计费');

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
INSERT INTO `user_user` VALUES ('49cf2dcc85009d259b3e598921576931', 'harden', 'aaaaaa', '17749707027', '詹姆斯.狗蛋', 1682);
INSERT INTO `user_user` VALUES ('4d3e6c9cbec284d6c5bc1311923e68f0', 'aaaaaa', 'admina', '17749707028', '赫乙正方', 3680);
INSERT INTO `user_user` VALUES ('6dfef63c1615f8a93a7331accec38cf4', 'guestc', 'aaaaaa', '13912345600', '保罗', 0);
INSERT INTO `user_user` VALUES ('748478b433aa3987747602f10fc7ad1a', 'admina', 'aaaaaa', '17748373732', '大力', 0);
INSERT INTO `user_user` VALUES ('765dd4600cc15cd0b936fd18a3001997', 'guestcr', 'cccccc', '13912345671', '安东尼', 0);
INSERT INTO `user_user` VALUES ('a48075877ea90d8c14dae3833641815c', 'test01', 'aaaaaa', '13912345634', 'test01', 0);
INSERT INTO `user_user` VALUES ('b0a895365eab35af00f88434d2fe7133', 'aaaaab', 'aaaaaa', '13912345678', 'aaaaaa', 2082);
INSERT INTO `user_user` VALUES ('eaf296e46448a27fa2eba609deece2312', 'admin', 'aaaaaa', '13912345678', '第三方', 0);
INSERT INTO `user_user` VALUES ('fa17844faba687c617bd4457fd0a3dbb', 'kobe', 'aaaaaa', '17748373732', 'kobe', 100);

SET FOREIGN_KEY_CHECKS = 1;
