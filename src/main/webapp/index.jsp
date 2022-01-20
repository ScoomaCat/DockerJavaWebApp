<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sample jsp page for LAB 6</title>
</head>
<body>
<h1>Druinskaia Polina proudly presents: </h1>
<p>To invoke the java servlet click <a href="UrlServlet">here</a></p>
<p>URL1 is ${pageContext.getServletContext().getInitParameter("URL1")}</p>
<p>URL2 is ${pageContext.getServletContext().getInitParameter("URL2")}</p>
</body>
</html>