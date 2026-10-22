[#ftl/]
<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta charset="utf-8">
    <title>Login page</title>
</head>
<body>
Please Login
[@control.form action="login" method="POST"]
    [@control.text name="username" label="Username"/]
    <input type="submit" id="submit" value="Submit">
[/@control.form]
</body>
</html>
