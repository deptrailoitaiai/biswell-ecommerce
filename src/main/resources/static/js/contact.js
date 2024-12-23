$(document).ready(function(){
    $(".map-contact .mc-tab").click(function(){
        if(!$(this).parents(".map-contact").hasClass("active")){
            $(this).parents(".map-contact").addClass("active");
        }
        else{
            $(this).parents(".map-contact").removeClass("active");
        }
    });
    $(".list-tab li a").click(function(){
        if($(window).innerWidth()<991){
            $(this).parents(".map-contact").removeClass("active");
            $(this).parents(".map-contact").find(".list-tab").stop().slideUp(500);
        }
    });
    $(".view-map-contact a").click(function(){
        $("html,body").animate({
            scrollTop: $(".map-contact").offset().top
        },1000);
        var target=$(this).attr("href");
        $(".map-contact .list-tab li").removeClass("active");
        $(".map-contact .list-tab li").each(function(){
            if($(this).find("a").attr("href")==target){
                $(this).addClass("active");
            }
        });
        return false;
    });
    $(".map-contact .list-tab li").click(function(){
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
        get_hh();
    });
    function get_hh(){
        var t = $(".list-tab li.active a").text();
        $(".map-contact .mc-tab").text(t);
    }
    get_hh();
	
	$("#viewmap li:first-child").find("a").trigger("click");
	var validator = $("#formContact").validate({
		rules: {
			name: {
				required: true,
			},
			email: {
				required: true,
				email: true,
			},
			phone: {
				required: true,
				number: true, 
			},
			address: {
				required: true,
			},
			subject: {
				required: true,
			},
			content: {
				required: true,
			},
			security_code: {
				required: true,
				number: true,
			},
		}
	});
});

function getMap(id = null, title = null, url = null) {
	switch (title) {
		case 1:
			$("#ext_maps").html('<div align="center"><img src="' + DIR_IMAGE + '/loading.gif" alt="Loadding ..." /><br>Loadding ...</div>');
			$.ajax({
				type: "GET",
				url: ROOT + 'modules/contact/popup/maps.php',
				data: "id=" + id,
				success: function(html){
					$("#ext_maps").html(html);
				}
			});
			break;
		case 2:
			$("#ext_maps").html("<img src='" + url + "' alt='map_picture' width='100%' />");
			break;
	}
}