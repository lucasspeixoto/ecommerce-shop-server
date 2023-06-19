CREATE TABLE IF NOT EXISTS `state` (
   `id` smallint unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(255) DEFAULT NULL,
   `country_id` smallint unsigned NOT NULL,
   PRIMARY KEY (`id`)
 );