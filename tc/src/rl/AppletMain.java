/**
 * 
 */
package rl;

import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import asciiPanel.AsciiPanel;
import rl.screens.Screen;
import rl.screens.StartScreen;

/**
 * @author cbyrd17
 *
 */
public class AppletMain extends Applet implements KeyListener {

	/**
	 * Pad the terminal height with this amount.
	 */
	private static final int TERMINAL_HEIGHT_PADDING = 20;

	/**
	 * Pad the terminal width with this amount.
	 */
	private static final int TERMINAL_WIDTH_PADDING = 20;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7285794208614043711L;

	/**
	 * This is the display window that can display Ascii characters.
	 */
	private final transient AsciiPanel terminal;

	/**
	 * This is the current screen to show in the terminal.
	 */
	private transient Screen currentScreen;

	/**
	 * 
	 */
	public AppletMain() {
		super();
		terminal = new AsciiPanel();
		terminal.write("rl tutorial", 1, 1);
		add(terminal);
		currentScreen = new StartScreen();
		addKeyListener(this);
		repaint();
		setFocusable(true);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see java.applet.Applet#init()
	 */
	public final void init() {
		super.init();
		this.setSize(terminal.getWidth() + TERMINAL_WIDTH_PADDING,
				terminal.getHeight() + TERMINAL_HEIGHT_PADDING);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see java.awt.Component#repaint()
	 */
	public final void repaint() {
		super.repaint();
		terminal.clear();
		currentScreen.displayOutput(terminal);
		terminal.repaint();
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public final void keyPressed(final KeyEvent keyEvent) {
		currentScreen = currentScreen.respondToUserInput(keyEvent);
		repaint();
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(final KeyEvent keyEvent) {
		// No use for keyReleased listener right now.
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(final KeyEvent keyEvent) {
		// No use for keyTyped listener right now.
	}

}
