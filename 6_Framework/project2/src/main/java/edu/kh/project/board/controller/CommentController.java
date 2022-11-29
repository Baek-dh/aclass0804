package edu.kh.project.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import edu.kh.project.board.model.service.CommentService;
import edu.kh.project.board.model.vo.Comment;

/* REST(REpresentational State Transfer)
 * - 자원을 이름으로 구분(REpresentational, 자원의 표현)하여
 *   자원의 상태(State)를 주고 받는 것(Transfer)
 *   
 * == 특정한 이름(주소)으로 요청하면 값을 받을 수 있다
 * */

@RestController  // 요청에 따른 응답이 모두 값 자체인 컨트롤러 + bean 등록
// A convenience annotation that is itself annotated with @Controller and @ResponseBody. 
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	
	// 댓글 목록 조회
	@GetMapping("/list")
	public String selectCommentList(int boardNo) {
		List<Comment> rList = service.selectCommentList(boardNo);
		return new Gson().toJson(rList); // JSON 형태로 변환(GSON 라이브러리 이용)
	}
	
	
	// 댓글 등록
	@PostMapping("/insert")
	public int insertComment(Comment comment/*커맨드객체*/) {
		return service.insertComment(comment);
	}
	
	
	
	// 댓글 삭제
	@GetMapping("/delete")
	public int deleteComment(int commentNo) {
		return service.deleteComment(commentNo);
	}
	
	// 댓글 수정
	@PostMapping("/update")
	public int updateComment(Comment comment) {
		return service.updateComment(comment);
	}
	
	
	
	
}

