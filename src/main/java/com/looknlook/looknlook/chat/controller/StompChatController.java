package com.looknlook.looknlook.chat.controller;

import com.looknlook.looknlook.chat.domain.dto.ChatMessageDto;
import com.looknlook.looknlook.chat.domain.request.ReqChat;
import com.looknlook.looknlook.chat.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final RoomService roomService;
    private final SimpMessagingTemplate template;

    //ChatHandler 역할을 함
    //@MessageMapping을 통행 WebSocket으로 들어오는 메세지 발행을 처리
    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter") //입장정보 /pub/chat/enter
    public void enter(ChatMessageDto message){
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하셨습니다.");

        log.info("1 ->" + message.getWriter() + " : " + message.getMessage());

        //webSocket으로 메세지 보내기
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message") // 메세지 전송 /pub/chat/message
    public void message(ChatMessageDto message){
        log.info("2 ->" + message.getWriter() + " : " + message.getMessage());

        //저장
        ReqChat reqChat = new ReqChat();
        reqChat.setChatCont(message.getMessage());
        reqChat.setRoomNo(message.getRoomNo());
        roomService.createChat(message.getMemNo(), reqChat);

        //해당 주소의 webSocket으로 메세지 보내기
        template.convertAndSend("/sub/chat/room/" + message.getRoomNo(), message);
    }

    @MessageMapping(value = "/chat/end") //나가기 /pub/chat/end
    public void end(ChatMessageDto message){
        message.setMessage(message.getWriter() + "님이 채팅방에서 나갔습니다.");

        log.info("3 ->" + message.getWriter() + " : " + message.getMessage());

        //webSocket으로 메세지 보내기
        template.convertAndSend("/sub/chat/room/" + message.getRoomNo(), message);
    }
}
