CREATE TABLE IF NOT EXISTS `CONTACT`
(
    `id`         INTEGER PRIMARY KEY AUTO_INCREMENT,
    `created_at` datetime(6) not null default now(),
    `deleted_at` datetime(6)          default null,
    `first_name` VARCHAR(30) NOT NULL,
    `last_name`  VARCHAR(30) NOT NULL,
    `number`    VARCHAR(20) NOT NULL
);