<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="mx-auto">
    <!-- POST encodes data whereas GET shows data on Https/SSL -->
    
    <main id="content">
        <div>
            <h3>Project: ${project.title}</h3>
            <p>Project Lead: ${project.lead}</p>
        </div>
        <div id="add_task">
            <form:form action="/project/add" method="POST" modelAttribute="project"> <!-- this project here matches the last "project" in modelAttribute("project") project project in the controller-->
                <form:label path="title" for="title">Title <!-- path matches the attribute in model.java -->
                    <form:errors path="title"/>
                    <form:input type="text" path="title"/>
                </form:label>
                <form:input type="submit" value="Submit" class="btn btn-light"/>
            </form:form>
        </div>
    </main>   
</body>
</html>