package RytmMaster_vol2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png"))
			.getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png"))
			.getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png"))
			.getImage();
	private Image noteRouteAImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteHImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image blueFlareImage;
	private Image judgementImage;
	
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	public static int combo = 0; //dd
	public static int score = 0; //dd
	public static int comboBonus = combo / 2;
	public static int life = 100;
	public static int lifeBonus = combo / 10;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	
	public void screenDraw(Graphics2D g) {
		if(DynamicBeat.isGameOver)
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Elephant", Font.BOLD, 80));
			g.drawString("GAME OVER", 340, 350);
		}
		else {
			g.drawImage(noteRouteAImage, 228, 30, null);
			g.drawImage(noteRouteSImage, 332, 30, null);
			g.drawImage(noteRouteDImage, 436, 30, null);
			g.drawImage(noteRouteFImage, 540, 30, null);
			g.drawImage(noteRouteHImage, 640, 30, null);
			g.drawImage(noteRouteJImage, 744, 30, null);
			g.drawImage(noteRouteKImage, 848, 30, null);
			g.drawImage(noteRouteLImage, 952, 30, null);
			g.drawImage(noteRouteLineImage, 224, 30, null);
			g.drawImage(noteRouteLineImage, 328, 30, null);
			g.drawImage(noteRouteLineImage, 432, 30, null);
			g.drawImage(noteRouteLineImage, 536, 30, null);
			g.drawImage(noteRouteLineImage, 640, 30, null);
			g.drawImage(noteRouteLineImage, 740, 30, null);
			g.drawImage(noteRouteLineImage, 844, 30, null);
			g.drawImage(noteRouteLineImage, 948, 30, null);
			g.drawImage(noteRouteLineImage, 1052, 30, null);
			g.drawImage(gameInfoImage, 0, 660, null);
			g.drawImage(judgementLineImage, 0, 580, null);
			for(int i = 0; i < noteList.size(); i++)
			{
				Note note = noteList.get(i);
				if(!note.isProceeded()) {
					noteList.remove(i);
					i--;
				}
				else {
					note.screenDraw(g);
				}
				note.screenDraw(g);
			}
			g.setColor(Color.white);
			g.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, 
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString(titleName, 20, 702);
			g.drawString(difficulty, 1190, 702);
			g.setFont(new Font("Arial", Font.PLAIN, 26));
			g.setColor(Color.RED);
			g.drawString("A", 270, 609);
			g.drawString("S", 374, 609);
			g.drawString("D", 478, 609);
			g.drawString("F", 580, 609);
			g.drawString("H", 687, 609);
			g.drawString("J", 784, 609);
			g.drawString("K", 889, 609);
			g.drawString("L", 993, 609);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Elephant", Font.BOLD, 30));
			g.drawString("SCORE : "+Game.score, 500,700);
			
			if(Game.combo != 0)
			{
				g.setColor(Color.RED);
				g.setFont(new Font("Elephant", Font.BOLD, 50));
				g.drawString(""+Game.combo+ "Combos", 600, 150);// 콤보 출력
				//g.drawImage(blueFlareImage, 320, 370, null);
				
			}
		}
		g.setColor(Color.GREEN);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("Life : "+ Game.life, 800, 700);// 라이프게이지 출력
	}
	/*public void screenGameOver(Graphics2D g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Elephant", Font.BOLD, 80));
		g.drawString("GAME OVER", 340, 350);
	}*/
	
	public void pressA() {
		judge("A");
		noteRouteAImage = new ImageIcon(Main.class.getResource("../images/red.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	public void releaseA() {
		noteRouteAImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/orange.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/yellow.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/green.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressH() {
		judge("H");
		noteRouteHImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseH() {
		noteRouteHImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/blue.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/purple.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/deepblue.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if(titleName.equals("Janji - Heroes Tonight") && difficulty.equals("Easy")) {
			int startTime = 1000- Main.REACH_TIME*1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime,"S"),
					new Beat(startTime+gap*2,"D"),
					new Beat(startTime+gap*4,"F"),
					new Beat(startTime+gap*6,"D"),
					new Beat(startTime+gap*8,"S"),
					new Beat(startTime+gap*12,"F"),
					new Beat(startTime+gap*16,"D"),
			};
		}
		else if(titleName.equals("Tubo - Life") && difficulty.equals("Easy")) {
			int startTime = 1000- Main.REACH_TIME*1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime,"A"),
					new Beat(startTime+gap*2,"A"),
					new Beat(startTime+gap*5,"A"),
					new Beat(startTime+gap*7,"S"),
					new Beat(startTime+gap*12,"S"),
					new Beat(startTime+gap*15,"S"),
					new Beat(startTime+gap*17,"S"),
					new Beat(startTime+gap*20,"S"),
					
					new Beat(startTime+gap*23,"D"),
					new Beat(startTime+gap*30,"D"),
					new Beat(startTime+gap*32,"D"),
					new Beat(startTime+gap*35,"D"),
					
					
					new Beat(startTime+gap*38,"F"),
					new Beat(startTime+gap*45,"F"),
					new Beat(startTime+gap*47,"F"),
					new Beat(startTime+gap*50,"F"),
					
					
					new Beat(startTime+gap*50,"H"),
					
					new Beat(startTime+gap*53,"H"),
					new Beat(startTime+gap*60,"H"),
					new Beat(startTime+gap*62,"H"),
					new Beat(startTime+gap*65,"H"),
					
					
					new Beat(startTime+gap*68,"J"),
					new Beat(startTime+gap*75,"J"),
					new Beat(startTime+gap*77,"J"),
					new Beat(startTime+gap*80,"J"),
					
					new Beat(startTime+gap*83,"K"),
					new Beat(startTime+gap*90,"K"),
					new Beat(startTime+gap*92,"K"),
					new Beat(startTime+gap*95,"K"),
					
					new Beat(startTime+gap*98,"L"),
					new Beat(startTime+gap*105,"L"),
					new Beat(startTime+gap*107,"L"),
					new Beat(startTime+gap*110,"L"),
					
					
					new Beat(startTime+gap*112,"L"),
					new Beat(startTime+gap*114,"J"),
					new Beat(startTime+gap*116,"D"),
					new Beat(startTime+gap*118,"A"),
					
					
					new Beat(startTime+gap*127,"A"),
					new Beat(startTime+gap*129,"S"),
					new Beat(startTime+gap*131,"D"),
					new Beat(startTime+gap*133,"A"),
					
					
					new Beat(startTime+gap*142,"L"),
					new Beat(startTime+gap*144,"J"),
					new Beat(startTime+gap*146,"D"),
					new Beat(startTime+gap*148,"A"),
					
					
					new Beat(startTime+gap*157,"A"),
					new Beat(startTime+gap*159,"S"),
					new Beat(startTime+gap*161,"D"),
					new Beat(startTime+gap*163,"A"),
					
					
					new Beat(startTime+gap*172,"L"),
					new Beat(startTime+gap*174,"J"),
					new Beat(startTime+gap*176,"D"),
					new Beat(startTime+gap*178,"A"),
					
					
					new Beat(startTime+gap*187,"A"),
					new Beat(startTime+gap*189,"S"),
					new Beat(startTime+gap*191,"D"),
					new Beat(startTime+gap*193,"A"),
					
					
					new Beat(startTime+gap*202,"L"),
					new Beat(startTime+gap*204,"J"),
					new Beat(startTime+gap*206,"D"),
					new Beat(startTime+gap*208,"A"),
					
					
					new Beat(startTime+gap*217,"A"),
					new Beat(startTime+gap*219,"S"),
					new Beat(startTime+gap*221,"D"),
					new Beat(startTime+gap*223,"L"),
					

					new Beat(startTime+gap*233,"A"),
					new Beat(startTime+gap*233,"D"),
					new Beat(startTime+gap*235,"A"),
					new Beat(startTime+gap*235,"D"),
		
					
					new Beat(startTime+gap*249,"S"),
					new Beat(startTime+gap*249,"F"),
					new Beat(startTime+gap*251,"S"),
					new Beat(startTime+gap*251,"F"),
					
					
					new Beat(startTime+gap*263,"H"),
					new Beat(startTime+gap*263,"K"),
					new Beat(startTime+gap*265,"H"),
					new Beat(startTime+gap*265,"K"),
					
					new Beat(startTime+gap*277,"J"),
					new Beat(startTime+gap*277,"L"),
					new Beat(startTime+gap*279,"J"),
					new Beat(startTime+gap*279,"L"),
					
					
					new Beat(startTime+gap*291,"H"),
					new Beat(startTime+gap*291,"K"),
					new Beat(startTime+gap*293,"H"),
					new Beat(startTime+gap*293,"K"),
					
					new Beat(startTime+gap*304,"S"),
					new Beat(startTime+gap*304,"F"),
					new Beat(startTime+gap*306,"S"),
					new Beat(startTime+gap*306,"F"),
					
					new Beat(startTime+gap*318,"A"),
					new Beat(startTime+gap*318,"D"),
					new Beat(startTime+gap*320,"A"),
					new Beat(startTime+gap*320,"D"),
					
			};
		}
		int i=0;
		gameMusic.start();
		while(i<beats.length&&!isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime()<=gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void judge(String input) {
		for(int i=0;i<noteList.size();i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				note.judge();
				break;
			}
		}
	}
	public void judgeEvent(String judge) {
		if(!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.jpg")).getImage();
		}
	}
}
