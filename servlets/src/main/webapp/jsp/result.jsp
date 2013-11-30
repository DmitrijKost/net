<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>hello</title>
    </head>
    <body>
        <c:forEach var="i" items="${matrix}">
             <div style="margin-bottom: -10px">
                  <c:forEach var = "j" items="${i}">
                            <img src="${j.id}.jpg" width="10" height="10" title="${j.power}" style="margin-right: -4px">
                  </c:forEach>
             </div>
        </c:forEach>
    </body>
</html>