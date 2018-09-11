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
-- Table structure for table `annual_promotion`
--

DROP TABLE IF EXISTS `annual_promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `annual_promotion` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `promotion_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKljp5q6d8hi8k2oo6rk3bounm5` (`created_by`),
  KEY `FK6q8w67b0gvmxslw2212taw6f8` (`modified_by`),
  KEY `FK6mjw92hve9l9lknfnb3lvh87r` (`promotion_id`),
  KEY `FK9pt4o6u3yq41fsrn6nq76qt7l` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annual_promotion`
--

LOCK TABLES `annual_promotion` WRITE;
/*!40000 ALTER TABLE `annual_promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `annual_promotion` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `brand` VALUES (1,'Listerine Brand',1),(2,'Nicorette Brand',1),(3,'Mylanta Brand',1),(4,'Olay Brand',1),(5,'Vicks - Diplomat Brand',1);
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
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `promotion_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKittd2e5m5fasuxnkpvvjqyfrs` (`promotion_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dual_mailer`
--

LOCK TABLES `dual_mailer` WRITE;
/*!40000 ALTER TABLE `dual_mailer` DISABLE KEYS */;
INSERT INTO `dual_mailer` VALUES (406,'DM1','2018-06-02','2018-04-02',402),(410,'DM2','2018-07-01','2018-06-01',407),(411,'DM1','2018-04-01','2018-02-01',407);
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
INSERT INTO `hibernate_sequence` VALUES (412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412),(412);
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
INSERT INTO `product` VALUES (1,11273721,2125633,'461690B6-A159-46D6-A0EA-A221615192AE','Listerine Pocket Freshburst Strips 72','Listerine','n/a','n/a','Pocket Freshburst Strips','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139573,'RANGED',10883,'D',1,1,1,3,1),(2,11273722,2125634,'461690B6-A159-46D6-A0EA-A221615192AE','LISTERINE Total Care Bonus Pk 500ml','Listerine','n/a','n/a','Total Care Bonus Pack','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139574,'RANGED',10883,'D',1,1,1,3,1),(3,11273723,2125635,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Chewing Gum 2mg Classic 105s','Nicorette','n/a','n/a','Gum Classic 2mg','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139575,'RANGED',10883,'D',1,2,1,3,1),(4,11273724,2125636,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Chewing Gum 2mg Icy Mint 105s','Nicorette','n/a','n/a','Icy Mint Gum 2mg','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139576,'RANGED',10883,'D',1,2,1,3,1),(5,11273725,2125637,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Chewing Gum 4mg Icy Mint 105s','Nicorette','n/a','n/a','Icy Mint Gum 4mg Pieces','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139577,'RANGED',10883,'D',1,2,1,3,1),(6,11273726,2125638,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Quick Mist Mouth Spray 150 Freshmint 13.2ml','Nicorette','n/a','n/a','Quickmist Mouth Spray','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139578,'RANGED',10883,'D',1,2,1,3,1),(7,11273727,2125639,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Invisipatch Step 3 16hr 25mg 7s','Nicorette','n/a','n/a','Invisipatch 25mg','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139579,'RANGED',10883,'D',1,2,1,3,1),(8,11273728,2125640,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Invisipatch Step 2 16hr 15mg 7s','Nicorette','n/a','n/a','Invisipatch 15mg','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139580,'RANGED',10883,'D',1,2,1,3,1),(9,11273729,2125641,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Invisipatch Step 1 16hr 10mg 7s','Nicorette','n/a','n/a','Invisipatch 10mg','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139581,'RANGED',10883,'D',1,2,1,3,1),(10,11273730,2125642,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Cooldrops Lozenges 2mg 20s','Nicorette','n/a','n/a','Cooldrops 2mg','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139582,'RANGED',10883,'D',1,2,1,3,1),(11,11273731,2125643,'461690B6-A159-46D6-A0EA-A221615192AE','Nicorette Cooldrops Lozenges 4mg 20s','Nicorette','n/a','n/a','Cooldrops 4mg','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139583,'RANGED',10883,'D',1,2,1,3,1),(12,11273732,2125644,'461690B6-A159-46D6-A0EA-A221615192AE','Mylanta Antacid Double Strength Liquid 500ml','Mylanta','n/a','n/a','Double Strength Liquid','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139584,'RANGED',10883,'D',1,3,1,3,1),(13,11273733,2125645,'461690B6-A159-46D6-A0EA-A221615192AE','Mylanta Antacid Liquid Original 500ml','Mylanta','n/a','n/a','Original Liquid','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139585,'RANGED',10883,'D',1,3,1,3,1),(14,11273734,2125646,'461690B6-A159-46D6-A0EA-A221615192AE','Mylanta Antacid Double Strength Liquid 200ml','Mylanta','n/a','n/a','Double Strength Liquid','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139586,'RANGED',10883,'D',1,3,1,3,1),(15,11273735,2125647,'461690B6-A159-46D6-A0EA-A221615192AE','Mylanta 2go Antacid Original Chewable Tablets Lemon Mint 24s','Mylanta','n/a','n/a','2Go Original Chewable Tablets Lemon Mint','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139587,'RANGED',10883,'D',1,3,1,3,1),(16,11273736,2125648,'461690B6-A159-46D6-A0EA-A221615192AE','Mylanta 2Go Antacid Double Strength Chewable Tablets Lemon Mint 48s','Mylanta','n/a','n/a','2Go Antacid Double Strength Chewable Tablets Lemon Mint','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139588,'RANGED',10883,'D',1,3,1,3,1),(17,11273737,2125649,'461690B6-A159-46D6-A0EA-A221615192AE','Mylanta 2Go Double Strength Chewable Tablets Lemon Mint 24s','Mylanta','n/a','n/a','2Go Double Strength Chewable Tablets Lemon Mint','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139589,'RANGED',10883,'D',1,3,1,3,1),(18,11273738,2125650,'461690B6-A159-46D6-A0EA-A221615192AE','Mylanta 2Go Original Tablets 48s','Mylanta','n/a','n/a','2Go Original Tablets','15ml','Johnson & Johnson (New Zealand) Ltd Auckland.','no','no',139590,'RANGED',10883,'D',1,3,1,3,1),(19,11273739,2125651,'461690B6-A159-46D6-A0EA-A221615192AE','Olay Moisturising Lotion 150ml','Olay','n/a','n/a','Olay Moisturising Lotion 150ml','150ml','DIPLOMAT NEW ZEALAND LTD','no','no',139591,'RANGED',18672,'D',1,4,1,3,1),(20,11273740,2125652,'461690B6-A159-46D6-A0EA-A221615192AE','Olay Moisturising Cream Sensitive Skin 100g','Olay','n/a','n/a','Olay Sensitive Moist Cream 100gm','100g','DIPLOMAT NEW ZEALAND LTD','no','no',139592,'RANGED',18672,'D',1,4,1,3,1),(21,11273741,2125653,'461690B6-A159-46D6-A0EA-A221615192AE','Olay Regenerist Revitalising Night Cream 50g','Olay','n/a','n/a','Olay Regenerist Night Cream 50g','50g','DIPLOMAT NEW ZEALAND LTD','no','no',139593,'RANGED',18672,'D',1,4,1,3,1),(22,11273742,2125654,'461690B6-A159-46D6-A0EA-A221615192AE','Olay Complete UV Max Defense Lotion 75ml','Olay','n/a','n/a','Complete UV Max Defense Lotion 75ml','75ml','DIPLOMAT NEW ZEALAND LTD','no','no',139594,'RANGED',18672,'D',1,4,1,3,1),(23,11273743,2125655,'461690B6-A159-46D6-A0EA-A221615192AE','Olay Complete UV Lotion Combination Oily 150ml','Olay','n/a','n/a','Complete UV Lotion Combination Oily 150ml','150ml','DIPLOMAT NEW ZEALAND LTD','no','no',139595,'RANGED',18672,'D',1,4,1,3,1),(24,11273744,2125656,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks Inhaler 0.5ml','Vicks','n/a','n/a','Inhaler','0.5ml','DIPLOMAT NEW ZEALAND LTD','no','no',139596,'RANGED',18672,'D',1,5,1,3,1),(25,11273745,2125657,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks VapoRub 50g','Vicks','n/a','n/a','VapoRub','50g','DIPLOMAT NEW ZEALAND LTD','no','no',139597,'RANGED',18672,'D',1,5,1,3,1),(26,11273746,2125658,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks Baby Balsam 50g','Vicks','n/a','n/a','Baby Balsam','50g','DIPLOMAT NEW ZEALAND LTD','no','no',139598,'RANGED',18672,'D',1,5,1,3,1),(27,11273747,2125659,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks VapoDrops Butter Menthol Lozenges 24s','Vicks','n/a','n/a','VapoDrops Butter Menthol Lozenges','24s','DIPLOMAT NEW ZEALAND LTD','no','no',139599,'RANGED',18672,'D',1,5,1,3,1),(28,11273748,2125660,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks VapoDrops Original Menthol Lozenges 24s','Vicks','n/a','n/a','VapoDrops Original Menthol Lozenges','24s','DIPLOMAT NEW ZEALAND LTD','no','no',139600,'RANGED',18672,'D',1,5,1,3,1),(29,11273749,2125661,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks Sinex Nasal Decongestant 15ml','Vicks','n/a','n/a','Sinex Nasal Decongestant','15ml','DIPLOMAT NEW ZEALAND LTD','no','no',139601,'RANGED',18672,'D',1,5,1,3,1),(30,11273750,2125662,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks Sinex Extra Fresh Nasal Decongestant 15ml','Vicks','n/a','n/a','Sinex Extra Fresh Nasal Decongestant','15ml','DIPLOMAT NEW ZEALAND LTD','no','no',139602,'RANGED',18672,'D',1,5,1,3,1),(31,11273751,2125663,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks VapoRub Vaporizing Ointment 100g','Vicks','n/a','n/a','VapoRub','100g','DIPLOMAT NEW ZEALAND LTD','no','no',139603,'RANGED',18672,'D',1,5,1,3,1),(32,11273752,2125664,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks VapoDrops Cooling Peppermint Lozenges 24s','Vicks','n/a','n/a','VapoDrops Cooling Peppermint Lozenges','24s','DIPLOMAT NEW ZEALAND LTD','no','no',139604,'RANGED',18672,'D',1,5,1,3,1),(33,11273753,2125665,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks VapoNaturals Lemon Menthol Lozenges 19s','Vicks','n/a','n/a','VapoNaturals Lemon Menthol Lozenges','19s','DIPLOMAT NEW ZEALAND LTD','no','no',139605,'RANGED',18672,'D',1,5,1,3,1),(34,11273754,2125666,'461690B6-A159-46D6-A0EA-A221615192AE','Vicks VapoNaturals Honey Lozenges 19s','Vicks','n/a','n/a','VapoNaturals Honey Lozenges','19s','DIPLOMAT NEW ZEALAND LTD','no','no',139606,'RANGED',18672,'D',1,5,1,3,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
  `admin_id` bigint(20) DEFAULT NULL,
  `annualpromotion_id` bigint(20) NOT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl97yeu70cw66xi1xc8ax8r0vl` (`admin_id`),
  KEY `FKnatnb1tyhru19jbfaphwq8r17` (`annualpromotion_id`),
  KEY `FK7fgounjlkxc59jnnxlf6qtin6` (`manager_id`),
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
  `modified_at` datetime DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKghoegpt2btpbr7xjsxhc4ab9g` (`created_by`),
  KEY `FK6tnvtq73tcskcq6kcjhgl681f` (`modified_by`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (402,'2018-09-09 19:58:22','2018-09-09 19:58:22','Winter Promotion','ACTIVE',1,1),(407,'2018-09-09 20:05:30','2018-09-09 20:05:30','Freebees','DRAFT',1,1);
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
  `annualpromo_id` bigint(20) DEFAULT NULL,
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
INSERT INTO `ratecard` VALUES (403,'RC3','Basic',150,NULL,402),(404,'RC2','Standard',300,NULL,402),(405,'RC1','Premium',500,NULL,402),(408,'RC2','Quartly',400,NULL,407),(409,'RC1','Monthly',200,NULL,407);
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
INSERT INTO `supplier` VALUES (1,'Johnson and Johnson',10883,2),(2,'Diplomat',18672,4);
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
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8hf47alcede1t1fn1513bf25e` (`promotion_id`),
  KEY `FK1wqkn0bg2lkbt9a35r0b046av` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_promotion_budget`
--

LOCK TABLES `supplier_promotion_budget` WRITE;
/*!40000 ALTER TABLE `supplier_promotion_budget` DISABLE KEYS */;
INSERT INTO `supplier_promotion_budget` VALUES (1,10000,0,0,1),(2,20000,0,0,2);
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
INSERT INTO `user_contact` VALUES (1,'admin','user',9632325588,1),(2,'manager','user',9632325588,3),(3,'johnson','johnson',9632325588,2),(4,'Diplomat','user',9632325588,4);
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
INSERT INTO `users` VALUES (1,1,'admin@test.com','$2a$10$CtT8Q7hyLJlL5oCNfUbbzOa/uMSLx6ENv5GSoIKq9oe9LQJSuNoCq',1),(2,1,'johnson@test.com','$2a$10$CtT8Q7hyLJlL5oCNfUbbzOa/uMSLx6ENv5GSoIKq9oe9LQJSuNoCq',2),(3,1,'cmanager@test.com','$2a$10$CtT8Q7hyLJlL5oCNfUbbzOa/uMSLx6ENv5GSoIKq9oe9LQJSuNoCq',3),(4,1,'diplomat@test.com','$2a$10$CtT8Q7hyLJlL5oCNfUbbzOa/uMSLx6ENv5GSoIKq9oe9LQJSuNoCq',2);
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

-- Dump completed on 2018-09-09 20:08:48
