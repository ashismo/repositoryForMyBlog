package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStockSell is a Querydsl query type for StockSell
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStockSell extends EntityPathBase<StockSell> {

    private static final long serialVersionUID = -83094340L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockSell stockSell = new QStockSell("stockSell");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final QMaterialTranHrd materialTranHrd;

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingRemarks = createString("passingRemarks");

    public final QRetailCustomerMaster retailCustomerMaster;

    public final NumberPath<Integer> sellId = createNumber("sellId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QStockSell(String variable) {
        this(StockSell.class, forVariable(variable), INITS);
    }

    public QStockSell(Path<? extends StockSell> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockSell(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockSell(PathMetadata<?> metadata, PathInits inits) {
        this(StockSell.class, metadata, inits);
    }

    public QStockSell(Class<? extends StockSell> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.materialTranHrd = inits.isInitialized("materialTranHrd") ? new QMaterialTranHrd(forProperty("materialTranHrd"), inits.get("materialTranHrd")) : null;
        this.retailCustomerMaster = inits.isInitialized("retailCustomerMaster") ? new QRetailCustomerMaster(forProperty("retailCustomerMaster"), inits.get("retailCustomerMaster")) : null;
    }

}

