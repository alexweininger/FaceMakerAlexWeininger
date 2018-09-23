/*
 *  MainActivity.java - main class that handles logic of app
 *
 *  @author: Alex Weininger
 */


package weininger.com.facemakeralexweininger;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

public class MainActivity extends Activity {

	private FaceView faceView;
	private Spinner styleSpinner; // Spinner for selecting hair style
	private SeekBar seekBar_r, seekBar_g, seekBar_b; // SeekBars for the r, g, and b components

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// set the face to the face view
		faceView = findViewById(R.id.face);

		Button randomButton = findViewById(R.id.button_random); // random button

		randomButton.setOnClickListener(new View.OnClickListener() { // random button listener
			@Override
			public void onClick(View view) {
				faceView.randomize();
				setSeekBarValues(faceView.getSelectedItemColor()); // update SeekBars
				styleSpinner.setSelection(faceView.getHairStyle(), true); // update Spinner
				faceView.invalidate();
			}
		});

		final RadioGroup radioButtonGroup = findViewById(R.id.radioGroup_itemSelector);
		final RadioButton radioButtonHair = findViewById(R.id.radioButton_hair);
		final RadioButton radioButtonEyes = findViewById(R.id.radioButton_eyes);
		final RadioButton radioButtonSkin = findViewById(R.id.radioButton_skin);

		radioButtonGroup.check(R.id.radioButton_hair); // set to Hair on startup

		radioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				if (i == radioButtonHair.getId()) {
					faceView.setSelectedItem(0);
				} else if (i == radioButtonEyes.getId()) {
					faceView.setSelectedItem(1);
				} else if (i == radioButtonSkin.getId()) {
					faceView.setSelectedItem(2);
				}

				setSeekBarValues(faceView.getSelectedItemColor()); // update SeekBars
			}
		});

		// setting the Spinner
		styleSpinner = findViewById(R.id.spinner_hairSelector);

		// creating and setting the adapter and filling the spinner with the values from a string array
		final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.styles, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		styleSpinner.setAdapter(adapter);

		// Spinner listener, handles changing the hairStyle of the face
		styleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				faceView.setHairStyle(adapterView.getSelectedItem().toString()); // set hairStyle to selected hairStyle from spinner
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {}
		});

		// defining the SeekBars
		seekBar_r = findViewById(R.id.seekBar_r);
		seekBar_g = findViewById(R.id.seekBar_g);
		seekBar_b = findViewById(R.id.seekBar_b);

		// SeekBar listeners for each SeekBar component
		seekBar_r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

				int color = faceView.getSelectedItemColor();
				int blue = blue(color);
				int green = green(color);

				color = Color.argb(255, i, green, blue);

				if (b) {
					faceView.setItemColor(color);
					faceView.invalidate();
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
		});

		// SeekBar for g
		seekBar_g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				int color = faceView.getSelectedItemColor();
				int blue = blue(color);
				int red = red(color);

				color = Color.argb(255, red, i, blue);

				if (b) {
					faceView.setItemColor(color);
					faceView.invalidate();
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
		});

		// SeekBar for b
		seekBar_b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				int color = faceView.getSelectedItemColor();
				int red = red(color);
				int green = green(color);

				color = Color.argb(255, red, green, i);

				if (b) {
					faceView.setItemColor(color);
					faceView.invalidate();
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
		});
	}

	private void setSeekBarValues(int color) { // sets SeekBar progress(s) to a given color

		// set SeekBar progress to (r, g, b) color component of the color
		seekBar_r.setProgress(red(color));
		seekBar_g.setProgress(green(color));
		seekBar_b.setProgress(blue(color));
	}
}