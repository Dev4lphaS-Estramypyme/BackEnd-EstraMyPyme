# Estramypyme Backend


## Descripción

Este es el backend de la plataforma web **EstraMyPyme**, que permite a individuos y empresas realizar pruebas diagnósticas basadas en la metodología descrita en el libro "EstraMyPyme" de la Universidad EAFIT. La API gestiona la autenticación de usuarios, roles, preguntas de las pruebas, y proporciona un panel de administración para la visualización de resultados. Las pruebas diagnósticas pueden modificarse, tienen historial, y pueden realizarse cada 6 meses. El backend está desarrollado siguiendo los principios de la S de **SOLID** para garantizar una estructura limpia y escalable.

## Características

- **Autenticación y autorización**: Utiliza JSON Web Tokens (JWT) para manejar el inicio de sesión y la autorización de usuarios.
- **CRUD de usuarios**: Administradores pueden gestionar usuarios y sus roles.
- **Pruebas diagnósticas**: Creación, modificación y gestión de las pruebas, que pueden realizarse cada 6 meses.
- **Historial de preguntas**: Las preguntas de las pruebas pueden ser modificadas y tienen un historial de cambios.
- **Descarga de resultados en PDF**.
- **Roles**: Existen superadministradores que pueden asignar permisos a otros administradores, así como roles para estudiantes, profesores y administradores.
- **Tipos de usuarios**: Se gestionan tanto usuarios naturales como jurídicos.

## Tecnologías Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON-web-tokens&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## Instalación

### Pre-rrequisitos

- Java 11+
- Maven
- MySQL



## Buenas Prácticas de Commits
Al realizar commits, seguimos las siguientes convenciones para mantener un historial limpio y organizado:

### feat: Se utiliza para agregar una nueva característica.

Ejemplo: git commit -m "feat(arquitectura): creación de la estructura del proyecto aplicando principios SOLID"
fix: Se usa para corregir un bug.

Ejemplo: git commit -m "fix(login): corregir error de autenticación"
perf: Para optimizar el rendimiento.

Ejemplo: git commit -m "perf(api): optimización en la consulta de pruebas"
build: Para cambios en la construcción o despliegue.

Ejemplo: git commit -m "build(deploy): agregar configuración para el pipeline de despliegue"
ci: Se utiliza para cambios en la integración continua.

Ejemplo: git commit -m "ci(pipeline): ajustar configuración de CI"
docs: Para cambios en la documentación.

Ejemplo: git commit -m "docs(readme): agregar detalles de instalación"
refactor: Refactorización del código sin cambios funcionales.

Ejemplo: git commit -m "refactor(auth): renombrar métodos en el servicio de autenticación"
style: Cambios de estilo, como espacios o tabulaciones.

Ejemplo: git commit -m "style(main): ajustes en el formato del código"
test: Para agregar o modificar pruebas.

Ejemplo: git commit -m "test(auth): agregar pruebas unitarias para autenticación"


## Estrategia de Ramificación

Las ramas se nombrarán en base al ID de las tareas asignadas en Azure DevOps, asegurando un control claro del trabajo. La estructura de ramas será la siguiente:

main: Rama principal.

### task-ID-Título-de-la-tarea: Ramas para cada tarea específica.
Por ejemplo, si se está trabajando en la tarea con el ID 123 para la arquitectura del proyecto, la rama sería:
git checkout -b task-123-Arquitectura-del-proyecto
