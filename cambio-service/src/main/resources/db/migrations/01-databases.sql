CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';

CREATE DATABASE IF NOT EXISTS `cambio_service`;
