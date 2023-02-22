CREATE TABLE `proselyte`.`specialty`
(
    `id`                     INT          NOT NULL AUTO_INCREMENT,
    `skill_name`             VARCHAR(100) NOT NULL,
    `specialty_developer_id` INT          NOT NULL,
     PRIMARY KEY (`id`),
     CONSTRAINT `developer_id`
         FOREIGN KEY (`specialty_developer_id`)
             REFERENCES `proselyte`.`developers` (`id`)
);