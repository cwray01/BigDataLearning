import javax.sound.midi.*;
public class MusicTest1 {
	public void play() {
		try{
		Sequencer sequencer = MidiSystem.getSequencer();
		System.out.println("We got a sequencer");
	} catch (MidiUnavailableException ex) {
		System.out.println("Bummer");
		}
	} //end play
	
	public static void main(String [] args){
		MusicTest1 mt = new MusicTest1();
		mt.play();
	}// end main
} // end class Music Test1
