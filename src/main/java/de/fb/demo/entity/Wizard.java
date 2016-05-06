package de.fb.demo.entity;

import java.util.UUID;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A Wizard is a simple entity, which is used in this WizBook demo application.
 */
public class Wizard {

	private UUID id;
	@NotEmpty
	private String name;
	private WIZARD_HAT hat;
	@NotEmpty
	private String phoneNumber;

	public Wizard() {
	}

	public Wizard(String name, WIZARD_HAT hat, String phone) {
		this(name, hat, phone, UUID.randomUUID());
	}

	public Wizard(String name, WIZARD_HAT hat, String phone, UUID id) {
		this.id = id;
		this.name = name;
		this.hat = hat;
		this.phoneNumber = phone;
	}

	public String getName() {
		return name;
	}

	public WIZARD_HAT getHat() {
		return hat;
	}

	public UUID getId() {
		return id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hat == null) ? 0 : hat.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
		Wizard other = (Wizard) obj;
		if (hat != other.hat)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wizard [id=" + id + ", name=" + name + ", hat=" + hat + ", phoneNumber=" + phoneNumber + "]";
	}

}
