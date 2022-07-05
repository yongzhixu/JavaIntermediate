package textcollage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.omg.CORBA.PRIVATE_MEMBER;

import say.swing.JFontChooser;

/**
 * A panel that contains a large drawing area where strings can be drawn. The
 * strings are represented by objects of type DrawTextItem. An input box under
 * the panel allows the user to specify what string will be drawn when the user
 * clicks on the drawing area.
 */
public class DrawTextPanel extends JPanel {

	// As it now stands, this class can only show one string at at
	// a time! The data for that string is in the DrawTextItem object
	// named theString. (If it's null, nothing is shown. This
	// variable should be replaced by a variable of type
	// ArrayList<DrawStringItem> that can store multiple items.

	private DrawTextItem theString; // change to an ArrayList<DrawTextItem> !
	private ArrayList<DrawTextItem> stringArrayList = new ArrayList<DrawTextItem>();

	private Color currentTextColor = Color.BLACK; // Color applied to new strings.

	private Font currentTextFont = new Font("Serif", Font.BOLD, 24);
	private Canvas canvas; // the drawing area.
	private JTextField input; // where the user inputs the string that will be added to the canvas
	private SimpleFileChooser fileChooser; // for letting the user select files
	private JMenuBar menuBar; // a menu bar with command that affect this panel
	private MenuHandler menuHandler; // a listener that responds whenever the user selects a menu command
	private JMenuItem undoMenuItem; // the "Remove Item" command from the edit menu
	private JFileChooser fileDialog; // save or open file dialog

	/**
	 * An object of type Canvas is used for the drawing area. The canvas simply
	 * displays all the DrawTextItems that are stored in the ArrayList, strings.
	 */
	private class Canvas extends JPanel {
		Canvas() {
			setPreferredSize(new Dimension(800, 600));
			setBackground(Color.LIGHT_GRAY);
			setFont(new Font("Serif", Font.BOLD, 24));
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			if (theString != null)
//				theString.draw(g);
			if (stringArrayList != null && !stringArrayList.isEmpty())
				for (DrawTextItem drawTextItem : stringArrayList) {
					drawTextItem.draw(g);
				}
		}
	}

	/**
	 * An object of type MenuHandler is registered as the ActionListener for all the
	 * commands in the menu bar. The MenuHandler object simply calls doMenuCommand()
	 * when the user selects a command from the menu.
	 */
	private class MenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			doMenuCommand(evt.getActionCommand());
		}
	}

	/**
	 * Creates a DrawTextPanel. The panel has a large drawing area and a text input
	 * box where the user can specify a string. When the user clicks the drawing
	 * area, the string is added to the drawing area at the point where the user
	 * clicked.
	 */
	public DrawTextPanel() {
		fileChooser = new SimpleFileChooser();
		undoMenuItem = new JMenuItem("Remove Item");
		undoMenuItem.setEnabled(false);
		menuHandler = new MenuHandler();
		setLayout(new BorderLayout(3, 3));
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		canvas = new Canvas();
		add(canvas, BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("Text to add: "));
		input = new JTextField("Hello World!", 40);
		bottom.add(input);
		add(bottom, BorderLayout.SOUTH);
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				doMousePress(e);
			}
		});
	}

	/**
	 * This method is called when the user clicks the drawing area. A new string is
	 * added to the drawing area. The center of the string is at the point where the
	 * user clicked.
	 * 
	 * @param e the mouse event that was generated when the user clicked
	 */
	public void doMousePress(MouseEvent e) {
		String text = input.getText().trim();
		if (text.length() == 0) {
			input.setText("Hello World!");
			text = "Hello World!";
		}
		DrawTextItem s = new DrawTextItem(text, e.getX(), e.getY());
		s.setTextColor(currentTextColor); // Default is null, meaning default color of the canvas (black).
		s.setFont(currentTextFont);

//   SOME OTHER OPTIONS THAT CAN BE APPLIED TO TEXT ITEMS:
//		s.setFont( new Font( "Serif", Font.ITALIC + Font.BOLD, 12 ));  // Default is null, meaning font of canvas.
//		s.setMagnification(3);  // Default is 1, meaning no magnification.
//		s.setBorder(true);  // Default is false, meaning don't draw a border.
//		s.setRotationAngle(25);  // Default is 0, meaning no rotation.
//		s.setTextTransparency(0.3); // Default is 0, meaning text is not at all transparent.
//		s.setBackground(Color.BLUE);  // Default is null, meaning don't draw a background area.
//		s.setBackgroundTransparency(0.7);  // Default is 0, meaning background is not transparent.

//		theString = s; // Set this string as the ONLY string to be drawn on the canvas!
		stringArrayList.add(s);
		undoMenuItem.setEnabled(true);
		canvas.repaint();
	}

	/**
	 * Returns a menu bar containing commands that affect this panel. The menu bar
	 * is meant to appear in the same window that contains this panel.
	 */
	public JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();

			String commandKey; // for making keyboard accelerators for menu commands
			if (System.getProperty("mrj.version") == null)
				commandKey = "control "; // command key for non-Mac OS
			else
				commandKey = "meta "; // command key for Mac OS

			JMenu fileMenu = new JMenu("File");
			menuBar.add(fileMenu);
			JMenuItem saveItem = new JMenuItem("Save...");
			saveItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "N"));
			saveItem.addActionListener(menuHandler);
			fileMenu.add(saveItem);
			JMenuItem openItem = new JMenuItem("Open...");
			openItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "O"));
			openItem.addActionListener(menuHandler);
			fileMenu.add(openItem);
			fileMenu.addSeparator();
			JMenuItem saveImageItem = new JMenuItem("Save Image...");
			saveImageItem.addActionListener(menuHandler);
			fileMenu.add(saveImageItem);

			JMenu editMenu = new JMenu("Edit");
			menuBar.add(editMenu);
			undoMenuItem.addActionListener(menuHandler); // undoItem was created in the constructor
			undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "Z"));
			editMenu.add(undoMenuItem);
			editMenu.addSeparator();
			JMenuItem clearItem = new JMenuItem("Clear");
			clearItem.addActionListener(menuHandler);
			editMenu.add(clearItem);

			JMenu optionsMenu = new JMenu("Options");
			menuBar.add(optionsMenu);
			JMenuItem colorItem = new JMenuItem("Set Text Color...");
			colorItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "T"));
			colorItem.addActionListener(menuHandler);
			optionsMenu.add(colorItem);
			JMenuItem fontItem = new JMenuItem("Set Text Font...");
			fontItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "F"));
			fontItem.addActionListener(menuHandler);
			optionsMenu.add(fontItem);
			JMenuItem bgColorItem = new JMenuItem("Set Background Color...");
			bgColorItem.addActionListener(menuHandler);
			optionsMenu.add(bgColorItem);

			JMenu funJMenu = new JMenu("ForFun");
			menuBar.add(funJMenu);
			JMenuItem randomColorItem = new JMenuItem("Set To Random Color...");
			randomColorItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "Q"));
			randomColorItem.addActionListener(menuHandler);
			funJMenu.add(randomColorItem);
			menuBar.add(funJMenu);
			JMenuItem randomTextItem = new JMenuItem("Text Dancing...");
			randomTextItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "R"));
			randomTextItem.addActionListener(menuHandler);
			funJMenu.add(randomTextItem);

		}
		return menuBar;
	}

	/**
	 * Carry out one of the commands from the menu bar.
	 * 
	 * @param command the text of the menu command.
	 */
	private void doMenuCommand(String command) {
		if (command.equals("Save...")) { // save all the string info to a file
//			JOptionPane.showMessageDialog(this, "Sorry, the Save command is not implemented.");
			save();
		} else if (command.equals("Open...")) { // read a previously saved file, and reconstruct the list of strings
//			JOptionPane.showMessageDialog(this, "Sorry, the Open command is not implemented.");
			openFile();
			canvas.repaint(); // (you'll need this to make the new list of strings take effect)
		} else if (command.equals("Clear")) { // remove all strings
//			theString = null; // Remove the ONLY string from the canvas.
			stringArrayList.clear();
			undoMenuItem.setEnabled(false);
			canvas.repaint();
		} else if (command.equals("Remove Item")) { // remove the most recently added string
//			theString = null; // Remove the ONLY string from the canvas.
			if (!stringArrayList.isEmpty()) {
				stringArrayList.remove(stringArrayList.size() - 1);
			}
			if (stringArrayList.isEmpty()) {
				undoMenuItem.setEnabled(false);
			}
//			stringArrayList.clear();
//			undoMenuItem.setEnabled(false);
			canvas.repaint();
		} else if (command.equals("Set Text Color...")) {
			Color c = JColorChooser.showDialog(this, "Select Text Color", currentTextColor);
			if (c != null)
				currentTextColor = c;
		} else if (command.equals("Set Text Font...")) {
			JFontChooser fontChooser = new JFontChooser();
			int result = fontChooser.showDialog(this);
			if (result == JFontChooser.OK_OPTION) {
				Font font = fontChooser.getSelectedFont();
				System.out.println("Selected Font : " + font);
				currentTextFont = font;
			}
		} else if (command.equals("Set Background Color...")) {
			Color c = JColorChooser.showDialog(this, "Select Background Color", canvas.getBackground());
			if (c != null) {
				canvas.setBackground(c);
				canvas.repaint();
			}
		} else if (command.equals("Set To Random Color...")) {
			for (DrawTextItem drawTextItem : stringArrayList) {
				Color textColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
						(int) (Math.random() * 255));
				drawTextItem.setTextColor(textColor);
			}
			canvas.setBackground(
					new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
			canvas.repaint();
		} else if (command.equals("Text Dancing...")) {

			DrawTextItem drawTextItem;
			for (int i = 0; i < 5; i++) {
				stringArrayList.clear();
				for (int j = 0; j < (int) (Math.random() * 50) + 1; j++) {
//				drawTextItem.se
					drawTextItem = new DrawTextItem(randomText((int) (Math.random() * 10) + 1),
							(int) (Math.random() * canvas.getSize().width),
							(int) (Math.random() * canvas.getSize().height));
					Color textColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
							(int) (Math.random() * 255));
					drawTextItem.setTextColor(textColor);
					drawTextItem.setFont(new Font("Serif", Font.BOLD, 24));
					stringArrayList.add(drawTextItem);
				}
				canvas.setBackground(Color.LIGHT_GRAY);
				canvas.repaint();
			}
		} else if (command.equals("Save Image...")) { // save a PNG image of the drawing area
			File imageFile = fileChooser.getOutputFile(this, "Select Image File Name", "textimage.png");
			if (imageFile == null)
				return;
			try {
				// Because the image is not available, I will make a new BufferedImage and
				// draw the same data to the BufferedImage as is shown in the panel.
				// A BufferedImage is an image that is stored in memory, not on the screen.
				// There is a convenient method for writing a BufferedImage to a file.
				BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				Graphics g = image.getGraphics();
				g.setFont(canvas.getFont());
				canvas.paintComponent(g); // draws the canvas onto the BufferedImage, not the screen!
				boolean ok = ImageIO.write(image, "PNG", imageFile); // write to the file
				if (ok == false)
					throw new Exception("PNG format not supported (this shouldn't happen!).");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Sorry, an error occurred while trying to save the image:\n" + e);
			}
		}

	}

	/**
	 * fetch background and text informations
	 * 
	 * @return ArrayList<String>
	 */
	private ArrayList<String> getContents() {
		ArrayList<String> contenstHashMap = new ArrayList<String>();
//		canvas properties
		contenstHashMap.add("canvas properties: ");
		contenstHashMap.add("canvasName: " + canvas.getName());
		contenstHashMap.add(
				"canvasBackground(RGBA): " + canvas.getBackground().getRed() + "-" + canvas.getBackground().getGreen()
						+ "-" + canvas.getBackground().getBlue() + "-" + canvas.getBackground().getAlpha());

		contenstHashMap.add("canvasFontFamily: " + canvas.getFont().getFamily());
		contenstHashMap.add("canvasFontName: " + canvas.getFont().getName());

//		text properties
		contenstHashMap.add("\ntext properties: ");
		for (int i = 0; i < stringArrayList.size(); i++) {
			DrawTextItem drawTextItem = stringArrayList.get(i);

			contenstHashMap.add("textItem" + (i + 1) + ": ");
			contenstHashMap.add("	Content: " + drawTextItem.getString());
			contenstHashMap.add("	Color(RGBA): " + drawTextItem.getTextColor().getRed() + "-"
					+ drawTextItem.getTextColor().getGreen() + "-" + drawTextItem.getTextColor().getBlue() + "-"
					+ drawTextItem.getTextColor().getAlpha());
			contenstHashMap.add("	X: " + String.valueOf(drawTextItem.getX()));
			contenstHashMap.add("	Y: " + String.valueOf(drawTextItem.getY()));
			contenstHashMap.add("	Font: " + String.valueOf(drawTextItem.getFont().getFamily()) + "-"
					+ drawTextItem.getFont().getStyle() + "-" + drawTextItem.getFont().getSize());
			contenstHashMap.add("	Transparency: " + String.valueOf(drawTextItem.getTextTransparency()));
			contenstHashMap.add("	RotationAngle: " + String.valueOf(drawTextItem.getRotationAngle()));
			contenstHashMap.add("	Magnification: " + String.valueOf(drawTextItem.getMagnification()));
			contenstHashMap.add("	Border: " + String.valueOf(drawTextItem.getBorder()));

		}
		return contenstHashMap;
	}

	/**
	 * save the panel to txt file
	 */
	private void save() {
		ArrayList<String> contenstHashMap = getContents();
//		System.out.println("The LIST contains the following associations:");
		for (String string : contenstHashMap) {
			System.out.println(string);
		}
		if (fileDialog == null)
			fileDialog = new JFileChooser(); // (fileDialog is an instance variable)
		File selectedFile = new File("output.txt"); // (default output file name)
		fileDialog.setSelectedFile(selectedFile); // Specify a default file name.
		fileDialog.setDialogTitle("Select File for Writing");
		int option = fileDialog.showSaveDialog(this);
		if (option != JFileChooser.APPROVE_OPTION)
			return; // User canceled or clicked the dialog's close box.
		selectedFile = fileDialog.getSelectedFile();
		if (selectedFile.exists()) { // Ask the user whether to replace the file.
			int response = JOptionPane.showConfirmDialog(this,
					"The file \"" + selectedFile.getName() + "\" already exists.\nDo you want to replace it?",
					"Confirm Save", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (response != JOptionPane.YES_OPTION)
				return; // User does not want to replace the file.
		}
		PrintWriter out; // (or use some other wrapper class)
		try {
			out = new PrintWriter(selectedFile);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Sorry, but an error occurred while trying to open the file:\n" + e);
			return;
		}
		try {
			// Write data to the output stream, out. (Does not throw exceptions.)
			for (String string : contenstHashMap) {
				out.println(string);
			}
			out.flush();
			out.close();
			if (out.checkError()) // (need to check for errors in PrintWriter)
				throw new IOException("Error occurred while trying to write file.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Sorry, but an error occurred while trying to write the data:\n" + e);
		}
	}

	/**
	 * open text file and show text with given properties on the panel
	 */
	public void openFile() {
		if (fileDialog == null) // (fileDialog is an instance variable)
			fileDialog = new JFileChooser();
		fileDialog.setDialogTitle("Select File for Reading");
		fileDialog.setSelectedFile(null); // No file is initially selected.
		int option = fileDialog.showOpenDialog(this);
		// (Using "this" as a parameter to showOpenDialog() assumes that the
		// readFile() method is an instance method in a GUI component class.)
		if (option != JFileChooser.APPROVE_OPTION)
			return; // User canceled or clicked the dialog's close box.
		File selectedFile = fileDialog.getSelectedFile();
		Scanner in; // (or use some other wrapper class)
		try {
//		      FileReader stream = new FileReader(selectedFile); // (or a FileInputStream)
			in = new Scanner(selectedFile);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Sorry, but an error occurred while trying to open the file:\n" + e);
			return;
		}
		try {
			// Read and process the data from the input stream, in.
			String nextString = "";
			DrawTextItem s;
			while (in.hasNext()) {
				if (nextString.trim().startsWith("textItem")) {
					nextString = in.nextLine();
					Color color = Color.BLACK;
					int X = 0, Y = 0;
					Font font = new Font("Serif", 1, 14);
					String textString = null;
					while (in.hasNext()) {
						if (nextString.trim().toLowerCase().contains("content")) {
							textString = nextString.substring(nextString.indexOf(':') + 1).trim();
						} else if (nextString.trim().toLowerCase().contains("color")) {
							String[] cString = nextString.substring(nextString.indexOf(':') + 1).trim().split("-");
							color = new Color(Integer.parseInt(cString[0]), Integer.parseInt(cString[1]),
									Integer.parseInt(cString[2]));
						} else if (nextString.trim().toLowerCase().contains("font")) {
							String[] fString = nextString.substring(nextString.indexOf(':') + 1).trim().split("-");
							font = new Font(fString[0], Integer.parseInt(fString[1]), Integer.parseInt(fString[2]));
						} else if (nextString.trim().toLowerCase().startsWith("x")) {
							X = Integer.parseInt(nextString.substring(nextString.indexOf(':') + 1).trim());
						} else if (nextString.trim().toLowerCase().startsWith("y")) {
							Y = Integer.parseInt(nextString.substring(nextString.indexOf(':') + 1).trim());
						}
						if (in.hasNext()) {
							nextString = in.nextLine();
						}
						if (nextString.trim().startsWith("textItem")) {
							break;
						}
					}
					s = new DrawTextItem(textString, X, Y);
					s.setTextColor(color);
					s.setFont(font);
					stringArrayList.add(s);
				} else if (nextString.trim().startsWith("canvas")) {

					Color color = Color.BLACK;
					String fontString = "Serif";
					while (in.hasNext()) {
						if (nextString.trim().toLowerCase().contains("background")) {
							String[] cString = nextString.substring(nextString.indexOf(':') + 1).trim().split("-");
							color = new Color(Integer.parseInt(cString[0]), Integer.parseInt(cString[1]),
									Integer.parseInt(cString[2]));
						} else if (nextString.trim().toLowerCase().contains("fontfamily")) {
							fontString = nextString.substring(nextString.indexOf(':') + 1).trim();
						}
						if (in.hasNext()) {
							nextString = in.nextLine();
						}
						if (nextString.trim().startsWith("textItem")) {
							break;
						}
					}
					canvas.setFont(new Font(fontString, Font.BOLD, 24));
					canvas.setBackground(color);
				} else {
					nextString = in.nextLine();
				}
			}
			if (!stringArrayList.isEmpty()) {
				undoMenuItem.setEnabled(true);
			}
			canvas.repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Sorry, but an error occurred while trying to read the data:\n" + e);
		} finally {
			in.close();
		}
	}

	/**
	 * generate random text with given length
	 * 
	 * @param n, the size of the string text
	 * @return String
	 */
	private String randomText(int n) {
		// lower limit for LowerCase Letters
		int lowerLimit = 97;

		// lower limit for LowerCase Letters
		int upperLimit = 122;

		Random random = new Random();

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer(n);

		for (int i = 0; i < n; i++) {

			// take a random value between 97 and 122
			int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));

			// append a character at the end of bs
			r.append((char) nextRandomChar);
		}

		// return the resultant string
		return r.toString();

	}

}
