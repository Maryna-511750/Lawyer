# Lawyer Client Website

A professional business card website for a lawyer, with both frontend and backend implementations.

## Branches

- **main**  
  Frontend template: HTML, CSS, and JS for the website interface.

- **lawyer-client-spring**  
  Backend service (Spring Boot) handling part of the site's functionality.  
  Features:
  - Java 21
  - Spring Boot & REST API
  - Spring Security with JWT authentication
  - JPA / Hibernate for database access
  - Maven for dependency management
  - Controllers, Services, Repositories, DTOs, and Exception handling

- **lawyer-mvp-spring**  
  Another backend service (Spring Boot) for a different part of the same website.  
  Features are similar to `lawyer-client-spring`, serving separate functional modules.

## Architecture

The project uses a microservices approach: the website's backend is split into two services (`lawyer-client-spring` and `lawyer-mvp-spring`) to separate concerns and improve modularity.

## Technologies

- Java 21  
- Spring Boot  
- Spring Security + JWT  
- Maven  
- Hibernate / JPA  
- HTML, CSS, JavaScript (frontend in `main` branch)

