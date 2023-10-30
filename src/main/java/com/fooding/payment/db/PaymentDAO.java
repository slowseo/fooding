package com.fooding.payment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.member.db.MemberDTO;

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
		DataSource ds = (DataSource)initCTX.lookup(" "); // 불러오기
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
	
	//============= 장바구니 정보 조회 메서드(== Db정보 dto에 입력하기) =======
	public ArrayList getCart() {
		ArrayList cartList = new ArrayList();
		
		try {
			con=getCon();
			
			sql = "select * from cart";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// rs -> dto -> list
				CartDTO dto = new CartDTO ();
				
				dto.setCart_id(rs.getInt("cart_id"));
				dto.setMember_id(rs.getInt("member_id"));
				
				cartList.add(dto);
				
				System.out.println(" DAO : cart 조회 완료!");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseDB();
		}
		return cartList;
	}
	//============= 장바구니 정보 조회 메서드(== Db정보 dto에 입력하기) =======
	public ArrayList getDetail() {
		ArrayList detailList = new ArrayList();
		
		try {
			con=getCon();
			
			sql = "select * from cart_detail";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// rs -> dto -> list
				cartDetailDTO dto = new cartDetailDTO();
				
				dto.setCartdetail_id(rs.getInt("cartdetail_id"));
				dto.setCart_id(rs.getInt("cart_id"));
				dto.setProduct_id(rs.getInt("product_id"));
				dto.setQuantity(rs.getInt("quantity"));
				dto.setAddress(rs.getString("address"));
				detailList.add(dto);
				
				System.out.println(" DAO : cart 조회 완료!");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseDB();
		}
		return detailList;
	}
	
	
	
	
	
	
	
}//dao end
