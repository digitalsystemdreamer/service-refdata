CREATE TABLE IF NOT EXISTS `billing` (
  `billing_id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`billing_id`)
);
MERGE INTO `billing` VALUES (1,'Hourly'),(2,'Daily'),(3,'Weekly'),(4,'Monthly'),(5,'Half Yearly'),(6,'Yearly');
CREATE TABLE IF NOT EXISTS `package` (
  `package_id` int NOT NULL,
  `package_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`package_id`)
);
MERGE INTO `package` VALUES (1,'Monthly'),(2,'Quarterly'),(3,'Half Yearly'),(4,'Annually');