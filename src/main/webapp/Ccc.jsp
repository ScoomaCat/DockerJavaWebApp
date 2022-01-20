<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sample jsp page for LAB 6</title>
</head>
<body>
<p>Value1 is ${pageContext.getServletContext().getAttribute("CBean").getValue1()}</p>
<p>Value2 is ${pageContext.getServletContext().getAttribute("CBean").getValue2()}</p>
<p>Value3 is ${pageContext.getServletContext().getAttribute("CBean").getValue3()}</p>
</body>
</html>