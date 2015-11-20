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
	 * @param terminal the panel to display any output on
	 */
	void displayOutput(AsciiPanel terminal);

	/**
	 * @param key what key or combination of keys is pressed
	 * @return what screen should be shown based on the key pressed
	 */
	Screen respondToUserInput(KeyEvent key);

}
