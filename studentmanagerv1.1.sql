/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : studentmanager

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-09 14:33:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_csmid`
-- ----------------------------
DROP TABLE IF EXISTS `t_csmid`;
CREATE TABLE `t_csmid` (
  `class_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`teacher_id`,`class_id`),
  KEY `fk_1` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_csmid
-- ----------------------------
INSERT INTO `t_csmid` VALUES ('1', '170001');
INSERT INTO `t_csmid` VALUES ('2', '170002');
INSERT INTO `t_csmid` VALUES ('3', '170003');
INSERT INTO `t_csmid` VALUES ('9', '15528');
INSERT INTO `t_csmid` VALUES ('10', '15528');
INSERT INTO `t_csmid` VALUES ('12', '15528');

-- ----------------------------
-- Table structure for `t_grade`
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `id` int(11) NOT NULL COMMENT '年级id',
  `gradename` varchar(255) NOT NULL COMMENT '大学-年级名称',
  `school_id` int(11) NOT NULL COMMENT '年级对应的学校',
  PRIMARY KEY (`id`),
  KEY `FK_school` (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grade
-- ----------------------------
INSERT INTO `t_grade` VALUES ('1', '大一', '1');
INSERT INTO `t_grade` VALUES ('2', '大二', '1');
INSERT INTO `t_grade` VALUES ('3', '大三', '1');
INSERT INTO `t_grade` VALUES ('4', '大四', '1');
INSERT INTO `t_grade` VALUES ('5', '大一', '2');
INSERT INTO `t_grade` VALUES ('6', '大二', '2');
INSERT INTO `t_grade` VALUES ('7', '大三', '2');
INSERT INTO `t_grade` VALUES ('8', '大四', '2');

-- ----------------------------
-- Table structure for `t_school`
-- ----------------------------
DROP TABLE IF EXISTS `t_school`;
CREATE TABLE `t_school` (
  `id` int(11) NOT NULL COMMENT '学校编号',
  `schoolname` varchar(255) NOT NULL COMMENT '学校名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_school
-- ----------------------------
INSERT INTO `t_school` VALUES ('1', '广东工业大学');
INSERT INTO `t_school` VALUES ('2', '华南理工大学');

-- ----------------------------
-- Table structure for `t_score`
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `subject_id` int(11) NOT NULL COMMENT '对应课程id',
  `student_id` int(11) NOT NULL COMMENT '对应学生学号',
  `subject_score` double(11,0) NOT NULL DEFAULT '0' COMMENT '课程成绩',
  `subject_comment` varchar(255) DEFAULT NULL COMMENT '老师评价',
  PRIMARY KEY (`subject_id`,`student_id`),
  KEY `student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES ('1', '170001', '100', '很好');
INSERT INTO `t_score` VALUES ('2', '170001', '98', '不错啊');
INSERT INTO `t_score` VALUES ('3', '170001', '98', '可以');

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL COMMENT '学号',
  `username` varchar(255) NOT NULL COMMENT '学生姓名',
  `sex` varchar(255) NOT NULL DEFAULT '男' COMMENT '学生性别',
  `phone` varchar(255) DEFAULT NULL COMMENT '紧急联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `class_id` int(11) NOT NULL DEFAULT '1' COMMENT '班级编号外键',
  `user_id` int(11) NOT NULL COMMENT '学生对应用户表',
  PRIMARY KEY (`id`),
  KEY `fk_inclassid` (`class_id`),
  KEY `fk_studentid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('170001', '学生170001', '男', '1234567', '123456', '1', '7');
INSERT INTO `t_student` VALUES ('170003', '学生170003', '男', '13652493839', null, '1', '9');
INSERT INTO `t_student` VALUES ('170004', '学生170004', '男', '13652493839', null, '1', '10');
INSERT INTO `t_student` VALUES ('170005', '学生170005', '男', '13652493839', null, '1', '11');
INSERT INTO `t_student` VALUES ('170006', '学生170006', '男', '13652493839', null, '1', '12');
INSERT INTO `t_student` VALUES ('170007', '学生170007', '男', '13652493839', null, '1', '13');
INSERT INTO `t_student` VALUES ('170008', '学生170008', '男', '13652493839', null, '1', '14');
INSERT INTO `t_student` VALUES ('170009', '学生170009', '男', '13652493839', null, '1', '15');
INSERT INTO `t_student` VALUES ('170010', '学生170010', '男', '13652493839', null, '1', '16');

-- ----------------------------
-- Table structure for `t_subject`
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `subjectname` varchar(255) NOT NULL COMMENT '课程名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `subject_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES ('1', '大学英语');
INSERT INTO `t_subject` VALUES ('2', '大学物理');
INSERT INTO `t_subject` VALUES ('3', '高等数学');
INSERT INTO `t_subject` VALUES ('4', '离散数学');
INSERT INTO `t_subject` VALUES ('5', '程序设计');
INSERT INTO `t_subject` VALUES ('6', '线性代数');
INSERT INTO `t_subject` VALUES ('7', '体育');
INSERT INTO `t_subject` VALUES ('8', '电工学与实验');

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '老师编号',
  `username` varchar(255) NOT NULL COMMENT '老师姓名',
  `sex` varchar(255) NOT NULL DEFAULT '男' COMMENT '老师性别',
  `grade_id` int(255) DEFAULT NULL COMMENT '老师所属年级',
  `class_id` int(11) DEFAULT NULL COMMENT '班主任对应班级',
  `phone` varchar(255) DEFAULT NULL COMMENT '老师联系方式',
  `email` varchar(255) DEFAULT NULL COMMENT '老师邮箱地址',
  `school_id` int(11) DEFAULT '1' COMMENT '教师对应学校',
  `power` smallint(6) NOT NULL DEFAULT '0' COMMENT '教师不同权限',
  `user_id` int(11) NOT NULL COMMENT '教师对应用户表',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacherId` (`id`) USING BTREE,
  KEY `fk_classid` (`class_id`),
  KEY `fk_teacherid` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170011 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('6000', '我是大四级长', '女', '4', null, '13652493839', null, '1', '2', '6');
INSERT INTO `t_teacher` VALUES ('7000', '我是大三级长', '男', '3', null, '13652493839', null, '1', '2', '5');
INSERT INTO `t_teacher` VALUES ('8000', '我是大二级长', '女', '2', null, '13652493839', null, '1', '2', '4');
INSERT INTO `t_teacher` VALUES ('9000', '我是大一级长', '男', '1', null, '13652493839', null, '1', '2', '3');
INSERT INTO `t_teacher` VALUES ('10000', '我是校长', '男', null, null, '15521399382', 'woshidalao@163.com', '1', '3', '2');
INSERT INTO `t_teacher` VALUES ('170001', '老师170001', '男', '1', '1', '12333333', '12444444', '1', '0', '17');
INSERT INTO `t_teacher` VALUES ('170003', '老师170003', '男', null, null, null, null, '1', '1', '19');
INSERT INTO `t_teacher` VALUES ('170004', '老师170004', '男', null, null, null, null, '1', '0', '20');
INSERT INTO `t_teacher` VALUES ('170005', '老师170005', '男', null, null, null, null, '1', '0', '21');
INSERT INTO `t_teacher` VALUES ('170006', '老师170006', '男', null, null, null, null, '1', '0', '22');
INSERT INTO `t_teacher` VALUES ('170007', '老师170007', '男', null, null, null, null, '1', '0', '23');
INSERT INTO `t_teacher` VALUES ('170008', '老师170008', '男', null, null, null, null, '1', '0', '24');
INSERT INTO `t_teacher` VALUES ('170009', '老师170009', '男', null, null, null, null, '1', '0', '25');
INSERT INTO `t_teacher` VALUES ('170010', '老师170010', '男', null, null, null, null, '1', '0', '26');

-- ----------------------------
-- Table structure for `t_theclass`
-- ----------------------------
DROP TABLE IF EXISTS `t_theclass`;
CREATE TABLE `t_theclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `classname` varchar(255) NOT NULL COMMENT '班级名称',
  `headteacher_id` int(255) DEFAULT NULL COMMENT '班主任',
  `grade_id` int(255) DEFAULT NULL COMMENT '所属年级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_id` (`id`),
  KEY `fk_gradeid` (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_theclass
-- ----------------------------
INSERT INTO `t_theclass` VALUES ('1', '17软工一班', '170001', '1');
INSERT INTO `t_theclass` VALUES ('2', '17软工二班', '17102', '1');
INSERT INTO `t_theclass` VALUES ('3', '17软工三班', '17103', '1');
INSERT INTO `t_theclass` VALUES ('4', '17软工四班', '17104', '1');
INSERT INTO `t_theclass` VALUES ('5', '16计科一班', '16201', '2');
INSERT INTO `t_theclass` VALUES ('6', '16计科二班', '16202', '2');
INSERT INTO `t_theclass` VALUES ('7', '16计科三班', '16203', '2');
INSERT INTO `t_theclass` VALUES ('8', '16计科四班', '16204', '2');
INSERT INTO `t_theclass` VALUES ('9', '15网工一班', '15301', '3');
INSERT INTO `t_theclass` VALUES ('10', '15网工二班', '15302', '3');
INSERT INTO `t_theclass` VALUES ('11', '15网工三班', '15303', '3');
INSERT INTO `t_theclass` VALUES ('12', '15网工四班', '15304', '3');
INSERT INTO `t_theclass` VALUES ('13', '14信安一班', '14401', '4');
INSERT INTO `t_theclass` VALUES ('14', '14信安二班', '14402', '4');
INSERT INTO `t_theclass` VALUES ('15', '14信安三班', '14403', '4');
INSERT INTO `t_theclass` VALUES ('16', '14信安四班', '14404', '4');
INSERT INTO `t_theclass` VALUES ('18', 'asdsa', '0', '3');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '123456' COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userinfo` (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'TiHom', '123456');
INSERT INTO `t_user` VALUES ('2', 'xiaozhang', '123456');
INSERT INTO `t_user` VALUES ('3', 'jizhang1', '123456');
INSERT INTO `t_user` VALUES ('4', 'jizhang2', '123456');
INSERT INTO `t_user` VALUES ('5', 'jizhang3', '123456');
INSERT INTO `t_user` VALUES ('6', 'jizhang4', '123456');
INSERT INTO `t_user` VALUES ('7', '学生1', '123456');
INSERT INTO `t_user` VALUES ('8', '学生2', '123456');
INSERT INTO `t_user` VALUES ('9', '学生3', '123456');
INSERT INTO `t_user` VALUES ('10', '学生4', '123456');
INSERT INTO `t_user` VALUES ('11', '学生5', '123456');
INSERT INTO `t_user` VALUES ('12', '学生6', '123456');
INSERT INTO `t_user` VALUES ('13', '学生7', '123456');
INSERT INTO `t_user` VALUES ('14', '学生8', '123456');
INSERT INTO `t_user` VALUES ('15', '学生9', '123456');
INSERT INTO `t_user` VALUES ('16', '学生10', '123456');
INSERT INTO `t_user` VALUES ('17', '老师1', '123456');
INSERT INTO `t_user` VALUES ('18', '老师2', '123456');
INSERT INTO `t_user` VALUES ('19', '老师3', '123456');
INSERT INTO `t_user` VALUES ('20', '老师4', '123456');
INSERT INTO `t_user` VALUES ('21', '老师5', '123456');
INSERT INTO `t_user` VALUES ('22', '老师6', '123456');
INSERT INTO `t_user` VALUES ('23', '老师7', '123456');
INSERT INTO `t_user` VALUES ('24', '老师8', '123456');
INSERT INTO `t_user` VALUES ('25', '老师9', '123456');
INSERT INTO `t_user` VALUES ('26', '老师10', '123456');
