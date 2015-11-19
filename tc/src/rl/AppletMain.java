/**
 * 
 */
package rl;

import java.applet.Applet;

import asciiPanel.AsciiPanel;

/**
 * @author cbyrd17
 *
 */
public class AppletMain extends Applet {

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
	 * 
	 */
	public AppletMain() {
		super();
		terminal = new AsciiPanel();
		terminal.write("rl tutorial", 1, 1);
		add(terminal);
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
		terminal.repaint();
	}
}
