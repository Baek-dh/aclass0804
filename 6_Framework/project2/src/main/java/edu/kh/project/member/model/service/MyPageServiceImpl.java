package edu.kh.project.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.MyPageDAO;
import edu.kh.project.member.model.vo.Member;

@Service // bean 등록
public class MyPageServiceImpl implements MyPageService {
	
	@Autowired // DI
	private MyPageDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	
	// 회원 정보 수정 서비스
	@Transactional
	@Override
	public int updateInfo(Member inputMember) {
		int result = dao.updateInfo(inputMember);
		
		return result;
	}

	
	// 비밀번호 변경 서비스
	@Transactional
	@Override
	public int changePw(Map<String, Object> paramMap) {
		// 현재 비밀번호 일치 시 새 비밀번호로 변경
		
		// 1. 회원 번호를 이용해서 DB에서 암호화된 비밀번호를 조회
		String encPw = dao.selectEncPw( (int)paramMap.get("memberNo") );
		
		// 2. matches(입력PW , 암호화PW) 결과가 true인 경우
		//   새 비밀번호로 UPDATE하는 DAO 코드를 호출
		
		if( bcrypt.matches( (String)paramMap.get("currentPw")  , encPw)  ) {
			
			// 새 비밀번호 암호화
			String newPw = bcrypt.encode(  (String)paramMap.get("newPw")  );
			
			paramMap.put("newPw", newPw);
			// paramMap에 존재하는 기존 "newPw"를 덮어쓰기
			
			// DAO 호출
			int result = dao.changePw(paramMap);
			
			return result;
		}
		
		
		return 0; // 비밀번호 불일치 시 0 반환
	}

	// 회원 탈퇴 서비스
	@Transactional
	@Override
	public int memberDelete(int memberNo, String memberPw) {
		
		// 1. 비밀번호 조회
		String encPw = dao.selectEncPw( memberNo );
		
		// 2. 일치하면 탈퇴 
		if(bcrypt.matches(memberPw, encPw)) {
			
			return dao.memberDelete(memberNo);
		}
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	

}
