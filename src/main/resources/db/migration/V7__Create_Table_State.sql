CREATE TABLE IF NOT EXISTS `state` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) DEFAULT NULL,
   `country_id` BIGINT(20) NOT NULL,
   PRIMARY KEY (`id`)
 );