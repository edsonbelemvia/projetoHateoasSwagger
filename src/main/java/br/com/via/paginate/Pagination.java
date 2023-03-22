package br.com.via.paginate;

public class Pagination {
 
 
        public Integer page;
       
        public Integer size;  

        public Integer numberOfElements;
   
        public Integer totalOfElements;
 
        public Integer totalPages;
        
        public Pagination() {
		 
		}

        
        
		public Pagination(Integer page, Integer size, Integer numberOfElements, Integer totalOfElements,
				Integer totalPages) {
			super();
			this.page = page;
			this.size = size;
			this.numberOfElements = numberOfElements;
			this.totalOfElements = totalOfElements;
			this.totalPages = totalPages;
		}

       

		@Override
		public String toString() {
			return "Pagination [page=" + page + ", size=" + size + ", numberOfElements=" + numberOfElements
					+ ", totalOfElements=" + totalOfElements + ", totalPages=" + totalPages + "]";
		}



		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public Integer getSize() {
			return size;
		}

		public void setSize(Integer size) {
			this.size = size;
		}

		public Integer getNumberOfElements() {
			return numberOfElements;
		}

		public void setNumberOfElements(Integer numberOfElements) {
			this.numberOfElements = numberOfElements;
		}

		public Integer getTotalOfElements() {
			return totalOfElements;
		}

		public void setTotalOfElements(Integer totalOfElements) {
			this.totalOfElements = totalOfElements;
		}

		public Integer getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(Integer totalPages) {
			this.totalPages = totalPages;
		}
        
        
}
