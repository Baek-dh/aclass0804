
// 선택한 채팅방 번호를 저장하기 위한 전역 변수
let selectChattingNo;
let selectTargetNo;
let selectTargetName;
let selectTargetProfile;

// sockjs를 이용한 WebSocket 구현

// 로그인이 되어 있을 경우에만
// /chattingSock 이라는 요청 주소로 통신할 수 있는  WebSocket 객체 생성
let chattingSock;

if(loginMemberNo != ""){
	chattingSock = new SockJS("/chattingSock");
}

// 문서 로딩 완료 후 수행할 기능
document.addEventListener("DOMContentLoaded", ()=>{
	
	// 채팅방 목록에 클릭 이벤트 추가
	roomListAddEvent(); 

	// 메세지 버튼에 이벤트 추가
	send.addEventListener("click", sendMessage);


	if(tempNo != ""){
		//selectChattingNo = tempNo;

		const chattingItemList = document.getElementsByClassName("chatting-item");

		for(let item of chattingItemList){
		
			// 클릭한 채팅방의 번호 얻어오기
			const id = item.getAttribute("id");
			const arr = id.split("-");

			if(arr[0] == tempNo){
				item.click();
				break;
			}
		}
	}
})




// 채팅 메세지 영역
const display = document.getElementsByClassName("display-chatting")[0];


// 채팅방 목록에 이벤트를 추가하는 함수 
const roomListAddEvent = () => {
	const chattingItemList = document.getElementsByClassName("chatting-item");
	
	for(let item of chattingItemList){
		item.addEventListener("click", e => {
	
			// 클릭한 채팅방의 번호 얻어오기
			const id = item.getAttribute("id");
			const arr = id.split("-");
			// 전역변수에 채팅방 번호, 상대 번호, 상태 프로필, 상대 이름 저장
			selectChattingNo = arr[0]
			selectTargetNo = arr[1];
			selectTargetProfile = item.children[0].children[0].getAttribute("src");
			selectTargetName = item.children[1].children[0].children[0].innerText;

			if(item.children[1].children[1].children[1] != undefined){
				item.children[1].children[1].children[1].remove();
			}

	
			// 모든 채팅방에서 select 클래스를 제거
			for(let it of chattingItemList) it.classList.remove("select")
	
			// 현재 클릭한 채팅방에 select 클래스 추가
			item.classList.add("select");
	
			// 비동기로 메세지 목록을 조회하는 함수 호출
			selectChattingFn();
		});
	}
}

// 비동기로 메세지 목록을 조회하는 함수
const selectChattingFn = () => {
	$.ajax({
		url : "/chatting/selectMessage",
		data : {"chattingNo" : selectChattingNo, "memberNo" : loginMemberNo},
		dataType : "JSON",
		success : messageList => {
			console.log(messageList);

			// <ul class="display-chatting">
			const ul = document.querySelector(".display-chatting");

			ul.innerHTML = ""; // 이전 내용 지우기

			// 메세지 만들어서 출력하기
			for(let msg of messageList){
				//<li>,  <li class="my-chat">
				const li = document.createElement("li");

				// 보낸 시간
				const span = document.createElement("span");
				span.classList.add("chatDate");
				span.innerText = msg.sendTime;

				// 메세지 내용
				const p = document.createElement("p");
				p.classList.add("chat");
				p.innerHTML = msg.messageContent; // br태그 해석을 위해 innerHTML

				// 내가 작성한 메세지인 경우
				if(loginMemberNo == msg.senderNo){ 
					li.classList.add("my-chat");
					
					li.append(span, p);
					
				}else{ // 상대가 작성한 메세지인 경우
					li.classList.add("target-chat");

					// 상대 프로필
					// <img src="/resources/images/user.png">
					const img = document.createElement("img");
					img.setAttribute("src", selectTargetProfile);
					
					const div = document.createElement("div");

					// 상대 이름
					const b = document.createElement("b");
					b.innerText = selectTargetName; // 전역변수

					const br = document.createElement("br");

					div.append(b, br, p, span);
					li.append(img,div);

				}

				ul.append(li);
				display.scrollTop = display.scrollHeight; // 스크롤 제일 밑으로
			}



		},
		error : () => {console.log("에러");}
	})
}



// 비동기로 채팅방 목록 조회
const selectRoomList = () => {
	$.ajax({
		url: "/chatting/roomList",
		data : {"memberNo" : loginMemberNo},
		dataType : "JSON",
		success : roomList => {
			console.log(roomList);
			// 채팅방 리스트
			const chattingList = document.querySelector(".chatting-list");

			// 채팅방 리스트 지우기
			chattingList.innerHTML = "";

			for(let room of roomList){
				const li = document.createElement("li");
				li.classList.add("chatting-item");
				li.setAttribute("id", room.chattingNo + "-" + room.targetNo);

				if(room.chattingNo == selectChattingNo){
					li.classList.add("select");
				}

				// item-header 부분
				const itemHeader = document.createElement("div");
				itemHeader.classList.add("item-header");

				const listProfile = document.createElement("img");
				listProfile.classList.add("list-profile");

				if(room.targetProfile == undefined)	
					listProfile.setAttribute("src", "/resources/images/user.png");
				else								
					listProfile.setAttribute("src", room.targetProfile);

				itemHeader.append(listProfile);

				// item-body 부분
				const itemBody = document.createElement("div");
				itemBody.classList.add("item-body");

				const p = document.createElement("p");

				const targetName = document.createElement("span");
				targetName.classList.add("target-name");
				targetName.innerText = room.targetNickName;
				
				const recentSendTime = document.createElement("span");
				recentSendTime.classList.add("recent-send-time");
				recentSendTime.innerText = room.sendTime;
				
				
				p.append(targetName, recentSendTime);
				
				
				const div = document.createElement("div");
				
				const recentMessage = document.createElement("p");
				recentMessage.classList.add("recent-message");

				if(room.lastMessage != undefined){
					recentMessage.innerHTML = room.lastMessage;
				}
				
				div.append(recentMessage);

				itemBody.append(p,div);

				// 현재 채팅방을 보고있는게 아니고 읽지 않은 개수가 0개 이상인 경우 -> 읽지 않은 메세지 개수 출력
				if(room.notReadCount > 0 && room.chattingNo != selectChattingNo ){
				// if(room.chattingNo != selectChattingNo ){
					const notReadCount = document.createElement("p");
					notReadCount.classList.add("not-read-count");
					notReadCount.innerText = room.notReadCount;
					div.append(notReadCount);
				}else{
					// 현재 채팅방을 보고있는 경우
					// 비동기로 해당 채팅방 글을 읽음으로 표시
					$.ajax({
						url : "/chatting/updateReadFlag",
						data : {"chattingNo" : selectChattingNo, "memberNo" : loginMemberNo},
						success : result => {
							console.log(result);
						}
					})
				}
				

				li.append(itemHeader, itemBody);
				chattingList.append(li);
			}

			roomListAddEvent();
		}
	})
}


// 채팅 입력
const send = document.getElementById("send");

const sendMessage = () => {
	const inputChatting = document.getElementById("inputChatting");

	if (inputChatting.value.trim().length == 0) {
		alert("채팅을 입력해주세요.");
		inputChatting.value = "";
	} else {
		var obj = {
			"senderNo": loginMemberNo,
			"targetNo": selectTargetNo,
			"chattingNo": selectChattingNo,
			"messageContent": inputChatting.value,
		};
		console.log(obj)

		// JSON.stringify() : 자바스크립트 객체를 JSON 문자열로 변환
		chattingSock.send(JSON.stringify(obj));

		inputChatting.value = "";
	}
}

// 엔터 == 제출
// 쉬프트 + 엔터 == 줄바꿈
inputChatting.addEventListener("keyup", e => {
	if(e.key == "Enter"){ 
		if (!e.shiftKey) {
			sendMessage();
		}
	}
})




// WebSocket 객체 chattingSock이 서버로 부터 메세지를 통지 받으면 자동으로 실행될 콜백 함수
chattingSock.onmessage = function(e) {
	// 메소드를 통해 전달받은 객체값을 JSON객체로 변환해서 msg 변수에 저장.
	const msg = JSON.parse(e.data);
	console.log(msg);

	// 현재 메세지를 받은 채팅방을 보고 있다면
	if(selectChattingNo == msg.chattingNo){
		const ul = document.querySelector(".display-chatting");
	
		// 메세지 만들어서 출력하기
		//<li>,  <li class="my-chat">
		const li = document.createElement("li");
	
		// 보낸 시간
		const span = document.createElement("span");
		span.classList.add("chatDate");
		span.innerText = msg.sendTime;
	
		// 메세지 내용
		const p = document.createElement("p");
		p.classList.add("chat");
		p.innerHTML = msg.messageContent; // br태그 해석을 위해 innerHTML
	
		// 내가 작성한 메세지인 경우
		if(loginMemberNo == msg.senderNo){ 
			li.classList.add("my-chat");
			
			li.append(span, p);
			
		}else{ // 상대가 작성한 메세지인 경우
			li.classList.add("target-chat");
	
			// 상대 프로필
			// <img src="/resources/images/user.png">
			const img = document.createElement("img");
			img.setAttribute("src", selectTargetProfile);
			
			const div = document.createElement("div");
	
			// 상대 이름
			const b = document.createElement("b");
			b.innerText = selectTargetName; // 전역변수
	
			const br = document.createElement("br");
	
			div.append(b, br, p, span);
			li.append(img,div);
	
		}
	
		ul.append(li)
		display.scrollTop = display.scrollHeight; // 스크롤 제일 밑으로
	}



	selectRoomList();
}

