# Tasks
## Classes
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
- [ ] Bill number (or Order Id) 
- [ ] Quantities
- [ ] Prices
- [ ] Date of transaction. 
### Manager
- [ ] Name
- [ ] Surname
- [ ] Birthday
- [ ] PhoneNumber
- [ ] Email
- [ ] Salary
- [ ] Access Level


## User Levels

- [ ] Librarian
    - Checkout Books
    - Create Bills
    - Enter data of bought books
      - Quantity
      - ISBN

**_If the book does not exist in stock there should be a warning !!_**
<br /> **_The software should provide him the total amountof the bill and it should be in printable format ([BillNo].txt) !!_**
<br /> The updates in the software file should be done  automatically by adding the data into the respective files.


- [ ] Manager
  - Modify the stock
    - Add/Remove
  - Add categories
  - Check performance of the librarians
    - Books sold
    - Bills
    - Money made
  - Statistics about books
    - Sold/Bought
      - Daily/Monthly/Yearly

**_The manager should  be informed when entered in the system if there are few (usually less than 5) items of a book in the bookstore stock._**

- [ ] Administrator
  - Everything from Librarian and Manager
  - Manage employees
    - Registering
    - Modifying
    - Deleting
    - Access information
  - Total income/cost during a period of time
  - Modify permissions for Managers/Librarians
