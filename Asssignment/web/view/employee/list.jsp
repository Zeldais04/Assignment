<%-- 
    Document   : list
    Created on : Nov 1, 2024, 9:45:07 AM
    Author     : Zeldais
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="http://example.com/customTags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee List</title>
        <link rel="stylesheet" href="styles/theme.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                color: #333;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 1200px;
                margin: 40px auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            h1 {
                text-align: center;
                color: #444;
                margin-bottom: 20px;
            }
            .styled-table {
                width: 100%;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 18px;
                text-align: left;
            }
            .styled-table th, .styled-table td {
                padding: 12px;
                border: 1px solid #ddd;
            }
            .styled-table thead th {
                background-color: #009879;
                color: #ffffff;
                text-transform: uppercase;
            }
            .styled-table tbody tr:nth-child(even) {
                background-color: #f3f3f3;
            }
            .styled-table tbody tr:hover {
                background-color: #f1f1f1;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <ct:navigationMenu/>
            <form action="list" method="get">
                <h1>Employee List</h1>
                <table class="styled-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Salary Level</th>
                            <th>Department</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${employees}" var="e">
                            <tr>
                                <td>${e.id}</td>
                                <td>${e.name}</td>
                                <td>${e.salaryLevel}</td>
                                <td>${e.d.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>