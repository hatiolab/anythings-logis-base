package xyz.anythings.base.event.main;

import java.util.Map;

import xyz.anythings.base.entity.JobBatch;
import xyz.anythings.base.event.EventConstants;
import xyz.anythings.sys.event.model.EventResultSet;
import xyz.anythings.sys.event.model.SysEvent;
import xyz.elidom.util.ValueUtil;

/**
 * 작업 관련 Root 이벤트 
 * @author yang
 *
 */
public class BatchRootEvent extends SysEvent{
	

	
	/**
	 * 1 . BEFORE 
	 * 2 . AFTER
	 */
	protected short eventStep;
		
	/**
	 * 다음 이벤트를 계속 발생 할건지 flag
	 * DEFAULT = false
	 */
	protected boolean isAfterEventCancel;
	
	protected JobBatch jobBatch;
	
	protected String jobType;
	
	public BatchRootEvent(short eventStep) {
		super();
		this.setEventStep(eventStep);
		this.setAfterEventCancel(false);
	}
	
	public BatchRootEvent(long domainId, short eventStep) {
		super(domainId);
		this.setEventStep(eventStep);
		this.setAfterEventCancel(false);
	}
	
	public short getEventStep() {
		return eventStep;
	}

	public void setEventStep(short eventStep) {
		this.eventStep = eventStep;
	}

	public boolean isAfterEventCancel() {
		return isAfterEventCancel;
	}

	public void setAfterEventCancel(boolean isAfterEventCancel) {
		this.isAfterEventCancel = isAfterEventCancel;
	}
	
	public JobBatch getJobBatch() {
		return this.jobBatch;
	}
	
	public void setJobBatch(JobBatch jobBatch) {
		this.jobBatch = jobBatch;
	}
	
	public String getJobType() {
		return this.jobType;
	}
	
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	/**
	 * 배치별 셋팅값 불러오기 
	 * 설비, 화주 , 호기 stack 구조 값 
	 * @return
	 */
	public Map<String,Object> getBatchSetting() {
		return ValueUtil.newMap("", "");
	}
	
	/**
	 * 이벤트 전/후 처리 결과 셋 가져오기 
	 * @return
	 */
	public EventResultSet getEventResultSet() {
		EventResultSet resultSet = new EventResultSet();
		
		if(ValueUtil.isEqual(this.getEventStep(), EventConstants.EVENT_STEP_ALONE)) {
			// 단독 이벤트 
			resultSet.setExecuted(this.isExecuted());
			resultSet.setResult(this.getResult());
		} else if(ValueUtil.isEqual(this.getEventStep(), EventConstants.EVENT_STEP_AFTER)) {
			// 후 처리 이벤트 
			resultSet.setExecuted(this.isExecuted());
			resultSet.setResult(this.getResult());
		} else {
			// 전 처리 이벤트 
			resultSet.setAfterEventCancel(this.isExecuted() && this.isAfterEventCancel() ? true:false);
			resultSet.setExecuted(this.isExecuted());
			resultSet.setResult(this.getResult());
		}
		
		return resultSet;
	}
}
