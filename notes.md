# RESTful Web Services


eg. Social Media Application

User -> Posts

- Retrieve all Users 	- GET  /users
- Create User 			- POST /users
- Retrieve one User 	- GET  /users/{id} -> /users/1
- Delete a User 		- DELETE /users/{id} -> /users/1



- Retrieve all posts for a user  - GET /users/{id}/posts
- Create a post for a user       - POST /users/{id}/posts
- Retrieve details of a post     - GET /users/{id}/posts/{post_id}



Spring Boot auto-configures most things like the dispatcher servlet
and conversion of response to json