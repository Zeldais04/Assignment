<%-- 
    Document   : create
    Created on : Nov 1, 2024, 10:36:57 PM
    Author     : Zeldais
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://example.com/customTags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create Schedule</title>
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
            .error-message {
                color: red;
                margin-bottom: 10px;
                text-align: center;
            }
            input[type="submit"] {
                background-color: #007BFF;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            input[type="submit"]:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <ct:navigationMenu/>
        <div class="container">
            <h1>Create Schedule for Plan: ${plan.id}</h1>
            <form action="<c:url value='/schedule/create' />" method="POST">
                <input type="hidden" name="planId" value="${plan.id}" />
                <input type="hidden" name="from" value="${plan.startTime}" />
                <input type="hidden" name="to" value="${plan.endTime}" />
                <c:forEach items="${requestScope.time}" var="d">
                    <h3>DATE ${d}</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>K1</th>
                                <th>K2</th>
                                <th>K3</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${campaigns}" var="campaign">
                                <tr>
                                    <td>${campaign.p.name}</td>
                                    <td>
                                        <input type="number" name="quantity_${campaign.id}_k1_${d}" min="0" max="${campaign.quantity}" value="0" />
                                    </td>
                                    <td>
                                        <input type="number" name="quantity_${campaign.id}_k2_${d}" min="0" max="${campaign.quantity}" value="0" />
                                    </td>
                                    <td>
                                        <input type="number" name="quantity_${campaign.id}_k3_${d}" min="0" max="${campaign.quantity}" value="0" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:forEach>

                <div class="button-container" style="text-align: center; margin-top: 20px;">
                    <input type="submit" value="Save Schedule" />
                </div>
            </form>

        </div>
    </body>
</html>
