<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ include file="../team2.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 아이콘 -->
 <script src="https://kit.fontawesome.com/38bf29a217.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<!-- Core theme CSS (includes Bootstrap)-->
 
</head>
<body>
<!------------------------  장바구니 --------------------------------------->

<!-- 장바구니 헤더 -->
    <div class="cart-header">
        <i class="fa-solid fa-cart-shopping"></i>
        <p class="cart-header-title">장바구니</p>
    </div>

<!-- 장바구니 표 시작 -->
    <div class="cart-container">
 
  <form action="./CartPay.car" >
<!-- 장바구니 상단체크 -->    
    <div class="cart-menu">
      <input class="form-check-input mt-0" type="checkbox"  aria-label="Checkbox for following text input" id="ck-all">
    
       <p class="menu-all">전체선택</p>
      <!--  <button  class="btn btn-secondary order-btn"  type="submit"  formaction="./CartPay.car" >주문하기</button> -->
		<button  class="btn btn-primary order-btn" type="submit"  formaction="./CartPay.car" >주문하기</button>
   

    <button  class="btn btn-light order-btn delete-btn" type="submit"  formaction="./CartDelete.car"  onclick="return checkcon();">삭제</button> 

  

    </div>

<!-- 장바구니 테이블 시작 -->
   <div>
    <table class="table">
      <thead class="table-light cart-thead">
        <tr>
         
          <th></th>
          <th>운행일</th>
          <th colspan="2">상품</th>
          <th>상품금액</th>
          <th>수량</th>
        </tr>
      </thead>
      <tbody>
      

		  <input type="hidden" name="member_id" value="${cartList[0].member_id}">
          <c:forEach var="list" items="${cartList}">
          <tr>
          <td><input class="form-check-input mt-0 ck" type="checkbox" name="cart_id" value="${list.cart_id }" aria-label="Checkbox for following text input" ></td>
          <td>${list.date }</td>
          <td><a href=""><img src="${list.image }" style="width: 100px;"></a></td>
          <td><a href="">${list.name }</a></td>
          <td>
            <input type="number" value="${list.price }" class="product-price" readonly > &nbsp;원
          </td>
          <td>
            <input type="number" value="${list.quantity }" name="quantity" class="product-quantity" min="1">&nbsp;개
        </tr>
        </c:forEach>
          
      </tbody>
    </table>
  </div>
  
 
 <!-- 장바구니 최종 결제금액 -->
  <div class = "total-container"  >
    <p class="totalbox" style="font-size: 25px;"> &nbsp; 최종 결제 금액</p>
    <div class="sumbox">
      <!--  &nbsp; <input type="number" id="sum" value="0" disabled>&nbsp;원  -->
       
       <p id=sum style="font-size: 25px; font-weight: bold; padding-left: 10px; color:  #ff0000"> 0원</p>
     
    </div> 

</div>
</form>
</div>



 <script>
  
  const ckAll = document.getElementById("ck-all");
  const ckArr = document.querySelectorAll(".ck");

  const productQuantity = document.querySelectorAll(".product-quantity");
  const productPrice = document.querySelectorAll(".product-price");
  const totalSum = document.getElementById("sum");

  


  // ---------------------------------------------------------------------- //
  // 체크리스트 제어
  // 상위체크박스와 하위체크박스의 checked일치시키기
  ckAll.addEventListener('click',()=>{
    ckArr.forEach(ck => {
      ck.checked = ckAll.checked;
    });
    updateTotal();
  });

  // 하위 체크박스 전체 선택되었을때 상위체크박스 checked하기
  ckArr.forEach(ck => {
    ck.addEventListener('click',()=>{
      let count = 0;
      ckArr.forEach(ck => {
        if(ck.checked){
          count ++;
        }
      });
      if(ckArr.length == count){
        ckAll.checked = true;
      }else{
        ckAll.checked = false;
      }
      updateTotal(); 
    });
    
  });

   // ---------------------------------------------------------------------- //

   

   // 수량이 변할때마다 가격합계에 반영하기
   productQuantity.forEach(pr => {
      pr.addEventListener('input',()=>{
        updateTotal();
      });
   });

   updateTotal();

  function updateTotal(){
  let priceTotal =0;
  let updatePrice = false; // 체크된게 없을때 전체가격을 0 출력하기 위해서 설정
      for(let i = 0; i < ckArr.length; i++){
        if(ckArr[i].checked){
          let quantity = parseInt(productQuantity[i].value);
          let price = parseInt(productPrice[i].value);
          
          priceTotal += (quantity * price);
          updatePrice = true;  
        }
        
      }

      if(updatePrice == false){
        priceTotal =0;
      }

     
      totalSum.innerHTML = priceTotal.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';
      
      //totalSum.value = priceTotal; //*
      
      

      

  }
  
  

  //-------------------------------------------------------------------------//
  // 삭제버튼 클릭시, 만약 체크된게 없으면 삭제 클릭했을때 선택된게 없다고 알려주기
   const deleteBtn = document.querySelector(".delete-btn");
   function checkcon() {
    let count = 0;
    ckArr.forEach(ck => {
        if (ck.checked) {
            count++;
        }
    });

    if (count === 0) {
        alert('선택된 항목이 없습니다.');
        return false; // 선택된 항목이 없을 때 폼 제출을 막기 위해 false 반환
    } else {
    	update();
        let result = confirm('삭제하시겠습니까?');
 		
        return result; // 결과에 따라 true 또는 false 반환
        
        
    }
}



  //-----------------------------------------------------------------//
  
 
  // 주문버튼을 클릭했을때 주문한게 없으면 없다고 alert창으로 알려주기 

  const orderButton = document.querySelector(".order-btn");
 
  
  orderButton.addEventListener('click',()=>{
   let count = 0;
   
   ckArr.forEach(ck => {
    if(ck.checked){
        count ++;
    }
  });

   if(count==0){
        alert("주문할 상품을 선택하여 주세요.");
        event.preventDefault();
   }else{
	   update(); //*
    document.querySelector("form").submit();
   }

   

  });

  
  
  
  // 주문하기 버튼 클릭했을때 선택된 정보 보내기
  
  //-------------------------------------------------------------------//
  // 폼태그에서 선택된 정보 보내기

      function update() {
        
        for (let i = 0; i < ckArr.length; i++) {
          if (ckArr[i].checked) {
            productQuantity[i].disabled = false;
          } else {
            productQuantity[i].disabled = true;
          }
        }
       

      }
 
      
  //---------------------------------------------------------//
  // 상품수량 제한하기 , 0이하 입력시 올바른 상품 수량을 입력하여 주십시오 출력

  
  productQuantity.forEach(pq => {
    pq.addEventListener('change',()=>{
      if(pq.value < 0){
        alert('올바른 상품 수량을 입력하여 주십시오. (최소 1개 이상 선택)');
        pq.value = 1;
      }else if(pq.value == 0){
    	  alert('최소 1개 이상 선택하셔야 합니다.');
    	  pq.value = 1;
      }
    });
  });





  </script>



</body>
</html>