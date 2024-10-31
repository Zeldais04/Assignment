<%-- 
    Document   : update
    Created on : Oct 31, 2024, 4:29:24 PM
    Author     : Zeldais
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa thời gian kế hoạch</title>
    <style>
        .form-container {
            width: 50%;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .form-container input[type="date"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
        .form-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Chỉnh sửa Thời Gian Kế Hoạch</h1>
    <div class="form-container">
        <form action="${pageContext.request.contextPath}/productionplan/edit" method="post">
            <input type="hidden" name="planId" value="${plan.id}">
            <label for="startTime">Ngày bắt đầu:</label>
            <input type="date" id="startTime" name="startTime" value="${plan.startTime}">
            <label for="endTime">Ngày kết thúc:</label>
            <input type="date" id="endTime" name="endTime" value="${plan.endTime}">
            <input type="submit" value="Cập nhật">
        </form>
    </div>
</body>
</html>
