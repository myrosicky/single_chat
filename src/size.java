import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
public class size extends JFrame implements KeyListener{
  JTextField jtf,jtf2;
  JButton jb1,jb2,ensure;
	JTextArea jl;
	JPanel jp1,jp2;
	String name;
	String ip="58.248.7.138";
	InetAddress ia;
	Socket so;
	PrintWriter pw;
	BufferedReader br;
	Boolean isclose=true;
  public size(){
	  try{ia=InetAddress.getByName(ip);
		Socket so=new Socket(ia,3000);
		isclose=false;
		 br=new BufferedReader(new InputStreamReader(so.getInputStream()));
		 pw=new PrintWriter(new OutputStreamWriter(so.getOutputStream()));
	  }
		catch(IOException e){}
	  
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setSize(700,500);
	  setTitle("client");
	  jp1=new JPanel();jp2=new JPanel();
		jtf=new JTextField("          ");jtf.setSize(30, 30);jtf.setVisible(false);
		jtf2=new JTextField("enter your name here");
		jtf2.addCaretListener(new CaretListener(){
			public void caretUpdate(CaretEvent e){
				if(jtf2.getText().equals("enter your name here")) jtf2.setText("          ");	
			}
		} );
		jtf2.addKeyListener(this);
		jl=new JTextArea("");jl.setSize(200, 200);
		jb1=new JButton("send");jb1.setVisible(false);jb2=new JButton("exit");jb2.setVisible(false);ensure=new JButton("ensure");ensure.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){jtf.setFocusable(true);name=jtf2.getText();ensure.setVisible(false);jtf2.setVisible(false);jtf.requestFocus();jb1.setVisible(true);jb2.setVisible(true);jtf.setVisible(true);}});
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(jtf.getText()!=""){
					jl.setText(jl.getText()+name+":"+jtf.getText()+"\n");jtf.requestFocus();
					pw.println(name+":"+jtf.getText());pw.flush();
				 jtf.setText("          ");
				
				
				}
				
			}
		});
		jtf.addKeyListener(this);jtf.addCaretListener(new CaretListener(){
			public void caretUpdate(CaretEvent e){
			}
			
		});
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				isclose=true;
				try{pw.close();br.close();so.close();}catch(IOException a){}
				System.exit(1);
				
			}
		});
		ensure.addKeyListener(this);
		jp2.add(jb1);jp2.add(jb2);jp1.add(jtf,"West");jp1.add(jl,"East");
		Container cc=getContentPane();cc.add(jp1,"North");cc.add(jp2,"South");cc.add(jtf2,"West");cc.add(ensure,"East");
	}
   public void keyReleased(KeyEvent e){
            if(e.getSource()==jtf2){if(e.getKeyChar()==KeyEvent.VK_ENTER)
            {
               ensure.doClick();	
            }}
            else if(e.getSource()==jtf){
            	if(e.getKeyChar()==KeyEvent.VK_ENTER)
                {
                   jb1.doClick();	
                }
            }
   }
 public void keyTyped(KeyEvent e){
	
   }
 public void keyPressed(KeyEvent e){
	 
 }
 public  void inin(){
	 while(isclose==false){
		try{
			String str=br.readLine();
			this.jl.setText(this.jl.getText()+str+"\n");
		    
		}catch(IOException e){}
	 }
 }
	public static void main(String[] args) {
		size si=new size();
		
		si.setVisible(true);
        si.inin();		

	}

}
