package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMaterialTranHrd is a Querydsl query type for MaterialTranHrd
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMaterialTranHrd extends EntityPathBase<MaterialTranHrd> {

    private static final long serialVersionUID = -1022075780L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaterialTranHrd materialTranHrd = new QMaterialTranHrd("materialTranHrd");

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> billAmt = createNumber("billAmt", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> billDate = createDate("billDate", java.util.Date.class);

    public final StringPath billNo = createString("billNo");

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final NumberPath<java.math.BigDecimal> discount = createNumber("discount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> grossTotal = createNumber("grossTotal", java.math.BigDecimal.class);

    public final ListPath<MaterialTranDtl, QMaterialTranDtl> materialTranDtls = this.<MaterialTranDtl, QMaterialTranDtl>createList("materialTranDtls", MaterialTranDtl.class, QMaterialTranDtl.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> outstandingAmt = createNumber("outstandingAmt", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> paidAmt = createNumber("paidAmt", java.math.BigDecimal.class);

    public final StringPath paidBy = createString("paidBy");

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final ListPath<RetailPayment, QRetailPayment> retailPayments = this.<RetailPayment, QRetailPayment>createList("retailPayments", RetailPayment.class, QRetailPayment.class, PathInits.DIRECT2);

    public final ListPath<RetailTransaction, QRetailTransaction> retailTransactions = this.<RetailTransaction, QRetailTransaction>createList("retailTransactions", RetailTransaction.class, QRetailTransaction.class, PathInits.DIRECT2);

    public final ListPath<StockSell, QStockSell> stockSells = this.<StockSell, QStockSell>createList("stockSells", StockSell.class, QStockSell.class, PathInits.DIRECT2);

    public final NumberPath<Integer> tranId = createNumber("tranId", Integer.class);

    public final StringPath tranNo = createString("tranNo");

    public final StringPath tranType = createString("tranType");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final NumberPath<java.math.BigDecimal> vat = createNumber("vat", java.math.BigDecimal.class);

    public QMaterialTranHrd(String variable) {
        this(MaterialTranHrd.class, forVariable(variable), INITS);
    }

    public QMaterialTranHrd(Path<? extends MaterialTranHrd> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialTranHrd(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialTranHrd(PathMetadata<?> metadata, PathInits inits) {
        this(MaterialTranHrd.class, metadata, inits);
    }

    public QMaterialTranHrd(Class<? extends MaterialTranHrd> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

