package rl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;
import rl.screens.Screen;
import rl.screens.StartScreen;

/**
 * @author cbyrd17
 */
public class ApplicationMain extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9213642049164053735L;

	/**
	 * This is the display window that can display Ascii characters.
	 */
	private transient AsciiPanel terminal;

	/**
	 * This is the current screen to show in the terminal.
	 */
	private transient Screen currentScreen;

	/**
	 * Code used in constructor and at deserialization time.
	 */
	private void initializeTerminal() {
		terminal = new AsciiPanel();
		terminal.write("rl tutorial", 1, 1);
		add(terminal);
		pack();
	}

	/**
	 * 
	 */
	public ApplicationMain() {
		super();
		initializeTerminal();
		currentScreen = new StartScreen();
		currentScreen.displayOutput(terminal);
		addKeyListener(this);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @see java.awt.Component#repaint()
	 */
	public final void repaint() {
		terminal.clear();
		currentScreen.displayOutput(terminal);
		super.repaint();
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

	/**
	 * @param inputStream
	 *            the stream containing the ApplicationMain object
	 * @throws IOException
	 *             - if an I/O error occurs.
	 * @throws ClassNotFoundException
	 *             - if the class of a serialized object could not be found.
	 */
	private void readObject(final java.io.ObjectInputStream inputStream)
			throws IOException, ClassNotFoundException {
		inputStream.defaultReadObject();
		initializeTerminal();
	}

	/**
	 * @param args
	 *            passed parameters from jvm
	 */
	public static void main(final String... args) {
		final ApplicationMain app = new ApplicationMain();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}
