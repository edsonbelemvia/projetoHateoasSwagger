package br.com.via.interfaces;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryByProductExecutor<T> {
	
	<S extends T> Optional<S> findOne(Example<S> var1);

	<S extends T> Iterable<S> findAll(Example<S> var1);

	<S extends T> Iterable<S> findAll(Example<S> var1, Sort var2);

	<S extends T> Page<S> findAll(Example<S> var1, Pageable var2);

	<S extends T> long count(Example<S> var1);

	<S extends T> boolean exists(Example<S> var1);
}
