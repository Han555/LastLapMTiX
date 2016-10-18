<%-- 
    Document   : bookingTickets
    Created on : Oct 9, 2016, 12:04:33 PM
    Author     : catherinexiong
--%>

<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Date"%>
<%@page import="entity.SectionEntity"%>
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
<jsp:include page="header.jsp" />
<link href="css/calendardate.css" rel='stylesheet' type='text/css' />







<% HashMap<Long, List<Double>> map = (HashMap<Long, List<Double>>)request.getAttribute("pricingHashMap"); %>

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
                        HashMap<String, List<SessionEntity>> dateMap = new LinkedHashMap();
                        for (int i = 0; i < sessions.size(); i++) {
                            String date = dateFormat.format(sessions.get(i).getTimeStart());
                            long HOUR = 3600*1000;
                            Date currentD = new Date();
                            Date revisedD = new Date(currentD.getTime()+2*HOUR);
                            if ((sessions.get(i).getTimeStart()).after(revisedD)){
                            if (dateMap.get(date) == null) {
                                List<SessionEntity> list = new ArrayList();
                                list.add(sessions.get(i));
                                dateMap.put(date, list);
                            } else {
                                dateMap.get(date).add(sessions.get(i));
                            }
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
               
   
        <div class="col-lg-12">
            <div class="panel panel-default" id="seat-panel" style="display: none; margin-left:-15px;">
                <div class="panel-heading" style="margin-top:10px;color:white;text-align: center;font-size:20px;background-color:#FE980F;">
                    Step 2: Choose Section
                </div>
                <div class="panel-body" id="seat-panel-body">
                    <img src="images/property/Concerthall_layout.png" alt="concert_layout" usemap="#image-map" >
                    <map name="image-map">
                        <c:forEach items="${sections}" var="section">
                            <area id="${section.numberInProperty}" class= "p1" href="#"  coords="${section.coords}"  shape="poly"  >
                        </c:forEach>
                    </map>
                    <div class="col-lg-4">
                    <div class="panel panel-default" id="price-div" style="display: none; margin-left:-15px;-moz-border-radius:10px;
   -webkit-border-radius: 10px;border-radius:10px;width:75%">
                <div class="panel-heading" id="price-div-heading" style="text-align: center;font-size:20px;">
                    
                </div>
                <div class="panel-body" id="price-div-body" style="color:pink">
                   
                   
                </div>
                        
            </div>
                    </div>
                </div>
            </div>
            
        </div>
                 

    </div>
</div>


<script>
    var closedSections = [];
    var reservedSections = [];
    var priceList = [];
    var scroll_offset;
    var selectedSession;
    var content;
    var hoveredId;
    var category = [];
    <c:forEach items="${sections}" var="section">
        category.push("${section.category.categoryName}");
        </c:forEach>
console.log(category);
    $("input[name='sessionList']").click(function () {

        selectedSession = $("input[name='sessionList']:checked").val();
             $.ajax({
                 url: "sectionListSpecial?type=closed&id=" + selectedSession,
                 success: function (result) {
                 closedSections = result;
            }
            });
            
            $.ajax({
                 url: "sectionListSpecial?type=reserved&id=" + selectedSession,
                 success: function (result) {
                 reservedSections = result;
            }
            });
            
            $.ajax({
                 url: "sessionPriceList?type=${type}&id=" + selectedSession,
                 success: function (result) {
                    priceList = result;
                    console.log(priceList);
            }
            });
            
           
            
           
        
            

        
        $("#seat-panel").show();
        scroll_offset = $("#seat-panel").offset();
        //console.log("offset", scroll_offset);
        $("html,body").animate({
            scrollTop: scroll_offset.top
        }, 2000);


    });
    for (var i = 0; i < closedSections.length; i++) {
    $(function(){
    
   $(closedSections[i]).unbind('click').removeAttr("onclick")[0].onclick = null;

});
    }


   
    $(".p1").mouseover(function (e) {
         hoveredId = this.id;
        console.log(hoveredId);
        var available = true;
        
  
        //console.log(closedSections);
        for (var i = 0; i < closedSections.length; i++) {
            //console.log("===" + closedSections[i]);
            if (hoveredId == closedSections[i]) {
                available = false;
                   
            }
        }

        console.log(reservedSections);
        for (var i = 0; i < reservedSections.length; i++) {
            if (hoveredId == reservedSections[i]){
                available = false;
                 
            }
                
        }
        
        $("#price-div").show();
        $("#price-div").offset({left:e.pageX,top:e.pageY});
       $("#price-div-heading").html("Section "+hoveredId);
       console.log(available);
       if(available==true) {
        
      
       $("#price-div-body").html("<div><div style='float:left;color:grey;'>"+category[hoveredId-1]+"</div><div style='color:grey;text-align:right;'>$"+priceList[hoveredId-1]+"</div></div>"+ "<br />" +"<div style='color:black;text-align:center'>Available</div>");
      
   } else {
       
           $("#price-div-body").html("<div><div style='float:left;color:grey;'>"+category[hoveredId-1]+"</div><div style='color:grey;text-align:right;'>$"+priceList[hoveredId-1]+"</div></div>"+ "<br />" +"<div style='color:red;text-align:center' >Unavailable</div>");
           
       
       
     
   }
//           console.log(hoveredId);
//           console.log(available);
//           content = $("#tooltip_content"+hoveredId).html(hoveredId + "<br />" + priceList[hoveredId-1]+ "<br />" +available);  
      
    });
    $(".p1").mouseout(function () {
         hoveredId = this.id;
        console.log(hoveredId);
        
        $("#price-div").hide();
      
        

      
    });
    
 

   $(".p1").click(function () {
       var clickId = this.id;
       for (var i = 0; i < closedSections.length; i++) {
            if (clickId == closedSections[i])
                return false;
       }

       for (var i = 0; i < reservedSections.length; i++) {
            if (clickId == reservedSections[i])
                return false;
        }


   });
    
    $(document).ready(function() {
 
//            $('.p1').tooltipster({
//                functionInit: function(instance, helper){
//         
//         instance.content(content);
//    },
//                
//            functionBefore: function(instance, helper){
//         
//         instance.content(content);
//    },
//    functionAfter: function(instance, helper){
//         
//         instance.content(content);
//    },
//      
//        interactive:true,
//        cache:false,
//        contentAsHTML:true,
//        maxWidth:250
//        
//    });
                //contentCloning: true
                
            });
     

</script>
<jsp:include page="footer.jsp" />
