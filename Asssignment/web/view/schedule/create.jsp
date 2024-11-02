<%-- 
    Document   : create
    Created on : Nov 1, 2024, 10:36:57 PM
    Author     : Zeldais
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create Schedule</title>
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
                max-width: 1200px;
                margin: 0 auto;
            }
            .error-message {
                color: red;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
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

                <input type="submit" value="Save Schedule" />
            </form>

        </div>
    </body>
</html>
