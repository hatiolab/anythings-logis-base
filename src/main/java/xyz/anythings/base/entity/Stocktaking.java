package xyz.anythings.base.entity;

import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Index;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "stocktakings", idStrategy = GenerationRule.UUID, uniqueFields="domainId,jobDate,jobSeq,rackCd", indexes = {
	@Index(name = "ix_stocktakings_0", columnList = "domain_id,job_date,job_seq,rack_cd", unique = true)
})
public class Stocktaking extends xyz.elidom.orm.entity.basic.ElidomStampHook {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 796043866203911286L;

	@PrimaryKey
	@Column (name = "id", nullable = false, length = 40)
	private String id;

	@Column (name = "job_date", nullable = false, length = 10)
	private String jobDate;

	@Column (name = "job_seq", nullable = false, length = 12)
	private Integer jobSeq;

	@Column (name = "rack_cd", nullable = false, length = 30)
	private String rackCd;

	@Column (name = "status", length = 10)
	private String status;

	@Column (name = "remark", length = 1000)
	private String remark;
  
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobDate() {
		return jobDate;
	}

	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}

	public Integer getJobSeq() {
		return jobSeq;
	}

	public void setJobSeq(Integer jobSeq) {
		this.jobSeq = jobSeq;
	}

	public String getRackCd() {
		return rackCd;
	}

	public void setRackCd(String rackCd) {
		this.rackCd = rackCd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	
}
