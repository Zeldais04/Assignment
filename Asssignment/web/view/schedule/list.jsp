<%-- 
    Document   : list
    Created on : Nov 2, 2024, 9:34:56 AM
    Author     : Zeldais
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Schedule List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Schedule List</h1>
        <table>
            <thead>
                <tr>
                    <th>SCID</th>
                    <th>Campaign ID</th>
                    <th>Date</th>
                    <th>Shift</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${schedules}" var="s">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.cam.id}</td>
                        <td>${s.date}</td>
                        <td>${s.shift}</td>
                        <td>${s.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
