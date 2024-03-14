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

curl -v http://localhost:8080/auth-needed

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