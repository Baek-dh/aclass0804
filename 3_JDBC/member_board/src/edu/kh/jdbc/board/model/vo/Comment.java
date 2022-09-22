package edu.kh.jdbc.board.model.vo;

// 댓글 1개에 대한 값을 저장하는 VO
public class Comment {
	private int commentNo; 			// 댓글 번호
	private String commentContent;	// 댓글 내용
	private int memberNo;			// 작성자 회원 번호
	private String memberName;		// 작성자 회원 이름
	private String createDate;		// 작성일
	private int boardNo;			// 작성된 게시글 번호(등록,수정,삭제 시 이용)
	
	public Comment() {}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	 
	
}
