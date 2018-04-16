$(document).ready(function() {

	$('.pagination').jqPagination({
		link_string	: '/?page={page_number}',
		max_page	: 5,
		paged		: function(page) {
			$('.log').prepend('<li>Requested page ' + page + '</li>');
			document.getElementById("hhhh").innerHTML="hello"+page;
		}
	});

});