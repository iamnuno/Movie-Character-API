# Movie-Character-API
Java Hibernate project made to simulate a IMDB style database.

The application is constructed using Spring, Spring Data, and Hibernate, and using PostgreSQL and a REST API to interact and manipulate the data.

## Project Requirements
1. The database entities should interact in a certain way:
    1. One movie contains many characters.
    2. One character can play in multiple movies.
    3. One movie belongs to one franchise.
    4. One franchise can contain many movies.

2. The character entities should minimum contain these data points:
    1. Auto incremented id.
    2. Full name.
    3. Alias (if applicable).
    4. Gender.
    5. Picture.

3. The movie entities should minimum contain these data points:
    1. Auto incremented id.
    2. Movie title.
    3. Genre.
    4. Release year.
    5. Director.
    6. Picture.
    7. Trailer.

4. The franchise entities should minimum contain these data points:
    1. Auto incremented id.
    2. Name.
    3. Description.
  
5. The API endpoints should account for:
    1. Generic CRUD and reporting methods.
    2. Capabilities to obtain all movies in a franchise.
    3. Capabilities to obtain all characters in a movie.
    4. Capabilities to obtain all characters in a franchise.
 
6. The API endpoints should have good naming convention and structure, and account for versioning.

7. Documentation for endpoint hierarchy should be made.

8. A collection of API calls in postman should be included.

9. The project should be published on Heroku.

## External Links
Heroku: https://character-movie-app.herokuapp.com \
Endpoint hierarchy: https://character-movie-app.herokuapp.com/swagger-ui/

## Authors
Project by: \
    1. Nuno Cunha \
    2. Okko Partanen \
    3. Rasmus Ulrichsen

