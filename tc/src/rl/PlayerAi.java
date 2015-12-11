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

	/*
	 * (non-Javadoc)
	 * 
	 * @see rl.CreatureAi#onEnter(int, int, rl.Tile)
	 */
	public final void onEnter(final int newXValue, final int newYValue,
			final Tile tile) {
		if (tile.isGround()) {
			getCreature().setXValue(newXValue);
			getCreature().setYValue(newYValue);
		} else if (tile.isDiggable()) {
			getCreature().dig(newXValue, newYValue);
		}
	}
}
