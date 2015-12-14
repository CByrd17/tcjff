/**
 * 
 */
package rl;

/**
 * @author cbyrd17
 *
 */
public class CreatureAi {
	/**
	 * This creature has this AI.
	 */
	private Creature creature;

	/**
	 * @return the creature
	 */
	public final Creature getCreature() {
		return creature;
	}

	/**
	 * @param newCreature
	 *            the creature to set
	 */
	public final void setCreature(final Creature newCreature) {
		creature = newCreature;
	}

	/**
	 * @param creatureInNeedOfAI
	 *            assign this AI to this creature
	 */
	public CreatureAi(final Creature creatureInNeedOfAI) {
		this.creature = creatureInNeedOfAI;
		this.creature.setArtificialIntelligence(this);
	}

	/**
	 * @param xLocation
	 *            the x component of a location being entered
	 * @param yLocation
	 *            the y component of a location being entered
	 * @param tile
	 *            the description of the location being entered
	 */
	public void onEnter(final int xLocation, final int yLocation,
			final Tile tile) {
		// This will be overridden by extending classes
	}

	/**
	 * @param newXValue
	 *            x coordinate of the creature's new location
	 * @param newYValue
	 *            y coordinate of the creature's new location
	 */
	protected final void updateLocation(final int newXValue,
			final int newYValue) {
		creature.setXValue(newXValue);
		creature.setYValue(newYValue);
	}

	/**
	 * @param newXValue
	 *            x coordinate of where the creature will try to dig
	 * @param newYValue
	 *            y coordinate of where the creature will try to dig
	 */
	protected final void creatureDig(final int newXValue,
			final int newYValue) {
		creature.dig(newXValue, newYValue);

	}

}
