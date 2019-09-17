# MealPlanner
Personal meal planning app (In Progress) 

This app is a personal project with the goal of promoting healthier eating as well as discouraging habits such 
as unnecessary eating out through careful meal planning.

## SplashActivity 
This is the first Activity that the user sees and acts as kind of a loading screen. Current splash activity has the app title fade into view in white against a colored background. Future additions may include the app logo and a custom animation that spins the logo before continuing to the LoginActivity.

![SplashActivity](/Images/SplashActivity.PNG)

## LoginActivity
This activity allows the user to either sign up or log in using an email and password. The password must be at least 6 characters in length and the emails must not have been used previously. The accounts created through this activity are stored in the database. If a user forgets to enter an email, enters an invalid email (no '@' character or not '.' character), or enters a password that is not long enough an error message will appear for the user. If the user successfully logs in or signs up it will be indicated using a short toast and will then transition into the MainActivity.

![LoginActivity](/Images/LoginActivity.PNG)

## MainActivity
This activity is the first thing that the user sees once they are logged in. This activity can display several fragments, the first of which is the **TodayFragment**, which will eventually display the recipes that have been planned for the current day. In this activity the user can also view the **CalendarFragment** which will eventually display a calendar to be used to schedule meals on certain days, and the **RecipesFragment** which displays the all the recipes that the user has ever entered into the app but sorted by the recipe's category.

![MainActivity/TodayFragment](/Images/TodayFragment.PNG)

## NewRecipeActivity
This activity is used for the user to store new recipes into the app. The activity uses three different fragments to achieve this goal: AddRecipeBulkFragment, AddIngredientsFragment, AddDirectionsFragment. The AddRecipeBulkFragment is the first to be view and takes in most of the information which includes the new recipe's name, its creator, the cook time required for the recipe, the recipe difficulty, and the category that the recipe falls into (meal, dessert, drink, other). The second fragment, AddIngredientsFragment, lets the user input the ingredients the recipe may require as well as how much of each ingredient. Once the ingredient is added it is then added to a list (RecyclerView) that the user can use to view or remove the ingredients that were already added. The final fragment, AddDirectionsFragment, allows the user to input the steps required to make the recipe as well as upload a photo to represent the recipe. The design of the last fragment is still being decided.

Carrot icon made by Smashicons from https://www.flaticon.com/

![Progress Map](/Images/MealPlanner.png)
