/**
 * 
 */
package rl;

import java.awt.Color;

/**
 * @author cbyrd17
 *
 */
public class World {

	/**
	 * A double dimension array that contains the tiles that make up the world.
	 */
	private final Tile[][] tiles;

	/**
	 * Used to store the width of the world at creation.
	 */
	private final int width;

	/**
	 * Used to store the height of the world at creation.
	 */
	private final int height;

	/**
	 * @return a copy of the tiles that make up the world
	 */
	public final Tile[][] getTiles() {
		return (Tile[][]) tiles.clone();
	}

	/**
	 * @param xOffset
	 *            - x offset into the double dimension array of tiles in the
	 *            world
	 * @param yOffset
	 *            - y offset into the double dimension array of tiles in the
	 *            world
	 * @return - the value of the tile at the x and y offset in the double
	 *         dimension array of tiles in the world
	 */
	public final Tile tile(final int xOffset, final int yOffset) {
		Tile requestedTile;
		if (xOffset < 0 || xOffset >= width || yOffset < 0
				|| yOffset >= height) {
			requestedTile = Tile.BOUNDS;
		} else {
			requestedTile = tiles[xOffset][yOffset];
		}
		return requestedTile;
	}

	/**
	 * @param xOffset
	 *            - x offset into the double dimension array of tiles in the
	 *            world
	 * @param yOffset
	 *            - y offset into the double dimension array of tiles in the
	 *            world
	 * @return - the character value of the tile at the x and y offset in the
	 *         double dimension array of tiles in the world
	 */
	public final char glyph(final int xOffset, final int yOffset) {
		return Tile.getGlyph(tile(xOffset, yOffset));
	}

	/**
	 * @param xOffset
	 *            - x offset into the double dimension array of tiles in the
	 *            world
	 * @param yOffset
	 *            - y offset into the double dimension array of tiles in the
	 *            world
	 * @return - the color value of the tile at the x and y offset in the double
	 *         dimension array of tiles in the world
	 */
	public final Color color(final int xOffset, final int yOffset) {
		return Tile.getColor(tile(xOffset, yOffset));
	}

	/**
	 * @return the value of width
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * @return the value of height
	 */
	public final int getHeight() {
		return height;
	}

	/**
	 * @param tilesToUse
	 *            a pre-filled set of tiles to create the world
	 */
	public World(final Tile[]... tilesToUse) {
		tiles = (Tile[][]) tilesToUse.clone();
		this.width = tiles.length;
		this.height = tiles[0].length;
	}

	/**
	 * @param xDigLocation
	 *            the x value to try to dig through
	 * @param yDigLocation
	 *            the y value to try to dig through
	 */
	public final void dig(final int xDigLocation, final int yDigLocation) {
		final Tile location = tile(xDigLocation, yDigLocation);
		if (location.isDiggable()) { // NOPMD
			tiles[xDigLocation][yDigLocation] = Tile.FLOOR;
		}
	}

	/**
	 * @param creature
	 *            the creature we're trying to place
	 */
	public final void addAtEmptyLocation(final Creature creature) {
		int potentialXLocation;
		int potentialYLocation;
		Tile potentialLocation;
		do {
			potentialXLocation = (int) (Math.random() * width);
			potentialYLocation = (int) (Math.random() * height);
			potentialLocation = tile(potentialXLocation, potentialYLocation);
		} while (!potentialLocation.isGround()); // NOPMD

		creature.setXValue(potentialXLocation);
		creature.setYValue(potentialYLocation);
	}

}
