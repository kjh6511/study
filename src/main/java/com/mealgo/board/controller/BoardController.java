package com.mealgo.board.controller;

import com.mealgo.board.domain.dto.RequestBoard;
import com.mealgo.board.domain.dto.ResponseBoard;
import com.mealgo.board.domain.dto.SearchBoardCondition;
import com.mealgo.board.service.BoardService;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.orders.domain.dto.ResponseOrder;
import com.mealgo.orders.domain.dto.SearchOrderCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
class BoardController {

    private final BoardService boardService;

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("requestBoard", new RequestBoard());
        return "board/boardForm";
    }

    @PostMapping
    public String save(@ModelAttribute RequestBoard dto,
                       @AuthenticationPrincipal Member auth) {
        boardService.saveBoard(dto, auth);
        return "redirect:/boards";
    }

    @GetMapping
    public String boardList(@AuthenticationPrincipal Member auth,
                            @ModelAttribute SearchBoardCondition condition,
                            @PageableDefault(size = 10) Pageable pageable,
                            Model model) {
        Page<ResponseBoard> boardPage = boardService.readBoardList(condition, pageable);
        model.addAttribute("boardPage", boardPage);
        model.addAttribute("condition", condition);
        return "board/board";
    }
}
