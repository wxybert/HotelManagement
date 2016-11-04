CREATE DATABASE  IF NOT EXISTS `hotel_management` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hotel_management`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_management
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `idNum` varchar(255) DEFAULT NULL,
  `isVIP` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `idNum_UNIQUE` (`idNum`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'110101198010018233','','张块','123456','15172365100'),(2,'110101198010016414','\0','李丈愿','123456','15172365101'),(3,'110101198010014451','\0','杜维出','123456','15172365102'),(4,'110101198010013731','\0','姜改','123456','15172365103'),(5,'110101198010011090','\0','谭秘','123456','15172365104'),(6,'110101198010013993','\0','潘缺名','123456','15172365105'),(7,'110101198010015331','\0','罗银录','123456','15172365106'),(8,'110101198010015657','\0','丁声','123456','15172365107'),(9,'110101198010017871','','陶然','123456','15172365108'),(10,'110101198010014312','\0','程让','123456','15172365109'),(11,'110101198010014419','\0','董响比','123456','15172365110'),(12,'110101198010018874','\0','杜尼','123456','15172365111'),(13,'110101198010013475','\0','尹空','123456','15172365112'),(14,'110101198010019359','\0','孙拍卡','123456','15172365113'),(15,'11010119801001147X','\0','龚莫','123456','15172365114'),(16,'110101198010016852','\0','唐福','123456','15172365115'),(17,'110101198010018479','\0','吴用','123456','15172365116'),(18,'110101198010013053','\0','魏奇','123456','15172365117'),(19,'110101198010014857','','林容纪','123456','15172365118'),(20,'110101198010019658','\0','江消','123456','15172365119'),(21,'110101198010018196','\0','黎叶皮','123456','15172365120'),(22,'110101198010012835','\0','范跟引','123456','15172365121'),(23,'110101198010016473','\0','毛木','123456','15172365122'),(24,'110101198010016334','\0','贾讲','123456','15172365123'),(25,'11010119801001745X','\0','袁最时','123456','15172365124'),(26,'110101198010016019','\0','闫切','123456','15172365125'),(27,'110101198010014750','\0','贾露线','123456','15172365126'),(28,'110101198010013838','\0','杨把汉','123456','15172365127'),(29,'110101198010011699','','杜化式','123456','15172365128'),(30,'110101198010014734','\0','向速依','123456','15172365129'),(31,'110101198010018238','\0','逗比哈哈哈','123456','15172365130'),(77,'110101198010018231','\0','佚名','123456','15172365131');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'小二','456789','456789');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `date_check_in` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FKqbhxoj3khrgh1alytif5dyp4` (`cid`),
  KEY `FK20k3m70dug0oq1s68n1i7oqlc` (`rid`),
  CONSTRAINT `FK20k3m70dug0oq1s68n1i7oqlc` FOREIGN KEY (`rid`) REFERENCES `room` (`rid`),
  CONSTRAINT `FKqbhxoj3khrgh1alytif5dyp4` FOREIGN KEY (`cid`) REFERENCES `customer` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2016-11-02 20:53:00','2016-11-03 12:00:00',1,1,1),(2,'2016-11-02 21:17:34','2016-11-03 12:00:00',3,1,1),(3,'2016-11-02 21:20:11','2016-11-02 12:00:00',1,2,2),(4,'2016-11-03 20:01:13','2016-11-04 12:00:00',2,5,3),(5,'2016-11-04 21:30:41','2016-11-05 12:00:00',0,4,4);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `is_free` bit(1) DEFAULT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `room_no` varchar(100) DEFAULT NULL,
  `size` int(11) NOT NULL,
  `room_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `UK_mna8ilkl1xyq2j47tld36dbfy` (`room_no`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'100',2,2),(2,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'101',1,2),(3,'双人普通标间，舒适自然','\0','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'102',2,2),(4,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'103',1,2),(5,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'104',2,2),(6,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'105',1,2),(7,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'106',2,2),(8,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'107',1,2),(9,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'108',2,2),(10,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'109',1,2),(11,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'110',2,2),(12,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'111',1,2),(13,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'112',2,2),(14,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'113',1,2),(15,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'114',2,2),(16,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'115',1,2),(17,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'116',2,2),(18,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'117',1,2),(19,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'118',2,2),(20,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'119',1,2),(21,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'200',2,2),(22,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'201',1,2),(23,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'202',2,2),(24,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'203',1,2),(25,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'204',2,2),(26,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'205',1,2),(27,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'206',2,2),(28,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'207',1,2),(29,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'208',2,2),(30,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'209',1,2),(31,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'210',2,2),(32,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'211',1,2),(33,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'212',2,2),(34,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'213',1,2),(35,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'214',2,2),(36,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'215',1,2),(37,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'216',2,2),(38,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'217',1,2),(39,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'218',2,2),(40,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'219',1,2),(41,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'300',2,2),(42,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'301',1,2),(43,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'302',2,2),(44,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'303',1,2),(45,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'304',2,2),(46,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'305',1,2),(47,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'306',2,2),(48,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'307',1,2),(49,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'308',2,2),(50,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'309',1,2),(51,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'310',2,2),(52,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'311',1,2),(53,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'312',2,2),(54,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'313',1,2),(55,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'314',2,2),(56,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'315',1,2),(57,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'316',2,2),(58,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'317',1,2),(59,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'318',2,2),(60,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'319',1,2),(61,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'400',2,2),(62,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'401',1,2),(63,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'402',2,2),(64,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'403',1,2),(65,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'404',2,2),(66,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'405',1,2),(67,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'406',2,2),(68,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'407',1,2),(69,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'408',2,2),(70,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'409',1,2),(71,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'410',2,2),(72,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'411',1,2),(73,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'412',2,2),(74,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'413',1,2),(75,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'414',2,2),(76,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'415',1,2),(77,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'416',2,2),(78,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'417',1,2),(79,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'418',2,2),(80,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'419',1,2),(81,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'500',2,2),(82,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'501',1,2),(83,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'502',2,2),(84,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'503',1,2),(85,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'504',2,2),(86,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'505',1,2),(87,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'506',2,2),(88,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'507',1,2),(89,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'508',2,2),(90,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'509',1,2),(91,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'510',2,2),(92,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'511',1,2),(93,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'512',2,2),(94,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'513',1,2),(95,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'514',2,2),(96,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'515',1,2),(97,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'516',2,2),(98,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'517',1,2),(99,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',128,'518',2,2),(100,'双人普通标间，舒适自然','','http://o762c73os.bkt.clouddn.com/ordinary_standard.png',108,'519',1,2),(101,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',288,'600',2,3),(102,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',208,'601',1,3),(103,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',288,'602',2,3),(104,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',208,'603',1,3),(105,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',288,'604',2,3),(106,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',208,'605',1,3),(107,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',288,'606',2,3),(108,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',208,'607',1,3),(109,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',288,'608',2,3),(110,'双人豪华标间，高端大气上档次','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',208,'609',1,3),(111,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'700',2,0),(112,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'701',2,0),(113,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'702',2,0),(114,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'703',2,0),(115,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'704',2,0),(116,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'705',2,0),(117,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'706',2,0),(118,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'707',2,0),(119,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'708',2,0),(120,'双人普通套房，提供全套服务','','http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg',300,'709',2,0),(121,'双人豪华套房，成功人士的选择','','http://o762c73os.bkt.clouddn.com/deluxe_suite.jpg',888,'800',2,1),(122,'双人豪华套房，成功人士的选择','','http://o762c73os.bkt.clouddn.com/deluxe_suite.jpg',888,'801',2,1),(123,'双人豪华套房，成功人士的选择','','http://o762c73os.bkt.clouddn.com/deluxe_suite.jpg',888,'802',2,1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-04 22:30:35
