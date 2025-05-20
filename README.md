
# 🛒 Product Catalog Application

#### by Kadyrbekova Nazimay

### Presentation:[[ https://www.canva.com/design/DAGnUgxzeEk/GM-3aurcF2b7kr7qTvnTwA/edit](https://www.canva.com/design/DAGnUgxzeEk/GM-3aurcF2b7kr7qTvnTwA/edit?utm_content=DAGnUgxzeEk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)](https://www.canva.com/design/DAGnUgxzeEk/GM-3aurcF2b7kr7qTvnTwA/edit?utm_content=DAGnUgxzeEk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
### Project Structure:[ https://www.canva.com/design/DAGnaZiMPfg/8ulMr-xLV1KkuvDCfpIhAw/edit](https://www.canva.com/design/DAGnaZiMPfg/8ulMr-xLV1KkuvDCfpIhAw/edit?utm_content=DAGnaZiMPfg&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
### Documentation: https://onedrive.live.com/personal/e3031c5e31a30688/_layouts/15/doc2.aspx?resid=d1df176a-000b-469f-9f15-e43a1515c263&cid=e3031c5e31a30688&action=editnew&wdNewAndOpenCt=1747377170640&ct=1747377172929&wdOrigin=OFFICECOM-WEB.START.NEW&wdPreviousSessionSrc=HarmonyWeb&wdPreviousSession=48db4a8e-e835-4f8b-84d3-3ef886b1304a

## Overview

This is a Java-based desktop application designed to manage a product catalog with user authentication and role-based access control. It provides a clear separation between data storage logic, business logic, and the user interface. Users can view and manage products depending on their roles, and data can be stored in either a PostgreSQL database or local files (CSV/JSON).

## Key Features

* 🔐 **Authentication & Roles**: Secure login system with `admin` and `user` roles
* 📦 **Product Management**: Full CRUD operations for admins
* 🛍️ **Shopping Cart**: Users can add/remove products to/from their cart
* 💾 **Dual Storage**: Supports PostgreSQL and file-based (CSV/JSON) storage
* 🖥️ **GUI**: Swing-based interface for product viewing

## Project Structure

* `model/` – Data models (e.g. `Product`, `User`)
* `dao/` – Database access logic (e.g. `UserDAO`, `ProductDAO`, `CartDAO`)
* `service/` – Business logic (e.g. authentication and role handling)
* `util/` – Utility classes for file I/O and database connection
* `ui/` – Swing-based UI (`ProductListFrame`) for viewing products

## Prerequisites

* Java 11 or higher
* PostgreSQL database with tables: `users`, `products`, `cart_items`
* Jackson library for JSON serialization

## Getting Started

1. Configure your PostgreSQL credentials in `Database.java`
2. Compile and run the application
3. Register as a user or login as an admin
4. Use the console or UI according to your access rights

## Roles

* **Admin**: Full access to product CRUD and storage mode switching
* **User**: Can view product catalog and manage their cart

## OOP Principles

The application architecture follows clean OOP design:

* Encapsulation of data in model classes
* Separation of concerns between data access, logic, and UI
* Modular and extensible design for future enhancements

## Code Review
#### Product List Frame
  ![image](https://github.com/user-attachments/assets/69504ac4-45b2-4288-98e5-1d94e1c0d22c)

#### User Role 
##### Adding to the cart, removing from the cart, view cart
![image](https://github.com/user-attachments/assets/42690027-c831-4c4d-8cb7-b046c31d6056)
![image](https://github.com/user-attachments/assets/e98744fc-a687-4a6a-a05b-ac85ccd5251b)
![image](https://github.com/user-attachments/assets/e96d5222-1530-4433-b230-45a46b7e9aed)
![image](https://github.com/user-attachments/assets/9b1b5951-598c-4769-bd44-9e66e541f228)


#### Admin Role
##### CRUD opeations with CSV, JSON files, PostgreSQL, review Report, list of users 
![image](https://github.com/user-attachments/assets/a213462e-3e29-4bda-9b06-9c7c8367079c)
![image](https://github.com/user-attachments/assets/411bfed7-3a1b-4dff-ad37-eaa0388072f3)
![image](https://github.com/user-attachments/assets/5b888389-c7d4-429c-aa76-9dbdb141fedd)
![image](https://github.com/user-attachments/assets/7078c8b6-64ed-4b1d-baee-0b6654dd89b9)
![image](https://github.com/user-attachments/assets/154681e1-f513-42f4-921e-eb363655490d)
![image](https://github.com/user-attachments/assets/ba5f86e9-5c50-4aa3-b1f4-bcf61c1328f3)
![image](https://github.com/user-attachments/assets/4a093dcc-0c93-4b41-90ed-e7d0f660563e)








