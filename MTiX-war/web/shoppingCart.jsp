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
	<div class="check">	 
			 <div class="col-md-3 cart-total">
                             <c:url var="continueS" value="Controller?action=continueShop" />
			 <a class="continue" href="${continueS}">Continue Shopping</a>
			 <div class="price-details">
				 <h3>Price Details</h3>
				 <span>Total</span>
				 <span class="total1">6200.00</span>
				 <span>Discount</span>
				 <span class="total1">---</span>
				 <span>Delivery Charges</span>
				 <span class="total1">150.00</span>
				 <div class="clearfix"></div>				 
			 </div>	
			 <ul class="total_price">
			   <li class="last_price"> <h4>TOTAL</h4></li>	
			   <li class="last_price"><span>6350.00</span></li>
			   <div class="clearfix"> </div>
			 </ul>
			
			 
			 <div class="clearfix"></div>
			 <a class="order" href="#">Place Order</a>
			 <div class="total-item">
				 <h3>OPTIONS</h3>
				 <h4>COUPONS</h4>
				 <a class="cpns" href="#">Apply Coupons</a>
				 <p><a href="#">Log In</a> to use accounts - linked coupons</p>
			 </div>
			</div>
		 <div class="col-md-9 cart-items">
			 <h1>My Shopping Bag</h1>
				<script>$(document).ready(function(c) {
					$('.close1').on('click', function(c){
						$('.cart-header').fadeOut('slow', function(c){
							$('.cart-header').remove();
						});
						});	  
					});
			   </script>
                           <c:forEach items="${records}" var="record">
                               <div class="cart-header">
				 <div class="close" id="${record.id}"> </div>
				  <div class="cart-sec simpleCart_shelfItem">
						
					   <div class="cart-item-info">
						<h3><a href="#">${record.eventName}</a><span>${record.session.name}</span></h3>
						<ul class="qty">
							
							<li><p>Qty : ${record.ticketQuantity} Tickets</p></li>
                                                        <li><p>Promotion : ${record.promotion}</p></li>
                                                        <li><p>Total Price : ${record.amount}</p></li>
						</ul>
							 <div class="delivery">
							 <p>Service Charges : $3 per Ticket </p>
<!--							 <span>Delivered in 2-3 bussiness days</span>-->
							 <div class="clearfix"></div>
				        </div>	
					   </div>
					   <div class="clearfix"></div>
											
				  </div>
			  </div>
                               
                           </c:forEach>
			 <script>$(document).ready(function(c) {
					$('.close').on('click', function(c){
							$('.cart-header').fadeOut('slow', function(c){
						$('.cart-header').remove();
					});
					});	  
					});
			 </script>
			 		
		 </div>
		 
		
			<div class="clearfix"> </div>
	 </div>
	 </div>


<jsp:include page="footer.jsp" />