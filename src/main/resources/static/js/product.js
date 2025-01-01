$(document).ready(function(){
	// var num = 0;
	// $( "#vnt-thumbnail-for .item" ).each(function( i ) { num++; });
    // PRODUCT THUMBNAIL
     $("#vnt-thumbnail-for").slick({
        slidesToShow:1,
        slidesToScroll:1,
        asNavFor:"#vnt-thumbnail-nav",
        arrows:false,
    });
    $("#vnt-thumbnail-nav").slick({
        slidesToShow:5,
        slidesToScroll:1,
        asNavFor:"#vnt-thumbnail-for",
        focusOnSelect: true,
        vertical:true,
    });
    // OTHER
    $("#slideOther").slick({
        slidesToShow : 4,
        autoplay:true,
        pauseOnHover:false,
        pauseOnFocus:false,
        responsive: [
            {
                breakpoint: 991,
                settings: {
                    slidesToShow: 4,
                }
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 3,
                }
            },
            {
                breakpoint: 500,
                settings: {
                    slidesToShow: 2,
                }
            },
            {
                breakpoint: 360,
                settings: {
                    slidesToShow: 1,
                }
            }
        ]
    });
});