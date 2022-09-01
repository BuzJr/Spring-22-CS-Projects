package agents;

import casino.BlackjackPlayer;
import casino.Card;
import casino.Move;

public class MyBlackjackPlayer extends BlackjackPlayer{

	private int previousChips = 0;
	private int cardcount = 0;
	private int bet = 0;
	
	@Override
	public int getBet() {
		if(this.getChips()>this.previousChips) {//If you win the previous round, increase bet
			if(this.getChips()>0) {
				bet += 5; //Increases the bet more generously if chips in the positive
				return bet;
			}
			else {
				bet += 1; //In general this is far more conservative than the last strategy
				return bet;
			}
		}
		else if(this.getChips()<this.previousChips)//If last round was lost
			if(this.getChips()>0 && bet>=1) { //If positive, gradually decrease bet to minimum of 1
				bet -=1;
				return bet;
			}
			else {
				bet = 1;
				return bet;
			}
		/*This betting strategy is incredibly more conservative then the other one
		 * in order to minimize potential chip loss on successive loses,
		 * this results in a chip total closer to the original starting point!
		 */
		return 1; //default return 1
	}

	@Override
	public Move getMove() {
		if(this.cards.get(0).getRank()==11){ //Checks if the first card is an Ace
			if(this.handScore()>=19)
				return Move.STAY;
			if(this.handScore()==18) {
				if(dealer.getVisibleCard().getRank()>=3 && dealer.getVisibleCard().getRank()<=6)
					return Move.DOUBLE;
				else if(dealer.getVisibleCard().getRank()==2||dealer.getVisibleCard().getRank()==7||dealer.getVisibleCard().getRank()==8)
					return Move.STAY;
				else
					return Move.HIT;
			}
			if(this.handScore()<=17) {
				if(dealer.getVisibleCard().getRank()==5||dealer.getVisibleCard().getRank()==6)
					return Move.DOUBLE;
				else
					return Move.HIT;
			}
		}
		if(this.handScore()<=8)
			return Move.HIT;
		if(this.handScore()<12) {
			if(dealer.getVisibleCard().getRank()>=3 && dealer.getVisibleCard().getRank()<=6)
				return Move.DOUBLE;
			else
				return Move.HIT;
		}
		if (this.handScore()>=12 && this.handScore()<=16){
			if(dealer.getVisibleCard().getRank()>=2 && dealer.getVisibleCard().getRank()<=6)
				return Move.STAY;
			else
				return Move.HIT;
		}
		return Move.STAY;//defaults to staying
	}

	@Override
	public void handOver(Card[] dealerHand) {
		//Since this method gets called at the end of every round I'm just using it to update the previous chip count and reset the card count
		cardcount = 0;
		previousChips = this.getChips();
		/**
		 * If you care about looking at the dealer's hand once
		 * the hand is over, then you can do it here. This method
		 * is called automatically after every hand and a copy of the dealer's
		 * final hand is given to you to process.
		 */
		
	}

	
}
