CREATE TABLE `proselyte`.`skill`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `skill_name`   VARCHAR(100) NOT NULL,
    `skill_developer_id` INT          NOT NULL,
     PRIMARY KEY (`id`),
     CONSTRAINT `skill_developer_id`
         FOREIGN KEY (`skill_developer_id`)
             REFERENCES `proselyte`.`developers` (`id`)
);