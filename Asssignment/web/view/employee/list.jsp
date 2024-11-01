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
                max-width: 800px;
                margin: 0 auto;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <ct:navigationMenu/>
            <form action="list" method="get">
                <h1>Employee List</h1>
                <table>
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
                                <td>${e.id}</td> <!-- Sửa từ e.eid thành e.id -->
                                <td>${e.name}</td> <!-- Sửa từ e.ename thành e.name -->
                                <td>${e.salaryLevel}</td> <!-- Không cần thay đổi -->
                                <td>${e.d.name}</td> <!-- Truy cập tên của phòng ban qua đối tượng Department -->
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
