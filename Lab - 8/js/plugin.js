(function( $ ) {

    const invalidClass = 'invalid';
 
    $.fn.validate = function(regex) {
         this.each(function() {
            var element = $( this );
             if(element.val().match(regex)){
                 element.removeClass(invalidClass);
             } else {
                 element.addClass(invalidClass);
             }
        });
         return this;
     };

 
}( jQuery ));