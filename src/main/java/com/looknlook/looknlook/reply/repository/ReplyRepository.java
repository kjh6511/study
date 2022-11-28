package com.looknlook.looknlook.reply.repository;

import com.looknlook.looknlook.order.domain.entity.Orders;
import com.looknlook.looknlook.order.repository.OrderQueryRepository;
import com.looknlook.looknlook.reply.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyQueryRepository {

}
