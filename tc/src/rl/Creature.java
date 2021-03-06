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
	 * @param worldToUse
	 *            where this creature will exist
	 * @param glyphToUse
	 *            the character will represent the creature
	 * @param colorToUse
	 *            the color of the creature
	 * @param xToUse
	 *            x coordinate of the new creature
	 * @param yToUse
	 *            y coordinate of the new creature
	 */
	public Creature(final World worldToUse, final char glyphToUse,
			final Color colorToUse, final int xToUse, final int yToUse) {
		this(worldToUse, glyphToUse, colorToUse);
		xValue = xToUse;
		yValue = yToUse;
	}

	/**
	 * @param oldCreature
	 *            make a new creature with this old one
	 */
	public Creature(final Creature oldCreature) {
		this(oldCreature.getWorld(), oldCreature.getGlyph(),
				oldCreature.getColor(), oldCreature.getXValue(),
				oldCreature.getYValue());
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
		final Creature other = world.getCreature(getXValue() + moveToX,
				getYValue() + moveToY);

		if (other == null) {
			artificialIntelligence.onEnter(xValue + moveToX, yValue + moveToY,
					world.tile(xValue + moveToX, yValue + moveToY));
		} else {
			attack(other);
		}
	}

	/**
	 * @param other
	 *            a creature that this creature is attacking
	 */
	private void attack(final Creature other) {
		world.remove(other);
	}

	/**
	 * For when a creature gets a chance to act.
	 */
	public final void update() {
		artificialIntelligence.onUpdate();
	}

	/**
	 * @param worldX
	 *            x coordinate to check for availability of the tile
	 * @param worldY
	 *            y coordinate to check for availability of the tile
	 * @return true if tile is available to enter
	 */
	public final boolean canEnter(final int worldX, final int worldY) {
		return isGround(world.tile(worldX, worldY))
				&& world.getCreature(worldX, worldY) == null;
	}

	/**
	 * @param tileToCheck
	 *            a tile to check whether or not it is open ground
	 * @return result of the check for open ground
	 */
	private boolean isGround(final Tile tileToCheck) {
		return tileToCheck.isGround();
	}

}
