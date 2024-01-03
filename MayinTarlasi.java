import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.JFrame;

public class MayinTarlasi implements MouseListener{
	JFrame frame;
	Btn[][] board =new Btn[10][10];
	
	public MayinTarlasi() {
		frame=new JFrame("Mayin Tarlasi");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10,10));
        //Image img=new ImageIcon(this.getClass().getResource("/mine.png")).getImage();
        
        
        for(int row=0; row<board.length; row++) {
        	for(int col=0;col<board[0].length;col++) {
        		Btn b=new Btn(row,col);
        		frame.add(b);
        		b.addMouseListener(this);
        		board[row][col]=b;
        	}
        }
        
        generateMine();
        updateCount();
        print();   
 
		frame.setVisible(true);
		
	}
	
	public void generateMine() {
		int i=0;
		while(i<10) {
			int randRow=(int)(Math.random()*board.length);
			int randCol=(int)(Math.random()*board[0].length);
			System.out.println("ilk while döngüsü col ve row üretildi");
			
			while(board[randRow][randCol].isMine()) {
				randRow=(int)(Math.random()*board.length);
				randCol=(int)(Math.random()*board[0].length);
				System.out.println("ikinci while döngüsüne girdi.");
			}
			board[randRow][randCol].setMine(true);
			System.out.println(i+"üretilen col ve rowa mayın yerleştirildi");
			i++;
			}
	}

	public void print() {
		for(int row=0; row<board.length; row++) {
        	for(int col=0;col<board[0].length;col++) {
        	   		if(board[row][col].isMine()) {
        			    //System.out.println(row+ " satırı ve " + col +" kolonuna mayn yerleştirldi");
        		        board[row][col].setIcon(new ImageIcon("mine.png"));	
        		    }else {
        		    	board[row][col].setText(board[row][col].getCount()+"");;
        		    }
        		}
        		     
        }
	}
	
	public void updateCount() {
		for(int row=0; row<board.length; row++) {
        	for(int col=0;col<board[0].length;col++) {
        	   		if(board[row][col].isMine()) {
        	   			counting(row,col);
        	   		}
        		}
       		}
	}
	public void counting(int row,int col) {
		for(int i=row-1; i<=row+1;i++) {
			for(int k=col-1; k<=col+1; k++) {
				try {
				  int value=board[i][k].getCount();
				  board[i][k].setCount(++value);
				}catch(Exception e) {
					
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Btn b=(Btn)e.getComponent();
		if(e.getButton()==1) {
			System.out.println("sol tıklandı");
			if(b.isMine()) {
				JOptionPane.showMessageDialog(frame,"Mayına Bastınız Oyun bitti !");
			}
		}else if(e.getButton()==3) {
			System.out.println("sağ tıklandı");
			if(!b.isFlag()) {
                b.setIcon(new ImageIcon("flag.png"));
                b.setFlag(true);
			}else {
				b.setIcon(null);
                b.setFlag(false);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
