CREATE DATABASE  IF NOT EXISTS `joinsexample`
USE `joinsexample`;


--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `City` varchar(255) COLLATE utf8mb4_lithuanian_ci DEFAULT NULL,
  `Street` varchar(255) COLLATE utf8mb4_lithuanian_ci DEFAULT NULL,
  `Building_Number` varchar(255) COLLATE utf8mb4_lithuanian_ci DEFAULT NULL,
  `Customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_lithuanian_ci;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
INSERT INTO `address` VALUES (2,'Kaunas','Saulės','33',NULL),(3,'Vilnius','Gedimino pr.','44',2);
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_lithuanian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_lithuanian_ci;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
INSERT INTO `customer` VALUES (3,'Jonas'),(5,'Mindaugas'),(10,'Xavier'),(50,'John'),(51,'Max');
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Item` varchar(255) COLLATE utf8mb4_lithuanian_ci DEFAULT NULL,
  `Customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cust_id` (`Customer_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_lithuanian_ci;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
INSERT INTO `orders` VALUES (1,'Towel',NULL),(2,'Dress',NULL),(12,'Desk',3),(13,'Desk',3),(14,'Desk',3),(15,'Chair',3);
UNLOCK TABLES;

-- Dump completed on 2023-08-15 18:33:13
