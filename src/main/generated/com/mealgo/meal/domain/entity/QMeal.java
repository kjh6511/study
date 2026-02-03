package com.mealgo.meal.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMeal is a Querydsl query type for Meal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMeal extends EntityPathBase<Meal> {

    private static final long serialVersionUID = 263873017L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMeal meal = new QMeal("meal");

    public final com.mealgo.category.domain.entity.QCategory category;

    public final StringPath mealInfo = createString("mealInfo");

    public final StringPath mealNm = createString("mealNm");

    public final NumberPath<Integer> mealNo = createNumber("mealNo", Integer.class);

    public final NumberPath<Integer> mealPri = createNumber("mealPri", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> mealRegDt = createDateTime("mealRegDt", java.time.LocalDateTime.class);

    public final EnumPath<com.mealgo.common.code.MealEnum> mealStat = createEnum("mealStat", com.mealgo.common.code.MealEnum.class);

    public final DateTimePath<java.time.LocalDateTime> mealUpDt = createDateTime("mealUpDt", java.time.LocalDateTime.class);

    public final com.mealgo.shop.domain.entity.QShop shop;

    public QMeal(String variable) {
        this(Meal.class, forVariable(variable), INITS);
    }

    public QMeal(Path<? extends Meal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMeal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMeal(PathMetadata metadata, PathInits inits) {
        this(Meal.class, metadata, inits);
    }

    public QMeal(Class<? extends Meal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.mealgo.category.domain.entity.QCategory(forProperty("category"), inits.get("category")) : null;
        this.shop = inits.isInitialized("shop") ? new com.mealgo.shop.domain.entity.QShop(forProperty("shop")) : null;
    }

}

