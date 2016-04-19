package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGlLedgerDtl is a Querydsl query type for GlLedgerDtl
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGlLedgerDtl extends EntityPathBase<GlLedgerDtl> {

    private static final long serialVersionUID = 93624450L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGlLedgerDtl glLedgerDtl1 = new QGlLedgerDtl("glLedgerDtl1");

    public final NumberPath<Integer> accountNo = createNumber("accountNo", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath drCr = createString("drCr");

    public final QGlLedgerDtl glLedgerDtl;

    public final ListPath<GlLedgerDtl, QGlLedgerDtl> glLedgerDtls = this.<GlLedgerDtl, QGlLedgerDtl>createList("glLedgerDtls", GlLedgerDtl.class, QGlLedgerDtl.class, PathInits.DIRECT2);

    public final QGlMaster glMaster;

    public final NumberPath<Integer> glTranDtlId = createNumber("glTranDtlId", Integer.class);

    public final StringPath passinAuthRemarks = createString("passinAuthRemarks");

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath remarks = createString("remarks");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QGlLedgerDtl(String variable) {
        this(GlLedgerDtl.class, forVariable(variable), INITS);
    }

    public QGlLedgerDtl(Path<? extends GlLedgerDtl> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGlLedgerDtl(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGlLedgerDtl(PathMetadata<?> metadata, PathInits inits) {
        this(GlLedgerDtl.class, metadata, inits);
    }

    public QGlLedgerDtl(Class<? extends GlLedgerDtl> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.glLedgerDtl = inits.isInitialized("glLedgerDtl") ? new QGlLedgerDtl(forProperty("glLedgerDtl"), inits.get("glLedgerDtl")) : null;
        this.glMaster = inits.isInitialized("glMaster") ? new QGlMaster(forProperty("glMaster"), inits.get("glMaster")) : null;
    }

}

