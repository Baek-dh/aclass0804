package edu.kh.project.chatting.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
    private int messageNo;
    private String messageContent;
    private String readFlag;
    private int senderNo;
    private int targetNo;
    private int chattingNo;
    private String sendTime;
}
