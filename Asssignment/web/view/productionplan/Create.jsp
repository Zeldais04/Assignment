<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Workshop Entry</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-k6RqeWeci5ZR/Lv4MR0z4MjrFdqY6KZBbNvZ92mJ8D0DjLg5YJ5uCvf0Usl5z+e" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f9f9f9;
                color: #333;
                line-height: 1.6;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 800px;
                margin: 40px auto;
                background: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            form {
                display: flex;
                flex-direction: column;
                gap: 15px;
            }
            label {
                font-weight: 500;
            }
            input, select {
                padding: 10px;
                margin: 5px 0;
                width: 100%;
                box-sizing: border-box;
                border-radius: 4px;
                border: 1px solid #ccc;
                transition: border-color 0.3s ease;
            }
            input:focus, select:focus {
                border-color: #007BFF;
                outline: none;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
                color: #333;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            input[type="submit"] {
                background-color: #007BFF;
                color: white;
                border: none;
                cursor: pointer;
                padding: 12px;
                font-size: 16px;
                border-radius: 4px;
                transition: background-color 0.3s ease;
            }
            input[type="submit"]:hover {
                background-color: #0056b3;
            }
            .fa-info-circle {
                color: #007BFF;
                margin-left: 5px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="container">
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
        </div>
    </body>
</html>