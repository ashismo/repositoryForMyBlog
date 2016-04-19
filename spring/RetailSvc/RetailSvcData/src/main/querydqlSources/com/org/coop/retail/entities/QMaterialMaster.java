package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMaterialMaster is a Querydsl query type for MaterialMaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMaterialMaster extends EntityPathBase<MaterialMaster> {

    private static final long serialVersionUID = 1552584117L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaterialMaster materialMaster = new QMaterialMaster("materialMaster");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final NumberPath<java.math.BigDecimal> lowStockLevel = createNumber("lowStockLevel", java.math.BigDecimal.class);

    public final StringPath materialDescription = createString("materialDescription");

    public final QMaterialGroup materialGroup;

    public final NumberPath<Integer> materialId = createNumber("materialId", Integer.class);

    public final StringPath materialName = createString("materialName");

    public final NumberPath<java.math.BigDecimal> mrp = createNumber("mrp", java.math.BigDecimal.class);

    public final StringPath qtyCheckRequiredInd = createString("qtyCheckRequiredInd");

    public final ListPath<RetailRateChart, QRetailRateChart> retailRateCharts = this.<RetailRateChart, QRetailRateChart>createList("retailRateCharts", RetailRateChart.class, QRetailRateChart.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> stockBalance = createNumber("stockBalance", java.math.BigDecimal.class);

    public final ListPath<StockEntry, QStockEntry> stockEntries = this.<StockEntry, QStockEntry>createList("stockEntries", StockEntry.class, QStockEntry.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> stockIn = createNumber("stockIn", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> stockOut = createNumber("stockOut", java.math.BigDecimal.class);

    public final StringPath uom = createString("uom");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QMaterialMaster(String variable) {
        this(MaterialMaster.class, forVariable(variable), INITS);
    }

    public QMaterialMaster(Path<? extends MaterialMaster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialMaster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMaterialMaster(PathMetadata<?> metadata, PathInits inits) {
        this(MaterialMaster.class, metadata, inits);
    }

    public QMaterialMaster(Class<? extends MaterialMaster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.materialGroup = inits.isInitialized("materialGroup") ? new QMaterialGroup(forProperty("materialGroup"), inits.get("materialGroup")) : null;
    }

}

