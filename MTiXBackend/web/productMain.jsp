<%-- 
    Document   : productMain
    Created on : Sep 19, 2016, 3:29:11 PM
    Author     : Student-ID
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<div class="side-body">


    <div class="container"> 
        <div class="page-title">
            <span class="title">Product Management System</span>
            
              
        </div>

        <div class="row">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/session.jpg" alt="concert_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Sessions</h3>
                        <p><c:url var="linkHref" value="/BackController?action=sessionMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/seatallocation.jpg" alt="theater_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Seat Configuration</h3>
                        <p><c:url var="linkHref" value="/BackController?action=displaySeatsMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/promotion.jpg" alt="outdoor_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3>Promotions</h3>
                        <p><c:url var="linkHref" value="/BackController?action=promotionMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
        </div>
    </div> 

    <jsp:include page="footer.jsp" />
