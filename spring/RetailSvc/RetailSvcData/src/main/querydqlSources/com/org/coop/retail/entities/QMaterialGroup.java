package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMaterialGroup is a Querydsl query type for MaterialGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMaterialGroup extends EntityPathBase<MaterialGroup> {

    private static final long serialVersionUID = -2033165108L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaterialGroup materialGroup = new QMaterialGroup("materialGroup");

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath groupDescription = createString("groupDescription");

    public final StringPath groupName = createString("groupName");

    public final ListPath<LedgerCodeRetail, QLedgerCodeRetail> ledgerCodeRetails = this.<LedgerCodeRetail, QLedgerCodeRetail>createList("ledgerCodeRetails", LedgerCodeRetail.class, QLedgerCodeRetail.class, PathInits.DIRECT2);

    public final NumberPath<Integer> materialGrpId = createNumber("materialGrpId", Integer.class);

    public final ListPath<MaterialMaster, QMaterialMaster> materialMasters = this.<MaterialMaster, QMaterialMaster>createList("materialMasters", MaterialMaster.class, QMaterialMaster.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QMaterialGroup(String variable) {
        this(MaterialGroup.class, forVariable(variable), INITS);
    }

    public QMaterialGroup(Path<? extends MaterialGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialGroup(PathMetadata<?> metadata, PathInits inits) {
        this(MaterialGroup.class, metadata, inits);
    }

    public QMaterialGroup(Class<? extends MaterialGroup> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

