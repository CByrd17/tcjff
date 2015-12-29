/**
 * 
 */
package rl.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rl.Creature;
import rl.CreatureFactory;
import rl.World;
import rl.WorldBuilder;

/**
 * @author cbyrd17
 *
 */
public class PlayScreen implements Screen { // NOPMD

	/**
	 * Default number of fungi to create.
	 */
	private static final int NUMBER_OF_FUNGI_TO_CREATE = 8;

	/**
	 * How high to make the screen.
	 */
	private static final int DEFAULT_SCREEN_HEIGHT = 21;

	/**
	 * How wide to make the screen.
	 */
	private static final int DEFAULT_SCREEN_WIDTH = 80;

	/**
	 * The state of the world at this time.
	 */
	private World world;

	/**
	 * The width of the screen.
	 */
	private int screenWidth;

	/**
	 * The height of the screen.
	 */
	private int screenHeight;

	/**
	 * You.
	 */
	private Creature player;

	/**
	 * @return the player
	 */
	public final Creature getPlayer() {
		return player;
	}

	/**
	 * @param newPlayer
	 *            the player to set
	 */
	public final void setPlayer(final Creature newPlayer) {
		player = newPlayer;
	}

	/**
	 * @return the world
	 */
	public final World getWorld() {
		return world;
	}

	/**
	 * @param worldToUse
	 *            the world to set
	 */
	public final void setWorld(final World worldToUse) {
		world = worldToUse;
	}

	/**
	 * @return the screenWidth
	 */
	public final int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * @param newScreenWidth
	 *            the screenWidth to set
	 */
	public final void setScreenWidth(final int newScreenWidth) {
		this.screenWidth = newScreenWidth;
	}

	/**
	 * @return the screenHeight
	 */
	public final int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * @param newScreenHeight
	 *            the screenHeight to set
	 */
	public final void setScreenHeight(final int newScreenHeight) {
		this.screenHeight = newScreenHeight;
	}

	/**
	 * Constructor.
	 */
	public PlayScreen() {
		screenWidth = DEFAULT_SCREEN_WIDTH;
		screenHeight = DEFAULT_SCREEN_HEIGHT;
		createWorld();
		final CreatureFactory creatureFactory = new CreatureFactory(world);
		createCreatures(creatureFactory);
	}

	/**
	 * @param creatureFactory
	 *            use this to instantiate creatures of different types
	 */
	private void createCreatures(final CreatureFactory creatureFactory) {
		player = creatureFactory.newPlayer();

		for (int i = 0; i < NUMBER_OF_FUNGI_TO_CREATE; i++) {
			creatureFactory.newFungus();
		}
	}

	/**
	 * Creates the world.
	 */
	private void createWorld() {
		final WorldBuilder worldBuilder = new WorldBuilder(90, 31);
		world = new World(worldBuilder.getTiles());
	}

	/**
	 * @return how far to scroll the window on the X axis
	 */
	public final int getScrollX() {
		return Math.max(0, Math.min(player.getXValue() - screenWidth / 2,
				world.getWidth() - screenWidth));
	}

	/**
	 * @return how far to scroll the window on the Y axis
	 */
	public final int getScrollY() {
		return Math.max(0, Math.min(player.getYValue() - screenHeight / 2,
				world.getHeight() - screenHeight));
	}

	/**
	 * @param terminal
	 *            where to output
	 * @param left
	 *            how far from the left we are already
	 * @param top
	 *            how far from the top we are already
	 */
	private void displayTiles(final AsciiPanel terminal, final int left,
			final int top) {
		for (int x = 0; x < screenWidth; x++) {
			for (int y = 0; y < screenHeight; y++) {
				final int worldX = x + left;
				final int worldY = y + top;

				writeOutTile(worldX, worldY, left, top, terminal, x, y);
			}
		}
	}

	/**
	 * @param worldX
	 *            the x coordinate of tile in the world coordinate system
	 * @param worldY
	 *            the y coordinate of tile in the world coordinate system
	 * @param left
	 *            the offset from the left side of the displayed world
	 * @param top
	 *            the offset from the top side of the displayed world
	 * @param terminal
	 *            where to write the tile
	 * @param terminalX
	 *            the x coordinate in the terminal of where to write the glyph
	 * @param terminalY
	 *            the y coordinate in the terminal of where to write the glyph
	 */
	private void writeOutTile(final int worldX, final int worldY,
			final int left, final int top, final AsciiPanel terminal,
			final int terminalX, final int terminalY) {
		Creature creature = world.getCreature(worldX, worldY);
		if (isValid(creature)) {
			creature = new Creature(creature);
			terminal.write(creature.getGlyph(), creature.getXValue() - left,
					creature.getYValue() - top, creature.getColor());
		} else {
			terminal.write(world.glyph(worldX, worldY), terminalX, terminalY,
					world.color(worldX, worldY));
		}

	}

	/**
	 * @param creature
	 *            the Creature to check for null
	 * @return if the creature is null or not
	 */
	private boolean isValid(final Creature creature) {
		boolean valid;
		if (creature == null) {
			valid = false;
		} else {
			valid = true;
		}
		return valid;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see rl.screens.Screen#displayOutput(asciiPanel.AsciiPanel)
	 */
	@Override
	public final void displayOutput(final AsciiPanel terminal) {
		final int left = getScrollX();
		final int top = getScrollY();

		displayTiles(terminal, left, top);

		terminal.writeCenter("-- press [escape] to lose or [enter] to win --",
				MESSAGE_LINES_FROM_TOP);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see rl.screens.Screen#respondToUserInput(java.awt.event.KeyEvent)
	 */
	@Override
	public final Screen respondToUserInput(final KeyEvent key) { // NOPMD
		Screen showThisScreen = this; // NOPMD

		switch (key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			showThisScreen = new LoseScreen();
			break;
		case KeyEvent.VK_ENTER:
			showThisScreen = new WinScreen();
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_H:
			player.moveBy(-1, 0);
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_L:
			player.moveBy(1, 0);
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_K:
			player.moveBy(0, -1);
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_J:
			player.moveBy(0, 1);
			break;
		case KeyEvent.VK_Y:
			player.moveBy(-1, -1);
			break;
		case KeyEvent.VK_U:
			player.moveBy(1, -1);
			break;
		case KeyEvent.VK_B:
			player.moveBy(-1, 1);
			break;
		case KeyEvent.VK_N:
			player.moveBy(1, 1);
			break;
		default:
			showThisScreen = this;
			break;
		}

		return showThisScreen;
	}

}
