package edu.kh.jdbc.main.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;


// DAO(Data Access Object) : DB 연결용 객체
public class MainDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	// Map<String, String> 제한, XML 파일 읽고/쓰고 특화
	
	
	// 기본 생성자
	public MainDAO() {
		
		try {
			prop = new Properties(); // Properties 객체 생성
			
			prop.loadFromXML(new FileInputStream("main-query.xml"));
			// main-query.xml 파일의 내용을 읽어와 Properties 객체에 저장
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	/** 아이디 중복 검사 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String memberId) throws Exception{

		// 1. 결과 저장용 변수 
		int result = 0;
		
		try {
			// 2. SQL 얻어오기
			String sql = prop.getProperty("idDupCheck");
			
			// 3. PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 알맞은 값 세팅
			pstmt.setString(1, memberId);
			
			// 5. SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과 옮겨 담기
			// 1행 조회 -> if
			// N행 조회 -> while
			if(rs.next()) {
				// result = rs.getInt("COUNT(*)"); // 컬럼명
				result = rs.getInt(1); // 컬럼 순서
			}
			
		}finally {
			// 7. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		
		// 8. 결과 반환
		return result;
	}


	
	/** 회원 가입 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception{
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
}
