package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	public static void main(String[] args) {
		
		// JDBC : Java에서 DB에 연결(접근)할 수 있게 해주는 Java Programming API
		//												(Java에 기본 내장된 인터페이스)
		// java.sql 패키지에서 제공
		
		// * JDBC를 이용한 애플리케이션을 만들 때 필요한 것
		// 1. Java의 JDBC 관련 인터페이스
		// 2. DBMS(Oracle)
		// 3. Oracle에서 Java 애플리케이션과 연결할 때 사용할
		//    JDBC를 상속 받아 구현한 클래스 모음(ojdbc11.jar 라이브러리)
		//		-> OracleDriver.class (JDBC 드라이버) 이용
		
		
		// 1단계 : JDBC 객체 참조 변수 선언 (java.sql패키지의 인터페이스)
		
		Connection conn = null;
		// DB 연결 정보를 담은 객체
		// -> DBMS 타입, 이름, IP, Port, 계정명, 비밀번호 저장 
		// -> DBeaver의 계정 접속 방법과 유사함
		// * Java와 DB 사이를 연결해주는 통로(Stream과 유사함)
		
		
		Statement stmt = null;
		// Connection을 통해
		// SQL 문을 DB에 전달하여 실행하고 
		// 생성된 결과(ResultSet, 성공한 행의 개수) 를 반환(Java)하는 데 사용되는 객체
		
		
		ResultSet rs = null;
		// SELECT 질의 성공 시 반환되는데 
		// 조회 결과 집합을 나타내는 객체
		
		
		try {
			// 2단계 : 참조 변수에 알맞은 객체 대입
			
			// 1. DB 연결에 필요한 Oracle JDBC Driver 메모리에 로드하기
													// -> 객체로 만들어 두기
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// -> () 안에 작성된 클래스의 객체를 반환
			// -> 메모리에 객체가 생성되어지고 JDBC 필요 시 알아서 참조해서 사용
			// --> 생략해도 자동으로 메모리 로드가 진행됨(명시적으로 작성하는걸 권장)
			
			
			// 2. 연결 정보를 담은 Connection을 생성
			//	-> DriverManager객체를 이용해서 Connection 객체를 만들어 얻어옴!
			
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버의 종류
			
			String ip = "localhost"; // DB 서버 컴퓨터 IP
			// localhost  ==  127.0.0.1  (loop back ip)
			// 115.90.212.22 (서버컴)
			
			String port = ":1521"; // 포트번호
			// 1521(기본값)
			// 9000 (서버컴)
				
			String sid = ":XE"; // DB 이름
			
			String user = "kh_bdh";
			
			String pw = "kh1234";
			
			
			// DriverManager : 
			// 메모리에 로드된 JDBC 드라이버를 이용해서
			// Connection 객체를 만드는 역할
			
			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			// SQLException : DB 관련 최상위 예외
			
			// 중간 확인
			System.out.println(conn);
			// oracle.jdbc.driver.T4Connection@~~~~
			
			
			
			
			
			// 3단계 : SQL을 수행해서 결과 반환 받기
			
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 경로가 잘못 작성되었습니다.");
		
		} catch(SQLException e) {
			e.printStackTrace();
			
			
		} finally {
			// 4단계 : 사용한 JDBC 객체 자원 반환( close() )
			
			
		}
		
		
		
		
	}
	
	
	
}



