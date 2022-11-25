package edu.kh.project.board.model.service;

import java.util.List;

import edu.kh.project.board.model.vo.Comment;

public interface CommentService {

	/** 댓글 목록 조회
	 * @param boardNo
	 * @return rList
	 */
	List<Comment> selectCommentList(int boardNo);

	/** 댓글 등록
	 * @param comment
	 * @return result
	 */
	int insertComment(Comment comment);

	/** 댓글 삭제
	 * @param commentNo
	 * @return result
	 */
	int deleteComment(int commentNo);

	/** 댓글 수정
	 * @param comment
	 * @return result
	 */
	int updateComment(Comment comment);

	
	
	
	
}
