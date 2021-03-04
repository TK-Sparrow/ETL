
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class MapingSheet extends JFrame{
	static String newMappingPath="src/mapping.csv";
	Map<String,String> mappingSheet=new HashMap<String,String>();
	BufferedReader br,br1;
	List<String> mapSheetDate=new ArrayList<String>();
	String[] listele;
	private Object[] columns={"Select","L2 Field","SACCR Field"};
	DefaultTableModel dtm = new DefaultTableModel(null,columns);
	private JTable table=new JTable(dtm) {
	@Override
	public Class getColumnClass(int column){
		switch(column){
		case 0:
			return Boolean.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
			
		default:
			return column==0?Boolean.class:String.class;}}};
	private JScrollPane scrollPane=new JScrollPane(table);
			
	private JScrollBar vScroll=scrollPane.getVerticalScrollBar();
		
	private boolean isAutoScroll;
	 public MapingSheet(String L2ArmlPath,String saccrPath,String sourceSystem) throws IOException{
		 super("SACCR");
		 setLayout(new FlowLayout());
		 Dimension d=new Dimension(625,8*table.getRowHeight());
		 table.setPreferredScrollableViewportSize(d);
		 scrollPane.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS	 );
		 vScroll.addAdjustmentListener(new AdjustmentListener(){

			public void adjustmentValueChanged(AdjustmentEvent e) {
				isAutoScroll=!e.getValueIsAdjusting();}});
		 		File isExsist=new File(newMappingPath);
		 		if(!isExsist.exists()){
		 			isExsist.createNewFile();}
		 		br=new BufferedReader(new FileReader(L2ArmlPath));
		 		br1=new BufferedReader(new FileReader(saccrPath));
		 		BufferedReader mappingFile=new BufferedReader(new FileReader(newMappingPath));
		 		String rowByRow=mappingFile.readLine();
		 		String[] L2ARMLMappingattribute=null,SACCRMappingAttribute=null;
		 		String elements=br.readLine();
		 		String element1=br1.readLine();
		 		elements=elements.toUpperCase();
		 		element1=element1.toUpperCase();
		 		listele=elements.split(",");
		 		String[] listele1=element1.split(",");
		 		JTextArea textArea=new JTextArea();
		 		JButton button=new JButton("Add");
		 		JButton button1=new JButton(" Update in mappingsheet");
		 		JButton button4=new JButton("Delete SS");
		 		JButton button2=new JButton("Delete");
		 		JButton button3=new JButton("Back");
		 		while(rowByRow!=null){
		 			rowByRow=rowByRow.toLowerCase();
		 			String[] temp=rowByRow.split(",");
		 			if(temp.length>0&&temp[0].equalsIgnoreCase(sourceSystem)){
		 				if(temp[1].contains("l2")){
		 					L2ARMLMappingattribute=temp;}
		 				
		 				else{
		 					SACCRMappingAttribute=temp;
		 				}
		 		
		 			}
		 			mapSheetDate.add(rowByRow);
		 			rowByRow=mappingFile.readLine();
		 		
	 }	
		 		mappingFile.close();
		 		if(L2ARMLMappingattribute!=null){
		 			
		 			for(int i=2;i<L2ARMLMappingattribute.length;i++){
		 				if(L2ARMLMappingattribute[i]!=null&&!L2ARMLMappingattribute[i].trim().equals("")){
		 					mappingSheet.put(L2ARMLMappingattribute[i],SACCRMappingAttribute[i]);
		 					List<String> list=new ArrayList<String>(Arrays.asList(listele));
		 					list.remove(L2ARMLMappingattribute[i].toUpperCase());
		 					listele=list.toArray(new String[0]);
		 					List<String> list1=new ArrayList<String>(Arrays.asList(listele1));
		 					list1.remove(SACCRMappingAttribute[i].toUpperCase());
		 					listele1=list1.toArray(new String[0]);
		 					Object[] data={new Boolean(false),L2ARMLMappingattribute[i],SACCRMappingAttribute[i]};
		 					dtm.addRow(data);
		 					textArea.setText(textArea.getText()+"\n"+L2ARMLMappingattribute[i]+"mapped with"+SACCRMappingAttribute[i]);
		 					
		 					
		 				}
		 			}
		 		}
		 		setContentPane(new JLabel(new ImageIcon("I:\\games\\New folder\\New folder\\ETL TOOL\\Images\\aqua-d9b59c89.png")));
		 		setLayout(new FlowLayout());
		 		setResizable(false);

		 		JLabel jl=new JLabel("Mapping Functinality");
		 		add(jl);
		 		jl.setForeground(Color.white);
		 		jl.setFont(new Font("Serif",Font.PLAIN,40));
		 		jl.setBorder(BorderFactory.createEmptyBorder(0,70,0,70));
		 		JPanel emptyPanel1=new JPanel();
		 		
		 		JPanel emptyPanel=new JPanel();
		 		emptyPanel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		 		emptyPanel.setPreferredSize(new Dimension(745,20));
		 		emptyPanel.setVisible(true);
		 		add(emptyPanel);
		 		emptyPanel.setBackground(new java.awt.Color(0,0,0,0));
		 		emptyPanel1.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		 		emptyPanel1.setPreferredSize(new Dimension(745,20));
		 		emptyPanel1.setVisible(true);
		 		add(emptyPanel1);
		 		emptyPanel1.setBackground(new java.awt.Color(0,0,0,0));
		 		JPanel panel=new JPanel();
		 		JComboBox<String> cb=new JComboBox(listele);
		 		panel.add(cb);
		 		add(panel);
		 		JComboBox cb1=new JComboBox(listele1);
		 		panel.add(cb1);
		 		panel.add(button);
		 		panel.add(button1);
		 		panel.setBackground(new java.awt.Color(0,0,0,0));
		 		
		 		
		 		
		 		JPanel emptyPanel2=new JPanel();
		 		emptyPanel2.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		 		emptyPanel2.setPreferredSize(new Dimension(745,20));
		 		emptyPanel2.setVisible(true);
		 		add(emptyPanel2);
		 		emptyPanel2.setBackground(new java.awt.Color(0,0,0,0));
		 		
		 		
		 		emptyPanel2.setBackground(new java.awt.Color(0,0,0,0));
		 		JPanel tablePanel = new JPanel();
		 		JTableHeader anHeader = table.getTableHeader();
		 		
		 		anHeader.setForeground(new Color(0).white);
		 		anHeader.setBackground(new Color(0).darkGray);
		 		anHeader.setFont(new Font("Serif",Font.BOLD, 14));
		 		
		 		table.setFont(new Font("Serif",Font.PLAIN, 14));
		 		table.setBackground(Color.lightGray);
		 		
		 		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		 		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		 		table.setDefaultRenderer(String.class,centerRenderer);
		 		
		 		add(scrollPane, BorderLayout.CENTER);
		 		tablePanel.setBackground(new java.awt.Color(0,0,0,0));
		 		add(scrollPane,BorderLayout.CENTER);
		 		
		 		JPanel emptyPanel3 = new JPanel();
		 		emptyPanel3.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		 		emptyPanel3.setPreferredSize(new Dimension(700,10));
		 		emptyPanel3.setVisible(true);
		 		add(emptyPanel3);
		 		emptyPanel3.setBackground(new java.awt.Color(0,0,0,0));
		 		
		 		JPanel panel1= new JPanel();
		 		panel1.setPreferredSize(new Dimension(370,35));
		 		panel1.setVisible(true);
		 		panel1.add(button4);
		 		panel1.add(button2);
		 		panel1.add(button3);
		 		add(panel1);
		 		panel1.setBackground(new java.awt.Color(0,0,0,0));
		 		
		 		Image icon = new ImageIcon().getImage();//need to add image location
		 		setIconImage(icon);
		 		
		 		button.addActionListener(new ActionListener() {

		 		public void actionPerformed(ActionEvent e)
		 		{
		 			String comboBox=cb.getSelectedItem().toString();
		 			String comboBox1=cb1.getSelectedItem().toString();
		 			cb.removeItemAt(cb.getSelectedIndex());
		 			cb1.removeItemAt(cb1.getSelectedIndex());
		 			mappingSheet.put(comboBox.toLowerCase(),comboBox1.toLowerCase());
		 			textArea.setText(textArea.getText()+"\n"+comboBox+"mapped with"+comboBox1);
		 			Object[] data= { new Boolean(false), comboBox.toLowerCase(), comboBox1.toLowerCase()};
		 			dtm.addRow(data);
		 			tablePanel.setAlignmentX(CENTER_ALIGNMENT);
		 			tablePanel.setSize(100,20);
		 		}}
		 	);	
		 		button1.addActionListener(new ActionListener() {
		 		
		 		public void actionPerformed(ActionEvent e)
		 		{
		 			String L2newData="", SACCRnewData="";
		 			BufferedWriter result;
		 			try {
		 				result = new BufferedWriter(new FileWriter(new File(newMappingPath)));
		 				for(String key:mappingSheet.keySet())
		 				{
		 					if(key!=null && !key.equals(""))
		 					{
		 						L2newData = L2newData+","+key;
		 						SACCRnewData= SACCRnewData+","+mappingSheet.get(key);
		 					}
		 				}
		 				int flag1=0, flag2=0;
		 				for(int i=0;i<mapSheetDate.size();i++)
		 				{
		 					String[] temp = mapSheetDate.get(i).split(",");
		 					if(temp.length>0 && temp[0].equalsIgnoreCase(sourceSystem))
		 					{
		 						if(temp[1].contains("l2"))
		 						{
		 							mapSheetDate.set(i,sourceSystem+","+"L2 ARML"+L2newData);
		 							flag1=1;
		 						}
		 						else
		 						{
		 							mapSheetDate.set(i,sourceSystem+","+"SACCR"+SACCRnewData);
		 							flag2=1;
		 						}
		 					}
		 					result.write(mapSheetDate.get(i)+"\n");
		 				}
		 				if(flag1==0)
		 				{
		 					result.write(sourceSystem+","+"L2 ARML"+L2newData+"\n");
		 				}
		 				if(flag2==0)
		 				{
		 					result.write(sourceSystem+","+"SACCR"+SACCRnewData+"\n");
		 				}
		 				result.flush();
		 				result.close();
		 			}catch(IOException e1)
		 			{
		 				e1.printStackTrace();		
		 		}
		 		}
		 		});
		 		button2.addActionListener(new ActionListener() {
		 			public void actionPerformed(ActionEvent e)
		 			{
		 				for(int i=0;i<dtm.getRowCount();i++)
		 				{
		 					String checked=dtm.getValueAt(i,0).toString();
		 					if(checked.equalsIgnoreCase("trrue"))
		 					{
		 						String toDelete=dtm.getValueAt(i,1).toString();
		 						String saccr=dtm.getValueAt(i,2).toString();
		 						cb.addItem(toDelete.toUpperCase());
		 						cb1.addItem(saccr.toUpperCase());
		 						dtm.removeRow(i);
		 						i--;
		 						mappingSheet.remove(toDelete);
		 					}
		 				}
		 			}
		 		}
		 	);
		 		button3.addActionListener(new ActionListener() {
		 			public void actionPerformed(ActionEvent e)
		 			{
		 				setVisible(false);
		 			}
		 		}
		 		);
		 		
		 		button4.addActionListener(new ActionListener() {
		 			public void actionPerformed(ActionEvent e)
		 			{
		 				BufferedWriter result;
		 				try {
		 					result= new BufferedWriter(new FileWriter(new File(newMappingPath)));
		 					for(int i=0;i<mapSheetDate.size();i++)
		 					{
		 						String[] temp=mapSheetDate.get(i).split(",");
		 						if(temp.length>0 && temp[0].equalsIgnoreCase(sourceSystem))
		 						{					}
		 						else
		 						{
		 							result.write(mapSheetDate.get(i)+"\n");
		 						}
		 					}
		 					result.flush();
		 					result.close();
		 				}
		 				catch(IOException e1)
		 				{
		 					e1.printStackTrace();
		 					setVisible(false);
		 				}
		 			}
		 		}
		 			);
		 		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 		setSize(800,460);
		 		setLocationRelativeTo(null);
		 	}
		 	public static String mappingPath(String L2ArmlPath,String saccrPath,String sourceSystem) {
		 		SwingUtilities.invokeLater(new Runnable() {
		 			public void run()
		 			{
		 				try {
		 					new MapingSheet(L2ArmlPath,saccrPath,sourceSystem).setVisible(true);
		 				}
		 				catch(IOException e)
		 				{
		 					e.printStackTrace();
		 				}
		 			}
		 		}
		 		);
		 		return "pass";
		 	}


		 						
		 		}
		 				
		 			
		 		
		 		
		 		
		 		
		 		