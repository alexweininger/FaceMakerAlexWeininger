package weininger.com.facemakeralexweininger;

import android.widget.SeekBar;

public class SeekbarHandler implements SeekBar.OnSeekBarChangeListener {

	protected Face face;
	public SeekbarHandler (Face face) {
		this.face = face;
	}

	public void	onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
		// use progress to set the size of the circle

	}


	public void	onStartTrackingTouch(SeekBar seekBar){

	}


	public void	onStopTrackingTouch(SeekBar seekBar){

	}
}
