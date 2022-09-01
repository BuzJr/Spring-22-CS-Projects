package casino;

/**
 * A stack of more than one deck used in a casino game
 *
 */
public class DeckStack {
	
	private Deck[] decks;
	
	public DeckStack(int numDecks) {
		decks = new Deck[numDecks];
		for(int i = 0; i<decks.length;i++) {
			decks[i] = new Deck();
		}
	}
	
	public Card dealTopCard() {
		for(int i=0;i<decks.length;i++) {
			if(decks[i].cardsLeft()>0) {
				return decks[i].dealTopCard();
			}
			else{
				for(int j=0;j<decks.length;j++) {
					decks[j].restockDeck();
				}
			}
		}
		return null;
		
	}
	
	protected void restoreDecks() {
		for(int i=0;i<decks.length;i++) {
			decks[i].shuffle();
		}
	}
	
	public int cardsLeft() {
		int total = 0;
		for(int i=0;i<decks.length;i++) {
			total += decks[i].cardsLeft();
		}
		return total;
	}
	
	
}
