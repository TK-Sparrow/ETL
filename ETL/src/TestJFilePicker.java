
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
@SuppressWarnings("serial")
public class TestJFilePicker extends JFrame{
@SuppressWarnings({"unchecked","rawtypes"})
public TestJFilePicker() throws IOException{
super("SACCR");
setContentPane(new JLabel(new ImageIcon("I:\\games\\New folder\\New folder\\ETL TOOL\\Images\\aqua-d9b59c89.png")));


String mappingPath="src/mapping.csv";
File isExist=new File(mappingPath);
if(!isExist.exists()){
isExist.createNewFile();
}
BufferedReader mappingFile=new BufferedReader(new FileReader(mappingPath));


String rowByRow=mappingFile.readLine();
Set<String>s=new HashSet<String>();
//s.add("ADD NEW");
while(rowByRow!=null){
rowByRow=rowByRow.toLowerCase();
String[] temp=rowByRow.split(",");
if(temp.length>0){
s.add(temp[0].toUpperCase());
}
rowByRow=mappingFile.readLine();
}
mappingFile.close();
int n=s.size();
String arr[]=new String[n+1];
arr[0]="ADD NEW";
int i=1;
for(String x:s)
arr[i++]=x;
setLayout(new FlowLayout());
JLabel jl=new JLabel("File Validation Tool");
add(jl);
jl.setForeground(Color.white);
jl.setFont(new Font("Serif",Font.PLAIN,40));
jl.setBorder(BorderFactory.createEmptyBorder(50,5,0,5));

JFilePicker filePicker=new JFilePicker("Select the source file","Select File");
filePicker.setMode(JFilePicker.MODE_SAVE);
filePicker.setBackground(new java.awt.Color(0,0,0,0));
filePicker.addFileTypeFilter(".xml","XMLFile");
filePicker.addFileTypeFilter(".csv","CSVFile");

JFileChooser fileChooser=filePicker.getFileChooser();
fileChooser.setCurrentDirectory(new File("c/:"));
add(filePicker);
filePicker.setBorder(BorderFactory.createEmptyBorder(50,5,5,5));
JFilePicker filePicker1=new JFilePicker("Select the target file","Select File");
filePicker1.setMode(JFilePicker.MODE_SAVE);
filePicker1.setBackground(new java.awt.Color(0,0,0,0));
filePicker1.addFileTypeFilter(".xml","XMLFile");
filePicker1.addFileTypeFilter(".csv","CSVFile");
JFileChooser fileChooser1=filePicker1.getFileChooser();
fileChooser1.setCurrentDirectory(new File("c/:"));
add(filePicker1);
filePicker1.setBorder(BorderFactory.createEmptyBorder(0,46,0,36));
setLayout(new FlowLayout());
JPanel panel2=new JPanel();
add(panel2);
panel2.setBackground(new java.awt.Color(0,0,0,0));
panel2.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
JLabel jl1=new JLabel("Select the Source System");
panel2.add(jl1);
jl1.setFont(new Font("Times New Roman",Font.PLAIN,20));
jl1.setForeground(Color.white);
String[] SourceSystem=new String[]{"Add New","SwapOne","Endur","Murex"};
JComboBox cb=new JComboBox(arr);
panel2.add(cb);

JTextField addnew=new JTextField(10);
addnew.setSize(100,40);
panel2.add(addnew);
JButton button1=new JButton("ADD");
JPanel panel=new JPanel();
JButton b=new JButton("VALIDATE");
b.setVisible(false);
add(panel);
panel.add(b);
panel.add(button1);
panel.setBackground(new java.awt.Color(0,0,0,0));
panel.setPreferredSize(new Dimension(500,35));
ActionListener cbActionListener=new ActionListener(){
@Override
public void actionPerformed(ActionEvent e){
String s=(String) cb.getSelectedItem();
System.out.println(s+"p"+s.equalsIgnoreCase("Add New"));
if(s!=null && s.equalsIgnoreCase("Add New")){
addnew.setVisible(true);
button1.setText("ADD");
//button1.setVisible(false);
b.setVisible(false);}
else{
b.setVisible(true);
button1.setText("EDIT");
//button1.setVisible(true);
addnew.setVisible(false);}}};
cb.addActionListener(cbActionListener);
Image icon=new ImageIcon("Images\\1102821.png").getImage();
setIconImage(icon);
JLabel jl2=new JLabel("SACCR-Standardized Approach for Counterparty Credit Risk");
add(jl2);
jl2.setBorder(BorderFactory.createEmptyBorder(75,0,0,530));
jl2.setFont(new Font("Times New Roman",Font.PLAIN,10));
jl2.setForeground(Color.white);
getContentPane().setBackground(new java.awt.Color(100,130,150));
setResizable(false);
button1.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent e){
if(filePicker.getSelectedFilePath()==null||filePicker.getSelectedFilePath().equals("")){
JOptionPane optionPane=new JOptionPane("File path missing for L2ARML",JOptionPane.WARNING_MESSAGE);
JDialog dialog=optionPane.createDialog("Status!");
dialog.setAlwaysOnTop(true);
dialog.setVisible(true);}




////


else if(filePicker1.getSelectedFilePath()==null || filePicker1.getSelectedFilePath().equals(""))
{
JOptionPane optionPane=new JOptionPane("File path missing for SACCR",JOptionPane.WARNING_MESSAGE);
JDialog dialog=optionPane.createDialog("status");
dialog.setAlwaysOnTop(true);//to show top of all other application
dialog.setVisible(true);
}//to visible the dialog}

else
{
	System.out.println(filePicker.getSelectedFilePath());
	System.out.println(filePicker1.getSelectedFilePath());
	System.out.println(cb.getSelectedItem().toString());
	try{
		String L2ARMLFilepath=filePicker.getSelectedFilePath(),SACCRFilePath=
		 filePicker1.getSelectedFilePath(),SourceSystem=cb.getSelectedItem().toString();
		 if(SourceSystem.equalsIgnoreCase("Add new"))
		 {
			 SourceSystem=addnew.getText().toString();
			 cb.addItem(SourceSystem.toUpperCase());
			 cb.setSelectedIndex(cb.getItemCount()-1);
		 }
		 int lastIndex=L2ARMLFilepath.lastIndexOf('.');
		 String format=L2ARMLFilepath.substring(lastIndex+1);
		 System.out.println("format"+format);
		 if(format.equalsIgnoreCase("xml"))
		 {
			 System.out.println("Contains xml");
			 L2ARMLFilepath=ConvertXMLtoCSV.ConvertCSV(L2ARMLFilepath);
			 
		 }
		 lastIndex=SACCRFilePath.lastIndexOf('.');
		 format=SACCRFilePath.substring(0,lastIndex+1);
		 if(format.equalsIgnoreCase("xml"))
		 {
			 SACCRFilePath=ConvertXMLtoCSV.ConvertCSV(SACCRFilePath);
			 
		 }
		 MapingSheet.mappingPath(L2ARMLFilepath,SACCRFilePath,SourceSystem);
		 
	}
	catch(IOException e1)
	{
		//TO DO Auto generated ca block
		e1.printStackTrace();
	}
}
}
});



b.addActionListener(new ActionListener()
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//TODO Auto generated method stub
		if(filePicker.getSelectedFilePath()==null||filePicker.getSelectedFilePath().equals(""))
		{
			JOptionPane optionPane=new JOptionPane("File path missing for L2ARML",JOptionPane.WARNING_MESSAGE);
			JDialog dialog=optionPane.createDialog("Status");
			dialog.setAlwaysOnTop(true);//to show top of all other applications
			dialog.setVisible(true);
		}
			else if(filePicker1.getSelectedFilePath()==null||filePicker1.getSelectedFilePath().equals(""))
				{
					JOptionPane optionPane=new JOptionPane("File path missing for SACCR",JOptionPane.WARNING_MESSAGE);
					JDialog dialog=optionPane.createDialog("Status");
					dialog.setAlwaysOnTop(true);//to show top of all other application
					dialog.setVisible(true);
					
				}
				else{
					System.out.println(filePicker.getSelectedFilePath());
					System.out.println(filePicker1.getSelectedFilePath());
					System.out.println(cb.getSelectedItem().toString());
				
				try{
					String L2ARMLFilepath=filePicker.getSelectedFilePath(),SACCRFilePath=filePicker1.getSelectedFilePath(),
					   SourceSystem=cb.getSelectedItem().toString();
					   if(SourceSystem.equalsIgnoreCase("Add New"))
					   {
						   SourceSystem=addnew.getText().toString();
					   }
						   int lastIndex=L2ARMLFilepath.lastIndexOf('.');
						   String format=L2ARMLFilepath.substring(lastIndex+1);
						   System.out.println("format"+format);
						   if(format.equalsIgnoreCase("xml"))
						   {
							   System.out.println("Contains xml");
							   L2ARMLFilepath=ConvertXMLtoCSV.ConvertCSV(L2ARMLFilepath);
							   
						    }
							lastIndex=SACCRFilePath.lastIndexOf('.');
							format=SACCRFilePath.substring(0,lastIndex+1);
							if(format.equalsIgnoreCase("xml"))
							{
								SACCRFilePath=ConvertXMLtoCSV.ConvertCSV(SACCRFilePath);
							}
						   String[] status=CSVCompare.CSVCompared(L2ARMLFilepath,SACCRFilePath,SourceSystem);
						   JPanel panel=new JPanel();
						   JLabel newLable=new JLabel("Validation Successful");
						   panel.add(newLable);
						   JTextArea textArea=new JTextArea(status[0]);
						   textArea.setAlignmentX(CENTER_ALIGNMENT);
						   panel.add(textArea);
						   //label.setName(status);
						   
						   int n=JOptionPane.showConfirmDialog(
						   null,panel,"SACCR Test Execution Status",
						   JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
						   
						   if(n==JOptionPane.YES_OPTION)
						   {
							  Desktop.getDesktop().open(new File(status[1]));
							  
						   }
						   else if(n==JOptionPane.NO_OPTION)
						   {
							   
						   }
						   else
						   {
							   
						   }
						   
						 
					   }
					   catch(IOException e1)
					   {
						   //TODO Auto generated catch block
						   e1.printStackTrace();
					   }
				}
			}
		
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,460);
		//setSize(1000,700);
		setLocationRelativeTo(null);
	}//center  on screen
		
		
		public static void main(String[] args)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					try{
						new TestJFilePicker().setVisible(true);
						
					}
					catch(IOException e)
					{
						//TODO Auto generated catch block
						e.printStackTrace();
						
					}
				}
			});
		}
}
	
	




