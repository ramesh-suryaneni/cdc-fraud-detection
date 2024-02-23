GRANT ALL PRIVILEGES ON *.* TO 'user';
CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

CREATE TABLE IF NOT EXISTS user_profile (
  user_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  surname VARCHAR(255),
  middle_name VARCHAR(255),
  suspicious_activity VARCHAR(255),
  PRIMARY KEY (user_id)
);
CREATE TABLE IF NOT EXISTS transactions (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT,
  amount VARCHAR(255),
  currency VARCHAR(255),
  type VARCHAR(255),
  country VARCHAR(255),
  timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user_profile(user_id)
);  