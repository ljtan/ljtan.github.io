(function($) {
    'use strict';

    var browserWindow = $(window);

    // :: Preloader Active Code
    browserWindow.on('load', function() {
        $('#preloader').fadeOut('slow', function() {
            $(this).remove();
        });
    });

    // 2.0 Nav Active Code
    if ($.fn.classyNav) {
        $('Navigation').classyNav();
    }

    // :: Masonary Gallery Active Code
    if ($.fn.imagesLoaded) {
        $('.portfolio').imagesLoaded(function() {
            // filter items on button click
            $('.portfolio-menu').on('click', 'button', function() {
                var filterValue = $(this).attr('data-filter');
                $grid.isotope({
                    filter: filterValue
                });
            });
            // init Isotope
            var $grid = $('.portfolio').isotope({
                itemSelector: '.single-portfolio-area',
                percentPosition: true,
                masonry: {
                    columnWidth: '.single-portfolio-area'
                }
            });
        });
    }

    // :: logo home button
    $("#logo").click(function() {
        console.log("test");
        $("html").animate({
            scrollTop: 0
        }, "slow");
    });
    // :: Gallery Menu Style Active Code
    $('.portfolio-menu button.button ').on('click', function() {
        $('.portfolio-menu button.button ').removeClass('active');
        $(this).addClass('active');
    });

    // :: Mobile Menu Active Code
    $('.nav-toggle').on('click', function() {
        $('.navWrap').toggleClass('on');
    });
    $('.navWrap').on('click', function() {
        $(this).removeClass('on');
    });

    // :: Image Popup Active Code
    if ($.fn.magnificPopup) {
        $('.img-url').magnificPopup({
            type: 'image',
            gallery: {
                enabled: true
            }
        });
    }

    // ::ScrollUp Active Code
    if ($.fn.scrollUp) {
        browserWindow.scrollUp({
            scrollSpeed: 1500,
            scrollText: '<i class="fa fa-angle-up"></i>'
        });
    }

    // :: CouterUp Active Code
    if ($.fn.counterUp) {
        $('.counter').counterUp({
            delay: 10,
            time: 3000
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

    // :: Progress Bar Active Code
    if ($.fn.circleProgress) {
        $('#circle').circleProgress({
            size: 180,
            emptyFill: "rgba(0, 0, 0, .0)",
            fill: '#14c4ff',
            thickness: '16',
            reverse: true
        });
        $('#circle2').circleProgress({
            size: 180,
            emptyFill: "rgba(0, 0, 0, .0)",
            fill: '#14c4ff',
            thickness: '16',
            reverse: true
        });
        $('#circle3').circleProgress({
            size: 180,
            emptyFill: "rgba(0, 0, 0, .0)",
            fill: '#14c4ff',
            thickness: '16',
            reverse: true
        });
        $('#circle4').circleProgress({
            size: 180,
            emptyFill: "rgba(0, 0, 0, .0)",
            fill: '#14c4ff',
            thickness: '16',
            reverse: true
        });
    }

    // :: onePageNav Active Code
    if ($.fn.onePageNav) {
        $('#Menu').onePageNav({
            currentClass: 'active',
            scrollSpeed: 2000,
            easing: 'easeOutQuad'
        });
    }

    // :: Nice Scroll Active Code
    if ($.fn.niceScroll) {
        $(".page-content").niceScroll({
            cursorborder: "0 solid transparent",
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

    // :: scroll progress bar
    let last_known_scroll_position = 0;
    let ticking = false;
    window.addEventListener('scroll', function(e) {
        last_known_scroll_position = window.scrollY;

        if (!ticking) {
            window.requestAnimationFrame(function() {
                setWidth(last_known_scroll_position);
                ticking = false;
            });

            ticking = true;
        }
    });

    function setWidth() {
        var winScroll = document.body.scrollTop || document.documentElement.scrollTop;
        var height = document.documentElement.scrollHeight - document.documentElement.clientHeight;
        var scrolled = (winScroll / height) * 100;
        document.getElementById("myBar").style.width = scrolled + "%";
    }

    // :: Copyright Year
    var newDate = new Date();
    newDate.setDate(newDate.getDate() + 1);

    //or insert it via javascript
    document.getElementById('displayDate').innerHTML = newDate.getFullYear();


})(jQuery);