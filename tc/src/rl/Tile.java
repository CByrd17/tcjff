/**
 * 
 */
package rl;

import java.awt.Color;

import asciiPanel.AsciiPanel;

/**
 * @author cbyrd17
 *
 */
public enum Tile {

	/**
	 * This is the character that will represent floors.
	 */
	FLOOR((char) 250, AsciiPanel.yellow),

	/**
	 * This is the character that will represent walls.
	 */
	WALL((char) 177, AsciiPanel.yellow),

	/**
	 * This is an "out-of-bounds" character.
	 */
	BOUNDS('x', AsciiPanel.brightBlack);

	/**
	 * Used to store the ascii character that is shown on the tile.
	 */
	private char glyph;

	/**
	 * @return the value of the tile's glyph
	 */
	public char glyph() {
		return glyph;
	}

	/**
	 * Used to store the color to use in this tile.
	 */
	private Color color; // NOPMD -- complains about fields not at top of class,
							// not valid for enum classes

	/**
	 * @return the value of the tile's color
	 */
	public Color color() {
		return color;
	}

	/**
	 * @param tile
	 *            - a tile whose glyph we're interested in
	 * @return a character that is the glyph of the tile
	 */
	public static final char getGlyph(final Tile tile) {
		return tile.glyph();
	}

	/**
	 * @param tile
	 *            - a tile whose color we're interested in
	 * @return a color that is the color of the tile
	 */
	public static final Color getColor(final Tile tile) {
		return tile.color();
	}

	/**
	 * @param characterToUse
	 *            a char that will be displayed representing the tile
	 * @param colorToUse
	 *            the color that will be used to color the glyph in the tile
	 */
	Tile(final char characterToUse, final Color colorToUse) {
		glyph = characterToUse;
		color = colorToUse;
	}

	/**
	 * @return can the tile be dug through?
	 */
	public boolean isDiggable() {
		return this == Tile.WALL;
	}
}
