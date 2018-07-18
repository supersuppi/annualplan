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
  `brand_group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKawnm3n0n8xym7dw0piidilycs` (`brand_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
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
  `gxh_id` int(11) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `discount_amount` bigint(20) DEFAULT NULL,
  `dollar_value` int(11) DEFAULT NULL,
  `dual_mailer_number` varchar(255) DEFAULT NULL,
  `living_reward` varchar(255) DEFAULT NULL,
  `mailer_id` int(11) DEFAULT NULL,
  `member_promo_cost` bigint(20) DEFAULT NULL,
  `montly_dual_mailer_budget` int(11) DEFAULT NULL,
  `promo_cost` bigint(20) DEFAULT NULL,
  `promorrp` varchar(255) DEFAULT NULL,
  `rate_card_code` varchar(255) DEFAULT NULL,
  `ratecard_id` int(11) DEFAULT NULL,
  `rebate` varchar(255) DEFAULT NULL,
  `space_allocation` int(11) DEFAULT NULL,
  `spend_difference` bigint(20) DEFAULT NULL,
  `supplier_yearly_budget` varchar(255) DEFAULT NULL,
  `total_spend_by_supplier` bigint(20) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `vendor_code` varchar(255) DEFAULT NULL,
  `yearly_ratecard_spend` int(11) DEFAULT NULL,
  `category_manager_id` bigint(20) DEFAULT NULL,
  `offercallout_id` bigint(20) DEFAULT NULL,
  `promogroup_id` bigint(20) DEFAULT NULL,
  `promomechic_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24wosuknq4v18byimem5wfl90` (`category_manager_id`),
  KEY `FK4b6ymh1qw3dc719me1yme77dd` (`offercallout_id`),
  KEY `FKbr4u2b80cnm43rneaseqfu4ik` (`promogroup_id`),
  KEY `FKsn1xr703udj5f97hnfok8i6fm` (`promomechic_id`),
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
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo_mechanic`
--

LOCK TABLES `promo_mechanic` WRITE;
/*!40000 ALTER TABLE `promo_mechanic` DISABLE KEYS */;
/*!40000 ALTER TABLE `promo_mechanic` ENABLE KEYS */;
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
  `max_tile_allocation` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `rc_dollar` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratecard`
--

LOCK TABLES `ratecard` WRITE;
/*!40000 ALTER TABLE `ratecard` DISABLE KEYS */;
INSERT INTO `ratecard` VALUES (1,'DC1',3,'Premier Plus Package',500),(2,'DC2',3,'Premier Package',450),(3,'DC3',4,'Supplier Stands',400),(4,'DC4',3,'Unichem Only Eventing',350),(5,'DC5',3,'Gondola Fins',300),(6,'DC6',3,'Impulse Bins',250),(7,'DC7',3,'Counter Units',200),(8,'DC8',3,'Full Page',150),(9,'DC9',3,'Half Page',100),(10,'DC10',3,'Triple tile Range / GWP Offer',75),(11,'DC11',3,'Double tile Range / GWP Offer',70),(12,'DC12',3,'Standard Tile',60),(13,'DC12',3,'NASH',50);
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
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
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
  `supplier` varchar(100) DEFAULT NULL,
  `supplier_captain` varchar(50) DEFAULT NULL,
  `vendor_ax_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
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
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
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
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-18 21:14:01
