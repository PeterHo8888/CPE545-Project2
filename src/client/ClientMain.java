package client;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import server.services.BlackjackClientState;
import server.services.BlackjackService;

public class ClientMain {
    
    static final String IP = "192.168.0.171";
    
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);

			BlackjackService service = (BlackjackService) LocateRegistry.getRegistry(IP, 1099)
					.lookup(BlackjackService.SERVICE_NAME);

			// service.connect(client);
			BlackjackClientState state = service.new_game();

			while (!state.game_over) {
			    System.out.println("Your count: " + state.player_count);
				System.out.println("You: " + state.cards);
				System.out.println("Them: " + state.house_cards);

				boolean valid;
				do {
					valid = true;
					System.out.print("> ");
	
					String choice = scanner.nextLine();
					switch (choice.toLowerCase()) {
					case "stand":
						state = service.do_action(state.id, 0);
						break;
					case "hit":
						state = service.do_action(state.id, 1);
						break;
					default:
						valid = false;
						System.out.println("Invalid option");
						break;
					}
				} while (!valid);
			}
			
			scanner.close();
			System.out.println("Server msg: " + state.err_string);
            System.out.println("Your count: " + state.player_count);
            System.out.println("Their count: " + state.house_count);
			System.out.println("You: " + state.cards);
			System.out.println("Them: " + state.house_cards);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
