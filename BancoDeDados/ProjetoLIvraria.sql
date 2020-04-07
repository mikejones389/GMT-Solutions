CREATE DATABASE  IF NOT EXISTS `projeto_livraria` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `projeto_livraria`;
-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: projeto_livraria
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

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
-- Table structure for table `carrinho_compra`
--

DROP TABLE IF EXISTS `carrinho_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carrinho_compra` (
  `cd_carrinhoCompra` int(5) NOT NULL AUTO_INCREMENT,
  `cd_usuario` int(5) NOT NULL,
  PRIMARY KEY (`cd_carrinhoCompra`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `carrinho_compra_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrinho_compra`
--

LOCK TABLES `carrinho_compra` WRITE;
/*!40000 ALTER TABLE `carrinho_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrinho_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `cd_compra` int(5) NOT NULL AUTO_INCREMENT,
  `dt_compra` date NOT NULL,
  `dt_entrega` date NOT NULL,
  `cd_fornecedor` int(3) NOT NULL,
  PRIMARY KEY (`cd_compra`),
  KEY `cd_fornecedor` (`cd_fornecedor`),
  CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`cd_fornecedor`) REFERENCES `fornecedor` (`cd_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupom`
--

DROP TABLE IF EXISTS `cupom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupom` (
  `cd_cupom` int(5) NOT NULL AUTO_INCREMENT,
  `vl_desconto` float(12,2) NOT NULL,
  `dt_aquisicao` date NOT NULL,
  `dt_vencimento` date NOT NULL,
  `cd_usuario` int(5) NOT NULL,
  PRIMARY KEY (`cd_cupom`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `cupom_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom`
--

LOCK TABLES `cupom` WRITE;
/*!40000 ALTER TABLE `cupom` DISABLE KEYS */;
/*!40000 ALTER TABLE `cupom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `cd_endereco` int(3) NOT NULL AUTO_INCREMENT,
  `rua` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `estado` char(2) NOT NULL,
  `pais` char(2) NOT NULL,
  `cep` int(8) NOT NULL,
  `numero` int(5) NOT NULL,
  `cd_usuario` int(5) DEFAULT NULL,
  `cd_fornecedor` int(3) DEFAULT NULL,
  PRIMARY KEY (`cd_endereco`),
  KEY `cd_usuario` (`cd_usuario`),
  KEY `cd_fornecedor` (`cd_fornecedor`),
  CONSTRAINT `endereco_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`),
  CONSTRAINT `endereco_ibfk_2` FOREIGN KEY (`cd_fornecedor`) REFERENCES `fornecedor` (`cd_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `cd_fornecedor` int(3) NOT NULL AUTO_INCREMENT,
  `nm_fornecedor` varchar(50) NOT NULL,
  `nm_fantasia` varchar(50) NOT NULL,
  `rz_social` varchar(50) NOT NULL,
  `cnpj` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `telefone` int(11) DEFAULT NULL,
  `celular` int(12) NOT NULL,
  `status` enum('1','0') DEFAULT '1',
  PRIMARY KEY (`cd_fornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (3,'Gustavo da Moita','Gudamota','MCDonalts',514524549,'gustavoMotoqueiro@gmail.com',66666669,966665698,'1'),(4,'Mike Jones ','MKJ','MKJ_teste',123456451,'mkjteste@gmail.com',54645678,1234564,'1');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_compra`
--

DROP TABLE IF EXISTS `item_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_compra` (
  `cd_itemCompra` int(5) NOT NULL AUTO_INCREMENT,
  `cd_livro` int(5) NOT NULL,
  `cd_compra` int(5) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco_unitario` float(12,2) DEFAULT NULL,
  PRIMARY KEY (`cd_itemCompra`),
  KEY `cd_livro` (`cd_livro`),
  KEY `cd_compra` (`cd_compra`),
  CONSTRAINT `item_compra_ibfk_1` FOREIGN KEY (`cd_livro`) REFERENCES `livro` (`cd_livro`),
  CONSTRAINT `item_compra_ibfk_2` FOREIGN KEY (`cd_compra`) REFERENCES `compra` (`cd_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_compra`
--

LOCK TABLES `item_compra` WRITE;
/*!40000 ALTER TABLE `item_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_desejado`
--

DROP TABLE IF EXISTS `item_desejado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_desejado` (
  `cd_itemDesejado` int(5) NOT NULL AUTO_INCREMENT,
  `cd_listaDesejo` int(5) NOT NULL,
  `cd_usuario` int(5) NOT NULL,
  `cd_livro` int(5) NOT NULL,
  PRIMARY KEY (`cd_itemDesejado`),
  KEY `cd_livro` (`cd_livro`),
  KEY `cd_listaDesejo` (`cd_listaDesejo`),
  KEY `cd_usuario` (`cd_usuario`),
  CONSTRAINT `item_desejado_ibfk_1` FOREIGN KEY (`cd_livro`) REFERENCES `livro` (`cd_livro`),
  CONSTRAINT `item_desejado_ibfk_2` FOREIGN KEY (`cd_listaDesejo`) REFERENCES `lista_desejo` (`cd_lista`),
  CONSTRAINT `item_desejado_ibfk_3` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_desejado`
--

LOCK TABLES `item_desejado` WRITE;
/*!40000 ALTER TABLE `item_desejado` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_desejado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_venda`
--

DROP TABLE IF EXISTS `item_venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_venda` (
  `cd_itemVenda` int(5) NOT NULL AUTO_INCREMENT,
  `cd_livro` int(5) NOT NULL,
  `cd_venda` int(5) NOT NULL,
  `cd_carrinhoCompra` int(5) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco_unitario` float(12,2) DEFAULT NULL,
  PRIMARY KEY (`cd_itemVenda`),
  KEY `cd_livro` (`cd_livro`),
  KEY `cd_venda` (`cd_venda`),
  KEY `cd_carrinhoCompra` (`cd_carrinhoCompra`),
  CONSTRAINT `item_venda_ibfk_1` FOREIGN KEY (`cd_livro`) REFERENCES `livro` (`cd_livro`),
  CONSTRAINT `item_venda_ibfk_2` FOREIGN KEY (`cd_venda`) REFERENCES `venda` (`cd_venda`),
  CONSTRAINT `item_venda_ibfk_3` FOREIGN KEY (`cd_carrinhoCompra`) REFERENCES `carrinho_compra` (`cd_carrinhoCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_venda`
--

LOCK TABLES `item_venda` WRITE;
/*!40000 ALTER TABLE `item_venda` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lista_desejo`
--

DROP TABLE IF EXISTS `lista_desejo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lista_desejo` (
  `cd_lista` int(5) NOT NULL AUTO_INCREMENT,
  `cd_usuario` int(5) NOT NULL,
  PRIMARY KEY (`cd_lista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_desejo`
--

LOCK TABLES `lista_desejo` WRITE;
/*!40000 ALTER TABLE `lista_desejo` DISABLE KEYS */;
/*!40000 ALTER TABLE `lista_desejo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livro` (
  `cd_livro` int(5) NOT NULL AUTO_INCREMENT,
  `nm_livro` varchar(50) NOT NULL,
  `autor` varchar(50) NOT NULL,
  `editora` varchar(50) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `ano_livro` int(4) NOT NULL,
  `edicao` int(11) DEFAULT NULL,
  `preco_venda` float(12,2) NOT NULL,
  `qnt_livro` int(11) NOT NULL,
  `cd_fornecedor` int(3) DEFAULT NULL,
  `link_img` varchar(200) NOT NULL,
  PRIMARY KEY (`cd_livro`),
  KEY `cd_fornecedor` (`cd_fornecedor`),
  CONSTRAINT `livro_ibfk_1` FOREIGN KEY (`cd_fornecedor`) REFERENCES `fornecedor` (`cd_fornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
INSERT INTO `livro` VALUES (4,'Harry Potter e a Pedra Filosofal','J.K Rolling','Rocco','Ficção',1998,1,27.99,200,3,'hp1.jpg'),(5,'Harry Potter e a Câmara','J.K Rolling','Rocco','Ficção',2000,1,27.99,200,3,'hp2.jpg'),(6,'Harry Potter e o Prisoneiro de Azkaban','J.K Rolling','Rocco','Ficção',2002,1,27.99,200,3,'hp3.jpg'),(7,'Harry Potter e o Cálice de Fogo','J.K Rolling','Rocco','Ficção',2004,1,27.99,200,3,'h4.jpg'),(8,'Harry Potter e a Ordem da Fênix','J.K Rolling','Rocco','Ficção',2006,1,27.99,200,3,'hp5.jpg'),(9,'Harry Potter e o Principe Mestiço ','J.K Rolling','Rocco','Ficção',2008,1,27.99,200,3,'hp6.jpg'),(10,'Harry Potter e as Relíquias da Morte ','J.K Rolling','Rocco','Ficção',2010,1,27.99,200,3,'hp7.jpg'),(11,'Harry Potter e as Relíquias da Morte ','J.K Rolling','Rocco','Ficção',2010,1,27.99,200,3,'hp8.jpg'),(12,'Como eu era antes de você ','Joye moes','Rocco','Romance',2011,1,129.99,200,3,'romance1.jpg'),(13,'A culpa é das Estrelas','John Green','Rocco','Romance',2015,1,99.99,200,3,'stars.jpg');
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `cd_usuario` int(5) NOT NULL AUTO_INCREMENT,
  `nm_usuario` varchar(50) NOT NULL,
  `cpf_usuario` int(11) NOT NULL,
  `sexo` char(1) NOT NULL,
  `data_nascimento` date NOT NULL,
  `email` varchar(30) NOT NULL,
  `celular` int(12) NOT NULL,
  `perfil` varchar(20) NOT NULL,
  `login` varchar(10) NOT NULL,
  `senha` varchar(16) NOT NULL,
  `status` enum('1','0') DEFAULT '1',
  PRIMARY KEY (`cd_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `cd_venda` int(5) NOT NULL AUTO_INCREMENT,
  `cd_usuario` int(5) NOT NULL,
  `qnt_livro` int(11) NOT NULL,
  `preco_unitario` float(12,2) NOT NULL,
  `data_venda` date NOT NULL,
  `cd_cupom` int(5) DEFAULT NULL,
  `cd_endereco` int(5) NOT NULL,
  PRIMARY KEY (`cd_venda`),
  KEY `cd_usuario` (`cd_usuario`),
  KEY `cd_cupom` (`cd_cupom`),
  KEY `cd_endereco` (`cd_endereco`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`cd_usuario`) REFERENCES `usuario` (`cd_usuario`),
  CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`cd_cupom`) REFERENCES `cupom` (`cd_cupom`),
  CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`cd_endereco`) REFERENCES `endereco` (`cd_endereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 16:22:27
