import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import java.awt.Font;

public class ShowResult extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowResult frame = new ShowResult();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public ShowResult() throws FileNotFoundException {
		setResizable(false);
		setTitle("Word Search Puzzle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 900, 600);
		FileReader fileReader = new FileReader("./out.txt");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 866, 543);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        try {
            BufferedReader input = new BufferedReader(fileReader);
            textArea.read(input, "Please Wait");

          } catch (Exception e) {
            e.printStackTrace();
          }
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
	}
}
