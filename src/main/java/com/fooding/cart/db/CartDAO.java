package com.fooding.cart.db;

import java.sql.Connection;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.*;


public class CartDAO {

	// 공통변수
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";

		// 공통메서드(기능)

		/////////////////////////// 디비 연결 메서드////////////////////////////////
		private Connection getCon() throws Exception {
			
			// Connection Pool을 사용한 디비 연결
			
			// 프로젝트의 정보를 확인(JNDI)
			Context initCTX = new InitialContext();
			// 프로젝트안에 작성된 디비 연결정보(context.xml)를 불러오기
			DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/mvc");
			// 디비 연결 수행
			con = ds.getConnection();
			System.out.println(" DAO : 디비연결 성공! (커넥션풀) ");
			System.out.println(" DAO : "+con);		

			return con;
		}
		/////////////////////////// 디비 연결 메서드////////////////////////////////

		/////////////////////////// 디비 연결(자원) 해제 메서드////////////////////////
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
		/////////////////////////// 디비 연결(자원) 해제 메서드/////////////////////////
		
		// 회원번호 가져오는 메서드 getMemberId(String id)
		public int getMemberId(String id) {
			int memberId  =0;
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 작성 & pstmt 객체
				sql ="select member_id from member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				if(rs.next()) {
					memberId = rs.getInt("member_id");
				}
				
				System.out.println("DAO : 회원번호 가져오기 완료 :"+memberId);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return memberId;
		}
		// 회원번호 가져오는 메서드 getMemberId(String id)
		

		// 삭제할 운행일 불러오는 메서드 deleteDate(String memberId)
		public ArrayList deleteDate(int memberId) {
			
			ArrayList<CartDTO> deleteCartList = new ArrayList<CartDTO>();
			try {
				con = getCon();
				sql="select cart_id from cart "
						+ "where member_id =? "
						+ "and date < now() ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, memberId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CartDTO dto = new CartDTO();
					
					dto.setCart_id(rs.getInt("cart_id"));
					
					deleteCartList.add(dto);
				}
				System.out.println("DAO : 삭제할 운행일 리스트 불러오기! ");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			finally {
				CloseDB();
			}
			return deleteCartList;
		}
		// 삭제할 운행일 불러오는 메서드 deleteDate(String memberId)
		
		// 장바구니 목록 가져오는 메서드 getCartList(int cartId)
		public ArrayList getCartList(int memberId) {
			ArrayList<CartListDTO> cartList = new ArrayList<CartListDTO>();
			
			
			try {
				con = getCon();
				
				sql="select C.member_id, C.cart_id, C.date, P.image, P.name, P.price, C.quantity "
						+ "from cart C "
						+ "join product P "
						+ "on C.product_id = P.product_id "
						+ "where C.member_id =? "
						+ "order by C.date ASC, C.cart_id ASC ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, memberId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CartListDTO listDto = new CartListDTO();
					
					listDto.setMember_id(memberId);
					listDto.setCart_id(rs.getInt("cart_id"));
					listDto.setDate(rs.getDate("date"));
					listDto.setImage(rs.getString("image"));
					listDto.setName(rs.getString("name"));
					listDto.setPrice(rs.getInt("price"));
					listDto.setQuantity(rs.getInt("quantity"));
					
					cartList.add(listDto);
				}
				System.out.println("DAO: 장바구니목록조회 완료");
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			return cartList;
		}
		// 장바구니 목록 가져오는 메서드 getCartList(int memberId)
		
		// 상품수량 수정해주는 메서드 updateQuantity(int memberId,List cartList)
		public int updateQuantity(int memberId,ArrayList<CartDTO> cartList) {
			int result = 0; 
			try {
				con = getCon();
				
				sql = "update cart "
						+ "set quantity = ? "
						+ "where member_id =? "
						+ "and cart_id =? ";
				
				pstmt = con.prepareStatement(sql);
				
				for(CartDTO cart : cartList) {
					pstmt.setInt(1, cart.getQuantity());
					pstmt.setInt(2, memberId);
					pstmt.setInt(3, cart.getCart_id());
					
					result += 
					pstmt.executeUpdate();
					
				}
				
				
				System.out.println(" DAO : 장바구니 수량 수정완료! ("+result+")");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			
			
			return result;
		}
		
		// 상품수량 수정해주는 메서드 updateQuantity
		
		// 장바구니 삭제 메서드 deleteCart(int memberId,List cartList)
		public void deleteCart(int memberId,ArrayList<CartDTO> cartList) {
			int result =0;
			try {
				con = getCon();
				sql ="delete from cart "
						+ "where member_id =? "
						+ "and cart_id =? ";
				pstmt = con.prepareStatement(sql);
				
				
				for(CartDTO cart : cartList) {
					pstmt.setInt(1, memberId);
					pstmt.setInt(2, cart.getCart_id());
					result += pstmt.executeUpdate();
				}
				
				System.out.println(" DAO : 장바구니 삭제완료! ("+result+")");
			} catch (Exception e) {
				
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			
			
			
			
		}
		// 장바구니 삭제 메서드 deleteCart(int memberId,List cartList)
}
