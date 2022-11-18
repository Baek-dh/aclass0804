package edu.kh.project.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.board.model.service.BoardService;
import edu.kh.project.board.model.vo.Board;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	// 특정 게시판 목록 조회
	// /board/1?cp=1
	// /board/2?cp=10
	// /board/3?cp=3
	// /board/4
	
	// -> @PathVariable 사용
	//   URL 경로에 있는 값을 파라미터(변수)로 사용할 수 있게하는 어노테이션
	//  + 자동으로 request scope로 등록되어 EL 구문으로 jsp에서 출력도 가능
	
	// 요청주소 ?K=V&K=V&K=V....  (queryString, 쿼리스트링)
	// -> 요청주소에 값을 담아서 전달할 때 사용하는 문자열
	
	@GetMapping("/board/{boardCode}")
	public String selectBoardList(@PathVariable("boardCode") int boardCode,
		Model model,
		@RequestParam(value="cp", required=false, defaultValue="1") int cp
		) {
		
		// Model : 값 전달용 객체
		// model.addAttribute("k", v) : request scope에 세팅
		//								-> forward 시 유지됨
		
		Map<String, Object> map = service.selectBoardList(boardCode, cp);
		
		model.addAttribute("map", map); // request scope 세팅
		 
		return "board/boardList"; // forward
	}
	
	
	// 게시글 상세조회
	@GetMapping("/board/{boardCode}/{boardNo}")
	public String boardDetail(
			@PathVariable("boardNo") int boardNo,
			@PathVariable("boardCode") int boardCode,
			Model model) {
		
		// 게시글 상세조회 서비스 호출
		Board board = service.selectBoardDetail(boardNo);
		// + 좋아요 수, 좋아요 여부
		// + 조회 수 증가(쿠키를 이용해서 해당 IP당 하루 한번)
		
		
		model.addAttribute("board",board);
		
		return "board/boardDetail";
	}
	
	
	
	
}








