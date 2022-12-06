package com.looknlook.looknlook.chat.controller;

import com.looknlook.looknlook.chat.domain.request.ReqRoom;
import com.looknlook.looknlook.chat.domain.response.ResRoom;
import com.looknlook.looknlook.chat.service.RoomService;
import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    //목록 조회
    @GetMapping("/list")
    public ModelAndView readRoomPage(@AuthenticationPrincipal Member member, Pageable pageable){
        // /list?page=0&size=10 방식으로 요청 pageable에 자동 바인딩(값이 없으면 page=0, size=20)

        Page<ResRoom> resRooms = roomService.readRoomByMemNoListPage(member.getMemNo(), pageable);
        ModelAndView modelAndView = new ModelAndView("chat/rooms");
        System.out.println(resRooms.getContent());
        modelAndView.addObject("list", resRooms);
        return modelAndView;
    }

    //방개설
    @PostMapping("")
    public String createRoom(ReqRoom reqRoom, @AuthenticationPrincipal Member member){
        roomService.createRoom(member.getMemNo(), reqRoom);
        return "redirect:/room/list?page=0&size=10";
    }

    @GetMapping("/{roomNo}")
    public String readRooms(@PathVariable("roomNo") Long roomNo, Model model, @AuthenticationPrincipal Member member){
        ResRoom resRoom = roomService.readRoom(roomNo);
        model.addAttribute("room", resRoom);
        return "chat/room";
    }
}
