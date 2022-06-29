package gameplay;
import java.util.Random;
import java.util.Scanner;
public class Gameplay {

	public void gameStart() {
		Scanner sc = new Scanner(System.in);
		Random randomNumber = new Random();
		PlayerStatus player1 = new PlayerStatus();
		PlayerStatus player2 = new PlayerStatus();
		PlayerStatus.setGameName("New Kombat");
		System.out.println("\t\t\t\tGame Name: " + PlayerStatus.getGameName());
		
		String[] arr = {"knife", "kalashnikov", "sniper"};
		int selectedWeapon;
		int counter = 1;
		String response;
		
//Initiate players		
		player1.initPlayer("Sub-Zero", 5, 0);
		player1.setScore(0);
		player1.setHealth(100);
		player1.setPositionX(randomNumber.nextDouble());
		player1.setPositionY(randomNumber.nextDouble());
		
		player2.initPlayer("Scorpion", 5, 0);
		player2.setScore(0);
		player2.setHealth(100);
		player2.setPositionX(randomNumber.nextDouble());
		player2.setPositionY(randomNumber.nextDouble());
		
		while (player1.getLives() * player2.getLives() > 0) {
			System.out.println("\n\t\t\t\t---------------");
			System.out.println("\t\t\t\t|   Round " + counter + "   |");
			System.out.println("\t\t\t\t---------------\n");
			player1.findArtifact(randomNumber.nextInt(8000));
			player2.findArtifact(randomNumber.nextInt(8000));
			player1.showInfo(player2);
			
//Player 1 chooses weapon			
			if (player1.getScore() >= 20000 && !player1.getWeaponInHand().equals("kalashnikov")) {
				System.out.println("Upgrade to kalashnikov? (y/n) ");
				response = sc.next();
				if (response.equals("y")) {
					player1.setWeaponInHand("kalashnikov");
				}
			} else if (player1.getScore() >= 10000 && !player1.getWeaponInHand().equals("sniper") && !player1.getWeaponInHand().equals("kalashnikov")) {
				System.out.println("Upgrade to sniper? (y/n) ");
				response = sc.next();
				if (response.equals("y")) {
					player1.setWeaponInHand("sniper");
				}
			} else if (player1.getScore() >= 1000 && !player1.getWeaponInHand().equals("knife") && 
						!player1.getWeaponInHand().equals("sniper") && !player1.getWeaponInHand().equals("kalashnikov")) {
				System.out.println("Upgrade to knife? (y/n) ");
				response = sc.next();
				if (response.equals("y")) {
					player1.setWeaponInHand("knife");
				}
			}
			
//Player 2 chooses weapon			
			if (player2.getScore() >= 1000) {
				selectedWeapon = randomNumber.nextInt(arr.length);
				System.out.println("\n" + player2.getNickname() + " chooses " + arr[selectedWeapon]);
				player2.setWeaponInHand(arr[selectedWeapon]);
			} else {
				System.out.println (player2.getNickname() + " doesn't have enough points to buy a weapon\n");
			}
			
//Fight			
			System.out.println("\nShould I attack? " + player1.shouldAttackOpponent(player2));
			player1.attackOpponent(player2);

//Players change places			
			player1.movePlayerTo(randomNumber.nextDouble(), randomNumber.nextDouble());
			player2.movePlayerTo(randomNumber.nextDouble(), randomNumber.nextDouble());

//End of fight - show player status			
			player1.showInfo(player2);
			counter++;
		}
		System.out.println("\n\t\t\t\t------------------");
		
//End of loop - print the winner
		if (player1.getLives() <= 0) {
			System.out.println("\t\t\t\t|    GAME OVER   |\n\t\t\t\t| " + player2.getNickname() + " WINNS |");
		} else if (player2.getLives() <= 0) {
			System.out.println("\t\t\t\t|    GAME OVER   |\n\t\t\t\t| " + player1.getNickname() + " WINNS |");
		}
		System.out.println("\t\t\t\t------------------");
		
		sc.close();
	}
}
