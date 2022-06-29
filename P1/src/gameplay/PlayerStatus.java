package gameplay;
public class PlayerStatus {

	private String nickname;
	private int score;
	private int lives;
	private int health;
	private String weaponInHand = "no weapon";
	private double positionX;
	private double positionY;
	private static String gameName;
	private double probability;
	private double distance;
	
	public void initPlayer(String nickname) {
		this.nickname = nickname;
	}
	public void initPlayer(String nickname, int lives) {
		this.nickname = nickname;
		this.lives = lives;
	}
	public void initPlayer(String nickname, int lives, int score) {
		this.nickname = nickname;
		this.lives = lives;
		this.score = score;
	}
	
//score	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
//positionX	
	public double getPositionX() {
		return positionX;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

//positionY	
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
//gameName	
	public static String getGameName() {
		return gameName;
	}
	protected static void setGameName(String gameName) {
		PlayerStatus.gameName = gameName;
	}

//nickname	
	public String getNickname() {
		return this.nickname;
	}

//health	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
//lives	
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}

//Player status	
	public void showInfo(PlayerStatus opponent) {
		System.out.print("\n\t-----------------------------------------------------------------------------------");
		System.out.println("\n\t" + nickname + ":\tlives - " + lives + " \thealth - " + health + "\tscore - " + score + "\tweapon - " + weaponInHand);
		System.out.println("\t\tOX: " + positionX + "\tOY: " + positionY);
		System.out.println("\t-----------------------------------------------------------------------------------");
		System.out.println("\t" + opponent.nickname + ":\tlives - " + opponent.lives + " \thealth - " + opponent.health + "\tscore - " + 
					opponent.score + "\tweapon - " + opponent.weaponInHand);
		System.out.print("\t\tOX: " + opponent.positionX + "\tOY: " + opponent.positionY + "\n");
		System.out.println("\t-----------------------------------------------------------------------------------\n");
	}

	private boolean isPerfectNumber(int artifactCode) {
		int sum = 0;
		for (int i = 2; i <= artifactCode/2; i++) {
			if (artifactCode % i == 0) {
				sum += i;
			}
		}	
		sum++;
		if (sum == artifactCode) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isPrime(int artifactCode) {
		for (int i = 2; i <= Math.sqrt(artifactCode); i++) {
			if (artifactCode % i == 0) {
				return false;
			}	
		}
		return true;
	}
	
	private boolean isSumDivisibleBy3 (int artifactCode) {
		int sum = 0;
		while (artifactCode != 0) {
			sum += artifactCode % 10;
			artifactCode /= 10;
		}
		if (sum % 3 == 0) {
			return true;
		}
		return false;
	}

//findArtifact	
	public void findArtifact(int artifactCode) {
		System.out.print(nickname + " found artifact code: " + artifactCode);
		if (isPerfectNumber(artifactCode)) {
			System.out.println("\t***** You found a perfect number *****");
			this.score += 5000;
			this.lives += 1;
			this.health = 100;
		} else if (isPrime(artifactCode)) {
			System.out.println("\t*** You found a prime number ***");
			this.score += 1000;
			this.lives += 2;
			this.health += 25;
			if (this.health > 100) {
				this.health = 100;
			}
		} else if ((artifactCode % 2 == 0) && (isSumDivisibleBy3(artifactCode))) {
			System.out.println("\t!!! This is a trap !!!");
			this.score -= 3000;
			this.health -= 25;
			if (health <= 0) {
				this.lives -= 1;
				this.health = 100;
			}
		} else {
			System.out.println("\t--- This is no magic number ---");
			this.score += artifactCode;
		}	
	}
	
//weapon	
	public String getWeaponInHand() {
		return weaponInHand;
	}
	
	public boolean setWeaponInHand (String weaponInHand) {
		if ((this.weaponInHand.equals(weaponInHand))) {
			System.out.println("You already have this weapon");
			return false;
		}
		if ((this.score >= 20000) && (!this.weaponInHand.equals("kalashnikov")) && (weaponInHand.equals("kalashnikov"))) {
			this.weaponInHand = "kalashnikov";
			this.score -= 20000;
			return true;
		} else if ((this.score < 20000) && (!this.weaponInHand.equals("kalashnikov")) && (weaponInHand.equals("kalashnikov"))) {
			System.out.println("You don't have enough points to buy this weapon");
			return false;
		} else if ((this.score >= 10000) && (!this.weaponInHand.equals("sniper")) && (weaponInHand.equals("sniper"))) {
			this.weaponInHand = "sniper";
			this.score -= 10000;
			return true;
		} else if ((this.score < 10000) && (!this.weaponInHand.equals("sniper")) && (weaponInHand.equals("sniper"))) {
			System.out.println("You don't have enough points to buy this weapon");
			return false;
		} else if ((this.score >= 1000) && (!this.weaponInHand.equals("knife")) && (weaponInHand.equals("knife"))) {
			this.weaponInHand = "knife";
			this.score -= 1000;
			return true;
		} else if ((this.score < 1000) && (!this.weaponInHand.equals("knife")) && (weaponInHand.equals("knife"))) {
			System.out.println("You don't have enough points to buy this weapon");
			return false;
		}
		return false;	
	}
	
	private double damageProbability(int health, int score) {
		probability = (3 * health + score / 1000) / 4;
		return probability;
	}
	
	private double distanceBetweenPlayers(double positionX, double positionY, PlayerStatus opponent) {
		distance = Math.sqrt((positionX - opponent.positionX) * (positionX - opponent.positionX) + (positionY - opponent.positionY) * (positionY - opponent.positionY));
		return distance;
	}
	
//shouldAttackOpponent
	public boolean shouldAttackOpponent(PlayerStatus opponent) {
		
		if (weaponInHand.equals(opponent.weaponInHand)) {
			if (damageProbability(health, score) > damageProbability(opponent.health, opponent.score)) {
				return true;
			} else if (damageProbability(health, score) < damageProbability(opponent.health, opponent.score)) {
				return false;
			} else if (damageProbability(health, score) == damageProbability(opponent.health, opponent.score)) {
				return false;
			}
		} else {
			if (distanceBetweenPlayers(positionX, positionY, opponent) > 1000) {
				if (weaponInHand.equals("sniper")) {
					return true;
				} else if (weaponInHand.equals("kalashnikov") && opponent.weaponInHand.equals("sniper")) {
					return false;
				} else if (weaponInHand.equals("kalashnikov") && opponent.weaponInHand.equals("knife")) {
					return true;
				} else if (weaponInHand.equals("kalashnikov") && opponent.weaponInHand.equals("no weapon")) {
					return true;
				} else if (weaponInHand.equals("knife") && opponent.weaponInHand.equals("sniper")) {
					return false;
				} else if (weaponInHand.equals("knife") && opponent.weaponInHand.equals("kalashnikov")) {
					return false;
				} else if (weaponInHand.equals("knife") && opponent.weaponInHand.equals("no weapon")) {
					return true;
				} else if (weaponInHand.equals("no weapon")) {
					return false;
				}
			} else if (distanceBetweenPlayers(positionX, positionY, opponent) <= 1000) {
				if (weaponInHand.equals("kalashnikov")) {
					return true;
				} else if (weaponInHand.equals("sniper") && opponent.weaponInHand.equals("kalashnikov")) {
					return false;
				} else if (weaponInHand.equals("sniper") && opponent.weaponInHand.equals("knife")) {
					return true;
				} else if (weaponInHand.equals("sniper") && opponent.weaponInHand.equals("no weapon")) {
					return true;
				} else if (weaponInHand.equals("knife") && opponent.weaponInHand.equals("sniper")) {
					return false;
				} else if (weaponInHand.equals("knife") && opponent.weaponInHand.equals("kalashnikov")) {
					return false;
				} else if (weaponInHand.equals("knife") && opponent.weaponInHand.equals("no weapon")) {
					return true;
				} else if (weaponInHand.equals("no weapon")) {
					return false;
				}
			}
		}
		return false;	
	}
	
		public void attackOpponent(PlayerStatus opponent) {
			
			if (this.shouldAttackOpponent(opponent)) {
				opponent.lives -= 1;
				System.out.println(nickname + " winns this round");
			} else {
				lives -= 1;
				System.out.println(opponent.nickname + " winns this round");
			}			
		}		
	}
	

