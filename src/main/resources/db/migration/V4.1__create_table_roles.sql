CREATE TABLE roles (
  id bigint NOT NULL AUTO_INCREMENT,
  nombre varchar(255) NOT NULL,
  description varchar(255) NOT NULL,

  created_at timestamp NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
  deleted_at TIMESTAMP NULL DEFAULT NULL,

  PRIMARY KEY (id),
  UNIQUE(nombre)
);