package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *
 * Glowna ramka programu
 *
 */
public class MainFrame extends JFrame {
	private final static String TITLE = "Sokoban v1.0.0";
	private final static int DEFAULT_WIDTH = 520;
	private final static int DEFAULT_HIGHT = 570;
	private Action saveAction;
	private Action newGameAction;
	private GameAreaPanel areaPanel;
	private GameStatePanel statePanel;
/**
 * Bezparametrowy konstruktor tworzacy glowna ramke aplikacji
 */
	public MainFrame() {
		setTitle(TITLE);
		setVisible(true);
		setBounds(new Rectangle(DEFAULT_WIDTH, DEFAULT_HIGHT));
		makeMenu();
		this.setResizable(true);
//		Image image = new ImageIcon("icon.jpg").getImage();
//		setIconImage(image);
	}
/**
 * uruchomienie nowej gry
 */
	private void newGameStart() {
		add(new GameAreaPanel("testLevel.txt"));
	}
/**
 * Metoda tworzaca menu
 */
	private void makeMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuGame = new JMenu("Gra");

		menuGame.add(new AbstractAction("Nowa gra") {
			public void actionPerformed(ActionEvent event) {
				newGameStart();
			}
		});

		menuGame.add(new AbstractAction("Najlepsze wyniki") {
			public void actionPerformed(ActionEvent event) {
				String fileName = "info\\highScore.txt";
				StringBuilder instrukcjaGry = new StringBuilder();
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(fileName));
					String tmp = null;
					while ((tmp = reader.readLine()) != null) {

						instrukcjaGry.append(tmp);
						instrukcjaGry.append("\n");

					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				JFrame frameInfo = new JFrame();
				JOptionPane.showMessageDialog(frameInfo, instrukcjaGry, "Sokoban - Top10", 1);
			}
		});

		menuGame.add(new AbstractAction("Wyjscie") {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		JMenu menuSettings = new JMenu("Ustawienia");
		ButtonGroup levelSettings = new ButtonGroup();
		JRadioButtonMenuItem easyLevel = new JRadioButtonMenuItem("Poziom latwy");
		easyLevel.setSelected(true);
		JRadioButtonMenuItem hardLevel = new JRadioButtonMenuItem("Poziom trudny");
		levelSettings.add(easyLevel);
		levelSettings.add(hardLevel);
		ButtonGroup networkSettings = new ButtonGroup();
		JRadioButtonMenuItem online = new JRadioButtonMenuItem("Online");
		JRadioButtonMenuItem offline = new JRadioButtonMenuItem("Offline");
		offline.setSelected(true);

		menuSettings.add(easyLevel);
		menuSettings.add(hardLevel);
		menuSettings.addSeparator();
		menuSettings.add(online);
		menuSettings.add(offline);

		JMenu menuHelp = new JMenu("Pomoc");
		menuHelp.add(new AbstractAction("Instrukcja") {
			public void actionPerformed(ActionEvent event) {
				String fileName = "info\\info.txt";
				StringBuilder instrukcjaGry = new StringBuilder();
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(fileName));
					String tmp = null;
					while ((tmp = reader.readLine()) != null) {

						instrukcjaGry.append(tmp);
						instrukcjaGry.append("\n");

					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				JFrame frameInfo = new JFrame();
				JOptionPane.showMessageDialog(frameInfo, instrukcjaGry, "Sokoban - Info", 1);
			}
		});

		menuBar.add(menuGame);
		menuBar.add(menuSettings);
		menuBar.add(menuHelp);

		this.setJMenuBar(menuBar);

	}
}
