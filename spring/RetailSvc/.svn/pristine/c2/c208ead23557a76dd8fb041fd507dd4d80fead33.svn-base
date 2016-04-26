package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLedgerCodeRetailPurchase is a Querydsl query type for LedgerCodeRetailPurchase
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLedgerCodeRetailPurchase extends EntityPathBase<LedgerCodeRetailPurchase> {

    private static final long serialVersionUID = -2118837242L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLedgerCodeRetailPurchase ledgerCodeRetailPurchase = new QLedgerCodeRetailPurchase("ledgerCodeRetailPurchase");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final NumberPath<Integer> glMasCode = createNumber("glMasCode", Integer.class);

    public final QMaterialGroup materialGroup;

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final NumberPath<Integer> retailLedgerCodeId = createNumber("retailLedgerCodeId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final QVendorMaster vendorMaster;

    public QLedgerCodeRetailPurchase(String variable) {
        this(LedgerCodeRetailPurchase.class, forVariable(variable), INITS);
    }

    public QLedgerCodeRetailPurchase(Path<? extends LedgerCodeRetailPurchase> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLedgerCodeRetailPurchase(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLedgerCodeRetailPurchase(PathMetadata<?> metadata, PathInits inits) {
        this(LedgerCodeRetailPurchase.class, metadata, inits);
    }

    public QLedgerCodeRetailPurchase(Class<? extends LedgerCodeRetailPurchase> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.materialGroup = inits.isInitialized("materialGroup") ? new QMaterialGroup(forProperty("materialGroup"), inits.get("materialGroup")) : null;
        this.vendorMaster = inits.isInitialized("vendorMaster") ? new QVendorMaster(forProperty("vendorMaster"), inits.get("vendorMaster")) : null;
    }

}

