function goTags(number){
	$('#tag1').removeClass();
	$('#tag2').removeClass();
	$('#tag3').removeClass();
	$('#tag4').removeClass();
	  	  $('#tab').hide();
		  $('#tab1').hide();
		  $('#tab2').hide();
		  $('#tab3').hide();
		  $('#tab4').hide();
	
	
	switch (number) {
	   case 1:
		  $('#tag1').addClass("selected");
		  $('#tab1').show();
		  break
	   case 2:
		  $('#tag2').addClass("selected");
		  $('#tab2').show();
		  break
	   case 3:
		  $('#tag3').addClass("selected");
		  $('#tab3').show();
		  break
	   default:
		  $('#tag4').addClass("selected");
		  $('#tab4').show();
	}
}