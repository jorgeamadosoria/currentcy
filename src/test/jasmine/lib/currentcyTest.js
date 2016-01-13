define(['currentcy','jasmine-boot'], function(currentcy,jasmineBoot){
describe("normalize", function() {

	it("normalize", function() {
		expect(currentcy.normalize('áéíóúñ')).toEqual("aeioun");
		expect(currentcy.normalize('aeioun')).toEqual("aeioun");
	});
});
});
// describe("calculate", function() {
//
// it("calculate", function() {
// expect(currentcy.calculate()).toEqual("Hello world!");
// });
//
// });
//
// describe("clearCalculate", function() {
//
// it("clearCalculate", function() {
// expect(currentcy.clearCalculate()).toEqual("Hello world!");
// });
// });
//
// describe("snapshot", function() {
//
// it("snapshot", function() {
// expect(currentcy.snapshot()).toEqual("Hello world!");
// });
// });
//
//
// describe("snapshotdetails", function() {
//
// it("snapshotdetails", function() {
// expect(currentcy.snapshotdetails()).toEqual("Hello world!");
// });
// });
//
// describe("initLanguage", function() {
//
// it("initLanguage", function() {
// expect(currentcy.initLanguage()).toEqual("Hello world!");
// });
// });
