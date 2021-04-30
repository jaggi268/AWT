import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
class TextEditor extends JFrame implements ActionListener
{
	String s = "";
	String str="";
	static JTextArea tx;
	JScrollPane scrollpane ;
	TextEditor()
	{
		this.setTitle("Text Editor");
		this.setSize(1366,750);
		this.setVisible(true);
		this.setBackground(Color.gray);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	/*	this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});*/

		//JMenuBar Appending
		JMenuBar mb = new JMenuBar();
		this.setJMenuBar(mb);

		//Creating JMenuItems
		JMenu m1 = new JMenu("File");
		JMenuItem m11 = new JMenuItem("New Window");
		JMenuItem m12 = new JMenuItem("Open");
		JMenuItem m13 = new JMenuItem("Save");
		JMenuItem m14 = new JMenuItem("Quit");
		m1.setMnemonic(KeyEvent.VK_F);
		m11.setMnemonic(KeyEvent.VK_N);
		m11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
		m12.setMnemonic(KeyEvent.VK_O);
		m12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK));
		m13.setMnemonic(KeyEvent.VK_S);
		m13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
		m14.setMnemonic(KeyEvent.VK_Q);
		m14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_DOWN_MASK));
		m1.add(m11);m1.add(m12);
		m1.addSeparator();
		m1.add(m13);m1.add(m14);
		mb.add(m1);
		m11.addActionListener(this);
		m12.addActionListener(this);
		m13.addActionListener(this);
		m14.addActionListener(this);

		JMenu m2 = new JMenu("Edit");
		m2.setMnemonic(KeyEvent.VK_E);
		JMenuItem m21 = new JMenuItem("Cut");
		JMenuItem m22 = new JMenuItem("Copy");
		JMenuItem m23 = new JMenuItem("Paste");
		m21.setMnemonic(KeyEvent.VK_X);
		m21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_DOWN_MASK));
		m22.setMnemonic(KeyEvent.VK_C);
		m22.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK));
		m23.setMnemonic(KeyEvent.VK_V);
		m23.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_DOWN_MASK));
		m2.add(m21);m2.add(m22);m2.add(m23);
		mb.add(m2);
		m21.addActionListener(this);
		m22.addActionListener(this);
		m23.addActionListener(this);



		//Creating JTextArea for the texteditor

		tx = new JTextArea("",44,123);
		scrollpane = new JScrollPane(tx);
		this.add(new JScrollPane(tx),BorderLayout.PAGE_START);
	}
	public void actionPerformed(ActionEvent e)
	{
		s = e.getActionCommand();
		if (s == "New Window") {
			TextEditor j = new TextEditor();
			j.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent je)
				{
					System.exit(0);
				}
			}
			);
		}
		if (s=="Open") {
			tx.setText("");
			FileDialog dialog = new FileDialog(new Frame(),"Open File",FileDialog.LOAD);
			dialog.setVisible(true);
			if (dialog.getDirectory()!= null) {
				String filePath = dialog.getDirectory()+dialog.getFile();
				try
				{
					FileReader fr = new FileReader(filePath);
					BufferedReader br = new BufferedReader(fr);
					String line = br.readLine();
					while(line!=null)
					{
						tx.append(line+"\n");
						line = br.readLine();
					}
					br.close();
					fr.close();
					this.setTitle(dialog.getFile());
				}
				catch(IOException exe)
				{
					exe.printStackTrace();
				}
			}
		}
		if (s=="Save") {
			FileDialog dialog = new FileDialog(new Frame(),"Open File",FileDialog.SAVE);
			dialog.setVisible(true);
			if (dialog.getDirectory()!= null) {
				String filePath = dialog.getDirectory()+dialog.getFile();
				try
				{
					FileWriter fw = new FileWriter(filePath);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(tx.getText());
					bw.flush();
					bw.close();
					fw.close();
					this.setTitle(dialog.getFile());
				}
				catch(IOException exe)
				{
					exe.printStackTrace();
				}
			}
		}
		if (s=="Quit") {
			System.exit(0);
		}
		if (s=="Cut") {
			tx.cut();
		}
		if (s=="Copy") {
			tx.copy();
		}
		if (s=="Paste") {
			tx.paste();
		}

	}
}
class test
{
	public static void main(String[] args) {
		TextEditor t = new TextEditor();
		
	}
}