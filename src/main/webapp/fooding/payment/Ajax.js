
	// 포트원 API 키 
	const IMP = "imp75410442";
	const RestApiKey = "1500428451315773";
	const RESTApiSecret = "T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B";

function getToken(){
	$.ajax({
		url: "https://api.iamport.kr/users/getToken",
		type: "POST",
		data: JSON.stringify({ imp_key: "RestApiKey", imp_secret: "RESTApiSecret" }),
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			console.log(data);
		}
	});
}