

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS `exam_system` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `exam_system`;

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_no` varchar(50) NOT NULL COMMENT '课程编号',
  `course_name` varchar(50) NOT NULL COMMENT '课程名称',
  `grade` smallint(5) unsigned NOT NULL COMMENT '年级，不要用来做强制性约束',
  `description` varchar(2000) DEFAULT NULL COMMENT '课程描述',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '课程创建者ID；不同届的同一门课程的课程编号是一样的，然后用课程编号+年级把课程唯一化，每个老师只能管理自己添加的课程（包括导入试题和创建考试，查看考试结果）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `no_name_index` (`course_no`,`course_name`),
  KEY `FK_course_info_sys_users` (`create_by`),
  CONSTRAINT `FK_course_info_sys_users` FOREIGN KEY (`create_by`) REFERENCES `sys_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='课程学习表，不同届的课程编号一样，使用年级来区分';

-- ----------------------------
-- Records of course_info
-- ----------------------------
INSERT INTO `course_info` VALUES ('2', '12341234', '软件过程', '14', '软件过程与管理', '19');
INSERT INTO `course_info` VALUES ('3', '45345234', '需求工程', '14', '需求工程2342', '20');
INSERT INTO `course_info` VALUES ('6', '12341', '软件工程', '14', '发牢骚的解放啦', '15');

-- ----------------------------
-- Table structure for question_info
-- ----------------------------
DROP TABLE IF EXISTS `question_info`;
CREATE TABLE `question_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) unsigned NOT NULL COMMENT '课程ID',
  `question` varchar(2000) NOT NULL COMMENT '题目',
  `options` varchar(2000) NOT NULL COMMENT '选项JSON字符串',
  `multi` tinyint(4) unsigned NOT NULL COMMENT '是否多选',
  `answer` varchar(20) NOT NULL COMMENT '正确答案',
  `create_by` bigint(20) unsigned DEFAULT NULL COMMENT '创建者，可以不做强制性约束',
  PRIMARY KEY (`id`),
  KEY `question_info_FK1` (`course_id`),
  CONSTRAINT `FK_question_info_course_info` FOREIGN KEY (`course_id`) REFERENCES `course_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='问题信息表';

-- ----------------------------
-- Records of question_info
-- ----------------------------
INSERT INTO `question_info` VALUES ('1', '3', '中国共产党成立于哪一年', '{\"A\":\"1920.0\",\"B\":\"1921.0\",\"C\":\"1922.0\",\"D\":\"1923.0\"}', '0', 'b', null);
INSERT INTO `question_info` VALUES ('2', '3', '南昌起义是哪一年', '{\"A\":\"1925\",\"B\":\"1926\",\"C\":\"1927\",\"D\":\"1928\"}', '0', 'c', null);
INSERT INTO `question_info` VALUES ('3', '3', '哪些是软院学生', '{\"A\":\"杨关\",\"B\":\"杨华安\",\"C\":\"陈欢\",\"D\":\"荣国平\"}', '1', 'a|b|c|d', null);
INSERT INTO `question_info` VALUES ('4', '2', '123.0', '{\"A\":\"123.0\",\"B\":\"234.0\",\"C\":\"3.0\",\"D\":\"4.0\"}', '0', 'A', null);

-- ----------------------------
-- Table structure for student_attendance
-- ----------------------------
DROP TABLE IF EXISTS `student_attendance`;
CREATE TABLE `student_attendance` (
  `test_info_id` bigint(20) unsigned NOT NULL COMMENT '考试id',
  `student_id` bigint(20) unsigned NOT NULL COMMENT '学生ID',
  `email` varchar(50) NOT NULL COMMENT '学生邮箱',
  `attendance` tinyint(4) unsigned DEFAULT NULL COMMENT 'd)	是否参加（默认未参加，参加考试后需要更新此字段）',
  `score` smallint(5) unsigned DEFAULT NULL COMMENT '得分',
  `password` varchar(200) NOT NULL COMMENT '用来验证考生身份和考生信息的加密字符串，包含在邮件内容的链接中',
  `send` smallint(6) DEFAULT '0' COMMENT '是否已发送成绩',
  UNIQUE KEY `test_student_index` (`test_info_id`,`student_id`),
  KEY `student_attendance_FK2` (`student_id`),
  CONSTRAINT `student_attendance_FK1` FOREIGN KEY (`test_info_id`) REFERENCES `test_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_attendance_FK2` FOREIGN KEY (`student_id`) REFERENCES `sys_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考生学生参与表\r\n';

-- ----------------------------
-- Records of student_attendance
-- ----------------------------
INSERT INTO `student_attendance` VALUES ('19', '15', '1176807410@qq.com', '0', null, '514f98b760374ad0ac7c238bcbbcca10', '1');
INSERT INTO `student_attendance` VALUES ('19', '17', '141250010@smail.nju.edu.cn', '1', null, '796c3e14cbab483092a36e50a27f27de', '1');
INSERT INTO `student_attendance` VALUES ('19', '18', '141250156@smail.nju.edu.cn', '1', null, 'a044c59241884b688a47aebd9f06e9f1', '1');

-- ----------------------------
-- Table structure for student_question_info
-- ----------------------------
DROP TABLE IF EXISTS `student_question_info`;
CREATE TABLE `student_question_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) unsigned NOT NULL COMMENT '考生编号，不是学号',
  `test_info_id` bigint(20) unsigned NOT NULL COMMENT '考试编号',
  `question_info_id` bigint(20) unsigned NOT NULL COMMENT '问题编号',
  `question_no` smallint(6) unsigned NOT NULL COMMENT '考生试卷考题编号',
  `question` varchar(2000) NOT NULL COMMENT '问题题目',
  `multi` tinyint(4) unsigned NOT NULL COMMENT '是否多选',
  `options` varchar(2000) NOT NULL COMMENT '打乱后的选项JSON字符串',
  `answer` varchar(20) NOT NULL COMMENT '正确答案',
  `student_answer` varchar(20) DEFAULT NULL COMMENT '学生答案',
  `mark` tinyint(4) unsigned NOT NULL COMMENT '分数',
  `score` tinyint(4) unsigned DEFAULT NULL COMMENT '学生得分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_test_question_index` (`student_id`,`test_info_id`,`question_info_id`),
  KEY `student_question_info_FK2` (`test_info_id`),
  KEY `student_question_info_FK3` (`question_info_id`),
  CONSTRAINT `student_question_info_FK1` FOREIGN KEY (`student_id`) REFERENCES `sys_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_question_info_FK2` FOREIGN KEY (`test_info_id`) REFERENCES `test_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_question_info_FK3` FOREIGN KEY (`question_info_id`) REFERENCES `question_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='学生答题信息表';

-- ----------------------------
-- Records of student_question_info
-- ----------------------------
INSERT INTO `student_question_info` VALUES ('16', '17', '19', '1', '1', '中国共产党成立于哪一年', '0', '{\"A\":\"1923.0\",\"B\":\"1920.0\",\"C\":\"1921.0\",\"D\":\"1922.0\"}', 'C', 'C', '50', '50');
INSERT INTO `student_question_info` VALUES ('17', '17', '19', '3', '2', '哪些是软院学生', '1', '{\"A\":\"杨关\",\"B\":\"陈欢\",\"C\":\"荣国平\",\"D\":\"杨华安\"}', 'ABCD', null, '50', '50');
INSERT INTO `student_question_info` VALUES ('18', '18', '19', '2', '1', '南昌起义是哪一年', '0', '{\"A\":\"1926\",\"B\":\"1928\",\"C\":\"1927\",\"D\":\"1925\"}', 'C', 'C', '50', '50');
INSERT INTO `student_question_info` VALUES ('19', '18', '19', '1', '2', '中国共产党成立于哪一年', '0', '{\"A\":\"1922.0\",\"B\":\"1920.0\",\"C\":\"1921.0\",\"D\":\"1923.0\"}', 'C', null, '50', null);

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------
INSERT INTO `sys_permissions` VALUES ('1', 'user:create', '用户模块新增', '1');
INSERT INTO `sys_permissions` VALUES ('3', 'menu:create', '菜单模块新增', '1');
INSERT INTO `sys_permissions` VALUES ('10', 'user:view', '查看用户信息', '0');

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('1', 'admin', '管理员', '1');
INSERT INTO `sys_roles` VALUES ('2', 'user', '用户管理员', '1');

-- ----------------------------
-- Table structure for sys_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permissions`;
CREATE TABLE `sys_roles_permissions` (
  `role_id` bigint(20) unsigned NOT NULL,
  `permission_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `sys_roles_permissions_FK2` (`permission_id`),
  CONSTRAINT `sys_roles_permissions_FK1` FOREIGN KEY (`role_id`) REFERENCES `sys_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_roles_permissions_FK2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permissions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_permissions
-- ----------------------------
INSERT INTO `sys_roles_permissions` VALUES ('1', '1');
INSERT INTO `sys_roles_permissions` VALUES ('2', '1');
INSERT INTO `sys_roles_permissions` VALUES ('2', '10');

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '学号或教师编号',
  `password` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户姓名',
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `grade` smallint(5) unsigned DEFAULT '0' COMMENT '年级',
  `token` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '认证token，每次登录时更新',
  `type` tinyint(4) DEFAULT NULL COMMENT '用户类型，1：学生  2：老师  3：系统管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'zhang', '26e645963625fc16184a7a7c5b81c82d', '81680a99d466f283be4ab34de9f1a707', '0', 'zhang', '141250189@smail.nju.edu.cn', '14', null, null);
INSERT INTO `sys_users` VALUES ('14', '141250177', '3562586798573aadb750c7080af57e69', '535a1097a3b53e66bae5ff6914036875', '0', 'yha', '1176807410@qq.com', '14', null, '1');
INSERT INTO `sys_users` VALUES ('15', '141250167', '0cd5b471af6e8f3895d9fbf17af1fe88', 'e4e15b900b7a30ccb42744f8e3c794f8', '0', '杨华安', '1176807410@qq.com', '14', '49e0b968a10946298cb48d6b8d7d854f', '1');
INSERT INTO `sys_users` VALUES ('16', '141250166', '77d04dbde5e31292c535ee6bfef0624e', '39d3667bee095fe7ce30154762a31c9d', '0', '杨关', '141250166@smail.nju.edu.cn', '14', null, '1');
INSERT INTO `sys_users` VALUES ('17', '141250010', 'acead41494859e0f795a65b4a6b03776', '176fe28ec53bd8e3bda210e311a7824b', '0', '陈欢', '141250010@smail.nju.edu.cn', '14', '4194ff8782e1444585659db3ef158414', '1');
INSERT INTO `sys_users` VALUES ('18', '141250156', '65b6e1d6e7ba5b60c173f7faebb92298', 'd2ea34d48cdc0a1ba30daad34f72c33f', '0', '熊凯奇', '141250156@smail.nju.edu.cn', '14', '186b478db51449b0b05a9f705aeb39e1', '1');
INSERT INTO `sys_users` VALUES ('19', 'a241231', 'a03200a0ac21b4c554ba42ec574e62b0', 'f66c2b4804e5dcbe93d21c2de11da957', '0', '荣国平', '141250167@smail.nju.edu.cn', '250', '134815afee5b48ada9084e2acade1bce', '2');
INSERT INTO `sys_users` VALUES ('20', 'b24d1231f', '34d5709d7f7d7cb804451d950f256e18', '7a199d9e69849756be126cb3e763b5a8', '0', '刘钦', '141250@smail.nju.edu.cn', '25', null, '0');
INSERT INTO `sys_users` VALUES ('21', '1432124', 'ef283c11a2bc4e3e0042beb2d9079806', '95e5925528b884597ed1da25fe8f72c3', '0', '卡西莫多', '141250451@smail.nju.edu.cn', '25', null, '1');
INSERT INTO `sys_users` VALUES ('22', '141257000', '38b33602ce6c0b4d1070177529a630a4', '6d6d9d9b50f079bceb8b4b33b62046a6', '0', '123', '2948363603@qq.com', '1', null, '1');
INSERT INTO `sys_users` VALUES ('23', '13235677667', '75851069708247f537b1a1dc8ed8ce8f', '6359a6039cc9df2608ff9cc477f76178', '0', 'rtt', '2948363603@qq.com', '1', null, '1');
INSERT INTO `sys_users` VALUES ('24', '1414124124', 'df1ca45636e7a94157241c8f8f38075a', '821377f7bd7edb9782e308a5c62f5d8c', '0', '1421', '2948363603@qq.com', '1', null, '1');
INSERT INTO `sys_users` VALUES ('25', '1441251454', '0f6c23db19b643e98d70c9c4fcae5b8f', '4b7dc3c2df164893a1445a0fbad422cf', '0', '141', '2948363603@qq.com', '1', null, '1');

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `sys_users_roles_FK2` (`role_id`),
  CONSTRAINT `sys_users_roles_FK1` FOREIGN KEY (`user_id`) REFERENCES `sys_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_users_roles_FK2` FOREIGN KEY (`role_id`) REFERENCES `sys_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES ('1', '1');

-- ----------------------------
-- Table structure for test_info
-- ----------------------------
DROP TABLE IF EXISTS `test_info`;
CREATE TABLE `test_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) unsigned NOT NULL COMMENT '课程id',
  `title` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '考试名称',
  `description` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '考试描述',
  `grade` smallint(6) unsigned DEFAULT NULL COMMENT '考生年级',
  `start_date` datetime NOT NULL COMMENT '考试开始时间',
  `end_date` datetime NOT NULL COMMENT '考试结束时间',
  `question_amount` smallint(6) unsigned NOT NULL COMMENT '题量',
  `score` smallint(6) unsigned NOT NULL COMMENT '总分',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '考试创建人ID',
  PRIMARY KEY (`id`),
  KEY `test_info_FK1` (`course_id`),
  KEY `test_info_FK2` (`create_by`),
  CONSTRAINT `test_info_FK1` FOREIGN KEY (`course_id`) REFERENCES `course_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `test_info_FK2` FOREIGN KEY (`create_by`) REFERENCES `sys_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='考试信息表';

-- ----------------------------
-- Records of test_info
-- ----------------------------
INSERT INTO `test_info` VALUES ('19', '3', '期末考试', '期末考试的考试', '14', '2017-12-16 00:00:00', '2018-01-05 23:00:00', '2', '100', '19');
INSERT INTO `test_info` VALUES ('20', '2', '期中考四', '123', '14', '2018-03-09 15:26:11', '2018-03-09 15:26:18', '2', '122', '19');
INSERT INTO `test_info` VALUES ('21', '3', '期初考试', '321', '15', '2018-03-09 15:27:11', '2018-03-09 15:27:13', '2', '155', '19');

-- ----------------------------
-- Table structure for test_questions
-- ----------------------------
DROP TABLE IF EXISTS `test_questions`;
CREATE TABLE `test_questions` (
  `test_info_id` bigint(20) unsigned NOT NULL COMMENT '考试ID',
  `question_info_id` bigint(20) unsigned NOT NULL COMMENT '问题id',
  `score` tinyint(3) unsigned NOT NULL COMMENT '分数',
  `question_no` smallint(5) unsigned DEFAULT NULL COMMENT '试题编号，这个不是强制的，可能不需要，直接使用排序检索出来的顺序',
  KEY `test_question_FK1` (`test_info_id`),
  KEY `test_question_FK2` (`question_info_id`),
  CONSTRAINT `test_question_FK1` FOREIGN KEY (`test_info_id`) REFERENCES `test_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `test_question_FK2` FOREIGN KEY (`question_info_id`) REFERENCES `question_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试问题对应表，专门用来对应老师设置的试卷';

-- ----------------------------
-- Records of test_questions
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yha', '123456');

use exam_system;

create user if not exists root identified by 'root' ;

grant all on exam_system.* to root@'%' identified by 'root' with grant option;

flush privileges;
