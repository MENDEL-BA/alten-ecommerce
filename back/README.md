Certainly! Below is a sample `README.md` documentation for the API endpoints you've provided. This documentation includes descriptions of each endpoint, the request format, and the expected responses.

```markdown
# API Documentation

The http request is in file back/generated-request.http
This document provides an overview of the available API endpoints for user authentication, product management, cart management, and wishlist management.

## Base URL

The base URL for all API endpoints is `http://localhost:8080/api`.

## Authentication

### Sign Up

**Endpoint:** `POST /auth/signup`

**Description:** Register a new user.

**Request Body:**
```json
{
  "username": "testuser",
  "firstname": "Test",
  "email": "testuser@example.com",
  "password": "password123"
}
```

**Response:**
- 201 Created: User successfully registered.
- 400 Bad Request: Invalid input data.

### Login

**Endpoint:** `POST /auth/login`

**Description:** Authenticate a user and retrieve an access token.

**Request Body:**
```json
{
  "email": "admin@admin.com",
  "password": "admin123"
}
```

**Response:**
- 200 OK: Authentication successful. Returns an access token.
- 401 Unauthorized: Invalid credentials.

## Products

### Get All Products

**Endpoint:** `GET /products`

**Description:** Retrieve a list of all products.

**Headers:**
- `Authorization: Bearer {{token}}`

**Response:**
- 200 OK: Returns a list of products.
- 401 Unauthorized: Invalid or missing token.

### Create a Product

**Endpoint:** `POST /products`

**Description:** Create a new product.

**Headers:**
- `Authorization: Bearer {{token}}`

**Request Body:**
```json
{
  "name": "Test Product",
  "description": "This is a test product.",
  "price": 19.99,
  "category": "Test Category"
}
```

**Response:**
- 201 Created: Product successfully created.
- 400 Bad Request: Invalid input data.
- 401 Unauthorized: Invalid or missing token.

### Get a Product

**Endpoint:** `GET /products/{id}`

**Description:** Retrieve details of a specific product.

**Headers:**
- `Authorization: Bearer {{token}}`

**Response:**
- 200 OK: Returns the product details.
- 404 Not Found: Product not found.
- 401 Unauthorized: Invalid or missing token.

### Update a Product

**Endpoint:** `PATCH /products/{id}`

**Description:** Update details of a specific product.

**Headers:**
- `Authorization: Bearer {{token}}`

**Request Body:**
```json
{
  "name": "Updated Product",
  "description": "Updated description.",
  "price": 29.99
}
```

**Response:**
- 200 OK: Product successfully updated.
- 400 Bad Request: Invalid input data.
- 404 Not Found: Product not found.
- 401 Unauthorized: Invalid or missing token.

### Delete a Product

**Endpoint:** `DELETE /products/{id}`

**Description:** Delete a specific product.

**Headers:**
- `Authorization: Bearer {{token}}`

**Response:**
- 204 No Content: Product successfully deleted.
- 404 Not Found: Product not found.
- 401 Unauthorized: Invalid or missing token.

## Cart

### Add to Cart

**Endpoint:** `POST /cart/add`

**Description:** Add a product to the cart.

**Headers:**
- `Authorization: Bearer {{token}}`

**Request Body:**
```json
{
  "productId": 3,
  "quantity": 1
}
```

**Response:**
- 200 OK: Product successfully added to the cart.
- 400 Bad Request: Invalid input data.
- 401 Unauthorized: Invalid or missing token.

### Get Cart

**Endpoint:** `GET /cart`

**Description:** Retrieve the current user's cart.

**Headers:**
- `Authorization: Bearer {{token}}`

**Response:**
- 200 OK: Returns the cart details.
- 401 Unauthorized: Invalid or missing token.

## Wishlist

### Add to Wishlist

**Endpoint:** `POST /wishlist/add`

**Description:** Add a product to the wishlist.

**Headers:**
- `Authorization: Bearer {{token}}`

**Request Body:**
```json
{
  "productId": 3
}
```

**Response:**
- 200 OK: Product successfully added to the wishlist.
- 400 Bad Request: Invalid input data.
- 401 Unauthorized: Invalid or missing token.

### Get Wishlist

**Endpoint:** `GET /wishlist`

**Description:** Retrieve the current user's wishlist.

**Headers:**
- `Authorization: Bearer {{token}}`

**Response:**
- 200 OK: Returns the wishlist details.
- 401 Unauthorized: Invalid or missing token.
```