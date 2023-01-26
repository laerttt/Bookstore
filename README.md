# Bookstore
Bookstore application for Java Project

The focus of this software will be to manage all important steps 
of a Library. The software should keep data about all  the books 
in the library, such us ISBN of the book, title, category of the 
book, supplier, purchased date, purchased price, original price, 
selling price, author, stock. At the same time, we should keep 
track of the sold products by bill number (or Order ID), their 
sold quantities, prices, and date of transaction.
The application will have a three-level user system: Librarian, 
Manager and Administrator. Each will have different  views and 
usage of the software. Note that each of the users has a username 
and a password to enter the software  (obviously a role as well).

Librarian -> Has the right to check out books that a customer may 
need from the bookstore. This means that the  librarian should 
create a bill and enter the data of the bought books, such as 
ISBN of the book and its quantity. If the  book is out of stock 
or does not exist, give an alert to the librarian. The software 
should provide him the total amount  of the bill, and it should be 
in printable format (BillNo.txt). The updates in the software 
file should be done  automatically by adding the data into the 
respective files.

Manager -> The manager has the right to supply the bookstore 
with the needed books. So, he/she may enter the stock the 
new book category, and/or add books of the same category to the 
stock of the bookstore. The manager should  be informed when 
entered in the system if there are few (usually less than 5) 
items of a book in the bookstore stock.  The same user may also
check the performance of the librarians by checking their total 
number of bills, books sold, and  the total amount of money made 
for a certain date or between a certain period of time. Also, the 
statistics about the  books sold and bought should be provided to 
them whenever requested from them daily, monthly and/or total.

Administrator -> The administrator has the right to manage almost 
everything that Librarian and Manager does. Beside  them, he has 
the right to manage the employees (Librarian and Manager), by 
registering, modifying, and deleting them. He may keep data about
them such as: name, birthday, phone, email, salary,  access level, 
and other information about them. The software should provide to 
him also data about total incomes (total  of books sold) and total
cost (total of items bought and staff salaries) during a period.
Another important role of the administrator can be to revoke 
permissions from librarians or managers. This means that the 
permissions should be role-based. This is not mandatory, but 
you get extra points if you can manage to implement it, otherwise
keep the permissions static.

**The data of the bookstore should be stored in binary and text 
files and managed appropriately by using the proper  Objects.** 

The students should organize the classes in packages and make 
use of proper data encapsulation. 

# Requirements

The requirements include:
1. Encapsulation, inheritance, polymorphism, abstract classes and interfaces
2. File Handling - Text and Binary IO (both of them)
3. Validation of Input (Very Important)  
   Usage of String Functions and/or Regular Expressions for validating the input 4. Exception Handling (try{}catch(){}finally{}, custom Exception Classes
4. JavaFX UI and Graphics (everything we have covered)
5. JavaFX Events (Event handling, Inner Classes and other things related to the lesson)
6. The project should be organised in Model-View-Controller, where the model  should fulfill all requirements from 1 to 4 and View-Controller requirements 5 and 6.
   
Note: Menus are a must for all projects, the application should be fully interactive, and it should have  a login system with different levels. More may be discussed during lessons.
Be aware: The Final submission of the project should include all source files (as a project in a .rar or  .zip format). In case you do not present your project, you get 0 points.
The name of the project should be the same as your username and zipped in a file like  nsurname22.zip. The project should also have a note.txt which has a list of username and password  to access your application.

WARNING: FXML (or Scene Builder) is not permitted, and will not be evaluated in the project.
