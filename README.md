 # MovieTicketBookingManagementApplication_RestAPI
  The MovieTicketBookingSystem is a demo project developed to simulate a real-time movie ticket booking experience. This system provides users with the ability to 
  browse movie listings, view available theaters, select seats, and make bookings

  ##  Tired of:
- **Spending hours** searching for relevant content across multiple platforms?
- **Struggling to find** detailed and accurate information about movies, theaters, or ticket bookings?
-  **Dealing with disorganized** booking systems and inconsistent user experiences?


##  Modules
- UserModule
- TheaterModule
- MovieModule
- ScreenModule
- SeatModule
- TicketModule


#
 ### **UserModule**
 -  **User :** Represents a user in the system. The user can have different roles (Admin or User).
 -  *Admin:* A user with elevated privileges who can manage users, view logs, and perform administrative tasks.
 -  *User:* A regular user who can access their profile, make bookings, etc.

  ***Attributes:***

   - ***id:***  Unique identifier for the user.
   - ***name:*** The name of the user.
   - ***email:*** The email address of the user.
   - ***password:*** The user’s password.
   - ***phoneNumber:*** The user's contact number.
   - ***User Role:*** Defines the user’s role. Can be either Admin or User.
     
 [![Screenshot-2024-12-20-183136.png](https://i.postimg.cc/yNth2mFV/Screenshot-2024-12-20-183136.png)](https://postimg.cc/06GJwJW3)

#
   ### **TheaterModule**
   - The TheaterModule is responsible for managing theaters in the Movie Ticket Booking Management System. It provides endpoints for creating, retrieving, updating, and deleting theaters. Admin users have the ability to modify theater information, while regular users can view theater details.

   ***Attributes:***
   - ***id:*** A unique identifier for the Theater.
   - ***name:*** The name of the Theater.
   - ***location:*** Physical location of the theater.

 [![Screenshot-2024-12-20-184044.png](https://i.postimg.cc/d39D8CvR/Screenshot-2024-12-20-184044.png)](https://postimg.cc/GTHcRH49)

 ***Endpoints:***

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
     [![Screenshot-2024-12-20-220452.png](https://i.postimg.cc/t4TTFWPz/Screenshot-2024-12-20-220452.png)](https://postimg.cc/D89nTWxW)
     
***Endpoints:***

   [![Screenshot-2024-12-19-122254.png](https://i.postimg.cc/qM8SkRRc/Screenshot-2024-12-19-122254.png)](https://postimg.cc/nCh02nqM)

#
   ### **ScreenModule**
   - The ScreenModule is a key part of the Movie Ticket Booking Management System that handles the management of screens within theaters. It allows admins to add, update, retrieve, and delete screens in the system. Each screen is associated with a theater and has specific attributes like capacity and name.
     
   ***Attributes:***

   - ***id:*** Unique identifier for the screen.
   - ***name:*** Name of the screen (e.g., "Screen 1", "IMAX").
   - ***capacity:*** The seating capacity of the screen.
     
     [![Screenshot-2024-12-20-220858.png](https://i.postimg.cc/66MLKL9r/Screenshot-2024-12-20-220858.png)](https://postimg.cc/rzttC4Rs)
   
   ***Endpoints:***

   [![Screenshot-2024-12-20-220924.png](https://i.postimg.cc/wBWLz9z6/Screenshot-2024-12-20-220924.png)](https://postimg.cc/TyDpqMhS)

#
   ### **SeatModule**
   - The SeatModule is an essential part of the Movie Ticket Booking Management System that handles the management of seats in theater screens. It ensures proper seat allocation, availability tracking, and categorization of seats into types such as Regular and Premium. Admin users can update seat availability, while all users can view available seats for a specific screen.

        ***Attributes:***

   - ***id:*** Unique identifier for the seat.
   - ***row:*** The row where the seat is located (e.g., "A", "B").
   - ***number:*** The seat number within the row.
   - ***type:*** Specifies the type of seat: Regular or Premium.
   - ***availability:*** Indicates whether the seat is available (true) or booked (false).
     
 [![Screenshot-2024-12-20-221829.png](https://i.postimg.cc/rwdWGB2s/Screenshot-2024-12-20-221829.png)](https://postimg.cc/nXJCpwzf)

   ***Endpoints:***

   [![Screenshot-2024-12-20-221849.png](https://i.postimg.cc/SRy1Jy5J/Screenshot-2024-12-20-221849.png)](https://postimg.cc/9RnYkjch)

#
   ### **TicketModule**
   - The TicketModule manages ticket booking, retrieval, and cancellation operations within the Movie Ticket Booking Management System. It enables users to book tickets for a movie, retrieve their ticket details, and cancel bookings. Admins can access a complete booking history for system oversight.

        ***Attributes:***

   - ***id:*** Unique identifier for the ticket.
   - ***userId:*** ID of the user who booked the ticket.
   - ***movieId:*** ID of the movie for which the ticket was booked.
   - ***theaterId:*** ID of the theater where the movie is being shown.
   - ***screenId:*** ID of the screen where the movie is being displayed.
   - ***seatIds:*** for the seat booked. This is used when a single seat is booked.
   - ***seatSelections:*** A list of detailed information about each seat booked as part of the ticket. This is used for multiple-seat bookings.

   ***SeatSelection Attributes***
     
   - ***row:*** The row where the seat is located (e.g., "A", "B").
   - ***number:*** The number of the seat within the row.
   - ***type:*** The type of the seat, indicating its classification (e.g., regular or premium).
   - ***status:*** The current status of the seat, indicating whether it is booked or cancelled.
   - ***price:*** The price of the seat based on its type (e.g., regular or premium).

     [![Screenshot-2024-12-20-223518.png](https://i.postimg.cc/wTVck1TB/Screenshot-2024-12-20-223518.png)](https://postimg.cc/tYsxqC60)

       ***Endpoints:***
     
     [![Screenshot-2024-12-20-223539.png](https://i.postimg.cc/s2tNRMWr/Screenshot-2024-12-20-223539.png)](https://postimg.cc/1grHG5MC)
     

      ***Endpoint:*** (http://localhost:2020/Ticket/get?id=11)
         
      [![Screenshot-2024-12-19-122551.png](https://i.postimg.cc/13qKxsGx/Screenshot-2024-12-19-122551.png)](https://postimg.cc/cvd3wqkT)

     ***Endpoint:*** (http://localhost:2020/Ticket/getAll)
     
    
     [![Screenshot-2024-12-19-122724.png](https://i.postimg.cc/vZh22QbQ/Screenshot-2024-12-19-122724.png)](https://postimg.cc/PCLMPnCc)
  
   ## Key Features:
     
   - ***User Registration and Authentication::*** Secure login and registration system.
   - ***Movie Listings:*** Browse through movies with detailed information such as title, genre, release date, and duration.
   - ***Real-Time Booking:*** Users can select available seats in a theater and book tickets instantly.
   - ***Admin Features:*** Admins can manage movie listings, theaters, and bookings.
   - ***Seat Availability:*** Real-time updates on seat availability, ensuring users don’t book already occupied seats.



     ##  Technologies Used
     
   - ***Java::*** The core programming language used for backend logic.
   - ***Spring Boot:*** Java framework for building REST APIs and handling backend services.
   - ***Spring Security with JDBC Authentication:*** For secure authentication and authorization using database credentials.
   - ***Spring Data JPA:*** Simplifies data access by providing an abstraction over JPA implementations.
   - ***Hibernate:*** ORM (Object-Relational Mapping) framework used as the JPA implementation.
   - ***MySQL:*** Relational database for storing application data.
   - ***REST API:*** For communication between the client and server.

     
     ##  Tools and Platforms
     
   - ***Postman::*** For API testing.
   - ***Maven:*** For project build and dependency management.
   - ***Spring Tool Suite (STS):***  IDE for Spring-based development.

  
     ##  Additional Features
     
   - ***Swagger:*** API documentation and testing.
   - ***Lombok:*** Reduces boilerplate code in Java.
   - ***JDBC Authentication:***  Implements authentication using credentials stored in the database.

     ## Setup Instructions
      ### **To set up and run the MovieTicketBookingSystem locally, follow these steps:**
     
     **1. Clone the Repository**:  git clone https://github.com/yourusername/MovieTicketBookingManagementApplication_RestAPI-.git
     - **Java 11 or higher:** Required for building and running the Spring Boot backend.
     - **Maven:** For project dependency management and building the backend application.
     - **MySQL:** Database system used for storing the data (or any other relational database).
     - ***Configure Database Connection:***  Open the application.properties file located in src/main/resources and update
        the database credentials if necessary:
    
       
       [![Screenshot-2024-12-20-234322.png](https://i.postimg.cc/RCHXjZh4/Screenshot-2024-12-20-234322.png)](https://postimg.cc/z3qCh5Tc)

       
     - **Run the Application:** Run The Application From Main Method.
       *This will start the backend on http://localhost:8080(default port No).*
       
     - **Accessing the API:**
       *Postman or Swagger:* You can use Postman or CURL to make API requests for the available endpoints.

       Thank you!
       Developed by: Velishala Akhil (MovieTicketBookingManagementApplication_RestAPI)

  
     

