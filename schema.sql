
CREATE TABLE PERSONA (
                         codigo          VARCHAR(20) PRIMARY KEY,
                         cedula          VARCHAR(15) NOT NULL UNIQUE,
                         nombres         VARCHAR(100) NOT NULL,
                         apellidos       VARCHAR(100) NOT NULL,
                         fecha_nacimiento DATE NOT NULL,
                         genero          CHAR(1) NOT NULL CHECK (genero IN ('M', 'F')),
                         telefono        VARCHAR(20),
                         direccion       VARCHAR(200),
                         email           VARCHAR(100)
);

CREATE TABLE USUARIO (
                         nombre_usuario  VARCHAR(20) PRIMARY KEY,
                         contrasena      VARCHAR(100) NOT NULL,
                         tipo            VARCHAR(20) NOT NULL CHECK (tipo IN ('Medico', 'Paciente', 'Administrador')),
                         codigo_persona  VARCHAR(20) NOT NULL UNIQUE REFERENCES PERSONA(codigo)
);

CREATE TABLE MEDICO (
                        codigo          VARCHAR(20) PRIMARY KEY REFERENCES PERSONA(codigo),
                        especialidad    VARCHAR(100) NOT NULL,
                        max_citas       INT NOT NULL DEFAULT 5,
                        activo          BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE PACIENTE (
                          codigo          VARCHAR(20) PRIMARY KEY REFERENCES PERSONA(codigo),
                          tipo_sangre     VARCHAR(5)
);

CREATE TABLE CITA (
                      codigo          VARCHAR(20) PRIMARY KEY,
                      codigo_persona  VARCHAR(20) NOT NULL REFERENCES PERSONA(codigo),
                      codigo_medico   VARCHAR(20) NOT NULL REFERENCES MEDICO(codigo),
                      fecha           DATE NOT NULL,
                      estado          BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE DIAGNOSTICO (
                             codigo          VARCHAR(20) PRIMARY KEY,
                             fecha           DATE NOT NULL,
                             tratamiento     TEXT
);

CREATE TABLE CONSULTA (
                          codigo              VARCHAR(20) PRIMARY KEY REFERENCES CITA(codigo),
                          precio              NUMERIC(10, 2) NOT NULL,
                          visibilidad         BOOLEAN NOT NULL DEFAULT FALSE,
                          codigo_diagnostico  VARCHAR(20) NOT NULL UNIQUE REFERENCES DIAGNOSTICO(codigo)
);


CREATE TABLE ENFERMEDAD (
                            codigo          VARCHAR(20) PRIMARY KEY,
                            nombre          VARCHAR(150) NOT NULL,
                            tratamiento     TEXT,
                            tipo            VARCHAR(20) NOT NULL CHECK (tipo IN ('Virus', 'Parasito', 'Bacteria')),
                            controlada      BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE VACUNA (
                        codigo              VARCHAR(20) PRIMARY KEY,
                        nombre              VARCHAR(150) NOT NULL,
                        descripcion         TEXT,
                        codigo_enfermedad   VARCHAR(20) NOT NULL UNIQUE REFERENCES ENFERMEDAD(codigo)
);

CREATE TABLE SINTOMA (
                         codigo          VARCHAR(20) PRIMARY KEY,
                         nombre          VARCHAR(150) NOT NULL UNIQUE
);

/*Crossed tables*/
CREATE TABLE ENFERMEDAD_SINTOMA (
                                    codigo_enfermedad   VARCHAR(20) REFERENCES ENFERMEDAD(codigo) ON DELETE CASCADE,
                                    codigo_sintoma      VARCHAR(20) REFERENCES SINTOMA(codigo) ON DELETE CASCADE,
                                    PRIMARY KEY (codigo_enfermedad, codigo_sintoma)
);

CREATE TABLE DIAGNOSTICO_SINTOMA (
                                     codigo_diagnostico  VARCHAR(20) REFERENCES DIAGNOSTICO(codigo) ON DELETE CASCADE,
                                     codigo_sintoma      VARCHAR(20) REFERENCES SINTOMA(codigo) ON DELETE CASCADE,
                                     PRIMARY KEY (codigo_diagnostico, codigo_sintoma)
);

CREATE TABLE PACIENTE_ENFERMEDAD (
                                     codigo_paciente     VARCHAR(20) REFERENCES PACIENTE(codigo) ON DELETE CASCADE,
                                     codigo_enfermedad   VARCHAR(20) REFERENCES ENFERMEDAD(codigo) ON DELETE CASCADE,
                                     PRIMARY KEY (codigo_paciente, codigo_enfermedad)
);

CREATE TABLE PACIENTE_VACUNA (
                                 codigo_paciente     VARCHAR(20) REFERENCES PACIENTE(codigo) ON DELETE CASCADE,
                                 codigo_vacuna       VARCHAR(20) REFERENCES VACUNA(codigo) ON DELETE CASCADE,
                                 aplicada            BOOLEAN NOT NULL DEFAULT FALSE,
                                 fecha               DATE,
                                 PRIMARY KEY (codigo_paciente, codigo_vacuna)
);

CREATE TABLE MEDICO_VACUNA (
                               codigo_medico       VARCHAR(20) REFERENCES MEDICO(codigo) ON DELETE CASCADE,
                               codigo_vacuna       VARCHAR(20) REFERENCES VACUNA(codigo) ON DELETE CASCADE,
                               PRIMARY KEY (codigo_medico, codigo_vacuna)
);