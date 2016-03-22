package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	private final static String TITLE = "Sokoban v1.0.0";
	private final static int DEFAULT_WIDTH = 400;
	private final static int DEFAULT_HIGHT = 400;
	private Action saveAction;
	private Action newGameAction;

	public MainFrame() {
		setTitle(TITLE);
		setVisible(true);
		setBounds(new Rectangle(DEFAULT_WIDTH, DEFAULT_HIGHT));

		makeMenu();
	}

	private void makeMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuGame = new JMenu("Gra");

		menuGame.add(new AbstractAction("Nowa gra") {
			public void actionPerformed(ActionEvent event) {
				System.out.println("tutaj bedize uruchamiana nowa gra");
			}
		});

		menuGame.add(new AbstractAction("Najlepsze wyniki") {
			public void actionPerformed(ActionEvent event) {
				System.out.println("tutaj wyskoczy okno z najlepszymi wynikami");
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
		menuHelp.add(new AbstractAction("Reguly gry") {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("wyskoczy messagebox z regulaim");

			}
		});

		menuBar.add(menuGame);
		menuBar.add(menuSettings);
		menuBar.add(menuHelp);

		this.setJMenuBar(menuBar);

	}
}
