-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.23

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `AID` int(11) NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(25) NOT NULL,
  `LNAME` varchar(25) DEFAULT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `PHONE` varchar(15) NOT NULL,
  PRIMARY KEY (`AID`),
  UNIQUE KEY `PHONE_UNIQUE` (`PHONE`),
  UNIQUE KEY `NAME` (`FNAME`,`LNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (6,'Arthur','Conan Doyle','221B Baker Street','8571239041'),(10,'Muhammad','Mazidi','University of Tabriz','9681239121'),(12,'Janice','Mazidi','University of Tabriz','9859123877');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `ISBN` varchar(20) NOT NULL,
  `TITLE` varchar(200) NOT NULL,
  `EDITION` varchar(20) DEFAULT NULL,
  `QUANTITY` int(11) NOT NULL,
  `ISSUED` int(11) NOT NULL,
  `PID` int(11) NOT NULL,
  `CID` int(11) NOT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `fk_book_1_idx` (`PID`),
  KEY `cid_idx` (`CID`),
  CONSTRAINT `cid` FOREIGN KEY (`CID`) REFERENCES `category` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pid` FOREIGN KEY (`PID`) REFERENCES `publisher` (`PID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('abc12','8051 progamming','2nd',3,0,1,1),('abc124','8086 embedded systems','5th',5,0,5,10);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `CID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`CID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Fiction','Unreal works of art'),(10,'Science','Physics, Chemistry and Biology related books');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `CID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL,
  `DEPARTMENT` varchar(50) NOT NULL,
  PRIMARY KEY (`CID`),
  UNIQUE KEY `TITLE_DEPT` (`DEPARTMENT`,`TITLE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'DMS','CSE'),(2,'MCES','CSE');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issuebook`
--

DROP TABLE IF EXISTS `issuebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issuebook` (
  `ISBN` varchar(20) NOT NULL,
  `SID` varchar(20) NOT NULL,
  `LID` varchar(100) NOT NULL,
  `DOI` date NOT NULL,
  `DOR` date DEFAULT NULL,
  `ISSUEID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ISSUEID`),
  KEY `LID_idx` (`LID`),
  KEY `SID_idx` (`SID`),
  KEY `ISBN_idx` (`ISBN`),
  CONSTRAINT `isbn_issue` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lid` FOREIGN KEY (`LID`) REFERENCES `librarian` (`LID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sid` FOREIGN KEY (`SID`) REFERENCES `student` (`SID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuebook`
--

LOCK TABLES `issuebook` WRITE;
/*!40000 ALTER TABLE `issuebook` DISABLE KEYS */;
INSERT INTO `issuebook` VALUES ('abc124','1RV16CS014','keerthan@gmail.com','2018-11-14','2018-11-15',4),('abc124','1RV16CS014','keerthan@gmail.com','2018-11-15','2018-11-15',7),('abc124','1RV16CS014','keerthan@gmail.com','2018-11-04','2018-11-15',8),('abc124','1RV16CS014','keerthan@gmail.com','2018-11-02','2018-11-15',13),('abc124','1RV16CS014','keerthan@gmail.com','2018-11-15','2018-11-15',18),('abc124','1RV16CS014','keerthan@gmail.com','2018-11-02','2018-11-15',19),('abc124','1RV16CS001','keerthan@gmail.com','2018-11-02','2018-11-15',20),('abc124','1RV16CS014','keerthan@gmail.com','2018-11-01','2018-11-15',22),('abc12','1RV16CS001','keerthan@gmail.com','2018-11-04','2018-11-15',23);
/*!40000 ALTER TABLE `issuebook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarian`
--

DROP TABLE IF EXISTS `librarian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `librarian` (
  `LID` varchar(100) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  PRIMARY KEY (`LID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarian`
--

LOCK TABLES `librarian` WRITE;
/*!40000 ALTER TABLE `librarian` DISABLE KEYS */;
INSERT INTO `librarian` VALUES ('ckarthik114@gmail.com','Karthik Cottur','123'),('keerthan@gmail.com','Keerthan Bhat','123');
/*!40000 ALTER TABLE `librarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescribes`
--

DROP TABLE IF EXISTS `prescribes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescribes` (
  `ISBN` varchar(20) NOT NULL,
  `CID` int(11) NOT NULL,
  KEY `course_id_idx` (`CID`),
  KEY `isbn_p_idx` (`ISBN`),
  CONSTRAINT `course_id` FOREIGN KEY (`CID`) REFERENCES `course` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `isbn_p` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescribes`
--

LOCK TABLES `prescribes` WRITE;
/*!40000 ALTER TABLE `prescribes` DISABLE KEYS */;
INSERT INTO `prescribes` VALUES ('abc12',1),('abc124',2),('abc12',2);
/*!40000 ALTER TABLE `prescribes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `PNAME` varchar(50) NOT NULL,
  `PHONE` varchar(15) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `PNAME_UNIQUE` (`PNAME`),
  UNIQUE KEY `PHONE_UNIQUE` (`PHONE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Bantam Books','9846581252','NY, USA'),(5,'Pearson','020 7010 2336','London, England');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `SID` varchar(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  PRIMARY KEY (`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('1RV16CS001','Aashish','1999-01-01','123'),('1RV16CS014','Akshay','1998-03-14','123');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `written_by`
--

DROP TABLE IF EXISTS `written_by`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `written_by` (
  `ISBN` varchar(20) NOT NULL,
  `AID` int(11) NOT NULL,
  KEY `isbn_wb_idx` (`ISBN`),
  KEY `aid_idx` (`AID`),
  CONSTRAINT `aid` FOREIGN KEY (`AID`) REFERENCES `author` (`AID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `isbn_wb` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `written_by`
--

LOCK TABLES `written_by` WRITE;
/*!40000 ALTER TABLE `written_by` DISABLE KEYS */;
INSERT INTO `written_by` VALUES ('abc124',10),('abc124',12);
/*!40000 ALTER TABLE `written_by` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-24 11:10:52
