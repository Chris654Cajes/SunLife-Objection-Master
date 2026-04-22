# SunLife Objection Master

An Android application designed to help sales professionals handle customer objections effectively with comprehensive response scripts, psychological insights, and confidence-building tips.

## Features

- **Comprehensive Objection Database**: Access a wide range of common customer objections with detailed responses
- **Smart Search**: Quickly find relevant objections using the powerful search functionality
- **Audio Support**: Listen to objection responses using text-to-speech technology
- **Detailed Information**: Each objection includes:
  - Response scripts
  - Follow-up questions
  - Psychological insights
  - Confidence tips
- **Modern UI**: Clean, Material Design-inspired interface with card-based layouts
- **Responsive Design**: Optimized for various screen sizes and device types

## Screenshots

### Main Screen
- Search functionality with real-time filtering
- List of objections in card format
- Audio playback controls for each objection

### Detail Screen
- Comprehensive objection information
- Organized sections for different aspects of handling objections
- Audio playback for response scripts

## Technical Details

### Architecture
- **MVVM Pattern**: Model-View-ViewModel architecture for clean separation of concerns
- **Android Jetpack**: Utilizes ViewModel, LiveData, and other Jetpack components
- **Material Design**: Follows Material Design guidelines for consistent UI/UX

### Key Components
- `MainActivity`: Main screen with search and objection list
- `ObjectionDetailActivity`: Detailed view of individual objections
- `ObjectionViewModel`: Manages objection data and business logic
- `ObjectionAdapter`: RecyclerView adapter for objection list
- `TextToSpeechManager`: Handles audio playback functionality

### Dependencies
- Android Jetpack components
- Material Design components
- RecyclerView for efficient list display
- SearchView for filtering functionality

## Installation

1. Clone this repository
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Build and run the application on an Android device or emulator

## Requirements

- Android Studio Arctic Fox or later
- Android SDK 21 (Android 5.0) or higher
- Kotlin 1.5.0 or higher

## Usage

1. **Browse Objections**: Scroll through the list of available objections on the main screen
2. **Search**: Use the search bar to find specific objections quickly
3. **View Details**: Tap on any objection card to view detailed information
4. **Audio Playback**: Use the play button to listen to objection responses
5. **Navigate**: Use the back button to return to the main screen

## File Structure

```
app/
├── src/main/
│   ├── java/com/objectionmaster/
│   │   ├── MainActivity.kt
│   │   ├── ObjectionDetailActivity.kt
│   │   ├── ui/adapter/
│   │   │   └── ObjectionAdapter.kt
│   │   ├── viewmodel/
│   │   │   └── ObjectionViewModel.kt
│   │   └── utils/
│   │       └── TextToSpeechManager.kt
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml
│   │   │   ├── activity_objection_detail.xml
│   │   │   └── objection_list_item.xml
│   │   ├── drawable/
│   │   │   └── search_background.xml
│   │   └── values/
│   │       ├── colors.xml
│   │       ├── strings.xml
│   │       └── styles.xml
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

Copyright © 2025 SunLife Objection Master. All rights reserved.

## Support

For support and inquiries, please contact the development team.

---

*Last updated: April 2025*
