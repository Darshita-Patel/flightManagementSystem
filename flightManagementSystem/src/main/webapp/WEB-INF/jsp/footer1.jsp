
<!-- ->footer>
  <section class="w3l-footer">
    <div class="w3l-footer-16-main py-5">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 column">
            <div class="row">
              <p>&copy; 2024 . All rights reserved.</p>
            </div>
          </div>
          
        </div>
        
          

    <!-- move top ->
    <button onclick="topFunction()" id="movetop" title="Go to top">
      <span class="fa fa-angle-up"></span>
    </button>
    <script>
      // When the user scrolls down 20px from the top of the document, show the button
      window.onscroll = function () {
        scrollFunction()
      };

      function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
          document.getElementById("movetop").style.display = "block";
        } else {
          document.getElementById("movetop").style.display = "none";
        }
      }

      // When the user clicks on the button, scroll to the top of the document
      function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
      }
    </script>
    <!-- //move top ->
    <script>
      $(function () {
        $('.navbar-toggler').click(function () {
          $('body').toggleClass('noscroll');
        })
      });
    </script>
  </section>
</footer-->
<!-- Template JavaScript -->
<script src="${pageContext.request.contextPath}/assets2/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets2/js/theme-change.js"></script>
<!--/slider-js-->
<script src="${pageContext.request.contextPath}/assets2/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets2/js/modernizr-2.6.2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets2/js/jquery.zoomslider.min.js"></script>
<!--//slider-js-->
<script src="${pageContext.request.contextPath}/assets2/js/owl.carousel.js"></script>
<!-- script for tesimonials carousel slider -->
<script>
  $(document).ready(function () {
    $("#owl-demo1").owlCarousel({
      loop: true,
      margin: 20,
      nav: false,
      responsiveClass: true,
      responsive: {
        0: {
          items: 1,
          nav: false
        },
        736: {
          items: 1,
          nav: false
        },
        1000: {
          items: 1,
          nav: false,
          loop: true
        }
      }
    })
  })
</script>
<!-- //script for tesimonials carousel slider -->
<!-- stats number counter-->
<script src="${pageContext.request.contextPath}/assets2/js/jquery.waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/assets2/js/jquery.countup.js"></script>
<script>
  $('.counter').countUp();
</script>
<!-- //stats number counter -->

<!--/MENU-JS-->
<script>
  $(window).on("scroll", function () {
    var scroll = $(window).scrollTop();

    if (scroll >= 80) {
      $("#site-header").addClass("nav-fixed");
    } else {
      $("#site-header").removeClass("nav-fixed");
    }
  });

  //Main navigation Active Class Add Remove
  $(".navbar-toggler").on("click", function () {
    $("header").toggleClass("active");
  });
  $(document).on("ready", function () {
    if ($(window).width() > 991) {
      $("header").removeClass("active");
    }
    $(window).on("resize", function () {
      if ($(window).width() > 991) {
        $("header").removeClass("active");
      }
    });
  });
</script>
<!--//MENU-JS-->

<script src="${pageContext.request.contextPath}/assets2/js/bootstrap.min.js"></script>

</body>

</html>