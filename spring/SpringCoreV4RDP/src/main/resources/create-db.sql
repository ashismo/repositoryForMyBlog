CREATE TABLE IF NOT EXISTS users (
  user_id INTEGER auto_increment PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(50) NOT NULL,
  role_id INTEGER,
  email  VARCHAR(50),
  create_user VARCHAR(100),
  create_date TIMESTAMP,
  update_user VARCHAR(100),
  update_date TIMESTAMP  
);

CREATE TABLE IF NOT EXISTS role (
  role_id INTEGER auto_increment PRIMARY KEY,
  rolename VARCHAR(50),
  role_desc VARCHAR(100),
  create_user VARCHAR(100),
  create_date TIMESTAMP,
  update_user VARCHAR(100),
  update_date TIMESTAMP
);

INSERT INTO role (role_id, rolename,role_desc) VALUES (1, 'ADMIN', 'Administrator Role');
INSERT INTO role (role_id, rolename,role_desc) VALUES (2, 'GENERAL', 'General Loggedin User');
INSERT INTO users (username,name,password,role_id) VALUES ('ashish', 'Ashish Mondal', 'ashish',1);