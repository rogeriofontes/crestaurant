create table `user` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`username` varchar(255) NOT NULL, 
	`password` varchar(255) NOT NULL, 
	`email` varchar(255) NOT NULL, 
	primary key (id)
);

insert into user (username, password, email) values ('rogeriofontes', '$2a$10$4q2I1/BSLFOx64ji5oDz2uH.ZLOtQFi9N821ILDmjxO7wgt/gagnS', 'fontestz@gmail.com');

create table `user_role` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`role_name` varchar(255), 
	`user_id` bigint,
	primary key (id)
);

insert into user_role (role_name, user_id) values ('USER', 1);

CREATE TABLE IF NOT EXISTS `restaurant_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `create_by` varchar(255) NOT NULL,
  `created_date` timestamp NOT NULL,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `restaurant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(500) NOT NULL,
  `address` varchar(150) NOT NULL,
  `rate` int(11) NOT NULL DEFAULT 0,
  `restaurant_type_id` bigint(20) NOT NULL,
  `create_by` varchar(255) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `plate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(500) NOT NULL,
  `on_menu` TINYINT(1) NOT NULL DEFAULT 0,
  `restaurant_id` bigint(20) NOT NULL,
  `create_by` varchar(255) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
