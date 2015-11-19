package rl;

import java.io.IOException;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;

/**
 * @author cbyrd17
 */
public class ApplicationMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the display window that can display Ascii characters.
	 */
	private transient AsciiPanel terminal;

	/**
	 *  Code used in constructor and at deserialization time.
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
