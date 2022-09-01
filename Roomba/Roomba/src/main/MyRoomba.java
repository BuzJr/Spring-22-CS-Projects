
package main;

import world.Move;
import world.Roomba;

public class MyRoomba extends Roomba{
	
	/*You can put variables here if it will be helpful*/
	/*Examples: Roomba is currently turning, Roomba is trying to get to some new direction, etc. */
	
	public MyRoomba(int x, int y, int radius) {
		super(x, y, radius);
	}

	@Override
	public Move makeMove() {
		double wallturn = Math.random();
		double randomturn = Math.random();
		if(this.frontBumper) { 
			if(wallturn>=0.5) {
				return Move.TURNCLOCKWISE;
			}
			else {
				return Move.TURNCOUNTERCLOCKWISE;
			}
		}
		if(this.wallSensor) {
			if(this.frontBumper && wallturn >=0.1) {
				return Move.TURNCLOCKWISE;
			}
			else if(randomturn<=0.05){
				return Move.TURNCOUNTERCLOCKWISE;
			}
			else
				return Move.FORWARD;
		}
		if((this.infraredSensor<=40 && randomturn<=0.025) || (!this.wallSensor && randomturn<=0.020)) {
			if(randomturn>=0.5) {
				return Move.TURNCOUNTERCLOCKWISE;
			}
			else
				return Move.TURNCLOCKWISE;
		}

		return Move.FORWARD;
	}
	
}