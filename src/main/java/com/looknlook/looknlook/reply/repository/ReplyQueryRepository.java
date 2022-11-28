package com.looknlook.looknlook.reply.repository;

import com.looknlook.looknlook.reply.domain.entity.Reply;

import java.util.List;

public interface ReplyQueryRepository {


    List<Reply> findAllByItemNo(Long itemNo);

    List<Reply> findAllByMemNo(Long memNo);
}
