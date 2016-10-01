<%-- 
    Document   : saveNewEvent
    Created on : Sep 22, 2016
    Author     : catherinexiong
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="event" class="entity.Event" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="side-body">
    <div class="page-title">
        <span class="title"></span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">Reservation Made for the Event Below</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>

                                    <th>Event Name</th>
                                    <th>Event Description</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Property</th>
                                    <th>Event Organizer Username</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>

                                    <td>${event.name}</td>
                                    <td>${event.description}</td>

                                    <td>${start}</td>
                                    <td>${end}</td>
                                    <td>${event.property.propertyName}</td>
                                    <td>${event.user.username}</td>

                                </tr>
                            </tbody>
                        </table>

                    </div>

                    <div class="col-sm-12 text-center" style="padding-bottom:50px;" >
                        <c:url var="addEquipment" value="/BackController?action=addExtraEquipment" />
                        <a class="btn btn-primary" href="${addEquipment}" role="button">Add Additional Equipment</a>
                        <c:url var="addEquipment" value="/BackController?action=addExtraManpower" />
                        <a class="btn btn-primary" href="${addManpower}" role="button">Add Additional Manpower</a>
                        
                    </div>
                </div>
            </div>

        </div>
    </div>


    <jsp:include page="footer.jsp" />


