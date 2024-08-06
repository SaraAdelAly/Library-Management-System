
# Library Management System

## Overview

The Library Management System is designed to streamline and automate the management of library operations, focusing on book borrowing and returning processes. The system provides a robust solution for managing books, patrons, and borrowing records, enabling efficient and accurate tracking of library activities.

### Key Features

### Book Management:

- Add New Books: Allows librarians to add new books to the library's collection, including details such as title, author, ISBN, publication year, and quantity.
- Update Book Information: Enables updates to existing book records to reflect changes in book details or quantities.
- Delete Books: Facilitates the removal of books from the system when they are no longer available or needed.
- Retrieve Book Details: Provides the ability to view details of individual books, including their availability status.

### Patron Management:

- Add New Patrons: Allows the addition of new patrons to the system, enabling them to borrow books.
- Update Patron Information: Supports updates to patron records to ensure accurate information is maintained.
- Delete Patrons: Facilitates the removal of patrons who are no longer active or associated with the library.

### Borrowing and Returning Books:

- Borrow Books: Allows patrons to borrow books, provided they do not have overdue books. The system checks the availability of the book and updates the borrowing record accordingly.
- Return Books: Enables patrons to return borrowed books. The system checks for overdue returns and updates the book quantity and borrowing record.

### Overdue Management:

- Overdue Book Check: Monitors borrowed books to identify overdue items. Patrons with overdue books are prevented from borrowing additional books until the overdue items are returned.

### Aspect-Oriented Programming:

- Book Quantity Check: Uses an aspect to ensure that books are available for borrowing and to update the book quantity after a successful borrowing operation. The aspect also enforces business rules related to book availability.

### Technology Stack
- Backend: Java Spring Boot framework, providing a robust and scalable backend for handling business logic and API endpoints.
- Database: Persistent storage for managing book, patron, and borrowing data.
- Testing Frameworks: JUnit and Mockito for unit testing and verifying the correctness of the service logic.
- Aspect-Oriented Programming: Used for implementing cross-cutting concerns such as book quantity checks and ensuring that business rules are enforced consistently.
- 
## Endpoints

# 1. Borrow a Book
## HTTP Method: POST

Path: /borrow/{bookId}/patron/{patronId}

Description: Allows a patron to borrow a book if the book is available and the patron does not have overdue books.

Path Parameters:
bookId (Integer): The ID of the book to borrow.

patronId (Integer): The ID of the patron borrowing the book.

Request Body: None

Responses:

- 201 Created: Book borrowed successfully.

- 404 Not Found: If the book or patron is not found.

- 403 Forbidden: If the patron has overdue books.

- 500 Internal Server Error: If an unexpected error occurs.



-----------------------------------------------------------------------------------------------------------------------------


# 2. Return a Book
   
## HTTP Method: POST

Path: /api/return/{bookId}/patron/{patronId}
Description: 
Allows a patron to return a borrowed book. Checks if the return date exceeds the allowed period.

Path Parameters:

bookId (Integer): The ID of the book being returned.

patronId (Integer): The ID of the patron returning the book.

Request Body: None

Responses:

- 200 OK: Book returned successfully.

- 404 Not Found: If the book or patron is not found, or if there is no active borrowing record.

- 400 Bad Request: If the return date exceeds the allowed period.

- 500 Internal Server Error: If an unexpected error occurs.

__________________________________________________________________________________________________________________________

# 3. Get All Books
## HTTP Method: GET

Path: /api/books

Description: 
Retrieves a list of all books in the system.

Request Parameters: None

Responses:

- 200 OK: Returns a list of all books.

- 500 Internal Server Error: If an unexpected error occurs.

__________________________________________________________________________________________________________________________

# 4. Get a Specific Book

## HTTP Method: GET

Path: /api/books/{id}

Description: Retrieves details of a specific book by ID.

Path Parameters:

- id (Integer): The ID of the book to retrieve.

Request Parameters: None

Responses:

- 200 OK: Returns the book details.
- 404 Not Found: If the book with the given ID is not found.
- 500 Internal Server Error: If an unexpected error occurs.

__________________________________________________________________________________________________________________________

# 5. Save a New Book

## HTTP Method: POST

Path: /api/books

Description: Adds a new book to the system.

Request Body:

{

  "title": "The Great Gatsby",
  
  "author": "F. Scott Fitzgerald",
  
  "isbn": "9780743273565",
  
  "publicationYear": "1925-04-10",
  
  "quantity": "9"

}

Responses:

- 201 Created: Book successfully added.
- 400 Bad Request: If the request body is invalid or required fields are missing.
- 500 Internal Server Error: If an unexpected error occurs.

-------------------------------------------------------------------------------------------------------------------------------------------------------

# 6. Update a Book
## HTTP Method: PUT

Path: /api/books/{id}

Description: Updates the details of an existing book by ID.

Path Parameters:
- id (Integer): The ID of the book to update.

Request Body:

{

  "title": "The Great Gatsby",
  
  "author": "F. Scott Fitzgerald",
  
  "isbn": "9780743273565",
  
  "publicationYear": "1925-04-10",
  
  "quantity": "9"

}

Responses:

- 200 OK: Book successfully updated.
- 404 Not Found: If the book with the given ID is not found.
- 500 Internal Server Error: If an unexpected error occurs.
----------------------------------------------------------------------------------------------------------------------------------------------------

# 7. Delete a Book
## HTTP Method: DELETE

Path: /api/books/{id}

Description: Deletes a book by ID from the system.

Path Parameters:
- id (Integer): The ID of the book to delete.

Request Parameters: None

Responses:

- 204 No Content: Book successfully deleted.
- 404 Not Found: If the book with the given ID is not found.
- 500 Internal Server Error: If an unexpected error occurs.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Use Cases
- Librarians can manage books and patrons, track borrowings and returns, and ensure compliance with library policies.
- Patrons can borrow and return books, view book details, and manage their borrowing records.

