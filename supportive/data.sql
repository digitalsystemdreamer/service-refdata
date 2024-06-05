DROP TABLE IF EXISTS `billing`;
CREATE TABLE `billing` (
  `billing_id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`billing_id`)
);
INSERT INTO `billing` VALUES (1,'Hourly'),(2,'Daily'),(3,'Weekly'),(4,'Monthly'),(5,'Half Yearly'),(6,'Yearly');

DROP TABLE IF EXISTS `billing_aud`;
CREATE TABLE `billing_aud` (
  `billing_id` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`billing_id`,`rev`),
  KEY `FKe9vw11f1025l2idakjn5y25lv` (`rev`)
);

DROP TABLE IF EXISTS `billing_seq`;
CREATE TABLE `billing_seq` (
  `next_val` bigint DEFAULT NULL
);
INSERT INTO `billing_seq` VALUES (1);

DROP TABLE IF EXISTS `facility`;
CREATE TABLE `facility` (
  `availability_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `facility_id` int NOT NULL,
  `rate` int DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`facility_id`)
);
INSERT INTO `facility` VALUES ('2025-12-05','2024-06-04',1,NULL,'2024-06-04',NULL,'Power Yoga Facility','Yoga1',NULL),('2025-12-05','2024-06-04',2,NULL,'2024-06-04',NULL,'Power Yoga Facility','Yoga2',NULL),('2025-12-05','2024-06-04',3,NULL,'2024-06-04',NULL,'Power Yoga Facility','Yoga3',NULL);

DROP TABLE IF EXISTS `facility_aud`;
CREATE TABLE `facility_aud` (
  `availability_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `facility_id` int NOT NULL,
  `rate` int DEFAULT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`facility_id`,`rev`),
  KEY `FKqqyb9ll281e2fbjv07ssgtq3q` (`rev`)
);
INSERT INTO `facility_aud` VALUES ('2025-12-05','2024-06-04',1,NULL,1,0,'2024-06-04',NULL,'Power Yoga Facility','Yoga1',NULL),('2025-12-05','2024-06-04',2,NULL,2,0,'2024-06-04',NULL,'Power Yoga Facility','Yoga2',NULL),('2025-12-05','2024-06-04',3,NULL,3,0,'2024-06-04',NULL,'Power Yoga Facility','Yoga3',NULL);

DROP TABLE IF EXISTS `facility_billing_map`;

CREATE TABLE `facility_billing_map` (
  `billing_billing_id` int NOT NULL,
  `facility_facility_id` int NOT NULL,
  `rate` int DEFAULT NULL,
  PRIMARY KEY (`billing_billing_id`,`facility_facility_id`),
  KEY `FKndvxccty60qvgk7ncm2ts91jg` (`facility_facility_id`)
);
INSERT INTO `facility_billing_map` VALUES (1,1,10),(1,2,10),(1,3,10),(3,1,50),(3,2,50),(3,3,50);

DROP TABLE IF EXISTS `facility_billing_map_aud`;
CREATE TABLE `facility_billing_map_aud` (
  `billing_billing_id` int NOT NULL,
  `facility_facility_id` int NOT NULL,
  `rate` int DEFAULT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  PRIMARY KEY (`billing_billing_id`,`facility_facility_id`,`rev`),
  KEY `FKkc3s8uu9i4tco5vsqf6bwxa6s` (`rev`)
);
INSERT INTO `facility_billing_map_aud` VALUES (1,1,10,1,0),(1,2,10,2,0),(1,3,10,3,0),(3,1,50,1,0),(3,2,50,2,0),(3,3,50,3,0);

DROP TABLE IF EXISTS `facility_seq`;

CREATE TABLE `facility_seq` (
  `next_val` bigint DEFAULT NULL
);
INSERT INTO `facility_seq` VALUES (101);

DROP TABLE IF EXISTS `membership`;

CREATE TABLE `membership` (
  `created_date` date DEFAULT NULL,
  `floor_package_package_id` int DEFAULT NULL,
  `membership_id` int NOT NULL,
  `points` int DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `valid_from` date DEFAULT NULL,
  `valid_to` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`membership_id`),
  KEY `FKdw9hnc8or64ai472tv4e7luup` (`floor_package_package_id`)
);
INSERT INTO `membership` VALUES ('2024-06-04',1,1,300,'2024-06-04','2025-12-05','2025-12-05',NULL,'Silver Membership','Silver',NULL),('2024-06-04',1,2,300,'2024-06-04','2025-12-05','2025-12-05',NULL,'Gold Membership','Gold',NULL);

DROP TABLE IF EXISTS `membership_aud`;

CREATE TABLE `membership_aud` (
  `created_date` date DEFAULT NULL,
  `floor_package_package_id` int DEFAULT NULL,
  `membership_id` int NOT NULL,
  `points` int DEFAULT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `valid_from` date DEFAULT NULL,
  `valid_to` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`membership_id`,`rev`),
  KEY `FKgvs7a1tngsagglrxd3k0wf13s` (`rev`)
);
INSERT INTO `membership_aud` VALUES ('2024-06-04',1,1,300,4,0,'2024-06-04','2025-12-05','2025-12-05',NULL,'Silver Membership','Silver',NULL),('2024-06-04',1,2,300,5,0,'2024-06-04','2025-12-05','2025-12-05',NULL,'Gold Membership','Gold',NULL);

DROP TABLE IF EXISTS `membership_facility_map`;

CREATE TABLE `membership_facility_map` (
  `duration` int DEFAULT NULL,
  `facility_facility_id` int NOT NULL,
  `membership_membership_id` int NOT NULL,
  PRIMARY KEY (`facility_facility_id`,`membership_membership_id`),
  KEY `FK6foaxx6bq2m7c5iad9dm59k49` (`membership_membership_id`)
);
INSERT INTO `membership_facility_map` VALUES (5,1,1),(5,1,2),(5,2,1),(5,2,2),(5,3,2);

DROP TABLE IF EXISTS `membership_facility_map_aud`;

CREATE TABLE `membership_facility_map_aud` (
  `duration` int DEFAULT NULL,
  `facility_facility_id` int NOT NULL,
  `membership_membership_id` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  PRIMARY KEY (`facility_facility_id`,`membership_membership_id`,`rev`),
  KEY `FKlxvi6jrxkr2aidkurskjhflbs` (`rev`)
);
INSERT INTO `membership_facility_map_aud` VALUES (5,1,1,4,0),(5,1,2,5,0),(5,2,1,4,0),(5,2,2,5,0),(5,3,2,5,0);

DROP TABLE IF EXISTS `membership_seq`;
CREATE TABLE `membership_seq` (
  `next_val` bigint DEFAULT NULL
);
INSERT INTO `membership_seq` VALUES (101);

DROP TABLE IF EXISTS `package`;

CREATE TABLE `package` (
  `package_id` int NOT NULL,
  `package_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`package_id`)
);
INSERT INTO `package` VALUES (1,'Monthly'),(2,'Quarterly'),(3,'Half Yearly'),(4,'Annually');

DROP TABLE IF EXISTS `package_aud`;

CREATE TABLE `package_aud` (
  `package_id` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `package_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`package_id`,`rev`),
  KEY `FKk7jls3jnw67qaea2ibfn243ys` (`rev`)
);

DROP TABLE IF EXISTS `package_seq`;

CREATE TABLE `package_seq` (
  `next_val` bigint DEFAULT NULL
);
INSERT INTO `package_seq` VALUES (1);

DROP TABLE IF EXISTS `revinfo`;

CREATE TABLE `revinfo` (
  `rev` int NOT NULL AUTO_INCREMENT,
  `revtstmp` bigint DEFAULT NULL,
  PRIMARY KEY (`rev`)
);
INSERT INTO `revinfo` VALUES (1,1717513645826),(2,1717513654075),(3,1717513659590),(4,1717513688610),(5,1717513715886);