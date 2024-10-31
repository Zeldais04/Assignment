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
    <title>Chỉnh sửa Kế Hoạch và Chiến Dịch Sản Xuất</title>
    <style>
        /* CSS tương tự các trang khác để tạo sự đồng nhất */
        .form-container {
            width: 80%;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .form-title {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
        }
        .form-field {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="date"], input[type="number"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .submit-button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .submit-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1 class="form-title">Chỉnh sửa Kế Hoạch và Chiến Dịch Sản Xuất</h1>
        <form action="update" method="get">
            <input type="hidden" name="planId" value="${plan.id}"/>
            
            <!-- Chỉnh sửa thông tin kế hoạch -->
            <div class="form-field">
                <label for="startDate">Ngày Bắt Đầu</label>
                <input type="date" id="startDate" name="startDate" value="${plan.startTime}"/>
            </div>
            <div class="form-field">
                <label for="endDate">Ngày Kết Thúc</label>
                <input type="date" id="endDate" name="endDate" value="${plan.endTime}"/>
            </div>
            
            <!-- Chỉnh sửa thông tin các chiến dịch -->
            <c:forEach var="campaign" items="${campaigns}">
                <div class="form-field">
                    <label>Chiến Dịch ID: <c:out value="${campaign.id}"/></label>
                    <input type="hidden" name="campaignId" value="${campaign.id}"/>
                    <label for="quantity_${campaign.id}">Số Lượng</label>
                    <input type="number" id="quantity_${campaign.id}" name="quantity_${campaign.id}" value="${campaign.quantity}"/>
                    <label for="effort_${campaign.id}">Đơn vị Effort</label>
                    <input type="number" id="effort_${campaign.id}" name="effort_${campaign.id}" step="0.01" value="${campaign.effort}"/>
                </div>
            </c:forEach>
            
            <button type="submit" class="submit-button">Lưu Thay Đổi</button>
        </form>
    </div>
</body>
</html>
