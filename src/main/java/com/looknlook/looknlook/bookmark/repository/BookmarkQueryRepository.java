package com.looknlook.looknlook.bookmark.repository;

import com.looknlook.looknlook.bookmark.domain.request.ReqBookmarkSearch;
import com.looknlook.looknlook.bookmark.domain.response.ResBookmarkItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkQueryRepository {

    Page<ResBookmarkItem> findAllByMemNoWithItemPage(Long memNo, ReqBookmarkSearch search, Pageable pageable);
}
