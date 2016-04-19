package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVendorMaster is a Querydsl query type for VendorMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVendorMaster extends EntityPathBase<VendorMaster> {

    private static final long serialVersionUID = 2120602646L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVendorMaster vendorMaster = new QVendorMaster("vendorMaster");

    public final StringPath addressLine1 = createString("addressLine1");

    public final StringPath addressLine2 = createString("addressLine2");

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath email1 = createString("email1");

    public final StringPath email2 = createString("email2");

    public final ListPath<LedgerCodeRetail, QLedgerCodeRetail> ledgerCodeRetails = this.<LedgerCodeRetail, QLedgerCodeRetail>createList("ledgerCodeRetails", LedgerCodeRetail.class, QLedgerCodeRetail.class, PathInits.DIRECT2);

    public final StringPath phone1 = createString("phone1");

    public final StringPath phone2 = createString("phone2");

    public final StringPath pin = createString("pin");

    public final ListPath<StockEntry, QStockEntry> stockEntries = this.<StockEntry, QStockEntry>createList("stockEntries", StockEntry.class, QStockEntry.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final NumberPath<Integer> vendorId = createNumber("vendorId", Integer.class);

    public final StringPath vendorName = createString("vendorName");

    public final StringPath website = createString("website");

    public QVendorMaster(String variable) {
        this(VendorMaster.class, forVariable(variable), INITS);
    }

    public QVendorMaster(Path<? extends VendorMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVendorMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVendorMaster(PathMetadata<?> metadata, PathInits inits) {
        this(VendorMaster.class, metadata, inits);
    }

    public QVendorMaster(Class<? extends VendorMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

