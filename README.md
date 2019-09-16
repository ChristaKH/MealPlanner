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

Carrot icon made by Smashicons from https://www.flaticon.com/

![Progress Map](/Images/MealPlanner.png)
