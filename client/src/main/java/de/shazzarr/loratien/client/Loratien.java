package de.shazzarr.loratien.client;

import de.shazzarr.loratien.client.locale.Translation;
import de.shazzarr.loratien.client.properties.Settings;
import de.shazzarr.loratien.client.screen.Screen;
import de.shazzarr.loratien.server.bean.game.Game;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status.Family;

public class Loratien implements Runnable {
	public static Settings properties = new Settings();
	public static Translation locale = new Translation();

	private final Screen screen;

	private Game game;

	public static void main(String[] args) {
		final Loratien loratien = new Loratien();
		loratien.run();
	}

	public Loratien() {

		final int screenWidth = Integer.parseInt(Loratien.properties.get("screenWidth"));
		final int playFieldRadius = Integer.parseInt(Loratien.properties.get("screenRadius"));
		final double hexWidth = screenWidth / (2.0 + 1.5 * playFieldRadius);
		final double hexHeight = hexWidth / 2 * Math.sqrt(3);
		final int screenHeight = (int) (hexHeight * (playFieldRadius + 1) + 30);

		this.game = loadGame();
		this.game = this.game != null ? this.game : newGame();
		this.screen = new Screen("Game: " + game.getUuid(), screenWidth, screenHeight, playFieldRadius);
	}

	private Game loadGame() {

		final Client client = ClientBuilder.newClient();

		final WebTarget resource = client.target("http://localhost:8090/game/load");

		final Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);

		final Response response = request.get();

		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			System.out.println("Success! " + response.getStatus());
			System.out.println(response.getEntity());
		} else {
			System.out.println("ERROR! " + response.getStatus());
			System.out.println(response.getEntity());
		}
		return null;
	}

	private Game newGame() {
		return null;
	}

	/**
	 * The run() method starts the game hosts the infinite while-loop and display
	 * the FPS of the game.
	 */
	@Override
	public void run() {
		final int fps = Integer.parseInt(Loratien.properties.get("framesPerSecond"));
		final double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now = 0L;
		long timer = 0L;
		int ticks = 0;
		long lastTime = System.nanoTime();

		while (true) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if (1 <= delta) {
				// world.evolve();
				screen.render();
				ticks++;
				delta--;
			}
			if (1000000000 <= timer) {
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	}
}
