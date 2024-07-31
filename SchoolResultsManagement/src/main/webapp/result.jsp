<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Results</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Student Results</h1>
        <p>Student Name: <%= request.getAttribute("studentName") %></p>
        <p>Student ID: <%= request.getAttribute("studentId") %></p>
        <table>
            <tr>
                <th>Subject</th>
                <th>Marks</th>
            </tr>
            <tr>
                <td>Tamil</td>
                <td><%= request.getAttribute("Tamil") %></td>
            </tr>
            <tr>
                <td>English</td>
                <td><%= request.getAttribute("English") %></td>
            </tr>
            <tr>
                <td>Mathematics</td>
                <td><%= request.getAttribute("Mathematics") %></td>
            </tr>
            <tr>
                <td>Science</td>
                <td><%= request.getAttribute("Science") %></td>
            </tr>
            <tr>
                <td>Social</td>
                <td><%= request.getAttribute("Social") %></td>
            </tr>
        </table>
    </div>
</body>
</html>