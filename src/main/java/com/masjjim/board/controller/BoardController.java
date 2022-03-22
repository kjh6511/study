package com.masjjim.board.controller;

import com.masjjim.board.domain.request.ReqBoardMenu;
import com.masjjim.board.service.BoardService;
import com.masjjim.member.domain.request.ReqRegister;
import com.masjjim.member.domain.response.ResMember;
import com.masjjim.member.service.MemberService;
import com.masjjim.util.network.Header;
import com.masjjim.util.security.domain.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/category")
    public Header<Void> createBoardCategory(@RequestBody ReqRegister reqRegister) throws Exception {

        return Header.CREATE();
    }

    @PostMapping("/menu")
    public Header<Void> createBoardMenu(@RequestBody ReqBoardMenu reqBoardMenu) throws Exception {
        boardService.createBoardMenu(reqBoardMenu);
        return Header.CREATE();
    }

}
