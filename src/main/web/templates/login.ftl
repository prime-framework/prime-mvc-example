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
    [@control.submit name="submit"/]
[/@control.form]
</body>
</html>
