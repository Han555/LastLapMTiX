<%-- 
    Document   : productEnterUser
    Created on : 22 Sep, 2016, 5:26:25 PM
    Author     : JingYing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="container-fluid">
    <c:url var="formAction" value="/BackController?action=productMain" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-10">
                <div class="card">       
                    <form id="contact_form" action="${formAction}" method="POST">  
                         <c:if test="${error == 'true'}">
                        <font color="red">Cannot Find Event Organizer</font><br/>
                    </c:if>
                        <table align="center">
                            <tr>
                                <td align="right">Event Organizer Email : &nbsp</td>
                                <td align="left"><input type="email" class="form-control date" required="true" name="email"</td>    
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                        </table>
                        <table align="center">
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td> 
                                <c:url var="formAction" value="/BackController" />
                                <td>
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Submit"/></td>
                            </tr>
                        </table> 
                    </form> 

                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


