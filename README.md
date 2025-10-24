# Android-UI-testing
Android UI Testing - CMPUT 301 Lab 7 

Compile SDK - 34 

Author - Tanisha Dhull 

ccid : tdhull 

Reference(s) : OpenAI ChatGPT 5.0 

Implementation Details
1. New Activity: ShowActivity

Purpose: Displays the city name selected from the MainActivity list.

Features Added:

A TextView to show the selected city’s name.

A Back button (button_back) to return to the MainActivity.

A Title layout where the city name appears on the left and the Back button is aligned to the right for a clean UI.

2. Intent Handling

The MainActivity sends the city name via an Intent extra using:

intent.putExtra("CITY_NAME", cityName);


ShowActivity retrieves it with:

String cityName = getIntent().getStringExtra("CITY_NAME");
text_selected_city.setText(cityName);

3. Back Button Functionality

A click listener was added to handle the return action:

button_back.setOnClickListener(v -> finish());


This ensures the user navigates back to MainActivity.

🧪 Testing Details
Test Cases Implemented

Activity Switch Test:
Verified that clicking on a city in the list correctly opens ShowActivity.
Used onData() and inAdapterView() to perform item selection.

City Name Consistency Test:
Confirmed that the name shown in ShowActivity matches the one clicked in MainActivity.

Back Button Navigation Test:
Checked that pressing the Back button (or system back if needed) returns to MainActivity.
Verified by asserting that the city list view is visible again after returning.

🧱 Key Methods and Variables
Component	Type	Description
text_selected_city	TextView	Displays the chosen city name
button_back	Button	Returns user to MainActivity
onClick(View v)	Method	Handles back navigation
putExtra() / getStringExtra()	Intent methods	Pass and retrieve city name between activities
onData() / onView()	Espresso methods	Locate UI components for testing
matches()	Assertion method	Verifies UI elements are displayed correctly
✅ Outcome

The app now supports full UI navigation between the two activities.

The tests confirm correct transitions, data consistency, and back navigation.

The project meets all participation exercise requirements for functionality and UI testing.



