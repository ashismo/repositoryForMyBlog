package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGlMaster is a Querydsl query type for GlMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGlMaster extends EntityPathBase<GlMaster> {

    private static final long serialVersionUID = -1004840973L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGlMaster glMaster = new QGlMaster("glMaster");

    public final NumberPath<Integer> annexureId = createNumber("annexureId", Integer.class);

    public final ListPath<GlLedgerDtl, QGlLedgerDtl> glLedgerDtls = this.<GlLedgerDtl, QGlLedgerDtl>createList("glLedgerDtls", GlLedgerDtl.class, QGlLedgerDtl.class, PathInits.DIRECT2);

    public final NumberPath<Integer> glMasCode = createNumber("glMasCode", Integer.class);

    public final StringPath glMasDesc = createString("glMasDesc");

    public final QGlSubHeader glSubHeader;

    public QGlMaster(String variable) {
        this(GlMaster.class, forVariable(variable), INITS);
    }

    public QGlMaster(Path<? extends GlMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGlMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGlMaster(PathMetadata<?> metadata, PathInits inits) {
        this(GlMaster.class, metadata, inits);
    }

    public QGlMaster(Class<? extends GlMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.glSubHeader = inits.isInitialized("glSubHeader") ? new QGlSubHeader(forProperty("glSubHeader"), inits.get("glSubHeader")) : null;
    }

}

