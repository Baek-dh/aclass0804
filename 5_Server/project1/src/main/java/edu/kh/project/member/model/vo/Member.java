package edu.kh.project.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor		// *기본 생성자 
@AllArgsConstructor		// 모든 매개변수 생성자
@Getter					// *모든 필드의 Getter
@Setter					// *모든 필드의 Setter
@ToString				// toString() 오버라이딩
public class Member {
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberNickname;
	private String memberTel;
	private String memberAddress;
	private String profileImage;
	private String enrollDate;
	private String memberDeleteFlag;
	private int authority;
	
}
