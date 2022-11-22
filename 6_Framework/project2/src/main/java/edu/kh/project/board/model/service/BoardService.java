package edu.kh.project.board.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.vo.Board;

public interface BoardService {

	/** 게시판 이름 목록 조회
	 * @return boardTypeList
	 */
	List<Map<String, Object>> selectBoardType();

	/** 특정 게시판 목록 조회 + 페이징 처리 계산
	 * @param boardCode
	 * @param cp
	 * @return map
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp);

	/** 게시글 상세조회
	 * @param boardNo
	 * @return board
	 */
	Board selectBoardDetail(int boardNo);

	/** 조회 수 증가
	 * @param boardNo
	 * @return result
	 */
	int updateReadCount(int boardNo);

	/** 좋아요 여부 체크
	 * @param map
	 * @return result
	 */
	int boardLikeCheck(Map<String, Object> map);

	/** 좋아요 수 증가(INSERT)
	 * @param paramMap
	 * @return result
	 */
	int boardLikeUp(Map<String, Object> paramMap);

	/** 좋아요 수 감소(DELETE)
	 * @param paramMap
	 * @return result
	 */
	int boardLikeDown(Map<String, Object> paramMap);

	/** 게시글 삭제
	 * @param boardNo
	 * @return result
	 */
	int boardDelete(int boardNo);

	/** 게시글 삽입
	 * @param board
	 * @param imageList
	 * @param webPath
	 * @param folderPath
	 * @return boardNo
	 */
	int boardWrite(Board board, List<MultipartFile> imageList, 
			String webPath, String folderPath) throws IOException;

	
	
	
	
	
	
}
