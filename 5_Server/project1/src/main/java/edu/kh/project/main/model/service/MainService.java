package edu.kh.project.main.model.service;

import static edu.kh.project.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Map;

import edu.kh.project.main.model.dao.MainDAO;

public class MainService {
	
	private MainDAO dao = new MainDAO();

	/** 게시판 종류 조회 서비스
	 * @return boardTypeMap
	 * @throws Exception
	 */
	public Map<Integer, String> selectBoardType() throws Exception{
		
		Connection conn = getConnection();
		
		Map<Integer, String> boardTypeMap = dao.selectBoardType(conn);
		
		close(conn);
		
		return boardTypeMap;
	}
	
	

	
	
	
	
	
}
