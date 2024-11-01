<%-- 
    Document   : list
    Created on : Oct 31, 2024, 9:10:32 PM
    Author     : Zeldais
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Chi Tiết Kế Hoạch Sản Xuất - Chiến Dịch Sản Xuất</title>
        <style>
            /* Giữ lại phong cách bảng từ trước để tạo cảm giác thống nhất */
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
            .back-button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                text-align: center;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .back-button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                text-align: center;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .back-button:hover {
                background-color: #45a049;
            }
            .button-container {
                text-align: right;
                margin-bottom: 20px;
            }
            .details {
                justify-content: center;
                text-align: center;
                margin-bottom: 20px;
            }
            .back-button, .edit-button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                text-align: center;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .back-button:hover, .edit-button:hover {
                background-color: #45a049;
            }
            .button-container {
                text-align: right;
                margin-bottom: 20px;
            }
            .details {
                text-align: center;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Danh sách Chiến Dịch Sản Xuất của Kế Hoạch ${planId}</h1>

        <!-- Nút trở về trang trước, căn sang bên phải -->
        <div class="button-container">
            <a href="${pageContext.request.contextPath}/productionplan/list" class="back-button">Trở về</a>
            <a href="${pageContext.request.contextPath}/productionplan/update?planId=${planId}" class="edit-button">Chỉnh sửa</a>
            <a href="${pageContext.request.contextPath}/productionplan/delete?planId=${planId}" class="delete-button" onclick="return confirm('Bạn có chắc muốn xóa kế hoạch này không?');">Xóa</a>


        </div>

        <!-- Hiển thị thông tin chi tiết về thời gian của kế hoạch sản xuất -->
        <div class="details">
            <p><strong>Thời gian bắt đầu:</strong> <c:out value="${plan.startTime}"/>
                <strong>Thời gian kết thúc:</strong> <c:out value="${plan.endTime}"/></p>

        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID Chiến Dịch</th>
                        <th>Sản Phẩm</th>
                        <th>Số Lượng</th>
                        <th>Đơn vị Effort</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="campaign" items="${campaigns}">
                        <tr>
                            <td><c:out value="${campaign.id}"/></td>
                            <td>
                                <c:out value="${campaign.p.name}"/> 
                            </td>
                            <td><c:out value="${campaign.quantity}"/></td>
                            <td><c:out value="${campaign.effort}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>

