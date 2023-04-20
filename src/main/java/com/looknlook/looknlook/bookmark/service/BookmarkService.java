package com.looknlook.looknlook.bookmark.service;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.repository.ItemRepository;
import com.looknlook.looknlook.board.domain.entity.Board;
import com.looknlook.looknlook.board.repository.BoardRepository;
import com.looknlook.looknlook.bookmark.domain.entity.Bookmark;
import com.looknlook.looknlook.bookmark.domain.request.ReqBookmark;
import com.looknlook.looknlook.bookmark.domain.request.ReqBookmarkSearch;
import com.looknlook.looknlook.bookmark.domain.response.ResBookmarkItem;
import com.looknlook.looknlook.bookmark.repository.BookmarkRepository;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.repository.MemberRepository;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.looknlook.looknlook.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;
    private final BoardRepository boardRepository;


    public void createBookmark(Long memNo, ReqBookmark reqBookmark)throws Exception{
        Member member = memberRepository.findById(memNo)
                .orElseThrow(NullPointerException::new);
        Bookmark bookmark = Bookmark.builder()
                   .bmType(reqBookmark.getBmType())
                   .member(member)
                   .build();

        if(reqBookmark.getBmType().equals("04001")){//shop
            Shop shop = shopRepository.findById(reqBookmark.getShopNo())
                                    .orElseThrow(NullPointerException::new);
            bookmark.setShop(shop);
        }else if(reqBookmark.getBmType().equals("04002")){ //item
            Item item = itemRepository.findById(reqBookmark.getItemNo())
                    .orElseThrow(NullPointerException::new);
            bookmark.setItem(item);
        }else if(reqBookmark.getBmType().equals("04003")){ //board
            Board board = boardRepository.findById(reqBookmark.getBoardNo())
                    .orElseThrow(NullPointerException::new);
            bookmark.setBoard(board);
        }
        bookmarkRepository.save(bookmark);
    }

    @Transactional
    public Page<ResBookmarkItem> readBookmarkItemListPage(Long memNo, ReqBookmarkSearch search, Pageable pageable)throws Exception{
           return bookmarkRepository.findAllByMemNoWithItemPage(memNo,search,pageable);
    }

    public void deleteBookmark(Long bmNo)throws Exception{
         bookmarkRepository.deleteById(bmNo);
    }
}
