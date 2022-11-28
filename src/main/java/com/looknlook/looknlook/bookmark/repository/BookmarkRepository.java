package com.looknlook.looknlook.bookmark.repository;

import com.looknlook.looknlook.bookmark.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkQueryRepository {


}
