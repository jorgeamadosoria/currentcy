requirejs.config({
	"baseUrl" : "../../",
	"paths" : {
		numeral : "main/webapp/dist/js/lib/numeral.min",
		bootstrap : "main/webapp/dist/js/lib/bootstrap.min",
		jquery : "main/webapp/dist/js/lib/jquery.min",
		currentcy : "main/webapp/dist/js/lib/currentcy",
		store : "main/webapp/dist/js/lib/store.min",
		"jquery-ui.min" : "main/webapp/dist/js/lib/jquery-ui.min",
		"jquery.bxslider.min" : "main/webapp/dist/js/lib/jquery.bxslider.min",
		"jquery.loadTemplate-1.4.4.min" : "main/webapp/dist/js/lib/jquery.loadTemplate-1.4.4.min",
		"jquery.i18n.properties" : "main/webapp/dist/js/lib/jquery.i18n.properties",
		"jquery.flot" : "main/webapp/dist/js/lib/jquery.flot",
		"jquery.flot.resize" : "main/webapp/dist/js/lib/jquery.flot.resize",
		"jquery.flot.time" : "main/webapp/dist/js/lib/jquery.flot.time",
		"jquery.flot.tooltip.min" : "main/webapp/dist/js/lib/jquery.flot.tooltip.min",
		"jquery.flot.tickrotor" : "main/webapp/dist/js/lib/jquery.flot.tickrotor",
		
		currentcyTest : "test/jasmine/lib/currentcyTest",
		jasmine : "test/jasmine/lib/jasmine-2.4.1/jasmine",
		"jasmine-html" : "test/jasmine/lib/jasmine-2.4.1/jasmine-html",
		"jasmine-boot" : "test/jasmine/lib/jasmine-2.4.1/boot",
		"jasmine-jquery" : "test/jasmine/lib/jasmine-2.4.1/jasmine-query.js"
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
		"jasmine-html" : [ 'jasmine' ],
		"jasmine-boot" : [ 'jasmine', 'jasmine-html' ],
		"jasmine-jquery" : [ "jasmine", "jquery" ]
	}
});

require(['currentcy','jquery','jasmine-boot',"currentcyTest" ], function(currentcy,$) {
		window.onload();
});