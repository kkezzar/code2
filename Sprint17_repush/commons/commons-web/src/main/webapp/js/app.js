	/*
	 * VARIABLES
	 * Description: All Global Vars
	 */
	// Impacts the responce rate of some of the responsive elements (lower value affects CPU but improves speed)
	$.throttle_delay = 350;
	
	// The rate at which the menu expands revealing child elements on click
	$.menu_speed = 235;
	
	// Note: You will also need to change this variable in the "variable.less" file.
	$.navbar_height = 49; 

	/*
	 * APP DOM REFERENCES
	 * Description: Obj DOM reference, please try to avoid changing these
	 */	
	$.root_ = $('body');
	$.left_panel = $('#left-panel');
	$.shortcut_dropdown = $('#shortcut');
	$.bread_crumb = $('#ribbon ol.breadcrumb');

    // desktop or mobile
    $.device = null;

	/*
	 * APP CONFIGURATION
	 * Description: Enable / disable certain theme features here
	 */		
	$.navAsAjax = false; // Your left nav in your app will no longer fire ajax calls
	
	// Please make sure you have included "jarvis.widget.js" for this below feature to work
	$.enableJarvisWidgets = true;
	
	// Warning: Enabling mobile widgets could potentially crash your webApp if you have too many 
	// 			widgets running at once (must have $.enableJarvisWidgets = true)
	$.enableMobileWidgets = false;


	/*
	 * DETECT MOBILE DEVICES
	 * Description: Detects mobile device - if any of the listed device is detected
	 * a class is inserted to $.root_ and the variable $.device is decleard. 
	 */	
	
	/* so far this is covering most hand held devices */
	var ismobile = (/iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i.test(navigator.userAgent.toLowerCase()));

	if (!ismobile) {
		// Desktop
		$.root_.addClass("desktop-detected");
		$.device = "desktop";
	} else {
		// Mobile
		$.root_.addClass("mobile-detected");
		$.device = "mobile";
		
		// Removes the tap delay in idevices
		// dependency: js/plugin/fastclick/fastclick.js 
		//FastClick.attach(document.body);
	}

/* ~ END: CHECK MOBILE DEVICE */

/*
 * DOCUMENT LOADED EVENT
 * Description: Fire when DOM is ready
 */

$(document).ready(function() {
	/*
	 * Fire tooltips
	 *
	if ($("[rel=tooltip]").length) {
		$("[rel=tooltip]").tooltip();
	}*/

	//TODO: was moved from window.load due to IE not firing consist
	nav_page_height();

	// INITIALIZE LEFT NAV
	if (!null) {
		$('nav ul').jarvismenu({
			accordion : true,
			speed : $.menu_speed,
			closedSign : '<em class="fa fa-plus-square-o"></em>',
			openedSign : '<em class="fa fa-minus-square-o"></em>'
				
		});
	} else {
		alert("Error - menu anchor does not exist");
	}

	// COLLAPSE LEFT NAV
	$('.minifyme').click(function(e) {
		$('body').toggleClass("minified");
		$(this).effect("highlight", {}, 500);
		e.preventDefault();
	});

	// HIDE MENU
	$('#hide-menu >:first-child > a').click(function(e) {
		$('body').toggleClass("hidden-menu");
		e.preventDefault();
	});
	
	$('#show-shortcut').click(function(e) {
		if ($.shortcut_dropdown.is(":visible")) {
			shortcut_buttons_hide();
		} else {
			shortcut_buttons_show();
		}
		e.preventDefault();
	});

	// SHOW & HIDE MOBILE SEARCH FIELD
	$('#search-mobile').click(function() {
		$.root_.addClass('search-mobile');
	});

	$('#cancel-search-js').click(function() {
		$.root_.removeClass('search-mobile');
	});

	// ACTIVITY
	// ajax drop
	$('#activity').click(function(e) {
		var $this = $(this);

		if ($this.find('.badge').hasClass('bg-color-red')) {
			$this.find('.badge').removeClassPrefix('bg-color-');
			$this.find('.badge').text("0");
			// console.log("Ajax call for activity")
		}

		if (!$this.next('.ajax-dropdown').is(':visible')) {
			$this.next('.ajax-dropdown').fadeIn(150);
			$this.addClass('active');
		} else {
			$this.next('.ajax-dropdown').fadeOut(150);
			$this.removeClass('active')
		}

		var mytest = $this.next('.ajax-dropdown').find('.btn-group > .active > input').attr('id');
		//console.log(mytest)

		e.preventDefault();
	});

	$('input[name="activity"]').change(function() {
		//alert($(this).val())
		var $this = $(this);

		url = $this.attr('id');
		container = $('.ajax-notifications');

		loadURL(url, container);

	});

	$(document).mouseup(function(e) {
		if (!$('.ajax-dropdown').is(e.target)// if the target of the click isn't the container...
		&& $('.ajax-dropdown').has(e.target).length === 0) {
			$('.ajax-dropdown').fadeOut(150);
			$('.ajax-dropdown').prev().removeClass("active")
		}
	});

	$('button[data-loading-text]').on('click', function() {
		var btn = $(this)
		btn.button('loading')
		setTimeout(function() {
			btn.button('reset')
		}, 3000)
	});

	// NOTIFICATION IS PRESENT

	function notification_check() {
		$this = $('#activity > .badge');

		if (parseInt($this.text()) > 0) {
			$this.addClass("bg-color-red bounceIn animated")
		}
	}

	notification_check();

	/*
	* SHORTCUTS
	*/

	// SHORT CUT (buttons that appear when clicked on user name)
	$.shortcut_dropdown.find('a').click(function(e) {

		e.preventDefault();

		window.location = $(this).attr('href');
		setTimeout(shortcut_buttons_hide, 300);

	});

	// SHORTCUT buttons goes away if mouse is clicked outside of the area
	$(document).mouseup(function(e) {
		if (!$.shortcut_dropdown.is(e.target)// if the target of the click isn't the container...
		&& $.shortcut_dropdown.has(e.target).length === 0) {
			shortcut_buttons_hide()
		}
	});

	// SHORTCUT ANIMATE HIDE
	function shortcut_buttons_hide() {
		$.shortcut_dropdown.animate({
			height : "hide"
		}, 300, "easeOutCirc");
		$.root_.removeClass('shortcut-on');

	}

	// SHORTCUT ANIMATE SHOW
	function shortcut_buttons_show() {
		$.shortcut_dropdown.animate({
			height : "show"
		}, 200, "easeOutCirc")
		$.root_.addClass('shortcut-on');
	}

});

/*
 * RESIZER WITH THROTTLE
 * Source: http://benalman.com/code/projects/jquery-resize/examples/resize/
 */

(function($, window, undefined) {

	var elems = $([]), jq_resize = $.resize = $.extend($.resize, {}), timeout_id, str_setTimeout = 'setTimeout', str_resize = 'resize', str_data = str_resize + '-special-event', str_delay = 'delay', str_throttle = 'throttleWindow';

	jq_resize[str_delay] = $.throttle_delay;

	jq_resize[str_throttle] = true;

	$.event.special[str_resize] = {

		setup : function() {
			if (!jq_resize[str_throttle] && this[str_setTimeout]) {
				return false;
			}

			var elem = $(this);
			elems = elems.add(elem);
			$.data(this, str_data, {
				w : elem.width(),
				h : elem.height()
			});
			if (elems.length === 1) {
				loopy();
			}
		},
		teardown : function() {
			if (!jq_resize[str_throttle] && this[str_setTimeout]) {
				return false;
			}

			var elem = $(this);
			elems = elems.not(elem);
			elem.removeData(str_data);
			if (!elems.length) {
				clearTimeout(timeout_id);
			}
		},

		add : function(handleObj) {
			if (!jq_resize[str_throttle] && this[str_setTimeout]) {
				return false;
			}
			var old_handler;

			function new_handler(e, w, h) {
				var elem = $(this), data = $.data(this, str_data);
				data.w = w !== undefined ? w : elem.width();
				data.h = h !== undefined ? h : elem.height();

				old_handler.apply(this, arguments);
			};
			if ($.isFunction(handleObj)) {
				old_handler = handleObj;
				return new_handler;
			} else {
				old_handler = handleObj.handler;
				handleObj.handler = new_handler;
			}
		}
	};

	function loopy() {
		timeout_id = window[str_setTimeout](function() {
			elems.each(function() {
				var elem = $(this), width = elem.width(), height = elem.height(), data = $.data(this, str_data);
				if (width !== data.w || height !== data.h) {
					elem.trigger(str_resize, [data.w = width, data.h = height]);
				}

			});
			loopy();

		}, jq_resize[str_delay]);

	};

})(jQuery, this);

/*
* NAV OR #LEFT-BAR RESIZE DETECT
* Description: changes the page min-width of #CONTENT and NAV when navigation is resized.
* This is to counter bugs for min page width on many desktop and mobile devices.
* Note: This script uses JSthrottle technique so don't worry about memory/CPU usage
*/

// Fix page and nav height
function nav_page_height() {
	var setHeight = $('#main').height();
	//menuHeight = $.left_panel.height();
	
	var windowHeight = $(window).height() - $.navbar_height;
	//set height

	if (setHeight > windowHeight) {// if content height exceedes actual window height and menuHeight
		$.left_panel.css('min-height', setHeight + 'px');
		$.root_.css('min-height', setHeight + $.navbar_height + 'px');

	} else {
		$.left_panel.css('min-height', windowHeight + 'px');
		$.root_.css('min-height', windowHeight + 'px');
	}
}

$('#main').resize(function() {
	nav_page_height();
	check_if_mobile_width();
})

$('nav').resize(function() {
	nav_page_height();
})

function check_if_mobile_width() {
	if ($(window).width() < 979) {
		$.root_.addClass('mobile-view-activated')
	} else if ($.root_.hasClass('mobile-view-activated')) {
		$.root_.removeClass('mobile-view-activated');
	}
}

/* ~ END: NAV OR #LEFT-BAR RESIZE DETECT */

/*
 * DETECT IE VERSION
 * Description: A short snippet for detecting versions of IE in JavaScript
 * without resorting to user-agent sniffing
 * RETURNS:
 * If you're not in IE (or IE version is less than 5) then:
 * //ie === undefined
 *
 * If you're in IE (>=5) then you can determine which version:
 * // ie === 7; // IE7
 *
 * Thus, to detect IE:
 * // if (ie) {}
 *
 * And to detect the version:
 * ie === 6 // IE6
 * ie > 7 // IE8, IE9 ...
 * ie < 9 // Anything less than IE9
 */

// TODO: delete this function later on - no longer needed (?)
var ie = ( function() {

		var undef, v = 3, div = document.createElement('div'), all = div.getElementsByTagName('i');

		while (div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->', all[0]);

		return v > 4 ? v : undef;

	}()); // do we need this? 

/* ~ END: DETECT IE VERSION */

/*
 * CUSTOM MENU PLUGIN
 */

$.fn.extend({

	//pass the options variable to the function
	jarvismenu : function(options) {

		var defaults = {
			accordion : 'true',
			speed : 200,
			closedSign : '[+]',
			openedSign : '[-]'
		};

		// Extend our default options with those provided.
		var opts = $.extend(defaults, options);
		//Assign current element to variable, in this case is UL element
		var $this = $(this);

		//add a mark [+] to a multilevel menu
		$this.find("li").each(function() {
			if ($(this).find("ul").size() != 0) {
				//add the multilevel sign next to the link
				$(this).find("a:first").append("<b class='collapse-sign'>" + opts.closedSign + "</b>");

				//avoid jumping to the top of the page when the href is an #
				if ($(this).find("a:first").attr('href') == "#") {
					$(this).find("a:first").click(function() {
						return false;
					});
				}
			}
		});

		//open active level
		$this.find("li.active").each(function() {
			$(this).parents("ul").slideDown(opts.speed);
			$(this).parents("ul").parent("li").find("b:first").html(opts.openedSign);
			$(this).parents("ul").parent("li").addClass("open")
		});

		$this.find("li a").click(function() {

			if ($(this).parent().find("ul").size() != 0) {

				if (opts.accordion) {
					//Do nothing when the list is open
					if (!$(this).parent().find("ul").is(':visible')) {
						parents = $(this).parent().parents("ul");
						visible = $this.find("ul:visible");
						visible.each(function(visibleIndex) {
							var close = true;
							parents.each(function(parentIndex) {
								if (parents[parentIndex] == visible[visibleIndex]) {
									close = false;
									return false;
								}
							});
							if (close) {
								if ($(this).parent().find("ul") != visible[visibleIndex]) {
									$(visible[visibleIndex]).slideUp(opts.speed, function() {
										$(this).parent("li").find("b:first").html(opts.closedSign);
										$(this).parent("li").removeClass("open");
									});

								}
							}
						});
					}
				}// end if
				if ($(this).parent().find("ul:first").is(":visible") && !$(this).parent().find("ul:first").hasClass("active")) {
					$(this).parent().find("ul:first").slideUp(opts.speed, function() {
						$(this).parent("li").removeClass("open");
						$(this).parent("li").find("b:first").delay(opts.speed).html(opts.closedSign);
					});

				} else {
					$(this).parent().find("ul:first").slideDown(opts.speed, function() {
						/*$(this).effect("highlight", {color : '#616161'}, 500); - disabled due to CPU clocking on phones*/
						$(this).parent("li").addClass("open");
						$(this).parent("li").find("b:first").delay(opts.speed).html(opts.openedSign);
					});
				} // end else
			} // end if
		});
	} // end function
});

/* ~ END: CUSTOM MENU PLUGIN */

/*
 * ELEMENT EXIST OR NOT
 * Description: returns true or false
 * Usage: $('#myDiv').doesExist();
 */

jQuery.fn.doesExist = function() {
	return jQuery(this).length > 0;
};

/* ~ END: ELEMENT EXIST OR NOT */

/*
 * FULL SCREEN FUNCTION
 */

// Find the right method, call on correct element
function launchFullscreen(element) {

	if (!$.root_.hasClass("full-screen")) {

		$.root_.addClass("full-screen");

		if (element.requestFullscreen) {
			element.requestFullscreen();
		} else if (element.mozRequestFullScreen) {
			element.mozRequestFullScreen();
		} else if (element.webkitRequestFullscreen) {
			element.webkitRequestFullscreen();
		} else if (element.msRequestFullscreen) {
			element.msRequestFullscreen();
		}

	} else {
		
		$.root_.removeClass("full-screen");
		
		if (document.exitFullscreen) {
			document.exitFullscreen();
		} else if (document.mozCancelFullScreen) {
			document.mozCancelFullScreen();
		} else if (document.webkitExitFullscreen) {
			document.webkitExitFullscreen();
		}

	}

}

/*
 * ~ END: FULL SCREEN FUNCTION
 */

/*
 * INITIALIZE FORMS
 * Description: Select2, Masking, Datepicker, Autocomplete
 */

function runAllForms() {

	/*
	 * AJAX BUTTON LOADING TEXT
	 * Usage: <button type="button" data-loading-text="Loading..." class="btn btn-xs btn-default ajax-refresh"> .. </button>
	 */
	$('button[data-loading-text]').on('click', function() {
		var btn = $(this)
		btn.button('loading')
		setTimeout(function() {
			btn.button('reset')
		}, 3000)
	});

}

/*
 * INITIALIZE JARVIS WIDGETS
 */

// Setup Desktop Widgets
function setup_widgets_desktop() {
}

// Setup Desktop Widgets
function setup_widgets_mobile() {

	if ($.enableMobileWidgets && $.enableJarvisWidgets) {
		setup_widgets_desktop();
	}

}

/* ~ END: INITIALIZE JARVIS WIDGETS */


/*
 * LOAD SCRIPTS
 * Usage:
 * Define function = myPrettyCode ()...
 * loadScript("js/my_lovely_script.js", myPrettyCode);
 */

var jsArray = {};

function loadScript(scriptName, callback) {

	if (!jsArray[scriptName]) {
		jsArray[scriptName] = true;

		// adding the script tag to the head as suggested before
		var body = document.getElementsByTagName('body')[0];
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = scriptName;

		// then bind the event to the callback function
		// there are several events for cross browser compatibility
		//script.onreadystatechange = callback;
		script.onload = callback;

		// fire the loading
		body.appendChild(script);

	} else if (callback) {// changed else to else if(callback)
		//console.log("JS file already added!");
		//execute function
		callback();
	}

}

/* ~ END: LOAD SCRIPTS */

/*
* APP AJAX REQUEST SETUP
* Description: Executes and fetches all ajax requests also
* updates naivgation elements to active
*/
if($.navAsAjax)
{
    // fire this on page load if nav exists
    if ($('nav').length) {
	    checkURL();
    };

    $(document).on('click', 'nav a[href!="#"]', function(e) {
	    e.preventDefault();
	    var $this = $(e.currentTarget);

	    // if parent is not active then get hash, or else page is assumed to be loaded
		if (!$this.parent().hasClass("active") && !$this.attr('target')) {

		    // update window with hash
		    // you could also do here:  $.device === "mobile" - and save a little more memory

		    if ($.root_.hasClass('mobile-view-activated')) {
			    $.root_.removeClass('hidden-menu');
			    window.setTimeout(function() {
					if (window.location.search) {
						window.location.href =
							window.location.href.replace(window.location.search, '')
								.replace(window.location.hash, '') + '#' + $this.attr('href');
					} else {
						window.location.hash = $this.attr('href')
					}
			    }, 150);
			    // it may not need this delay...
		    } else {
				if (window.location.search) {
					window.location.href =
						window.location.href.replace(window.location.search, '')
							.replace(window.location.hash, '') + '#' + $this.attr('href');
				} else {
					window.location.hash = $this.attr('href');
				}
		    }
	    }

    });

    // fire links with targets on different window
    $(document).on('click', 'nav a[target="_blank"]', function(e) {
	    e.preventDefault();
	    var $this = $(e.currentTarget);

	    window.open($this.attr('href'));
    });

    // fire links with targets on same window
    $(document).on('click', 'nav a[target="_top"]', function(e) {
	    e.preventDefault();
	    var $this = $(e.currentTarget);

	    window.location = ($this.attr('href'));
    });

    // all links with hash tags are ignored
    $(document).on('click', 'nav a[href="#"]', function(e) {
	    e.preventDefault();
    });

    // DO on hash change
    $(window).on('hashchange', function() {
	    checkURL();
    });
}

// CHECK TO SEE IF URL EXISTS
function checkURL() {

	//get the url by removing the hash
	var url = location.hash.replace(/^#/, '');

	container = $('#content');
	// Do this if url exists (for page refresh, etc...)
	if (url) {
		// remove all active class
		$('nav li.active').removeClass("active");
		// match the url and add the active class
		$('nav li:has(a[href="' + url + '"])').addClass("active");
		var title = ($('nav a[href="' + url + '"]').attr('title'))

		// change page title from global var
		document.title = (title || document.title);
		//console.log("page title: " + document.title);

		// parse url to jquery
		loadURL(url + location.search, container);
	} else {

		// grab the first URL from nav
		var $this = $('nav > ul > li:first-child > a[href!="#"]');

		//update hash
		window.location.hash = $this.attr('href');

	}

}

// LOAD AJAX PAGES

function loadURL(url, container) {
	//console.log(container)

	$.ajax({
		type : "GET",
		url : url,
		dataType : 'html',
		cache : true, // (warning: this will cause a timestamp and will call the request twice)
		beforeSend : function() {
			// cog placed
			container.html('<h1><i class="fa fa-cog fa-spin"></i> Loading...</h1>');
		
			// Only draw breadcrumb if it is main content material
			// TODO: see the framerate for the animation in touch devices
			
			if (container[0] == $("#content")[0]) {
				drawBreadCrumb();
				// scroll up
				$("html").animate({
					scrollTop : 0
				}, "fast");
			} 
		},
		/*complete: function(){
	    	// Handle the complete event
	    	// alert("complete")
		},*/
		success : function(data) {
			// cog replaced here...
			// alert("success")
			
			container.css({
				opacity : '0.0'
			}).html(data).delay(50).animate({
				opacity : '1.0'
			}, 300);
			

		},
		error : function(xhr, ajaxOptions, thrownError) {
			container.html('<h4 style="margin-top:10px; display:block; text-align:left"><i class="fa fa-warning txt-color-orangeDark"></i> Error 404! Page not found.</h4>');
		},
		async : false
	});

	//console.log("ajax request sent");
}

// UPDATE BREADCRUMB
function drawBreadCrumb() {
	var nav_elems = $('nav li.active > a'), count = nav_elems.length;
	
	//console.log("breadcrumb")
	$.bread_crumb.empty();
	$.bread_crumb.append($("<li>Home</li>"));
	nav_elems.each(function() {
		$.bread_crumb.append($("<li></li>").html($.trim($(this).clone().children(".badge").remove().end().text())));
		// update title when breadcrumb is finished...
		if (!--count) document.title = $.bread_crumb.find("li:last-child").text();
	});

}

/* ~ END: APP AJAX REQUEST SETUP */

/*
 * PAGE SETUP
 * Description: fire certain scripts that run through the page
 * to check for form elements, tooltip activation, popovers, etc...
 */
function pageSetUp() {

	if ($.device === "desktop"){
		// is desktop
		
		// activate tooltips
		$("[rel=tooltip]").tooltip();
	
		// activate popovers
		$("[rel=popover]").popover();
	
		// activate popovers with hover states
		$("[rel=popover-hover]").popover({
			trigger : "hover"
		});
	

		// setup widgets
		setup_widgets_desktop();
	
		//setup nav height (dynamic)
		nav_page_height();
	
		// run form elements
		runAllForms();

	} else {
		
		// is mobile
		
		// activate popovers
		$("[rel=popover]").popover();
	
		// activate popovers with hover states
		$("[rel=popover-hover]").popover({
			trigger : "hover"
		});
	
		// setup widgets
		setup_widgets_mobile();
	
		//setup nav height (dynamic)
		nav_page_height();
	
		// run form elements
		runAllForms();
		
	}

}


PrimeFaces.locales ['fr'] = {
	    closeText: 'Fermer',
	    prevText: 'Précédent',
	    nextText: 'Suivant',
	    monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre' ],
	    monthNamesShort: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Jun', 'Jul', 'Aoû', 'Sep', 'Oct', 'Nov', 'Déc' ],
	    dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
	    dayNamesShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
	    dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
	    weekHeader: 'Semaine',
	    firstDay: 1,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix:'',
	    timeOnlyTitle: 'Choisir l\'heure',
	    timeText: 'Heure',
	    hourText: 'Heures',
	    minuteText: 'Minutes',
	    secondText: 'Secondes',
	    currentText: 'Maintenant',
	    ampm: false,
	    month: 'Mois',
	    week: 'Semaine',
	    day: 'Jour',
	    allDayText: 'Toute la journée'
	};

PrimeFaces.locales ['ar'] = {
	    closeText: 'إغلق',
	    prevText: 'إلى الخلف',
	    nextText: 'إلى الأمام',
	    currentText: 'بداية',
	    monthNames: ['ديسمبر', 'نوفمبر', 'أكتوبر', 'سبتمبر', 'أغسطس', 'يوليو', 'يونيو', 'مايو', 'ابريل', 'مارس', 'فبراير', 'يناير' ],
	    monthNamesShort: ['ديسمبر', 'نوفمبر', 'أكتوبر', 'سبتمبر', 'أغسطس', 'يوليو', 'يونيو', 'مايو', 'ابريل', 'مارس', 'فبراير', 'يناير' ],
	    dayNames: ['يوم الأحد‎', 'يوم الإثنين‎', 'يوم الثلاثاء‎', '‏يوم الأَرْبعاء‎', '‏يوم الخَمِيس‎', 'يوم الجُمْعَة‎‎', 'يوم السَّبْت'],
	    dayNamesShort: ['الأحد‎', 'الإثنين‎', 'الثلاثاء‎', 'الأَرْبعاء‎', 'الخَمِيس‎', 'الجُمْعَة‎‎', 'السَّبْت'],
	    dayNamesMin: ['الأحد‎', 'الإثنين‎', 'الثلاثاء‎', 'الأَرْبعاء‎', 'الخَمِيس‎', 'الجُمْعَة‎‎', 'السَّبْت'],
	    weekHeader: 'الأسبوع',
	    firstDay: 1,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix:'' ,
	    timeOnlyTitle: 'الوقت فقط' ,
	    timeText: 'الوقت' ,
	    hourText: 'ساعة',
	    minuteText: 'دقيقة',
	    secondText: 'ثانية',
	    ampm: false,
	    month: 'الشهر',
	    week: 'الأسبوع',
	    day: 'اليوم',
	    allDayText: 'سا ئراليوم'
	};