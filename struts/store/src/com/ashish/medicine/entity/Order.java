package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the order database table.
 * 
 */

/*
select m.MEDICINE_NAME, c.COMPANY_NAME, md.BATCH_NAME, m.MEDICINE_ID, 
sum(md.STOCK - md.SOLDOUT_STOCK) as availableStock,md.EXP_DATE, md.MED_DOSE, md.MED_WEIGHT, md.MED_TYPE,
o.ORDER_DATE as orderDateActual,
o.ORDER_EXECUTION_DATE as orderExecutionDateActual,
o.ORDER_STATUS as orderStatus
from `medicine` m left outer join `medicine_details` md
on m.MEDICINE_ID=md.MEDICINE_ID 
join `company` c on c.COMPANY_ID=m.COMPANY_ID 
left outer join `order` o on o.MEDICINE_ID=m.MEDICINE_ID
where md.EXP_DATE > CURRENT_DATE
group by md.MED_DOSE,md.MED_WEIGHT HAVING availableStock<50 order by m.MEDICINE_NAME



select od.ORDER_ID, od.MEDICINE_DETAILS, od.ORDER_DATE,od.ORDER_EXECUTION_DATE,od.ORDER_DESC,
m.MEDICINE_ID, m.MEDICINE_NAME,c.COMPANY_NAME,
md.MEDICINE_DETAILS_ID, md.BATCH_NAME,
md.MFG_DATE, md.MED_DOSE, md.MED_WEIGHT, md.MED_TYPE
from `order_details` od 
join `medicine` m on od.MEDICINE_ID = m.MEDICINE_ID 
left outer join `company` c on c.COMPANY_ID = m.COMPANY_ID
inner join `medicine_details` md on m.MEDICINE_ID=md.MEDICINE_ID
where od.ORDER_EXECUTION_DATE is not null and md.PURCHASE_DATE=od.ORDER_EXECUTION_DATE
and od.ORDER_EXECUTION_DATE between '2012-09-09' and '2012-09-10'

*/

@NamedQueries ({
	@NamedQuery(name="getOrderByMedicineId",
			query="select o from Order o where o.medicine.medicineId = :medicineId " +
					"and o.orderStatus is null"
	),
	@NamedQuery(name="getOrderByOrderId",
			query="select o from Order o where o.orderId = :orderId"
	),
	@NamedQuery(name="getOrderByMedicineDetails",
			query="select o from Order o where o.medicineDetails = :medicineDetails and o.orderStatus is null"
	)
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "searchLowerStocksMap",
        columns= {
        			@ColumnResult(name="medicineName"),
                    @ColumnResult(name="companyName"),
                    @ColumnResult(name="batchName"),
//                    @ColumnResult(name="availableStock"),
                    @ColumnResult(name="expDateActual"),
                    @ColumnResult(name="medDose"),
                    @ColumnResult(name="medWeight"),
                    @ColumnResult(name="medType")
                }
        )
})

@NamedNativeQueries({

        @NamedNativeQuery(name = "searchLowerStocks",
                     query = "select m.MEDICINE_NAME as medicineName, " +
                     		"m.MEDICINE_ID as medicineId, " +
                     		"c.COMPANY_NAME as companyName, " +
                     		"md.BATCH_NAME as batchName, " +
							"sum(md.STOCK - md.SOLDOUT_STOCK) as availableStock," +
							"md.EXP_DATE as expDateActual, md.MED_DOSE as medDose, " +
							"md.MED_WEIGHT as medWeight, md.MED_TYPE as medType " +
							"from MEDICINE m left outer join MEDICINE_DETAILS md " +
							"on m.MEDICINE_ID=md.MEDICINE_ID " +
							"join COMPANY c on c.COMPANY_ID=m.COMPANY_ID " + 
							"where md.EXP_DATE >= CURRENT_DATE and md.MED_TYPE like :medType " +
							"group by md.MEDICINE_ID " +
							"HAVING sum(md.STOCK - md.SOLDOUT_STOCK)<= :availableStock " +
							"order by m.MEDICINE_NAME asc",
                     resultSetMapping  = "searchLowerStocksMap"
            ),
            @NamedNativeQuery(name = "searchRecentNotes",
                    query = "select od.ORDER_ID as orderId, " +
			            		"od.ORDER_DATE as orderDateActual," +
			            		"od.ORDER_EXECUTION_DATE as orderExecutionDateActual," +
			            		"od.ORDER_DESC as orderDesc,od.ORDER_STATUS as orderStatus " +
								"from ORDER_DETAILS od " +
								"where od.MEDICINE_ID is null " +
								"and od.ORDER_DATE between :startDate and :endDate order by od.ORDER_DATE",
                    resultSetMapping  = "searchLowerStocksMap"
               ),
                @NamedNativeQuery(name = "searchExecutedOrders",
                        query = "select od.ORDER_ID as orderId, " +
                        		"od.ORDER_DATE as orderDateActual,od.ORDER_EXECUTION_DATE as orderExecutionDateActual," +
                        		"od.ORDER_DESC as orderDesc, od.ORDER_STATUS as orderStatus," +
								"m.MEDICINE_ID as medicineId, m.MEDICINE_NAME as medicineName," +
								"c.COMPANY_NAME as companyName," +
								"md.BATCH_NAME as batchName," +
								"md.MFG_DATE as mfgDateActual, md.EXP_DATE as expDateActual," +
								"md.MED_DOSE as medDose, md.MED_WEIGHT as medWeight, md.MED_TYPE as medType " +
								"from ORDER_DETAILS od " +
								"join MEDICINE m on od.MEDICINE_ID = m.MEDICINE_ID " + 
								"left outer join COMPANY c on c.COMPANY_ID = m.COMPANY_ID " +
								"inner join MEDICINE_DETAILS md on m.MEDICINE_ID=md.MEDICINE_ID " +
								"where od.ORDER_EXECUTION_DATE is not null and md.PURCHASE_DATE=od.ORDER_EXECUTION_DATE " +
								"and od.ORDER_EXECUTION_DATE between :startDate and :endDate",
                        resultSetMapping  = "searchLowerStocksMap"
                   )
})


@Entity
@Table(name="order_details")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ORDER_ID", unique=true, nullable=false)
	private int orderId;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

    @Temporal( TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	@Column(name="ORDER_DESC", length=500)
	private String orderDesc;
	
	@Column(name="MEDICINE_DETAILS", length=200)
	private String medicineDetails;

    @Temporal( TemporalType.DATE)
	@Column(name="ORDER_EXECUTION_DATE")
	private Date orderExecutionDate;

	@Column(name="ORDER_STATUS", length=20)
	private String orderStatus;

	//bi-directional many-to-one association to Medicine
    @ManyToOne
	@JoinColumn(name="MEDICINE_ID")
	private Medicine medicine;

    public Order() {
    }

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Timestamp getDbAddTs() {
		return this.dbAddTs;
	}

	public void setDbAddTs(Timestamp dbAddTs) {
		this.dbAddTs = dbAddTs;
	}

	public int getDbAddUser() {
		return this.dbAddUser;
	}

	public void setDbAddUser(int dbAddUser) {
		this.dbAddUser = dbAddUser;
	}

	public Timestamp getDbUpdTs() {
		return this.dbUpdTs;
	}

	public void setDbUpdTs(Timestamp dbUpdTs) {
		this.dbUpdTs = dbUpdTs;
	}

	public int getDbUpdUser() {
		return this.dbUpdUser;
	}

	public void setDbUpdUser(int dbUpdUser) {
		this.dbUpdUser = dbUpdUser;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDesc() {
		return this.orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Date getOrderExecutionDate() {
		return this.orderExecutionDate;
	}

	public void setOrderExecutionDate(Date orderExecutionDate) {
		this.orderExecutionDate = orderExecutionDate;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public String getMedicineDetails() {
		return medicineDetails;
	}

	public void setMedicineDetails(String medicineDetails) {
		this.medicineDetails = medicineDetails;
	}
	
	
	
}