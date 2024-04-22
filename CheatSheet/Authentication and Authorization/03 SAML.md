[refer](https://en.wikipedia.org/wiki/SAML_2.0)

**For authentication and basic authorization, Assertions are validators**

![[saml.png]]

**Security Assertion Markup Language 2.0** (**SAML 2.0**) is a version of the [SAML](https://en.wikipedia.org/wiki/Security_Assertion_Markup_Language "Security Assertion Markup Language") standard for exchanging [authentication](https://en.wikipedia.org/wiki/Authentication "Authentication") and [authorization](https://en.wikipedia.org/wiki/Authorization "Authorization") identities between [security domains](https://en.wikipedia.org/wiki/Security_domain "Security domain"). SAML 2.0 is an [XML](https://en.wikipedia.org/wiki/XML "XML")-based [protocol](https://en.wikipedia.org/wiki/Communications_protocol "Communications protocol") that uses [security tokens](https://en.wikipedia.org/wiki/Software_token "Software token") containing [assertions](https://en.wikipedia.org/wiki/Security_Assertion_Markup_Language "Security Assertion Markup Language") to pass information about a principal (usually an end user) between a SAML authority, named an [Identity Provider](https://en.wikipedia.org/wiki/Identity_Provider "Identity Provider"), and a SAML consumer, named a [Service Provider](https://en.wikipedia.org/wiki/Service_Provider "Service Provider"). SAML 2.0 enables web-based, cross-domain [single sign-on](https://en.wikipedia.org/wiki/Single_sign-on "Single sign-on") (SSO), which helps reduce the administrative overhead of distributing multiple authentication tokens to the user.

An assertion is a package of information that supplies zero or more statements made by a SAML authority.
- Authentication Statement: The assertion subject was authenticated by a particular means at a particular time.
- Attribute Statement: The assertion subject is associated with the supplied attributes.
- Authorization Decision Statement: A request to allow the assertion subject to access the specified resource has been granted or denied

An important type of SAML assertion is the so-called "bearer" assertion used to facilitate Web Browser SSO. Here is an example of a short-lived bearer assertion issued by an identity provider (https://idp.example.org/SAML2) to a service provider (https://sp.example.com/SAML2).

There are actually three key players that make SSO possible:

- **The Principal** (sometimes known as the “subject”): the user trying to access an app.
- **Identity provider (IdP)**: the service that performs authentication, stores and confirms a user’s identity.
- **Service provider (SP):** the app or service the user wants to access who trusts the identity provider and authorizes access.



There are two types of ways to initiate SSO:

- **IdP-initiated SSO:** users need to log into an IdP’s SSO page, and then they’ll be redirected to the app or platform. **One login for multiple Service provider**
- **SP-initiated SSO:** users need to log in directly to an app’s login screen. This will send an authorization request to the app’s IdP. Once the user’s identity is authenticated, they’ll be able to log in and  the app.