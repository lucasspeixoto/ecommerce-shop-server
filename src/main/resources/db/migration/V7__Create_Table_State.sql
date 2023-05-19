CREATE TABLE IF NOT EXISTS `state` (
   `id` smallint unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(255) DEFAULT NULL,
   `country_id` smallint unsigned NOT NULL,
   PRIMARY KEY (`id`),
   KEY `fk_country` (`country_id`),
   CONSTRAINT `fk_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
 );