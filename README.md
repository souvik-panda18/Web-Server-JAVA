# 🧵 Multi-Threaded Java Client-Server Application

This project demonstrates a **multi-threaded server** and multiple **Java client threads** communicating using **sockets**. It shows how Java can handle concurrent client connections and basic message exchange between server and client using TCP.

---

## 🚀 Features

- ✅ Multi-threaded server that accepts and handles multiple client connections simultaneously
- ✅ Clients connect to the server and receive a welcome message
- ✅ Uses `Socket`, `ServerSocket`, `PrintWriter`, and `BufferedReader`
- ✅ Demonstrates the use of `Runnable`, thread creation, and network communication

---

## 📂 Project Structure

Single-and-multi-threded-JAVA-Server-
│
├── Server.java # Multi-threaded server that listens for client connections
├── Client.java # Client class that creates 100 threads to connect to the server
├── README.md # This file

---

## 🧠 How It Works

### 🖥️ Server Side (`Server.java`)
- Listens on port `8010`
- Accepts incoming socket connections
- Spawns a new thread for each connected client using a `Consumer<Socket>`
- Sends a welcome message to each client

### 💻 Client Side (`Client.java`)
- Starts 100 threads
- Each thread:
  - Connects to the server
  - Sends a greeting
  - Receives a response from the server
  - Prints the response

---

## 🛠️ How to Run

### ✅ Prerequisites
- Java 8 or above
- Git (optional for cloning)
- Any IDE or terminal

### 🔧 Steps


