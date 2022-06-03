<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="mx-auto">
    <!-- POST encodes data whereas GET shows data on Https/SSL -->
    <main id="content"> 
        <div>
            <h3>All Projects</h3>
            <table>
                <thead>
                    <tr>
                        <th>Project</th>
                        <th>Team Lead</th>
                        <th>Due Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="project" items="${projects}">
                        <tr>
                            <c:if test="${project.lead.id != logged_in_user.id}">
                                <td>
                                    <a href="/project/view/${project.id}" class="btn btn-link">${project.title}</a>
                                </td>
                                <td>
                                    <c:out value="${project.lead.fname}"></c:out>
                                </td>
                                <td>
                                    <fmt:formatDate value="${project.dueDate" pattern="MMMM dd"/>
                                </td>
                                <td>
                                    <a href="/project/join/${project.id}">Join team</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
            <h3>My Projects</h3>
            <table>
                <thead>
                    <tr>
                        <th>Project</th>
                        <th>Team Lead</th>
                        <th>Due Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="project" items="${projects}">
                        <tr>
                            <td>
                                <a href="/project/view/${project.id}" class="btn btn-link">${project.title}</a>
                            </td>
                            <td>
                                <c:out value="${project.lead.fname}"></c:out>
                            </td>
                            <td>
                                <fmt:formatDate value="${project.dueDate" pattern="MMMM dd"/>
                            </td>
                            <c:when test="${project.lead.id != logged_in_user.id}"></c:if>
                                <td>
                                    <a href="/project/leave/${project.id}">Leave team</a>
                                </td>
                            </c:when>
                            <c:otherwies>
                                <td>
                                    <a href="/project/edit/${project.id}">Edit</a>
                                </td>
                            </c:otherwies>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>