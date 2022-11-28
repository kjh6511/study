package com.looknlook.looknlook.board.service;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.repository.ItemRepository;
import com.looknlook.looknlook.board.domain.entity.Board;
import com.looknlook.looknlook.board.domain.entity.BoardItem;
import com.looknlook.looknlook.board.domain.request.ReqBoard;
import com.looknlook.looknlook.board.domain.request.ReqBoardSearch;
import com.looknlook.looknlook.board.domain.request.ReqBoardUpdate;
import com.looknlook.looknlook.board.domain.response.ResBoard;
import com.looknlook.looknlook.board.domain.response.ResBoardList;
import com.looknlook.looknlook.board.repository.BoardItemRepository;
import com.looknlook.looknlook.board.repository.BoardRepository;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardItemRepository boardItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void createBoard(Long memNo, ReqBoard reqBoard)throws Exception{
        Member member = memberRepository.findById(memNo)
                .orElseThrow(NullPointerException::new);

        Board board = Board.builder()
                .boardNm(reqBoard.getBoardNm())
                .boardStu("01001")
                .boardType(reqBoard.getBoardType())
                .boardText(reqBoard.getBoardText())
                .boardDt(LocalDateTime.now())
                .member(member)
                .build();
        Long boardNo = boardRepository.save(board).getBoardNo();

        if(reqBoard.getItemNoList() != null || reqBoard.getItemNoList().length != 0){
            Board getBoard = boardRepository.findById(boardNo)
                    .orElseThrow(NullPointerException::new);
            for(Long itemNo : reqBoard.getItemNoList()){
                Item item = itemRepository.findById(itemNo)
                        .orElseThrow(NullPointerException::new);
                BoardItem boardItem = BoardItem
                        .builder()
                        .board(getBoard)
                        .item(item)
                        .build();
                boardItemRepository.save(boardItem);
            }
        }
    }

    @Transactional
    public List<ResBoardList> readBoardList(String boardType)throws Exception{
        List<Board> boards = boardRepository.findAllByBoardTypeWithItem(boardType);
        return boards.stream().map(ResBoardList::new).collect(Collectors.toList());
    }

    @Transactional
    public Page<ResBoardList> readBoardListPage(ReqBoardSearch search, Pageable pageable)throws Exception{
        Page<ResBoardList> pageList = boardRepository.findAllByBoardTypeWithItemPage(search,pageable);

        return pageList;
    }

    @Transactional
    public ResBoard readBoard(Long boardNo)throws Exception{
        Board board = boardRepository.findByBoardNoWithItem(boardNo);
        return new ResBoard(board);
    }

    @Transactional
    public void updateBoard(ReqBoardUpdate reqBoardUpdate)throws Exception{
        Long boardNo = reqBoardUpdate.getBoardNo();
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(NullPointerException::new);
        board.setBoardNm(reqBoardUpdate.getBoardNm());
        board.setBoardStu(reqBoardUpdate.getBoardStu());
        board.setBoardType(reqBoardUpdate.getBoardType());
        board.setBoardText(reqBoardUpdate.getBoardText());
        board.setBoardUpDt(LocalDateTime.now());
        boardRepository.save(board);

        if(reqBoardUpdate.getItemNoList() != null || reqBoardUpdate.getItemNoList().length != 0){
            boardItemRepository.deleteByBoardNo(boardNo);
            for(Long itemNo : reqBoardUpdate.getItemNoList()){
                Item item = itemRepository.findById(itemNo)
                        .orElseThrow(NullPointerException::new);
                BoardItem boardItem = BoardItem
                        .builder()
                        .board(board)
                        .item(item)
                        .build();
                boardItemRepository.save(boardItem);
            }
        }
    }
}
