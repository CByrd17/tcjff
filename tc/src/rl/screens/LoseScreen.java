/**
 * 
 */
package rl.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

/**
 * @author cbyrd17
 *
 */
public class LoseScreen implements Screen {

	/**
	 * (non-Javadoc).
	 * 
	 * @see rl.screens.Screen#displayOutput(asciiPanel.AsciiPanel)
	 */
	@Override
	public final void displayOutput(final AsciiPanel terminal) {
		terminal.write("You lost.", 1, 1);
		terminal.writeCenter("-- press [enter] to restart --",
				MESSAGE_LINES_FROM_TOP);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see rl.screens.Screen#respondToUserInput(java.awt.event.KeyEvent)
	 */
	@Override
	public final Screen respondToUserInput(final KeyEvent key) {
		Screen showThisScreen;

		if (key.getKeyCode() == KeyEvent.VK_ENTER) {
			showThisScreen = new PlayScreen();
		} else {
			showThisScreen = this;
		}

		return showThisScreen;
	}
}
