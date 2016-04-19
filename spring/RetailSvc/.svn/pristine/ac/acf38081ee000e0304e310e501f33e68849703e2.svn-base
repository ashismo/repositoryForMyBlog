package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRetailRateChart is a Querydsl query type for RetailRateChart
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRetailRateChart extends EntityPathBase<RetailRateChart> {

    private static final long serialVersionUID = -333453489L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRetailRateChart retailRateChart = new QRetailRateChart("retailRateChart");

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final QMaterialMaster materialMaster;

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final NumberPath<Integer> rateId = createNumber("rateId", Integer.class);

    public final DatePath<java.sql.Date> startDate = createDate("startDate", java.sql.Date.class);

    public final NumberPath<java.math.BigDecimal> unitRate = createNumber("unitRate", java.math.BigDecimal.class);

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QRetailRateChart(String variable) {
        this(RetailRateChart.class, forVariable(variable), INITS);
    }

    public QRetailRateChart(Path<? extends RetailRateChart> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRetailRateChart(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRetailRateChart(PathMetadata<?> metadata, PathInits inits) {
        this(RetailRateChart.class, metadata, inits);
    }

    public QRetailRateChart(Class<? extends RetailRateChart> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
        this.materialMaster = inits.isInitialized("materialMaster") ? new QMaterialMaster(forProperty("materialMaster"), inits.get("materialMaster")) : null;
    }

}

