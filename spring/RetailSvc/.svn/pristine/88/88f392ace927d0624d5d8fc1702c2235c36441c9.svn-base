package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = 1116779562L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomer customer = new QCustomer("customer");

    public final StringPath aadharNo = createString("aadharNo");

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final StringPath activeInd = createString("activeInd");

    public final QBranchMaster branchMaster;

    public final StringPath cifNo = createString("cifNo");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final ListPath<CustomerAccount, QCustomerAccount> customerAccounts = this.<CustomerAccount, QCustomerAccount>createList("customerAccounts", CustomerAccount.class, QCustomerAccount.class, PathInits.DIRECT2);

    public final NumberPath<Integer> customerId = createNumber("customerId", Integer.class);

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath drivingLicence = createString("drivingLicence");

    public final StringPath emailId = createString("emailId");

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final DateTimePath<java.sql.Timestamp> lastUsedDate = createDateTime("lastUsedDate", java.sql.Timestamp.class);

    public final StringPath middleName = createString("middleName");

    public final StringPath mobile1 = createString("mobile1");

    public final StringPath mobile2 = createString("mobile2");

    public final StringPath panNo = createString("panNo");

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final StringPath salute = createString("salute");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final StringPath voterId = createString("voterId");

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomer(PathMetadata<?> metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

