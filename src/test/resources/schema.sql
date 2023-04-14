CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255)  ,
  first_name VARCHAR(255)  ,
  is_blocked BOOLEAN  ,
  last_name VARCHAR(255)  ,
  password VARCHAR(255)  ,
  reg_date TIMESTAMP  ,
  username VARCHAR(255)  ,
  UNIQUE (email),
  UNIQUE (username)
);

CREATE TABLE roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(255),
  name VARCHAR(255)  ,
  UNIQUE (name)
);

CREATE TABLE tasks (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  creation_date TIMESTAMP  ,
  deadline TIMESTAMP  ,
  description VARCHAR(255)  ,
  title VARCHAR(255)  ,
  assignee_id BIGINT,
  creator_id BIGINT,
  FOREIGN KEY (assignee_id) REFERENCES users (id),
  FOREIGN KEY (creator_id) REFERENCES users (id)
);

CREATE TABLE user_roles (
  user_id BIGINT  ,
  role_id BIGINT  ,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (role_id) REFERENCES roles (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);