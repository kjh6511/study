package com.looknlook.looknlook.chat.service;

import com.looknlook.looknlook.chat.domain.entity.Room;
import com.looknlook.looknlook.chat.domain.request.ReqChat;
import com.looknlook.looknlook.chat.domain.request.ReqRoom;
import com.looknlook.looknlook.chat.domain.response.ResRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    void createRoom() {
        ReqRoom reqRoom = new ReqRoom();
        reqRoom.setRoomNm("test님의 문의");
        reqRoom.setRoomType("08002");
        reqRoom.setShopNo(2L); //memNo:6(test) 의 shop

        roomService.createRoom(8L,reqRoom);

    }

    @Test
    void readRoomByMemNoListPage() {

        PageRequest pageRequest = PageRequest.of(0,10);

        Page<ResRoom> list1 = roomService.readRoomByMemNoListPage(6L,pageRequest);
        System.out.println("shopMember : " + list1.getContent());
        System.out.println("======================================");

        Page<ResRoom> list2 = roomService.readRoomByMemNoListPage(7L,pageRequest);
        System.out.println("regMember : " + list2.getContent());
    }

    @Test
    void readRoom() {
        ResRoom room = roomService.readRoom(1L);
        System.out.println(room);
    }

    @Test
    void createChat(){
        ReqChat reqChat = new ReqChat();
        reqChat.setChatCont("테스트 입니다. 1:1 ");
        reqChat.setRoomNo(2L);
        roomService.createChat(8L,reqChat);

    }
}