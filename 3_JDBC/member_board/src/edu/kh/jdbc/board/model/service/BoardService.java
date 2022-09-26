package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.board.model.vo.Comment;

public class BoardService {

	// BoardDAO 객체 생성
	private BoardDAO dao = new BoardDAO();
	
	// CommentDAO 객체 생성 -> 상세 조회 시 댓글 목록 조회용도로 사용
	private CommentDAO cDao = new CommentDAO();

	
	
	/** 게시글 목록 조회 서비스
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllBoard() throws Exception{
		Connection conn = getConnection(); // 커넥션 생성
		
		// DAO 메서드 호출 후 결과 반환 받기
		List<Board> boardList = dao.selectAllBoard(conn);
		
		close(conn); // 커넥션 반환
		
		return boardList; // 조회 결과 반환
	}



	/** 게시글 상세 조회 서비스
	 * @param boardNo
	 * @param memberNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		
		// 1. 게시글 상세 조회 DAO 호출
		Board board = dao.selectBoard(conn, boardNo);
		// -> 조회 결과가 없으면 null, 있으면 null 아님
		
		
		if(board != null) { // 게시글이 존재 한다면
			
			// 2. 댓글 목록 조회 DAO 호출
			List<Comment> commentList = cDao.selectCommentList(conn, boardNo);
			
			// 조회된 댓글 목록을 board에 저장
			board.setCommentList(commentList);
			
			
			// 3. 조회 수 증가
			// 단, 로그인한 회원과 게시글 작성자가 다를 경우에만 증가
			if( memberNo != board.getMemberNo() ) {
				int result = dao.increaseReadCount(conn, boardNo);
				
				// 트랜잭션 제어  
				if(result > 0) {
					commit(conn);
					
					// 미리 조회된 board의 조회 수를 
					// 증가된 DB의 조회 수와 동일 한 값을 가지도록 동기화
					board.setReadCount( board.getReadCount() + 1 );
				}
				else			rollback(conn);
			}
		}
		
		close(conn);
		
		return board;
	}


	/** 게시글 수정 서비스
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Board board) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateBoard(conn, board);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 게시글 삭제 서비스
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn, boardNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 게시글 등록 서비스
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Board board) throws Exception{
		
		Connection conn = getConnection();
		
		// 게시글 번호 생성 dao 호출
		// 왜? 동시에 여러 사람이 게시글을 등록하면
		// 시퀀스가 한번에 증가하여 CURRVAL 구문을 이용하면 문제가 발생함
		// -> 게시글 등록 서비스를 호출한 순서대로
		//    미리 게시글 번호를 생성해서 얻어온 다음 이를 이용해 insert 진행
		int boardNo = dao.nextBoardNo(conn);
		
		board.setBoardNo(boardNo); // 얻어온 다음 번호를 board에 세팅
		// -> 다음 게시글 번호, 제목, 내용, 회원 번호
		
		int result = dao.insertBaord(conn, board);
		
		if(result > 0) {
			commit(conn);
			
			result = boardNo;
			// INSERT 성공 시 생성된 게시글 번호(boardNo)를 결과로 반환
		}
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 게시글 검색
	 * @param condition
	 * @param query
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(int condition, String query) throws Exception{
		Connection conn = getConnection();
		
		List<Board> boardList = dao.searchBoard(conn, condition, query);
		
		close(conn);
		
		return boardList;
	}
	
	
	
	
	
	
}
