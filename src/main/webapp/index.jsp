<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Get Links</title>
</head>
<body>
<h1>Get Links
</h1>
<form action="action" method="get">
    Link: <input id="input-id" type="text" name="user_link" placeholder="please add link">
    <input type="submit" value="Submit" />
    <button onclick="clearTable()">Clear</button>
</form>

<c:if test="${not empty links}">
    <table width="70%" border="1">
    <c:forEach var="link" items="${links}">
        <tr>
            <td><c:out value="${link.num}"/></td>
            <td><a href="#"><c:out value="${link.link}"/></a></td>
            <td><c:out value="${link.text}"/> </td>
        </tr>
    </c:forEach>
    </table>
</c:if>
<c:if test="${not empty error}">
        <p><c:out value="${error}"/> </p>
</c:if>
<script>
    let link = document.querySelectorAll('a');
    let input = document.querySelector('#input-id');
    link.forEach(el => el.addEventListener('click', () => { input.value = el.textContent;}));
    function clearTable(){
      let table = document.querySelector("table");
      table.remove();
    }
</script>

</body>
</html>