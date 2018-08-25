-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gxhdb
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `barcode`
--

DROP TABLE IF EXISTS `barcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barcode` (
  `id` bigint(20) NOT NULL,
  `barcode1` bigint(20) DEFAULT NULL,
  `barcode2` bigint(20) DEFAULT NULL,
  `barcode3` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barcode`
--

LOCK TABLES `barcode` WRITE;
/*!40000 ALTER TABLE `barcode` DISABLE KEYS */;
INSERT INTO `barcode` VALUES (1,619828139573,0,0),(2,619828139580,0,0),(3,619828139597,0,0);
/*!40000 ALTER TABLE `barcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `brand_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKawnm3n0n8xym7dw0piidilycs` (`brand_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'OPI Brand',1),(2,'Skin Care Brand',1),(3,'Hair Care Brand',1),(4,'Winter Care Brand',1);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand_group`
--

DROP TABLE IF EXISTS `brand_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand_group` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand_group`
--

LOCK TABLES `brand_group` WRITE;
/*!40000 ALTER TABLE `brand_group` DISABLE KEYS */;
INSERT INTO `brand_group` VALUES (1,'OPI Brand Group');
/*!40000 ALTER TABLE `brand_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `sub_category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'PERSONAL CARE','Nail Colour','NAIL CARE');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dual_mailer`
--

DROP TABLE IF EXISTS `dual_mailer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dual_mailer` (
  `id` bigint(20) NOT NULL,
  `code` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dual_mailer`
--

LOCK TABLES `dual_mailer` WRITE;
/*!40000 ALTER TABLE `dual_mailer` DISABLE KEYS */;
INSERT INTO `dual_mailer` VALUES (1,'DM1'),(2,'DM2'),(3,'DM3'),(4,'DM4'),(5,'DM5'),(6,'DM6'),(7,'DM7'),(8,'DM8'),(9,'DM9'),(10,'DM10'),(11,'DM11'),(12,'DM12'),(13,'DM13');
/*!40000 ALTER TABLE `dual_mailer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `manager_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5cq2mdiw42aesa2n3xyxhhx54` (`manager_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'Naida Tiongson','CATEGORY',3);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager_supplier`
--

DROP TABLE IF EXISTS `manager_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager_supplier` (
  `manager_id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
  PRIMARY KEY (`manager_id`,`supplier_id`),
  KEY `FKecw9cb50fets2qabge37tt46m` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager_supplier`
--

LOCK TABLES `manager_supplier` WRITE;
/*!40000 ALTER TABLE `manager_supplier` DISABLE KEYS */;
INSERT INTO `manager_supplier` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `manager_supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer_callout`
--

DROP TABLE IF EXISTS `offer_callout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer_callout` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer_callout`
--

LOCK TABLES `offer_callout` WRITE;
/*!40000 ALTER TABLE `offer_callout` DISABLE KEYS */;
/*!40000 ALTER TABLE `offer_callout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricing`
--

DROP TABLE IF EXISTS `pricing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pricing` (
  `id` bigint(20) NOT NULL,
  `gdc_cost` float DEFAULT NULL,
  `gdc_markup` float DEFAULT NULL,
  `gdc_sell_cost` float DEFAULT NULL,
  `direct_cost` float DEFAULT NULL,
  `price_list_cost` float DEFAULT NULL,
  `price_list_terms` float DEFAULT NULL,
  `tier1_margin` float DEFAULT NULL,
  `tier1_margin_percent` float DEFAULT NULL,
  `tier1_price` float DEFAULT NULL,
  `tier2_margin` float DEFAULT NULL,
  `tier2_margin_percent` float DEFAULT NULL,
  `tier2_price` float DEFAULT NULL,
  `tier3_margin` float DEFAULT NULL,
  `tier3_margin_percent` float DEFAULT NULL,
  `tier3_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricing`
--

LOCK TABLES `pricing` WRITE;
/*!40000 ALTER TABLE `pricing` DISABLE KEYS */;
INSERT INTO `pricing` VALUES (1,0,0,0,10.45,10.45,0,6.9,0.4,19.95,6.9,0.4,19.95,6.9,0.4,19.95);
/*!40000 ALTER TABLE `pricing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `gxh_id` bigint(20) DEFAULT NULL,
  `plu_code` bigint(20) DEFAULT NULL,
  `product_guid` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `marketing_brand_name` varchar(60) DEFAULT NULL,
  `marketing_description` varchar(100) DEFAULT NULL,
  `marketing_mandatory_description` varchar(100) DEFAULT NULL,
  `marketing_short_name` varchar(60) DEFAULT NULL,
  `marketing_size` varchar(20) DEFAULT NULL,
  `marketing_supplier_legal_name` varchar(80) DEFAULT NULL,
  `only_in_pharmacy` varchar(3) DEFAULT NULL,
  `pharmacist_only` varchar(3) DEFAULT NULL,
  `product_code` bigint(20) DEFAULT NULL,
  `range_status` varchar(6) DEFAULT NULL,
  `vendor_ax_code` bigint(20) DEFAULT NULL,
  `warehouse_or_direct` varchar(1) DEFAULT NULL,
  `barcode_id` bigint(20) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `category_manager_id` bigint(20) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsx81utdtw9a990yvuaig0e1hd` (`barcode_id`),
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FKb0hsf61m71mqpyat0v1crkwgu` (`category_manager_id`),
  KEY `FKdndms3j250e8gl6f3vicuc081` (`price_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,11273721,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 1','15ml','CS Company Ltd| Auckland.','no','no',139573,'RANGED',10883,'D',1,1,1,3,1),(2,11273722,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 2','15ml','CS Company Ltd| Auckland.','no','no',139574,'RANGED',10883,'D',2,1,1,3,1),(3,11273723,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 3','15ml','CS Company Ltd| Auckland.','no','no',139575,'RANGED',10883,'D',3,1,1,3,1),(4,11273724,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 4','15ml','CS Company Ltd| Auckland.','no','no',139576,'RANGED',10883,'D',1,1,1,3,1),(5,11273725,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 5','15ml','CS Company Ltd| Auckland.','no','no',139577,'RANGED',10883,'D',2,1,1,3,1),(6,11273726,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 6','15ml','CS Company Ltd| Auckland.','no','no',139578,'RANGED',10883,'D',3,1,1,3,1),(7,11273727,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 7','15ml','CS Company Ltd| Auckland.','no','no',139579,'RANGED',10883,'D',1,1,1,3,1),(8,11273728,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 8','15ml','CS Company Ltd| Auckland.','no','no',139580,'RANGED',10883,'D',2,1,1,3,1),(9,11273729,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 9','15ml','CS Company Ltd| Auckland.','no','no',139581,'RANGED',10883,'D',3,1,1,3,1),(10,11273730,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 10','15ml','CS Company Ltd| Auckland.','no','no',139582,'RANGED',10883,'D',1,1,1,3,1),(11,11273731,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 11','15ml','CS Company Ltd| Auckland.','no','no',139583,'RANGED',10883,'D',2,1,1,3,1),(12,11273732,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 12','15ml','CS Company Ltd| Auckland.','no','no',139584,'RANGED',10883,'D',3,1,1,3,1),(13,11273733,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 13','15ml','CS Company Ltd| Auckland.','no','no',139585,'RANGED',10883,'D',1,1,1,3,1),(14,11273734,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 14','15ml','CS Company Ltd| Auckland.','no','no',139586,'RANGED',10883,'D',2,1,1,3,1),(15,11273735,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 15','15ml','CS Company Ltd| Auckland.','no','no',139587,'RANGED',10883,'D',3,1,1,3,1),(16,11273736,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 16','15ml','CS Company Ltd| Auckland.','no','no',139588,'RANGED',10883,'D',1,1,1,3,1),(17,11273737,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 17','15ml','CS Company Ltd| Auckland.','no','no',139589,'RANGED',10883,'D',2,1,1,3,1),(18,11273738,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 18','15ml','CS Company Ltd| Auckland.','no','no',139590,'RANGED',10883,'D',3,1,1,3,1),(19,11273739,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 19','15ml','CS Company Ltd| Auckland.','no','no',139591,'RANGED',10883,'D',1,1,1,3,1),(20,11273740,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 20','15ml','CS Company Ltd| Auckland.','no','no',139592,'RANGED',10883,'D',2,1,1,3,1),(21,11273741,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 21','15ml','CS Company Ltd| Auckland.','no','no',139593,'RANGED',10883,'D',3,1,1,3,1),(22,11273742,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 22','15ml','CS Company Ltd| Auckland.','no','no',139594,'RANGED',10883,'D',1,1,1,3,1),(23,11273743,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 23','15ml','CS Company Ltd| Auckland.','no','no',139595,'RANGED',10883,'D',2,1,1,3,1),(24,11273744,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 24','15ml','CS Company Ltd| Auckland.','no','no',139596,'RANGED',10883,'D',3,1,1,3,1),(25,11273745,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 25','15ml','CS Company Ltd| Auckland.','no','no',139597,'RANGED',10883,'D',1,1,1,3,1),(26,11273746,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 26','15ml','CS Company Ltd| Auckland.','no','no',139598,'RANGED',10883,'D',2,1,1,3,1),(27,11273747,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','NASAL DECONGESTANTS 27','15ml','CS Company Ltd| Auckland.','no','no',139599,'RANGED',10883,'D',3,1,1,3,1),(28,11273748,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','NASAL DECONGESTANTS 28','15ml','CS Company Ltd| Auckland.','no','no',139600,'RANGED',10883,'D',1,1,1,3,1),(29,11273749,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','NASAL DECONGESTANTS 29','15ml','CS Company Ltd| Auckland.','no','no',139601,'RANGED',10883,'D',2,1,1,3,1),(30,11273750,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 1','15ml','CS Company Ltd| Auckland.','no','no',139602,'RANGED',10883,'D',3,2,1,3,1),(31,11273751,0,'461690B6-A159-46D6-A0EA-A221615192AE','OPI NL Peru Don\'t Toot My Flute','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 2','15ml','CS Company Ltd| Auckland.','no','no',139603,'RANGED',10883,'D',1,2,1,3,1),(32,11273752,0,'4A157320-C07C-422A-A115-ED9734D26DA9','OPI NL Peru Grandma Kissed a Gaucho','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 3','15ml','CS Company Ltd| Auckland.','no','no',139604,'RANGED',10883,'D',2,2,1,3,1),(33,11273753,0,'6F96DF88-C10B-44D0-9525-3C70BBC791DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 4','15ml','CS Company Ltd| Auckland.','no','no',139605,'RANGED',10883,'D',3,2,1,3,1),(34,11273754,0,'6F96DF88-C10B-44D0-9525-3C70BBC792DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 5','15ml','CS Company Ltd| Auckland.','no','no',139606,'RANGED',10883,'D',3,2,1,3,1),(35,11273755,0,'6F96DF88-C10B-44D0-9525-3C70BBC793DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 6','15ml','CS Company Ltd| Auckland.','no','no',139607,'RANGED',10883,'D',3,2,1,3,1),(36,11273756,0,'6F96DF88-C10B-44D0-9525-3C70BBC794DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 7','15ml','CS Company Ltd| Auckland.','no','no',139608,'RANGED',10883,'D',3,2,1,3,1),(37,11273757,0,'6F96DF88-C10B-44D0-9525-3C70BBC795DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 8','15ml','CS Company Ltd| Auckland.','no','no',139609,'RANGED',10883,'D',3,2,1,3,1),(38,11273758,0,'6F96DF88-C10B-44D0-9525-3C70BBC796DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 9','15ml','CS Company Ltd| Auckland.','no','no',139610,'RANGED',10883,'D',3,2,1,3,1),(39,11273759,0,'6F96DF88-C10B-44D0-9525-3C70BBC797DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','LOZENGES/SORE THROAT 10','15ml','CS Company Ltd| Auckland.','no','no',139611,'RANGED',10883,'D',3,2,1,3,1),(40,11273760,0,'6F96DF88-C10B-44D0-9525-3C70BBC798DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','HYDRALYTE 1','15ml','CS Company Ltd| Auckland.','no','no',139612,'RANGED',10883,'D',3,2,1,3,1),(41,11273761,0,'6F96DF88-C10B-44D0-9525-3C70BBC799DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','HYDRALYTE 2','15ml','CS Company Ltd| Auckland.','no','no',139613,'RANGED',10883,'D',3,2,1,3,1),(42,11273762,0,'6F96DF88-C10B-44D0-9525-3C70BBC800DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','HYDRALYTE 3','15ml','CS Company Ltd| Auckland.','no','no',139614,'RANGED',10883,'D',3,3,1,3,1),(43,11273763,0,'6F96DF88-C10B-44D0-9525-3C70BBC801DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','HYDRALYTE 4','15ml','CS Company Ltd| Auckland.','no','no',139615,'RANGED',10883,'D',3,3,1,3,1),(44,11273764,0,'6F96DF88-C10B-44D0-9525-3C70BBC802DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','HYDRALYTE 5','15ml','CS Company Ltd| Auckland.','no','no',139616,'RANGED',10883,'D',3,3,1,3,1),(45,11273765,0,'6F96DF88-C10B-44D0-9525-3C70BBC803DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','VITAMINS1','15ml','CS Company Ltd| Auckland.','no','no',139617,'RANGED',10883,'D',3,3,1,3,1),(46,11273766,0,'6F96DF88-C10B-44D0-9525-3C70BBC804DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','VITAMINS2','15ml','CS Company Ltd| Auckland.','no','no',139618,'RANGED',10883,'D',3,3,1,3,1),(47,11273767,0,'6F96DF88-C10B-44D0-9525-3C70BBC805DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','VITAMINS3','15ml','CS Company Ltd| Auckland.','no','no',139619,'RANGED',10883,'D',3,3,1,3,1),(48,11273768,0,'6F96DF88-C10B-44D0-9525-3C70BBC806DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','VITAMINS4','15ml','CS Company Ltd| Auckland.','no','no',139620,'RANGED',10883,'D',3,3,1,3,1),(49,11273769,0,'6F96DF88-C10B-44D0-9525-3C70BBC807DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','Cold Lotion 1','15ml','CS Company Ltd| Auckland.','no','no',139621,'RANGED',10883,'D',3,4,1,3,1),(50,11273770,0,'6F96DF88-C10B-44D0-9525-3C70BBC808DB','OPI NL Peru Machu Peach-u','O.P.I','n/a','n/a','Cold Lotion 2','15ml','CS Company Ltd| Auckland.','no','no',139622,'RANGED',10883,'D',3,4,1,3,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo`
--

DROP TABLE IF EXISTS `promo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promo` (
  `id` bigint(20) NOT NULL,
  `gxh_id` bigint(20) DEFAULT NULL,
  `banner` varchar(50) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `dollar_value` float DEFAULT NULL,
  `dual_mailer_number` varchar(5) DEFAULT NULL,
  `living_reward` varchar(50) DEFAULT NULL,
  `mailer_id` int(11) DEFAULT NULL,
  `member_promo_cost` float DEFAULT NULL,
  `montly_dual_mailer_budget` int(11) DEFAULT NULL,
  `promo_cost` float DEFAULT NULL,
  `promorrp` varchar(50) DEFAULT NULL,
  `rate_card_code` varchar(5) DEFAULT NULL,
  `rebate` varchar(50) DEFAULT NULL,
  `space_allocation` int(11) DEFAULT NULL,
  `spend_difference` bigint(20) DEFAULT NULL,
  `supplier_yearly_budget` varchar(255) DEFAULT NULL,
  `total_spend_by_supplier` bigint(20) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `vendor_code` bigint(20) DEFAULT NULL,
  `yearly_ratecard_spend` int(11) DEFAULT NULL,
  `category_manager_id` bigint(20) DEFAULT NULL,
  `offercallout_id` bigint(20) DEFAULT NULL,
  `promogroup_id` bigint(20) DEFAULT NULL,
  `promomechic_id` bigint(20) DEFAULT NULL,
  `ratecard_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24wosuknq4v18byimem5wfl90` (`category_manager_id`),
  KEY `FK4b6ymh1qw3dc719me1yme77dd` (`offercallout_id`),
  KEY `FKbr4u2b80cnm43rneaseqfu4ik` (`promogroup_id`),
  KEY `FKsn1xr703udj5f97hnfok8i6fm` (`promomechic_id`),
  KEY `FKn383s8ktr6yrislf1dn15y7xt` (`ratecard_id`),
  KEY `FKog071pofwscjvrt8aqnbhghpj` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo`
--

LOCK TABLES `promo` WRITE;
/*!40000 ALTER TABLE `promo` DISABLE KEYS */;
/*!40000 ALTER TABLE `promo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo_comments`
--

DROP TABLE IF EXISTS `promo_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promo_comments` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `promotion_id` bigint(20) NOT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7fgounjlkxc59jnnxlf6qtin6` (`manager_id`),
  KEY `FKp94eraveudxtan7cl9slhidsy` (`promotion_id`),
  KEY `FK16grlolxa5uus0ud2tk7t6pr2` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo_comments`
--

LOCK TABLES `promo_comments` WRITE;
/*!40000 ALTER TABLE `promo_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `promo_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo_group`
--

DROP TABLE IF EXISTS `promo_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promo_group` (
  `id` bigint(20) NOT NULL,
  `gxhid` bigint(20) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo_group`
--

LOCK TABLES `promo_group` WRITE;
/*!40000 ALTER TABLE `promo_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `promo_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo_mechanic`
--

DROP TABLE IF EXISTS `promo_mechanic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promo_mechanic` (
  `id` bigint(20) NOT NULL,
  `max_product_allocation` int(11) DEFAULT NULL,
  `max_tile_allocation` int(11) DEFAULT NULL,
  `min_tile_allocation` int(11) DEFAULT NULL,
  `xnash` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo_mechanic`
--

LOCK TABLES `promo_mechanic` WRITE;
/*!40000 ALTER TABLE `promo_mechanic` DISABLE KEYS */;
INSERT INTO `promo_mechanic` VALUES (1,3,3,0,0),(2,2,2,0,2),(3,12,12,9,0),(4,6,6,3,0),(5,2,2,0,0),(6,1,1,0,0),(7,0,0,0,0);
/*!40000 ALTER TABLE `promo_mechanic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `year` date DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeljt1teoxotnnk9lty3ltskvm` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (1,'2018-01-01 00:00:00','ACTIVE','2018-01-01',1),(2,'2018-01-01 00:00:00','ACTIVE','2018-01-01',2);
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_level_ratecard`
--

DROP TABLE IF EXISTS `promotion_level_ratecard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_level_ratecard` (
  `id` bigint(20) NOT NULL,
  `dualmailer_id` bigint(20) DEFAULT NULL,
  `promo_id` bigint(20) DEFAULT NULL,
  `ratecard_id` bigint(20) DEFAULT NULL,
  `tiles_selected` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_level_ratecard`
--

LOCK TABLES `promotion_level_ratecard` WRITE;
/*!40000 ALTER TABLE `promotion_level_ratecard` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion_level_ratecard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_level_sku`
--

DROP TABLE IF EXISTS `promotion_level_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_level_sku` (
  `id` bigint(20) NOT NULL,
  `dualmailer_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `promo_id` bigint(20) DEFAULT NULL,
  `promo_count` int(11) DEFAULT NULL,
  `promotion_name` varchar(255) DEFAULT NULL,
  `promotion_type` varchar(255) DEFAULT NULL,
  `ratecard_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_level_sku`
--

LOCK TABLES `promotion_level_sku` WRITE;
/*!40000 ALTER TABLE `promotion_level_sku` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion_level_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratecard`
--

DROP TABLE IF EXISTS `ratecard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratecard` (
  `id` bigint(20) NOT NULL,
  `code` varchar(4) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `rc_dollar` float DEFAULT NULL,
  `promo_mechanic_id` bigint(20) DEFAULT NULL,
  `promotion_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmca1q1dj5x90127yl6tb28w6p` (`promo_mechanic_id`),
  KEY `FK21v1hbosdwpjyrh61hlen7tq6` (`promotion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratecard`
--

LOCK TABLES `ratecard` WRITE;
/*!40000 ALTER TABLE `ratecard` DISABLE KEYS */;
INSERT INTO `ratecard` VALUES (1,'DC1','Premier Plus Package',500,1,1),(2,'DC2','Premier Package',450,1,1),(3,'DC3','Supplier Stands',400,2,1),(4,'DC4','Unichem Only Eventing',350,1,1),(5,'DC5','Gondola Fins',300,2,1),(6,'DC6','Impulse Bins',250,5,1),(7,'DC7','Counter Units',200,5,1),(8,'DC8','Full Page',150,3,1),(9,'DC9','Half Page',100,4,1),(10,'DC10','Triple tile Range / GWP Offer',75,1,1),(11,'DC11','Double tile Range / GWP Offer',70,5,1),(12,'DC12','Standard Tile',60,1,1),(13,'DC13','NASH',50,7,1),(14,'DC1','Premier Plus Package',500,1,2),(15,'DC2','Premier Package',450,1,2),(16,'DC3','Supplier Stands',400,2,2),(17,'DC4','Unichem Only Eventing',350,1,2),(18,'DC5','Gondola Fins',300,2,2),(19,'DC6','Impulse Bins',250,5,2),(20,'DC7','Counter Units',200,5,2),(21,'DC8','Full Page',150,3,2),(22,'DC9','Half Page',100,4,2),(23,'DC10','Triple tile Range / GWP Offer',75,1,2),(24,'DC11','Double tile Range / GWP Offer',70,5,2),(25,'DC12','Standard Tile',60,1,2),(26,'DC13','NASH',50,7,2);
/*!40000 ALTER TABLE `ratecard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'VENDOR'),(3,'CM');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL,
  `vendor_name` varchar(100) DEFAULT NULL,
  `vendor_ax_code` bigint(20) DEFAULT NULL,
  `supplier_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfi5efmtyy1r3qx75r74m3cgnb` (`supplier_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Johnson and Johnson',10883,2),(2,'GSK',10883,4);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_promotion_budget`
--

DROP TABLE IF EXISTS `supplier_promotion_budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_promotion_budget` (
  `id` bigint(20) NOT NULL,
  `promotion_budget_allocated` bigint(20) DEFAULT NULL,
  `promotion_budget` bigint(20) DEFAULT NULL,
  `promotion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8hf47alcede1t1fn1513bf25e` (`promotion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_promotion_budget`
--

LOCK TABLES `supplier_promotion_budget` WRITE;
/*!40000 ALTER TABLE `supplier_promotion_budget` DISABLE KEYS */;
INSERT INTO `supplier_promotion_budget` VALUES (1,10000,0,1),(2,20000,0,2);
/*!40000 ALTER TABLE `supplier_promotion_budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tile`
--

DROP TABLE IF EXISTS `tile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tile` (
  `id` bigint(20) NOT NULL,
  `available_tiles` int(11) DEFAULT NULL,
  `catalogue_number` bigint(20) DEFAULT NULL,
  `loyalty_tiles` int(11) DEFAULT NULL,
  `nos_pages` int(11) DEFAULT NULL,
  `professional_service_tiles` int(11) DEFAULT NULL,
  `year_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tile`
--

LOCK TABLES `tile` WRITE;
/*!40000 ALTER TABLE `tile` DISABLE KEYS */;
/*!40000 ALTER TABLE `tile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_contact`
--

DROP TABLE IF EXISTS `user_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_contact` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKigqfory4r46pqd0sl4csnwp72` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_contact`
--

LOCK TABLES `user_contact` WRITE;
/*!40000 ALTER TABLE `user_contact` DISABLE KEYS */;
INSERT INTO `user_contact` VALUES (1,'admin','user',9632325588,1),(2,'manager','user',9632325588,3),(3,'johnson','user',9632325588,2),(4,'gsk','user',9632325588,4);
/*!40000 ALTER TABLE `user_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4qu1gr772nnf6ve5af002rwya` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'admin@test.com','$2a$10$CtT8Q7hyLJlL5oCNfUbbzOa/uMSLx6ENv5GSoIKq9oe9LQJSuNoCq',1),(2,1,'johnson@test.com','$2a$10$CtT8Q7hyLJlL5oCNfUbbzOa/uMSLx6ENv5GSoIKq9oe9LQJSuNoCq',2),(3,1,'cmanager@test.com','$2a$10$CtT8Q7hyLJlL5oCNfUbbzOa/uMSLx6ENv5GSoIKq9oe9LQJSuNoCq',3),(4,1,'gsk@test.com','$2a$10$CtT8Q7hyLJlL5oCNfUbbzOa/uMSLx6ENv5GSoIKq9oe9LQJSuNoCq',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-25 20:44:22
