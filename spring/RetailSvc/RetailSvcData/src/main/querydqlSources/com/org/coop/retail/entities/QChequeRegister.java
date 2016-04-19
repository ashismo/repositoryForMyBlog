package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QChequeRegister is a Querydsl query type for ChequeRegister
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChequeRegister extends EntityPathBase<ChequeRegister> {

    private static final long serialVersionUID = -958292176L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChequeRegister chequeRegister = new QChequeRegister("chequeRegister");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final StringPath bankName = createString("bankName");

    public final StringPath branchName = createString("branchName");

    public final DatePath<java.util.Date> chequeBounceDate = createDate("chequeBounceDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> chequeCharge = createNumber("chequeCharge", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> chequeClearAmt = createNumber("chequeClearAmt", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> chequeClearDate = createDate("chequeClearDate", java.util.Date.class);

    public final DatePath<java.util.Date> chequeDate = createDate("chequeDate", java.util.Date.class);

    public final NumberPath<Integer> chequeId = createNumber("chequeId", Integer.class);

    public final StringPath chequeNo = createString("chequeNo");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final QTransactionPayment transactionPayment;

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final NumberPath<java.math.BigDecimal> upperBankCharge = createNumber("upperBankCharge", java.math.BigDecimal.class);

    public QChequeRegister(String variable) {
        this(ChequeRegister.class, forVariable(variable), INITS);
    }

    public QChequeRegister(Path<? extends ChequeRegister> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChequeRegister(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChequeRegister(PathMetadata<?> metadata, PathInits inits) {
        this(ChequeRegister.class, metadata, inits);
    }

    public QChequeRegister(Class<? extends ChequeRegister> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.transactionPayment = inits.isInitialized("transactionPayment") ? new QTransactionPayment(forProperty("transactionPayment"), inits.get("transactionPayment")) : null;
    }

}

