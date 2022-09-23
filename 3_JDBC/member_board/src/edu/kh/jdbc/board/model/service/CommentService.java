package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.vo.Comment;

public class CommentService {
	
	private CommentDAO dao = new CommentDAO();

	/** 댓글 등록 서비스
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Comment comment) throws Exception {

		
		return 0;
	}
	
	
	
}
