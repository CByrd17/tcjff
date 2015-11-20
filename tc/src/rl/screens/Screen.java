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
public interface Screen {

	/**
	 * This is how far from the top the message will be displayed.
	 */
	int MESSAGE_LINES_FROM_TOP = 22;

	/**
	 * @param terminal
	 *            the panel to display any output on
	 */
	void displayOutput(AsciiPanel terminal);

	/**
	 * @param key
	 *            what key or combination of keys is pressed
	 * @return what screen should be shown based on the key pressed
	 */
	Screen respondToUserInput(KeyEvent key);

}
