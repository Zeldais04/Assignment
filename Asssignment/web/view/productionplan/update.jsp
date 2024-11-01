<%-- 
    Document   : update
    Created on : Oct 31, 2024, 4:29:24 PM
    Author     : Zeldais
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh Sửa Kế Hoạch Sản Xuất</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }
            .form-container {
                background: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 600px;
            }
            h1 {
                font-size: 1.5em;
                text-align: center;
                color: #333;
            }
            h5 {
                text-align: center;
                color: red;
            }
            form {
                display: flex;
                flex-direction: column;
            }
            label {
                font-weight: bold;
                margin-top: 10px;
            }
            input[type="date"], select, input[type="text"] {
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 1em;
            }
            table {
                width: 100%;
                margin-top: 15px;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: center;
            }
            th {
                background-color: #f0f0f0;
            }
            .button-container {
                margin-top: 20px;
                display: flex;
                justify-content: center;
            }
            input[type="submit"] {
                background-color: #28a745;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            input[type="submit"]:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h1>Chỉnh Sửa Kế Hoạch Sản Xuất</h1>
            <h5>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">${errorMessage}</div>
                </c:if>
            </h5>
            <form action="update" method="POST"> 
                <input type="hidden" name="planId" value="${plan.id}"/>
                
                <label for="from">Thời gian bắt đầu:</label>
                <input type="date" name="from" id="from" value="${plan.startTime}" required/> 
                
                <label for="to">Thời gian kết thúc:</label>
                <input type="date" name="to" id="to" value="${plan.endTime}" required/>
                
                <label for="workshop">Phân xưởng:</label>
                <select name="did" id="workshop">
                    <c:forEach items="${requestScope.depts}" var="d">
                        <option value="${d.id}" ${d.id == plan.d.id ? 'selected' : ''}>${d.name}</option>
                    </c:forEach>
                </select>
                
                <table>
                    <thead>
                        <tr>
                            <th>Sản Phẩm</th>
                            <th>Số Lượng</th>
                            <th>Effort Dự Kiến</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${campaigns}" var="campaign">
                        <tr>
                            <td>${campaign.p.name}<input type="hidden" name="pid" value="${campaign.p.id}"/></td>
                            <td><input type="text" name="quantity${campaign.p.id}" value="${campaign.quantity}" required/></td>
                            <td><input type="text" name="effort${campaign.p.id}" value="${campaign.effort}" required/></td>
                        </tr>   
                        </c:forEach>
                    </tbody>
                </table>
                
                <div class="button-container">
                    <input type="submit" name="Save" value="Cập Nhật" />
                </div>
            </form>
        </div>
    </body>
</html>
