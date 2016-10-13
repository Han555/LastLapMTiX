<%-- 
    Document   : home
    Created on : Sep 10, 2016, 11:46:10 PM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <div class="side-body padding-top">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <c:url var="linkHref" value="/BackController?action=bulletinBoard" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-inbox fa-4x"></i>
                            <div class="content">
                                <div class="title">Bulletin Board</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <c:url var="linkHref" value="/BackController?action=message" />
                <a href="${linkHref}">
                    <div class="card yellow summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-comments fa-4x"></i>
                            <div class="content">
                                <div class="title">Inbox Messages</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
            <c:if test="${role == 'super administrator'}">
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                    <c:url var="linkHref" value="/BackController?action=createAccount" />
                    <a href="${linkHref}">
                        <div class="card blue summary-inline">
                            <div class="card-body">
                                <i class="icon fa fa-share-alt fa-4x"></i>
                                <div class="content">
                                    <div class="title">Create Account</div>
                                </div>
                                <div class="clear-both"></div>
                            </div>
                        </div>
                    </a>
                </div>
            </c:if>

        </div>
        <div class="row">
            <c:if test="${(role == 'super administrator' || role == 'property manager')}">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src="assets/img/faces/propertymanagement.jpg" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Property Management System</h3>
                            <p><c:url var="linkHref" value="/BackController?action=propertyMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${(role == 'super administrator' || role == 'product manager')}">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src="assets/img/faces/productmanagement.jpg" alt="theater_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Product Management System</h3>
                            <p><c:url var="linkHref" value="/BackController?action=productMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                        </div>
                    </div>
                </div>
            </c:if>   

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="assets/img/faces/finances.jpg" alt="theater_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Finances</h3>
                        <p><c:url var="linkHref" value="/BackController?action=addingSub" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
        </div>
    </div> 
</div>
</div>




