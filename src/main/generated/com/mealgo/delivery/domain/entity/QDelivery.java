package com.mealgo.delivery.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = 1237752537L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final DateTimePath<java.time.LocalDateTime> deliDt = createDateTime("deliDt", java.time.LocalDateTime.class);

    public final StringPath deliInfo = createString("deliInfo");

    public final NumberPath<Integer> deliNo = createNumber("deliNo", Integer.class);

    public final EnumPath<com.mealgo.common.code.DeliveryEnum> deliStat = createEnum("deliStat", com.mealgo.common.code.DeliveryEnum.class);

    public final com.mealgo.orders.domain.entity.QOrders orders;

    public QDelivery(String variable) {
        this(Delivery.class, forVariable(variable), INITS);
    }

    public QDelivery(Path<? extends Delivery> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDelivery(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDelivery(PathMetadata metadata, PathInits inits) {
        this(Delivery.class, metadata, inits);
    }

    public QDelivery(Class<? extends Delivery> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orders = inits.isInitialized("orders") ? new com.mealgo.orders.domain.entity.QOrders(forProperty("orders"), inits.get("orders")) : null;
    }

}

