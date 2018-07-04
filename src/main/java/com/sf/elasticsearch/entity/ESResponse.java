package com.sf.elasticsearch.entity;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;

public class ESResponse {

	private String index;
	private String id;
	private String type;
	private long version;
	private boolean created;
	private boolean found;

	public ESResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ESResponse(String index, String id, String type, long version) {
		super();
		this.index = index;
		this.id = id;
		this.type = type;
		this.version = version;
	}

	public ESResponse(IndexResponse indexResponse) {
		this(indexResponse.getIndex(), indexResponse.getId(), indexResponse.getType(), indexResponse.getVersion());
		this.created = indexResponse.isCreated();
		this.found = true;
	}

	public ESResponse(DeleteResponse deleteResponse) {
		this(deleteResponse.getIndex(), deleteResponse.getId(), deleteResponse.getType(), deleteResponse.getVersion());
		this.found = deleteResponse.isFound();
		this.created = false;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	@Override
	public String toString() {
		return "ESResponse [index=" + index + ", id=" + id + ", type=" + type + ", version=" + version + ", created="
				+ created + ", found=" + found + "]";
	}

}
