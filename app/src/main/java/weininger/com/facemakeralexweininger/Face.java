/*
 *  Face.java - class contains methods to draw the face
 *
 *  @author: Alex Weininger
 */


package weininger.com.facemakeralexweininger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class Face extends SurfaceView {

	int skinColor;
	int eyeColor;
	int hairColor;
	int hairStyle;

	int color;

	int selectedItem;

	public Face(Context context) {
		super(context);
		setWillNotDraw(false);
		randomize();
	}

	public Face(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		randomize();
	}

	protected void randomize() {
		Random random = new Random();

		this.hairStyle = random.nextInt(3);

		this.skinColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
		this.eyeColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
		this.hairColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
		invalidate();
		Log.d("user", "face randomized");
	}


	public void onDraw(Canvas canvas) {
		canvas.drawColor(-1);

		drawHairStyle(canvas);
		drawSkin(canvas);
		drawEyes(canvas);
	}

	protected void drawHairStyle(Canvas canvas) {
		int x = canvas.getWidth();
		int y = canvas.getHeight();
		int width = y / 4;

		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(this.hairColor);

		if (this.hairStyle == 1) {
			canvas.drawCircle(x / 2, y / 2 - width / 3, y / 4, paint);
		} else if (this.hairStyle == 2) {
			canvas.drawRect(x / 2 - 100, y / 2 - width - 40, x / 2 - 80, y / 2 + y / 4, paint);
			canvas.drawRect(x / 2, y / 2 - width - 50, x / 2 + 20, y / 2 + y / 4, paint);
			canvas.drawRect(x / 2 + 100, y / 2 - width - 40, x / 2 + 120, y / 2 + y / 4, paint);
			canvas.drawRect(x / 2 - 140, y / 2 - width - 40, x / 2 - 120, y / 2 + y / 4, paint);
			canvas.drawRect(x / 2 + 140, y / 2 - width - 40, x / 2 + 160, y / 2 + y / 4, paint);

			canvas.drawRect(x / 2 - width, y / 2 - width, x / 2 + width, y / 2 + y / 4, paint);
		} else if (this.hairStyle == 3) {
			canvas.drawCircle(x / 2 + 200, y / 2 - width / 3, y / 6, paint);
			canvas.drawCircle(x / 2, y / 2 - width / 3, y / 6, paint);
			canvas.drawCircle(x / 2 - 200, y / 2 - width / 3, y / 6, paint);
		}
	}

	protected void drawSkin(Canvas canvas) {
		int x = canvas.getWidth();
		int y = canvas.getHeight();
		int radius = y / 3;

		Paint skinPaint = new Paint();
		skinPaint.setColor(this.skinColor);
		skinPaint.setStyle(Paint.Style.FILL);

		canvas.drawCircle(x / 2, (y * 2) / 3, radius, skinPaint);
	}

	protected void drawEyes(Canvas canvas) {
		int x = canvas.getWidth();
		int y = canvas.getHeight();
		int radius = 70;

		Paint eyePaint = new Paint();
		eyePaint.setColor(this.eyeColor);
		eyePaint.setStyle(Paint.Style.FILL);

		canvas.drawCircle(x / 2 - radius * 2, y / 2, radius, eyePaint);
		canvas.drawCircle(x / 2 + radius * 2, y / 2, radius, eyePaint);

	}


	protected void setHairStyle(String hairStyleString) {
		switch (hairStyleString) {
			case "Style 1":
				this.hairStyle = 1;

				break;
			case "Style 2":
				this.hairStyle = 2;
				break;
			case "Style 3":
				this.hairStyle = 3;
				break;
			default:
				this.hairStyle = 0;
				Log.d("user", "did not find hairStyleString in switch statement - Face.setHairStyle: STRING: " + hairStyleString);
		}
		Log.d("user", "style set to: " + this.hairStyle);
		invalidate();
	}

	public void setSelectedItem(int item) {
		this.selectedItem = item;
	}

	public int getSelectedItemColor() {
		if (this.selectedItem == 0) {
			return this.hairColor;
		} else if (this.selectedItem == 1) {
			return this.eyeColor;
		} else if (this.selectedItem == 2) {
			return this.skinColor;
		} else {
			return 0;
		}
	}

	public void setItemColor(int color) {
		if (this.selectedItem == 0) {
			this.hairColor = color;
		} else if (this.selectedItem == 1) {
			this.eyeColor = color;
		} else if (this.selectedItem == 2) {
			this.skinColor = color;
		}
	}


}
