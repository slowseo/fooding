package com.fooding.payment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class PaymentDAO {
	
	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// ==== 디비 연결 메서드 ====
	
	private Connection getCon() throws Exception{
		// Connection Pool을 사용한 디비 연결
		
		// 프로젝트의 정보를 확인(JNDI)
		Context initCTX = new InitialContext();  //컨텍스트= 프로젝트, init = 초기화, CTX = 컨텍스트
		// 프로젝트안에 작성된 디비 연결정보(context.xml)를 불러오기
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/mvc"); // 불러오기
		// DB 연결 수행
		con = ds.getConnection();
		System.out.println(" DAO : 디비연결 성공! (커넥션풀)");
		return con; }

	
	//================    디비 연결(자원) 해제 메서드    ====================
	public void CloseDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
			System.out.println(" DAO : 디비 자원해제 완료! ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//================    디비 연결(자원) 해제 메서드    ====================
	//=============     /////////////    =======================
	// 1. String[] 으로 오는 장바구니 번호 ArrayList<Integer>로 변환하기
	public ArrayList<Integer> stringToArrayList(String[] arr){
		ArrayList<Integer> cart_id = new ArrayList<>();
		
		for(String eachArr : arr) {
			cart_id.add(Integer.parseInt(eachArr));
		}
		System.out.println("Payment DAO : 전달받은 장바구니 번호 변환 완료 ");
		return cart_id;
	} // 1. 끝
	
	
	// 2. 장바구니번호 ArrayList<Integer> 로 장바구니 정보 조회하기
	public ArrayList getCart(ArrayList<Integer> cart_id) {
		ArrayList cartList = new ArrayList();
		
		try {con=getCon();
			
			sql = "select * from cart where cart_id = ?";
			pstmt = con.prepareStatement(sql);
			
			for(int cartId : cart_id) {
				pstmt.setInt(1, cartId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					CartDTO cartDto = new CartDTO();
					
					cartDto.setCart_id(rs.getInt("cart_id"));
					cartDto.setMember_id(rs.getInt("member_id"));
					cartDto.setProduct_id(rs.getInt("product_id"));
					cartDto.setQuantity(rs.getInt("quantity"));
					cartDto.setAddress(rs.getString("address"));
					cartDto.setStopdate_id(rs.getInt("stopdate_id"));
					
					cartList.add(cartDto);
				}
			}
			System.out.println("Payment DAO : 장바구니 목록 조회 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseDB();
		}
		return cartList;
	} // 2. 끝
	
	//3. 상품정보 가져오기
	public ArrayList getProduct(ArrayList<Integer> cart_id) {
		ArrayList productList = new ArrayList();
		
		try {con=getCon();
			
			sql = "select * from product p where product_id = (select product_id from cart where cart_id = ?)";
			pstmt = con.prepareStatement(sql);
			
			for(int cartId : cart_id) {
				pstmt.setInt(1, cartId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ProductDTO ProDto = new ProductDTO ();
					
					ProDto.setProduct_id(rs.getInt("product_id"));
					ProDto.setName(rs.getString("name"));
					ProDto.setPrice(rs.getInt("price"));
					ProDto.setImage(rs.getString("image"));
					
					productList.add(ProDto);
				}
			}
			System.out.println("Payment DAO : 상품 정보 조회 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseDB();
		}
		return productList;
		}//3.끝
	
	//4. 카트랑 프로덕트 합치기
	public ArrayList getPurchase(ArrayList<Integer> cart_id) {
		ArrayList purchasetList = new ArrayList();
		
		try {con=getCon();
			
			sql = "select * from cart c join product p on c.product_id = p.product_id where c.cart_id =?";
			pstmt = con.prepareStatement(sql);
			
			for(int cartId : cart_id) {
				pstmt.setInt(1, cartId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					PaymentDTO payDto = new PaymentDTO();
					
					payDto.setCart_id(rs.getInt("cart_id"));
					payDto.setMember_id(rs.getInt("member_id"));
					payDto.setProduct_id(rs.getInt("product_id"));
					payDto.setQuantity(rs.getInt("quantity"));
					payDto.setAddress(rs.getString("address"));
					payDto.setStopdate_id(rs.getInt("stopdate_id"));
					payDto.setName(rs.getString("name"));
					payDto.setPrice(rs.getInt("price"));
					payDto.setImage(rs.getString("image"));
					
					purchasetList.add(payDto);
				}
			}
			System.out.println("Payment DAO : 상품 정보 조회 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseDB();
		}
		return purchasetList;
		}//4.끝
	
	//=================================================================================
	//1. 
	public ArrayList stringToArrayList(String[] purchase,String[] member,
						String[] product,String[] quantity,String[] address){
		ArrayList<PurchaseDTO> purchaseList = new ArrayList<>();
		for (int i = 0; i < purchase.length; i++) {
		    PurchaseDTO dto = new PurchaseDTO();
		    dto.setPurchase_id(Integer.parseInt(purchase[i]));
		    dto.setMember_id(Integer.parseInt(member[i]));
		    dto.setProduct_id(Integer.parseInt(product[i]));
		    dto.setQuantity(Integer.parseInt(quantity[i]));
		    dto.setAddress(address[i]);
		    purchaseList.add(dto);
		}
		System.out.println("Payment DAO : 전달받은 장바구니 번호 변환 완료 ");
		return purchaseList;
	} // 
	
	
	
	
}//dao end
