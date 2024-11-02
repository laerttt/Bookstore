# Bookstore Management System

A Java-based Bookstore Management application designed to manage key aspects of a library/bookstore. This software stores and processes data on all available and sold books, supports multi-level user access, and enables efficient handling of stock, sales, and employee management.

## Project Overview

The application manages data for all books in the library, including:
- ISBN
- Title
- Category
- Supplier
- Purchase Date
- Purchase Price
- Original Price
- Selling Price
- Author
- Stock Quantity

Additionally, the system tracks sold items with:
- Order ID (Bill Number)
- Sold Quantity
- Price
- Transaction Date

## User Roles and Permissions

The application includes three user roles, each with unique permissions and views:

### 1. Librarian
- **Primary Tasks**: 
  - Checks out books requested by customers.
  - Creates bills, entering details like ISBN and quantity.
  - Generates a printable bill (saved as `BillNo.txt`).
- **Special Features**:
  - Alerts when a book is out of stock or unavailable.
  - Automatically updates inventory records after each sale.

### 2. Manager
- **Primary Tasks**:
  - Supplies and updates stock in the bookstore.
  - Adds new book categories or updates existing stock.
- **Special Features**:
  - Alerts for low-stock items (below 5).
  - Tracks librarian performance (total bills, books sold, revenue).
  - Accesses daily, monthly, and total statistics for sold and bought books.

### 3. Administrator
- **Primary Tasks**:
  - Manages all functionalities of Librarian and Manager.
  - Oversees employee management (registers, modifies, deletes librarians and managers).
- **Special Features**:
  - Maintains records of employees (name, birthdate, contact, salary, access level).
  - Tracks bookstore finances (total revenue and costs, including salaries).
  - Optionally, revokes permissions for specific librarians or managers for enhanced security.

**Note**: Implementing role-based permissions (instead of static permissions) is optional and earns additional points.

## Technical Requirements

This project includes the following key components:

1. **Object-Oriented Programming Concepts**
   - Encapsulation, inheritance, polymorphism, abstract classes, and interfaces.
   
2. **File Handling**
   - Stores data in both text and binary files.

3. **Input Validation**
   - Validates user input using string functions and/or regular expressions.

4. **Exception Handling**
   - Uses `try-catch-finally` and custom exception classes.

5. **JavaFX UI and Events**
   - Incorporates JavaFX for interactive GUI (without FXML or Scene Builder).
   - Event handling through inner classes and other methods.

6. **Model-View-Controller (MVC) Architecture**
   - The **Model** should implement all requirements from points 1 to 4.
   - The **View-Controller** should handle GUI and user interactions as outlined in points 5 and 6.

## Additional Notes

- **Menu Navigation**: Menus are required for all functions.
- **Login System**: Includes a secure login system with role-based access for Librarian, Manager, and Administrator.
  
## Submission Guidelines

- **Project Format**: Submit the project as a `.zip` file containing all source files.
- **Naming Convention**: Name the zip file as `yourusername.zip` (e.g., `jsmith22.zip`).
- **Credentials**: Include a `note.txt` file with a list of usernames and passwords for accessing the application.
  
**Warning**: Use of FXML or Scene Builder is prohibited and will not be evaluated.

## Author
Developed as a Java Project for academic purposes.
