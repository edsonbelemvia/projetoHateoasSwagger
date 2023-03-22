package br.com.via.paginate;

import java.util.ArrayList;
import java.util.List;

public class PagedList<T> {
	 
		        public List<Pagination> content =new ArrayList<Pagination>();
		      
		        public Pagination pageable   = new Pagination();        

		        public PagedList(List<T> items, int count, int pageNumber, int pageSize)
		        {
		            content.AddRange(items);
		            pageable.page = pageNumber;
		            pageable.size = pageSize;
		            pageable.numberOfElements=items.Count;
		            pageable.totalOfElements = count;            
		            pageable.totalPages = (int)Math.Ceiling(count / (double)pageSize);         
		        }

		        public static PagedList<T> ToPagedList(IEnumerable<T> source, int pageNumber, int pageSize)
		        {
		            var count = source.Count();
		            var items = source.Skip((pageNumber - 1) * pageSize).Take(pageSize).ToList();
		            return new PagedList<T>(items, count, pageNumber, pageSize);
		        }
}
