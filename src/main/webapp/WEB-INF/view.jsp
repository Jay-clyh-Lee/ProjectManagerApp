<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="mx-auto">
    <!-- POST encodes data whereas GET shows data on Https/SSL -->
    <main id="content">
        <div>
            <h3>View Project</h3>
        </div>
        <div>
            <p>Project: ${project.title}</p>
            <p>Description: ${project.description}</p>
            <p>Due Date: ${project.dueDate}</p>
            <a class="btn btn-link" href="/project/task">View tasks</a>
        </div>
    </main>
</body>
</html>