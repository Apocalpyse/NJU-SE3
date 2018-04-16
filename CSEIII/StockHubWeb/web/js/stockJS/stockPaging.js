/**
 * Created by A on 2017/6/13.
 */
$(document).ready(function() {

    $('.pagination').jqPagination({
        link_string	: '/?page={page_number}',
        max_page	: 5,
        paged		: function(page) {
            $('.log').prepend('<li>Requested page ' + page + '</li>');


        }
    });

});