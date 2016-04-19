package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStockEntry is a Querydsl query type for StockEntry
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStockEntry extends EntityPathBase<StockEntry> {

    private static final long serialVersionUID = 1706389576L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockEntry stockEntry = new QStockEntry("stockEntry");

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> availableQty = createNumber("availableQty", java.math.BigDecimal.class);

    public final StringPath batch = createString("batch");

    public final DatePath<java.util.Date> billDate = createDate("billDate", java.util.Date.class);

    public final StringPath billNo = createString("billNo");

    public final QBranchMaster branchMaster;

    public final DatePath<java.util.Date> challanDate = createDate("challanDate", java.util.Date.class);

    public final StringPath challanNo = createString("challanNo");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath entryType = createString("entryType");

    public final DateTimePath<java.sql.Timestamp> expDate = createDateTime("expDate", java.sql.Timestamp.class);

    public final QMaterialMaster materialMaster;

    public final ListPath<MaterialTranDtl, QMaterialTranDtl> materialTranDtls = this.<MaterialTranDtl, QMaterialTranDtl>createList("materialTranDtls", MaterialTranDtl.class, QMaterialTranDtl.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> mfgDate = createDateTime("mfgDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> parentStockId = createNumber("parentStockId", Integer.class);

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final NumberPath<java.math.BigDecimal> purchasePrice = createNumber("purchasePrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> qty = createNumber("qty", java.math.BigDecimal.class);

    public final NumberPath<Integer> stockId = createNumber("stockId", Integer.class);

    public final ListPath<StockReturn, QStockReturn> stockReturns = this.<StockReturn, QStockReturn>createList("stockReturns", StockReturn.class, QStockReturn.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final QVendorMaster vendorMaster;

    public QStockEntry(String variable) {
        this(StockEntry.class, forVariable(variable), INITS);
    }

    public QStockEntry(Path<? extends StockEntry> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockEntry(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockEntry(PathMetadata<?> metadata, PathInits inits) {
        this(StockEntry.class, metadata, inits);
    }

    public QStockEntry(Class<? extends StockEntry> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
        this.materialMaster = inits.isInitialized("materialMaster") ? new QMaterialMaster(forProperty("materialMaster"), inits.get("materialMaster")) : null;
        this.vendorMaster = inits.isInitialized("vendorMaster") ? new QVendorMaster(forProperty("vendorMaster"), inits.get("vendorMaster")) : null;
    }

}

