<%-- 
    Document   : list
    Created on : Nov 2, 2024, 9:34:56 AM
    Author     : Zeldais
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://example.com/customTags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Schedule List</title>
        <link rel="stylesheet" href="styles/theme.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f6f9;
                margin: 0;
                padding: 0;
            }
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
                background-color: #009879;
                color: white;
            }
            .container {
                max-width: 1200px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>
        <ct:navigationMenu/>
        <div class="container">
            <h1>Schedule List</h1>
            <table>
                <thead>
                    <tr>        
                        <th>Date</th>
                        <th>SCID</th>
                        <th>Campaign ID</th>
                        <th>Shift</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${schedules}" var="s">
                        <tr>
                            <td><ct:formatDate value="${s.date}"/></td>
                            <td>${s.id}</td>
                            <td>${s.cam.id}</td>
                            <td>${s.shift}</td>
                            <td>${s.quantity}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
