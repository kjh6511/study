package com.looknlook.looknlook.bookmark.service;

import com.looknlook.looknlook.bookmark.domain.request.ReqBookmark;
import com.looknlook.looknlook.bookmark.domain.request.ReqBookmarkSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Size;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookmarkServiceTest {

    @Autowired
    private BookmarkService bookmarkService;

    @Test
    void createBookmark() throws Exception {
        ReqBookmark reqBookmark = new ReqBookmark();
        reqBookmark.setBoardNo(2L);
        reqBookmark.setBmType("04003");

        bookmarkService.createBookmark(6L,reqBookmark);
    }

    @Test
    void readBookmarkItemListPage()throws Exception{
        ReqBookmarkSearch search = new ReqBookmarkSearch();
        search.setIcNum(null);
        search.setBmType("04003");
        PageRequest pageRequest = PageRequest.of(0,5);



    }
}