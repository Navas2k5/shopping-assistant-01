# 🛍️ ShopAI – Hyper-Personalized Shopping Assistant

ShopAI is a Generative AI-powered shopping assistant designed to provide personalized product recommendations based on a user's preferences, shopping history, and interactions.

The system combines a traditional e-commerce platform with Generative AI to create a smarter and more personalized shopping experience.

---

## 🚀 Features

### 🤖 AI Shopping Assistant
- Conversational AI-powered shopping assistant
- Understands natural-language user queries
- Provides personalized product recommendations
- Uses user preferences and previous purchases
- Recommends products available in the application's database

### 🛒 E-Commerce Features
- Browse products
- Search products
- View product details
- User registration and login
- Purchase history
- Personalized recommendations

### 🧠 Personalization
ShopAI considers:
- User preferences
- Previous purchases
- Product categories
- User interactions

This allows the assistant to provide recommendations that are more relevant to each individual user.

### 💬 Conversational Shopping

Users can interact with the assistant using natural language instead of manually searching through products.

Example:

**User:**
> I need comfortable running shoes under ₹3000.

**ShopAI:**
> Based on your preferences and available products, here are some suitable running shoes from our catalog.

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
       │    MySQL   │   │  Gemini API  │  │ User Data    │
       │  Database  │   │  Generative  │  │ Preferences  │
       │            │   │      AI      │  │ & Purchases  │
       └────────────┘   └──────────────┘  └──────────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │ Personalized Product │
                    │   Recommendations    │
                    └──────────────────────┘
🛠️ Tech Stack
Backend
Java
Spring Boot
Spring Data JPA
Hibernate
Maven
Frontend
Thymeleaf
HTML
CSS
Bootstrap
Database
MySQL
Generative AI
Google Gemini API
Deployment
Railway
GitHub
📂 Project Structure
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
⚙️ How It Works
The user logs into the application.
The system retrieves the user's preferences and purchase history.
The user interacts with the AI shopping assistant.
The user's natural-language query is processed using Generative AI.
Relevant user and product information is provided to the AI.
The AI generates personalized recommendations.
Recommendations are restricted to products available in the application's database.
The user can view and explore the recommended products.
🧠 Example Use Case

A user who frequently purchases sportswear asks:

"I need something for running."

ShopAI can use the user's previous purchases and preferences to understand their interests and recommend suitable running products from the available product catalog.

🔐 Environment Variables

API keys and database credentials should never be committed to GitHub.

Configure sensitive values using environment variables.

Example:

GEMINI_API_KEY=${GEMINI_API_KEY}

SPRING_DATASOURCE_URL=${MYSQL_URL}
SPRING_DATASOURCE_USERNAME=${MYSQLUSER}
SPRING_DATASOURCE_PASSWORD=${MYSQLPASSWORD}

⚠️ Never upload your actual API keys, database passwords, or other secrets to GitHub.

☁️ Deployment

The application is deployed using Railway.

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

Railway hosts both the Spring Boot application and the MySQL database, allowing the application to be accessed through a public URL.

🌟 Key Highlights
🤖 Generative AI integration
🛍️ Hyper-personalized shopping experience
💬 Conversational AI assistant
🧠 User preference-based recommendations
🛒 Purchase history-based personalization
🗄️ MySQL database integration
☁️ Cloud-hosted database
🚀 Cloud deployment using Railway
🔗 GitHub-based deployment
📱 Accessible through the web
🎯 Project Objective

The main objective of ShopAI is to demonstrate how Generative AI can be integrated into a real-world e-commerce application to create a more intelligent and personalized shopping experience.

Instead of presenting the same products to every customer, ShopAI aims to understand each user's preferences and shopping behavior and provide more relevant product recommendations.

🔮 Future Enhancements
🔐 Advanced authentication using Spring Security
🧠 Improved recommendation algorithms
🛒 AI-powered cart assistance
📊 User behavior analytics
🔎 Semantic product search
🧬 Vector database integration
🖼️ AI-powered product image understanding
🎯 Advanced personalization
📱 Improved mobile responsiveness
💳 Payment gateway integration
🌐 Live Demo

Coming Soon / Add your Railway URL here

YOUR_RAILWAY_URL
👨‍💻 Author
Muhammed Navas S

GenAI Designathon 2026
