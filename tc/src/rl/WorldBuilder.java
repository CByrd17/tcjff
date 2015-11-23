/**
 * 
 */
package rl;

/**
 * @author cbyrd17
 *
 */
public class WorldBuilder {
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
	 * @param desiredWidth
	 *            how wide the world should be
	 * @param desiredHeight
	 *            how tall the world should be
	 */
	public WorldBuilder(final int desiredWidth, final int desiredHeight) {
		width = desiredWidth;
		height = desiredHeight;
		tiles = new Tile[desiredWidth][desiredHeight];
	}

	/**
	 * @return a whole new world
	 */
	public final World build() {
		return new World(tiles);
	}

	/**
	 * @return copy with filled in randomized tiles
	 */
	private WorldBuilder randomizeTiles() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (Math.random() < FLOOR_OR_WALL_CUTOFF) {
					tiles[x][y] = Tile.FLOOR;
				} else {
					tiles[x][y] = Tile.WALL;
				}
			}
		}
		return this;
	}

	/**
	 * @param times
	 *            how many times to run through the smoothing process
	 * @return a WorldBuilder that has had its tiles modified to make more caves
	 *         and passages vs totally random patterns of walls and floors
	 */
	private WorldBuilder smooth(final int times) {
		Tile[][] tiles2 = new Tile[width][height];
		for (int time = 0; time < times; time++) {

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int floors = 0;
					int rocks = 0;

					for (int ox = -1; ox < 2; ox++) {
						for (int oy = -1; oy < 2; oy++) {
							if (x + ox < 0 || x + ox >= width || y + oy < 0
									|| y + oy >= height) {
								continue;
							}

							if (tiles[x + ox][y + oy] == Tile.FLOOR) {
								floors++;
							} else {
								rocks++;
							}
						}
					}
					if (floors >= rocks) {
						tiles2[x][y] = Tile.FLOOR;
					} else {
						tiles2[x][y] = Tile.WALL;
					}
				}
			}
			tiles = tiles2;
		}
		return this;
	}

	/**
	 * @return a WorldBuilder with tiles full of caves
	 */
	public final WorldBuilder makeCaves() {
		WorldBuilder wb = randomizeTiles();

		return wb.smooth(NUMBER_OF_TIMES_TO_SMOOTH);
	}

}
