package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerAccount is a Querydsl query type for CustomerAccount
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerAccount extends EntityPathBase<CustomerAccount> {

    private static final long serialVersionUID = 922372771L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerAccount customerAccount = new QCustomerAccount("customerAccount");

    public final QAccount account;

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final QCustomer customer;

    public final NumberPath<Integer> customerAccountId = createNumber("customerAccountId", Integer.class);

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final StringPath primaryHolderInd = createString("primaryHolderInd");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public QCustomerAccount(String variable) {
        this(CustomerAccount.class, forVariable(variable), INITS);
    }

    public QCustomerAccount(Path<? extends CustomerAccount> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerAccount(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerAccount(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerAccount.class, metadata, inits);
    }

    public QCustomerAccount(Class<? extends CustomerAccount> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
    }

}

