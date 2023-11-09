package com.fooding.orderDetail.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fooding.payment.db.MemberDTO;
import com.fooding.payment.db.PurchaseDTO;

public class orderDetailDAO {


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
	//0. 아이디로 회원번호 얻어오기 getMemberId(String id)
	public int getMemberId(String id) {
		int MemberId = 0;
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
				MemberDTO dto = new MemberDTO();
				//rs => dto 저장
				dto.setMember_id(rs.getInt("member_id"));
				
				MemberId = dto.getMember_id();;
			}
			
			System.out.println("DAO : 회원번호 조회 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		
		return MemberId;
	}//0. 끝
	
//1. 글 갯수 계산 메서드 - getPurchaseCount
public int getPurchaseCount() {
		
		int result = 0;
	
		//1. 드라이버 로드
		try {
				con = getCon();
			//3. sql 작성(select) & pstmt객체
			sql ="select count(*) from purchase ";
			pstmt = con.prepareStatement(sql);
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리  - 개수를 저장
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println("DAO : 개수 "+result+"개");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return result;
	
}// 1. 글갯수계산 끝

//2. 주문내역 정보 전부 가져오기 getPurchaseList(int startRow, int pageSize, int id)
public ArrayList getPurchaseList(int startRow, int pageSize, int id){
	// 글 정보를 저장하는 배열
	ArrayList purchaseList = new ArrayList();
	try {	
		con = getCon();
	//---------------위의 과정은 반복되기 때문에 합친다-
	//3.sql 작성(select) & pstmt 객체
		sql = "SELECT *, f.name As foodTruckName "
				+ "FROM purchase c "
				+ "JOIN product p ON c.product_id = p.product_id "
				+ "JOIN foodtruck f ON f.foodtruck_id = p.foodtruck_id "
				+ "WHERE c.member_id = ? "
				+ "order by detail_id desc limit ?,?";
		pstmt = con.prepareStatement(sql);				
		
		//????
		pstmt.setInt(1, id);
		pstmt.setInt(2, startRow-1); // 시작행 번호
		pstmt.setInt(3, pageSize); // 페이지 사이즈(=가져올 글 갯수)
	// 4. sql실행
		rs = pstmt.executeQuery();
	// 5. 데이터처리
		// 글 정보 전부 가져오기
		// BoardBean 객체 여러개 => ArrayList 저장
		while(rs.next()) {
			// 글 하나의 정보 => BoardBean저장 
			OrderDetailDTO pdto = new OrderDetailDTO();
			pdto.setDetail_id(rs.getInt("detail_id"));
			pdto.setPurchaseid(rs.getInt("purchaseid"));
			pdto.setProduct_id(rs.getInt("product_id"));
			pdto.setMember_id(rs.getInt("member_id"));
			pdto.setQuantity(rs.getInt("quantity"));
			pdto.setOrderdate(rs.getTimestamp("orderdate"));
			pdto.setDate(rs.getString("date"));
			pdto.setAddress(rs.getString("address"));
			pdto.setStoptime(rs.getString("stoptime"));
			
			pdto.setName(rs.getString("name"));
			pdto.setPrice(rs.getInt("price"));
			pdto.setImage(rs.getString("image"));
			
			pdto.setFoodtruckName(rs.getString("foodtruckName"));
			
			// 글 하나의 정보를 배열의 한칸에 저장
			purchaseList.add(pdto);
			
		}//while
		System.out.println("DAO : 게시판 글 목록 조회 성공!");
		System.out.println("DAO : " + purchaseList.size());
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		CloseDB();
	}
	return purchaseList;
	}








}//DAO 끝
