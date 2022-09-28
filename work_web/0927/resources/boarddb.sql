CREATE DATABASE  IF NOT EXISTS `boarddb`;
USE `boarddb`;

DROP TABLE IF EXISTS `board`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` VARCHAR(20) NOT NULL,
  `pw` VARCHAR(20) NOT NULL,
  `isActive` BOOL DEFAULT TRUE,
  PRIMARY KEY (`id`)
);

INSERT INTO `user` (`id`, `pw`)
VALUES ('admin','admin'),
	('ssafy','ssafy');

COMMIT;

CREATE TABLE `board` (
   `no`			INTEGER AUTO_INCREMENT PRIMARY KEY,
   `title`		VARCHAR(100),
   `content`	VARCHAR(4000),
   `user_id`	VARCHAR(100),
   `created_at`	TIMESTAMP NOT NULL,
   FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

INSERT INTO BOARD (title, content, user_id, created_at)
VALUES ('TITLE1', 'CONTENT-1', 'admin', NOW()),
	('TITLE2', 'CONTENT-2', 'admin', NOW()),
    ('TITLE3', 'CONTENT-3', 'admin', NOW()),
    ('TITLE4', 'CONTENT-4', 'admin', NOW()),
    ('TITLE5', 'CONTENT-5', 'admin', NOW()),
    ('TITLE6', 'CONTENT-6', 'admin', NOW()),
    ('TITLE7', 'CONTENT-7', 'admin', NOW()),
    ('TITLE8', 'CONTENT-8', 'admin', NOW()),
    ('TITLE9', 'CONTENT-9', 'admin', NOW());
    
COMMIT;