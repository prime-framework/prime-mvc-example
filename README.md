# Pre-reqs

Savant installed
JDK installed

# Running

To ensure your dependencies are fetched, before opening IntelliJ

`sb clean idea`

Then open the project in IntelliJ

Navigate to the `PrimeTest` class and run it as a Java application.

The web server should now be running on port 8080.

Actions you can hit:

http://localhost:8080 (goes to IndexAction)

We don't have session support in this example, but if you go here, you'll see a redirect to login since /auth-needed requires authentication.

curl -v http://localhost:8080/page-that-requires-auth

Response:

```
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /auth-needed HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
> 
< HTTP/1.1 302 
< set-cookie: prime-mvc-saved-request=QkJCA3yO_9zMWYUHvVRCN9_hMvUKQnUhxWXJLRrvcsEhTWCniLiFy3Vai_m6uBWHuUH4rGwzclpXG0xPm8BK98_zppl6NScBP2gVnpeMU0UUEtyN; HttpOnly; Path=/; SameSite=Strict
< content-length: 0
< location: /
< connection: keep-alive
< cache-control: no-cache
< 
* Connection #0 to host localhost left intact
```

The redirect to / happens because:

1. We are not authenticated.
1. `PageThatRequiresAuthAction` `@Action` annotation has `requiresAuthentication = true`
1. `BaseAction` has this annotation: `@SaveRequest(uri = "/")`

The latter causes a redirect to occur if an action fails due to an `unauthenticated` response code. It also saves the original URL the user was attempting to access such that if the login is successful, they can be sent back there.
If the cookie is present and the login succeeds, code such as `SavedRequestWorkflow` and `ReexecuteSavedRequestResult` will send the user back to

# What all is in here?

* 2 actions (1 action base class) in `src/main/java/org/primeframework/mvc/sampleapp/action`
* 2 templates for those actions in `src/main/web/templates`.
* `PrimeTest` class to bootstrap HTTP server and supply the correct Guice modules.
* Basic Guice module `OurModule` to wire up the minimum Prime dependencies.
* Configuration classes
  * `OurPrimeConfig` is the main one - controls paths (which affect template resolution), cookie settings, CSRF settings
  * `OurCORS` is the CORS policy config class