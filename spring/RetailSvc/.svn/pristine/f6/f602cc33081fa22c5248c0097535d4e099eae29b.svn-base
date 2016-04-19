package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTransactionPayment is a Querydsl query type for TransactionPayment
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTransactionPayment extends EntityPathBase<TransactionPayment> {

    private static final long serialVersionUID = 1957422740L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTransactionPayment transactionPayment = new QTransactionPayment("transactionPayment");

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final ListPath<AdvanceRegister, QAdvanceRegister> advanceRegisters = this.<AdvanceRegister, QAdvanceRegister>createList("advanceRegisters", AdvanceRegister.class, QAdvanceRegister.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final QBranchMaster branchMaster;

    public final ListPath<CardRegister, QCardRegister> cardRegisters = this.<CardRegister, QCardRegister>createList("cardRegisters", CardRegister.class, QCardRegister.class, PathInits.DIRECT2);

    public final ListPath<CashRegister, QCashRegister> cashRegisters = this.<CashRegister, QCashRegister>createList("cashRegisters", CashRegister.class, QCashRegister.class, PathInits.DIRECT2);

    public final ListPath<ChequeRegister, QChequeRegister> chequeRegisters = this.<ChequeRegister, QChequeRegister>createList("chequeRegisters", ChequeRegister.class, QChequeRegister.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final ListPath<CreditRegister, QCreditRegister> creditRegisters = this.<CreditRegister, QCreditRegister>createList("creditRegisters", CreditRegister.class, QCreditRegister.class, PathInits.DIRECT2);

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final QGlLedgerHrd glLedgerHrd;

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final NumberPath<Integer> paymentId = createNumber("paymentId", Integer.class);

    public final StringPath paymentType = createString("paymentType");

    public final ListPath<RetailPayment, QRetailPayment> retailPayments = this.<RetailPayment, QRetailPayment>createList("retailPayments", RetailPayment.class, QRetailPayment.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QTransactionPayment(String variable) {
        this(TransactionPayment.class, forVariable(variable), INITS);
    }

    public QTransactionPayment(Path<? extends TransactionPayment> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTransactionPayment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTransactionPayment(PathMetadata<?> metadata, PathInits inits) {
        this(TransactionPayment.class, metadata, inits);
    }

    public QTransactionPayment(Class<? extends TransactionPayment> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
        this.glLedgerHrd = inits.isInitialized("glLedgerHrd") ? new QGlLedgerHrd(forProperty("glLedgerHrd"), inits.get("glLedgerHrd")) : null;
    }

}

