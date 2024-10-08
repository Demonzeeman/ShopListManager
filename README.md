•	Home: The landing page of the app, providing quick access to all features.
•	Add Items: Users can add new items (e.g., eggs, milk, bread) to the app's library for future use.
•	Create List: Users can generate a new shopping list by selecting items from the app's library.
•	Previous Lists: Displays all saved lists, allowing users to view, edit, and strike through items as they are purchased.
________________________________________
Code Breakdown by File Path:
1.	androidTest/java/com/christiaan/shoppinglist/ExampleInstrumentedTest.kt
Instrumentation test to validate the app’s behavior on an actual Android device.
________________________________________
2.	main/AndroidManifest.xml
The manifest file outlines the app's essential information, including permissions, app components (activities, services), and intent filters.
________________________________________
3.	Main Java Directory:
o	MainActivity.kt
The entry point for the app. Handles the main navigation, linking to various fragments like Home, CreateList, AddItems, and PreviousLists.
________________________________________
4.	Data Layer (Database, Models, Repositories):
o	Converters.kt
Used to convert complex data types to simple types for database storage (e.g., lists to JSON strings and vice versa).
o	Item.kt
Data class representing an individual shopping item. Contains properties like name, quantity, and category.
o	ItemDao.kt
Data Access Object (DAO) responsible for CRUD operations related to Item objects within the database.
o	ItemDatabase.kt
Room database class that provides the main entry point for accessing the database.
o	ItemListFragment.kt
Fragment displaying all available items that can be selected when creating a new shopping list.
o	ItemRepository.kt
Centralizes all data operations for the Item entity, fetching data from the database and returning it to the ViewModel.
o	ShoppingList.kt
Data class representing a shopping list. Stores a list of Item objects, along with metadata (e.g., list name, creation date).
o	ShoppingListConverters.kt
Converters for transforming the ShoppingList object’s contents to a storable format (such as JSON) for Room persistence.
o	ShoppingListDao.kt
DAO for accessing and managing ShoppingList objects in the database. Handles operations like fetching, adding, and updating lists.
o	ShoppingListRepository.kt
Repository responsible for managing data related to shopping lists, linking the database to the ViewModel.
________________________________________
5.	UI Layer (ViewModels, Adapters, Fragments):
o	ItemViewModel.kt
ViewModel that handles all UI-related data for managing individual items. Fetches, adds, and updates items in the app.
o	ItemViewModelFactory.kt
Factory class that constructs ItemViewModel objects, injecting necessary dependencies (like repositories).
________________________________________
6.	Adapters for Recycler Views:
o	ItemAdapter.kt
Adapter for displaying individual items in a RecyclerView (e.g., in the item list or when creating a shopping list).
o	ShoppingListAdapter.kt
Adapter for displaying a list of shopping lists in a RecyclerView (e.g., in the PreviousListsFragment).
________________________________________
7.	Fragment Files for Different App Screens:
o	CreateListFragment.kt
Fragment for creating new shopping lists by selecting items from the app’s item library.
o	NewItemsFragment.kt
Fragment for adding new items to the item library.
o	PreviousListsFragment.kt
Fragment displaying saved shopping lists. Allows users to open, view, and edit each list.
o	HomeFragment.kt
The home screen fragment that provides access to the main features of the app.
________________________________________
8.	UI Layout Files (XML):
These files define the visual structure of different screens and components in the app.
o	activity_main.xml
Layout for the main activity that contains the navigation drawer and fragment container.
o	fragment_create_list.xml
Layout for the create list fragment.
o	fragment_home.xml
Layout for the home screen.
o	fragment_item_list.xml
Layout for displaying a list of items.
o	fragment_new_items.xml
Layout for adding new items.
o	fragment_previous_lists.xml
Layout for viewing previous shopping lists.
________________________________________
9.	Navigation (XML):
o	mobile_navigation.xml
Contains the navigation graph, defining the different destinations (fragments) within the app and how they are connected.
________________________________________
10.	Resources (Images and Colors):
•	Drawables and Icons:
Various icons and logos used in the app, such as baseline_home.xml, circlelogo.png, and other images for buttons.
•	Colors and Themes (XML):
Defines the color scheme of the app and UI themes for light and dark modes.
________________________________________
11.	Test Files:
•	test/java/com/christiaan/shoppinglist/ExampleUnitTest.kt
Unit tests for validating the logic of the app.
