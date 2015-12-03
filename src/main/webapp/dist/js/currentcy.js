var currentcy = {

	preventLetter: function(e) {
        if (e.shiftKey === true ) {
            if (e.which == 9) {
                return true;
            }
            return false;
        }
        if (e.which > 57) {
            return false;
        }
        if (e.which==32) {
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
						$(el).find("#buy-amount").text(numeral(Math.floor(amount*$(el).find("#buy").text())).format(format));
						$(el).find("#avg-amount").text(
								numeral(Math.floor(amount*$(el).find("#avg").text())).format(format));
						$(el).find("#sell-amount").text(
								numeral(Math.floor(amount*$(el).find("#sell").text())).format(format));
					});
		}
		return true;
	},
	
	snapshot : function() {
		$.get('snapshot', function(data) {

			$("#calc-container").loadTemplate("dist/templates/calcrow.html",
					data);
			
			$("#trend-options-container").loadTemplate("dist/templates/trendoption.html",
					data,{success:currentcy.flot});

			$("#snapshot-container").loadTemplate(
					"dist/templates/snapshot.html",
					data,
					{
						beforeInsert : function(elem) {
							var src = $(elem).find("#code").attr("src");
							var trend = $(elem).find("#trend").text();
							$(elem).find("#trend").empty();

							if (trend == '<')
								$(elem).find("#trend").toggleClass(
										"text-danger fa-arrow-circle-up");
							if (trend == '-')
								$(elem).find("#trend").toggleClass(
										"text-primary fa-minus-circle");
							if (trend == '>')
								$(elem).find("#trend").toggleClass(
										"text-success fa-arrow-circle-down");

							if (trend == 'x')
								$(elem).find("#trend").toggleClass(
										"fa-question-circle");

							$(elem).find("a#details").click(function(e) {
								// currentcy.flot();
							});
							$(elem).find("a#details").click();
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
			url : source + "/samples",
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
					xaxis: {
					    ticks: dates,
					    rotateTicks: 90
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
