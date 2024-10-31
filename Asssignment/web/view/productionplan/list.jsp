<%-- 
    Document   : list
    Created on : Oct 31, 2024, 4:35:14 PM
    Author     : Zeldais
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Kế hoạch Sản xuất</title>
</head>
<body>
    <h1>Danh sách Kế hoạch Sản xuất</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID Kế hoạch</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Thông tin thống kê</th> <!-- Bạn sẽ cung cấp thêm thông tin này sau -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="plan" items="${plans}">
                <tr>
                    <td><c:out value="${plan.id}"/></td>
                    <td><c:out value="${plan.startTime}"/></td>
                    <td><c:out value="${plan.endTime}"/></td>
                    <td>Thống kê</td> <!-- Thêm thông tin thống kê khi có dữ liệu -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
