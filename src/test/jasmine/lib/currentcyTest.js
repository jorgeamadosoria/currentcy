define(
		['currentcy', 'jquery' ],
		function(app, $) {

			describe(
					"normalize",
					function() {

						beforeEach(function() {
						});

						it(
								"normalizes input when it includes accents",
								function() {
									expect(
											app
													.normalize('&aacute;&eacute;&iacute;&oacute;&uacute;&ntilde;'))
											.toEqual("áéíóúñ");
								});
						it(
								"leaves input as is when it does not include accents",
								function() {
									expect(app.normalize('aeioun'))
											.toEqual("aeioun");
								});
					});
			describe("calculate", function() {

				it("returns false when the input is not a number", function() {
					app.setSelected({buyValue:20,buyValue:15,sellValue:10});
					expect(app.calculate('a')).toEqual(false);
				});
				
				it("returns an object with the results of the calculations when the input is a number", function() {
					var numericValue = 1;
					var selected = {buyValue:20,buyValue:15,sellValue:10};
					app.setSelected(selected);
					var returnObject = app.calculate(numericValue);
					expect(returnObject.buyAmount).toEqual('$ '+numericValue*selected.buyValue);
					expect(returnObject.avgAmount).toEqual('$ '+numericValue*selected.avgValue);
					expect(returnObject.sellAmount).toEqual('$ '+numericValue*selected.sellValue);
				});

			});

//			describe("snapshot", function() {
//
//				it("snapshot", function() {
//					expect(app.snapshot()).toEqual("Hello world!");
//				});
//			});

//			describe("snapshotdetails", function() {
//
//				it("snapshotdetails",
//						function() {
//				//	app.setSelected('Abit');
//				//			expect(app.snapshotdetails('Abit')).toEqual("Hello world!");
//						});
//			});
//
			describe("initLanguage", function() {
				it("initLanguage", function() {
					expect(app.initLanguage('../../../main/webapp/dist/bundle')).toEqual("Hello world!");
				});
			});
		});
