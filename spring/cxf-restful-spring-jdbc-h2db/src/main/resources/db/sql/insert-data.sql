INSERT INTO role (rolename,role_desc) VALUES ('ADMIN', 'Administrator Role');
INSERT INTO users (username,name,password,role_id) VALUES ('ashish', 'Ashish Mondal', 'ashish',1);
INSERT INTO users (username,name,password) VALUES ('kumar', 'Ashish Kumar Mondal', 'ashish');
INSERT INTO users (username,name,password) VALUES ('mondal', 'Ashish Kumar Mondal', 'ashish');
INSERT INTO environment_master (env_name,env_desc) VALUES ('DEV', 'Development');
INSERT INTO module (module_name,description) VALUES ('iDesk', 'Desktop');
INSERT INTO module_env (module_id,env_id) VALUES (1, 1);
