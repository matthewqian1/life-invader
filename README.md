# Life Invader App

## Background
I am creating a fitness app which tracks calorie consumption and goals. In the past I have found the benefit of being mindful of this to lose weight and now I want to create an app which acts as a clear tracker for this measurement. Im hoping to expand this to add more features and functionality to create a seamless user experience. My first real project using frontend coding too, a bit rough on the edges with this but learning as I go :) You can check it out here https://life-invader-ui-8f3ba75e3e79.herokuapp.com/

## Tech Stack
### Frontend (https://github.com/matthewqian1/life-invader-ui)
- JavaScript
- HTML
- CSS
### Backend
- Spring Boot Java
- MongoDB

## Design
### Nutrition API
Food searching leverages a third party API to get nutrition information https://api-ninjas.com/api/nutrition. To limit calls to the API, I store successful search results to the database, the database is queried first for any searches.
### Database
Storing all user and nutrition information in a MongoDB database
### Test Data 
To allow for easy usage of the app, default data such as a test user and test data are preloaded
### Features
- Login/Logout
- Break down of daily consumption items
- Query nutritional information by food name
- Display past 1 week of calorie consumption and comparing to daily calorie goal to track progression

## Demo
https://github.com/matthewqian1/life-invader/assets/66478998/252565f3-b1eb-4cb9-968a-5fa5584dfbe8

## Improvements/Future Considerations
- Layout/Design/Colouring
- Add future consumption recommendations based on current progress (food recs/future calorie breakdown)
- Add exercise to measure calories too
- Social media/network features (posts/likes/friends)
- Web scraping for health news
- ChatGPT Integration as a help bot

