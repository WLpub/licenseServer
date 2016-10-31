-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: licenseServer
-- ------------------------------------------------------
-- Server version	5.6.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `license` varchar(125) DEFAULT 'default.jpg',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userID` int(11) NOT NULL,
  `status` varchar(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `userID_UNIQUE` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (8,'baidu','1476358649769getPassCodeNew.jpg','2016-10-12 15:46:48',11,'1'),(9,'baidu ','1477643499639Hydrangeas.jpg','2016-10-28 08:21:04',13,'1'),(11,'screenTieger','1477763801113favicon.ico','2016-10-29 20:22:06',15,'1');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `license`
--

DROP TABLE IF EXISTS `license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `license` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file` varchar(100) NOT NULL,
  `result` varchar(100) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userID` int(11) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT '0',
  `description` varchar(500) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idlicense_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `license`
--

LOCK TABLES `license` WRITE;
/*!40000 ALTER TABLE `license` DISABLE KEYS */;
INSERT INTO `license` VALUES (1,'1477500670455.XLS','1477500670455.XLS','2016-10-26 16:47:23',11,'1',''),(2,'1477500670455.XLS','1477500670455.XLS','2016-10-26 16:51:29',11,'1',''),(3,'1477501983513.xls',NULL,'2016-10-26 17:13:20',11,'0',''),(4,'1477501983513.xls',NULL,'2016-10-26 17:15:17',11,'0',''),(5,'1477537434958.XLS','1477537434958.XLS','2016-10-27 03:04:26',11,'1',''),(6,'1477556360525.XLS',NULL,'2016-10-27 08:19:54',11,'0','å®å¨å°ç»ç³è¯·'),(7,'1477556360525.XLS',NULL,'2016-10-27 08:21:26',11,'0','daedafaå®å¨å°ç»'),(8,'1477556360525.XLS',NULL,'2016-10-27 08:23:40',11,'0','dasfå®å¨å°ç»'),(9,'1477556781760.XLS','1477556781760.XLS','2016-10-27 08:26:48',11,'1','中文乱码问题'),(10,'1477643904763.txt','1477643904763.txt','2016-10-28 08:41:02',13,'1','lab实验室人员申请'),(11,'1477643904763.txt','','2016-10-28 08:43:55',13,'-1','lab11人员申请，这是长描述测试。\r\nlab11人员申请，这是长描述测试。\r\nlab11人员申请，这是长描述测试。\r\nlab11人员申请，这是长描述测试。'),(12,'1477763801128.txt',NULL,'2016-10-29 20:23:44',15,'0','这是公司IT部门的申请表，一共fdsfs');
/*!40000 ALTER TABLE `license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `version` varchar(45) NOT NULL,
  `description` varchar(500) NOT NULL,
  `price` varchar(1000) DEFAULT '[]',
  `website` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'ScreenTiger','Beta1.0','<h2>创新录屏大不同</h2><p>ScreenTiger是唯一考虑到企业安全的录屏工具。真正让企业用户放心录制企业会议，培训，开发/测试中软件等敏感内容。</p><p>企业版用户录屏内容均可加密，且限定同公司企业版用户才可以回放。</p><p>无需担心ScreenTiger企业版的录屏文件即使流出公司，256位强度的加密内容结合针对每个操作系统的软件授权是您的安全保证。</p><h2>支持内容创作者</h2><p>ScreenTiger 长期赞助 开源社区</p>','{price:[{time:1,money:0,point:100,id:0},{time:6,money:0,point:500,id:1},{time:12,money:0,point:1000,id:2}]}','http://www.screentiger.cn');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `priceID` int(11) NOT NULL,
  `code` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1,6,1,'2016-10-05 16:21:26',0,'122'),(2,6,1,'2016-10-05 16:24:08',0,'21332'),(3,6,1,'2016-10-05 16:25:30',0,'121'),(4,6,1,'2016-10-05 16:26:03',1,'dssf'),(5,6,1,'2016-10-05 16:26:46',2,'1212332'),(6,6,1,'2016-10-05 16:58:21',0,'dsdd'),(7,6,1,'2016-10-05 17:21:26',0,'12'),(8,6,1,'2016-10-05 17:22:56',0,'12121'),(9,6,1,'2016-10-05 17:27:57',0,'1212'),(10,6,1,'2016-10-05 18:16:07',0,'qewew'),(11,6,1,'2016-10-05 18:18:37',0,'qwqw'),(12,11,1,'2016-10-11 18:13:53',0,'12321r1frefref'),(13,11,1,'2016-10-13 11:43:14',0,'gsdfdgfdgd'),(14,11,1,'2016-10-28 16:51:19',0,'大大的As费萨尔vgferve');
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `permission` varchar(45) NOT NULL DEFAULT '000000',
  `code` varchar(10) DEFAULT NULL,
  `invitationCode` varchar(10) DEFAULT NULL,
  `point` int(10) NOT NULL DEFAULT '0',
  `balance` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'fewrwe','rewrw@12.com','13311928995','123456','000000','1234','123456',0,0),(5,'wdewq2','q12@dasd.com','13311928992','123456','000000',NULL,'123456',0,0),(6,'fesfsfd','123@d.com','13818566643','123456','000000',NULL,'123456',100,10),(7,'rewrwerwe','rwerew@qq.com','13818566642','123456','000000',NULL,'123456',0,0),(11,'wangli','12@123.com','13818566641','123456','012000','cofxrf','123456',900,100),(12,'wangli','wangli@163.com','13818566644','123456','001000','7ohzb8','000000',10,0),(13,'wangli','31223@ewe.com','13818566645','123456','002000','poh6gq','123456',10,0),(14,'wangli','13@fs.com','13818566648','123456','000000','5oz7ur','123456',10,0),(15,'13818566641','13818566641','13818566646','123456','002000','iog9jc','123456',10,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-31 16:51:12
