package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFyClose is a Querydsl query type for FyClose
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFyClose extends EntityPathBase<FyClose> {

    private static final long serialVersionUID = -972292327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFyClose fyClose = new QFyClose("fyClose");

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final DateTimePath<java.sql.Timestamp> endDate = createDateTime("endDate", java.sql.Timestamp.class);

    public final StringPath fy = createString("fy");

    public final NumberPath<Integer> fyId = createNumber("fyId", Integer.class);

    public final NumberPath<Byte> isClosed = createNumber("isClosed", Byte.class);

    public final DateTimePath<java.sql.Timestamp> startDate = createDateTime("startDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final DateTimePath<java.sql.Timestamp> yearClosedOn = createDateTime("yearClosedOn", java.sql.Timestamp.class);

    public QFyClose(String variable) {
        this(FyClose.class, forVariable(variable), INITS);
    }

    public QFyClose(Path<? extends FyClose> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFyClose(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFyClose(PathMetadata<?> metadata, PathInits inits) {
        this(FyClose.class, metadata, inits);
    }

    public QFyClose(Class<? extends FyClose> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

