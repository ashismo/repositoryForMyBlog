INSERT INTO app_user (id, username, password, first_name, last_name) VALUES (1, 'ashish', '$2a$10$u0pzkxGOPKkq1iufN19OtO/tsT1vq0FI58RUMWYq7nG8bZJBLar3u',  'ashish', 'mondal');
INSERT INTO app_user (id, username, password, first_name, last_name) VALUES (2, 'admin', 'admin',  'admin', 'admin');


INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User has no admin rights.');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User has permission to admin task.');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);

