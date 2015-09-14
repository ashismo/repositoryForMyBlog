package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the schedule database table.
 * 
 */

@NamedQueries ({
	@NamedQuery(name="getScheduleLeftItemsByDoctors",
			query="select d.doctorName as leftItemName, d.doctorId as doctorId from Doctor d " +
					"where (d.dateOfRelease >= :firstDayOfWeek or d.dateOfRelease is null) and " +
					"(d.dateOfAssociation is not null and d.dateOfAssociation <= :lastDayOfWeek)"
	),
	@NamedQuery(name="getScheduleByScheduleAttr",
			query="select s from Schedule s where s.scheduleDate = :scheduleDate and " +
					"s.doctor.doctorId = :doctorId and s.scheduleDay = :scheduleDay and " +
					"s.scheduleTime = :scheduleTime"
	),
	@NamedQuery(name="getScheduleOfAWeek",
			query="select s.doctor.doctorId as doctorId," +
					"concat(s.doctor.doctorId,',',s.scheduleValue) as scheduleValue," +
					"concat(s.scheduleTime,'-',s.scheduleDay) as rightItemClass," +
					"s.scheduleDay as scheduleDay," +
					"s.scheduleTime as scheduleTime," +
					"s.scheduleLookupId as scheduleLookupId," +
					"s.scheduleId as scheduleId " +
					"from Schedule s where s.scheduleDate between :scheduleStartDate and :scheduleEndDate"
	),
	@NamedQuery(name="getScheduleEntitiesOfAWeek",
			query="select s from Schedule s where s.scheduleDate between :scheduleStartDate and :scheduleEndDate"
	),
	@NamedQuery(name="getSchedulesByScheduleIds",
			query="select s from Schedule s where s.scheduleDate between :scheduleStartDate and :scheduleEndDate"
	)
})


@Entity
@Table(name="schedule")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SCHEDULE_ID", unique=true, nullable=false)
	private int scheduleId;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

    @Temporal( TemporalType.DATE)
	@Column(name="SCHEDULE_DATE")
	private Date scheduleDate;

	@Column(name="SCHEDULE_DAY", length=50)
	private String scheduleDay;

	@Column(name="SCHEDULE_LOOKUP_ID")
	private int scheduleLookupId;

	@Column(name="SCHEDULE_TIME", length=50)
	private String scheduleTime;

	@Column(name="SCHEDULE_VALUE", length=50)
	private String scheduleValue;

	//bi-directional many-to-one association to Doctor
    @ManyToOne
	@JoinColumn(name="DOCTOR_ID")
	private Doctor doctor;

	//bi-directional many-to-one association to WholeSeller
    @ManyToOne
	@JoinColumn(name="WHOLESELLER_ID")
	private WholeSeller wholeSeller;

	//bi-directional many-to-one association to MedRep
    @ManyToOne
	@JoinColumn(name="MED_REP_ID")
	private MedRep medRep;

	//bi-directional many-to-one association to Customer
    @ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

    public Schedule() {
    }

	public int getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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

	public Date getScheduleDate() {
		return this.scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleDay() {
		return this.scheduleDay;
	}

	public void setScheduleDay(String scheduleDay) {
		this.scheduleDay = scheduleDay;
	}

	public int getScheduleLookupId() {
		return this.scheduleLookupId;
	}

	public void setScheduleLookupId(int scheduleLookupId) {
		this.scheduleLookupId = scheduleLookupId;
	}

	public String getScheduleTime() {
		return this.scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getScheduleValue() {
		return this.scheduleValue;
	}

	public void setScheduleValue(String scheduleValue) {
		this.scheduleValue = scheduleValue;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public WholeSeller getWholeSeller() {
		return this.wholeSeller;
	}

	public void setWholeSeller(WholeSeller wholeSeller) {
		this.wholeSeller = wholeSeller;
	}
	
	public MedRep getMedRep() {
		return this.medRep;
	}

	public void setMedRep(MedRep medRep) {
		this.medRep = medRep;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}