package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Comment;

public class CommentDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public CommentDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("comment-query.xml"));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 댓글 목록 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return commentList
	 * @throws Exception
	 */
	public List<Comment> selectCommentList(Connection conn, int boardNo) throws Exception{
		
		List<Comment> commentList = new ArrayList<>();
		
		try{
			String sql = prop.getProperty("selectCommentList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comment comment = new Comment();
				
				comment.setCommentNo(rs.getInt(1));
				comment.setCommentContent(rs.getString(2));
				comment.setMemberNo(rs.getInt(3));
				comment.setMemberName(rs.getString(4));
				comment.setCreateDate(rs.getString(5));
				
				commentList.add(comment);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return commentList;
	}
 
	/** 댓글 등록 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Connection conn, Comment comment) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertComment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getMemberNo());
			pstmt.setInt(3, comment.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
 
	/** 댓글 수정 DAO
	 * @param conn
	 * @param commentNo
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Connection conn, int commentNo, String content) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateComment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, content);
			pstmt.setInt(2, commentNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	/** 댓글 삭제 DAO
	 * @param conn
	 * @param commentNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteComment(Connection conn, int commentNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteComment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	
	
	
	
	
	
	
	
}
