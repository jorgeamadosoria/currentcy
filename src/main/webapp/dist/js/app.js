requirejs.config({
	"baseUrl" : "dist/js/lib",
	"paths" : {
		numeral : "numeral.min",
		bootstrap : "bootstrap.min",
		jquery : "jquery.min",
		currentcy : "currentcy",
		store : "store.min"
	},
	"shim" : {
		"websocket" : "websocket",
		"bootstrap" : [ "jquery" ],
		"jquery-ui.min" : [ "jquery" ],
		"jquery.bxslider.min" : [ "jquery" ],
		"jquery.loadTemplate-1.4.4.min" : [ "jquery" ],
		"jquery.i18n.properties" : [ "jquery" ],
		"jquery.flot" : [ "jquery" ],
		"jquery.flot.resize" : [ "jquery", "jquery.flot" ],
		"jquery.flot.time" : [ "jquery", "jquery.flot" ],
		"jquery.flot.tooltip.min" : [ "jquery", "jquery.flot" ],
		"jquery.flot.tickrotor" : [ "jquery", "jquery.flot" ],
	}
});

// Load the main app module to start the app
requirejs([ "currentcy" ], function(currentcy) {
	currentcy.init();
});