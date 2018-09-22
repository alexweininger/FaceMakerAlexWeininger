package weininger.com.facemakeralexweininger;

import android.app.Activity;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
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

	SeekBar seekBar_r, seekBar_g, seekBar_b;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas = new Canvas();
        face = (Face) findViewById(R.id.face);

        seekBar_r = (SeekBar) findViewById(R.id.seekBar_r);
        seekBar_g = (SeekBar) findViewById(R.id.seekBar_g);
        seekBar_b = (SeekBar) findViewById(R.id.seekBar_b);

        Button randomButton = (Button) findViewById(R.id.button_random);

        SeekbarHandler seekbarHandler = new SeekbarHandler(face);

        final RadioGroup radioButtonGroup = (RadioGroup) findViewById(R.id.radioGroup_itemSelector);
        final RadioButton radioButtonHair = (RadioButton) findViewById(R.id.radioButton_hair);
        final RadioButton radioButtonEyes = (RadioButton) findViewById(R.id.radioButton_eyes);
        final RadioButton radioButtonSkin = (RadioButton) findViewById(R.id.radioButton_skin);

        radioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d("user", "radio group onCheckChange listener value: " + i);

                int r, g, b;
                int color = -1;

	            if(i == radioButtonHair.getId()) {
					color = face.hairColor;

	            } else if (i == radioButtonEyes.getId()) {
					color = face.eyeColor;
	            } else if (i == radioButtonSkin.getId()) {
					color = face.skinColor;
	            }

				if(color != -1) {
					r = red(color);
					g = green(color);
					b = blue(color);

					seekBar_r.setProgress(r);
					seekBar_g.setProgress(g);
					seekBar_b.setProgress(b);
				}

            }
        });

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("user", "random button click listener");
                face.randomize();
            }
        });

        final Spinner styleSpinner = (Spinner) findViewById(R.id.spinner_hairSelector);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.styles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        styleSpinner.setAdapter(adapter);

        styleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("user" , "spinner selected item: " + adapterView.getSelectedItem());
                face.setHairStyle(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
