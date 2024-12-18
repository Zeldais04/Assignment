<%--  
    Document   : Create
    Created on : Oct 30, 2024, 8:55:56 AM
    Author     : Zeldais
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri="http://example.com/customTags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Styled JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="styles/theme.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f6f9;
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
                border: 1px solid #ddd;
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
                background-color: #009879;
                color: white;
                font-weight: bold;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #ddd;
            }
            .button-container {
                margin-top: 20px;
                display: flex;
                justify-content: center;
            }
            .save-button {
                background-color: #007BFF;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                text-decoration: none;
                font-size: 16px;
            }
            .save-button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>

        <div class="form-container">
            <ct:navigationMenu/>
            <h1>Workshop Schedule Form</h1>
            <h5>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">${errorMessage}</div>
                </c:if>
            </h5>
            <form action="create" method="POST" onsubmit="return validateForm()"> 
                <label for="from">From:</label>
                <input type="date" name="from" id="from" /> 

                <label for="to">To:</label>
                <input type="date" name="to" id="to" />

                <label for="workshop">Workshop:</label>
                <select name="did" id="workshop">
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
                                <td>${p.name}<input type="hidden" name="pid" value="${p.id}"/></td>
                                <td><input type="text" name="quantity${p.id}"/></td>
                                <td><input type="text" name="effort${p.id}"/></td>
                            </tr>   
                        </c:forEach>
                    </tbody>
                </table>

                <div class="button-container">
                    <input type="submit" class="save-button" name="Save" value="Save" />
                </div>
            </form>
        </div>

    </body>

</html>
