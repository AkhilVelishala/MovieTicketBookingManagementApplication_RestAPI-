 # MovieTicketBookingManagementApplication_RestAPI

##  Modules
- UserModule
- TheaterModule
- MovieModule
- ScreenModule
- SeatModule
- TicketModule

[![Screenshot-2024-12-20-171004.png](https://i.postimg.cc/kGKnbJ2d/Screenshot-2024-12-20-171004.png)](https://postimg.cc/Lg4KdMky)
#  
 ### **UserModule**
 -  **User :** Represents a user in the system. The user can have different roles (Admin or User).
 -  *Admin:* A user with elevated privileges who can manage users, view logs, and perform administrative tasks.
 -  *User:* A regular user who can access their profile, make bookings, etc.

   ***Endpoints:***

   #
   ### **TheaterModule**
   - The TheaterModule is responsible for managing theaters in the Movie Ticket Booking Management System. It provides endpoints for creating, retrieving, updating, and deleting theaters. Admin users have the ability to modify theater information, while regular users can view theater details.

   ***Attributes:***
    - ***id:*** A unique identifier for the Theater.
    - ***name:*** The name of the Theater.
    - ***location:*** Physical location of the theater.
    

     [![Screenshot-2024-12-19-122215.png](https://i.postimg.cc/s2bs57mb/Screenshot-2024-12-19-122215.png)](https://postimg.cc/jW6pbWGh)

   #
   ### **MovieModule**
   - The MovieModule is a vital component of the Movie Ticket Booking Management System that handles all operations related to movies. This module provides functionality for adding, retrieving, updating, and deleting movies from the system. It allows admins to manage movie data, while users can view a list of available movies and access detailed information about each movie.
     
   ***Attributes:***

   - ***id:*** A unique identifier for the movie.
   - ***name:*** The title of the movie.
   - ***description:*** A brief description of the movie’s plot or storyline.
   - ***genre:*** The genre of the movie (e.g., Action, Comedy, Drama, etc.).
   - ***duration:*** The total duration of the movie in minutes.
   - ***releaseDate:*** The release date of the movie.
   - ***rating:*** The movie’s average rating (e.g., from 1 to 10, or as a rating system).


