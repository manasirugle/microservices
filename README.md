# Insurance Microservices Project

This project simulates a basic Insurance backend using microservices architecture.


This project includes 4 Spring Boot microservices:

### 1. User Service
- APIs: createUser, getUser, updateUser, removeUser, login, logout, isUserExists
- Manages: Users (agents), authentication, and permissions

### 2. Lead Service
- APIs: createLead, getLeadDetails, getLeads, updateLead, deleteLead
- Manages: Sales leads and their assignment to users

### 3. Customer Service
- APIs: createCustomer, getCustomerDetails, getCustomers, updateCustomer, isCustomerExists
- Manages: Customers and their details

### 4. Policy Service
- APIs: createPolicy, getPolicyDetails, getPolicies, updatePolicy, isPolicyExists
- Manages: Policies tied to customers

Each service runs independently with its own database.

---

### Technologies Used:
- Spring Boot
- Maven
- PostgreSQL
- Docker (optional)
- Postman (for testing)

---
