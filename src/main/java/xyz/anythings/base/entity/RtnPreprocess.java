package xyz.anythings.base.entity;

import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Index;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "tb_rtn_preprocess", idStrategy = GenerationRule.UUID, uniqueFields="domainId,batchId,comCd,skuCd,locCd", indexes = {
	@Index(name = "ix_tb_rtn_preprocess_0", columnList = "loc_cd,com_cd,sku_cd,batch_id,domain_id", unique = true),
	@Index(name = "ix_tb_rtn_preprocess_1", columnList = "order_group,batch_id")
})
public class RtnPreprocess extends xyz.elidom.orm.entity.basic.ElidomStampHook {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 360615349892354508L;

	@PrimaryKey
	@Column (name = "id", nullable = false, length = 40)
	private String id;

	@Column (name = "batch_id", nullable = false, length = 40)
	private String batchId;

	@Column(name = "com_cd", length = 32)
	private String comCd;
	
	@Column (name = "sku_cd", nullable = false, length = 50)
	private String skuCd;

	@Column (name = "sku_nm", length = 385)
	private String skuNm;

	@Column (name = "chute_no", length = 30)
	private String chuteNo;
	
	@Column (name = "region_cd", length = 30)
	private String regionCd;

	@Column (name = "region_nm", length = 100)
	private String regionNm;

	@Column (name = "loc_cd", length = 30)
	private String locCd;

	@Column (name = "zone_cd", length = 30)
	private String zoneCd;

	@Column (name = "order_group", length = 100)
	private String orderGroup;

	@Column (name = "order_cust_qty", nullable = false, length = 19)
	private Integer orderCustQty;

	@Column (name = "order_pcs_qty", nullable = false, length = 19)
	private Integer orderPcsQty;

	@Column (name = "job_data_count", length = 12)
	private Integer jobDataCount;

	@Column (name = "clone_group_id", length = 40)
	private String cloneGroupId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getComCd() {
		return comCd;
	}

	public void setComCd(String comCd) {
		this.comCd = comCd;
	}

	public String getSkuCd() {
		return skuCd;
	}

	public void setSkuCd(String skuCd) {
		this.skuCd = skuCd;
	}


	public String getSkuNm() {
		return skuNm;
	}

	public void setSkuNm(String skuNm) {
		this.skuNm = skuNm;
	}

	public String getChuteNo() {
		return chuteNo;
	}

	public void setChuteNo(String chuteNo) {
		this.chuteNo = chuteNo;
	}

	public String getRegionCd() {
		return regionCd;
	}

	public void setRegionCd(String regionCd) {
		this.regionCd = regionCd;
	}

	public String getRegionNm() {
		return regionNm;
	}

	public void setRegionNm(String regionNm) {
		this.regionNm = regionNm;
	}

	public String getCloneGroupId() {
		return cloneGroupId;
	}

	public void setCloneGroupId(String cloneGroupId) {
		this.cloneGroupId = cloneGroupId;
	}

	public String getLocCd() {
		return locCd;
	}

	public void setLocCd(String locCd) {
		this.locCd = locCd;
	}

	public String getZoneCd() {
		return zoneCd;
	}

	public void setZoneCd(String zoneCd) {
		this.zoneCd = zoneCd;
	}

	public String getOrderGroup() {
		return orderGroup;
	}

	public void setOrderGroup(String orderGroup) {
		this.orderGroup = orderGroup;
	}

	public Integer getOrderCustQty() {
		return orderCustQty;
	}

	public void setOrderCustQty(Integer orderCustQty) {
		this.orderCustQty = orderCustQty;
	}

	public Integer getOrderPcsQty() {
		return orderPcsQty;
	}

	public void setOrderPcsQty(Integer orderPcsQty) {
		this.orderPcsQty = orderPcsQty;
	}

	public Integer getJobDataCount() {
		return jobDataCount;
	}

	public void setJobDataCount(Integer jobDataCount) {
		this.jobDataCount = jobDataCount;
	}
}
