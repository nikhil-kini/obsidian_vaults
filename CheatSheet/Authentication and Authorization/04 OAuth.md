**Only for authorization and delegated authorization, tokens are validators**

![[Oauth.png]]


[OAuth 2](https://oauth.net/2/) is an authorization framework that enables applications — such as GitHub, and DigitalOcean — to obtain limited access to user accounts on an HTTP service. It works by delegating user authentication to the service that hosts a user account and authorizing third-party applications to access that user account. OAuth 2 provides authorization flows for web and desktop applications, as well as mobile devices.

### OAuth Roles

OAuth defines four roles:

- **Resource Owner**: The resource owner is the _user_ who authorizes an _application_ to access their account. The application’s access to the user’s account is limited to the scope of the authorization granted (e.g. read or write access)
- **Client**: The client is the _application_ that wants to access the _user_’s account. Before it may do so, it must be authorized by the user, and the authorization must be validated by the API.
- **Resource Server**: The resource server hosts the protected user accounts.
- **Authorization Server**: The authorization server verifies the identity of the _user_ then issues access tokens to the _application_.

### Client ID and Client Secret

The **Client ID** is a publicly exposed string that is used by the service API to identify the application, and is also used to build authorization URLs that are presented to users.
The **Client Secret** is used to authenticate the identity of the application to the service API when the application requests to access a user’s account, and must be kept private between the application and the API.

### Authorization Grant

The authorization grant type depends on the method used by the application to request authorization, and the grant types supported by the API. OAuth 2 defines three primary grant types, each of which is useful in different cases:

- **Authorization Code**: used with server-side Applications
- **Client Credentials**: used with Applications that have API access
- **Device Code**: used for devices that lack browsers or have input limitations