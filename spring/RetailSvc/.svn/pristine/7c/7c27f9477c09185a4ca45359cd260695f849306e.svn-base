package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRetailDocument is a Querydsl query type for RetailDocument
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRetailDocument extends EntityPathBase<RetailDocument> {

    private static final long serialVersionUID = 1399013002L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRetailDocument retailDocument = new QRetailDocument("retailDocument");

    public final QBranchMaster branchMaster;

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final NumberPath<Integer> docId = createNumber("docId", Integer.class);

    public final NumberPath<Integer> docSize = createNumber("docSize", Integer.class);

    public final StringPath docType = createString("docType");

    public final ArrayPath<byte[], Byte> document = createArray("document", byte[].class);

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final StringPath title = createString("title");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QRetailDocument(String variable) {
        this(RetailDocument.class, forVariable(variable), INITS);
    }

    public QRetailDocument(Path<? extends RetailDocument> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRetailDocument(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRetailDocument(PathMetadata<?> metadata, PathInits inits) {
        this(RetailDocument.class, metadata, inits);
    }

    public QRetailDocument(Class<? extends RetailDocument> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

