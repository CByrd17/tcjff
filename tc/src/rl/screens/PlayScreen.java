/**
 * 
 */
package rl.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rl.World;
import rl.WorldBuilder;

/**
 * @author cbyrd17
 *
 */
public class PlayScreen implements Screen { // NOPMD

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
	 * The middle of the X axis for the screen.
	 */
	private int centerX;

	/**
	 * The middle of the Y axis for the screen.
	 */
	private int centerY;

	/**
	 * The width of the screen.
	 */
	private int screenWidth;

	/**
	 * The height of the screen.
	 */
	private int screenHeight;

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
	 * @return the centerX
	 */
	public final int getCenterX() {
		return centerX;
	}

	/**
	 * @param newCenterX
	 *            the centerX to set
	 */
	public final void setCenterX(final int newCenterX) {
		this.centerX = newCenterX;
	}

	/**
	 * @return the centerY
	 */
	public final int getCenterY() {
		return centerY;
	}

	/**
	 * @param newCenterY
	 *            the centerY to set
	 */
	public final void setCenterY(final int newCenterY) {
		this.centerY = newCenterY;
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
		return Math.max(0, Math.min(centerX - screenWidth / 2,
				world.getWidth() - screenWidth));
	}

	/**
	 * @return how far to scroll the window on the Y axis
	 */
	public final int getScrollY() {
		return Math.max(0, Math.min(centerY - screenHeight / 2,
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

				terminal.write(world.glyph(worldX, worldY), x, y,
						world.color(worldX, worldY));
			}
		}
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
		terminal.write('X', centerX - left, centerY - top);
		terminal.write("You are having fun.", 1, 1);
		terminal.writeCenter("-- press [escape] to lose or [enter] to win --",
				MESSAGE_LINES_FROM_TOP);
	}

	/**
	 * @param moveX
	 *            the value to scroll by for the x axis
	 * @param moveY
	 *            the value to scroll by for the y axis
	 */
	private void scrollBy(final int moveX, final int moveY) {
		centerX = Math.max(0, Math.min(centerX + moveX, world.getWidth() - 1));
		centerY = Math.max(0,
				Math.min(centerY + moveY, world.getHeight() - 1));
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
			scrollBy(-1, 0);
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_L:
			scrollBy(1, 0);
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_K:
			scrollBy(0, -1);
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_J:
			scrollBy(0, 1);
			break;
		case KeyEvent.VK_Y:
			scrollBy(-1, -1);
			break;
		case KeyEvent.VK_U:
			scrollBy(1, -1);
			break;
		case KeyEvent.VK_B:
			scrollBy(-1, 1);
			break;
		case KeyEvent.VK_N:
			scrollBy(1, 1);
			break;
		default:
			showThisScreen = this;
			break;
		}

		return showThisScreen;
	}

}
