package com.fooding.payment.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
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
	
	//0-1. getCartID(ArrayList<CartDTO> arr)
	public ArrayList<Integer> cartList(ArrayList<CartDTO> arr){
		ArrayList<Integer> cartIdList = new ArrayList<Integer>();

		for (CartDTO cartDTO : arr) {
		    int cartId = cartDTO.getCart_id(); 
		    cartIdList.add(cartId);
		}
		System.out.println("Payment DAO : 장바구니 번호 CartDTO");
		return cartIdList;
	}
	
	
	
	// 1. String[] 으로 오는 장바구니 번호 ArrayList<Integer>로 변환하기
	public ArrayList stringToArrayList(String[] arr){
		ArrayList cart_id = new ArrayList<>();
		
		for(String eachArr : arr) {
			cart_id.add(Integer.parseInt(eachArr));
		}
		System.out.println("Payment DAO : 전달받은 장바구니 번호 변환 완료 ");
		return cart_id;
	} // 1. 끝
	
	
	
	
//	// 2. 장바구니번호 ArrayList<Integer> 로 장바구니 정보 조회하기
//	public ArrayList getCart(ArrayList<Integer> cart_id) {
//		ArrayList cartList = new ArrayList();
//		
//		try {con=getCon();
//			
//			sql = "select * from cart where cart_id = ?";
//			pstmt = con.prepareStatement(sql);
//			
//			for(int cartId : cart_id) {
//				pstmt.setInt(1, cartId);
//				rs = pstmt.executeQuery();
//				while (rs.next()) {
//					CartDTO cartDto = new CartDTO();
//					
//					cartDto.setCart_id(rs.getInt("cart_id"));
//					cartDto.setMember_id(rs.getInt("member_id"));
//					cartDto.setProduct_id(rs.getInt("product_id"));
//					cartDto.setQuantity(rs.getInt("quantity"));
//					cartDto.setAddress(rs.getString("address"));
//					cartDto.setStopdate_id(rs.getInt("stopdate_id"));
//					
//					cartList.add(cartDto);
//				}
//			}
//			System.out.println("Payment DAO : 장바구니 목록 조회 완료!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			CloseDB();
//		}
//		return cartList;
//	} // 2. 끝
//	
//	//3. 상품정보 가져오기
//	public ArrayList getProduct(ArrayList<Integer> cart_id) {
//		ArrayList productList = new ArrayList();
//		
//		try {con=getCon();
//			
//			sql = "select * from product p where product_id = (select product_id from cart where cart_id = ?)";
//			pstmt = con.prepareStatement(sql);
//			
//			for(int cartId : cart_id) {
//				pstmt.setInt(1, cartId);
//				rs = pstmt.executeQuery();
//				while (rs.next()) {
//					ProductDTO ProDto = new ProductDTO ();
//					
//					ProDto.setProduct_id(rs.getInt("product_id"));
//					ProDto.setName(rs.getString("name"));
//					ProDto.setPrice(rs.getInt("price"));
//					ProDto.setImage(rs.getString("image"));
//					
//					productList.add(ProDto);
//				}
//			}
//			System.out.println("Payment DAO : 상품 정보 조회 완료!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			CloseDB();
//		}
//		return productList;
//		}//3.끝
	
//	//4. 카트랑 프로덕트 합치기
//	public ArrayList getPurchase(ArrayList<Integer> cart_id) {
//		ArrayList purchasetList = new ArrayList();
//		
//		try {con=getCon();
//			
//			sql = "select * from cart c join product p on c.product_id = p.product_id where c.cart_id =?";
//			pstmt = con.prepareStatement(sql);
//			
//			for(int cartId : cart_id) {
//				pstmt.setInt(1, cartId);
//				rs = pstmt.executeQuery();
//				while (rs.next()) {
//					PaymentDTO payDto = new PaymentDTO();
//					
//					payDto.setCart_id(rs.getInt("cart_id"));
//					payDto.setMember_id(rs.getInt("member_id"));
//					payDto.setProduct_id(rs.getInt("product_id"));
//					payDto.setQuantity(rs.getInt("quantity"));
//					payDto.setAddress(rs.getString("address"));
//					payDto.setStopdate_id(rs.getInt("stopdate_id"));
//					payDto.setName(rs.getString("name"));
//					payDto.setPrice(rs.getInt("price"));
//					payDto.setImage(rs.getString("image"));
//					
//					purchasetList.add(payDto);
//				}
//			}
//			System.out.println("Payment DAO : (cart+product)상품 정보 조회 완료!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			CloseDB();
//		}
//		return purchasetList;
//		}//4.끝
	//4. 카트랑 프로덕트 합치기
	public ArrayList getPurchase(ArrayList<Integer> cart_id) {
		ArrayList purchasetList = new ArrayList();
		
		try {con=getCon();
		
		sql = "SELECT *, f.name As foodTruckName "
				+ "FROM cart c "
				+ "JOIN product p ON c.product_id = p.product_id "
				+ "JOIN foodtruck f ON f.foodtruck_id = p.foodtruck_id "
				+ "WHERE c.cart_id = ?";
		pstmt = con.prepareStatement(sql);
		
		for(int cartId : cart_id) {
			pstmt.setInt(1, cartId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PaymentDTO payDto = new PaymentDTO();
				// 카트테이블 전부
				payDto.setCart_id(rs.getInt("cart_id"));
				payDto.setMember_id(rs.getInt("member_id"));
				payDto.setProduct_id(rs.getInt("product_id"));
				payDto.setQuantity(rs.getInt("quantity"));
				payDto.setDate(rs.getString("date"));
				payDto.setAddress(rs.getString("address"));
				payDto.setStoptime(rs.getString("stoptime"));
				// 상품 이름과 가격, 이미지 주소
				payDto.setName(rs.getString("name"));
				payDto.setPrice(rs.getInt("price"));
				payDto.setImage(rs.getString("image"));
				// 푸드트럭 이름
				payDto.setFoodtruckName(rs.getString("foodTruckName"));
				
				purchasetList.add(payDto);
			}
		}
		System.out.println("Payment DAO : (cart+product+time)상품 정보 조회 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseDB();
		}
		return purchasetList;
	}//4.끝
	
	// 5. 아이디정보로 회원정보 조회하기 getMember()
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		// 1.2.  디비연결
		try {
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select * from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 4. sql 실행 
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리 (DB에 저장된 정보(rs)를 DTO로 저장)
			if(rs.next()) {
				dto = new MemberDTO();
				//rs => dto 저장
				dto.setMember_id(rs.getInt("member_id"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getTimestamp("regdate"));
			}
			
			System.out.println("DAO : 회원정보 조회 완료!"+dto.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		
		return dto;
	}
	
	// 이름 합치기 combinedName(PurchaseList)
	public String combinedName(ArrayList<PaymentDTO> purchaseList) {
	    StringBuilder nameString = new StringBuilder();
	    int nameCount = 0;

	    for (PaymentDTO payDTO : purchaseList) {
	        if (nameCount == 0) {
	            nameString.append(payDTO.getName());
	        } else if (nameCount == 1) {
	            nameString.append(", ").append(payDTO.getName());
	        } else {
	            break; // 2개 이상의 상품이 있는 경우 루프를 종료
	        }
	        nameCount++;
	    }

	    if (nameCount > 1) {
	        nameString.append(" 외 ").append(nameCount);
	    }

	    String result = nameString.toString();
	    System.out.println("DAO : 이름합치기 완료 : " + result);
	    return result;
	}
	

	//AFter=================================================================================
	//1. 
	public ArrayList stringToArrayList(String[] purchase,String[] member,
						String[] product,String[] quantity,String[] address){
		ArrayList<PurchaseDTO> purchaseList = new ArrayList<>();
		for (int i = 0; i < purchase.length; i++) {
		    PurchaseDTO dto = new PurchaseDTO();
		    dto.setPurchaseid(Integer.parseInt(purchase[i]));
		    dto.setMember_id(Integer.parseInt(member[i]));
		    dto.setProduct_id(Integer.parseInt(product[i]));
		    dto.setQuantity(Integer.parseInt(quantity[i]));
		    dto.setAddress(address[i]);
		    purchaseList.add(dto);
		}
		System.out.println("Payment DAO : 전달받은 String[] 하나로 합치기 완료 ");
		return purchaseList;
	} // 1.끝 
	
	//2. insertPurchase(ArrayList arr) 만들기
	//		=> DB업데이트
	public void insertPurchase(ArrayList<PurchaseDTO> arr) {
		   try {
		      con = getCon();
		      //쿼리에서 date 삭제하기
		      sql = "INSERT INTO purchase (purchaseid, member_id, product_id, quantity, orderdate, date, address, stoptime) VALUES (?,?,?,?,CURRENT_TIMESTAMP,?,?,?)";
		      pstmt = con.prepareStatement(sql);

		      for (PurchaseDTO dto : arr) {
		         pstmt.setInt(1, dto.getPurchaseid());
		         pstmt.setInt(2, dto.getMember_id());
		         pstmt.setInt(3, dto.getProduct_id());
		         pstmt.setInt(4, dto.getQuantity());
		         pstmt.setString(5, dto.getDate());//date
		         pstmt.setString(6, dto.getAddress());//address
		         pstmt.setString(7, dto.getStoptime());//stoptime

		         pstmt.executeUpdate();
		      }
		      System.out.println(" DAO : 데이터 입력 완");
		   } catch (Exception e) {
		      e.printStackTrace();
		   } finally {
		      CloseDB();
		   }
		}// 2. 끝
	
	//3. deleteCart(ArrayList arr)
	public int deleteMember(ArrayList<Integer> arr) {
		int result=-1;  // -1, 0, 1
		ArrayList cartIds = new ArrayList<>();
		
		try {
			// select 해서 지워야할 데이터 찾기
			con=getCon();
			for (int dto : arr) {
				sql = "DELETE FROM cart WHERE cart_id = ?";
				try(PreparedStatement pstmt = con.prepareStatement(sql)){
					pstmt.setInt(1, dto);
					 int deletedRows = pstmt.executeUpdate();
	                    if (deletedRows > 0) {
	                    	result = -1;
	                        System.out.println("cart_id " + dto + "의 레코드를 삭제했습니다.");
	                    } else {
	                    	result=1;
	                        System.out.println("cart_id " + dto + "를 찾을 수 없습니다.");
	                    }
	                }
	            }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		System.out.println("DAO : 회원정보 삭제("+result+")");

		return result;
}
	// 글 정보 목록을 가져오는 메서드 - getBoardList(int startRow, int pageSize)
	public PurchaseDTO getBoard() {
		PurchaseDTO dto = null;
		try {
			con = getCon();
			
			sql="select * from purchase";
			pstmt=con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new PurchaseDTO();
				
				dto.setDetail_id(rs.getInt("detail_id"));
				dto.setMember_id(rs.getInt("member_id"));
				dto.setProduct_id(rs.getInt("product_id"));
				dto.setPurchaseid(rs.getInt("purchaseid"));
				dto.setQuantity(rs.getInt("quantity"));
				dto.setAddress(rs.getString("address"));
				
			} // if
			
			System.out.println("DAO : 글 정보 조회 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return dto;
	}
		
		
		
	
}//dao end
