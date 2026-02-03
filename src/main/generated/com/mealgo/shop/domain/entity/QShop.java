package com.mealgo.shop.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShop is a Querydsl query type for Shop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShop extends EntityPathBase<Shop> {

    private static final long serialVersionUID = -1352878311L;

    public static final QShop shop = new QShop("shop");

    public final StringPath shopId = createString("shopId");

    public final StringPath shopImg = createString("shopImg");

    public final StringPath shopInfo = createString("shopInfo");

    public final StringPath shopNm = createString("shopNm");

    public final NumberPath<Integer> shopNo = createNumber("shopNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> shopRegDt = createDateTime("shopRegDt", java.time.LocalDateTime.class);

    public final EnumPath<com.mealgo.common.code.ShopEnum> shopStat = createEnum("shopStat", com.mealgo.common.code.ShopEnum.class);

    public final StringPath shopTel = createString("shopTel");

    public final DateTimePath<java.time.LocalDateTime> shopUpDt = createDateTime("shopUpDt", java.time.LocalDateTime.class);

    public QShop(String variable) {
        super(Shop.class, forVariable(variable));
    }

    public QShop(Path<? extends Shop> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShop(PathMetadata metadata) {
        super(Shop.class, metadata);
    }

}

