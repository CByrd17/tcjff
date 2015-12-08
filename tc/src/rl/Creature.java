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

	private int xValue;
	public int y;

	private char glyph;

	public char glyph() {
		return glyph;
	}

	private Color color;

	public Color color() {
		return color;
	}

	// private CreatureAi ai;
	// public void setCreatureAi(Creature aiToUse) {
	// ai = aiToUse;
	// }

	/**
	 * 
	 */
	public Creature(World worldToUse, char glyphToUse, Color colorToUse) {
		world = worldToUse;
		glyph = glyphToUse;
		color = colorToUse;
	}

	public void dig(int wx, int wy) {
		world.dig(wx, wy);
	}

	/**
	 * @return the world
	 */
	public final World getWorld() {
		return world;
	}

}
