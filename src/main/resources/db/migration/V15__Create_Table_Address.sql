CREATE TABLE IF NOT EXISTS `address` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `city` varchar(255) DEFAULT NULL,
    `country` varchar(255) DEFAULT NULL,
    `state` varchar(255) DEFAULT NULL,
    `street` varchar(255) DEFAULT NULL,
    `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;