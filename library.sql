-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.24

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
  `PHONE` int(11) NOT NULL,
  PRIMARY KEY (`AID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
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
  `EDITION` varchar(20) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `ISSUED` int(11) NOT NULL,
  `PID` int(11) NOT NULL,
  `CID` int(11) NOT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `fk_book_1_idx` (`PID`),
  KEY `cid_idx` (`CID`),
  CONSTRAINT `cid` FOREIGN KEY (`CID`) REFERENCES `category` (`CID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pid` FOREIGN KEY (`PID`) REFERENCES `publisher` (`PID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Fiction','Unreal works of art'),(10,'Science','Physics, Chemistry and Biology');
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
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
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
  `DOR` date NOT NULL,
  `ISSUEID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ISSUEID`),
  KEY `LID_idx` (`LID`),
  KEY `SID_idx` (`SID`),
  KEY `ISBN_idx` (`ISBN`),
  CONSTRAINT `isbn_issue` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lid` FOREIGN KEY (`LID`) REFERENCES `librarian` (`LID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sid` FOREIGN KEY (`SID`) REFERENCES `student` (`SID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuebook`
--

LOCK TABLES `issuebook` WRITE;
/*!40000 ALTER TABLE `issuebook` DISABLE KEYS */;
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
INSERT INTO `librarian` VALUES ('ckarthik114@gmail.com','Karthik Cottur','123');
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
  CONSTRAINT `course_id` FOREIGN KEY (`CID`) REFERENCES `course` (`CID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `isbn_p` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescribes`
--

LOCK TABLES `prescribes` WRITE;
/*!40000 ALTER TABLE `prescribes` DISABLE KEYS */;
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
  `PHONE` int(11) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
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
  CONSTRAINT `aid` FOREIGN KEY (`AID`) REFERENCES `author` (`AID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `isbn_wb` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `written_by`
--

LOCK TABLES `written_by` WRITE;
/*!40000 ALTER TABLE `written_by` DISABLE KEYS */;
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

-- Dump completed on 2018-11-10 21:44:20
