package com.epam.library.domain;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	private static final long serialVersionUID = -9015253583316207322L;
	private int id;
	
	public Entity(int id) {
		this.id = id;
	}
	
	public Entity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + ". ";
	}
	
}
