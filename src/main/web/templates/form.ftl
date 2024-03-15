[#ftl/]
<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta charset="utf-8">
    <title>Form page</title>
</head>
<body>
[#if result?has_content ]
    <h1>${result}</h1>
[/#if]
Please fill out the following form:
[@control.form action="form" method="POST"]
    [@control.text name="yourName" label="Greeting"/]
    [@control.submit name="submit"/]
[/@control.form]
</body>
</html>