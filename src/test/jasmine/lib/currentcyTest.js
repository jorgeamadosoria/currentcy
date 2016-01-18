define([ 'currentcy', 'jquery' ], function(app, $) {

	 describe(
	 "normalize",
	 function() {

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

	describe("snapshot", function() {
		var best;
		beforeEach(function()
		{
			app.setSelected(null);
			data = [ {
				bestBuy : true,
				bestSell : false,
				code : 'AA',
				name : 'Exch A'
			}, {
				bestBuy : false,
				bestSell : true,
				code : 'BB',
				name : 'Exch B'
			}, {
				bestBuy : false,
				bestSell : false,
				code : 'CC',
				name : 'Exch C'
			}, {
				bestBuy : false,
				bestSell : false,
				code : 'DD',
				name : 'Exch D'
			} ];
			best = app.setUpSnapshotData(data);
		});
		
		it("without selected exchange, the first exchange is selected by default", function() {
			expect(app.getSelected().code).toEqual("AA");
		});
		
		it("The best buy is the one with bestBuy = true", function() {
			expect(best.bestBuy.code).toEqual("AA");
		});
		
		it("The best sell is the one with bestSell = true", function() {
			expect(best.bestSell.code).toEqual("BB");
		});
		
	});

});