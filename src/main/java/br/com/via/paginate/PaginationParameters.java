package br.com.via.paginate;

public class PaginationParameters {
	  public Integer maxPageSize = 50;
      public Integer PageNumber  = 1;
      private Integer _pageSize = 50;
      
      public PaginationParameters() {
		// TODO Auto-generated constructor stub
	}
      
     
      
	public PaginationParameters(Integer maxPageSize, Integer pageNumber, Integer _pageSize) {
		super();
		this.maxPageSize = maxPageSize;
		PageNumber = pageNumber;
		this._pageSize = _pageSize;
	}


	@Override
	public String toString() {
		return "PaginationParameters [maxPageSize=" + maxPageSize + ", PageNumber=" + PageNumber + ", _pageSize="
				+ _pageSize + "]";
	}



	public Integer getMaxPageSize() {
		return maxPageSize;
	}
	public void setMaxPageSize(Integer maxPageSize) {
		this.maxPageSize = maxPageSize;
	}
	public Integer getPageNumber() {
		return PageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		PageNumber = pageNumber;
	}
	public Integer get_pageSize() {
		return _pageSize;
	}
	public void set_pageSize(Integer _pageSize) {
		this._pageSize = _pageSize;
	}
      
      
}
