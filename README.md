# Pre-reqs

Savant installed
JDK installed

# Assumptions

1. You can get familiar with Freemarker Template basics elsewhere
1. You can figure out how to run this outside of an IDE elsewhere, since it's just executing a Java class with a main method.

# Running

To ensure your dependencies are fetched, before opening IntelliJ

`sb clean idea`

Then open the project in IntelliJ

Navigate to the `ExamplePrimeMain` class and run it as a Java application.

The web server should now be running on port 8080.

# Sample URLs to access

## /

```shell
# (goes to `IndexAction`)
curl -v http://localhost:8080
```

## /page-that-requires-auth

If you go here, you'll see a redirect to `/login` (keep reading for why).

```shell
curl -v http://localhost:8080/page-that-requires-auth
```

Sample output:

```
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /page-that-requires-auth HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
> 
< HTTP/1.1 302 
< set-cookie: prime-mvc-saved-request=QkJCAwUi8e1Fw4rBpC3SRqnJ0ah6qu9DFUBigwt0vxv2dh8_77s9lK3u9cweEQZ5x6bsSQyPVxFK5M9Ult6PvgSlL3inue5pPvsbXHDtRatALu5VMFzFA1q42HxdItqkDfy3MA==; HttpOnly; Path=/; SameSite=Strict
< content-length: 0
< location: /login
< connection: keep-alive
< cache-control: no-cache
< 
* Connection #0 to host localhost left intact
```

The redirect to `/login` happens because:

1. We are not authenticated.
2. The `PageThatRequiresAuthAction` action class has an `@Action` annotation has `requiresAuthentication = true`
3. `BaseAction` has this annotation: `@SaveRequest(uri = "/login")`

The latter causes a redirect to occur if an action fails due to an `unauthenticated` response code. It also saves the original URL the user was attempting to access such that if the login is successful, they can be sent back there.

If the cookie is present and the login succeeds, code such as `SavedRequestWorkflow` and `ReexecuteSavedRequestResult` will send the user back to the page they attempted to access.

You can try this by entering any username in the login form and submitting. It will establish a session and redirect you back to where you started.

In this example, we are using the `BaseUserIdCookieSecurityContext` to keep track of who is logged in (a session). That
base class serializes the User ID, login time, and a session ID into an encrypted cookie.
There are other options as well (JWT, etc.).

## /foobar

Prime uses our `MissingAction` to handle this one. This is because the `missingPath` on the `MVCConfiguration` interface is set to `/missing`.

## /form

Shows using a form control. The form:

1. Has validation to ensure you supply the required name field.
2. The controls handle populating the form fields with data based on the `FormAction` class fields.
3. The controls also populate the `FormAction` class fields with data when the Freemarker templates are rendered (could be on any of action methods).
4. If you include the word `crash` in your name, you will see how an exception is handled (`BaseAction` has the code `error` mapped).
5. If you visit `/login` first and login/establish a session, then PMVC's Freemarker form control (see `[@control.form` in `form.ftl`) will include a CSRF token like this that is validated on POST requests only:

```html
<input type="hidden" name="primeCSRFToken" value="QkJCAYS8c-DFoBAd02ReblqfhEKQXD3DeZ77s_z046IHzuNu71_5t2wqZ9zeYyKfOQrqhoF-u9pm0GfRWUW4e3bgIpk4j3YzKxMz4j699_su984E3tCgZGT4uM_XoI3G5qtTag=="/>
```

# What else is in here?

* 2 templates for those actions in `src/main/web/templates`.
* `ExamplePrimeMain` class to bootstrap HTTP server and supply the correct Guice modules.
* Basic Guice module `ExampleModule` to wire up the minimum Prime dependencies.
* Configuration classes
  * `ExamplePrimeConfig` is the main one - controls paths (which affect template resolution), cookie settings, CSRF settings
  * `ExampleCORSProvider` is the CORS policy config class
