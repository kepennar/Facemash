package org.kepennar.facemash.datatable;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class PageRequest implements Serializable {

	public enum Order {
		ASC("asc"),
		DESC("desc");
		
		private String key;
		Order(String key) {
			this.key = key;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public static Order getByKey(String key) {
			for (Order order : Order.values()) {
				if (order.getKey().equals(key)) {
					return order;
				}
			}
			return null;
		}
		 
	}
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private static final int DEFAULT_PAGE_SIZE = 10;

	
	
	@JsonProperty("iDisplayLength")
	private int iDisplayLength;
	@JsonProperty("iDisplayStart")
	private int iDisplayStart;
	@JsonProperty("sSearch")
	private String sSearch;
	@JsonProperty("orderBy")
	private String orderBy;
	@JsonProperty("orderSens")
	private Order orderSens;

	public PageRequest() {
		this.iDisplayStart = 0;
		this.iDisplayLength = DEFAULT_PAGE_SIZE;
	}

	public int getPageNumber() {

		int page = 0;

		if (iDisplayStart >= this.getiDisplayLength()) {
			page = iDisplayStart / this.getiDisplayLength();
		}

		return page;
	}

	@JsonGetter(value = "offset")
	public int getOffset() {
		return getPageNumber() * iDisplayLength;
	}


	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}


	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Order getOrderSens() {
		return orderSens;
	}

	public void setOrderSens(Order orderSens) {
		this.orderSens = orderSens;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageRequest)) {
			return false;
		}

		PageRequest that = (PageRequest) obj;

		boolean pageEqual = this.getPageNumber() == that.getPageNumber();
		boolean sizeEqual = this.getiDisplayLength() == that.getiDisplayLength();

		return pageEqual && sizeEqual;
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + getPageNumber();
		result = 31 * result + getiDisplayLength();

		return result;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(PageRequest.class).add("iDisplayLength", iDisplayLength)
				.add("iDisplayStart", iDisplayStart).add("search", sSearch).toString();
	}

}