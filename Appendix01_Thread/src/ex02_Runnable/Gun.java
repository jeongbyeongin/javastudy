package ex02_Runnable;

public class Gun {

	private int bullet;
	
	public Gun(int bullet) {
		this.bullet = bullet;
	}
	
	public void shoot() {
		if(bullet == 0) {
			throw new RuntimeException("총알이 없습니다.");	// RuntimeException은 코드생략 가능 <- UncheckdeException에 대표주자
		}
		bullet--;
		System.out.println("빵빠라방방!!" + bullet + "발 남았습니다.");
	}

	public int getBullet() {
		return bullet;
	}

	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	
}
