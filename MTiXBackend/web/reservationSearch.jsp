<%-- 
    Document   : ReservationSearch
    Created on : Sep 17, 2016, 3:39:09 PM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="container-fluid">
    <div class="side-body">
        <div class="page-title">
            <span class="title">Search Avaliable Venues</span>

        </div>



        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Indicate Search Criteria</div>
                            <div class="description">Please take note Date Range is the whole period of time for the event (including setup and cleanup etc.)</div>
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction" value="/BackController?action=reservationSearchResult" />
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="eventscale" class="col-sm-2 control-label">Scale of Event</label>
                                <div class="col-sm-6">
                                    <select class="js-example-basic-single js-states" style="width: 100%" name="eventscale" id="eventscale">
                                        <option value="SS">Small Scale</option>
                                        <option value="SS">Medium Scale</option>
                                        <option value="LS">Large Scale</option>

                                    </select>
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;">

                                <label for="visualevent" class="col-sm-2 control-label">Visual Event</label>
                                <div class="col-sm-4">
                                    <div class="radio3 radio-check radio-inline">
                                        <input type="radio" id="radio1" name="radioV" value="yes" checked="" required
                                               data-fv-notempty-message="Visual Event is required">
                                        <label for="radio1">
                                            Yes
                                        </label>
                                    </div>
                                    <div class="radio3 radio-check radio-success radio-inline">
                                        <input type="radio" id="radio2" name="radioV" value="no">
                                        <label for="radio2">
                                            No
                                        </label>
                                    </div>

                                </div>
                            </div>

                            <div class="form-group" style="padding-bottom: 20px;">

                                <label for="eventdaterange" class="col-sm-2 control-label">Event Date Range</label>
                                <div class="col-sm-6">
                                    <input type="text" name="daterange" id="daterange" class="form-control" value="" required
                                           data-fv-notempty-message="Event Date Range is required">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i><span style="color:#FF0000">${errormsg}</span>

                                </div> 
                            </div>

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
<script>
    var propertyId = 1;
    var blockedDates = null;
    var publicHolidays = null;
  
    
    function notifyPeak(){
        
    }
    function deNotifyPeak(){
        
    }

    $(document).ready(function () {

        

       

        $('#daterange').daterangepicker(function (start, end, label) {
            console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
        });

        
    });




</script>      

<jsp:include page="footer.jsp" />

