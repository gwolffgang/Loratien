package de.shazzarr.loratien.client.screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

public class Screen {

	private JFrame frame;
	private Canvas canvas;
	private List<Hex> grid;

	private final String title;
	private final int width;
	private final int height;
	private final int radius;

	/**
	 * The Screen() constructor is used to initiate the needed variables and call
	 * createDisplay().
	 *
	 * @param title  the title of the GUI game window
	 * @param width  the width of the GUI game window
	 * @param height the height of the GUI game window
	 * @param radius the radius of the circle of hexes shown in the GUI
	 */
	public Screen(final String title, final int width, final int height, final int radius) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.radius = radius;

		createScreen();
	}

	/**
	 * Creates and shows the new window to display the game. It is called
	 * automatically when the program is started.
	 */
	private void createScreen() {
		frame = new JFrame(this.title);
		frame.setSize(this.width, this.height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setJMenuBar(createJMenuBar());

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(this.width, this.height));
		canvas.setMinimumSize(new Dimension(this.width, this.height));
		canvas.setMaximumSize(new Dimension(this.width, this.height));

		frame.add(this.canvas);
		frame.pack();

		grid = new ArrayList<>();
		final int cols = this.radius * 2 + 1;
		final int rows = this.radius * 2 + 1;
		final double diameter = this.width / (this.radius * 2 * 1.5 + 2.0);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				grid.add(new Hex(col, row, diameter));
			}
		}
	}

	private JMenuBar createJMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		final JMenu game = new JMenu("Loratien");
		final JMenuItem newGame = new JMenuItem("New game");
		newGame.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		game.add(newGame);
		game.addSeparator();
		final JMenuItem saveGame = new JMenuItem("Save game");
		saveGame.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		game.add(saveGame);
		final JMenuItem loadGame = new JMenuItem("Load game");
		loadGame.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		game.add(loadGame);
		game.addSeparator();
		final JMenuItem exitGame = new JMenuItem("Exit");
		exitGame.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		exitGame.addActionListener(e -> exitGameClicked());
		game.add(exitGame);
		menuBar.add(game);
		final JMenu settings = new JMenu("Settings");
		menuBar.add(settings);
		final JMenu help = new JMenu("Help");
		menuBar.add(help);
		return menuBar;
	}

	private void exitGameClicked() {
		if (JOptionPane.showConfirmDialog(new JFrame(), "Do you want to close Loratien? ", "Exit Loratien?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public List<Hex> getGrid() {
		return grid;
	}

	public Hex getCenterHex() {
		return getHex(this.radius + 1, this.radius + 1);
	}

	public Hex getHex(int col, int row) {
		return grid.get(row * this.width + col);
	}

	/**
	 * The render() method calculates and updates all data. It is part of the
	 * threading functionality and is called automatically.
	 */
	public void render() {
		final BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		final Graphics g = bs.getDrawGraphics();
		for (int col = 0; col < this.radius * 2 + 1; col++) {
			for (int row = 0; row < this.radius * 2 + 1; row++) {
				g.setColor(Color.black);
				g.drawPolygon(getHex(col, row));
				g.setColor(new Color(0, 255, 0));
				g.fillPolygon(getHex(col, row));
			}
		}
		bs.show();
		g.dispose();
	}
}