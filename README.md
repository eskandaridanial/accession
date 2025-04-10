Accession is a modular, open-source access & identity management platform designed for secure, scalable, and flexible user access control. Inspired by modern GitOps tools, it decouples identity logic from application logic while enabling declarative and dynamic permission models.


## Architecture Overview

Accession consists of two main services:

    User Management Service

    Access Control Service

These services can be deployed independently or together depending on your system design.

                        +--------------------------+
                        |   Client Applications    |
                        +-----------+--------------+
                                    |
                        +-----------v--------------+
                        |  Access Control Service  |
                        +-----------+--------------+
                                    |
                        +-----------v--------------+
                        |  User Management Service |
                        +--------------------------+
                                    |
                        +-----------v--------------+
                        |       PostgreSQL         |
                        +--------------------------+

## Services

1. user-management-service

Handles all identity-related operations such as:

  User registration, login, logout
  
  Realms, roles, and permission management

  JWT token generation for authenticated sessions

2. access-control-service

Acts as a gateway to validate tokens and enforce access policies based on:

  Role-based access control (RBAC)

  Fine-grained permissions

  Real-time token introspection

## Local Development (Docker Compose)

Here's a sample setup using Docker Compose:

```shell
git clone https://github.com/your-org/accession.git
cd accession
docker-compose up --build
```
