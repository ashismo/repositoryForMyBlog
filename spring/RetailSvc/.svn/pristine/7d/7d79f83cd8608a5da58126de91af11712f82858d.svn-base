package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLedgerCodeRetail is a Querydsl query type for LedgerCodeRetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLedgerCodeRetail extends EntityPathBase<LedgerCodeRetail> {

    private static final long serialVersionUID = -1928239131L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLedgerCodeRetail ledgerCodeRetail = new QLedgerCodeRetail("ledgerCodeRetail");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath customerType = createString("customerType");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final NumberPath<Integer> glMasCode = createNumber("glMasCode", Integer.class);

    public final QMaterialGroup materialGroup;

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final StringPath purchaseSale = createString("purchaseSale");

    public final NumberPath<Integer> retailLedgerCodeId = createNumber("retailLedgerCodeId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final QVendorMaster vendorMaster;

    public QLedgerCodeRetail(String variable) {
        this(LedgerCodeRetail.class, forVariable(variable), INITS);
    }

    public QLedgerCodeRetail(Path<? extends LedgerCodeRetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLedgerCodeRetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLedgerCodeRetail(PathMetadata<?> metadata, PathInits inits) {
        this(LedgerCodeRetail.class, metadata, inits);
    }

    public QLedgerCodeRetail(Class<? extends LedgerCodeRetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.materialGroup = inits.isInitialized("materialGroup") ? new QMaterialGroup(forProperty("materialGroup"), inits.get("materialGroup")) : null;
        this.vendorMaster = inits.isInitialized("vendorMaster") ? new QVendorMaster(forProperty("vendorMaster"), inits.get("vendorMaster")) : null;
    }

}

