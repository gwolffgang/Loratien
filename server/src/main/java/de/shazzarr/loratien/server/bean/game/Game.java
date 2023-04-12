package de.shazzarr.loratien.server.bean.game;

import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import de.shazzarr.loratien.server.bean.player.Player;
import de.shazzarr.loratien.server.bean.worldmap.Worldmap;
import de.shazzarr.loratien.server.entity.tileobject.lifeform.Humanoid;

@Entity
@Table(name = "`game`", uniqueConstraints = @UniqueConstraint(columnNames = { "uuid" }))
public class Game {
	@Id
	@Column(name = "uuid", unique = true, nullable = false)
	private final String uuid;
	@Column(name = "worldmap", nullable = false)
	private final Worldmap worldmap;
	@Column(name = "player", nullable = false)
	private Player player = new Player();
	@Column(name = "characters", nullable = false)
	private ArrayList<Humanoid> characters = new ArrayList<>();

	public Game(int worldWidth, int worldHeight) {
		this.uuid = UUID.randomUUID().toString();
		this.worldmap = new Worldmap(worldWidth, worldHeight);
	}

	public String getUuid() {
		return uuid;
	}

	public Worldmap getWorldmap() {
		return worldmap;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Humanoid> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<Humanoid> characters) {
		this.characters = characters;
	}
}
