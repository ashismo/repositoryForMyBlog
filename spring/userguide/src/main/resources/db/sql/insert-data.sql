--INSERT INTO role (rolename,role_desc) VALUES ('ADMIN', 'Administrator Role');
--INSERT INTO role (rolename,role_desc) VALUES ('GENERAL', 'General Loggedin User');
--INSERT INTO users (username,name,password,role_id) VALUES ('ashish', 'Ashish Mondal', 'YXNoaXNo',1);
INSERT INTO users (username,name,password,role_id) VALUES ('kumar', 'Ashish Kumar Mondal', 'YXNoaXNo',2);
INSERT INTO users (username,name,password,role_id) VALUES ('mondal', 'Ashish Kumar Mondal', 'YXNoaXNo',1);
INSERT INTO environment_master (env_name,env_desc) VALUES ('DEV', 'Development');
INSERT INTO module (module_name,description) VALUES ('Desk', 'Desktop');
INSERT INTO module_env (module_id,env_id) VALUES (1, 1);
INSERT INTO url (name,description,url,module_id,env_id,role_id,username,password,visible) VALUES ('AA','A A','www.google.com',1, 1,1,'ashish','YXNoaXNo',true);