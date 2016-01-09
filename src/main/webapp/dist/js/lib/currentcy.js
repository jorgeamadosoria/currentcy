define(["numeral","bootstrap","store","jquery","jquery-ui.min","jquery.bxslider.min","jquery.loadTemplate-1.4.4.min","jquery.i18n.properties","jquery.flot","jquery.flot.resize","jquery.flot.time","jquery.flot.tooltip.min","jquery.flot.tickrotor"], function() {
	/**
	 * Imported module to handle localStorage (AMD-complaint version of store.js)
	 * @var store 
	 */
	var store = require("store");
	/**
	 * The main point of entry for all calls for the custom javascript code.
	 * This object contains everything necessary to work with the client side as
	 * well as to make the ajax requests to the server side
	 * 
	 * @exports currentcy
	 */
	var currentcy = {};

	/**
	 * default value for currency is US Dollars.
	 * @var defaultCurrency 
	 */
	currentcy.defaultCurrency= 'usd';
	/**
	 * default value for locale is English with no country code. 
	 * @var defaultLocale
	 */
	currentcy.defaultLocale = 'en';
	/**
	 * Map for accent folding for the exchange search
	 * @var accentMap 
	 */
	currentcy.accentMap = {
	      "&aacute;": "á",
	      "&eacute;": "é",
	      "&iacute;": "í",
	      "&oacute;": "ó",
	      "&uacute;": "ú"
	    };

	/**
	 * Function to get the selected currency for the user. Uses localStorage
	 * 
	 * @function getCurrency
	 * @return the string representing the currency selected
	 */
	currentcy.getCurrency = function() {
		if (!store.get('currency'))
			currentcy.setCurrency(currentcy.defaultCurrency);
		return store.get('currency');
	};

	/**
	 * Function to set the selected currency for the user. Uses localStorage 
	 * @function setCurrency
	 * @param {e} currency to set
	 */
	currentcy.setCurrency = function(cur) {
		store.set('currency',cur);
	};
	
	/**
	 * Function to get the selected snapshot for the user. Uses localStorage
	 * 
	 * @function getSelected
	 * @return the object representing the snapshot selected
	 */
	currentcy.getSelected = function() {
		return store.get('selected');
	};

	/**
	 * Function to set the selected snapshot for the user. Uses localStorage
	 * 
	 * @function setSelected
	 * @param {e}
	 *            snapshot to set
	 */
	currentcy.setSelected = function(exchange) {
		store.set('selected',exchange);
	};
	
	/**
	 * Function to get the selected locale for the user. Uses localStorage
	 * 
	 * @function getLocale
	 * @return the string representing the locale selected
	 */
	currentcy.getLocale = function() {
		if (!store.get('locale'))
			currentcy.setLocale(currentcy.defaultLocale);
		return store.get('locale');
	};

	/**
	 * Function to get the selected locale for the user. Uses localStorage
	 * 
	 * @function setLocale
	 * @param {e}
	 *            locale to set
	 */
	currentcy.setLocale = function(locale) {
		store.set('locale',locale);
	};
	// ---------------------------------------
	
	// Event Handlers
	
	/**
	 * Language handler for locale menu. Click event handler
	 * 
	 * @callback changeLanguage
	 * @param {e}
	 *            event data
	 */
	currentcy.changeLanguage= function(e) {
		currentcy.setLocale($(this).data('lang'));
		currentcy.initLanguage($(this).data('lang'));
	};
	
	/**
	 * Currency handler for currency menu. Click event handler
	 * 
	 * @callback changeCurrency
	 * @param {e}
	 *            event data
	 */
	currentcy.changeCurrency= function(e) {
		currentcy.setCurrency($(this).data('currency'));
		currentcy.snapshot();
	};
	
	/**
	 * Handler for email subscribe button. Click event handler
	 * 
	 * @callback subscribeButton
	 * @param {e}
	 *            event data
	 */
	currentcy.subscribeButton= function(e) {
		$.ajax({
			url : "email/subscribe",
			method : "POST",
			data : {
				"email" : $("input#email").val(),
				"code" : currentcy.getSelected().code
			},
			beforeSend : function(data) {
				$("#subscribe-group").toggleClass("hidden");
				$("#subscribe-loading").toggleClass("hidden");
			},
			success : function(data) {
				$("#subscribe-group").toggleClass("hidden");
				$("#subscribe-loading").toggleClass("hidden");
				$("input#email").val('');
			}
		});
	};
	
	/**
	 * Handler for paypal form submit. Click event handler
	 * 
	 * @callback paypalSubmit
	 * @param {e}
	 *            event data
	 */
	currentcy.paypalSubmit= function() {
		$("#paypal-form").submit();
	};
	
	// To avoid letters and symbols in the input field for amount calculator
	currentcy.preventLetter = function(e) {
		if (e.shiftKey === true) {
			return (e.which == 9);
		}
		
		return !(e.which > 57 || e.which == 32);
	};
	
	currentcy.calculate = function(ele) {
		var e = $("#amount").val();

		if (isNaN(e)) {
			ele.preventDefault();
			return false;
		} else {
			var amount = $("#amount").val();
			var format = '$ 0,0';
			var value = numeral(amount);
			
						$("#buy-amount").text(
								numeral(
										Math.floor(amount* currentcy.getSelected().buyValue))
										.format(format));
						$("#avg-amount").text(
								numeral(
										Math.floor(amount* currentcy.getSelected().avgValue))
										.format(format));
						$("#sell-amount").text(
								numeral(
										Math.floor(amount* currentcy.getSelected().sellValue))
										.format(format));
	}};
	
	currentcy.snapshotDetailsAfterInsert= function(elem) {
		var src = $(elem).find(
				".panel-default")
				.attr("id");
		$(elem)
				.find("#code")
				.attr(
						"src",
						"dist/logos/"+ src);
		var trend = currentcy.getSelected().trend;
		var trendClass = "";
		if (trend == '<')
			trendClass="text-danger fa-arrow-circle-up";
		if (trend == '-')
			trendClass="text-primary fa-minus-circle";
		if (trend == '>')
			trendClass="text-success fa-arrow-circle-down";
		if (trend == 'x')
			trendClass="fa-question-circle";

		$(elem).find("#trend").toggleClass(trendClass);
	};
	
	// ----------------------------------------
	
	// manually invoked functions
	
	// Initial configuration of events, local storage and UI settings for the
	// first time the user access the page on the session
	currentcy.init= function(){
		
			// Setting event handlers to page elements
			$("a#paypal-submit").on("click",currentcy.paypalSubmit);
			$('button#subscribe-button').on("click", currentcy.subscribeButton);
			$('.lang_link').on("click", currentcy.changeLanguage);
			$('.currency_link').on("click", currentcy.changeCurrency);
			$('#paypal-submit').on("click", currentcy.paypalSubmit);

			// setting events for the calculator
			$("#amount").val("");
			$("#amount").on("input", currentcy.calculate);
			$("#amount").on("keypress", currentcy.preventLetter);

			// select active currency in nav menu
			$("#" + currentcy.getCurrency()).addClass("active");
			// select active locale in nav menu
			$("#" + currentcy.getLocale()).addClass("active");

			currentcy.snapshot();
			currentcy.initLanguage();

	};
	
	
	currentcy.clearCalculate =function(){
		$("#amount").val('');
					$("#buy-amount").text(
							"$ 0");
					$("#avg-amount").text(
					"$ 0");
					$("#sell-amount").text(
					"$ 0");
	};

	// Initiate current message properties to translate the page to selected
	// locale
	currentcy.initLanguage = function() {

		var lang = currentcy.getLocale();
		$.i18n.properties({
			path : 'dist/bundle/',
			mode : 'map',
			language : lang,
			callback : function() {
				// alert($.i18n.prop('msg.lemma'));
				$("#msg-en").html($.i18n.prop('msg.en'));
				$("#msg-es-cu").html($.i18n.prop('msg.es.cu'));
				$("#msg-es-uy").html($.i18n.prop('msg.es.uy'));
				$("#msg-exchange").html($.i18n.prop('msg.exchange'));
				$("#msg-calculator").html($.i18n.prop('msg.calculator'));
				$("#msg-currency").html($.i18n.prop('msg.currency'));
				$("#lemma").html($.i18n.prop('msg.lemma'));
				$("#title").html($.i18n.prop('msg.title'));
				$("#menu-title").html($.i18n.prop('msg.title'));
				$("#github").html($.i18n.prop('msg.github'));
				$("#paypal").html($.i18n.prop('msg.paypal'));
				$("#msg-language").html($.i18n.prop('msg.language'));
				$("#exchange-input").attr('placeholder',$.i18n.prop('exchange.placeholder'));
				$("#email").attr('placeholder',$.i18n.prop('subscribe.placeholder'));
				$("#swagger").html($.i18n.prop('msg.swagger'));
				$("#about").html($.i18n.prop('msg.about'));
				$("#exchange-rate").html($.i18n.prop('msg.exchange.rate'));
				$("#author").html($.i18n.prop('msg.author'));
				$("#author-title").html($.i18n.prop('msg.author.title'));
				$("#author-comment").html($.i18n.prop('msg.author.comment'));
				$("#author-email").html($.i18n.prop('msg.author.email'));
				$("#contact-me").html($.i18n.prop('msg.contact.me'));
				$("#kudos").html($.i18n.prop('msg.kudos'));
				$("#msg-trend").html($.i18n.prop('msg.trend'));
				$("#subscribe").html($.i18n.prop('msg.subscribe'));
				$("button#subscribe").html($.i18n.prop('msg.subscribe'));
				$("#msg-current-rate").html($.i18n.prop('msg.current.rate'));
			}
		});
	};
	
	// function show snapshot details for the selected exchange. This will
	// become the selected exchange for all subsequent requests to the page by
	// the user.
	currentcy.snapshotdetails= function(snapshotId){
		var snapshot = store.get(snapshotId);
		currentcy.setSelected(snapshot);
		$("#snapshot-container")
		.loadTemplate(
				"dist/templates/snapshot-details.template",
				snapshot,
				{
					afterInsert : currentcy.snapshotDetailsAfterInsert
				});
		currentcy.clearCalculate();
		currentcy.flot();
	};
	
	
		    
	currentcy.normalize = function( term ) {
			str = term;
		      for ( var obj in currentcy.accentMap ) {
		        str = str.replace(new RegExp(obj,"g"),currentcy.accentMap[ obj ]);
		      }
		      return str;
	};
	
	// register accent folding for exchange search using jquery UI autocomplete.
	// It matches accents according to the accentMap. ExchangeNames is the
	// complete list of all exchanges and their codes to be used as label and
	// values for each entry for jquery autocomplete UI, respectively.
	currentcy.registerAccentFolding = function(exchangeNames){
		
		$(function() {
		    $( "#exchange-input" ).autocomplete({
		    	minLength: 0,
		    	// ui.item represents the selected exchange on the autocomplete
				// element. Value is the value to be used, and label is the
				// value to show on the UI
		    	select: function( event, ui ) {currentcy.snapshotdetails(ui.item.value); $( "#exchange-input" ).val(ui.item.label); return false;},
		      source: function( request, response ) {
		        var matcher = new RegExp( $.ui.autocomplete.escapeRegex( request.term ), "i" );
		        response( $.grep( exchangeNames, function( value ) {
		          value = value.label || value.value || value;
		          return matcher.test( value ) || matcher.test( currentcy.normalize( value ) );
		        }) );
		      }
		    }).focus(function(){
		        $(this).autocomplete("search",$(this).val());
		    });
		  });
	};
	
	// takes the latest snapshot for each exchange
	currentcy.snapshot = function() {
			$
					.get(
							'snapshot?currency=' + currentcy.getCurrency(),
							function(data) {
								var bestBuy = null;
								var sellBuy = null;
								var exchangeNames = Array();
								for(var snapshot of data){
									if (!currentcy.getSelected()){
										currentcy.setSelected(snapshot);
									}
									if (snapshot.bestBuy){
										bestBuy = snapshot;
									}
									if (snapshot.bestSell){
										bestSell = snapshot;
									}
									
									store.set(snapshot.code,snapshot);
									var exch = {label:currentcy.normalize(snapshot.name),value:snapshot.code};
									exchangeNames.push(exch);
								}
								
								currentcy.registerAccentFolding(exchangeNames);
								
								currentcy.snapshotdetails(currentcy.getSelected().code);
								
								$("#calc-container").find("#trend").empty();
	
												
						
			$("#ticker").on("click", "li a#snapshot-link", function(event){
				currentcy.snapshotdetails($(this).find("div").attr("id"));
				return false;
			});			
			
			$("#ticker")
			.loadTemplate(
					"dist/templates/snapshot.template",
					data,
					{
						afterInsert : function(elem) {
							var src = $(elem).find(
									".panel-default")
									.attr("id");
							$(elem)
									.find("#code")
									.attr(
											"src",
											"dist/logos/"+ src);
						},
						success: function(e){
							store.set('ticker',$('#ticker').bxSlider({
								  minSlides: 4,
								  maxSlides: 10,
								  slideWidth:300,
								  slideMargin: 2,
								  ticker: true,
								  useCSS: false, // to allow tickerHover
								  speed: 100000,
								  pager: true,
								  infiniteLoop:true,
								  controls:true
								}));
						}
					});
	
			
		});
		

	};

	// create and configure the flot chart using the currently selected currency
	// and exchange. This method sends an ajax request for the latest samples
	// for the current selection and recreate the flot chart to show the trend.
	currentcy.flot = function() {
		var source = currentcy.getSelected().code;

		var offset = 0;
		$.ajax({
			url : source + "/samples?currency=" + currentcy.getCurrency(),
			beforeSend : function(data) {
				$("#flot-chart-loading").show();
				$("#flot-line-chart").hide();
			},
			success : function(data) {
				$("#flot-chart-loading").hide();
				$("#flot-line-chart").show();
				var buy = [];
				var sell = [];
				// needed for the ticks in the x axis
				var dates = [];
				for (var i = 0; i < data.samples.length; i++) {
					buy.push([ i, data.samples[i].buyValue ]);
					sell.push([ i, data.samples[i].sellValue ]);
					dates.push([ i, data.samples[i].trendDate ]);
				}
				// options for the flot chart
				var options = {
					series : {
						lines : {
							show : true
						},
						points : {
							show : true
						}
					},
					grid : {
						hoverable : true
					// IMPORTANT! this is needed for tooltip to work
					},
					yaxis : {
						min : data.min,
						max : data.max
					},
					xaxis : {
						ticks : dates,
						rotateTicks : 90
					},
					tooltip : true,
					tooltipOpts : {
						content : "'%s' of %x is %y.4",
						shifts : {
							x : -50,
							y : 1
						}
					}
				};
				// actual creation of the flot chart
				var plotObj = $.plot($("#flot-line-chart"), [ {
					data : buy,
					label : source + ' buy value'
				}, {
					data : sell,
					label : source + ' sell value'
				} ], options);
			}
		});
};
return currentcy;
});
