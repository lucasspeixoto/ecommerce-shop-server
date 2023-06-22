CREATE TABLE IF NOT EXISTS `country` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
 );