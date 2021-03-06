package se.kth.mobdev.ruontime.persistence;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

public interface IGenericDao<T extends IEntity> {

	public abstract T load(Integer id) throws EntityNotFoundException;

	public abstract T get(Integer id);

	public abstract List<T> getAll();

	public abstract T save(T object);

	public abstract void save(T... objects);

	public abstract void delete(T object) throws UnsupportedOperationException;

}