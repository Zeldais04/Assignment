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
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f6f9;
        }
        h1 {
            text-align: center;
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .table-container {
            width: 80%;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .info {
            color: #666;
            font-size: 14px;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>Danh sách Kế hoạch Sản xuất</h1>
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>ID Kế hoạch</th>
                    <th>Ngày Bắt Đầu</th>
                    <th>Ngày Kết Thúc</th>
                    <th>Thông Tin Thống Kê</th> <!-- Sẽ bổ sung sau -->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="plan" items="${plans}">
                    <tr>
                        <td><c:out value="${plan.id}"/></td>
                        <td><c:out value="${plan.startTime}"/></td>
                        <td><c:out value="${plan.endTime}"/></td>
                        <td>Thống kê</td> <!-- Thông tin chi tiết sau -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
