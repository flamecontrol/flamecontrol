package src;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Dialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel jLabelMerberIDTitle = null;
    private JLabel jLabelHelp = null;
    private JTextField jTextFieldMerberID = null;
    private JButton jButtonSure = null;
    private JButton jButtonExit = null;
    
    public Dialog(Frame owner,String s,boolean b) {
        super(owner,s,b);
        initialize();
    }
    
    private void initialize() {
        this.setSize(782, 198);
        this.setContentPane(getJContentPane());
        this.setLocationRelativeTo(this);
        this.jTextFieldMerberID.setText(getClipBoardContents());
        this.setVisible(true);
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabelMerberIDTitle = new JLabel();
            jLabelMerberIDTitle.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 12));
            jLabelMerberIDTitle.setText("请输入信息");
            jLabelMerberIDTitle.setBounds(new Rectangle(49, 37, 114, 33));
            jLabelHelp = new JLabel();
            jLabelHelp.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 12));
            jLabelHelp.setText("信息类型：网络URL（http://img.neusoft.com/img/1.gif），本地图片路径（E:\\img\\2.jpg），Base64图片流（iVBORw0K）");
            jLabelHelp.setBounds(new Rectangle(49, 70, 714, 33));
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabelMerberIDTitle, null);
            jContentPane.add(jLabelHelp, null);
            jContentPane.add(getJTextFieldMerberID(), null);
            jContentPane.add(getJButtonSure(), null);
            jContentPane.add(getJButtonExit(), null);
        }
        return jContentPane;
    }

    private JTextField getJTextFieldMerberID() {
        if (jTextFieldMerberID == null) {
            jTextFieldMerberID = new JTextField();
            jTextFieldMerberID.setBounds(new Rectangle(162, 37, 314, 33));
        }
        return jTextFieldMerberID;
    }
    
    
    private JButton getJButtonSure() {
        if (jButtonSure == null) {
            jButtonSure = new JButton();
            jButtonSure.setBounds(new Rectangle(61, 98, 70, 33));
            jButtonSure.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 12));
            jButtonSure.setText("确定");
            jButtonSure.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                            String id = jTextFieldMerberID.getText();    
//                            b.jLabelNumShow.setText(id);
                            setVisible(false);
                            System.out.println("确定");
                            if(!"".equals(id)){
//                            	id = id.replaceAll("\\/", "\\\\");
                				ImageFrame inst = new ImageFrame(id);
                				inst.setLocationRelativeTo(null);
                				inst.setAlwaysOnTop(true);
                				inst.setVisible(true);
                            }
                            }});
        }
        return jButtonSure;
    }

    
    private JButton getJButtonExit() {
        if (jButtonExit == null) {
            jButtonExit = new JButton();
            jButtonExit.setBounds(new Rectangle(192, 98, 70, 33));
            jButtonExit.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 12));
            jButtonExit.setText("取消");
            jButtonExit.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    setVisible(false);
                    System.out.println("取消");
                }
            });
        }
        return jButtonExit;
    }
    
    public static void main(String[] args) {
    	JFrame f = new JFrame("Test");
    	Dialog aa = new Dialog(f,"",true);
    	
    }
    /**
     * 获得剪贴板的内容
     * @return
     */
    public static String getClipBoardContents() {
        String text = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null) &&
            contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                text = (String) contents.getTransferData(DataFlavor.stringFlavor);
                return text;
            }
            catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    

}  
