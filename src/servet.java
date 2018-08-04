import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.net.*;
import java.io.*;
public class servet extends JFrame implements KeyListener{
  JTextField jtf,jtf2;
  JButton jb1,jb2,ensure;
	JTextArea jl;
	JPanel jp1,jp2;
	String name;
	String ip="";
	InetAddress ia;
	Socket so;
	PrintWriter pw;
	BufferedReader br;
	Boolean isclose=true;
	ServerSocket ss;
 
	public servet(){}
	
	public void gogogo()
  {
	  try
	  {
		 br=new BufferedReader(new InputStreamReader(so.getInputStream()));
		 pw=new PrintWriter(new OutputStreamWriter(so.getOutputStream()));
	  }
		catch(IOException e){}
	  
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setSize(700,500);
	  setTitle("server");
	  jp1=new JPanel();jp2=new JPanel();jp1.setLayout(null);jp2.setLayout(null);
		jtf=new JTextField("          ");jtf.setSize(30, 30);jtf.setVisible(false);
		jtf2=new JTextField("enter your name here");
		jtf2.addCaretListener(new CaretListener(){
			public void caretUpdate(CaretEvent e){
				if(jtf2.getText().equals("enter your name here")) jtf2.setText("  ");	
			}
		} );
		jtf2.addKeyListener(this);
		jtf.addKeyListener(this);jtf.addCaretListener(new CaretListener(){
			public void caretUpdate(CaretEvent e){
				
			}
			
		});
		jl=new JTextArea("");jl.setSize(200,200);
		jb1=new JButton("send");jb1.setVisible(false);jb2=new JButton("exit");
		jb2.setVisible(false);ensure=new JButton("ensure");
        ensure.addActionListener(new ActionListener()
      {
	public void actionPerformed(ActionEvent e)
	{jtf.setFocusable(true);name=jtf2.getText();ensure.setVisible(false);jtf2.setVisible(false);jtf.requestFocus();jb1.setVisible(true);
jb2.setVisible(true);jtf.setVisible(true);}
	});
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(jtf.getText()!=""){
					jl.setText(jl.getText()+name+":"+jtf.getText()+"\n");jtf.requestFocus();
					pw.println(name+":"+jtf.getText());pw.flush();
				 jtf.setText("        ");
				
				
				}
				
			}
		});
		jtf.addKeyListener(this);
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
 
 public void detect()
 {
	 try{
		 ia=InetAddress.getByName(ip);
		ss=new ServerSocket(3000);
		while(true)
		{
			so=ss.accept();System.out.println(""+so.getInetAddress());
			isclose=false;
			servet si=new servet();
			si.gogogo();
			si.setVisible(true);
		    si.inin();
		}	
	  }
		catch(IOException e){}
 }
	public static void main(String[] args) 
	{
		
		new servet().detect();
		
		

	}

}
