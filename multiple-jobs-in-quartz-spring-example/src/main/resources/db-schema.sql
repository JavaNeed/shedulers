drop table if exists `customer`;
CREATE TABLE `customer` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `STATUS` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


insert into customer(id, name, status) values (1, "Joe", "NEW");
insert into customer(id, name, status) values (2, "Sam", "NEW");
insert into customer(id, name, status) values (3, "Rahul", "NEW");
insert into customer(id, name, status) values (4, "Savani", "NEW");
