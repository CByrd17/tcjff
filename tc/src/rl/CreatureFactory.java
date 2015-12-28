/**
 * 
 */
package rl;

import asciiPanel.AsciiPanel;

/**
 * @author cbyrd17
 *
 */
public class CreatureFactory {

	/**
	 * The world that we'll add creatures to.
	 */
	private final World world;

	/**
	 * @param worldToUse
	 *            add creatures to this world
	 */
	public CreatureFactory(final World worldToUse) {
		world = worldToUse;
	}

	/**
	 * @return the world
	 */
	public final World getWorld() {
		return world;
	}

	/**
	 * @return a new creature that is a player
	 */
	public final Creature newPlayer() {
		final Creature player = new Creature(world, '@',
				AsciiPanel.brightWhite);
		world.addAtEmptyLocation(player);
		new PlayerAi(player);
		return player;
	}

	/**
	 * @return a new creature that is a fungus
	 */
	public final Creature newFungus() {
		final Creature fungus = new Creature(world, 'f', AsciiPanel.green);
		world.addAtEmptyLocation(fungus);
		new FungusAi(fungus);
		return fungus;
	}

}
