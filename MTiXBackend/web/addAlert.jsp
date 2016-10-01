<%-- 
    Document   : addAlert
    Created on : Sep 23, 2016, 4:39:58 PM
    Author     : Student-ID
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="container-fluid">
    <div class="side-body">




        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Create Alert</div>
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction" value="/BackController?action=addingAlert" />
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">

                            <div class="form-group" style="padding-bottom: 20px;">
                                <% String eventId = (String) request.getAttribute("eventId");%>
                                <label for="eventdaterange" class="col-sm-2 control-label">Sales Percentage</label>
                                <div class="col-sm-6">
                                    <input type="text" name="sales" id="sales"  value="" required
                                           data-fv-notempty-message="sales percentage is required">
                                </div> 
                            </div>

                            <div class="form-group" style="padding-bottom: 20px;">

                                <label for="eventdaterange" class="col-sm-2 control-label">Alert Type</label>
                                <div class="col-sm-6">
                                    <input type="text" name="alertType" id="alertType"  value="" required
                                           data-fv-notempty-message="Alert type is required">
                                </div> 
                            </div>

                            <div class="form-group" style="padding-bottom: 20px;">

                                <label for="eventdaterange" class="col-sm-2 control-label">Person to alert</label>
                                <div class="col-sm-6">
                                    <input type="email" name="person" id="person"  value="" required
                                           data-fv-notempty-message="Email is required">
                                </div> 
                            </div>

                            <div class="form-group" style="padding-bottom: 20px;" id="date">

                                <label for="eventdaterange" class="col-sm-2 control-label">Date to alert</label>
                                <div class="col-sm-6">
                                    <input type="date" name="date" id="date" class="form-control" value="" required
                                           data-fv-notempty-message="Alert date is required">
                                </div> 
                            </div>
                                <input type="hidden" name="eventId" value=<%= eventId%> readonly="readonly" />
                            <div class="form-group" style="padding-bottom: 20px;">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default" value="formSubmit">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    

<jsp:include page="footer.jsp" />

