package com.mealgo.delivery.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveryHistory is a Querydsl query type for DeliveryHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryHistory extends EntityPathBase<DeliveryHistory> {

    private static final long serialVersionUID = 1758440315L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveryHistory deliveryHistory = new QDeliveryHistory("deliveryHistory");

    public final QDelivery delivery;

    public final NumberPath<Integer> dhNo = createNumber("dhNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> dhRegDt = createDateTime("dhRegDt", java.time.LocalDateTime.class);

    public final EnumPath<com.mealgo.common.code.DeliveryEnum> dhStat = createEnum("dhStat", com.mealgo.common.code.DeliveryEnum.class);

    public QDeliveryHistory(String variable) {
        this(DeliveryHistory.class, forVariable(variable), INITS);
    }

    public QDeliveryHistory(Path<? extends DeliveryHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveryHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveryHistory(PathMetadata metadata, PathInits inits) {
        this(DeliveryHistory.class, metadata, inits);
    }

    public QDeliveryHistory(Class<? extends DeliveryHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
    }

}

