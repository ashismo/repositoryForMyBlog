DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  skills VARCHAR(250) DEFAULT NULL
);

INSERT INTO employees (first_name, last_name, skills) VALUES
  ('Ashish', 'Mondal', 'Java'),
  ('Dona', 'M', 'Angular'),
  ('Aakash', 'Bhatt', 'API');