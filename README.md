
# 🛒 Product Catalog Application

####by Kadyrbekova Nazimay

### Presentation: https://www.canva.com/design/DAGnUgxzeEk/GM-3aurcF2b7kr7qTvnTwA/edit
### Project Structure: https://www.canva.com/design/DAGnaZiMPfg/8ulMr-xLV1KkuvDCfpIhAw/edit
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
