package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVatRegNo is a Querydsl query type for VatRegNo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVatRegNo extends EntityPathBase<VatRegNo> {

    private static final long serialVersionUID = -702841832L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVatRegNo vatRegNo = new QVatRegNo("vatRegNo");

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final DatePath<java.util.Date> endDate = createDate("endDate", java.util.Date.class);

    public final DatePath<java.util.Date> startDate = createDate("startDate", java.util.Date.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final NumberPath<Integer> vatId = createNumber("vatId", Integer.class);

    public final StringPath vatRegistrationNo = createString("vatRegistrationNo");

    public QVatRegNo(String variable) {
        this(VatRegNo.class, forVariable(variable), INITS);
    }

    public QVatRegNo(Path<? extends VatRegNo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVatRegNo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVatRegNo(PathMetadata<?> metadata, PathInits inits) {
        this(VatRegNo.class, metadata, inits);
    }

    public QVatRegNo(Class<? extends VatRegNo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

