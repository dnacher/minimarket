-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: minimarket
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit` (
  `audit_id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit`
--

LOCK TABLES `audit` WRITE;
/*!40000 ALTER TABLE `audit` DISABLE KEYS */;
INSERT INTO `audit` VALUES (3,20,'CREATED','2021-04-02 20:41:53'),(10,NULL,'ERROR','2021-04-02 21:18:03'),(11,21,'CREATED','2021-04-02 21:19:54'),(12,NULL,'ERROR','2021-04-02 21:37:38'),(13,22,'CREATED','2021-04-02 21:37:46'),(14,NULL,'ERROR','2021-04-03 12:37:13'),(15,23,'CREATED','2021-04-03 12:40:45'),(16,24,'CREATED','2021-04-03 12:40:57'),(17,25,'CREATED','2021-04-03 12:41:06'),(18,26,'CREATED','2021-04-03 12:43:06'),(19,27,'CREATED','2021-04-03 23:05:42'),(20,28,'CREATED','2021-04-03 23:06:23'),(21,29,'CREATED','2021-04-03 23:06:41'),(22,30,'CREATED','2021-04-03 23:07:22'),(23,31,'CREATED','2021-04-06 10:37:43');
/*!40000 ALTER TABLE `audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(80) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Agua Mineral Natural',116),(2,'Queso Muzzarella',390),(3,'Pera Williams',75),(4,'Harina Tipo 0000',50),(5,'Carne Picada Super especial',369),(6,'Azucar',39),(7,'Coca Cola Light',118),(8,'Galleta Oblea Rellena',79),(9,'Arroz blanco',56),(10,'Toallas de cocina',79),(11,NULL,NULL),(12,'Pan Flauta',35);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`stock_id`),
  KEY `stock_product_id_idx` (`product_id`),
  CONSTRAINT `stock_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,1,34),(2,2,27),(3,3,45),(4,4,500),(5,5,120),(6,6,38),(7,7,14),(8,8,35),(9,9,22),(10,10,41),(11,12,70);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_line`
--

DROP TABLE IF EXISTS `transaction_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_line` (
  `transaction_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `subtotal` decimal(20,2) DEFAULT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaction_line_id`),
  KEY `product_id_idx` (`product_id`),
  KEY `transaction_id_idx` (`transaction_id`),
  KEY `transaction_line_product_id_idx` (`product_id`),
  KEY `transaction_line_transaction_id_idx` (`transaction_id`),
  CONSTRAINT `transaction_line_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`code`),
  CONSTRAINT `transaction_line_transaction_id` FOREIGN KEY (`transaction_id`) REFERENCES `transaction_table` (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_line`
--

LOCK TABLES `transaction_line` WRITE;
/*!40000 ALTER TABLE `transaction_line` DISABLE KEYS */;
INSERT INTO `transaction_line` VALUES (15,12,2,70.00,20),(16,12,4,140.00,21),(17,12,1,35.00,22),(18,12,1,35.00,23),(19,12,1,35.00,24),(20,12,1,35.00,25),(21,12,1,35.00,26),(22,1,8,928.00,27),(23,1,8,928.00,28),(24,2,8,3120.00,29),(25,6,2,78.00,30),(26,3,4,300.00,31);
/*!40000 ALTER TABLE `transaction_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_table`
--

DROP TABLE IF EXISTS `transaction_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_table` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `total` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_table`
--

LOCK TABLES `transaction_table` WRITE;
/*!40000 ALTER TABLE `transaction_table` DISABLE KEYS */;
INSERT INTO `transaction_table` VALUES (20,'2021-04-01',NULL),(21,'2021-04-01',NULL),(22,'2021-04-01',NULL),(23,'2021-04-03',NULL),(24,'2021-04-10',NULL),(25,'2021-04-14',NULL),(26,'2021-04-13',NULL),(27,NULL,NULL),(28,'2021-04-02',NULL),(29,'2021-04-07',NULL),(30,'2021-04-05',NULL),(31,'2021-03-31',300.00);
/*!40000 ALTER TABLE `transaction_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-06 10:40:00
