package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class Table_Model extends AbstractTableModel {

    private static final long serialVersionUID = -7495940408592595397L;

    private Vector content = null;

    private List title_name = new ArrayList();
    
	Stardust testf = null;

    public Table_Model() {
        content = new Vector();
    }

    public Table_Model(int count) {
        content = new Vector(count);
    }
    
    public Table_Model(List listinfo) {
    	if(listinfo==null || listinfo.size()<2){
    		return ;
    	}
    	System.out.println("开始处理");
    	title_name = (List)listinfo.get(1);
    	title_name.add(0, "编号");
    	title_name.add(0, "选取");
    	for(int i=2 ;i<listinfo.size();i++){
	    	List rowList =(List)listinfo.get(i);
	    	if(content==null)
	    		content = new Vector(listinfo.size());
	    	Vector v = new Vector(rowList.size());
	        v.add(0, new Boolean(true));
	        v.add(1, new Integer(content.size()+1));
	        //int j = 0;
	        for(int j = 0;j<rowList.size();j++){
		        v.add(j+2,rowList.get(j));
	        }	       
	    	content.add(v);
    	}
    }
    
    
    public void addRow(String a1,String a2,String a3,String a4,String a5) {
        Vector v = new Vector(7);
        v.add(0, new Boolean(true));
        v.add(1, new Integer(content.size()));
        v.add(2, a1);
        v.add(3, a2);
        v.add(4, a3);
        v.add(5, a4);
        v.add(6, a5);
        content.add(v);
    }
    
    public void addRow(List listinfo) {
        content.add(listinfo);
    }

    public void removeRow(int row) {
        content.remove(row);
    }

    public void removeRows(int row, int count) {
        for (int i = 0; i < count; i++) {
            if (content.size() > row) {
                content.remove(row);
            }
        }
    }

    /**
    * 让表格中某些值可修改，但需要setValueAt(Object value, int row, int col)方法配合才能使修改生效
    */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	System.out.println("点击了第"+rowIndex+"行，第"+columnIndex+"列");
    	if(columnIndex == 0){
    		return true;
    	}else if(columnIndex == 1){
    		return false;
    	}
//		String urlkey = (String)getValueAt(rowIndex,4);
//    	if(area != null){
//        	area.dispose();
//    	}
//    	area = new TestJTextArea((String)getValueAt(rowIndex,columnIndex),urlkey);
//
//    	area.setSize(400, 300);
//    	area.setVisible(true);
		List<String> list = new ArrayList<String>();
		String urlkey = (String)getValueAt(rowIndex,2);
		if("".equals(urlkey))
			urlkey= "单独测试";
		list.add(urlkey+"&&&"+(String)getValueAt(rowIndex,5));
		list.add((String)getValueAt(rowIndex,6));
		list.add((String)getValueAt(rowIndex,7));
		list.add((String)getValueAt(rowIndex,11));
		list.add((String)getValueAt(rowIndex,10));
		list.add((String)getValueAt(rowIndex,2) + (String)getValueAt(rowIndex,3) + (String)getValueAt(rowIndex,4));


    	setstardust(list);
//        if (columnIndex == 1) {
//            return false;
//        }
//        return true;
        return false;
    }

    void setstardust(List<String> list) {
		// TODO Auto-generated method stub
    	if(testf != null){
    		testf.dispose();
    		testf = null;
    	}
    	testf = new Stardust(list);
	}

	/**
    * 使修改的内容生效
    */
    public void setValueAt(Object value, int row, int col) {
        ((Vector) content.get(row)).remove(col);
//        String str = (String)value;
//        str.replaceAll("\n","n");
        ((Vector) content.get(row)).add(col, value);
        this.fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
        return (String) title_name.get(col);
    }

    public int getColumnCount() {
        return title_name.size();
    }

    public int getRowCount() {
    	if(content==null){
    		return 0;
    	}
        return content.size();
    }

    public Object getValueAt(int row, int col) {
        return ((Vector) content.get(row)).get(col);
    }
    
    public Object getValueAt() {
        return (Vector) content;
    }

    /**
    * 返回数据类型
    */
    public Class getColumnClass(int col) {
//    	if(getValueAt(0, col)==null){
//    		return ;
//    	}
        return getValueAt(0, col).getClass();
    }
}
