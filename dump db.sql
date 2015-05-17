CREATE DATABASE  IF NOT EXISTS `sis_pizza_db` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_general_cs */;
USE `sis_pizza_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sis_pizza_db
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `bebida`
--

DROP TABLE IF EXISTS `bebida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bebida` (
  `numero_bebida` int(11) NOT NULL AUTO_INCREMENT,
  `nome_bebida` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `preco_bebida` decimal(10,2) NOT NULL,
  PRIMARY KEY (`numero_bebida`),
  UNIQUE KEY `numero_bebida_UNIQUE` (`numero_bebida`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bebida`
--

LOCK TABLES `bebida` WRITE;
/*!40000 ALTER TABLE `bebida` DISABLE KEYS */;
INSERT INTO `bebida` VALUES (1,'Coca-Cola 300 ml',2.50);
/*!40000 ALTER TABLE `bebida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `logradouro` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `bairro` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `numero_cep` varchar(10) COLLATE latin1_general_cs NOT NULL,
  `cidade` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `uf` varchar(20) COLLATE latin1_general_cs NOT NULL,
  `pessoa_cpf` char(11) COLLATE latin1_general_cs NOT NULL,
  KEY `fk_endereco_pessoa_idx` (`pessoa_cpf`),
  CONSTRAINT `fk_endereco_pessoa` FOREIGN KEY (`pessoa_cpf`) REFERENCES `pessoa` (`cpf`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES ('QNP 14 Conjunto T Casa 03','PSul','72231420','Ceilândia','Distrito Federal','02529061106'),('Rua 10 Casa 06','Zona Sul','720369741','Samambaia','Distrito Federal','17938651168');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `pessoa_cpf` char(11) COLLATE latin1_general_cs NOT NULL,
  `senha` varchar(45) COLLATE latin1_general_cs NOT NULL,
  PRIMARY KEY (`pessoa_cpf`),
  KEY `fk_login_pessoa1_idx` (`pessoa_cpf`),
  CONSTRAINT `fk_login_pessoa1` FOREIGN KEY (`pessoa_cpf`) REFERENCES `pessoa` (`cpf`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('02529061106','200389'),('17938651168','123456789');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `numero_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `data_pedido` varchar(10) COLLATE latin1_general_cs NOT NULL,
  `pizza_numero_pizza` int(11) NOT NULL,
  `pessoa_cpf_cliente` char(11) COLLATE latin1_general_cs NOT NULL,
  `pessoa_cpf_cozinheiro` char(11) COLLATE latin1_general_cs NOT NULL,
  `bebida_numero_bebida` int(11) NOT NULL,
  `hora_pedido` varchar(10) COLLATE latin1_general_cs NOT NULL,
  `valor_pedido` decimal(10,2) NOT NULL,
  `desconto_pedido` decimal(3,2) DEFAULT NULL,
  `data_cancelamento` varchar(10) COLLATE latin1_general_cs DEFAULT NULL,
  `hora_cancelamento` varchar(10) COLLATE latin1_general_cs DEFAULT NULL,
  `quantidade_pizza` int(11) NOT NULL,
  `quantidade_bebida` int(11) NOT NULL,
  `tamanho_pizza` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `observacao` varchar(100) COLLATE latin1_general_cs NOT NULL,
  `descricao_situacao_pedido` varchar(45) COLLATE latin1_general_cs NOT NULL,
  PRIMARY KEY (`numero_pedido`),
  UNIQUE KEY `numero_pedido_UNIQUE` (`numero_pedido`),
  KEY `fk_pedido_pizza1_idx` (`pizza_numero_pizza`),
  KEY `fk_pedido_bebida1_idx` (`bebida_numero_bebida`),
  KEY `fk_pedido_pessoa1_idx` (`pessoa_cpf_cliente`),
  KEY `fk_pedido_pessoa2_idx` (`pessoa_cpf_cozinheiro`),
  CONSTRAINT `fk_pedido_bebida1` FOREIGN KEY (`bebida_numero_bebida`) REFERENCES `bebida` (`numero_bebida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_pessoa1` FOREIGN KEY (`pessoa_cpf_cliente`) REFERENCES `pessoa` (`cpf`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_pessoa2` FOREIGN KEY (`pessoa_cpf_cozinheiro`) REFERENCES `pessoa` (`cpf`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_pizza1` FOREIGN KEY (`pizza_numero_pizza`) REFERENCES `pizza` (`numero_pizza`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'10/05/2015',1,'02529061106','17938651168',1,'17:34:20',37.47,0.00,NULL,NULL,3,3,'Único','','Efetuado'),(2,'10/05/2015',1,'02529061106','17938651168',1,'17:40:23',1249.00,0.00,NULL,NULL,100,100,'Único','','Efetuado'),(3,'10/05/2015',1,'02529061106','17938651168',1,'19:45:26',54.95,0.00,NULL,NULL,5,2,'Único','','Efetuado');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `cpf` char(11) COLLATE latin1_general_cs NOT NULL,
  `nome` varchar(50) COLLATE latin1_general_cs NOT NULL,
  `numero_rg` varchar(11) COLLATE latin1_general_cs NOT NULL,
  `orgao_emissor_rg` varchar(20) COLLATE latin1_general_cs NOT NULL,
  `telefone` varchar(20) COLLATE latin1_general_cs NOT NULL,
  `data_cadastro` char(10) COLLATE latin1_general_cs DEFAULT NULL,
  `data_cancelamento_cadastro` char(10) COLLATE latin1_general_cs DEFAULT NULL,
  `matricula` int(11) NOT NULL AUTO_INCREMENT,
  `perfil` varchar(45) COLLATE latin1_general_cs DEFAULT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES ('02529061106','Carlos André de Souza','2644249','SSPDF','33775539',NULL,NULL,16,NULL),('17938651168','Maria da Dores','7411209','SSPDF','33761028',NULL,NULL,20,'Cozinheiro');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza` (
  `numero_pizza` int(11) NOT NULL AUTO_INCREMENT,
  `nome_pizza` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `tipo_pizza` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `tipo_massa` varchar(45) COLLATE latin1_general_cs NOT NULL,
  `ingredientes_pizza` varchar(100) COLLATE latin1_general_cs NOT NULL,
  `preco_pizza` decimal(10,2) NOT NULL,
  PRIMARY KEY (`numero_pizza`),
  UNIQUE KEY `numero_pizza_UNIQUE` (`numero_pizza`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (1,'Calabresa','Salgada','Fina','Calabresa, molho de tomate, mussarela, oregano',9.99);
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-16 20:58:34
