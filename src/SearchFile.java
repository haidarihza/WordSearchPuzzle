import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class SearchFile extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFile frame = new SearchFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchFile() {
		setResizable(false);
		setTitle("Word Search Puzzle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please Choose a File");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(335, 121, 179, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton) {
					
					JFileChooser fileChooser = new JFileChooser();
					
					fileChooser.setCurrentDirectory(new File(".")); //sets current directory
					
					int response = fileChooser.showOpenDialog(null); //select file to open
					//int response = fileChooser.showSaveDialog(null); //select file to save
					
					if(response == JFileChooser.APPROVE_OPTION) {
						File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						FileProcess dataProcess = new FileProcess(file);
					}
				}
			}
		});
		
		btnNewButton.setBounds(369, 195, 119, 33);
		contentPane.add(btnNewButton);
	}

}
