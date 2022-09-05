package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 1단계 : JDBC 객체 참조 변수 생성(java.sql)
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			// 2단계 : 참조변수에 알맞은 객체 대입
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1521"; 
			String sid = ":XE"; 
			String user = "kh_bdh";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			
			
			System.out.println("<입력 받은 급여보다 많이 받는(초과) 직원만 조회>");
			System.out.print("급여 입력 : ");
			
			int input = sc.nextInt();
			
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE WHERE SALARY > " + input;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			
			// 3단계 : SQL을 수행해서 반환 받은 결과(ResultSet)를
			//			한 행씩 접근해서 컬럼 값 얻어오기
			while(rs.next()) {
				
				String empId = rs.getString("EMP_ID"); 
				String empName = rs.getString("EMP_NAME"); 
				int salary = rs.getInt("SALARY"); 
				
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d\n",
								empId, empName, salary);
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			
			// 4단계 : 사용한 JDBC 객체 자원 반환(close())
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
