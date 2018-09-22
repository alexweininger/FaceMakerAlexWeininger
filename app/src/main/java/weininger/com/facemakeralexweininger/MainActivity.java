/*
 *  MainActivity.java - main class that handles logic of app
 *
 *  @author: Alex Weininger
 */


package weininger.com.facemakeralexweininger;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

	Face face;
	Canvas canvas;
	Spinner styleSpinner;
	SeekBar seekBar_r, seekBar_g, seekBar_b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		canvas = new Canvas();
		face = findViewById(R.id.face);

		seekBar_r = findViewById(R.id.seekBar_r);
		seekBar_g = findViewById(R.id.seekBar_g);
		seekBar_b = findViewById(R.id.seekBar_b);

		seekBar_r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

				int color = face.getSelectedItemColor();
				int blue = blue(color);
				int green = green(color);

				color = Color.argb(255, i, green, blue);

				if (b) {
					face.setItemColor(color);
					face.invalidate();
				}

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		seekBar_g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				int color = face.getSelectedItemColor();
				int blue = blue(color);
				int red = red(color);

				color = Color.argb(255, red, i, blue);

				if (b) {
					face.setItemColor(color);
					face.invalidate();
				}

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		seekBar_b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				int color = face.getSelectedItemColor();
				int red = red(color);
				int green = green(color);

				color = Color.argb(255, red, green, i);

				if (b) {
					face.setItemColor(color);
					face.invalidate();
				}


			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		Button randomButton = findViewById(R.id.button_random);

		final RadioGroup radioButtonGroup = findViewById(R.id.radioGroup_itemSelector);
		final RadioButton radioButtonHair = findViewById(R.id.radioButton_hair);
		final RadioButton radioButtonEyes = findViewById(R.id.radioButton_eyes);
		final RadioButton radioButtonSkin = findViewById(R.id.radioButton_skin);

		radioButtonGroup.check(R.id.radioButton_hair);
		face.setSelectedItem(0);

		radioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				Log.d("user", "radio group onCheckChange listener value: " + i);

				if (i == radioButtonHair.getId()) {
					face.setSelectedItem(0);

				} else if (i == radioButtonEyes.getId()) {
					face.setSelectedItem(1);
				} else if (i == radioButtonSkin.getId()) {
					face.setSelectedItem(2);
				}

				setSeekBarValues(face.getSelectedItemColor());

			}
		});

		randomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("user", "random button click listener");
				face.randomize();
				setSeekBarValues(face.getSelectedItemColor());
				styleSpinner.setSelection(face.hairStyle);
			}
		});

		styleSpinner = findViewById(R.id.spinner_hairSelector);
		final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.styles, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		styleSpinner.setAdapter(adapter);

		styleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				Log.d("user", "spinner selected item: " + adapterView.getSelectedItem());
				face.setHairStyle(adapterView.getSelectedItem().toString());

			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

	}

	protected void setSeekBarValues(int color) {
		int r = red(color);
		int g = green(color);
		int b = blue(color);

		seekBar_r.setProgress(r);
		seekBar_g.setProgress(g);
		seekBar_b.setProgress(b);
	}
}
