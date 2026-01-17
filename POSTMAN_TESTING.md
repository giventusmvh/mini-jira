# Postman API Testing Documentation

This document provides a complete guide for testing the Mini Jira API using Postman.

**Base URL**: `http://localhost:8080`

---

## 🔐 1. Authentication (Login)

You must login first to get the **JWT Token**. This token is required for all other requests.

### **Login**

- **Method**: `POST`
- **URL**: `/auth/login`
- **Body** (JSON):
  ```json
  {
    "email": "wei.sheng@tutorle.com",
    "password": "password"
  }
  ```
  _(Note: Use the email/password of the dummy user or created user)_

### **Response**

Copy the `token` from the response. User data is also included for frontend display:

```json
{
  "success": true,
  "message": "Login successfully",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWI...",
    "user": {
      "id": 1,
      "name": "Given User",
      "email": "given@example.com",
      "active": true,
      "createdAt": "2026-01-17T18:00:00"
    }
  }
}
```

---

## 🔑 How to Use the Token

For all requests below, you must add the token to the **Headers**:

| Key             | Value                            |
| --------------- | -------------------------------- |
| `Authorization` | `Bearer <PASTE_YOUR_TOKEN_HERE>` |

---

## 📁 2. Categories API

### **Create Category**

- **Method**: `POST`
- **URL**: `/api/categories`
- **Body** (JSON):
  ```json
  {
    "name": "Work",
    "description": "Office related tasks",
    "color": "#FF5733"
  }
  ```

### **Get All Categories**

- **Method**: `GET`
- **URL**: `/api/categories`

### **Get Category By ID**

- **Method**: `GET`
- **URL**: `/api/categories/{id}`
- **Example**: `/api/categories/1`

### **Update Category**

- **Method**: `PUT`
- **URL**: `/api/categories/{id}`
- **Body** (JSON):
  ```json
  {
    "name": "Work Updated",
    "description": "Updated description",
    "color": "#0000FF"
  }
  ```

### **Delete Category**

- **Method**: `DELETE`
- **URL**: `/api/categories/{id}`

---

## ✅ 3. Tasks API

### **Create Task**

- **Method**: `POST`
- **URL**: `/api/tasks`
- **Body** (JSON):
  ```json
  {
    "title": "Finish Backend",
    "description": "Implement all service layers",
    "priority": "HIGH",
    "status": "TODO",
    "categoryId": 1
  }
  ```
  _(Note: `categoryId` is optional. `priority`: LOW, MEDIUM, HIGH, URGENT. `status`: TODO, IN_PROGRESS, DONE)_

### **Get All Tasks**

- **Method**: `GET`
- **URL**: `/api/tasks`

### **Get Task By ID**

- **Method**: `GET`
- **URL**: `/api/tasks/{id}`
- **Example**: `/api/tasks/1`

### **Update Task**

- **Method**: `PUT`
- **URL**: `/api/tasks/{id}`
- **Body** (JSON):
  ```json
  {
    "title": "Finish Backend - Refactored",
    "description": "Refactoring code",
    "priority": "URGENT",
    "status": "IN_PROGRESS",
    "categoryId": 1
  }
  ```

### **Update Task Status (Quick)**

- **Method**: `PATCH`
- **URL**: `/api/tasks/{id}/status?status=DONE`
- **Query Params**:
  - `status`: `TODO`, `IN_PROGRESS`, or `DONE`

### **Delete Task**

- **Method**: `DELETE`
- **URL**: `/api/tasks/{id}`

---

## ⚙️ 4. User API (Auth)

### **Register New User**

- **Method**: `POST`
- **URL**: `/auth/register`
- **Body** (JSON):
  ```json
  {
    "name": "Given User",
    "email": "given@example.com",
    "password": "password123"
  }
  ```
- **Auth**: Not required (Public endpoint)

### **Create Dummy User** (Alternative)

- **Method**: `POST`
- **URL**: `/api/users/dummy`
- **Body**: None
