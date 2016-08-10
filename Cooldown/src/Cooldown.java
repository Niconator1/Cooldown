import org.bukkit.entity.Player;

public class Cooldown {
	private int ticks;
	private Player p;
	public Cooldown(Player p,int ticks){
		this.p=p;
		this.ticks=ticks;
	}
	public Player getPlayer(){
		return this.p;
	}
	public int getRemainingTicks(){
		return this.ticks;
	}
	public void setRemainingTicks(int i) {
		this.ticks=i;
	}
}
