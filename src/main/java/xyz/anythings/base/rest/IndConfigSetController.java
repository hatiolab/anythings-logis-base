package xyz.anythings.base.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import xyz.anythings.base.entity.IndConfig;
import xyz.anythings.base.entity.IndConfigSet;
import xyz.anythings.base.entity.JobBatch;
import xyz.anythings.base.service.impl.ConfigSetService;
import xyz.anythings.base.util.LogisEntityUtil;
import xyz.anythings.sys.model.KeyValue;
import xyz.elidom.dbist.dml.Filter;
import xyz.elidom.dbist.dml.Page;
import xyz.elidom.orm.system.annotation.service.ApiDesc;
import xyz.elidom.orm.system.annotation.service.ServiceDesc;
import xyz.elidom.sys.entity.Domain;
import xyz.elidom.sys.system.service.AbstractRestService;

@RestController
@Transactional
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/rest/ind_config_set")
@ServiceDesc(description = "IndConfigSet Service API")
public class IndConfigSetController extends AbstractRestService {

	@Autowired
	private ConfigSetService configSetService;

	@Override
	protected Class<?> entityClass() {
		return IndConfigSet.class;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Search (Pagination) By Search Conditions")
	public Page<?> index(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "limit", required = false) Integer limit,
			@RequestParam(name = "select", required = false) String select,
			@RequestParam(name = "sort", required = false) String sort,
			@RequestParam(name = "query", required = false) String query) {
		return this.search(this.entityClass(), page, limit, select, sort, query);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Find one by ID")
	public IndConfigSet findOne(@PathVariable("id") String id) {
		return this.getOne(this.entityClass(), id);
	}

	@RequestMapping(value = "/{id}/exist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Check exists By ID")
	public Boolean isExist(@PathVariable("id") String id) {
		return this.isExistOne(this.entityClass(), id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiDesc(description = "Create")
	public IndConfigSet create(@RequestBody IndConfigSet input) {
		return this.createOne(input);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Update")
	public IndConfigSet update(@PathVariable("id") String id, @RequestBody IndConfigSet input) {
		return this.updateOne(input);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Delete")
	public void delete(@PathVariable("id") String id) {
		this.deleteOne(this.entityClass(), id);
	}

	@RequestMapping(value = "/update_multiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Create, Update or Delete multiple at one time")
	public Boolean multipleUpdate(@RequestBody List<IndConfigSet> list) {
		return this.cudMultipleData(this.entityClass(), list);
	}

	@RequestMapping(value = "/{id}/include_details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Find One included all details by ID")
	public Map<String, Object> findDetails(@PathVariable("id") String id) {
		return this.findOneIncludedDetails(id);
	}

	@RequestMapping(value = "/{id}/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Search detail list by master ID")
	public List<IndConfig> findIndConfig(@PathVariable("id") String id) {
		xyz.elidom.dbist.dml.Query query = new xyz.elidom.dbist.dml.Query();
		query.addFilter(new Filter("indConfigSetId", id));
		query.addOrder("category", true);
		query.addOrder("name", true);
		return this.queryManager.selectList(IndConfig.class, query);
	}

	@RequestMapping(value = "/{id}/items/update_multiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Create, Update, Delete multiple details at one time")
	public List<IndConfig> updateIndConfig(@PathVariable("id") String id, @RequestBody List<IndConfig> list) {
		this.cudMultipleData(IndConfig.class, list);
		return this.findIndConfig(id);
	}
	
	@RequestMapping(value = "/build_template", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiDesc(description = "Build template config set")
	public IndConfigSet buildTemplateConfigSet() {
		return this.configSetService.buildTemplateIndConfigSet(Domain.currentDomainId());
	}
	
	@RequestMapping(value = "/{id}/copy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiDesc(description = "Copy template config set")
	public IndConfigSet copyConfigSet(@PathVariable("id") String sourceConfigSetId) {
		return this.configSetService.copyIndConfigSet(Domain.currentDomainId(), sourceConfigSetId);
	}
	
	@RequestMapping(value = "/build_config_set/{batch_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Build config set by batch id")
	public IndConfigSet buildBatchConfigSet(@PathVariable("batch_id") String batchiId) {
		JobBatch batch = LogisEntityUtil.findEntityById(true, JobBatch.class, batchiId);
		return this.configSetService.buildIndConfigSet(batch);
	}

	@RequestMapping(value = "/clear_config_set/{batch_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Clear config set by batch id")
	public IndConfigSet clearBatchConfigSet(@PathVariable("batch_id") String batchiId) {
		JobBatch batch = LogisEntityUtil.findEntityById(true, JobBatch.class, batchiId);
		return this.configSetService.buildIndConfigSet(batch);
	}
	
	@RequestMapping(value = "/config_value/{batch_id}/{config_key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description = "Config key by batch id")
	public KeyValue getConfigValue(@PathVariable("batch_id") String batchiId, @PathVariable("config_key") String configKey) {
		JobBatch batch = LogisEntityUtil.findEntityById(true, JobBatch.class, batchiId);
		String value = this.configSetService.getIndConfigValue(batch, configKey);
		return new KeyValue(configKey, value);
	}

}