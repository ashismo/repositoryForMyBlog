jQuery(document).delegate("ul", "click", function(event) {
	jQuery('.current').removeClass('current').addClass('select');
	jQuery(this).removeClass('select').addClass('current');
});

jQuery(document).ready(function() {
	var url = jQuery(location).attr('href');
	url = url.substring(url.indexOf('://') + 3);
	url = url.substring(url.indexOf('/') + 1);
	url = url.substring(url.indexOf('/') + 1);
	url = url.substring(0, url.indexOf('.action'));
	
	url = '/' + url;
	var parentUrl = url.substring(0, url.lastIndexOf('/'));
	parentUrl = parentUrl + '/home.action';
	jQuery('ul').each(function(){
		if(jQuery(this).html().toString().indexOf(parentUrl) != -1) {
			jQuery('.current').removeClass('current').addClass('select');
			jQuery(this).removeClass('select').addClass('current');
			
			jQuery('.select_sub', '.show').removeClass('show');
			jQuery(this).children('li').children('div').addClass('show');
			
			jQuery(this).children('li').children('div').children('ul').children('li').each(function(index, value){
				
				if(jQuery(this).html().toString().indexOf(url) != -1) {
					jQuery('.sub_show').removeClass('sub_show');
					jQuery(this).addClass('sub_show');
				}
			});
		}
		
	});
	
	jQuery('#cc').combobox({
		filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
		
});