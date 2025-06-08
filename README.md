# Rick and Morty Kotlin App

Rick and Morty Kotlin App is a modern Android application built using **Clean Architecture**, following best practices to ensure maintainability, scalability, and testability. The app fetches Rick and Morty data from an API GraphQL, supports pagination, uses image loading.

## 📂 Project Structure

The project follows **Clean Architecture**, which divides the codebase into three layers:

### 1️⃣ **Presentation Layer**

- Implements **MVVM (Model-View-ViewModel) architecture**.
- Uses **Jetpack Compose** for UI.
- Handles user interactions and UI state.
- Uses **Hilt** for dependency injection.

### 2️⃣ **Domain Layer**

- Contains **use cases** that encapsulate business logic.
- Defines repository interfaces.
- Independent of frameworks and UI.

### 3️⃣ **Data Layer**

- Implements repositories and data sources.
- Uses **Apollo GraphQL** for network requests.
- Caches data using **Room Database**.
- Implements pagination with **Paging 3**.

## 🛠️ Tech Stack

- **Kotlin** - Modern, concise, and expressive language.
- **Clean Architecture** - Separation of concerns for better maintainability.
- **MVVM** - Architecture pattern for handling UI and state.
- **Hilt** - Dependency Injection framework.
- **Apollo GraphQL** - For networking and API-GraphQL calls.
- **Room Database** - Local database for caching.
- **Paging 3** - Efficient data loading with pagination.
- **Coil** - Image loading and caching.

## 🔧 Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/OscarDiazMunar/rickandmorty_kotlin.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle and run the app on an emulator or a physical device.

## 🚀 Features

✅ Fetch Characters list from API-GraphQL\
✅ Implement infinite scrolling with Paging 3\
✅ Cache data using Room Database\
✅ Load images efficiently with Coil\
✅ Follow Clean Architecture best practices

## 📸 Screenshots

Here are some screenshots of Rick and Morty in action:

### Home Screen
<p align="center">
  <img width="270" src="https://github.com/OscarDiazMunar/rickandmorty_kotlin/blob/main/screenshots/screen1.png"/>
</p>

### Movie Details
<p align="center">
  <img width="270" src="https://github.com/OscarDiazMunar/rickandmorty_kotlin/blob/main/screenshots/screen2.png"/>
</p>

<p align="center">
  <img width="270" src="https://github.com/OscarDiazMunar/rickandmorty_kotlin/blob/main/screenshots/screen3.png"/>
</p>