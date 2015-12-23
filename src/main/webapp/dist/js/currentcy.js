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

	getLocale : function() {
		if (!store.get('locale'))
			currentcy.setLocale(currentcy.defaultLocale);
		return store.get('locale');
	},

	setLocale : function(locale) {
		store.set('locale',locale);
	},

	checkLocalStore : function() {
		if (!store.enabled) {
			alert('Local storage is not supported by your browser. Please disable "Private Mode", or upgrade to a modern browser.')
			return false;
		}
		return true;
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
			$("#calc-container tr").map(
					function(index, el) {
						$(el).find("#buy-amount").text(
								numeral(
										Math.floor(amount
												* $(el).find("#buy").text()))
										.format(format));
						$(el).find("#avg-amount").text(
								numeral(
										Math.floor(amount
												* $(el).find("#avg").text()))
										.format(format));
						$(el).find("#sell-amount").text(
								numeral(
										Math.floor(amount
												* $(el).find("#sell").text()))
										.format(format));
					});
		}
		return true;
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
				$("#about").html($.i18n.prop('msg.about'));
				$("#exchange-rate").html($.i18n.prop('msg.exchange.rate'));
				$("#author").html($.i18n.prop('msg.author'));
				$("#author-title").html($.i18n.prop('msg.author.title'));
				$("#author-comment").html($.i18n.prop('msg.author.comment'));
				$("#author-email").html($.i18n.prop('msg.author.email'));
				$("#contact-me").html($.i18n.prop('msg.contact.me'));
				$("#kudos").html($.i18n.prop('msg.kudos'));
				$("#table-trend").html($.i18n.prop('msg.table.trend'));
				$("#table-exchange").html($.i18n.prop('msg.table.exchange'));
				$("#table-average").html($.i18n.prop('msg.table.average'));
				$("#table-buy").html($.i18n.prop('msg.table.buy'));
				$("#table-sell").html($.i18n.prop('msg.table.sell'));
				$("#msg-trend").html($.i18n.prop('msg.trend'));
				$("#subscribe").html($.i18n.prop('msg.subscribe'));
				$("button#subscribe").html($.i18n.prop('msg.subscribe'));
				$("#msg-current-rates").html($.i18n.prop('msg.current.rates'));
			}
		});
	},

	snapshot : function() {
		$
				.get(
						'snapshot?currency=' + currentcy.getCurrency(),
						function(data) {
							var bestBuy = null;
							var sellBuy = null;
							for(var snapshot of data){
								if (snapshot.bestBuy){
									bestBuy = snapshot;
								}
								if (snapshot.bestSell){
									bestSell = snapshot;
								}
							}
							
							
							$("#calc-container").loadTemplate(
									"dist/templates/calcrow.html", data,{
										beforeInsert : function(elem) {
											$("tr#"+bestBuy.code).addClass("success");
											var trend = $(elem).find(
													"#trend").text();
											$(elem).find("#trend")
													.empty();

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
										}
									});

							
							
							$("#trend-options-container").loadTemplate(
									"dist/templates/trendoption.html", data, {
										success : currentcy.flot
									});

							
							$("#snapshot-container")
									.loadTemplate(
											"dist/templates/snapshot.html",
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
																	"dist/logos/"
																			+ src);
												},
												error : function(e) {
													alert(e);
												}
											});
							
							
						});
		

	},

	flot : function() {

		var source = $("#trend-options-container").val();

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
					dates.push([ i, data.samples[i].date ]);
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
