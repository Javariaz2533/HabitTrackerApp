# Habit Tracker – Android Application

## Overview

Habit Tracker is an Android application developed using **Kotlin** and **Jetpack Compose**.
The app allows users to create, view, update, and delete daily habits, supports offline usage through local data persistence, and demonstrates server integration, notifications, and permission handling.

The project follows an **agile development approach** using Scrum, with version control managed through Git.

## Key Features

* Splash screen on launch
* Habit creation, editing, and deletion
* Local data persistence using **Room**
* Offline-first behaviour with cached data
* Server data integration using **Retrofit** (mock REST API)
* Habit reminder notifications
* Runtime permission handling (Android 13+)
* Secure HTTPS communication

---

## Technical Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose
* **Architecture:** MVVM
* **Local Database:** Room
* **Networking:** Retrofit
* **Asynchronous Processing:** Kotlin Coroutines
* **Build System:** Gradle
* **IDE:** Android Studio

---

## Setup & Installation

### Prerequisites

* Android Studio (latest stable version recommended)
* Android SDK (API level 24 or above)
* Internet connection (required only for server sync demonstration)

### How to Run

1. Open Android Studio
2. Select **Open an existing project**
3. Navigate to the project root folder
4. Allow Gradle to sync
5. Run the app on an Android Emulator or physical device

No additional configuration is required.

---

## Permissions Used

* `INTERNET` – required for server data synchronization
* `POST_NOTIFICATIONS` – required for habit reminder notifications (Android 13+)

The application requests only essential permissions and does not collect personal or sensitive user data.

---

## Data Persistence

* Habit data is stored locally using Room
* Cached data is available when the device is offline
* Server data is synchronised and stored locally for reliability

---

## Version Control

* Git is used for source control
* Feature branches are created per user story
* Development is merged into the main branch at sprint completion

---

## Notes

* A mock REST API is used to demonstrate server integration
* The app is intended for academic assessment purposes and is not production-ready

---

## Build Status

The project builds and runs successfully on an Android Emulator without additional setup.
