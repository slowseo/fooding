/**
 * 
 */

 /*===========================================================================*/

		// 상품번호만들기
		function createOrderNum() {
		const date = new Date();
		const year = date.getFullYear().toString().slice(-2);
		const month = String(date.getMonth() + 1).pa+dStart(2, "0");
		const day = String(date.getDate()).padStart(2, "0");

		let orderNum = day;
		for (let i = 0; i < 6; i++) {
			orderNum += Math.floor(Math.random() * 10);
		}
		return orderNum; // 총 8자리 숫자
	}
	
		createOrderNum();
		var merchant_uid = createOrderNum();
/*===========================================================================*/
	// 포트원 API 키 
	const IMP = "imp75410442";
	const RestApiKey = "1500428451315773";
	const RESTApiSecret = "T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B";


/*===========================================================================*/

//토큰 얻기
function getToken(){
	axios({
		url: "/users/getToken",
		method: "post",
		headers: { "Content-Type": "application/json" },
		data: JSON.stringify({
		 "imp_key": "1500428451315773",
		 "imp_secret": "T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B",
		})
	})
}

 getToken();