# NAB Weather forecast challenge

## **1. Application Architecture:**

![Application Architecture](nab_architecture.png)

- Application Architecture: **MVVM**
- Dependency Injection Framework: **Hilt**
- Networking: **Retrofit 2**
- Local database: **Room database**
- Multithreading: **Kotlin Coroutine for Android**
- Databinding with LiveData.
- AndroidX and KTX libraries like: viewmodel-ktx, livedata-ktx.
- Testing: JUnit4, Robolectric, Hamcrest, Room testing, Coroutine testing.

## **2. Code folders:**
- _/data_: Include repositories, local and remote data sources.
- _/di_: Include DI modules.
- _/ext_: Include Kotlin Extension functions.
- _/ui_: Include the weather activity, viewmodel and weather list adapter.
- _/util_: Include helper-classes and functions.

## **3. Local development:**
- ```Android Studio 4.0``` or above.
- Gradle: ```AGP 4.1.0```
- Useful Gradle/Adb commands:
  - ```./gradlew :app:assembleBetaDebug``` for ```beta``` flavor debug build.
  - ```./gradlew :app:assembleBetaRelease``` for ```beta``` flavor release build.
  - ```./gradlew :app:assembleProductDebug``` for ```product``` flavor debug build.
  - ```./gradlew :app:assembleProductRelease``` for ```product``` flavor release build.
  
## **4. Check list:**
- [x] The application is a simple Android application which is written by Java/Kotlin.\
- [x] The application is able to retrieve the weather information from OpenWeatherMaps\
API.\
- [x] The application is able to allow user to input the searching term.\
- [x] The application is able to proceed searching with a condition of the search term length
must be from 3 characters or above.\
- [x] The application is able to render the searched results as a list of weather items.\
- [x] The application is able to support caching mechanism so as to prevent the app from
generating a bunch of API requests.\
- [x] The application is able to manage caching mechanism & lifecycle.\
- [x] The application is able to handle failures.\
- [x] The application is able to support the disability to scale large text for who can't see the
text clearly.\
- [x] The application is able to support the disability to read out the text using VoiceOver
controls.\
