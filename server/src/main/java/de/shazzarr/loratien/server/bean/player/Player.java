package de.shazzarr.loratien.server.bean.player;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "`player`", uniqueConstraints = @UniqueConstraint(columnNames = { "uuid", "name" }))
public class Player {

	@Id
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	@Column(name = "name", unique = true, nullable = false)
	@NotEmpty(message = "The name cannot be empty")
	private String name;

	public Player() {
	}

	public Player(String name) {
		this.uuid = UUID.randomUUID().toString();
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}