<%-- 
    Document   : bookingTickets
    Created on : Oct 9, 2016, 12:04:33 PM
    Author     : catherinexiong
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.SessionEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="sections" class="java.util.List<entity.SectionEntity>" scope="request"/>

<link href="css/calendardate.css" rel='stylesheet' type='text/css' />

<jsp:include page="header.jsp" />

<div class="container">
    <div class="panel panel-default col-lg-12">
        <div class="panel-heading" style="height:40px;margin-left: -15px; margin-right: -15px;background: repeating-linear-gradient(
             -45deg,
             transparent,
             transparent,
             #EEE 2px,
             transparent 3px
             );"></div> 
        <div class="col-lg-10">
            <div class="panel panel-default" id="date-time-panel" style="margin-top: 20px;margin-left: -15px;">
                <div class="panel-heading" style=" margin-top:10px;color:white;text-align: center;font-size:20px;background-color:#FE980F;//#EBEDE8;">
                    Step 1: Choose Date & Time
                </div>
                <div class="panel-body">
                    <p style="margin-top: 10px;margin-bottom:10px;text-align: center">Which date and time would you like? </p>
                    <%
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MMM/dd/EEE");
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

                        List<SessionEntity> sessions = (List<SessionEntity>) request.getAttribute("sessions");
                        HashMap<String, List<SessionEntity>> dateMap = new HashMap();
                        for (int i = 0; i < sessions.size(); i++) {
                            String date = dateFormat.format(sessions.get(i).getTimeStart());
                            if (dateMap.get(date) == null) {
                                List<SessionEntity> list = new ArrayList();
                                list.add(sessions.get(i));
                                dateMap.put(date, list);
                            } else {
                                dateMap.get(date).add(sessions.get(i));
                            }
                        }

                        for (Map.Entry<String, List<SessionEntity>> entry : dateMap.entrySet()) { %>
                    <% String dateStr = entry.getKey();%>
                    <time class="date-as-calendar position-pixels">
                        <span class="day"><%=dateStr.substring(9, 11)%></span>
                        <span class="weekday"><%=dateStr.substring(12)%></span>
                        <span class="month"><%=dateStr.substring(5, 8)%></span>
                        <span class="year"><%=dateStr.substring(0, 4)%></span>
                    </time>

                    <div class="arrow_box" style="margin-top:-55px;margin-left: 80px;height:50px;">
                        <% for (SessionEntity s : entry.getValue()) {%>
                        <label class="radio-inline" style="margin-top:15px;margin-left: 25px;width:200px">
                            <input type="radio" name="sessionList" value="<%=s.getId()%>" /><span style="color:white"> <%= timeFormat.format(s.getTimeStart())%> </span>
                        </label>
                        <% } %>
                    </div>

                    <br>
                    <br>
                    <%}
                    %>
                </div>
            </div>
        </div>
        <div class="col-lg-2" style="margin-top: 10px">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Test
                </div>
            </div>
            <div class="panel-body">
            </div>
        </div>
        <div class="col-lg-10">
            <div class="panel panel-default" id="seat-panel" style="display: none; margin-left:-15px;">
                <div class="panel-heading" style="margin-top:10px;color:white;text-align: center;font-size:20px;background-color:#FE980F;">
                    Step 2: Choose Section
                </div>
                <div class="panel-body" id="seat-panel-body">
                    <img src="images/property/Concerthall_layout.png" alt="concert_layout" usemap="#image-map" >
                    <map name="image-map">
                        <c:forEach items="${sections}" var="section">
                            <area id="${section.numberInProperty}" class= "p1" alt="section${section.numberInProperty}" title="section${section.numberInProperty}" href="#" coords="${section.coords}" shape="poly">
                        </c:forEach>
                    </map>
                </div>
            </div>
        </div>

    </div>
</div>


<script>
    var closed = [];
    var reserved = [];
    var scroll_offset;




    $("input[name='sessionList']").click(function () {

        var selectedSection = $("input[name='sessionList']:checked").val();

//              $.ajax({
//                 url: "?type=closed&id=" + selectedSection;
//                 
//              });
//              
//              $.ajax({
//                 url: "?type=reserved&id=" + selectedSection;
//                 
//              });
        
        $("#seat-panel").show();
        scroll_offset = $("#seat-panel").offset();
        console.log("offset", scroll_offset);
        $("html,body").animate({
            scrollTop: scroll_offset.top
        }, 800);


    });


    //$(".p1").tooltip();
    $(".p1").hover(function () {
        var hoveredId = this.id;
        for (var i = 0; i < closed.length; i++) {
            if (hoveredId == closed[i])
                ;
        }

        for (var i = 0; i < reserved.length; i++) {
            if (hoveredId == reserved[i])
                ;
        }
    });

    $(".p1").click(function () {
        var clickId = this.id;
        for (var i = 0; i < closed.length; i++) {
            if (clickId == closed[i])
                return;
        }

        for (var i = 0; i < reserved.length; i++) {
            if (clickId == reserved[i])
                return;
        }


    });

</script>
<jsp:include page="footer.jsp" />
