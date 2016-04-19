package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGlLedgerHrd is a Querydsl query type for GlLedgerHrd
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGlLedgerHrd extends EntityPathBase<GlLedgerHrd> {

    private static final long serialVersionUID = 93628224L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGlLedgerHrd glLedgerHrd = new QGlLedgerHrd("glLedgerHrd");

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath fy = createString("fy");

    public final NumberPath<Integer> glTranId = createNumber("glTranId", Integer.class);

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final ListPath<RetailTransaction, QRetailTransaction> retailTransactions = this.<RetailTransaction, QRetailTransaction>createList("retailTransactions", RetailTransaction.class, QRetailTransaction.class, PathInits.DIRECT2);

    public final StringPath tranNo = createString("tranNo");

    public final ListPath<TransactionPayment, QTransactionPayment> transactionPayments = this.<TransactionPayment, QTransactionPayment>createList("transactionPayments", TransactionPayment.class, QTransactionPayment.class, PathInits.DIRECT2);

    public final StringPath tranType = createString("tranType");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QGlLedgerHrd(String variable) {
        this(GlLedgerHrd.class, forVariable(variable), INITS);
    }

    public QGlLedgerHrd(Path<? extends GlLedgerHrd> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGlLedgerHrd(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGlLedgerHrd(PathMetadata<?> metadata, PathInits inits) {
        this(GlLedgerHrd.class, metadata, inits);
    }

    public QGlLedgerHrd(Class<? extends GlLedgerHrd> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

