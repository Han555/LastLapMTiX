<%-- 
    Document   : shoppingCart
    Created on : Oct 26, 2016, 1:31:53 AM
    Author     : catherinexiong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:useBean id="records" class="java.util.Collection<entity.ShopCartRecordEntity>" scope="request"/>
<jsp:include page="header.jsp" />

<div class="container">
    <div class="panel panel-default col-lg-12" style="box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -moz-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -webkit-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);">
        <div class="panel-heading" style="height:40px;text-align:center;font-size:20px;margin-left: -15px; margin-right: -15px;background: repeating-linear-gradient(
             -45deg,
             transparent,
             transparent,
             #EEE 2px,
             transparent 3px
             );">My Shopping Bag</div> 
    <div class="check">	
        <%if (request.getAttribute("records") == null) {%>
        <div class="col-md-12 cart-items">
            <div style="font-size:24px;text-align:center ">No Shopping Cart Records</div>
        </div>
        <%} else { %> 
      
    <div class="col-md-3 cart-total">
        
			 <a class="continue" href="#">Continue to basket</a>
			 
			
			 
			 <div class="clearfix"></div>
			 <a class="order" href="#">Place Order</a>
			 
			</div>    
            
        
<div class="col-md-9 cart-items" style="margin-top:-50px;">
            <c:forEach items="${records}" var="record">
                
                    <div class="cart-sec simpleCart_shelfItem">

                        <div class="cart-item-info" style="width:120%">
                            <h2 style="font-size:18px;"><a href="#">${record.eventName}</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span style="font-size:12px;">${record.session.name}</span></h2>
                            <ul class="qty">

                                <li><p style="font-size:13px;">Qty : ${record.ticketQuantity} Tickets</p></li>
                                <li><p style="font-size:13px;">Promotion : ${record.promotion}</p></li>
                                <li><p style="font-size:13px;">Total Ticket Price : ${record.amount}</p></li>
                                <li><input class="btn btn-info" value="Remove" onclick="remove(${record.id})" type="button"></li>
                            </ul>
                            <div style="color:#A6A6A6">
                                <p>Service Charges : $3 per Ticket </p>
                                <!--							 <span>Delivered in 2-3 bussiness days</span>-->
                                <div class="clearfix"></div>
                            </div>	
                        </div>
                        <div class="clearfix"></div>

                    </div>
               

            </c:forEach>
        </div>

        


        <div class="clearfix"> </div>
    </div>

    <%}%>
</div>
</div>

<!--<script>
    var removeId;
    function remove(id){
        
        
    }
 (".button").click(function){
     
   removerId = this.id;
   ajax{}
     
 });   
</script>-->

<jsp:include page="footer.jsp" />