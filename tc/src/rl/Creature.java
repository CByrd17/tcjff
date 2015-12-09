/**
 * 
 */
package rl;

import java.awt.Color;

/**
 * @author cbyrd17
 *
 */
public class Creature {

	/**
	 * This is the world that the creature is in.
	 */
	private final World world;

	/**
	 * Horizontal location of the creature in the world.
	 */
	private int xValue;

	/**
	 * Vertical location of the creature in the world.
	 */
	private int yValue;

	/**
	 * The character representation of the creature.
	 */
	private final char glyph;

	/**
	 * The color of the creature.
	 */
	private final Color color;

	/**
	 * The AI for this creature.
	 */
	private CreatureAi artificialIntelligence;

	/**
	 * @return the glyph value
	 */
	public final char getGlyph() {
		return glyph;
	}

	/**
	 * @return the world
	 */
	public final World getWorld() {
		return world;
	}

	/**
	 * @return the xValue
	 */
	public final int getXValue() {
		return xValue;
	}

	/**
	 * @param newXValue
	 *            the xValue to set
	 */
	public final void setXValue(final int newXValue) {
		xValue = newXValue;
	}

	/**
	 * @return the yValue
	 */
	public final int getYValue() {
		return yValue;
	}

	/**
	 * @param newYValue
	 *            the yValue to set
	 */
	public final void setYValue(final int newYValue) {
		yValue = newYValue;
	}

	/**
	 * @return the color
	 */
	public final Color getColor() {
		return color;
	}

	/**
	 * @param aiToUse
	 *            The AI to use for this creature
	 */
	public final void setArtificialIntelligence(final CreatureAi aiToUse) {
		artificialIntelligence = aiToUse;
	}

	/**
	 * @return creature's artificial intelligence
	 */
	public final CreatureAi getArtificialIntelligence() {
		return artificialIntelligence;
	}

	/**
	 * @param worldToUse
	 *            where this creature will exist
	 * @param glyphToUse
	 *            the character will represent the creature
	 * @param colorToUse
	 *            the color of the creature
	 */
	public Creature(final World worldToUse, final char glyphToUse,
			final Color colorToUse) {
		world = worldToUse;
		glyph = glyphToUse;
		color = colorToUse;
	}

	/**
	 * @param worldXLocation
	 *            horizontal location in the world to try to dig
	 * @param worldYLocation
	 *            vertical location in the world to try to dig
	 */
	public final void dig(final int worldXLocation, final int worldYLocation) {
		world.dig(worldXLocation, worldYLocation);
	}

	/**
	 * @param moveToX
	 *            move to the tile in this x location
	 * @param moveToY
	 *            move to the tile in this y location
	 */
	public final void moveBy(final int moveToX, final int moveToY) {
		if (artificialIntelligence != null) {
			artificialIntelligence.onEnter(xValue + moveToX, yValue + moveToY,
					world.tile(xValue + moveToX, yValue + moveToY));
		}
	}
}
