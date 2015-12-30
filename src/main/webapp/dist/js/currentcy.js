var currentcy = {

	defaultCurrency: 'usd',
	defaultLocale : 'en',
		
	getCurrency : function() {
		if (!store.get('currency'))
			currentcy.setCurrency(currentcy.defaultCurrency);
		return store.get('currency');
	},

	setCurrency : function(cur) {
		store.set('currency',cur);
	},
	
	getSelected : function() {
		return store.get('selected');
	},

	setSelected : function(exchange) {
		store.set('selected',exchange);
	},

	getLocale : function() {
		if (!store.get('locale'))
			currentcy.setLocale(currentcy.defaultLocale);
		return store.get('locale');
	},

	setLocale : function(locale) {
		store.set('locale',locale);
	},

	
	init: function(){
		$(function() {
            var availableTutorials = [
               "ActionScript",
               "Boostrap",
               "C",
               "C++",
            ];
         });
		
		window.addEventListener("awesomplete-selectcomplete", function(e) {
			currentcy.snapshotdetails(store.get($(e.target).val()));
		}, false);
		
			$("a#paypal-submit").attr("href", "javascript:currentcy.paypalSubmit();");
			$('.panel-heading span.clickable').on(
					"click",
					function(e) {
						if ($(this).hasClass('panel-collapsed')) {
							// expand the panel
							$(this).parents('.panel').find('.panel-body')
									.slideDown();
							$(this).removeClass('panel-collapsed');
							$(this).find('i').removeClass(
									'glyphicon-chevron-down').addClass(
									'glyphicon-chevron-up');
						} else {
							// collapse the panel
							$(this).parents('.panel').find('.panel-body')
									.slideUp();
							$(this).addClass('panel-collapsed');
							$(this).find('i').removeClass(
									'glyphicon-chevron-up').addClass(
									'glyphicon-chevron-down');
						}
					});

			$('button#subscribe').on("click", function(e) {

				$.ajax({
					url : "email/subscribe",
					method : "POST",
					data : {
						"email" : $("input#email").val()
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
			});

			$('.lang_link').on("click", function(e) {
				currentcy.setLocale($(this).data('lang'));
				currentcy.initLanguage($(this).data('lang'));
			});

			$('.currency_link').on("click", function(e) {
				currentcy.setCurrency($(this).data('currency'));
				currentcy.snapshot();
			});

			$('#paypal-submit').on("click", function(e) {
				$('#paypal-form').submit();
			});

			currentcy.snapshot();

			$("#amount").val("");
			$("#amount").on("input", currentcy.calculate);
			$("#amount").on("keypress", currentcy.preventLetter);

			$("#" + currentcy.getCurrency()).addClass("active");
			$("#" + currentcy.getLocale()).addClass("active");

			currentcy.initLanguage();

	},
	
	
	checkLocalStore : function() {
		if (!store.enabled) {
			alert('Local storage is not supported by your browser. Please disable "Private Mode", or upgrade to a modern browser.');
			return false;
		}
		return true;
	},
	
	clearCalculate:function(){
		$("#amount").val('');
					$("#buy-amount").text(
							"$ 0");
					$("#avg-amount").text(
					"$ 0");
					$("#sell-amount").text(
					"$ 0");
	},

	preventLetter : function(e) {
		if (e.shiftKey === true) {
			if (e.which == 9) {
				return true;
			}
			return false;
		}
		if (e.which > 57) {
			return false;
		}
		if (e.which == 32) {
			return false;
		}
		return true;
	},

	calculate : function(ele) {
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
	}},
	
	paypalSubmit: function() {
		$("form#paypal-form").submit();
	},
	
	initLanguage : function() {

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
	},
	
	snapshotdetails: function(snapshotId){
		var snapshot = store.get(snapshotId);
		currentcy.setSelected(snapshot);
		$("#snapshot-container")
		.loadTemplate(
				"dist/templates/snapshot-details.template",
				snapshot,
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
						var trend = snapshot.trend;
						if (trend == '<')
							$(elem)
									.find("#trend")
									.toggleClass(
											"text-danger fa-arrow-circle-up");
						if (trend == '-')
							$(elem)
									.find("#trend")
									.toggleClass(
											"text-primary fa-minus-circle");
						if (trend == '>')
							$(elem)
									.find("#trend")
									.toggleClass(
											"text-success fa-arrow-circle-down");

						if (trend == 'x')
							$(elem)
									.find("#trend")
									.toggleClass(
											"fa-question-circle");
						
					},
					error : function(e) {
						alert(e);
					}
				});
		currentcy.clearCalculate();
		currentcy.flot();
	},
	
	accentMap : {
		      "&aacute;": "á",
		      "&eacute;": "é",
		      "&iacute;": "í",
		      "&oacute;": "ó",
		      "&uacute;": "ú"
		    },
		    
	normalize : function( term ) {
			str = term;
		      for ( var obj in currentcy.accentMap ) {
		        str = str.replace(new RegExp(obj,"g"),currentcy.accentMap[ obj ]);
		      }
		      return str;
	},
	
	registerAccentFolding : function(exchangeNames){
		
		$(function() {
		    $( "#exchange-input" ).autocomplete({
		    	select: function( event, ui ) {currentcy.snapshotdetails(ui.item.value); $( "#exchange-input" ).val(ui.item.label); return false;},
		      source: function( request, response ) {
		        var matcher = new RegExp( $.ui.autocomplete.escapeRegex( request.term ), "i" );
		        response( $.grep( exchangeNames, function( value ) {
		          value = value.label || value.value || value;
		          return matcher.test( value ) || matcher.test( currentcy.normalize( value ) );
		        }) );
		      }
		    });
		  });
	},
	
	snapshot : function() {
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
								
								var exch = {label:currentcy.normalize(snapshot.name),value:snapshot.code};
								exchangeNames.push(exch);
							}
							
							currentcy.registerAccentFolding(exchangeNames);
							
							currentcy.snapshotdetails(currentcy.getSelected().code);
							
											var trend = currentcy.getSelected().trend;
											$("#calc-container").find("#trend")
													.empty();

											
							
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
						$(elem).find("#snapshot-link").attr("href","javascript:currentcy.snapshotdetails('"+src+"')");
					},
					error : function(e) {
						alert(e);
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
		

	},

	flot : function() {

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
				var dates = [];
				for (var i = 0; i < data.samples.length; i++) {
					buy.push([ i, data.samples[i].buyValue ]);
					sell.push([ i, data.samples[i].sellValue ]);
					dates.push([ i, data.samples[i].trendDate ]);
				}
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

				var plotObj = $.plot($("#flot-line-chart"), [ {
					data : buy,
					label : source + ' buy value'
				}, {
					data : sell,
					label : source + ' sell value'
				} ], options);
			}
		});
	}

};
