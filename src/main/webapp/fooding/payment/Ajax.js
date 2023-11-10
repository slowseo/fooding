/**
 * 
 */

 
/*===========================================================================*/
	// 포트원 API 키 
	const IMP = "imp75410442";
	const RestApiKey = "1500428451315773";
	const RESTApiSecret = "T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B";

/*===========================================================================*/

async function getToken(){
	// 인증 토큰 발급 받기
  axios({
    url: "https://api.iamport.kr/users/getToken",
    // POST method
    method: "post", 
    // "Content-Type": "application/json"
    headers: { "Content-Type": "application/json" }, 
    data: {
      // REST API키
      imp_key: "imp_apikey", 
      // REST API Secret
      imp_secret: "ekKoeW8RyKuT0zgaZsUtXXTLQ4AhPFW3ZGseDA6bkA5lamv9OqDMnxyeB9wqOsuO9W3Mx9YSJ4dTqJ3f" 
    }
  });
}
/*===========================================================================*/

const httpRequest = new XMLHttpRequest();

function handler(){
	// 프로세스 서버 응답 여기
}
httpRequest.onreadystatechange = handler;







