$(document).ready(function() {
	$("#year").hide();
	$("#contruct").hide();
	$("#areas").hide();
	$("#exceed").hide();

	$("#totalAmount").focus(function() {
		$(".error").hide();
		$("#year").hide();
		$("#contruct").hide();
		$("#areas").hide();
		$("#exceed").hide();

		var data = $("#form").serialize();
		$.post('calc', data, function(res) {
			if (res.status == "SUCCESS") {
				var totalTax = parseFloat(res.result, 10);
				$('#totalAmount').val(totalTax);
			} else {
				if (res.result.area != null)
					$("#areas").show();
				if (res.result.constructedYear != null)
					$("#contruct").show();
				if (res.result.yearAssessment != null)
					$("#year").show();
				if(res.result.exceed!=null)
					$("#exceed").show();
			}
		});
	});

	$('#cancel').click(function() {

		window.location.replace("index");
	});
	
//	$('#pay').click(function() {
//		alert("Tax details are saved successfully");
//	});
});
