package RytmMaster_vol2;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/Note.png")).getImage();
	private int x, y = (580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED)*Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	public boolean isProceeded() {
		return proceeded;
	}
	public void close() {
		proceeded = false;
	}
	public Note(String noteType) {
		if(noteType.equals("A")) {
			x = 228;
		}
		else if(noteType.equals("S")) {
			x = 332;
		}
		else if(noteType.equals("D")) {
			x = 436;
		}
		else if(noteType.equals("F")) {
			x = 540;
		}
		else if(noteType.equals("H")) {
			x = 644;
		}
		else if(noteType.equals("J")) {
			x = 744;
		}
		else if(noteType.equals("K")) {
			x = 848;
		}
		else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		if(noteType.equals("Space"))
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		else
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y>620) {
			System.out.println("Miss");
			Game.combo = 0;
			if(Game.life - 5 <= 0)
			{
				//System.out.println("GAME OVER");
				DynamicBeat.isGameOver = true;
				Main.NOTE_SPEED = 0;
				//System.exit(0);
			}
			else 
			{
				Game.life -= 5;
			}
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt();
					break;
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void judge() {
		if(y>=613) {
			System.out.println("Late");
			Game.combo++;
			Game.score += 50 + Game.comboBonus;
			if(Game.life + Game.lifeBonus <= 100)
				Game.life += Game.lifeBonus;
			close();
		}
		else if(y>=600) {
			System.out.println("Good");
			Game.combo++;
			Game.score += 65 + Game.comboBonus;
			if(Game.life + 1 + Game.lifeBonus <= 100)
				Game.life += 1 + Game.lifeBonus;
			close();
		}
		else if(y>=587) {
			System.out.println("Great");
			Game.combo++;
			Game.score += 80 + Game.comboBonus;
			if(Game.life + 2 + Game.lifeBonus <= 100)
				Game.life += 2 + Game.lifeBonus;
			close();
		}
		else if(y>=573) {
			System.out.println("Perfect");
			Game.combo++;
			Game.score += 100 + Game.comboBonus;
			if(Game.life + 3 + Game.lifeBonus <= 100)
				Game.life += 3 + Game.lifeBonus;
			close();
		}
		else if(y>=565) {
			System.out.println("Great");
			Game.combo++;
			Game.score += 80 + Game.comboBonus;
			if(Game.life + 2 + Game.lifeBonus <= 100)
				Game.life += 2 + Game.lifeBonus;
			close();
		}
		else if(y>=550) {
			System.out.println("Good");
			Game.combo++;
			Game.score += 65 + Game.comboBonus;
			if(Game.life + 1 + Game.lifeBonus <= 100)
				Game.life += 1 + Game.lifeBonus;
			close();
		}
	}
}
