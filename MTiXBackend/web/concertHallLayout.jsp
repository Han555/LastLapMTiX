<%-- 
    Document   : concertHallLayout
    Created on : Sep 20, 2016, 4:32:07 PM
    Author     : Student-ID
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="side-body">
    <div class="container">
        <div class="page-title">
            <span class="title" style="font-size: 40px;color: #22A7F0">Merlion Concert Hall</span>
        </div>
        <div class="row">
            <img src="img/property/Concerthall_layout.png" alt="concert_main" usemap="#image-map" >
            <map name="image-map">
                <area data-toggle="modal" data-target="#myModal" id="1" class= "p1"alt="section1" title="section1" href="#" coords="541,900,583,968,585,1001,648,972,722,962,710,949,680,926,649,893,632,878,611,873,599,868,586,864,575,863,562,864,555,871" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="2" class= "p1"alt="section2" title="section2" href="#" coords="495,922,480,899,472,876,469,861,487,866,497,869,512,873,516,884,519,895,527,906,532,915" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="3"class= "p1" alt="section3" title="section3" href="#" coords="528,851,505,847,489,844,481,837,488,824,497,817,505,812,515,808,524,803,533,802,542,801,552,801,560,801,567,801,575,801,583,801,590,804,604,810,598,819,593,827,589,835,578,832,569,832,559,833,548,835,539,840" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="4" class= "p1" alt="section4" title="section4" href="#" coords="655,881,648,871,643,866,636,860,627,851,619,846,610,842,608,832,610,824,613,818,616,811,622,816,628,822,635,826,642,830,649,834,658,840,665,848,671,854,680,862,671,869,663,873" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="5" class= "p1"alt="section5" title="section5" href="#" coords="575,700,555,674,555,666,560,663,570,662,574,665,578,670,583,674,590,685,584,694" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="6" class= "p1" alt="section6" title="section6" href="#" coords="526,669,499,638,502,627,511,621,519,633,526,641,535,651,540,659,534,665" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="7" class= "p1" alt="sction7" title="sction7" href="#" coords="549,658,535,642,520,621,523,614,531,610,540,618,546,626,552,635,557,643,561,650,556,655" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="8" class= "p1" alt="section8" title="section8" href="#" coords="488,614,469,598,469,588,469,576,476,565,481,574,487,583,491,590,496,599,496,608" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="9" class= "p1" alt="section9" title="section9" href="#" coords="502,603,499,593,494,584,490,574,490,561,490,554,497,553,504,559,506,569,511,576,519,586" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="10" class= "p1" alt="section10" title="section10" href="#" coords="498,535,489,531,481,528,480,521,488,512,495,506,503,503,511,498,519,495,528,492,540,492,548,492,559,492,567,492,577,492,582,497,588,502,588,510,581,512,574,512,565,512,559,512,549,512,541,512,533,512,524,516,515,520,509,525,503,528" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="11" class= "p1" alt="section11" title="section11" href="#" coords="627,567,621,560,613,554,607,549,600,543,591,539,591,531,596,524,604,524,614,530,623,535,630,543,636,550,632,556" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="12" class= "p1" alt="section12" title="section12" href="#" coords="656,556,647,547,637,539,630,532,621,525,622,518,627,515,635,516,643,519,650,524,658,533,663,541,665,548" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="13" class= "p1" alt="section13" title="section13" href="#" coords="673,621,664,613,656,604,650,594,643,584,645,575,651,570,658,576,664,583,670,590,674,594,679,599,681,606,683,615" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="14" class= "p1" alt="section14" title="section14" href="#" coords="692,604,684,594,676,586,670,578,666,569,669,561,676,559,683,565,688,572,693,578,699,585,703,593,700,600" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="15" class= "p1" alt="section15" title="section15" href="#" coords="696,656,689,649,684,642,679,634,680,626,687,622,694,622,701,627,706,634,709,642,713,649,707,656" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="16" class= "p1"alt="section16" title="section16" href="#" coords="563,424,561,415,555,404,545,392,534,378,524,365,517,353,509,342,499,330,488,313,501,305,509,318,517,333,530,348,539,362,545,371,554,382,564,393,574,406,579,416,579,426,576,435,571,440,564,436" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="17" class= "p1" alt="section17" title="section17" href="#" coords="482,273,466,270,465,257,469,249,479,241,484,235,492,231,501,226,509,224,517,219,526,218,533,217,545,216,555,216,564,214,575,216,584,217,592,220,599,225,606,229,608,238,605,245,600,251,592,250,583,250,574,248,563,246,552,245,543,245,534,247,525,251,519,255,513,258,508,265,503,272" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="18" class= "p1" alt="section18" title="section18" href="#" coords="749,384,732,382,720,377,712,367,705,360,696,350,687,340,678,329,667,318,658,308,650,300,642,292,634,285,621,278,626,272,632,269,638,275,645,278,652,284,659,290,665,295,672,304,684,315,689,321,695,330,703,338,709,345,715,352,720,359,725,365,734,370,740,372,746,375" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="19" class= "p1" alt="section19" title="section19"href="#" coords="498,141,494,130,486,122,476,112,469,107,464,97,454,89,460,82,468,78,476,74,484,68,494,64,504,60,515,57,525,53,537,52,547,51,559,51,570,53,579,57,586,61,594,68,599,75,606,83,613,93,616,98,622,105,611,108,601,100,592,95,583,90,572,87,562,87,553,87,542,88,533,90,524,93,513,97,505,104,503,114,503,124,503,133" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="20" class= "p1" alt="section20" title="section20" href="#" coords="563,1000,553,998,543,994,542,985,538,976,532,966,526,958,521,951,517,944,528,939,539,936,545,944,549,953,555,962,560,970,563,978,568,987,569,994,567,999" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="21" class= "p1" alt="section21" title="section21" href="#" coords="734,937,725,937,716,933,708,928,701,924,693,915,686,907,679,900,669,893,676,890,684,885,692,882,700,890,708,898,715,905,722,911,728,919,732,924,734,931" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="22" class= "p1" alt="section22" title="section22" href="#" coords="513,611,506,604,512,597,519,591,526,600,520,607" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="23" class= "p1" alt="section23" title="section23" href="#" coords="635,572,630,565,634,560,638,555,643,561,641,568" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="24" class= "p1" alt="section24" title="section24" href="#" coords="505,550,494,542,504,533,512,541" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="25" class= "p1" alt="section25" title="section25" href="#" coords="584,531,578,526,574,521,585,512,592,524" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="26" class= "p1" alt="section26" title="section26" href="#" coords="484,292,482,284,486,277,494,277,502,277,505,283,505,291,496,293" shape="poly">
                <area data-toggle="modal" data-target="#myModal" id="27" class= "p1" alt="section27" title="section27" href="#" coords="607,270,600,269,600,261,603,254,607,248,612,243,615,250,615,258,610,262" shape="poly">
            </map>
        </div>
    </div>
</div>
<script>
var id = 0;
    $(".p1").click(function () {
        id = $(this).attr('id');
       
        $(".modal-header #myModalLabel").text('Detailed Seats Arrangement #' + id);
        $("#popup").html("LOADING...");
        $("#popup").html('<iframe id="p1frame" class="embed-responsive-item" frameborder="0" src="http://localhost:8080/MTiXBackend/seat.jsp?id=' + id + '"></iframe>');
        
        //console.log('"http://localhost:8080/MTiX-war/seat.jsp?id=' + id + '"');
    });
</script>
<div class="modal fade modal-primary" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Detailed seats</h4>
            </div>
            <div class="modal-body">
                <div class="embed-responsive embed-responsive-16by9" id="popup">
                    <!---
                    <iframe id="p1frame" class="embed-responsive-item" frameborder="0" src="http://localhost:8080/MTiXBackend/seat.jsp?id="></iframe>
                    -->
                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">OK</button>
                </div>
            </div>
        </div>
    </div>
 

    <jsp:include page="footer.jsp" />