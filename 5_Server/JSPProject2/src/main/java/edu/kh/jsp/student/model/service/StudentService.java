package edu.kh.jsp.student.model.service;

// JDBCTemplate의 static 필드/메서드 호출 시 클래스명 생략
import static edu.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jsp.student.model.dao.StudentDAO;
import edu.kh.jsp.student.model.vo.Student;

public class StudentService {

	private StudentDAO dao = new StudentDAO();

	// alt + shift + j : 클래스/메서드 설명용 주석 (HTML 작성법)
	/** 학생 전체 조회 서비스
	 * @return stdList
	 * @throws Exception
	 */
	public List<Student> selectAll() throws Exception{
		
		// Connection 생성
		Connection conn = getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받기
		List<Student> stdList = dao.selectAll(conn);
		
 		// 트랜잭션 처리(DML인 경우에만 진행)
		
		// Connection 객체 반환
		close(conn);
		
		// 결과 반환
		return stdList;
	}

	
	public List<Student> selectDepartment(String inputDept) throws Exception{
		// Connection 생성
		Connection conn = getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받기
		List<Student> stdList = dao.selectDepartment(conn, inputDept);
		
		// Connection 객체 반환
		close(conn);
		
		// 결과 반환
		return stdList;
	}
	
	
	
	
	
	
}

