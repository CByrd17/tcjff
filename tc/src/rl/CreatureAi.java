/**
 * 
 */
package rl;

/**
 * @author cbyrd17
 *
 */
public class CreatureAi {
	protected Creature creature;

	public CreatureAi(Creature creature) {
		this.creature = creature;
		this.creature.setArtificialIntelligence(this);
	}

	public void onEnter(int x, int y, Tile tile) {
	}

}
