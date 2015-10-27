package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class Mirage {
	private JFrame frame = null;

	private JTable table = null;

	private static Table_Model model = null;

	private JScrollPane s_pan = null;

	private JButton button_0 = null, button_1 = null, button_2 = null,
			button_3 = null, button_4 = null;

	private JPanel pane = null;

	private JPanel paneleft = null;

	private TestToolFrame tool = null;

	JMenuBar menuBar = new JMenuBar();

	JMenu fileMenu = new JMenu("File");

	JMenuItem fileNew = new JMenuItem("New");

	JMenuItem fileOpen = new JMenuItem("Open");

	JMenuItem fileSave = new JMenuItem("Save");

	JMenuItem fileExit = new JMenuItem("Exit");

	JMenu toolMenu = new JMenu("Tool");

	JMenuItem toolImage = new JMenuItem("Image");

	JMenuItem toolIllusion = new JMenuItem("Stardust");

	JMenu editMenu = new JMenu("Edit");

	JMenuItem editCut = new JMenuItem("Cut");

	JMenuItem editCopy = new JMenuItem("Copy");

	JMenuItem editPaste = new JMenuItem("Paste");

	JTree tree = new JTree();

	Stardust Stardust = null;

	private JScrollPane jScrollPane1;

	private JScrollPane jScrollPane2;

	private JSplitPane jSplitPane1;

	private String nowSheet = "";

	int screenwidth = 0; // 获得当前屏的分辨率

	int screenheight = 0; // 获得当前屏的分辨率

	int framewidth = 0; // 获得当前屏的分辨率

	int frameheight = 0; // 获得当前屏的分辨率

	JLabel jlbtime = new JLabel("等待时间");

	JTextArea jtatime = new JTextArea("0");

	public static String Dialogstring = "";

	public Mirage() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获得当前屏的分辨率
		screenwidth = screenSize.width;
		screenheight = screenSize.height;

		frame = new JFrame("Mirage");
		tool = new TestToolFrame();
		pane = new JPanel();
		s_pan = new JScrollPane();
		paneleft = new JPanel();
		model = new Table_Model();

		pane.setBackground(Color.white);
		paneleft.setBackground(Color.white);

		fileMenu.add(fileNew);
		fileMenu.add(fileOpen);
		// fileMenu.add(fileSave);
		fileMenu.add(fileExit);
		toolMenu.add(toolIllusion);
		toolMenu.add(toolImage);
		// editMenu.add(editCut);
		// editMenu.add(editCopy);
		// editMenu.add(editPaste);
		menuBar.add(fileMenu);
		// menuBar.add(editMenu);
		menuBar.add(toolMenu);
		frame.setJMenuBar(menuBar);

		button_0 = new JButton("导入数据");
		button_1 = new JButton("选取数据");
		button_2 = new JButton("添加数据");
		button_3 = new JButton("执行测试");
		button_4 = new JButton("保存文件");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// removeData();
				testcheck();
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				starttest();

			}
		});
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveData();
			}
		});

		jSplitPane1 = new JSplitPane();
		jScrollPane2 = new JScrollPane(paneleft,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane2.getViewport().add(paneleft, null);
		jSplitPane1.setLeftComponent(jScrollPane2);

		jScrollPane1 = new JScrollPane(pane,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1.getViewport().add(pane, null);
		jSplitPane1.setRightComponent(jScrollPane1);
		// 设置分割条位置
		jSplitPane1.setDividerLocation(200);

		frame.add(jSplitPane1, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupEventHandlers();
		// frame.setResizable(false);

		frame.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// 根据当前状况修改尺寸
				int w = frame.getWidth();
				int h = frame.getHeight();
				if (w < 800)
					w = 800;
				if (h < 600)
					h = 600;
				frame.setSize(w, h);
				setloc();
			}
		});

		framewidth = (int) (screenwidth * 0.8);
		frameheight = (int) (screenheight * 0.8);
		frame.setSize(framewidth, frameheight);
		frame.setLocation((screenwidth - framewidth) / 2,
				(screenheight - frameheight) / 3);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		framewidth = frame.getWidth(); // 获得当前屏的分辨率
		frameheight = frame.getHeight(); // 获得当前屏的分辨率
		
		try {
			tool.init(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		init();
	}

	public void init() {
		pane.removeAll();
		paneleft.removeAll();
		jScrollPane2.getViewport().add(paneleft, null);
		jScrollPane1.getViewport().add(pane, null);
		frame.setVisible(true);
		List<String> titlelist = tool.getsheetname();
		String[] title = titlelist
				.toArray(new String[titlelist.size()]);

//		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
//		DefaultMutableTreeNode father = new DefaultMutableTreeNode(
//				"father");
//		DefaultMutableTreeNode child = new DefaultMutableTreeNode(
//				"child");

		tree = new JTree(title);
		tree
				.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
					public void valueChanged(
							javax.swing.event.TreeSelectionEvent evt) {
						jTreeValueChanged(evt);
					}
				});
		paneleft.removeAll();
		paneleft.add(tree);
		frame.setVisible(true);
	}

	void setupEventHandlers() {
		// addWindowListener(new WindowHandler());
		fileNew.addActionListener(new MenuItemHandler());
		fileOpen.addActionListener(new MenuItemHandler());
		fileSave.addActionListener(new MenuItemHandler());
		fileExit.addActionListener(new MenuItemHandler());
		editCut.addActionListener(new MenuItemHandler());
		editCopy.addActionListener(new MenuItemHandler());
		editPaste.addActionListener(new MenuItemHandler());
		toolIllusion.addActionListener(new MenuItemHandler());
		toolImage.addActionListener(new MenuItemHandler());
	}

	public class WindowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public class MenuItemHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Exit")) {
				System.exit(0);
			} else if (cmd.equals("Image")) {
				Dialog dialog = new Dialog(frame, "Image Information", false);
			} else if (cmd.equals("Stardust")) {
				if (Stardust != null)
					Stardust.closeframe();
				List<String> list = new ArrayList<String>();
				
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("单独测试发送");
				model.setstardust(list);
			} else if (cmd.equals("Open")) {
				try {
					if (!tool.init(false)) {
						return;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					return;
				}
				init();
			} else if (cmd.equals("New")) {
				try {
					if (!tool.newfile()) {
						return;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					return;
				}
				init();
			}
		}
	}

	private void jTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();// 返回最后选定的节点
		nowSheet = selectedNode.toString();
		// if(selectedNode.toString().equals("child")){
		// }
		setTable();
		if (model.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "选取文件格式有误！", "文件格式",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void setTable() {
		pane.removeAll();
		pane.setLayout(null);
		model = new Table_Model(tool.getsheet(nowSheet));
		table = new JTable(model);
		
		TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(18);
        
        
		s_pan = new JScrollPane(table);
		s_pan.setBackground(Color.white);

		setloc();

		// pane.add(button_0);
		pane.add(button_1);
		// pane.add(button_2);
		pane.add(button_3);
		pane.add(button_4);
		pane.add(s_pan);
		pane.add(jlbtime);
		pane.add(jtatime);

		jScrollPane1.getViewport().add(pane, null);

		makeFace(table);
		frame.setVisible(true);
		System.out.println("数据加载完毕！");
	}

	private void setloc() {
		framewidth = frame.getWidth(); // 获得当前屏的分辨率
		frameheight = frame.getHeight(); // 获得当前屏的分辨率

		int buttonwidth = 100;
		int buttonheight = 30;

//		int beginwidth = 10;
//		int beginheight = 10;
//
//		int spacewidth = 5;
//		int spaceheight = 5;

		int Splitloc = jSplitPane1.getDividerLocation();

		jlbtime.setBounds(new Rectangle(
				(int) (framewidth - Splitloc - 50 - 200), 15, 100, 20));
		jtatime.setBounds(new Rectangle(
				(int) (framewidth - Splitloc - 50 - 100), 15, 100, 20));

		button_1.setBounds(new Rectangle(10, 10, buttonwidth, buttonheight));
		button_3.setBounds(new Rectangle(120, 10, buttonwidth, buttonheight));
		button_4.setBounds(new Rectangle(230, 10, buttonwidth, buttonheight));
		button_0.setBounds(new Rectangle(340, 10, buttonwidth, buttonheight));
		button_2.setBounds(new Rectangle(450, 10, buttonwidth, buttonheight));
		s_pan.setBounds(new Rectangle(10, 50,
				(int) (framewidth - Splitloc - 50), (int) (frameheight - 120)));

		if (!(frame.getExtendedState() == frame.MAXIMIZED_BOTH)) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获得当前屏的分辨率
			int screenwidth = screenSize.width;
			int screenheight = screenSize.height;
			int x = frame.getLocation().x;
			int y = frame.getLocation().y;
			if ((x + 800) > screenwidth || (y + 600) > screenheight) {
				frame.setLocation((screenwidth - framewidth) / 2,
						(screenheight - frameheight) / 2);
			}
		}
		frame.setVisible(true);
	}

	private void removeData() {
		model.removeRows(0, model.getRowCount());
		table.updateUI();
	}

	private void testcheck() {
		boolean checkall = false;
		for (int i = 0; i < model.getRowCount(); i++) {
			if (model.getValueAt(i, 0).equals(false)) {
				checkall = true;
				break;
			}
		}

		for (int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(checkall, i, 0);
		}
		table.updateUI();
	}

	private void removeLastData() {
		model.removeRow(model.getRowCount() - 1);
		table.updateUI();
	}

	private List getneed() {
		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("0");
		for (int i = 0; i < model.getRowCount(); i++) {
			if (model.getValueAt(i, 0).equals(true)) {
				list.add("1");
			} else {
				list.add("0");
			}
		}
		return list;
	}

	// 保存数据，暂时是将数据从控制台显示出来
	private void starttest() {
		int setTime = 0;
		try {
			setTime = Integer.parseInt(jtatime.getText());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(jtatime.getText() + "无法转换成运行等待时间！");
		}
		tool.setSetTime(setTime);
		List<String> listneed = getneed();
		tool.business(nowSheet, listneed);

		while (tool.getruntype()) {
			System.out.println("测试运行中...");
			try {
				// 对EXCEL文件中的所有sheet进行循环处理
				Thread curt = new Thread();
				curt.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		setTable();
	}

	// 保存数据，暂时是将数据从控制台显示出来
	private void saveData() {
		tool.saveFile();
		while (tool.getsavetype()) {
			System.out.println("测试运行中...");
			try {
				// 对EXCEL文件中的所有sheet进行循环处理
				Thread curt = new Thread();
				curt.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "文件保存成功！", "文件保存",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// 不支持格式
	public void exportTable(JTable table, File file) throws IOException {
		TableModel model = table.getModel();
		FileWriter out = new FileWriter(file);

		for (int i = 0; i < model.getColumnCount(); i++) {
			out.write(model.getColumnName(i) + "\t");
		}
		out.write("\n");
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount(); j++) {
				out.write(model.getValueAt(i, j).toString() + "\t");
			}
			out.write("\n");
		}
		out.close();
		System.out.println("write out to: " + file);
	}

	public static void makeFace(JTable table) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
					setHorizontalAlignment(SwingConstants.CENTER);
					if ("不通过".equals(model.getValueAt(row, 12))) {
						setBackground(Color.red);
						setForeground(Color.white);
					} else {
						setBackground(Color.white);
						setForeground(Color.black);
					}
					return super.getTableCellRendererComponent(table, value,
							isSelected, hasFocus, row, column);
				}
			};
			for (int i = 1; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new Mirage();
		System.out.println("开始\n------------------------------------");

	}

}
