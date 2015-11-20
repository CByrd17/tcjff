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
public class PlayScreen implements Screen {

	/**
	 * (non-Javadoc).
	 * 
	 * @see rl.screens.Screen#displayOutput(asciiPanel.AsciiPanel)
	 */
	@Override
	public final void displayOutput(final AsciiPanel terminal) {
		terminal.write("You are having fun.", 1, 1);
		terminal.writeCenter("-- press [escape] to lose or [enter] to win --",
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

		switch (key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			showThisScreen = new LoseScreen();
			break;
		case KeyEvent.VK_ENTER:
			showThisScreen = new WinScreen();
			break;
		default:
			showThisScreen = this;
			break;
		}

		return showThisScreen;
	}

}
