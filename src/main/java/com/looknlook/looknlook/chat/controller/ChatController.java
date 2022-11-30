package com.looknlook.looknlook.chat.controller;

import com.looknlook.looknlook.chat.repository.ChatRoomRepository;
import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomRepository repository;

    @GetMapping("")
    public String chatGet(Model model, @AuthenticationPrincipal Member member)throws Exception{
        System.out.println("chat Get ================================ ");
        System.out.println("name :::::::: "+member.getMemName());
        model.addAttribute("memName",member.getMemName());
        return "chat/chat";
    }

    //채팅방 관련

    //채팅방 목록 조회
    @GetMapping("/rooms")
    public ModelAndView rooms(){

        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list", repository.findAllRooms());

        return mv;
    }

    //채팅방 개설
    @PostMapping("/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){

        log.info("# Create Chat Room , name: " + name);
        rttr.addFlashAttribute("roomName", repository.createChatRoomDto(name));
        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomId, Model model){

        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", repository.findRoomById(roomId));
    }
}
