<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="mx-auto">
    <!-- POST encodes data whereas GET shows data on Https/SSL -->
    
    <main id="content">
        <div>
            <h3>Create a Project</h3>
        </div>
        <div id="add_project">
            <form:form action="/project/add" method="POST" modelAttribute="project"> <!-- this project here matches the last "project" in modelAttribute("project") project project in the controller-->
                <form:label path="title" for="title">Title <!-- path matches the attribute in model.java -->
                    <form:errors path="title"/>
                    <form:input type="text" path="title"/>
                </form:label>
                <form:label path="description" for="description">Description <!-- path matches the attribute in model.java -->
                    <form:errors path="description"/>
                    <form:input type="text" path="description"/>
                </form:label>
                <form:label path="dueDate" for="dueDate">Due Date <!-- path matches the attribute in model.java -->
                    <form:errors path="dueDate"/>
                    <form:input type="date" path="dueDate"/>
                </form:label>
                <form:input type="submit" class="btn btn-light"/>
                <a class="btn btn-light" href="/dashboard">Cancel</a>
            </form:form>
        </div>
    </main>   
</body>
</html>