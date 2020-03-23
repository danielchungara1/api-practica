create table usuarios_roles(
  usuario_id bigint not null,
  rol_id bigint not null,

  created_at timestamp NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
  deleted_at TIMESTAMP NULL DEFAULT NULL,

  CONSTRAINT fk_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
  CONSTRAINT fk_roles FOREIGN KEY (rol_id) REFERENCES roles(id)

);