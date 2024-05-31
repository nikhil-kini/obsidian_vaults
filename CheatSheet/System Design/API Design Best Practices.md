
[What is the use of all GET, PUT, DELETE when anything can be done by POST in the most secured way of communication in REST API calls](https://stackoverflow.com/questions/62360163/what-is-the-use-of-all-get-put-delete-when-anything-can-be-done-by-post-in-the)

Note that, historically, [SOAP](https://en.wikipedia.org/wiki/SOAP) essentially did everything by POST; effectively reducing HTTP from an application protocol to a transport protocol.

The big advantage of GET/PUT/DELETE is that the additional semantics that they promise (meaning, the semantics that are part of the uniform interface agreed to by _all_ resources) allow us to build _general purpose_ components that can do interesting things with the meta data alone, without needing to understand anything specific about the body of the message.

The most important of these is `GET`, which promises that the action of the request is safe. What this means is that, for any resource in the world, we can just try a GET request to "see what happens".

In other words, because GET is safe, we can have web crawlers, and automated document indexing, and eventually Google.

(Another example - today, I can send you a bare URI, like [https://www.google.com](https://www.google.com) and it "just works" because GET is understood uniformly, and does not require that we share any further details about a payload or metadata.)

Similarly, PUT and DELETE have additional semantic constraints that allow general purpose components to do interesting things like automatically retry lost requests when the network is unreliable.

POST, however, is effectively unconstrained, and this greatly restricts the actions of general purpose components.

That doesn't mean that POST is [the wrong choice](https://roy.gbiv.com/untangled/2009/it-is-okay-to-use-post); if the semantics of a request aren't worth standardizing, then POST is _fine_.

## Versioning

- Helps client to differentiate between the stable and development branch.
```java
// Version 1 
"/api/v1/workouts" 

// Version 2 
"/api/v2/workouts" 

// ...
```

## Name Resources in Plurals

The creation endpoint **/api/v1/workout**, there's nothing wrong with that approach – but this can lead to misunderstandings.

Resources are like a box, is a collection that stores different **workouts**. **/api/v1/workouts** would be more intuitive. 

## Accept and respond with data in JSON format


## Respond with standard HTTP Error Codes

To eliminate confusion for API users when an error occurs, we should handle errors gracefully and return HTTP response codes that indicate what kind of error occurred. This gives maintainers of the API enough information to understand the problem that’s occurred. We don’t want errors to bring down our system, so we can leave them unhandled, which means that the API consumer has to handle them.

Common error HTTP status codes include:

- 400 Bad Request - This means that client-side input fails validation.
- 401 Unauthorized - This means the user isn't not authorized to access a resource. It usually returns when the user isn't authenticated.
- 403 Forbidden - This means the user is authenticated, but it's not allowed to access a resource.
- 404 Not Found - This indicates that a resource is not found.
- 500 Internal server error - This is a generic server error. It probably shouldn't be thrown explicitly.
- 502 [Bad Gateway](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/502) - This indicates an invalid response from an upstream server.
- 503 Service Unavailable - This indicates that something unexpected happened on server side (It can be anything like server overload, some parts of the system failed, etc.).


## Avoid verbs in endpoint names, Use Nouns

The action should be indicated by the HTTP request method that we're making. The most common methods include GET, POST, PUT, and DELETE.

- GET retrieves resources.
- POST submits new data to the server.
- PUT updates existing data.
- DELETE removes data.

The verbs map to [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) operations.

| CRUD   | HTTP                                                                                                                                                                                                                                                  |
| ------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Create | [POST, PUT](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods "Hypertext Transfer Protocol") if we have `id` or `uuid`                                                                                                        |
| Read   | [GET](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods "Hypertext Transfer Protocol")                                                                                                                                        |
| Update | [PUT](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods "Hypertext Transfer Protocol") to replace, [PATCH](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods "Hypertext Transfer Protocol") to modify |
| Delete | [DELETE](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods "Hypertext Transfer Protocol")                                                                                                                                     |
```javascript
// Current implementations (without verbs)
GET "/api/v1/workouts" 
GET "/api/v1/workouts/:workoutId" 
POST "/api/v1/workouts" 
PATCH "/api/v1/workouts/:workoutId" 
DELETE "/api/v1/workouts/:workoutId"  

// Implementation using verbs 
GET "/api/v1/getAllWorkouts" 
GET "/api/v1/getWorkoutById/:workoutId" 
CREATE "/api/v1/createWorkout" 
PATCH "/api/v1/updateWorkout/:workoutId" 
DELETE "/api/v1/deleteWorkout/:workoutId"
```
Having a completely different URL for every behavior can become confusing and unnecessarily complex pretty fast.



## Group associated resources together (logical nesting)

**/api/v1/workouts/:workoutId/records** 

Theoretically you can nest it how deep you want, but the rule of thumb here is to go three levels deep at a maximum.
```javascript
GET /api/v1/workouts/:workoutId/records/members/:memberId
```
The endpoint now becomes less manageable the more nesting we add to it.

## Integrate filtering, sorting & pagination

Normally in a GET request we add the filter criteria as a query parameter.

Our new URI will look like this, when we'd like to get only the workouts that are in the mode of "AMRAP" (**A**s **M**any **R**ounds **A**s **P**ossible): **/api/v1/workouts?mode=amrap.**

You can try it further with adding "for%20time" as the value for the "mode" parameter (remember --> "%20" means "white-space") and you should receive all workouts that have the mode "For Time" if there are any stored.

- Receive all workouts that require a barbell: **/api/v1/workouts?equipment=barbell**
- Get only 5 workouts: **/api/v1/workouts?length=5**
- When using pagination, receive the second page: **/api/v1/workouts?page=2**
- Sort the workouts in the response in descending order by their creation date: **/api/v1/workouts?sort=-createdAt**
- You can also combine the parameters, to get the last 10 updated workouts for example: **/api/v1/workouts?sort=-updatedAt&length=10**

## Use data caching for performance improvements

One appropriate example is to use [redis](https://www.npmjs.com/package/redis) or the express middle-ware [apicache](https://www.npmjs.com/package/apicache).

A few things you have to be aware of when using a cache:

- you always have to make sure that the data inside the cache is up to date because you don't want to serve outdated data
- while the first request is being processed and the cache is about to be filled and more requests are coming in, you have to decide if you delay those other requests and serve the data from the cache or if they also receive data straight from the database like the first request
- it's another component inside your infrastructure if you're choosing a distributed cache like Redis (so you have to ask yourself if it really makes sense to use it)

>**Start building an API and there are no particular reasons to use a cache straight away. When reasons arise to use a cache, implement it.**

## Good security practices

- The first and absolute must have is to use **SSL/TLS** because it's a standard nowadays for communications on the internet. It's even more important for API's where private data is send between the client and our API.
- Add Authentication and Authorization to access the endpoints
- To enforce the principle of least privilege, we need to add role checks either for a single role, or have more granular roles for each user.

## Document your API properly

Always remember that the documentation is usually the first interaction consumers have with your API. The faster users can understand the documentation, the faster they can use the API.

some sort of standard for documenting API's called [OpenAPI Specification](https://swagger.io/specification/).

Use Swagger-docs and other Auto documentation tools.