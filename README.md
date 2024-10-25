# Sistema de Gestión de Reclamos
Versión 1.0.0

## Descripción General

El Sistema de Gestión de Reclamos es una aplicación empresarial desarrollada para administrar y dar seguimiento a reclamos de clientes. Implementada utilizando Java 17 y Spring Boot 3.2.10, proporciona una solución robusta para el registro, seguimiento y resolución de reclamos de clientes.

## Índice

1. [Requerimientos del Sistema](#requerimientos-del-sistema)
2. [Arquitectura](#arquitectura)
3. [Instalación](#instalación)
4. [Configuración](#configuración)
5. [Estructura del Proyecto](#estructura-del-proyecto)
6. [Base de Datos](#base-de-datos)
7. [API](#api)
8. [Pruebas](#pruebas)
9. [Despliegue](#despliegue)
10. [Mantenimiento](#mantenimiento)

## Requerimientos del Sistema

### Software
- Java Development Kit (JDK) 17 o superior
- Apache Maven 3.8+
- PostgreSQL 12+
- Docker (opcional)
- Google Cloud SDK (para despliegue)

## Arquitectura

### Componentes Principales
- Spring Boot 3.2.10
- Spring Data JPA
- Spring MVC
- PostgreSQL
- Swagger/OpenAPI 3.0
- Google Cloud Run

### Patrones de Diseño Implementados
- DTO (Data Transfer Object)
- Repository Pattern
- Service Layer
- MVC (Model-View-Controller)
- Builder Pattern (para pruebas)

## Instalación

1. Clonar el repositorio
```bash
git clone [URL_REPOSITORIO]
```

2. Compilar el proyecto
```bash
mvn clean install
```

3. Crear esquema de base de datos
```sql
CREATE SCHEMA claims_schema;
```

4. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

## Configuración

### Archivo application.properties
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server
server.port=8089
```

### Perfiles de Ejecución
- development
- test
- production

## Estructura del Proyecto

```plaintext
claims-management/
├── migrations/
│   ├── extensions/
│   ├── schemas/
│   ├── sequences/
│   └── tables/
│       ├── claims/
│       ├── claim_status/
│       └── claim_attachments/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/claims/
│   │   │   ├── config/
│   │   │   ├── constants/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── mapper/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   │   ├── impl/
│   │   │   │   └── util/
│   │   │   └── validation/
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       │   └── com/example/claims/
│       │       ├── controller/
│       │       ├── service/
│       │       └── util/
│       └── resources/
│
├── .gitignore
├── Dockerfile
├── pom.xml
└── README.md
```

## Base de Datos

### Modelo de Datos
- claims: Tabla principal de reclamos
- claim_status: Historial de estados
- claim_attachments: Archivos adjuntos

### Secuencias
```sql
CREATE SEQUENCE claims_schema.claim_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
```

### Índices
```sql
CREATE INDEX idx_claims_creation_date ON claims_schema.claims(creation_date DESC);
CREATE INDEX idx_claim_status_claim_id ON claims_schema.claim_status(claim_id);
```

## API

### Documentación
- Swagger UI (Producción): [https://claims-api-740392458099.us-central1.run.app/api/swagger-ui/index.html](https://claims-api-740392458099.us-central1.run.app/api/swagger-ui/index.html)
- OpenAPI Spec (Producción): [https://claims-api-740392458099.us-central1.run.app/api/api-docs](https://claims-api-740392458099.us-central1.run.app/api/api-docs)

### Endpoints Principales
| Método | URL | Descripción |
|--------|-----|-------------|
| POST | /claims | Crear reclamo |
| GET | /claims | Listar reclamos |
| GET | /claims/{id} | Obtener detalle |
| POST | /claims/{id}/status | Actualizar estado |
| GET | /master-data | Obtener datos maestros |
| GET | /health | Health check |

## Pruebas

### Tipos de Pruebas
1. **Pruebas Unitarias**
    - ClaimServiceTest
    - ClaimCodeServiceTest
    - ClaimControllerTest

### Ejecución
```bash
# Todas las pruebas
mvn test

# Pruebas específicas
mvn test -Dtest=ClaimServiceTest
```

## Despliegue

### Google Cloud Run

1. Construir imagen
```bash
gcloud builds submit --tag gcr.io/[PROJECT_ID]/claims-api
```

2. Desplegar
```bash
gcloud run deploy claims-api \
  --image gcr.io/[PROJECT_ID]/claims-api \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated \
  --memory 512Mi \
  --set-env-vars="SPRING_PROFILES_ACTIVE=prod"
```

### Docker Local
```bash
# Construir
docker build -t claims-api .

# Ejecutar
docker run -p 8089:8089 claims-api
```

---
**Nota**: Este sistema es una prueba de concepto.

Última actualización: Octubre 2024