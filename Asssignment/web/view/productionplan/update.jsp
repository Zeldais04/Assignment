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
    <title>Chỉnh Sửa Kế Hoạch Sản Xuất</title>
    <style>
        /* CSS cơ bản cho form */
        .form-container {
            width: 50%;
            margin: 0 auto;
            background-color: #f4f6f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        input[type="text"], input[type="date"], input[type="number"], select {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Chỉnh Sửa Kế Hoạch Sản Xuất</h2>
    <div class="form-container">
        <form action="${pageContext.request.contextPath}/productionplan/update" method="post">
            <input type="hidden" name="planId" value="${plan.id}"/>
            
            <label for="startTime">Thời gian bắt đầu:</label>
            <input type="date" id="startTime" name="startTime" value="${plan.startTime}" required/>

            <label for="endTime">Thời gian kết thúc:</label>
            <input type="date" id="endTime" name="endTime" value="${plan.endTime}" required/>

            <label for="workshopId">Phân xưởng:</label>
            <input type="text" id="workshopId" name="workshopId" value="${plan.workshop.id}" required/>

            <c:forEach var="campaign" items="${plan.campains}">
                <fieldset>
                    <legend>Chi Tiết Chiến Dịch Sản Xuất (ID: <c:out value="${campaign.id}"/>)</legend>
                    
                    <label for="product_${campaign.id}">Sản phẩm ID:</label>
                    <input type="text" id="product_${campaign.id}" name="product_${campaign.id}" value="${campaign.p.id}" required/>

                    <label for="quantity_${campaign.id}">Số lượng sản phẩm:</label>
                    <input type="number" id="quantity_${campaign.id}" name="quantity_${campaign.id}" value="${campaign.quantity}" required/>

                    <label for="effort_${campaign.id}">Effort:</label>
                    <input type="number" step="0.1" id="effort_${campaign.id}" name="effort_${campaign.id}" value="${campaign.effort}" required/>
                </fieldset>
            </c:forEach>

            <input type="submit" value="Cập Nhật Kế Hoạch"/>
        </form>
    </div>
</body>
</html>
