package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRetailPayment is a Querydsl query type for RetailPayment
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRetailPayment extends EntityPathBase<RetailPayment> {

    private static final long serialVersionUID = -353707337L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRetailPayment retailPayment = new QRetailPayment("retailPayment");

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final QMaterialTranHrd materialTranHrd;

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final NumberPath<Integer> retailPaymentId = createNumber("retailPaymentId", Integer.class);

    public final QTransactionPayment transactionPayment;

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QRetailPayment(String variable) {
        this(RetailPayment.class, forVariable(variable), INITS);
    }

    public QRetailPayment(Path<? extends RetailPayment> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRetailPayment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRetailPayment(PathMetadata<?> metadata, PathInits inits) {
        this(RetailPayment.class, metadata, inits);
    }

    public QRetailPayment(Class<? extends RetailPayment> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
        this.materialTranHrd = inits.isInitialized("materialTranHrd") ? new QMaterialTranHrd(forProperty("materialTranHrd"), inits.get("materialTranHrd")) : null;
        this.transactionPayment = inits.isInitialized("transactionPayment") ? new QTransactionPayment(forProperty("transactionPayment"), inits.get("transactionPayment")) : null;
    }

}

