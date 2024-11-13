CREATE DATABASE backnodo;

-- Tabla de usuarios
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    active BOOLEAN DEFAULT TRUE,  -- Indica si el usuario está activo
    role_name ENUM('Admin', 'Student', 'Teacher') NOT NULL
);

-- Tabla principal para los usuarios de empresas (clientes)
CREATE TABLE users_companies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identification_number INT NOT NULL,  -- Cédula o NIT
    name_or_business_name VARCHAR(255) NOT NULL,  -- Nombre o Razón Social
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    type_user ENUM('Natural', 'Juridico') NOT NULL,  -- Tipos de usuarios
    company_size ENUM('Pequeña', 'Mediana', 'Grande') NOT NULL,  -- Tamaño de la compañía
    sector ENUM('Sector Agrícola', 'Sector Industrial', 'Sector Servicios', 'Sector Construcción') NOT NULL,  -- Sector de la compañía
    registration_date DATE,
    active BOOLEAN DEFAULT TRUE,  -- Indica si el usuario está activo
    is_book_downloaded BOOLEAN DEFAULT FALSE  -- Indica si el libro ha sido descargado
);

-- Tabla de tests realizados por las empresas
CREATE TABLE tests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company_id INT NOT NULL,
    test_date DATE,
    is_reviewed BOOLEAN DEFAULT FALSE,  -- Indica si el test ha sido revisado
    FOREIGN KEY (company_id) REFERENCES users_companies(id)
);

-- Tabla de preguntas generales
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT NOT NULL,
    created_at DATE,  -- Fecha de creación
    active BOOLEAN DEFAULT TRUE  -- Indica si la pregunta está activa
);

-- Tabla de asignaciones de tests a revisores (pueden ser admins, estudiantes o profesores)
CREATE TABLE test_assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    test_id INT NOT NULL,
    user_id INT NOT NULL,  -- ID del revisor (referencia a la tabla users)
    assignment_date DATE,
    review_completed BOOLEAN DEFAULT FALSE,  -- Indica si el revisor completó la revisión
    FOREIGN KEY (test_id) REFERENCES tests(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Tabla de relación entre tests y preguntas
CREATE TABLE test_questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    test_id INT NOT NULL,
    question_id INT NOT NULL,
    FOREIGN KEY (test_id) REFERENCES tests(id),
    FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- Tabla de respuestas a las preguntas
CREATE TABLE answers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    test_id INT NOT NULL,
    question_id INT NOT NULL,
    answer TEXT NOT NULL,
    FOREIGN KEY (test_id) REFERENCES tests(id),
    FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- Insertar datos en la tabla de usuarios
INSERT INTO users (email, password, name, active, role_name) VALUES
('admin@example.com', 'adminpassword', 'Admin User', TRUE, 'Admin'),
('student@example.com', 'studentpassword', 'Student User', TRUE, 'Student'),
('teacher@example.com', 'teacherpassword', 'Teacher User', TRUE, 'Teacher');

-- Insertar datos en la tabla de usuarios de empresas
INSERT INTO users_companies (identification_number, name_or_business_name, email, password, type_user, company_size, sector, registration_date, active, is_book_downloaded) VALUES
(123456789, 'Company A', 'companya@example.com', 'companyapassword', 'Juridico', 'Mediana', 'Sector Servicios', '2023-01-01', TRUE, FALSE),
(987654321, 'Company B', 'companyb@example.com', 'companybpassword', 'Natural', 'Pequeña', 'Sector Agrícola', '2023-02-01', TRUE, TRUE);

-- Insertar datos en la tabla de tests
INSERT INTO tests (company_id, test_date, is_reviewed) VALUES
(1, '2023-03-01', FALSE),
(2, '2023-04-01', TRUE);

-- Insertar datos en la tabla de preguntas
INSERT INTO questions (question, created_at, active) VALUES
('What is your company size?', '2023-01-01', TRUE),
('What sector does your company belong to?', '2023-01-02', TRUE);

-- Insertar datos en la tabla de asignaciones de tests a revisores
INSERT INTO test_assignments (test_id, user_id, assignment_date, review_completed) VALUES
(1, 1, '2023-03-02', FALSE),
(2, 2, '2023-04-02', TRUE);

-- Insertar datos en la tabla de relación entre tests y preguntas
INSERT INTO test_questions (test_id, question_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2);

-- Insertar datos en la tabla de respuestas a las preguntas
INSERT INTO answers (test_id, question_id, answer) VALUES
(1, 1, 'Medium'),
(1, 2, 'Services'),
(2, 1, 'Small'),
(2, 2, 'Agriculture');