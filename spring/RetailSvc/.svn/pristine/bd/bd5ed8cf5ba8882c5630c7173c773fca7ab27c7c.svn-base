package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBankMaster is a Querydsl query type for BankMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBankMaster extends EntityPathBase<BankMaster> {

    private static final long serialVersionUID = 963432714L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBankMaster bankMaster = new QBankMaster("bankMaster");

    public final StringPath bankAddress = createString("bankAddress");

    public final NumberPath<Integer> bankId = createNumber("bankId", Integer.class);

    public final StringPath bankName = createString("bankName");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final QGlMaster glMaster;

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemarks = createString("passingAuthRemarks");

    public final StringPath phone1 = createString("phone1");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QBankMaster(String variable) {
        this(BankMaster.class, forVariable(variable), INITS);
    }

    public QBankMaster(Path<? extends BankMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBankMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBankMaster(PathMetadata<?> metadata, PathInits inits) {
        this(BankMaster.class, metadata, inits);
    }

    public QBankMaster(Class<? extends BankMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.glMaster = inits.isInitialized("glMaster") ? new QGlMaster(forProperty("glMaster"), inits.get("glMaster")) : null;
    }

}

