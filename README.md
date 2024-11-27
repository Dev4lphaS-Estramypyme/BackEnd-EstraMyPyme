# Backend EstraMyPyme

## Descripción

El proyecto **EstraMyPyme** es el backend de una plataforma web diseñada para permitir a individuos y empresas realizar pruebas diagnósticas basadas en la metodología descrita en el libro *EstraMyPyme* de la Universidad EAFIT. Este backend proporciona funcionalidades para la gestión de usuarios, roles, preguntas de las pruebas, y permite la administración de resultados a través de un panel.

El objetivo principal es conectar un frontend con la base de datos, implementando un sistema CRUD robusto y funcional. El proyecto sigue los principios de la **S** de **SOLID** para garantizar una estructura limpia, escalable y fácilmente mantenible.

---

## Características

- **Gestión de usuarios**:
  - Registro, consulta, actualización y eliminación de usuarios.
  - Clasificación en tipos: naturales y jurídicos.
  - Manejo de roles: superadministradores, administradores, estudiantes y profesores.

- **Gestión de pruebas diagnósticas**:
  - Creación y modificación de pruebas con restricciones cada 6 meses.
  - Historial de cambios en las preguntas.

- **Descarga de resultados**:
  - Generación de resultados en formato PDF para consulta o almacenamiento.

- **Panel de administración**:
  - Visualización de datos organizados para la gestión eficiente de usuarios y pruebas.

---

## Tecnologías Utilizadas

El backend fue desarrollado con las siguientes herramientas:

- **Java**: Lenguaje principal para el desarrollo.
- **Spring Boot**: Framework para la creación de la API REST.
- **Spring Data JPA**: Para la gestión de datos.
- **MySQL**: Base de datos relacional utilizada para almacenar la información.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **Postman**: Para realizar pruebas de los endpoints de la API.
- **Visual Studio Code**: Entorno de desarrollo utilizado.

---

## Instalación

### Requisitos Previos

- **Java 11** o superior.
- **Maven** instalado.
- **MySQL Workbench** para gestionar la base de datos.
- **Clever Cloud** para gestionar la base de datos (Opcional).
- **Postman** para realizar pruebas de los endpoints.
- **Angular CLI** para correr el frontend.
- **Visual Studio Code** como entorno de desarrollo.

---

### Configuración Inicial

#### Paso 1: Clonar El Repositorio
Desde tu entorno de desarrollo, ejecuta el siguiente comando en la terminal:
```bash
Copiar codigo
git clone https://github.com/Dev4lphaS-Estramypyme/BackEnd-EstraMyPyme.git
Esto descargará el proyecto en tu entorno de desarrollo.

Paso 2: Crear la Base de Datos
Crea una base de datos llamada backnodo en MySQL Workbench:

sql
Copiar código
CREATE DATABASE backnodo;
Después, Crea las tablas como estan en el archivo db.sql, el cual se encuentra dentro del proyecto.

Paso 3: Configurar el Archivo application.properties
Edita el archivo src/main/resources/application.properties para incluir las credenciales de tu base de datos:

properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost/backnodo
spring.datasource.username=root
spring.datasource.password=(contraseña de tu localhost)
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Paso 4: Configurar CORS
Configura CORS en tu proyecto para permitir conexiones desde el frontend. Agrega esta clase en el paquete de configuración (config):

java
Copiar código
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") // Cambiar si el frontend se despliega en otra URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}


Paso 5: Iniciar el Servidor
Arranca el servidor con el siguiente comando:

bash
Copiar código
mvn spring-boot:run

Si utilizas Visual Studio Code, también puedes instalar la extensión Code Runner y ejecutar el proyecto seleccionando la opción Run Code en el archivo principal.

Paso 6: Clonar y Ejecutar el Frontend
Clona el proyecto del frontend ejecutando el siguiente comando:

bash
Copiar código
git clone https://github.com/Dev4lphaS-Estramypyme/FrontEnd-EstraMyPyme.git
Navega al directorio del proyecto clonado y ejecuta:

bash
Copiar código
npm install
ng serve
Esto levantará el servidor del frontend en http://localhost:4200.

 Paso 7: Uso de Postman
Para realizar pruebas de los endpoints de la API, puedes acceder a la colección de Postman creada para este proyecto.

Abre Postman.
Importa la colección de endpoints de EstraMyPyme utilizando el siguiente enlace:
https://estramypymebackend.postman.co/workspace/EstraMyPyme_Backend~828e4b65-e033-47ad-90fa-104e2d4f6b78/collection/38604144-8ce70132-6cab-4bff-9816-704a8e84d3eb?action=share&creator=38664908

Esto te proporcionará acceso a todos los endpoints preconfigurados para que puedas realizar las pruebas necesarias de la API.

Buenas Prácticas de Commits
Seguimos un estándar para los mensajes de commit, asegurando un historial limpio y entendible:

feat: Agregar una nueva funcionalidad.
Ejemplo: git commit -m "feat(user): agregar registro de usuarios"
fix: Solución de errores.
Ejemplo: git commit -m "fix(auth): corregir validación de contraseñas"
docs: Actualización de documentación.
Ejemplo: git commit -m "docs(readme): agregar instrucciones de instalación"
refactor: Cambios en el código sin modificar la funcionalidad.
Ejemplo: git commit -m "refactor(service): optimizar consultas a la base de datos"
Estrategia de Ramificación
Ramas Utilizadas
main: Rama principal que contiene la versión estable del proyecto.
task-{ID}-{descripción}: Ramas específicas para tareas.
Ejemplo: git checkout -b task-123-crear-endpoint-usuarios
Repositorio
El código fuente está disponible en el siguiente repositorio:
Repositorio en GitHub

Autor
El proyecto fue creado utilizando Spring Boot para generar la base del backend y desarrollado en Visual Studio Code. La implementación se enfoca en conectar un frontend con una base de datos MySQL, cumpliendo con los requisitos funcionales mediante un sistema CRUD completo.
