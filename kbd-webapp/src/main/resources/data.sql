CREATE TABLE `temperature` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`temp_code` VARCHAR(20) NOT NULL COLLATE 'utf8_unicode_ci',
	`celsius` VARCHAR(10) NOT NULL COLLATE 'utf8_unicode_ci',
	`light` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`electric` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`humidity` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`color` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`x` INT(11) NULL DEFAULT NULL,
	`y` INT(11) NULL DEFAULT NULL,
	`create_time` BIGINT(20) NULL DEFAULT NULL,
	`update_time` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;

CREATE TABLE `temperature_history` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`temp_code` VARCHAR(20) NOT NULL COLLATE 'utf8_unicode_ci',
	`celsius` VARCHAR(10) NOT NULL COLLATE 'utf8_unicode_ci',
	`light` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`electric` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`humidity` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`create_time` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;
