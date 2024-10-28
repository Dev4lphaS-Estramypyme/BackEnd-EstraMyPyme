-- Active: 1730133573426@@bex8rslfxuyzyfudg6hk-mysql.services.clever-cloud.com@3306
/* conexion al servidor
Host: bex8rslfxuyzyfudg6hk-mysql.services.clever-cloud.com
Database: bex8rslfxuyzyfudg6hk
User: umjcsvke5lr7svzt
Password: m9IRespcSBvvmEWpCxYq
Port: 3306 */


-- Tabla de usuarios
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    active BOOLEAN DEFAULT TRUE,  -- Indica si el usuario está activo
    roleName ENUM('Admin', 'Student', 'Teacher') NOT NULL
);
-- Tabla principal para los usuarios de empresas (clientes)
CREATE TABLE usersCompanies (
    id VARCHAR(50) PRIMARY KEY,
    identificationNumber VARCHAR(50) NOT NULL,  -- Cédula o NIT
    nameOrBusinessName VARCHAR(255) NOT NULL,  -- Nombre o Razón Social
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    typeUser ENUM('Natural', 'Juridico') NOT NULL,  -- Tipos de usuarios
    companySize ENUM('Pequeña', 'Mediana', 'Grande') NOT NULL,  -- Tamaño de la compañía
    sector ENUM('Sector Agrícola', 'Sector Industrial', 'Sector Servicios', 'Sector Construcción') NOT NULL,  -- Sector de la compañía
    registrationDate DATE,
    active BOOLEAN DEFAULT TRUE,  -- Indica si el usuario está activo
    isBookDownloaded BOOLEAN DEFAULT FALSE  -- Indica si el libro ha sido descargado
);

-- Tabla de tests realizados por las empresas
CREATE TABLE tests (
    id VARCHAR(50) PRIMARY KEY,
    companyId VARCHAR(50),
    testDate DATE,
    isReviewed BOOLEAN DEFAULT FALSE,  -- Indica si el test ha sido revisado
    FOREIGN KEY (companyId) REFERENCES usersCompanies(id)
);

-- Tabla de preguntas generales
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT NOT NULL,
    createdAt DATE,  -- Fecha de creación
    active BOOLEAN DEFAULT TRUE  -- Indica si la pregunta está activa
);

-- Tabla de asignaciones de tests a revisores (pueden ser admins, estudiantes o profesores)
CREATE TABLE testAssignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    testId VARCHAR(50) NOT NULL,
    UserId VARCHAR(50) NOT NULL,  -- ID del revisor (referencia a la tabla users)
    assignmentDate DATE,
    reviewCompleted BOOLEAN DEFAULT FALSE,  -- Indica si el revisor completó la revisión
    FOREIGN KEY (testId) REFERENCES tests(id),
    FOREIGN KEY (UserId) REFERENCES users(id)
);

-- Tabla de relación entre tests y preguntas
CREATE TABLE testQuestions (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    testId VARCHAR(50) NOT NULL,
    questionId INT NOT NULL,
    FOREIGN KEY (testId) REFERENCES tests(id),
    FOREIGN KEY (questionId) REFERENCES questions(id)
);

-- Insertar datos en la tabla de usuarios
INSERT INTO users (id, email, password, name, active, roleName) VALUES
('1', 'admin@example.com', 'adminpassword', 'Admin User', TRUE, 'Admin'),
('2', 'student@example.com', 'studentpassword', 'Student User', TRUE, 'Student'),
('3', 'teacher@example.com', 'teacherpassword', 'Teacher User', TRUE, 'Teacher');

-- Insertar datos en la tabla de usuarios de empresas
INSERT INTO usersCompanies (id, identificationNumber, nameOrBusinessName, email, password, typeUser, companySize, sector, registrationDate, active, isBookDownloaded) VALUES
('1', '123456789', 'Company A', 'companya@example.com', 'companyapassword', 'Juridico', 'Mediana', 'Sector Servicios', '2023-01-01', TRUE, FALSE),
('2', '987654321', 'Company B', 'companyb@example.com', 'companybpassword', 'Natural', 'Pequeña', 'Sector Agrícola', '2023-02-01', TRUE, TRUE);

-- Insertar datos en la tabla de tests
INSERT INTO tests (id, companyId, testDate, isReviewed) VALUES
('1', '1', '2023-03-01', FALSE),
('2', '2', '2023-04-01', TRUE);

-- Insertar datos en la tabla de preguntas
INSERT INTO questions (question, createdAt, active) VALUES
('What is your company size?', '2023-01-01', TRUE),
('What sector does your company belong to?', '2023-01-02', TRUE);

-- Insertar datos en la tabla de asignaciones de tests a revisores
INSERT INTO testAssignments (testId, UserId, assignmentDate, reviewCompleted) VALUES
('1', '1', '2023-03-02', FALSE),
('2', '2', '2023-04-02', TRUE);

-- Insertar datos en la tabla de relación entre tests y preguntas
INSERT INTO testQuestions (testId, questionId) VALUES
('1', 1),
('1', 2),
('2', 1),
('2', 2);