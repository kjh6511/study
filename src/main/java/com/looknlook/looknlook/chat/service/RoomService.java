package com.looknlook.looknlook.chat.service;

import com.looknlook.looknlook.chat.domain.entity.Chat;
import com.looknlook.looknlook.chat.domain.entity.ChatMember;
import com.looknlook.looknlook.chat.domain.entity.Room;
import com.looknlook.looknlook.chat.domain.request.ReqChat;
import com.looknlook.looknlook.chat.domain.request.ReqRoom;
import com.looknlook.looknlook.chat.domain.response.ResRoom;
import com.looknlook.looknlook.chat.repository.ChatMemberRepository;
import com.looknlook.looknlook.chat.repository.ChatRepository;
import com.looknlook.looknlook.chat.repository.RoomRepository;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.repository.MemberRepository;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.looknlook.looknlook.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final ChatRepository chatRepository;

    @Transactional
    public void createRoom(Long memNo, ReqRoom reqRoom) {
        Member member = memberRepository.findById(memNo).orElseGet(Member::new);

        Shop shop = null;
        //고객센터 용
        Member resMember = memberRepository.findById(5L).orElseGet(Member::new);
        if(reqRoom.getRoomType().equals("08001")){
            shop = shopRepository.findById(reqRoom.getShopNo()).orElseGet(Shop::new);
            reqRoom.setRoomNm(member.getMemName() +"님의 1:1 문의사항");
            //shop 작성자
            resMember = shop.getMember();
        }else if(reqRoom.getRoomType().equals("08002")){
            shop = null;
            reqRoom.setRoomNm(member.getMemName() +"님의 고객센터 문의사항");
        }

        Room room = Room.builder()
                .member(member)
                .shop(shop)
                .roomStu("01001")
                .roomType(reqRoom.getRoomType())
                .roomNm(reqRoom.getRoomNm())
                .roomRegDt(LocalDateTime.now())
                .build();
        roomRepository.save(room);

        ChatMember regMember = ChatMember.builder()
                .cmStu("01001")
                .cmRegDt(LocalDateTime.now())
                .room(room)
                .member(member)
                .build();
        chatMemberRepository.save(regMember);

        ChatMember shopMember = ChatMember.builder()
                .cmStu("01001")
                .cmRegDt(LocalDateTime.now())
                .room(room)
                .member(resMember)
                .build();
        chatMemberRepository.save(shopMember);

    }

    @Transactional
    public Page<ResRoom> readRoomByMemNoListPage(Long memNo, Pageable pageable) {
        return roomRepository.findAllByMemNoPage(memNo, pageable);

    }

    public ResRoom readRoom(Long roomNo) {
        return roomRepository.findByRoomNo(roomNo);
    }

    public void createChat(Long memNo, ReqChat reqChat){
        Member member = memberRepository.findById(memNo).orElseGet(Member::new);
        Room room = roomRepository.findById(reqChat.getRoomNo()).orElseGet(Room::new);

        Chat chat = Chat.builder()
                .room(room)
                .member(member)
                .chatCont(reqChat.getChatCont())
                .chatDt(LocalDateTime.now())
                .build();
        chatRepository.save(chat);
    }
}
