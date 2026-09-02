# 🛍️ ShopAI – Hyper-Personalized Shopping Assistant

**ShopAI** is a Generative AI-powered shopping assistant designed to provide personalized product recommendations based on a user's preferences, shopping history, and interactions.

The system combines a traditional e-commerce platform with **Generative AI** to create a smarter, more personalized, and conversational shopping experience.

---

## 🚀 Features

### 🤖 AI Shopping Assistant

* Conversational AI-powered shopping assistant
* Understands natural-language user queries
* Provides personalized product recommendations
* Uses user preferences and previous purchases
* Recommends only products available in the application's database

### 🛒 E-Commerce Features

* Browse products
* Search products
* View product details
* User registration and login
* Purchase history
* Personalized recommendations

### 🧠 Personalization

ShopAI considers multiple sources of user information, including:

* User preferences
* Previous purchases
* Product categories
* User interactions

This allows the assistant to provide recommendations that are more relevant to each individual user instead of showing the same products to everyone.

### 💬 Conversational Shopping

Users can interact with the shopping assistant using natural language instead of manually searching through the entire product catalog.

**Example:**

> **User:**
> I need comfortable running shoes under ₹3000.

> **ShopAI:**
> Based on your preferences and the products currently available, here are some suitable running shoes from our catalog.

---

## 🏗️ System Architecture

```text
                    ┌──────────────────────┐
                    │        User          │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │    Thymeleaf UI      │
                    │   Web Application    │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │     Spring Boot      │
                    │       Backend        │
                    └──────────┬───────────┘
                               │
              ┌────────────────┼────────────────┐
              │                │                │
              ▼                ▼                ▼
       ┌────────────┐   ┌──────────────┐  ┌──────────────┐
       │   MySQL    │   │  Gemini API  │  │  User Data   │
       │  Database  │   │  Generative  │  │ Preferences  │
       │            │   │      AI      │  │ & Purchases  │
       └────────────┘   └──────────────┘  └──────────────┘
              │                │                │
              └────────────────┼────────────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │ Personalized Product │
                    │   Recommendations    │
                    └──────────────────────┘
```

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven

### Frontend

* Thymeleaf
* HTML
* CSS
* Bootstrap

### Database

* MySQL

### Generative AI

* Google Gemini API

### Deployment & Tools

* Railway
* Git
* GitHub

---

## 📂 Project Structure

```text
ShoppingAssistant/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── entity/
│   │   │       └── config/
│   │   │
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── home.html
│   │       │   ├── assistant.html
│   │       │   ├── login.html
│   │       │   └── ...
│   │       │
│   │       ├── static/
│   │       │   ├── css/
│   │       │   └── images/
│   │       │
│   │       └── application.properties
│   │
├── pom.xml
└── README.md
```

---

## ⚙️ How It Works

1. The user logs into the application.
2. The system retrieves the user's preferences and purchase history.
3. The user interacts with the AI shopping assistant using natural language.
4. The user's query is processed by the application.
5. Relevant user information and product data are provided to the Generative AI model.
6. Gemini analyzes the user's request along with the supplied context.
7. ShopAI generates personalized product recommendations.
8. Recommendations are restricted to products available in the application's database.
9. The user can view and explore the recommended products.

---

## 🧠 Example Use Case

Consider a user who frequently purchases sportswear.

The user asks:

> **"I need something for running."**

Instead of providing generic suggestions, ShopAI can use information such as:

* Previous purchases
* User preferences
* Relevant product categories
* Available products

The assistant can then recommend suitable running-related products from the application's catalog.

This creates a more personalized shopping experience compared with traditional keyword-based product search.

---

## 🔐 Environment Variables

Sensitive information such as API keys and database credentials should **never be committed to GitHub**.

Configure sensitive values using environment variables.

Example:

```properties
GEMINI_API_KEY=${GEMINI_API_KEY}

SPRING_DATASOURCE_URL=${MYSQL_URL}
SPRING_DATASOURCE_USERNAME=${MYSQLUSER}
SPRING_DATASOURCE_PASSWORD=${MYSQLPASSWORD}
```

> ⚠️ **Important:** Never upload actual API keys, database passwords, tokens, or other secrets to GitHub.

---

## ☁️ Deployment

ShopAI is designed to be deployed using **Railway**.

```text
                         GitHub
                           │
                           ▼
                        Railway
                           │
             ┌─────────────┴─────────────┐
             │                           │
             ▼                           ▼
      Spring Boot App              MySQL Database
             │                           │
             └─────────────┬─────────────┘
                           │
                           ▼
                    Public Web Application
```

Railway hosts the Spring Boot application and MySQL database, allowing ShopAI to be accessed through a public web URL.

---

## 🌟 Key Highlights

* 🤖 Generative AI integration
* 🛍️ Hyper-personalized shopping experience
* 💬 Conversational shopping assistant
* 🧠 User preference-based recommendations
* 🛒 Purchase history-based personalization
* 🎯 Database-restricted product recommendations
* 🗄️ MySQL database integration
* ☁️ Cloud-hosted database
* 🚀 Railway cloud deployment
* 🔗 GitHub-based deployment workflow
* 📱 Web-accessible application

---

## 🎯 Project Objective

The main objective of **ShopAI** is to demonstrate how Generative AI can be integrated into a real-world e-commerce application to create a more intelligent and personalized shopping experience.

Traditional e-commerce systems often rely heavily on manual search, filtering, and generic product recommendations.

ShopAI aims to improve this experience by allowing users to communicate naturally with an AI assistant.

Instead of presenting the same products to every customer, the system uses available information about each user's preferences and shopping behavior to provide more relevant recommendations.

At the same time, the AI is grounded using the application's own product catalog so that recommendations remain connected to products actually available within the system.

---

## 🔮 Future Enhancements

* 🔐 Advanced authentication using Spring Security
* 🧠 Improved recommendation algorithms
* 🛒 AI-powered cart assistance
* 📊 User behavior analytics
* 🔎 Semantic product search
* 🧬 Vector database integration
* 🖼️ AI-powered product image understanding
* 🎯 Advanced personalization
* 📱 Improved mobile responsiveness
* 💳 Payment gateway integration

---

## 🌐 Live Demo
shopping-assistant-01-production.up.railway.app

## 👨‍💻 Author

**Muhammed Navas S**

---

⭐ If you find this project interesting, consider giving the repository a star!
