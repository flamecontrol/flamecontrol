package src;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/**
 *
 * @author Administrator
 */
public class DropDragSupportTextArea extends JTextArea implements DropTargetListener{
    private DropTarget dropTarget;

    public DropDragSupportTextArea() {
        //注册DropTarget，并将它与组件相连，处理哪个组件的相连
        //即连通组件（第一个this）和Listener(第二个this)
        dropTarget = new DropTarget(this,DnDConstants.ACTION_COPY_OR_MOVE, this, true);
    }
    
    /**
     * 拖入文件或字符串,这里只说明能拖拽，并未打开文件并显示到文本区域中
     */
    public void dragEnter(DropTargetDragEvent dtde) {
        DataFlavor[] dataFlavors = dtde.getCurrentDataFlavors();
        if(dataFlavors[0].match(DataFlavor.javaFileListFlavor)){
            try {
                Transferable tr = dtde.getTransferable();
                Object obj = tr.getTransferData(DataFlavor.javaFileListFlavor);
                List<File> files = (List<File>)obj;
                
                for(int i = 0; i < files.size(); i++){
//                    append(read(files.get(0)));
                	setText(read(files.get(0)));
                }
            } catch (UnsupportedFlavorException ex) {

            } catch (IOException ex) {

            }
        }
    }

    public void dragOver(DropTargetDragEvent dtde) {
      
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
       
    }

    public void dragExit(DropTargetEvent dte) {
        
    }

    public void drop(DropTargetDropEvent dtde) {
        
    }
    
	 public String read(File file) throws IOException {
//			File[] fileArr = new File[255];
			InputStream is = null;
			is = new FileInputStream(file);
			
			AccessTextFile test = new AccessTextFile();
//			InputStream is = new FileInputStream("E:\\test.txt");
			StringBuffer buffer = new StringBuffer();
			test.readToBuffer(buffer, is);
			System.out.println(buffer);     // 将读到 buffer 中的内容写出来
			is.close();		
			return buffer.toString();
		 }

}
