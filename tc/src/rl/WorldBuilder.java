/**
 * 
 */
package rl;

/**
 * @author cbyrd17
 *
 */
public class WorldBuilder {
	/**
	 * How many times to run through the smoothing algorithm when building a
	 * world.
	 */
	private static final int NUMBER_OF_TIMES_TO_SMOOTH = 8;

	/**
	 * This is representative of the percentage of how often floors will be
	 * generated.
	 */
	private static final double FLOOR_OR_WALL_CUTOFF = 0.5;

	/**
	 * How wide to make this world.
	 */
	private final int width;

	/**
	 * How high to make this world.
	 */
	private final int height;

	/**
	 * The tiles that make up the world.
	 */
	private Tile[][] tiles;

	/**
	 * @return the tiles
	 */
	public final Tile[][] getTiles() {
		return tiles.clone();
	}

	/**
	 * @param tilesToSet
	 *            the tiles to set
	 */
	public final void setTiles(final Tile[]... tilesToSet) {
		this.tiles = tilesToSet.clone();
	}

	/**
	 * @return the width
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public final int getHeight() {
		return height;
	}

	/**
	 * @param desiredWidth
	 *            how wide the world should be
	 * @param desiredHeight
	 *            how tall the world should be
	 */
	public WorldBuilder(final int desiredWidth, final int desiredHeight) {
		width = desiredWidth;
		height = desiredHeight;
		tiles = new Tile[desiredWidth][desiredHeight];
		makeCaves();
	}

	/**
	 * @return a whole new world
	 */
	public final World build() {
		return new World(tiles);
	}

	/**
	 * 
	 */
	private void randomizeTiles() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (Math.random() < FLOOR_OR_WALL_CUTOFF) {
					tiles[x][y] = Tile.FLOOR;
				} else {
					tiles[x][y] = Tile.WALL;
				}
			}
		}
	}

	/**
	 * @param times
	 *            how many times to run through the smoothing process
	 */
	private void smooth(final int times) {
		Tile[][] tiles2 = new Tile[width][height]; // NOPMD
		for (int time = 0; time < times; time++) {

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int floors = 0; // NOPMD
					int rocks = 0; // NOPMD

					for (int ox = -1; ox < 2; ox++) {
						for (int oy = -1; oy < 2; oy++) {
							if (x + ox < 0 || x + ox >= width || y + oy < 0
									|| y + oy >= height) {
								continue;
							}

							if (tiles[x + ox][y + oy] == Tile.FLOOR) {
								floors++; // NOPMD
							} else {
								rocks++; // NOPMD
							}
						}
					}
					Tile currentValue;
					if (floors >= rocks) {
						currentValue = Tile.FLOOR;
					} else {
						currentValue = Tile.WALL;
					}
					tiles2[x][y] = currentValue; // NOPMD
				}
			}
			tiles = tiles2;
		}
	}

	/**
	 * Makes caves in the world.
	 */
	public final void makeCaves() {
		randomizeTiles();
		smooth(NUMBER_OF_TIMES_TO_SMOOTH);
	}

}
