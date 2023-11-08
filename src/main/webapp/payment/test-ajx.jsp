<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<meta charset="UTF-8">
<title>결제창</title>
</head>
<body>
<script>
/*===========================================================================*/

		// 상품번호만들기
		function createOrderNum() {
		const date = new Date();
		const year = date.getFullYear().toString().slice(-2);
		const month = String(date.getMonth() + 1).padStart(2, "0");
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

	// 데이터 받아오는거?
// 	async function getData(){
// 		const url = "https://api.iamport.kr/payments?imp_uid%5B%5D=imp75410442&merchant_uid%5B%5D={"여기에 주문번호(생성한거) 입력 "}";
// 	const response = await fetch(url);
// 	const data = await response.json();
// 	console.log("data",data);
// 	}
	
/*===========================================================================*/
	async function getToken(){
		const url ="https://api.iamport.kr/users/getToken?imp_key=1500428451315773&imp_secret=T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B";
		const response = await fetch(url);
		const data = await response.json();
		console.log("data",data);
	}
	
	// => 이걸로 데이터 정보 받아와서 가격 비교?하면 되지 않을까?
	// 사전 검증을 여기서 하고  script src 로 가져오면 될듯...
	
/*===========================================================================*/
	// 사전검증용
	async function checkCheck(){
	await axios({
  url: "https://api.iamport.kr/payments/prepare",
  method: "post",
  headers: { "Content-Type": "application/json" }, 
  data: {
    merchant_uid: IMP, // 가맹점 주문번호
    amount: 420000, // 결제 예정금액
  }
});
	}
	
/*===========================================================================*/
	// 사후검증용(node.js용이긴함)
	async function checkOut(){
		app.use(bodyParser.json());
		app.post("/payments/complete", async (req, res) => {
			try {
			    // req의 body에서 imp_uid, merchant_uid 추출
			    const { imp_uid, merchant_uid } = req.body;
			    // 액세스 토큰(access token) 발급 받기
			    const getToken = await axios({
			      url: "https://api.iamport.kr/users/getToken",
			      method: "post", // POST method
			      headers: { "Content-Type": "application/json" },
			      data: {
			        imp_key: RestApiKey, // REST API 키
			        imp_secret: RESTApiSecret // REST API Secret
			      }
			    });
			    const { access_token } = getToken.data; // 인증 토큰
			    // imp_uid로 포트원 서버에서 결제 정보 조회
			    const getPaymentData = await axios({
			      // imp_uid 전달
			      url: "https://api.iamport.kr/payments/${imp_uid}",
			      // GET method
			      method: "get",
			      // 인증 토큰 Authorization header에 추가
			      headers: { "Authorization": access_token }
			    });
			    const paymentData = getPaymentData.data.response; // 조회한 결제 정보
		  } catch (e) {
		    res.status(400).send(e);
		  }
		});
	}
	
	
	checkout();
/*===========================================================================*/
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 */
</script>
	
</body>
</html>