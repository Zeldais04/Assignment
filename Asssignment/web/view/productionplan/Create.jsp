<%-- 
    Document   : Create
    Created on : Oct 30, 2024, 8:55:56 AM
    Author     : Zeldais
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Workshop Entry</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            form {
                max-width: 600px;
                margin: auto;
            }
            input, select {
                padding: 8px;
                margin: 8px 0;
                width: 100%;
                box-sizing: border-box;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #000;
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
                padding: 10px;
                margin-top: 20px;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <h2>Create Workshop Entry</h2>
        <form action="create" method="POST">
            <label for="from">From:</label>
            <input type="date" id="from" name="from" required/>

            <label for="to">To:</label>
            <input type="date" id="to" name="to" required/>

            <label for="workshop">Workshop:</label>
            <select id="workshop" name="did" required>
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>

            <table>
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Estimated Effort</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.products}" var="p">
                    <tr>
                        <td>
                            ${p.name}
                            <input type="hidden" name="pid" value="${p.id}"/>
                        </td>
                        <td><input type="number" name="quantity${p.id}" min="0" required/></td>
                        <td><input type="number" name="effort${p.id}" min="0" required/></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>

            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
