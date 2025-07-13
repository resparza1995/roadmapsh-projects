## Scalable ecommerce platform
In progress...

### Microservices
- [x] usersms
- [x] payments
- [ ] products
- [ ] notifications
- [x] shopping cart
- [x] API Gateway
- [x] Set up Docker and Docker Compose 
Run each microservice on a different port (e.g., 8081, 8082, 8083).
Add Docker support to each microservice (Dockerfile + Docker Compose).
Expose all services internally in the same network.
Point the API Gateway to Docker service names instead of localhost.

- [ ] Integrate Services
- [ ] Implement Service Discovery
- [ ] Set up Monitoring and Logging
- [ ] Deploy the Platform
- [ ] CI/CD Integration

No auth/security implemented for simplicity.

## Run
`docker-compose build`: Build mvn projects and build containers from each Dockerfile.
`docker-compose up`

**Url example:** `http://localhost:8080/payments`  