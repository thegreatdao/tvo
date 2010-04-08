-- MySQL dump 10.13  Distrib 5.1.36, for Win32 (ia32)
--
-- Host: localhost    Database: tvo_web_repository_rev9
-- ------------------------------------------------------
-- Server version	5.1.36-community-log

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
-- Table structure for table `air_time`
--

DROP TABLE IF EXISTS `air_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `air_time` (
  `air_time_id` int(11) NOT NULL,
  `asset_root_id` int(11) DEFAULT NULL,
  `strand_id` varchar(45) DEFAULT NULL,
  `air_time_type` int(11) DEFAULT NULL,
  `air_time` datetime DEFAULT NULL,
  `strands_schedule_id` int(11) DEFAULT NULL,
  `is_repeat` bit(1) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`air_time_id`),
  KEY `fk_air_time_id` (`asset_root_id`),
  KEY `fk_strands_schedule_id` (`strands_schedule_id`),
  CONSTRAINT `fk_air_time_id` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_strands_schedule_id` FOREIGN KEY (`strands_schedule_id`) REFERENCES `strands_schedule` (`strands_schedule_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `air_time`
--

LOCK TABLES `air_time` WRITE;
/*!40000 ALTER TABLE `air_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `air_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `air_time_type`
--

DROP TABLE IF EXISTS `air_time_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `air_time_type` (
  `air_time_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`air_time_type_id`),
  KEY `fk_airtime_type` (`air_time_type_id`),
  CONSTRAINT `fk_airtime_type` FOREIGN KEY (`air_time_type_id`) REFERENCES `air_time` (`air_time_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `air_time_type`
--

LOCK TABLES `air_time_type` WRITE;
/*!40000 ALTER TABLE `air_time_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `air_time_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_article`
--

DROP TABLE IF EXISTS `asset_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_article` (
  `asset_article_id` int(11) NOT NULL,
  `asset_root_id` int(11) DEFAULT NULL,
  `teaser` varchar(255) DEFAULT NULL,
  `body` text,
  PRIMARY KEY (`asset_article_id`),
  KEY `fk_asset_article_id` (`asset_root_id`),
  CONSTRAINT `fk_asset_article_id` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_article`
--

LOCK TABLES `asset_article` WRITE;
/*!40000 ALTER TABLE `asset_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_attachment`
--

DROP TABLE IF EXISTS `asset_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_attachment` (
  `asset_attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_root_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mime_type` varchar(100) DEFAULT NULL,
  `url_location` text,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`asset_attachment_id`),
  KEY `fk_asset_attachment_id` (`asset_root_id`),
  CONSTRAINT `fk_asset_attachment_id` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_attachment`
--

LOCK TABLES `asset_attachment` WRITE;
/*!40000 ALTER TABLE `asset_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_blog`
--

DROP TABLE IF EXISTS `asset_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_blog` (
  `asset_blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_root_id` int(11) DEFAULT NULL,
  `body` text,
  `search` text,
  `rss_feed_name` text,
  PRIMARY KEY (`asset_blog_id`),
  KEY `fk_asset_blog` (`asset_root_id`),
  CONSTRAINT `fk_asset_blog` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_blog`
--

LOCK TABLES `asset_blog` WRITE;
/*!40000 ALTER TABLE `asset_blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_blog_post`
--

DROP TABLE IF EXISTS `asset_blog_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_blog_post` (
  `asset_blog_post_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_blog_id` int(11) DEFAULT NULL,
  `comment` text,
  `summary` text,
  `detail` text,
  `link` varchar(255) DEFAULT NULL,
  `sort_order` bit(1) DEFAULT NULL,
  PRIMARY KEY (`asset_blog_post_id`),
  KEY `fk_asset_blog_post` (`asset_blog_id`),
  CONSTRAINT `fk_asset_blog_post` FOREIGN KEY (`asset_blog_id`) REFERENCES `asset_blog` (`asset_blog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_blog_post`
--

LOCK TABLES `asset_blog_post` WRITE;
/*!40000 ALTER TABLE `asset_blog_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_blog_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_metadata`
--

DROP TABLE IF EXISTS `asset_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_metadata` (
  `asset_metadata_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_sub_id` int(11) DEFAULT NULL,
  `asset_root_id` int(11) DEFAULT NULL,
  `domain_name_id` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description_internet` text,
  `meta_tags` text,
  `revision` int(11) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`asset_metadata_id`),
  KEY `asset_meta_id` (`asset_root_id`),
  KEY `asset_meta_domain` (`domain_name_id`),
  KEY `asset_meta_sub_category` (`category_sub_id`),
  CONSTRAINT `asset_meta_domain` FOREIGN KEY (`domain_name_id`) REFERENCES `domain_name` (`domain_name_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `asset_meta_id` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `asset_meta_sub_category` FOREIGN KEY (`category_sub_id`) REFERENCES `category_sub` (`category_sub_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_metadata`
--

LOCK TABLES `asset_metadata` WRITE;
/*!40000 ALTER TABLE `asset_metadata` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_relation`
--

DROP TABLE IF EXISTS `asset_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_relation` (
  `asset_relation_id` int(11) NOT NULL,
  `asset_parent_id` int(11) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `asset_child_id` int(11) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`asset_relation_id`),
  KEY `fk_asset_parent` (`asset_parent_id`),
  KEY `fk_asset_child` (`asset_child_id`),
  CONSTRAINT `fk_asset_child` FOREIGN KEY (`asset_child_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_asset_parent` FOREIGN KEY (`asset_parent_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_relation`
--

LOCK TABLES `asset_relation` WRITE;
/*!40000 ALTER TABLE `asset_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_right`
--

DROP TABLE IF EXISTS `asset_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_right` (
  `asset_right_id` int(11) NOT NULL,
  `asset_root_id` int(11) DEFAULT NULL,
  `right_type_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` datetime DEFAULT NULL,
  `updated_by` datetime DEFAULT NULL,
  PRIMARY KEY (`asset_right_id`),
  KEY `fk_right_type` (`right_type_id`),
  KEY `fk_asset_rights` (`asset_root_id`),
  CONSTRAINT `fk_asset_rights` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_right_type` FOREIGN KEY (`right_type_id`) REFERENCES `right_type` (`right_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_right`
--

LOCK TABLES `asset_right` WRITE;
/*!40000 ALTER TABLE `asset_right` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_root`
--

DROP TABLE IF EXISTS `asset_root`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_root` (
  `asset_root_id` int(11) NOT NULL AUTO_INCREMENT,
  `telescope_asset_id` varchar(50) DEFAULT NULL,
  `telescope_record_id` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `source` varchar(100) NOT NULL,
  `asset_type` varchar(60) DEFAULT NULL,
  `description_internet` text,
  `description_short` varchar(255) DEFAULT NULL,
  `age_rating` char(2) DEFAULT NULL,
  `user_time_start` datetime NOT NULL,
  `user_time_end` datetime NOT NULL,
  `duration` time DEFAULT NULL,
  `geo_filter_id` int(11) DEFAULT NULL,
  `release_date` datetime NOT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`asset_root_id`),
  KEY `fk_geo_filter_id` (`geo_filter_id`),
  CONSTRAINT `fk_geo_filter_id` FOREIGN KEY (`geo_filter_id`) REFERENCES `geo_filter` (`geo_filter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_root`
--

LOCK TABLES `asset_root` WRITE;
/*!40000 ALTER TABLE `asset_root` DISABLE KEYS */;
INSERT INTO `asset_root` VALUES (150,'103056',1772940,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:25','0000-00-00 00:00:00','ron','ron'),(151,'103055',1772935,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:27','0000-00-00 00:00:00','ron','ron'),(152,'542315',1809556,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-21 16:58:52','2025-04-19 16:58:59',NULL,1,'2010-04-21 16:58:52','2010-04-06 13:43:27','0000-00-00 00:00:00','ron','ron'),(153,'542231',1771343,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2009-10-26 10:29:16','2014-12-03 00:00:00',NULL,1,'2009-10-26 10:29:16','2010-04-06 13:43:30','0000-00-00 00:00:00','ron','ron'),(154,'542231',1771343,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2009-10-26 10:29:16','2014-12-03 00:00:00',NULL,1,'2009-10-26 10:29:16','2010-04-06 13:43:31','0000-00-00 00:00:00','ron','ron'),(155,'238277_01073328_01085115',1817672,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 14:28:40','2100-04-05 14:28:46',NULL,1,'2010-04-05 14:28:40','2010-04-06 13:43:32','0000-00-00 00:00:00','ron','ron'),(156,'897653B',1758826,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-28 23:44:00','2013-11-30 00:00:00',NULL,1,'2010-04-28 23:44:00','2010-04-06 13:43:33','0000-00-00 00:00:00','ron','ron'),(157,'E20700D814863',1815736,'','OECA Video Element','VIDEO',NULL,NULL,NULL,'2010-03-29 17:47:54','2010-08-31 17:47:59',NULL,1,'2010-03-29 17:47:54','2010-04-06 13:43:33','0000-00-00 00:00:00','ron','ron'),(158,'103054',1772945,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:34','0000-00-00 00:00:00','ron','ron'),(159,'542313',1791368,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-03-04 00:00:00','2022-03-04 00:00:00',NULL,1,'2010-03-04 00:00:00','2010-04-06 13:43:34','0000-00-00 00:00:00','ron','ron'),(160,'238277_01245903_01270813',1817674,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 14:28:40','2100-04-05 14:28:46',NULL,1,'2010-04-05 14:28:40','2010-04-06 13:43:35','0000-00-00 00:00:00','ron','ron'),(161,'103061',1788504,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:36','0000-00-00 00:00:00','ron','ron'),(162,'991581',1806085,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-03-04 17:57:02','2020-03-29 17:57:06',NULL,1,'2010-03-04 17:57:02','2010-04-06 13:43:36','0000-00-00 00:00:00','ron','ron'),(163,'1792384',1814975,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-04-06 00:00:00','2099-04-06 00:00:00',NULL,1,'2010-04-06 00:00:00','2010-04-06 13:43:37','0000-00-00 00:00:00','ron','ron'),(164,'103067',1788519,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:37','0000-00-00 00:00:00','ron','ron'),(165,'838292',1812774,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 23:30:28','2029-03-31 11:44:45',NULL,1,'2010-04-05 23:30:28','2010-04-06 13:43:38','0000-00-00 00:00:00','ron','ron'),(166,'897653',1758829,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-28 23:44:00','2013-11-30 00:00:00',NULL,1,'2010-04-28 23:44:00','2010-04-06 13:43:39','0000-00-00 00:00:00','ron','ron'),(167,'749420',1799610,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-02-19 00:00:00','2012-02-17 00:00:00',NULL,1,'2010-02-19 00:00:00','2010-04-06 13:43:40','0000-00-00 00:00:00','ron','ron'),(168,'1814971',1817659,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-04-06 00:00:00','2099-04-06 00:00:00',NULL,1,'2010-04-06 00:00:00','2010-04-06 13:43:41','0000-00-00 00:00:00','ron','ron'),(169,'103063',1788507,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:41','0000-00-00 00:00:00','ron','ron'),(170,'103069',1786283,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:42','0000-00-00 00:00:00','ron','ron'),(171,'1803382',1804503,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-02-24 00:28:10','2099-02-24 00:28:12',NULL,1,'2010-02-24 00:28:10','2010-04-06 13:43:43','0000-00-00 00:00:00','ron','ron'),(172,'1803382',1804503,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-02-24 00:28:10','2099-02-24 00:28:12',NULL,1,'2010-02-24 00:28:10','2010-04-06 13:43:43','0000-00-00 00:00:00','ron','ron'),(173,'1803382',1804503,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-02-24 00:28:10','2099-02-24 00:28:12',NULL,1,'2010-02-24 00:28:10','2010-04-06 13:43:44','0000-00-00 00:00:00','ron','ron'),(174,'1803382',1804503,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-02-24 00:28:10','2099-02-24 00:28:12',NULL,1,'2010-02-24 00:28:10','2010-04-06 13:43:45','0000-00-00 00:00:00','ron','ron'),(175,'238277_01022307_01035825',1817669,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 14:28:40','2100-04-05 14:28:46',NULL,1,'2010-04-05 14:28:40','2010-04-06 13:43:46','0000-00-00 00:00:00','ron','ron'),(176,'542135',1780165,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-03-26 00:00:00','2022-01-29 00:00:00',NULL,1,'2010-03-26 00:00:00','2010-04-06 13:43:47','0000-00-00 00:00:00','ron','ron'),(177,'102923',1783184,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2009-12-11 00:00:00','2012-12-04 00:00:00',NULL,1,'2009-12-11 00:00:00','2010-04-06 13:43:48','0000-00-00 00:00:00','ron','ron'),(178,'102923',1783184,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2009-12-11 00:00:00','2012-12-04 00:00:00',NULL,1,'2009-12-11 00:00:00','2010-04-06 13:43:49','0000-00-00 00:00:00','ron','ron'),(179,'103062',1788510,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:50','0000-00-00 00:00:00','ron','ron'),(180,'238279',1816012,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2099-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:50','0000-00-00 00:00:00','ron','ron'),(181,'238279',1816012,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2099-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:51','0000-00-00 00:00:00','ron','ron'),(182,'103052',1773502,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:52','0000-00-00 00:00:00','ron','ron'),(183,'103053',1773845,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:52','0000-00-00 00:00:00','ron','ron'),(184,'238281',1811817,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-02 00:00:00','2099-01-27 00:00:00',NULL,1,'2010-04-02 00:00:00','2010-04-06 13:43:53','0000-00-00 00:00:00','ron','ron'),(185,'104049',1800962,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-03-02 15:40:12','2013-02-22 15:40:25',NULL,1,'2010-03-02 15:40:12','2010-04-06 13:43:54','0000-00-00 00:00:00','ron','ron'),(186,'103059',1772944,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:55','0000-00-00 00:00:00','ron','ron'),(187,'1814968',1816826,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-04-05 00:00:00','2099-04-05 00:00:00',NULL,1,'2010-04-05 00:00:00','2010-04-06 13:43:55','0000-00-00 00:00:00','ron','ron'),(188,'103057',1772943,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:56','0000-00-00 00:00:00','ron','ron'),(189,'009454',1807259,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-11 00:49:44','2010-06-30 00:49:55',NULL,1,'2010-04-11 00:49:44','2010-04-06 13:43:57','0000-00-00 00:00:00','ron','ron'),(190,'542146',1780180,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-03-26 00:00:00','2022-01-29 00:00:00',NULL,1,'2010-03-26 00:00:00','2010-04-06 13:43:58','0000-00-00 00:00:00','ron','ron'),(191,'103064',1788513,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:43:59','0000-00-00 00:00:00','ron','ron'),(192,'E20700D868712',1816953,'','OECA Video Element','VIDEO',NULL,NULL,NULL,'2010-03-29 17:47:54','2010-08-31 17:47:59',NULL,1,'2010-03-29 17:47:54','2010-04-06 13:43:59','0000-00-00 00:00:00','ron','ron'),(193,'991255',1779609,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2009-12-18 00:00:00','2011-12-07 00:00:00',NULL,1,'2009-12-18 00:00:00','2010-04-06 13:44:00','0000-00-00 00:00:00','ron','ron'),(194,'103066',1788850,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:44:00','0000-00-00 00:00:00','ron','ron'),(195,'155847',1811094,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-11 00:49:44','2010-06-30 00:49:55',NULL,1,'2010-04-11 00:49:44','2010-04-06 13:44:01','0000-00-00 00:00:00','ron','ron'),(196,'E20700D868643',1814099,'','OECA Video Element','VIDEO',NULL,NULL,NULL,'2010-04-05 17:47:54','2011-08-31 17:47:59',NULL,1,'2010-04-05 17:47:54','2010-04-06 13:44:02','0000-00-00 00:00:00','ron','ron'),(197,'238277',1816152,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 14:28:40','2100-04-05 14:28:46',NULL,1,'2010-04-05 14:28:40','2010-04-06 13:44:02','0000-00-00 00:00:00','ron','ron'),(198,'238277',1816152,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 14:28:40','2100-04-05 14:28:46',NULL,1,'2010-04-05 14:28:40','2010-04-06 13:44:03','0000-00-00 00:00:00','ron','ron'),(199,'103068',1786285,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:44:04','0000-00-00 00:00:00','ron','ron'),(200,'E20700D814860',1816958,'','OECA Video Element','VIDEO',NULL,NULL,NULL,'2010-03-29 17:47:54','2010-08-31 17:47:59',NULL,1,'2010-03-29 17:47:54','2010-04-06 13:44:04','0000-00-00 00:00:00','ron','ron'),(201,'542212',1782661,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-03-04 00:00:00','2022-03-04 00:00:00',NULL,1,'2010-03-04 00:00:00','2010-04-06 13:44:05','0000-00-00 00:00:00','ron','ron'),(202,'1814035',1817675,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-04-06 00:00:00','2099-04-06 00:00:00',NULL,1,'2010-04-06 00:00:00','2010-04-06 13:44:06','0000-00-00 00:00:00','ron','ron'),(203,'103060',1791920,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:44:06','0000-00-00 00:00:00','ron','ron'),(204,'E20700D868648',1815739,'','OECA Video Element','VIDEO',NULL,NULL,NULL,'2010-03-29 17:47:54','2010-08-31 17:47:59',NULL,1,'2010-03-29 17:47:54','2010-04-06 13:44:07','0000-00-00 00:00:00','ron','ron'),(205,'103058',1772946,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:44:07','0000-00-00 00:00:00','ron','ron'),(206,'238277_01054917_01072719',1817671,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 14:28:40','2100-04-05 14:28:46',NULL,1,'2010-04-05 14:28:40','2010-04-06 13:44:08','0000-00-00 00:00:00','ron','ron'),(207,'1804384',1804454,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-02-24 00:28:10','2099-02-24 00:28:12',NULL,1,'2010-02-24 00:28:10','2010-04-06 13:44:09','0000-00-00 00:00:00','ron','ron'),(208,'1804384',1804454,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-02-24 00:28:10','2099-02-24 00:28:12',NULL,1,'2010-02-24 00:28:10','2010-04-06 13:44:10','0000-00-00 00:00:00','ron','ron'),(209,'1804384',1804454,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-02-24 00:28:10','2099-02-24 00:28:12',NULL,1,'2010-02-24 00:28:10','2010-04-06 13:44:10','0000-00-00 00:00:00','ron','ron'),(210,'1804384',1804454,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-02-24 00:28:10','2099-02-24 00:28:12',NULL,1,'2010-02-24 00:28:10','2010-04-06 13:44:11','0000-00-00 00:00:00','ron','ron'),(211,'1814696',1815764,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-04-06 00:00:00','2099-04-06 00:00:00',NULL,1,'2010-04-06 00:00:00','2010-04-06 13:44:12','0000-00-00 00:00:00','ron','ron'),(212,'838257',1803233,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 23:30:00','2029-04-30 16:49:12',NULL,1,'2010-04-05 23:30:00','2010-04-06 13:44:12','0000-00-00 00:00:00','ron','ron'),(213,'1814972',1817637,'','OECA Segment','VIDEO',NULL,NULL,NULL,'2010-04-06 00:00:00','2099-04-06 00:00:00',NULL,1,'2010-04-06 00:00:00','2010-04-06 13:44:12','0000-00-00 00:00:00','ron','ron'),(214,'838287',1812012,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-05 23:30:00','2029-03-31 11:54:15',NULL,1,'2010-04-05 23:30:00','2010-04-06 13:44:13','0000-00-00 00:00:00','ron','ron'),(215,'103065',1788518,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-09 00:00:00','2020-04-09 00:00:00',NULL,1,'2010-04-09 00:00:00','2010-04-06 13:44:13','0000-00-00 00:00:00','ron','ron'),(216,'542215',1795722,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-20 16:58:52','2025-04-19 16:58:59',NULL,1,'2010-04-20 16:58:52','2010-04-06 13:44:14','0000-00-00 00:00:00','ron','ron'),(217,'004401',1816975,'','OECA BPN','VIDEO',NULL,NULL,NULL,'2010-04-06 11:18:20','2010-04-13 11:18:30',NULL,1,'2010-04-06 11:18:20','2010-04-06 13:44:14','0000-00-00 00:00:00','ron','ron');
/*!40000 ALTER TABLE `asset_root` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_video`
--

DROP TABLE IF EXISTS `asset_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_video` (
  `asset_video_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_root_id` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `link_url` text,
  `link_title` varchar(255) DEFAULT NULL,
  `master_series_number` varchar(12) DEFAULT NULL,
  `is_embed_code` bit(1) DEFAULT NULL,
  `thumbnail_url` text,
  `video_still_url` text,
  `video_url` text,
  `bc_ref_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`asset_video_id`),
  KEY `fk_asset_video` (`asset_root_id`),
  CONSTRAINT `fk_asset_video` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_video`
--

LOCK TABLES `asset_video` WRITE;
/*!40000 ALTER TABLE `asset_video` DISABLE KEYS */;
INSERT INTO `asset_video` VALUES (140,150,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(141,151,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(142,152,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(143,153,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(144,154,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(145,155,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(146,156,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(147,157,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(148,158,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(149,159,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(150,160,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(151,161,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(152,162,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(153,163,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(154,164,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(155,165,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(156,166,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(157,167,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(158,168,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(159,169,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(160,170,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(161,171,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(162,172,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(163,173,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(164,174,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(165,175,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(166,176,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(167,177,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(168,178,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(169,179,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(170,180,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(171,181,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(172,182,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(173,183,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(174,184,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(175,185,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(176,186,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(177,187,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(178,188,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(179,189,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(180,190,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(181,191,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(182,192,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(183,193,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(184,194,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(185,195,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(186,196,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(187,197,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(188,198,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(189,199,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(190,200,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(191,201,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(192,202,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(193,203,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(194,204,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(195,205,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(196,206,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(197,207,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(198,208,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(199,209,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(200,210,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(201,211,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(202,212,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(203,213,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(204,214,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(205,215,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(206,216,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL),(207,217,2210911,NULL,NULL,'',NULL,'http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577180001_1801083-1-1.jpg?pubId=1351824783','http://brightcove.vo.llnwd.net/d9/unsecured/media/1351824783/1351824783_67577179001_1801083-1.jpg?pubId=1351824783',NULL,NULL);
/*!40000 ALTER TABLE `asset_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brightcove_id`
--

DROP TABLE IF EXISTS `brightcove_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brightcove_id` (
  `brightcove_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_video_id` int(11) DEFAULT NULL,
  `domain_name_id` int(11) DEFAULT NULL,
  `bc_id` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`brightcove_id`),
  KEY `fk_bc_video` (`asset_video_id`),
  KEY `fk_bc_domain_name` (`domain_name_id`),
  CONSTRAINT `fk_bc_domain_name` FOREIGN KEY (`domain_name_id`) REFERENCES `domain_name` (`domain_name_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bc_video` FOREIGN KEY (`asset_video_id`) REFERENCES `asset_video` (`asset_video_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brightcove_id`
--

LOCK TABLES `brightcove_id` WRITE;
/*!40000 ALTER TABLE `brightcove_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `brightcove_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_sub`
--

DROP TABLE IF EXISTS `category_sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_sub` (
  `category_sub_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`category_sub_id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_sub`
--

LOCK TABLES `category_sub` WRITE;
/*!40000 ALTER TABLE `category_sub` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_sub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_sub_asset`
--

DROP TABLE IF EXISTS `category_sub_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_sub_asset` (
  `category_sub_asset_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_root_id` int(11) DEFAULT NULL,
  `category_sub_id` int(11) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `update_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`category_sub_asset_id`),
  KEY `fk_category_sub` (`category_sub_id`),
  KEY `fk_category_asset_root` (`asset_root_id`),
  CONSTRAINT `fk_category_asset_root` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_sub` FOREIGN KEY (`category_sub_id`) REFERENCES `category_sub` (`category_sub_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_sub_asset`
--

LOCK TABLES `category_sub_asset` WRITE;
/*!40000 ALTER TABLE `category_sub_asset` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_sub_asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contributor`
--

DROP TABLE IF EXISTS `contributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contributor` (
  `contributor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `unique_name` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`contributor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor`
--

LOCK TABLES `contributor` WRITE;
/*!40000 ALTER TABLE `contributor` DISABLE KEYS */;
/*!40000 ALTER TABLE `contributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contributor_profile`
--

DROP TABLE IF EXISTS `contributor_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contributor_profile` (
  `contributor_profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `bio` text,
  `image` varchar(255) DEFAULT NULL,
  `thumbnail_image` varchar(255) DEFAULT NULL,
  `link` text,
  `domain_name_id` int(11) DEFAULT NULL,
  `revision` int(11) DEFAULT NULL,
  `contributor_id` int(11) DEFAULT NULL,
  `contributor_type_id` int(11) DEFAULT NULL,
  `screen_name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`contributor_profile_id`),
  KEY `fk_contributor_id` (`contributor_id`),
  KEY `fk_contributor_domain_id` (`domain_name_id`),
  CONSTRAINT `fk_contributor_domain_id` FOREIGN KEY (`domain_name_id`) REFERENCES `domain_name` (`domain_name_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_id` FOREIGN KEY (`contributor_id`) REFERENCES `contributor` (`contributor_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor_profile`
--

LOCK TABLES `contributor_profile` WRITE;
/*!40000 ALTER TABLE `contributor_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `contributor_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contributor_profile_article`
--

DROP TABLE IF EXISTS `contributor_profile_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contributor_profile_article` (
  `contributor_article_profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_article_id` int(11) DEFAULT NULL,
  `contributor_profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`contributor_article_profile_id`),
  KEY `fk_contributor_article_profile_id` (`contributor_profile_id`),
  KEY `fk_contributor_asset_article_id` (`asset_article_id`),
  CONSTRAINT `fk_contributor_article_profile_id` FOREIGN KEY (`contributor_profile_id`) REFERENCES `contributor_profile` (`contributor_profile_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_asset_article_id` FOREIGN KEY (`asset_article_id`) REFERENCES `asset_article` (`asset_article_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor_profile_article`
--

LOCK TABLES `contributor_profile_article` WRITE;
/*!40000 ALTER TABLE `contributor_profile_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `contributor_profile_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contributor_profile_asset`
--

DROP TABLE IF EXISTS `contributor_profile_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contributor_profile_asset` (
  `id_contributor_profile_asset` int(11) NOT NULL AUTO_INCREMENT,
  `contributor_profile_id` int(11) DEFAULT NULL,
  `asset_root_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_contributor_profile_asset`),
  KEY `fk_contributor_asset_profile_id` (`id_contributor_profile_asset`),
  KEY `fk_contributor_asset_root_id` (`asset_root_id`),
  CONSTRAINT `fk_contributor_asset_profile_id` FOREIGN KEY (`id_contributor_profile_asset`) REFERENCES `contributor_profile` (`contributor_profile_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_asset_root_id` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor_profile_asset`
--

LOCK TABLES `contributor_profile_asset` WRITE;
/*!40000 ALTER TABLE `contributor_profile_asset` DISABLE KEYS */;
/*!40000 ALTER TABLE `contributor_profile_asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contributor_profile_blog`
--

DROP TABLE IF EXISTS `contributor_profile_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contributor_profile_blog` (
  `contributor_profile_blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_blog_id` int(11) DEFAULT NULL,
  `contributor_profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`contributor_profile_blog_id`),
  KEY `fk_contributor_blog_profile_id` (`contributor_profile_blog_id`),
  KEY `fk_contributor_blog_asset_root_id` (`asset_blog_id`),
  CONSTRAINT `fk_contributor_blog_asset_root_id` FOREIGN KEY (`asset_blog_id`) REFERENCES `asset_blog` (`asset_blog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_blog_profile_id` FOREIGN KEY (`contributor_profile_blog_id`) REFERENCES `contributor_profile` (`contributor_profile_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor_profile_blog`
--

LOCK TABLES `contributor_profile_blog` WRITE;
/*!40000 ALTER TABLE `contributor_profile_blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `contributor_profile_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contributor_profile_blog_post`
--

DROP TABLE IF EXISTS `contributor_profile_blog_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contributor_profile_blog_post` (
  `contributor_profile_blog_post_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_blog_post_id` int(11) DEFAULT NULL,
  `contributor_profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`contributor_profile_blog_post_id`),
  KEY `fk_contributor_blog_post_profile_id` (`contributor_profile_id`),
  KEY `fk_contributor_asset_blog_post_id` (`asset_blog_post_id`),
  CONSTRAINT `fk_contributor_asset_blog_post_id` FOREIGN KEY (`asset_blog_post_id`) REFERENCES `asset_blog_post` (`asset_blog_post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contributor_blog_post_profile_id` FOREIGN KEY (`contributor_profile_id`) REFERENCES `contributor_profile` (`contributor_profile_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor_profile_blog_post`
--

LOCK TABLES `contributor_profile_blog_post` WRITE;
/*!40000 ALTER TABLE `contributor_profile_blog_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `contributor_profile_blog_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contributor_type`
--

DROP TABLE IF EXISTS `contributor_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contributor_type` (
  `contributor_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`contributor_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contributor_type`
--

LOCK TABLES `contributor_type` WRITE;
/*!40000 ALTER TABLE `contributor_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `contributor_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries_iso`
--

DROP TABLE IF EXISTS `countries_iso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries_iso` (
  `iso2` char(2) NOT NULL,
  `iso3` char(3) DEFAULT NULL,
  `print_name` varchar(80) DEFAULT NULL,
  `numcode` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`iso2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries_iso`
--

LOCK TABLES `countries_iso` WRITE;
/*!40000 ALTER TABLE `countries_iso` DISABLE KEYS */;
/*!40000 ALTER TABLE `countries_iso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domain_name`
--

DROP TABLE IF EXISTS `domain_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domain_name` (
  `domain_name_id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_name` varchar(200) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`domain_name_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain_name`
--

LOCK TABLES `domain_name` WRITE;
/*!40000 ALTER TABLE `domain_name` DISABLE KEYS */;
INSERT INTO `domain_name` VALUES (1,'tvo.org','2010-04-06 16:32:17','2010-04-06 20:32:17','ron','0'),(2,'tvoparents.org','2010-04-06 16:40:06','2010-04-06 20:40:06','ron','0'),(3,'tvokids.org','2010-04-06 16:42:37','2010-04-06 20:42:37','ron','0');
/*!40000 ALTER TABLE `domain_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domain_publish`
--

DROP TABLE IF EXISTS `domain_publish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domain_publish` (
  `domain_publish_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_root_id` int(11) DEFAULT NULL,
  `domain_name_id` int(11) DEFAULT NULL,
  `is_published` bit(1) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`domain_publish_id`),
  KEY `fk_domain_publish` (`asset_root_id`),
  KEY `fk_domain_name` (`domain_name_id`),
  CONSTRAINT `fk_domain_name` FOREIGN KEY (`domain_name_id`) REFERENCES `domain_name` (`domain_name_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_domain_publish` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain_publish`
--

LOCK TABLES `domain_publish` WRITE;
/*!40000 ALTER TABLE `domain_publish` DISABLE KEYS */;
/*!40000 ALTER TABLE `domain_publish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `geo_filter`
--

DROP TABLE IF EXISTS `geo_filter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `geo_filter` (
  `geo_filter_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_root_id` int(11) DEFAULT NULL,
  `filter_name` varchar(50) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`geo_filter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `geo_filter`
--

LOCK TABLES `geo_filter` WRITE;
/*!40000 ALTER TABLE `geo_filter` DISABLE KEYS */;
INSERT INTO `geo_filter` VALUES (1,NULL,'test_filter',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `geo_filter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `geo_location`
--

DROP TABLE IF EXISTS `geo_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `geo_location` (
  `geo_location_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_root_id` int(11) DEFAULT NULL,
  `country_iso2` char(2) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`geo_location_id`),
  KEY `fk_country_iso` (`country_iso2`),
  KEY `fk_geo_asset` (`asset_root_id`),
  CONSTRAINT `fk_country_iso` FOREIGN KEY (`country_iso2`) REFERENCES `countries_iso` (`iso2`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_geo_asset` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `geo_location`
--

LOCK TABLES `geo_location` WRITE;
/*!40000 ALTER TABLE `geo_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `geo_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_asset_telescope`
--

DROP TABLE IF EXISTS `log_asset_telescope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_asset_telescope` (
  `asset_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_root_id` int(11) DEFAULT NULL,
  `action_command` varchar(60) DEFAULT NULL,
  `is_pass` bit(1) DEFAULT NULL,
  `java_exception` text,
  `warnings` text,
  `created_on` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`asset_log_id`),
  KEY `fk_log_asset_root_id` (`asset_root_id`),
  CONSTRAINT `fk_log_asset_root_id` FOREIGN KEY (`asset_root_id`) REFERENCES `asset_root` (`asset_root_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_asset_telescope`
--

LOCK TABLES `log_asset_telescope` WRITE;
/*!40000 ALTER TABLE `log_asset_telescope` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_asset_telescope` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `right_type`
--

DROP TABLE IF EXISTS `right_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `right_type` (
  `right_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` timestamp NULL DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`right_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `right_type`
--

LOCK TABLES `right_type` WRITE;
/*!40000 ALTER TABLE `right_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `right_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strands`
--

DROP TABLE IF EXISTS `strands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strands` (
  `strand_id` int(11) NOT NULL AUTO_INCREMENT,
  `strand_title` varchar(100) DEFAULT NULL,
  `strand_description` text,
  `listing_strand` char(1) DEFAULT NULL,
  `web_strand` char(1) DEFAULT NULL,
  `strand_active` char(1) DEFAULT NULL,
  `changed_by` varchar(40) DEFAULT NULL,
  `change_time` datetime DEFAULT NULL,
  `strand_color` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`strand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strands`
--

LOCK TABLES `strands` WRITE;
/*!40000 ALTER TABLE `strands` DISABLE KEYS */;
/*!40000 ALTER TABLE `strands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strands_schedule`
--

DROP TABLE IF EXISTS `strands_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strands_schedule` (
  `strands_schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `strand_id` int(11) DEFAULT NULL,
  `start_datetime` datetime DEFAULT NULL,
  `end_datetime` datetime DEFAULT NULL,
  `listings_strand_title` char(1) DEFAULT NULL,
  `listings_strand_description` char(1) DEFAULT NULL,
  `listings_series_description` char(1) DEFAULT NULL,
  `listings_program_title` char(1) DEFAULT NULL,
  `listings_program_description` char(1) DEFAULT NULL,
  `web_strand_title` char(1) DEFAULT NULL,
  `web_strand_description` char(1) DEFAULT NULL,
  `web_series_title` char(1) DEFAULT NULL,
  `web_series_description` char(1) DEFAULT NULL,
  `web_program_title` char(1) DEFAULT NULL,
  `web_program_description` char(1) DEFAULT NULL,
  `is_active` char(1) DEFAULT NULL,
  `change_by` varchar(40) DEFAULT NULL,
  `change_time` datetime DEFAULT NULL,
  PRIMARY KEY (`strands_schedule_id`),
  KEY `fk_strands` (`strands_schedule_id`),
  CONSTRAINT `fk_strands` FOREIGN KEY (`strands_schedule_id`) REFERENCES `strands` (`strand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strands_schedule`
--

LOCK TABLES `strands_schedule` WRITE;
/*!40000 ALTER TABLE `strands_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `strands_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `user_name` varchar(40) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  `first_name` varchar(40) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-04-07 20:04:46
