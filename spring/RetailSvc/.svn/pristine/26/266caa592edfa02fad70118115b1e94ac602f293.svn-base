package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCreditRegister is a Querydsl query type for CreditRegister
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCreditRegister extends EntityPathBase<CreditRegister> {

    private static final long serialVersionUID = -990642168L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCreditRegister creditRegister = new QCreditRegister("creditRegister");

    public final QAccount account;

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final NumberPath<Integer> creditId = createNumber("creditId", Integer.class);

    public final StringPath creditType = createString("creditType");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final QTransactionPayment transactionPayment;

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QCreditRegister(String variable) {
        this(CreditRegister.class, forVariable(variable), INITS);
    }

    public QCreditRegister(Path<? extends CreditRegister> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCreditRegister(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCreditRegister(PathMetadata<?> metadata, PathInits inits) {
        this(CreditRegister.class, metadata, inits);
    }

    public QCreditRegister(Class<? extends CreditRegister> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
        this.transactionPayment = inits.isInitialized("transactionPayment") ? new QTransactionPayment(forProperty("transactionPayment"), inits.get("transactionPayment")) : null;
    }

}

