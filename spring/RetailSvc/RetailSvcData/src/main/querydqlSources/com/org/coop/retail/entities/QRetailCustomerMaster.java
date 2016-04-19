package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRetailCustomerMaster is a Querydsl query type for RetailCustomerMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRetailCustomerMaster extends EntityPathBase<RetailCustomerMaster> {

    private static final long serialVersionUID = -504097265L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRetailCustomerMaster retailCustomerMaster = new QRetailCustomerMaster("retailCustomerMaster");

    public final StringPath addressLine1 = createString("addressLine1");

    public final StringPath addressLine2 = createString("addressLine2");

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final NumberPath<Integer> customerId = createNumber("customerId", Integer.class);

    public final StringPath customerName = createString("customerName");

    public final StringPath customerType = createString("customerType");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath email1 = createString("email1");

    public final StringPath email2 = createString("email2");

    public final StringPath phone1 = createString("phone1");

    public final StringPath phone2 = createString("phone2");

    public final StringPath pin = createString("pin");

    public final NumberPath<Integer> refCustomerId = createNumber("refCustomerId", Integer.class);

    public final ListPath<StockSell, QStockSell> stockSells = this.<StockSell, QStockSell>createList("stockSells", StockSell.class, QStockSell.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final StringPath website = createString("website");

    public QRetailCustomerMaster(String variable) {
        this(RetailCustomerMaster.class, forVariable(variable), INITS);
    }

    public QRetailCustomerMaster(Path<? extends RetailCustomerMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRetailCustomerMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRetailCustomerMaster(PathMetadata<?> metadata, PathInits inits) {
        this(RetailCustomerMaster.class, metadata, inits);
    }

    public QRetailCustomerMaster(Class<? extends RetailCustomerMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

