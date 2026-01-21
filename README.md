# Task Management API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green.svg)](https://spring.boot.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> REST API completa per la gestione di task con autenticazione JWT, sviluppata come progetto di studio per approfondire Spring Boot, Spring Security e architetture REST.

## 📋 Indice

- [Panoramica](#panoramica)
- [Funzionalità](#funzionalità)
- [Tecnologie Utilizzate](#tecnologie-utilizzate)
- [Architettura](#architettura)
- [Installazione](#installazione)
- [Configurazione](#configurazione)
- [API Endpoints](#api-endpoints)
- [Esempi di Utilizzo](#esempi-di-utilizzo)
- [Obiettivi di Apprendimento](#obiettivi-di-apprendimento)
- [Sviluppi Futuri](#sviluppi-futuri)
- [Contatti](#contatti)

## 🎯 Panoramica

Task Management API è un sistema backend completo per la gestione di task e categorie, sviluppato per consolidare le competenze in:
- Sviluppo di REST API con Spring Boot
- Autenticazione e autorizzazione con JWT
- Gestione database con JPA/Hibernate
- Architettura a livelli (Controller-Service-Repository)
- Sicurezza applicativa e best practices

## ✨ Funzionalità

### Autenticazione e Sicurezza
- ✅ Registrazione utenti con validazione email
- ✅ Login con generazione JWT token
- ✅ Password hashing con BCrypt
- ✅ Protezione endpoint con Spring Security
- ✅ Token-based authentication (stateless)

### Gestione Task
- ✅ CRUD completo per task (Create, Read, Update, Delete)
- ✅ Associazione task a categorie
- ✅ Filtri e ricerca
- ✅ Timestamp automatico creazione
- ✅ Gestione stato completamento

### Gestione Categorie
- ✅ Sistema di categorizzazione task
- ✅ Colori personalizzati per categorie
- ✅ Relazioni bidirezionali ottimizzate

### Validazione e Sicurezza
- ✅ Bean Validation per input utente
- ✅ Gestione errori centralizzata
- ✅ Validazione lunghezza campi

## 🛠 Tecnologie Utilizzate

### Backend
- **Java 17** - Linguaggio di programmazione
- **Spring Boot 3.2** - Framework applicativo
- **Spring Security** - Autenticazione e autorizzazione
- **Spring Data JPA** - Persistenza dati
- **Hibernate** - ORM

### Database
- **MySQL 8.0** - Database relazionale

### Sicurezza
- **JWT (JSON Web Token)** - Autenticazione stateless
- **BCrypt** - Hashing password

### Librerie
- **JJWT** - Gestione JWT
- **Bean Validation** - Validazione input
- **Lombok** (opzionale) - Riduzione boilerplate

### Tools
- **Maven** - Build automation
- **Postman** - Test API
- **MySQL Workbench** - Gestione database

## 🏗 Architettura

Il progetto segue un'architettura a livelli (Layered Architecture):
```
┌─────────────────────────────────────┐
│         Controller Layer            │  ← REST endpoints
│  (TaskController, AuthController)   │
├─────────────────────────────────────┤
│          Service Layer              │  ← Business logic
│    (TaskService, AuthService)       │
├─────────────────────────────────────┤
│        Repository Layer             │  ← Data access
│  (TaskRepository, UserRepository)   │
├─────────────────────────────────────┤
│         Security Layer              │  ← JWT Filter
│      (JwtUtil, JwtFilter)           │
├─────────────────────────────────────┤
│        Database (MySQL)             │  ← Persistence
└─────────────────────────────────────┘
```

### Design Patterns Implementati
- **Repository Pattern** - Astrazione accesso dati
- **Dependency Injection** - Loose coupling
- **DTO Pattern** - Separazione concerns (opzionale)
- **Singleton** - Spring beans
- **Filter Pattern** - JWT authentication

## 📦 Installazione

### Prerequisiti
- JDK 17 o superiore
- Maven 3.6+
- MySQL 8.0+
- Postman (per testing)

### Step 1: Clone del Repository
```bash
git clone https://github.com/tuo-username/task-management-api.git
cd task-management-api
```

### Step 2: Configurazione Database
```sql
CREATE DATABASE taskdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Step 3: Configurazione Application
Crea/modifica `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tua_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080
```

### Step 4: Build e Run
```bash
# Build del progetto
mvn clean install

# Avvio applicazione
mvn spring-boot:run
```

L'applicazione sarà disponibile su: `http://localhost:8080`

## ⚙️ Configurazione

### Opzioni `spring.jpa.hibernate.ddl-auto`
- `create` - Ricrea schema ad ogni avvio (perde dati)
- `update` - Aggiorna schema preservando dati ✅ Consigliato
- `validate` - Solo validazione schema
- `none` - Nessuna azione

### Inizializzazione Dati
Al primo avvio, `DataInitializer` popola il database con:
- 4 categorie predefinite (Lavoro, Personale, Studio, Urgente)
- 5 task di esempio

## 🔌 API Endpoints

### Autenticazione (Public)

#### Registrazione
```http
POST /api/auth/register
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "password123"
}
```

**Response:**
```json
{
    "message": "Registrazione completata con successo"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "password123"
}
```

**Response:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer",
    "message": "Login effettuato con successo"
}
```

### Task Management (Protected)

> **Nota:** Tutti gli endpoint task richiedono header `Authorization: Bearer {token}`

#### Lista Task
```http
GET /api/tasks
Authorization: Bearer {token}
```

#### Dettaglio Task
```http
GET /api/tasks/{id}
Authorization: Bearer {token}
```

#### Crea Task
```http
POST /api/tasks
Authorization: Bearer {token}
Content-Type: application/json

{
    "titolo": "Nuovo task",
    "descrizione": "Descrizione dettagliata",
    "completato": false,
    "categoria": {
        "id": 1
    }
}
```

#### Aggiorna Task
```http
PUT /api/tasks/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
    "titolo": "Task aggiornato",
    "descrizione": "Nuova descrizione",
    "completato": true
}
```

#### Elimina Task
```http
DELETE /api/tasks/{id}
Authorization: Bearer {token}
```

## 💡 Esempi di Utilizzo

### Esempio completo con cURL
```bash
# 1. Registrazione
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"test123"}'

# 2. Login
TOKEN=$(curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"test123"}' \
  | jq -r '.token')

# 3. Lista task
curl http://localhost:8080/api/tasks \
  -H "Authorization: Bearer $TOKEN"

# 4. Crea task
curl -X POST http://localhost:8080/api/tasks \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"titolo":"Studiare Spring","descrizione":"JPA e Security","completato":false}'
```

### Postman Collection

Importa la collection da `postman/TaskAPI.postman_collection.json` per avere tutti gli endpoint preconfigurati.

## 📚 Obiettivi di Apprendimento

Questo progetto è stato sviluppato per consolidare le seguenti competenze:

### Backend Development
- ✅ Sviluppo REST API seguendo convenzioni RESTful
- ✅ Architettura a livelli e separazione responsabilità
- ✅ Dependency Injection e IoC
- ✅ Design patterns (Repository, Factory, Observer, Strategy)

### Spring Ecosystem
- ✅ Spring Boot: configurazione, auto-configuration, starters
- ✅ Spring Data JPA: repository, query derivate, JPQL
- ✅ Spring Security: filtri, authentication, authorization
- ✅ Spring MVC: controllers, request mapping, exception handling

### Database & ORM
- ✅ Modellazione database relazionale
- ✅ Mapping entità con JPA/Hibernate
- ✅ Relazioni (@OneToMany, @ManyToOne)
- ✅ Gestione transazioni

### Sicurezza
- ✅ Autenticazione stateless con JWT
- ✅ Password hashing con BCrypt
- ✅ Input validation

### Best Practices
- ✅ Validazione input (Bean Validation)
- ✅ Gestione errori centralizzata
- ✅ Logging appropriato
- ✅ Codice pulito e manutenibile
- ✅ Documentazione API

## 🚀 Possibili Sviluppi Futuri

### Funzionalità
- [ ] Sistema di ruoli e permessi (ADMIN, USER)
- [ ] Refresh token per sessioni lunghe
- [ ] Pagination e sorting avanzato
- [ ] Filtri multipli e ricerca full-text
- [ ] Condivisione task tra utenti
- [ ] Notifiche email per scadenze
- [ ] Upload allegati

### Tecnico
- [ ] Test unitari (JUnit, Mockito)
- [ ] Test di integrazione
- [ ] Containerizzazione (Docker)
- [ ] CI/CD pipeline
- [ ] API documentation (Swagger/OpenAPI)
- [ ] Caching con Redis
- [ ] Rate limiting
- [ ] Logging strutturato (ELK stack)

### Frontend
- [ ] Dashboard React/Angular
- [ ] Mobile app (React Native/Flutter)

## 📝 Licenza

Questo progetto è rilasciato sotto licenza MIT. Vedi il file [LICENSE](LICENSE) per dettagli.

## 👤 Contatti

**[Vincenzo Maria Siniscalco]**

- LinkedIn: [linkedin.com/in/vincenzo-siniscalco97](https://www.linkedin.com/in/vincenzo-siniscalco97/)
- GitHub: [github.com/VincenzoSiniscalco](https://github.com/VincenzoSiniscalco)
- Email: vincenzosiniscalco97@gmail.com


---
📖 Sviluppato come progetto di apprendimento - feedback e contributi sono benvenuti!
```
