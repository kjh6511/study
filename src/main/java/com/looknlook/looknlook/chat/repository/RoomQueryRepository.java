package com.looknlook.looknlook.chat.repository;

import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.chat.domain.response.ResRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomQueryRepository {
    Page<ResRoom> findAllByMemNoPage(Long memNo, Pageable pageable);
    ResRoom findByRoomNo(Long roomNo);
}
