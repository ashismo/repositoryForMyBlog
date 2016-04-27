package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLedgerCodeRetailSale is a Querydsl query type for LedgerCodeRetailSale
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLedgerCodeRetailSale extends EntityPathBase<LedgerCodeRetailSale> {

    private static final long serialVersionUID = 1422401996L;

    public static final QLedgerCodeRetailSale ledgerCodeRetailSale = new QLedgerCodeRetailSale("ledgerCodeRetailSale");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath customerType = createString("customerType");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteRemark = createString("deleteRemark");

    public final NumberPath<Integer> glMasCode = createNumber("glMasCode", Integer.class);

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final NumberPath<Integer> retailLedgerCodeSaleId = createNumber("retailLedgerCodeSaleId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QLedgerCodeRetailSale(String variable) {
        super(LedgerCodeRetailSale.class, forVariable(variable));
    }

    public QLedgerCodeRetailSale(Path<? extends LedgerCodeRetailSale> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLedgerCodeRetailSale(PathMetadata<?> metadata) {
        super(LedgerCodeRetailSale.class, metadata);
    }

}

