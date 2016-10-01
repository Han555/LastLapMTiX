<%-- 
    Document   : editAlert
    Created on : Sep 23, 2016, 10:02:49 PM
    Author     : Student-ID
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <c:url var="formAction" value="/BackController?action=editingAlert" />
    <div class="side-body padding-top">


        <div class="row">
            <c:if test="${alertEdited == 'true'}">
                <font color="red">Alert successfully Edited!</font><br/>
            </c:if>
        </div>
        
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Events</h4>

                    </div>
                    <div class="content">
                        <table class="table table-inbox table-hover">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<ArrayList<String>> inboxPage = (ArrayList<ArrayList<String>>) request.getAttribute("inbox");
                                    for (int i = 0; i < size; i++) {
                                        String id = inboxPage.get(i).get(0);
                                        String event = inboxPage.get(i).get(1);
                                        String description = inboxPage.get(i).get(2);
                                %>

                                <c:url var="formAction" value="/BackController?action=editingAlert" />

                                <tr class="unread">




                                    <td class="view-message  dont-show"><%= event%></td>
                                    <td class="view-message "><%= description%></td>

                                <td class="view-message">
                                    <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                        <input type="hidden" name="eventId" value=<%= id%> readonly="readonly" />
                                        <c:url var="formAction" value="/BackController" />
                                        <input type="submit" value="Edit Alert" /> 
                                    </form></td>


                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                        </table>
                        <table border="1" cellpadding="5" cellspacing="5">
                            <tr>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <td>${i}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <c:url var="linkHref" value="/BackController?action=editAlert&page=${i}" />

                                                <a href="${linkHref}">${i}</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </tr>
                        </table>
                        <div class="footer">

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div> 
</div>
</div>

<jsp:include page="footer.jsp" />


