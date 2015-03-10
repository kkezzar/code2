$(document).ready(function() {	

    	var menu_ul = $('.menu > li > ul'), menu_a  = $('.menu > li > a');
	menu_ul.hide();
	menu_a.click(function(e) {
		e.preventDefault();
		if(!$(this).hasClass('active')) {
			menu_a.removeClass('active');
			menu_ul.filter(':visible').slideUp('normal');
			$(this).addClass('active').next().stop(true,true).slideDown('normal');
		} else {
			$(this).removeClass('active');
			$(this).next().stop(true,true).slideUp('normal');
		}
	});
	$("#nav_hide").on("click", function() {
		if ($("#nav").css("display") != "none") {
			$("#nav_hide").animate({'right':'98%'}, function() {
				$("#nav_hide").css({'right':'auto','left':'0'})
			});
			$("#nav").animate({'opacity':'0'});
			$("#content").animate({'opacity':'0'}, function() {
				$("#nav").hide();
				$("#content").css({'width':'100%'}).animate({'opacity':'100'}, 1000);
			});
		} else {
			$("#nav_hide").css({'right':'98%','left':'auto'}).animate({'right':'80%'}, 400);
			
			//$("#nav_hide").animate({'left':'-18px'});
//			$("#nav").animate({'opacity':'0'});
			$("#content").animate({'opacity':'0'}, function() {
				$("#nav").show().animate({'opacity':'100'}, 1000);
				$("#content").css({'width':'80%'}).animate({'opacity':'100'}, 1000);
			});
		}
	});
    
    
	//descativer CTRL + Fleche gauche repli le menu
	//layoutWidget.layout.west.options.enableCursorHotkey = false;
	
	//Maintenir le menu ouvert avec l'item selectionné en gras
	$("#menuForm").find(".ui-state-active").parent().parent().parent().css("display","block"); // .ui-panelmenu-content
	$("#menuForm").find(".ui-state-active").parent().parent().parent().parent().find(".ui-panelmenu-header").addClass("ui-state-active").removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");
	$("#menuForm li.ui-menuitem a").on("click", function(){
		selectMenuitemLink(this);
	});
	
	//ajouter la class css  ui-state-active au l'element clique dans le menu
	function selectMenuitemLink(link) {
	    $("#menuForm").find(".ui-state-active").removeClass("ui-state-active");
	    $(link).addClass("ui-state-active");
	}
	
});