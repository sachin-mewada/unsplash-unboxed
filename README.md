# unsplash-unboxed
# Image Loading and Caching Android App

This repository contains code for an Android application that efficiently loads and displays images in a scrollable grid using the Unsplash API. The app also implements caching to store images for efficient retrieval.

## Instructions

### Prerequisites
- Android Studio installed on your machine.
- Internet connection to fetch images from the Unsplash API.

### Running the App

1. Clone the Repository: Clone this repository to your local machine using the following command:
   
   git clone "https://github.com/sachin-mewada/unsplash-unboxed.git"

2. Open the Project in Android Studio:
    - Launch Android Studio.
    - Select "Open an existing Android Studio project" from the welcome screen.
    - Navigate to the directory where you cloned the repository and select it.

3. Build and Run the App:
    - After the project is opened in Android Studio, wait for the Gradle sync to complete.
    - Once the sync is finished, you can build the app by clicking on the green play button in the toolbar or by selecting "Run > Run 'app'" from the menu.
    - Choose an emulator or a connected device to run the app on.
    - The app will be installed and launched on the selected device/emulator.

4. Using the App:
    - Upon launching the app, you'll see a scrollable grid of images fetched from the Unsplash API.
    - Scroll through the grid to view more images.
    - Images are cached for efficient retrieval, ensuring smooth scrolling performance.

### Additional Notes
- API Key: Ensure that you have a valid API key for the Unsplash API. If not, you can obtain one by signing up at [Unsplash Developer](https://unsplash.com/developers) and replace the placeholder API key in the Constant objects of utils directory code.
- Error Handling: The app handles network errors and image loading failures gracefully, providing informative error messages or placeholders for failed image loads.
