package de.shazzarr.loratien.server;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;

import de.shazzarr.loratien.server.bean.game.Game;
import de.shazzarr.loratien.server.locale.Translation;
import de.shazzarr.loratien.server.properties.Settings;

public class Loratien {
	public static Settings properties = new Settings();
	public static Translation locale = new Translation();
	public static Engine engine = new Engine();
	private static Loratien loratien = new Loratien();
	private static JettyServer jettyServer = new JettyServer();
	private static Game currentGame = null;

	private final List<Game> games = new ArrayList<>();

	public static void main(String[] args) {
		currentGame = loratien.newGame(1000, 1000);
		jettyServer.start();
	}

	public static Game getCurrentGame() {
		return currentGame;
	}

	public List<Game> getGames() {
		return games;
	}

	public Game newGame(int worldWidth, int worldHeight) {
		final Game game = new Game(worldWidth, worldHeight);
		this.games.add(game);
		return game;
	}

}
