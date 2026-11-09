(function($) {
    'use strict';

    var browserWindow = $(window);

    // :: Preloader Active Code
    browserWindow.on('load', function() {
        $('#preloader').fadeOut('slow', function() {
            $(this).remove();
        });
    });

    // :: logo home button
    $("#logo").click(function() {
        $("html").animate({
            scrollTop: 0
        }, "slow");
    });

    // ::ScrollUp Active Code
    if ($.fn.scrollUp) {
        browserWindow.scrollUp({
            scrollSpeed: 1500,
            scrollText: '<i class="fa fa-angle-up"></i>'
        });
    }

    // :: awards Slides Active Code
    if ($.fn.owlCarousel) {
        $(".award-slides").owlCarousel({
            items: 3,
            loop: true,
            center: true,
            autoplay: true,
            smartSpeed: 1500,
            margin: 30,
            nav: true,
            navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
            responsive: {
                0: {
                    items: 1
                },
                992: {
                    items: 3
                }
            }
        });
    }

    // :: Awards carousel recalculation on full load.
    // On wide screens the carousel can measure its stage before the final
    // layout settles, leaving it shifted until a manual window resize;
    // refreshing on window load performs that same recalculation automatically.
    browserWindow.on('load', function() {
        $(".award-slides").trigger('refresh.owl.carousel');
    });

    // :: Sticky Active Code
    browserWindow.on('scroll', function() {
        if (browserWindow.scrollTop() > 0) {
            $('.header-area').addClass('sticky');
        } else {
            $('.header-area').removeClass('sticky');
        }
    });

    // :: wow Active Code
    if (browserWindow.width() > 767) {
        new WOW().init();
    }

    // :: onePageNav Active Code
    if ($.fn.onePageNav) {
        $('#Menu').onePageNav({
            currentClass: 'active',
            scrollSpeed: 2000,
            easing: 'easeOutQuad'
        });
    }

    // :: Tooltip Active Code
    if ($.fn.tooltip) {
        $('[data-toggle="tooltip"]').tooltip();
    }

    // :: 16.0 prevent default a click
    $('a[href="#"]').on('click', function($) {
        $.preventDefault();
    });

    // :: Copyright Year
    var newDate = new Date();
    newDate.setDate(newDate.getDate() + 1);

    //or insert it via javascript
    document.getElementById('displayDate').innerHTML = newDate.getFullYear();


})(jQuery);
