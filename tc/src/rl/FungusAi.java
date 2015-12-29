package rl;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author cbyrd17 This class describes the behavior of a Fungus
 */
public class FungusAi extends CreatureAi {

	private static final int TOO_FAR_AWAY = 11;

	private static final double SPREAD_FACTOR = 0.02;

	private static final int TOO_MANY_SPREADINGS = 5;

	/**
	 * Use when additional creatures are needed.
	 */
	private CreatureFactory factory;

	/**
	 * How many times has this fugus spread?
	 */
	private int spreadcount;

	/**
	 * @return the factory
	 */
	public final CreatureFactory getFactory() {
		return factory;
	}

	/**
	 * @param factoryToUse
	 *            the factory to set
	 */
	public final void setFactory(final CreatureFactory factoryToUse) {
		this.factory = factoryToUse;
	}

	/**
	 * @return the spreadcount
	 */
	public final int getSpreadcount() {
		return spreadcount;
	}

	/**
	 * @param newSpreadcount
	 *            the spreadcount to set
	 */
	public final void setSpreadcount(final int newSpreadcount) {
		spreadcount = newSpreadcount;
	}

	/**
	 * @param creatureInNeedOfAI
	 *            we'll make this creature act like a fungus
	 * @param factoryToUse
	 *            for use in making more creatures
	 */
	public FungusAi(final Creature creatureInNeedOfAI,
			final CreatureFactory factoryToUse) {
		super(creatureInNeedOfAI);
		setFactory(factoryToUse);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see rl.CreatureAi#onUpdate()
	 */
	@Override
	public final void onUpdate() {
		if (spreadcount < TOO_MANY_SPREADINGS
				&& Math.random() < SPREAD_FACTOR) {
			spread();
		}

	}

	/**
	 * Potentially spawn a new fungus.
	 */
	private void spread() {

		final int potentialX = getCreature().getXValue() + spreadProximity();
		final int potentialY = getCreature().getYValue() + spreadProximity();

		if (!isClear(potentialX, potentialY)) {
			return;
		}

		final Creature child = factory.newFungus();
		child.setXValue(potentialX);
		child.setYValue(potentialY);
		spreadcount++;
	}

	/**
	 * @return a random distance for spreading
	 */
	private int spreadProximity() {
		final ThreadLocalRandom tlr = ThreadLocalRandom.current();
		return tlr.nextInt(0, TOO_FAR_AWAY) - TOO_FAR_AWAY / 2;
	}

}
