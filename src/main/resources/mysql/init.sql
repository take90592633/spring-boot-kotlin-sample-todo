CREATE TABLE `todos`
(
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `text` VARCHAR(255) NULL DEFAULT NULL COLLATE,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;