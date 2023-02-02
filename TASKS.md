# Tasks
## Classes
### People
Abstract Superclass of Librarian Manager Administrator Author
### Book
- [ ] ISBN of the book
- [ ] Title 
- [ ] Category of the book 
- [ ] Supplier
- [ ] Purchased date
- [ ] Purchased price 
- [ ] Original price
- [ ] Selling price
- [ ] Author
- [ ] Stock
### Bills
- [x] Bill number (or Order ID) 
- [x] Quantities
- [x] Prices
- [x] Date of transaction. 
### Manager/Librarian
- [x] Name
- [x] Surname
- [x] Birthday
- [x] PhoneNumber
- [x] Email
- [x] Salary
- [x] Access Level


## User Levels

- [x] **Librarian**
    - Checkout Books
    - Create Bills
    - Enter data of bought books
      - Quantity
      - ISBN

**_If the book does not exist in stock there should be a warning !!_**
<br /> **_The software should provide him the total amountof the bill and it should be in printable format ([BillNo].txt) !!_**
<br /> The updates in the software file should be done  automatically by adding the data into the respective files.


- [x] **Manager**
  - Modify the stock
    - Add/Remove
  - Check performance of the librarians
    - Books sold
    - Bills
    - Money made
  - Statistics about books
    - Sold/Bought
      - Daily/Monthly/Yearly

**_The manager should  be informed when entered in the system if there are few (usually less than 5) items of a book in the bookstore stock._**

- [x] **Administrator**
  - Everything from Librarian and Manager
  - Manage employees
    - Registering
    - Modifying
    - Deleting
    - Access information
  - Total income/cost during a period of time
  - Modify permissions for Managers/Librarians
## Controls
- [x] LogIn
- [ ] Librarian
- [ ] Manager
  - Lib
  - Stat
- [ ] Admin