package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Stardust extends JFrame {
	List<String> listkey = new ArrayList<String>();

	List<String> listvalue = new ArrayList<String>();

	private JPanel pane = null;

	private TestToolFrame tool = null;

	private JButton buttonstart = null;

	private JButton buttonclose = null;

	private JButton buttonreset = null;

	private JButton buttonread = null;

	private JButton buttonwrite = null;

	private JButton buttonformat = null;

	private JButton buttonbodyformat = null;

	private JButton buttonviewres = null;

	private JButton buttonviewbody = null;

	List<String> baselist = new ArrayList<String>();

	boolean booViewbody = false;

	boolean booViewres = false;

	boolean booformatres = false;

	String formatres = "";

	JComboBox comBox = null;
	
	JComboBox comBoxChar = null;

	String JsonFormat = "    ";

	JTextArea jtaurl = null;

	JTextArea jtahead = null;

	JTextArea jtabody = null;

	JTextArea jtares = null;

	JTextArea jtarehead = null;

	JLabel jlburl = new JLabel("地址");

	JLabel jlbhead = new JLabel("请求头");

	JLabel jlbbody = new JLabel("请求体");

	JLabel jlbbodyinfo = new JLabel("支持拖拽");

	JLabel jlbresinfo = new JLabel("支持拖拽");

	JLabel jlbres = new JLabel("应答体");

	JLabel jlbrehead = new JLabel("应答头");

	JLabel jlbtestcase = null;

	JScrollPane scrollurl = null;

	JScrollPane scrollhead = null;

	JScrollPane scrollbody = null;

	JScrollPane scrollres = null;

	JScrollPane scrollrehead = null;

	JsonTool jsontool = new JsonTool();

	int framewidth = 0; // 获得当前屏的分辨率

	int frameheight = 0; // 获得当前屏的分辨率
	
	String settingfile = (String)(AutoProperties.getInstance().get("settingfile"));

	public Stardust(List<String> list) {
		baselist = new ArrayList<String>(list);
		seturllist(baselist.get(0));
		this.setTitle("Stardust");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeframe();
			}
		});
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);

		framewidth = this.getWidth(); // 获得当前屏的分辨率
		frameheight = this.getHeight(); // 获得当前屏的分辨率
		tool = new TestToolFrame();
		pane = new JPanel();
		pane.setLayout(null);
		buttonstart = new JButton("发送");
		buttonclose = new JButton("关闭");
		buttonreset = new JButton("重置");
		buttonread = new JButton("读取");
		buttonwrite = new JButton("写入");
		buttonformat = new JButton("格式化");
		buttonbodyformat = new JButton("格式化");
		buttonviewres = new JButton("显示");
		buttonviewbody = new JButton("显示");

		ComboBoxModel mode = new AModel();
		ComboBoxModel modeChar = new BModel();
		comBox = new JComboBox(mode);
		comBox.setBackground(Color.WHITE);
		comBoxChar = new JComboBox(modeChar);
		comBoxChar.setBackground(Color.WHITE);

		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				// int state = itemEvent.getStateChange();
				// System.out.println((state == ItemEvent.SELECTED) ? "Selected"
				// : "Deselected");
				// System.out.println("Item: " + itemEvent.getItem());
				// ItemSelectable is = itemEvent.getItemSelectable();
				// System.out.println(", Selected: " + selectedString(is));
				ItemSelectable is = itemEvent.getItemSelectable();
				System.out.println(selectedString(is));
				seturlvalue(selectedString(is));
			}
		};
		comBox.addItemListener(itemListener);

		comBox.getEditor().getEditorComponent().addKeyListener(
				new KeyListener() {

					public void keyPressed(KeyEvent e) {

						// TODO Auto-generated method stub
						System.out.println("keyPressed");
					}

					public void keyReleased(KeyEvent e) {

						// TODO Auto-generated method stub

						// 获取输入的字符
						// System.out.println(comBox.getEditor().getItem().toString());
						System.out.println("keyReleased");
					}

					public void keyTyped(KeyEvent e) {

						// TODO Auto-generated method stub
						System.out.println("keyTyped");
					}
				});

		// comBox.setEditable(true);

		jtaurl = new JTextArea(list.get(0));
		jtahead = new JTextArea(list.get(1));
		jtabody = new DropDragSupportTextArea();
		jtabody.setText(list.get(2));
		jtares = new DropDragSupportTextArea();
		jtares.setText(list.get(3));
		jtarehead = new JTextArea(list.get(4));

		jlbtestcase = new JLabel("用例说明：" + list.get(5));

		// jtahead.setColumns(20);
		// jtahead.setRows(5);
		// jtahead.setDragEnabled(true);

		jtaurl.setLineWrap(true);
		jtahead.setLineWrap(true);
		jtabody.setLineWrap(true);
		jtares.setLineWrap(true);
		jtarehead.setLineWrap(true);

		scrollurl = new JScrollPane(jtaurl);
		scrollhead = new JScrollPane(jtahead);
		scrollbody = new JScrollPane(jtabody);
		scrollres = new JScrollPane(jtares);
		scrollrehead = new JScrollPane(jtarehead);

		scrollurl
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollurl
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollhead
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollhead
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollbody
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollbody
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollres
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollres
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollrehead
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollrehead
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		setloc();

		buttonstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtares.removeAll();
				jtarehead.removeAll();
				List<String> list = new ArrayList<String>();
				String Charset = comBoxChar.getSelectedItem().toString();
				list = tool.sendMsgPOST(jtahead.getText(), jtaurl.getText(),
						jtabody.getText(),Charset);
				String res = list.get(0);
				jtares.setText(res);
				jtarehead.setText(list.get(1));
				booformatres = false;
				resFormat();
				reflash();
			}
		});
		buttonclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeframe();
			}

		});
		buttonreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comBox.setSelectedIndex(0);
				jtahead.setText(baselist.get(1));
				jtabody.setText(baselist.get(2));
				jtares.setText(baselist.get(3));
				jtarehead.setText(baselist.get(4));
			}
		});
		buttonread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jtabody.setText(read());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonwrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					writefile();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonformat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resFormat();
			}
		});
		buttonbodyformat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bodyFormat();
			}
		});
		buttonviewres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (booViewres) {
					booViewbody = false;
					booViewres = false;
					scrollhead.setBounds(new Rectangle(70, 140, 700, 120));
					scrollbody.setBounds(new Rectangle(70, 270, 700, 120));
					scrollres.setBounds(new Rectangle(70, 400, 700, 120));
					scrollrehead.setBounds(new Rectangle(70, 530, 700, 120));
				} else {
					booViewres = true;
					scrollhead.setBounds(new Rectangle(0, 0, 0, 0));
					scrollbody.setBounds(new Rectangle(0, 0, 0, 0));
					scrollrehead.setBounds(new Rectangle(0, 0, 0, 0));
					scrollres.setBounds(new Rectangle(70, 140, 700, 510));
				}
				reflash();
			}
		});

		buttonviewbody.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (booViewbody) {
					booViewbody = false;
					booViewres = false;
					scrollhead.setBounds(new Rectangle(70, 140, 700, 120));
					scrollbody.setBounds(new Rectangle(70, 270, 700, 120));
					scrollres.setBounds(new Rectangle(70, 400, 700, 120));
					scrollrehead.setBounds(new Rectangle(70, 530, 700, 120));
				} else {
					booViewbody = true;
					scrollhead.setBounds(new Rectangle(0, 0, 0, 0));
					scrollbody.setBounds(new Rectangle(70, 140, 700, 510));
					scrollrehead.setBounds(new Rectangle(0, 0, 0, 0));
					scrollres.setBounds(new Rectangle(0, 0, 0, 0));
				}
				reflash();
			}
		});
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				setsize();
				setloc();
			}

		});

		jtares.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("changedUpdate");
				formatres = "";
				buttonformat.setText("Format");
				booformatres = false;
			}

			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("insertUpdate");
				formatres = "";
				buttonformat.setText("Format");
				booformatres = false;
			}

			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("removeUpdate");
				formatres = "";
				buttonformat.setText("Format");
				booformatres = false;
			}

		});

		pane.add(buttonviewbody);
		pane.add(buttonviewres);
		pane.add(buttonformat);
		pane.add(buttonbodyformat);
		pane.add(jlburl);
		pane.add(jlbhead);
		pane.add(jlbbody);
		pane.add(jlbbodyinfo);
		pane.add(jlbresinfo);
		pane.add(jlbres);
		pane.add(jlbrehead);
		pane.add(jlbtestcase);

		pane.add(comBox);
		pane.add(comBoxChar);		
		pane.add(scrollurl);
		pane.add(scrollhead);
		pane.add(scrollbody);
		pane.add(scrollres);
		pane.add(scrollrehead);
		pane.add(buttonstart);
		pane.add(buttonclose);
		pane.add(buttonreset);
		pane.add(buttonread);
		pane.add(buttonwrite);

		seturlvalue(comBox.getSelectedItem().toString());
		this.add(pane);
		// this.setResizable(false);

		framewidth = 800;
		frameheight = 768;
		int frameheightbottom = 50 * 1;	//桌面底部任务栏行数
		this.setSize(framewidth, frameheight);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获得当前屏的分辨率
		int screenwidth = screenSize.width;
		int screenheight = screenSize.height;
		int width = (screenwidth - framewidth) / 2;	//当前展示横位置
		int height = (screenheight
				- frameheight - frameheightbottom) / 2;	//当前展示竖位置
		if (height<0){
			height = 0;
		}
		this.setLocation(width,height);

		this.setVisible(true);
		setloc();

	}

	private void writefile() throws Exception {
		String text = jtares.getText();
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showSaveDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(text.getBytes());
			} catch (IOException ioe) {

			}finally{
				file= null;
				fileChooser = null;
			}
		}

	}

	private void setsize() {
		// 根据当前状况修改尺寸
		int w = this.getWidth();
		int h = this.getHeight();
		if (w < 800) {
			w = 800;
		}
		if (h < 600) {
			h = 600;
		}
		this.setSize(w, h);
	}

	public void reflash() {
		this.setVisible(true);
	}

	private void setloc() {
		framewidth = this.getWidth(); // 获得当前屏的分辨率
		frameheight = this.getHeight(); // 获得当前屏的分辨率
		int loc = 0;
		int leftloc = 10;
		int rightloc = framewidth / 2;
		int distance = 10;
		int lbwidth = 70;
		int lbheight = 20;
		int headheight = 120;
		int bodyheight = 400;

		bodyheight = frameheight - 110 - lbheight * 2 - distance * 4
				- headheight - 30;

		int toploc = 0;
		int lbawidth = framewidth / 2 - distance * 3 - lbwidth;
		// 标题
		toploc = 10;
		jlbtestcase.setBounds(new Rectangle(leftloc, toploc, framewidth - 30,
				lbheight));

		// URL
		toploc = 40;
		loc = leftloc;
		jlburl.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));
		loc = leftloc + lbwidth + distance;
		comBox.setBounds(new Rectangle(loc, toploc, 150, lbheight));
		loc = leftloc + lbwidth + distance * 2 + 150;
		scrollurl.setBounds(new Rectangle(loc, toploc, framewidth - loc - 20,
				lbheight));

		// HEAD
		toploc = toploc + distance + lbheight;
		loc = leftloc;
		jlbhead.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));
		loc = leftloc + lbwidth + distance;
		scrollhead.setBounds(new Rectangle(loc, toploc, lbawidth, headheight));
		loc = rightloc;
		jlbrehead.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));
		loc = rightloc + lbwidth + distance;
		scrollrehead
				.setBounds(new Rectangle(loc, toploc, lbawidth, headheight));

		// BODY
		toploc = toploc + distance + headheight;
		loc = leftloc;
		jlbbody.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));
		loc = leftloc + lbwidth + distance;
		scrollbody.setBounds(new Rectangle(loc, toploc, lbawidth, bodyheight));
		loc = rightloc;
		jlbres.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));
		loc = rightloc + lbwidth + distance;
		scrollres.setBounds(new Rectangle(loc, toploc, lbawidth, bodyheight));

		// BODY1
		toploc = toploc + distance + lbheight;
		loc = leftloc;
		jlbbodyinfo.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));
		loc = rightloc;
		jlbresinfo.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));

		// BODY2
		toploc = toploc + distance + lbheight;
		loc = leftloc;
		buttonread.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));
		loc = rightloc;
		buttonwrite.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));

		// BODY3
		toploc = toploc + distance + lbheight;
		loc = leftloc;
		// buttonbodyformat.setBounds(new Rectangle(loc, toploc, lbwidth,
		// lbheight));
		loc = rightloc;
		buttonformat.setBounds(new Rectangle(loc, toploc, lbwidth, lbheight));

		// BODY4
		toploc = toploc + distance + lbheight;
		loc = leftloc;
		// buttonviewbody.setBounds(new Rectangle(loc, toploc, lbwidth,
		// lbheight));
		loc = rightloc;
		// buttonviewres.setBounds(new Rectangle(loc, toploc, lbwidth,
		// lbheight));

		// SEND
		toploc = toploc + distance + bodyheight - (distance + lbheight) * 4;
		buttonstart.setBounds(new Rectangle(100, toploc, 100, lbheight));
		buttonclose.setBounds(new Rectangle(210, toploc, 100, lbheight));
		buttonreset.setBounds(new Rectangle(320, toploc, 100, lbheight));
		comBoxChar.setBounds(new Rectangle(430, toploc, 100, lbheight));

		if (!(this.getExtendedState() == this.MAXIMIZED_BOTH)) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获得当前屏的分辨率
			int screenwidth = screenSize.width;
			int screenheight = screenSize.height;
			int x = this.getLocation().x;
			int y = this.getLocation().y;
			if ((x + 800) > screenwidth || (y + 600) > screenheight) {
				this.setLocation((screenwidth - framewidth) / 2,
						(screenheight - frameheight) / 2);
			}
		}

		this.setVisible(true);
	}

	private void resFormat() {
		if (booformatres) {
			jtares.setText(formatres);
			buttonformat.setText("Format");
			booformatres = false;
		} else {
			String res = jtares.getText();
			formatres = res;
			if ("".equals(formatres)) {
				return;
			} else if ("{".equals(formatres.subSequence(0, 1))) {
				formatres = JsonTool.formatJson(formatres, JsonFormat);
			} else if ("<".equals(formatres.subSequence(0, 1))) {
				formatres = XmlFormat.formatXml(formatres);
			}
			// TODO 设置setText后formatres变量被清空
			jtares.setText(formatres);
			formatres = res;
			buttonformat.setText("还原");
			booformatres = true;
		}
	}

	private void bodyFormat() {
		if (booformatres) {
			jtares.setText(formatres);
			buttonformat.setText("Format");
			booformatres = false;
		} else {
			String res = jtares.getText();
			formatres = res;
			if ("".equals(formatres)) {
				return;
			} else if ("{".equals(formatres.subSequence(0, 1))) {
				formatres = JsonTool.formatJson(formatres, JsonFormat);
			} else if ("<".equals(formatres.subSequence(0, 1))) {
				formatres = XmlFormat.formatXml(formatres);
			}
			// TODO 设置setText后formatres变量被清空
			jtares.setText(formatres);
			formatres = res;
			buttonformat.setText("还原");
			booformatres = true;
		}
	}

	static private String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}

	private void seturlvalue(String urlkey) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listkey.size(); i++) {
			if (urlkey.equals(listkey.get(i))) {
				jtaurl.setText(listvalue.get(i));
			}
		}
	}

	private void seturllist(String urlinfo) {
		// TODO Auto-generated method stub
		String resultName;
		if(settingfile == null){
			resultName = "";
		}else{
			try {
				resultName = new String(settingfile.getBytes("ISO-8859-1"),"gbk");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!"".equals(urlinfo)) {
			String info[] = urlinfo.split("&&&");
			if (info.length > 1) {
				listkey.add(info[0]);
				listvalue.add(info[1]);
			} else {
				listkey.add(info[0]);
				listvalue.add("");
			}
		}
		listkey.add("POI管理功能");
		listvalue
				.add("http://10.10.92.238:5080/coberturaDemo/req");
		listkey.add("切客");
		listvalue
				.add("http://10.10.92.153:9099/niapf/server/business/CheckInServlet");
		listkey.add("问答");
		listvalue
				.add("http://10.10.92.153:9099/niapf/server/business/QuestionServlet");
		listkey.add("挂号");
		listvalue
				.add("http://10.10.92.153:9099/niapf/server/business/RegisterServlet");
		listkey.add("纠错");
		listvalue
				.add("http://10.10.92.153:9099/niapf/server/business/PutRightServlet");
		listkey.add("积分");
		listvalue
				.add("http://10.10.92.153:9099/niapf/server/business/ScoreServlet");
		listkey.add("优惠卷");
		listvalue
				.add("http://10.10.92.153:9099/niapf/server/business/DownloadCouponsSelvet");
		listkey.add("导航点");
		listvalue
				.add("http://10.10.92.153:9099/niapf/server/business/NavigationServlet");
		listkey.add("预留");
		listvalue.add("没事选预留做妹啊？？？？");
	}

	public String read() throws IOException {
		File[] fileArr = new File[255];
		InputStream is = null;
		JFileChooser jfc = new JFileChooser();
		if (null != jfc) {
			jfc.setMultiSelectionEnabled(true);
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Test File", "txt", "bak", "xml", "properties", "ini");
		jfc.setFileFilter(filter);
		if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null)) {

			// TODO 多文件选取处理或屏蔽
			fileArr = jfc.getSelectedFiles();
			is = new FileInputStream(fileArr[0]);
		} else {
			return "";
		}
		AccessTextFile test = new AccessTextFile();
		// InputStream is = new FileInputStream("E:\\test.txt");
		StringBuffer buffer = new StringBuffer();
		test.readToBuffer(buffer, is);
		System.out.println(buffer); // 将读到 buffer 中的内容写出来
		is.close();
		return buffer.toString();
	}

	public void closeframe() {
		this.dispose();
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list
				.add("POI检索&&&http://10.10.92.238:5080/coberturaDemo/req");
//		list
//				.add("POST\n"
//						+ "Connection:Keep-Alive\n"
//						+ "Accept:application/vnd.wap.wmlc, application/vnd.wap.wmlscriptc, text/vnd.wap.wml, image/vnd.wap.wbmp, */*\n"
//						+ "Accept-Charset:utf-8, iso-8859-1\n"
//						+ "Accept-Language:en, zh-cn\n"
//						+ "Content-Length:512\n"
//						+ "Via:HTTP/1.1 LNSY-PS-WAP2-GW06 (infoX-WISG, Huawei Technologies)\n"
//						+ "User-Agent1:termver/5 termos/ce5 termcode/020002 termdbver/0005\n"
//						+ "x-up-calling-line-id:8615840513611\n"
//						+ "xx-up-calling-line-id:8615840513611\n"
//						+ "x-protocol-version:1.0\n"
//						+ "x-session-id:9dc23b83-8eb7-4b06-9a5f-ec62eea5311b\n"
//						+ "x-service-type:003C\n"
//						+ "x-on-id:2\n"
//						+ "X-UGC-SESSIONID:9dc23b83-8eb7-4b06-9a5f-ec62eea5311b\n"
//						+ "X-UGC-CITY-CODE:024");
		list
		.add("<?xml version=\"1.0\" encoding=\"GBK\"?><TSP_Req><ICHR><PROTOCOL-VERSION>0100</PROTOCOL-VERSION><BUSSINESS-ID>100</BUSSINESS-ID><SERVICES-TYPE>411</SERVICES-TYPE><NET-ACCOUNT>1111111111</NET-ACCOUNT><NET-PASSWD>password</NET-PASSWD><MSISDN>13478330122</MSISDN><REQUESTID>ST33333333</REQUESTID><TESTFLAG>1</TESTFLAG></ICHR><VLR></VLR></TSP_Req>");

		list
				.add("{\"programmers\":[{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\",\"email\":\"aaaa\"},{\"firstName\":\"Jason\",\"lastName\":\"Hunter\",\"email\":\"bbbb\"},{\"firstName\":\"Elliotte\",\"lastName\":\"Harold\",\"email\":\"cccc\"}],\"authors\":[{\"firstName\":\"Isaac\",\"lastName\":\"Asimov\",\"genre\":\"sciencefiction\"},{\"firstName\":\"Tad\",\"lastName\":\"Williams\",\"genre\":\"fantasy\"},{\"firstName\":\"Frank\",\"lastName\":\"Peretti\",\"genre\":\"christianfiction\"}],\"musicians\":[{\"firstName\":\"Eric\",\"lastName\":\"Clapton\",\"instrument\":\"guitar\"},{\"firstName\":\"Sergei\",\"lastName\":\"Rachmaninoff\",\"instrument\":\"piano\"}]}");
		list.add("");
		list.add("");
		list.add("单独测试发送");

		Stardust f = new Stardust(list);
	}

	class AModel extends DefaultComboBoxModel {
		AModel() {
			for (int i = 0; i < listkey.size(); i++) {
				addElement(listkey.get(i));
			}
		}
	}
	class BModel extends DefaultComboBoxModel {
		BModel() {
			addElement("GBK");
			addElement("UTF-8");
		}
	}
}
