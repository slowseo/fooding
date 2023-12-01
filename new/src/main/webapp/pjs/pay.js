/**
 * 
 */
 		// 상품번호 생성
		function createOrderNum() {
			const date = new Date();
			const day = String(date.getDate()).padStart(2, "0");

			let orderNum = day;
			for (let i = 0; i < 6; i++) {
				orderNum += Math.floor(Math.random() * 10);
			}
			return orderNum; // 총 8자리 숫자
		}
		
		// 상품번호(merchant_uid)
		const purchase_id = createOrderNum();
		document.getElementById("purchase_id").value = purchase_id;


 
 // 장바구니로 돌아가기
		function cartBack() {
			history.back();
			}
		

		// 결제수단 미선택시 알림창 알려주기
		function findSubject() {
			var arrRadio = document.getElementsByName("pay");
			var selected = false;
			for (var i = 0; i < arrRadio.length; i++) {
				if (arrRadio[i].checked) {
					selected = true;
					break;
				}
			}
			if (selected) {
				requestPay();
			} else {
				alert('결제수단을 선택하세요');
			}
		}
		
		

		// 포트원(구 아임포트) API
		function requestPay() {
			const userCode = "";
			IMP.init(userCode);

			var selectedPG = document
					.querySelector('input[name="pay"]:checked').value;

			IMP.request_pay({
				pg : selectedPG, 
				pay_method : "card",
				merchant_uid : purchase_id, 
				name : name, 
				amount : money,
				buyer_email : email,
				buyer_name : userName
			}, function(data) {
				if (data.success) { 
					var msg = "결제 완료";
					$.ajax({
			            url: './check.ajx', 
			            type: 'post',
			            success: function(token) {
			        document.getElementById("token").value = token;
					
					document.getElementById("mypayment").submit();

			            }});
				} else { 
					var msg = "결제를 취소하셨습니다";
				}
				alert(msg);
			});
		}
		