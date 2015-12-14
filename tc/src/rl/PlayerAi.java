/**
 * 
 */
package rl;

/**
 * @author cbyrd17
 *
 */
public class PlayerAi extends CreatureAi {

	/**
	 * @param creatureInNeedOfAI
	 *            the creature that will get this type of player AI
	 */
	public PlayerAi(final Creature creatureInNeedOfAI) {
		super(creatureInNeedOfAI);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see rl.CreatureAi#onEnter(int, int, rl.Tile)
	 */
	@Override
	public final void onEnter(final int newXValue, final int newYValue,
			final Tile tile) {
		if (tile.isGround()) {
			updateLocation(newXValue, newYValue);
		} else if (tile.isDiggable()) {
			creatureDig(newXValue, newYValue);
		}
	}
}
