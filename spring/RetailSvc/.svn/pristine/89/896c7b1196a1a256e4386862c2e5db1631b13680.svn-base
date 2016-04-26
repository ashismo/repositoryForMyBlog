package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDenominationRegister is a Querydsl query type for DenominationRegister
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDenominationRegister extends EntityPathBase<DenominationRegister> {

    private static final long serialVersionUID = -918776844L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDenominationRegister denominationRegister = new QDenominationRegister("denominationRegister");

    public final QBranchMaster branchMaster;

    public final QCashRegister cashRegister;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final NumberPath<Integer> denoId = createNumber("denoId", Integer.class);

    public final NumberPath<Integer> denomination = createNumber("denomination", Integer.class);

    public final StringPath noteCoin = createString("noteCoin");

    public final NumberPath<java.math.BigDecimal> noteCoinAmount = createNumber("noteCoinAmount", java.math.BigDecimal.class);

    public final NumberPath<Integer> noteCoinCount = createNumber("noteCoinCount", Integer.class);

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QDenominationRegister(String variable) {
        this(DenominationRegister.class, forVariable(variable), INITS);
    }

    public QDenominationRegister(Path<? extends DenominationRegister> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDenominationRegister(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDenominationRegister(PathMetadata<?> metadata, PathInits inits) {
        this(DenominationRegister.class, metadata, inits);
    }

    public QDenominationRegister(Class<? extends DenominationRegister> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
        this.cashRegister = inits.isInitialized("cashRegister") ? new QCashRegister(forProperty("cashRegister"), inits.get("cashRegister")) : null;
    }

}

