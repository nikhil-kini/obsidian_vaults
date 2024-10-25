
[Reference](https://kinde.com/guides/authentication/overview/)

It’s part of a three-step process to:
- **Identify**: pinpoint who a user is.
- **Authenticate**: prove their identity with unique credentials.
- **Authorize**: define permission and access levels.

### Sessions, cookies and tokens
- When you sign in, the app creates a **token** (usually a series of random characters) that is stored on the app’s database.
- Within your browser, the **app creates a cookie with that token** linked.
- When you refresh or open a new page that needs to authenticate your identity, the app will compare the token in the cookie to the token in the database. If they match, you’ll remain logged in.

After a certain amount of time has elapsed, the **app will destroy the token on their server - meaning you’ll need to log in again and re-authenticate, sessions**, a.k.a. How long you’re logged in without needing to re-authenticate.

## Authentication Factors
Not all credentials are created equal. There are different ways to verify a user’s identity, known as authentication factors.
- **Knowledge factor (a.k.a. Something you know)**: as you’d expect this factor is the most common. It uses confidential information to authenticate access, whether it’s a PIN, username, password or the name of your first pet.
- **Possession factor (a.k.a. Something you have)**: this is something tangible you have access to, such as a security token, a smartphone that can receive text messages or an authentication app that generates Time-based One-Time Passwords (TOTPs) or codes.
- **Inherence factor (a.k.a. Something you are)**: this is where biometric identification comes into play, including fingerprint scans, voice or facial recognition and even retina patterns.
- **Location factor (a.k.a Where you are)**: this could mean pinging your device’s GPS or checking your computer’s network address. While it’s rarely used as a standalone factor, it can be used to detect and alert suspicious activity (such as an attempt to access your account from the other side of the world).
- **Time factor (a.k.a. When you’re authenticating)**: again, this factor won’t be used in isolation but it’s often used in combination with location data to catch and prevent hacking (for example, it could spot someone logging into your account shortly after your last session from a different country).
## Authentication vs Authorization
Authentication comes first (_who are you?_) and authorization comes second (_do you have access to this specific resource?_).
- **Authentication**: this is the process of validating your identity. It involves checking your credentials match the records in a database before giving you access to a secure system.
    - It involves sharing login credentials (such as passwords, fingerprint scans or OTPs).
    - It transmits information through an **ID token.**
- **Authorization**: is all about granting permission to a specific resource and defining levels of access.
    - It involves verifying access in line with specific policies and rules.
    - It transmits information through an **Access token**.

|Authentication|Authorization|
|---|---|
|Verifies the identity of the user|Determines what the user can and can’t access|
|Challenges the user to validate their credentials|Verifies user access through a series of policies and rules|
|Done before authorization|Done after the user has been successfully verified|
|Usually transmits through an ID token|Usually transmits through an access token|
|Usually governed by Open ID Connect protocol|Generally governed by OAuth framework|

![[Authenticate.png]]
## What is identity access management (IAM)?
It’s a set of processes and policies that control employee access within a company. From email accounts to databases, IAM allows each employee to receive a single digital identity that can be used across every platform they need to access.