package com.jisr.entity.core;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.domain.Persistable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> implements Persistable<PK>, Serializable {

	private static final long serialVersionUID = -6562723448792543543L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private PK id;

	@Version
	private Integer version;

	@Override
	public PK getId() {
		return id;
	}

	protected void setId(PK id) {
		this.id = id;
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		return id == null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseEntity<?> that = (BaseEntity<?>) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return String.format("%s{id=%s}", getClass().getSimpleName(), id);
	}
}
