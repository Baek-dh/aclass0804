package edu.kh.project.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.vo.Comment;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 댓글 목록 조회
	 * @param boardNo
	 * @return rList
	 */
	public List<Comment> selectCommentList(int boardNo) {
		return sqlSession.selectList("boardMapper.selectCommentList", boardNo);
	}

	/** 댓글 등록
	 * @param comment
	 * @return result
	 */
	public int insertComment(Comment comment) {
		return sqlSession.insert("commentMapper.insertComment", comment);
	}

	/** 댓글 삭제
	 * @param commentNo
	 * @return result
	 */
	public int deleteComment(int commentNo) {
		return sqlSession.update("commentMapper.deleteComment", commentNo);
	}

	/** 댓글 수정
	 * @param comment
	 * @return result
	 */
	public int updateComment(Comment comment) {
		return sqlSession.update("commentMapper.updateComment", comment);
	}
	
	
	
	
	
	
}
