describe("cenzor", function () {
    jasmine.clock().install();

    beforeEach(function () {
        let s = spyOn(console, 'log').and.callThrough();
        $('body').append('<input type="text" name="firstName" class="my-input" />');
    });

    afterEach(function () {
        $('input').remove();
    });

    it("should add invalid class", function () {
        const element = $('input[name=firstName]');
        element.validate(/[a-z]/);
        expect(element.hasClass('invalid')).toBe(true);
    });

    it("should not add invalid class", function () {
        const element = $('input[name=firstName]');
        element.val('abc');
        element.validate(/[a-z]/);
        expect(element.hasClass('invalid')).toBe(false);
    });

    it("should work for more than 1 element", function () {
        $('body').append('<input type="text" name="lastName" class="my-input" />');
        const elements = $('.my-input');
        elements.validate(/[a-z]/);
        elements.each(function() {
            expect($(this).hasClass('invalid')).toBe(true);
        })
    });
});