CREATE TABLE app_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  PRIMARY KEY(id)
);


CREATE TABLE app_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  role_name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE user_role (
	
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT abcd FOREIGN KEY(user_id) REFERENCES app_user(id),
  CONSTRAINT pqrs FOREIGN KEY(role_id) REFERENCES app_role(id) 
  
);