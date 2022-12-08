package edu.kh.project.chatting.model.websocket;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import edu.kh.project.chatting.model.service.ChattingService;
import edu.kh.project.chatting.model.vo.Message;
import edu.kh.project.member.model.vo.Member;

public class ChattingWebsocketHandler extends TextWebSocketHandler {
	
	private Logger logger = LoggerFactory.getLogger(ChattingWebsocketHandler.class);

	@Autowired
	private ChattingService service;
	
    private Set<WebSocketSession> sessions  = Collections.synchronizedSet(new HashSet<WebSocketSession>());
    // synchronizedSet : 동기화된 Set 반환(HashSet은 기본적으로 비동기)
    // -> 멀티스레드 환경에서 하나의 컬렉션에 여러 스레드가 접근하여 의도치 않은 문제가 발생되지 않게 하기 위해
    //    동기화를 진행하여 스레드가 여러 순서대로 한 컬렉션에 순서대로 접근할 수 있게 변경.
	
	// afterConnectionEstablished
	// - 클라이언트와 연결이 완료되고 통신할 준비가 되면 수행되는 메서드
	// --> JS : new SockJS("/chattingSock");
	// --> servlet-content.xml에서 연결 
	// --> 핸들러 클래스 매핑 
	// --> 해당 메서드 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		// WebSocketSession session : 클라이언트와 서버간의 전이중 통신을 담당하는 객체
		//  + 웹소켓에 접속한 회원의 HttpSession을 훔쳐서 가지고 있음
			
		sessions.add(session);
		// 현재 채팅방에 접속한 회원의 세션을 모아둠
	}

	
	// handleTextMessage : 클라이언트로부터 텍스트 메세지를 받으면 수행되는 메서드
	// --> JS : chattingSock.send(JSON데이터);
	// --> handleTextMessage 수행
	//   --> message.getPayLoad() == JSON데이터
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		// 전달 받은 내용 확인 (JSON) 
		// {"senderNo":"21","targetNo":"1","chattingNo":"43","messageContent":"test"}
		logger.info("전달 받은 내용 : " + message.getPayload());
		
		// Jackson-databind에서 제공하는 ObjectMapper 객체 사용
			
		// ObjectMapper : JSON을 해석해서 지정된 VO로 변환하는 객체(필드에 값 세팅)
		ObjectMapper objectMapper = new ObjectMapper();
		
		Message msg = objectMapper.readValue(message.getPayload(), Message.class);
														// JSON  , 변경할 VO 클래스
		
		logger.debug(msg.toString());
		
		// 메세지 DB에 insert
		int result = service.insertMessage(msg);
		
		if(result > 0) { // 삽입 성공 시
			
			// 보낸 시간에 DB에 있고 msg 객체에는 없는 상태
			// -> 보낸 시간 생성
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
			msg.setSendTime(  sdf.format(new Date())  );
			
			// msg 객체(채팅방번호, 대상번호, 내용, 보낸사람번호, 보낸시간)
			// -> JSON으로 변환
			// -> 로그인한 회원 중
			//    대상번호, 보낸사람번호가 일치하는 2명에게 
			//    웹소켓으로 메세지 전달
			
			
            // 전역변수로 선언된 sessions에는 접속중인 모든 회원의 세션 정보가 담겨 있음
            for(WebSocketSession s : sessions) {
                // WebSocketSession은 HttpSession의 속성을 가로채서 똑같이 가지고 있기 때문에
                // 회원 정보를 나타내는 loginMember도 가지고 있음.
                
                // 로그인된 회원 정보 중 회원 번호 얻어오기
                int loginMemberNo = ((Member)s.getAttributes().get("loginMember")).getMemberNo();
                logger.debug("loginMemberNo : " + loginMemberNo);
                
                // 로그인 상태인 회원 중 targetNo가 일티하는 회원에게 메세지 전달
                if(loginMemberNo == msg.getTargetNo() || loginMemberNo == msg.getSenderNo()) {
                    
                    s.sendMessage(new TextMessage(new Gson().toJson(msg)));
                }
            }
		}
		
		
	}

	
	// afterConnectionClosed : 클라이언트와 연결이 끊기면 수행되는 메서드
	// --> 채팅방 화면(SockJS 객체가 있는 화면)을 벗어나면 연결 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		sessions.remove(session);
		// 채팅방에서 나간 회원의 세션을 Set에서 없앰
	
	}

	
	
}



/* WebSocket
- 브라우저와 웹서버간의 전이중통신을 지원하는 프로토콜이다
- HTML5버전부터 지원하는 기능이다.
- 자바 톰캣7버전부터 지원했으나 8버전부터 본격적으로 지원한다.
- spring4부터 웹소켓을 지원한다. 
(전이중 통신(Full Duplex): 두 대의 단말기가 데이터를 송수신하기 위해 동시에 각각 독립된 회선을 사용하는 통신 방식. 
대표적으로 전화망, 고속 데이터 통신)


WebSocketHandler 인터페이스 : 웹소켓을 위한 메소드를 지원하는 인터페이스
    -> WebSocketHandler 인터페이스를 상속받은 클래스를 이용해 웹소켓 기능을 구현


WebSocketHandler 주요 메소드
        
    void handlerMessage(WebSocketSession session, WebSocketMessage message)
    - 클라이언트로부터 메세지가 도착하면 실행
    
    void afterConnectionEstablished(WebSocketSession session)
    - 클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행

    void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
    - 클라이언트와 연결이 종료되면 실행

    void handleTransportError(WebSocketSession session, Throwable exception)
    - 메세지 전송중 에러가 발생하면 실행 


----------------------------------------------------------------------------

TextWebSocketHandler :  WebSocketHandler 인터페이스를 상속받아 구현한 텍스트 메세지 전용 웹소켓 핸들러 클래스
 
    handlerTextMessage(WebSocketSession session, TextMessage message)
    - 클라이언트로부터 텍스트 메세지를 받았을때 실행
     

*/




