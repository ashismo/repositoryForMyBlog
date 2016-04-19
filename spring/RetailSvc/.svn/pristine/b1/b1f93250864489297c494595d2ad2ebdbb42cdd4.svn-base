package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStockReturn is a Querydsl query type for StockReturn
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStockReturn extends EntityPathBase<StockReturn> {

    private static final long serialVersionUID = 1722339354L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockReturn stockReturn = new QStockReturn("stockReturn");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final NumberPath<java.math.BigDecimal> qty = createNumber("qty", java.math.BigDecimal.class);

    public final StringPath reason = createString("reason");

    public final DatePath<java.util.Date> returnDate = createDate("returnDate", java.util.Date.class);

    public final QStockEntry stockEntry;

    public final NumberPath<Integer> stockReturnId = createNumber("stockReturnId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QStockReturn(String variable) {
        this(StockReturn.class, forVariable(variable), INITS);
    }

    public QStockReturn(Path<? extends StockReturn> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockReturn(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockReturn(PathMetadata<?> metadata, PathInits inits) {
        this(StockReturn.class, metadata, inits);
    }

    public QStockReturn(Class<? extends StockReturn> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stockEntry = inits.isInitialized("stockEntry") ? new QStockEntry(forProperty("stockEntry"), inits.get("stockEntry")) : null;
    }

}

