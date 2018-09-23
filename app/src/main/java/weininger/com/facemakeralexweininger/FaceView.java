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
import android.view.SurfaceView;
import java.util.Random;

public class FaceView extends SurfaceView {

	// colors for different parts of face
	private int skinColor;
	private int eyeColor;
	private int hairColor;

	private int hairStyle; // 1 - 3 hairStyle

	private int selectedItem; // value for the currently selected item (hair, eyes, skin)

	public FaceView(Context context) {
		super(context);
		setWillNotDraw(false);
		randomize();
		this.selectedItem = 0;
	}

	public FaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		randomize();
		this.selectedItem = 0;
	}

	protected void randomize() {
		Random random = new Random();

		this.hairStyle = random.nextInt(2) + 1;

		this.skinColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
		this.eyeColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
		this.hairColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));

		invalidate();
	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(-1);

		drawHairStyle(canvas);
		drawSkin(canvas);
		drawEyes(canvas);
	}

	protected void drawHairStyle(Canvas canvas) {

		int x = canvas.getWidth(); // getting the width and height of canvas
		int y = canvas.getHeight();
		int width = y / 4;

		Paint hairPaint = new Paint();
		hairPaint.setStyle(Paint.Style.FILL);
		hairPaint.setColor(this.hairColor);

		if (this.hairStyle == 1) {
			canvas.drawCircle(x / 2, y / 2 - width / 3, y / 4, hairPaint);
		} else if (this.hairStyle == 2) {

			// drawing rectangles for cool hair
			canvas.drawRect(x / 2 - 100, y / 2 - width - 40, x / 2 - 80, y / 2 + y / 4, hairPaint);
			canvas.drawRect(x / 2, y / 2 - width - 50, x / 2 + 20, y / 2 + y / 4, hairPaint);
			canvas.drawRect(x / 2 + 100, y / 2 - width - 40, x / 2 + 120, y / 2 + y / 4, hairPaint);
			canvas.drawRect(x / 2 - 140, y / 2 - width - 40, x / 2 - 120, y / 2 + y / 4, hairPaint);
			canvas.drawRect(x / 2 + 140, y / 2 - width - 40, x / 2 + 160, y / 2 + y / 4, hairPaint);

			canvas.drawRect(x / 2 - width, y / 2 - width, x / 2 + width, y / 2 + y / 4, hairPaint);

		} else if (this.hairStyle == 3) {

			canvas.drawCircle(x / 2 + 200, y / 2 - width / 3, y / 6, hairPaint);
			canvas.drawCircle(x / 2, y / 2 - width / 3, y / 6, hairPaint);
			canvas.drawCircle(x / 2 - 200, y / 2 - width / 3, y / 6, hairPaint);
		}
	}

	// method to draw the skin of the face
	protected void drawSkin(Canvas canvas) {
		int x = canvas.getWidth(); // getting the width and height of canvas
		int y = canvas.getHeight();
		int radius = y / 3;

		Paint skinPaint = new Paint(); // making Paint for the skin
		skinPaint.setColor(this.skinColor);
		skinPaint.setStyle(Paint.Style.FILL);

		canvas.drawCircle(x / 2, (y * 2) / 3, radius, skinPaint);
	}

	// method to draw two eyes on the face
	protected void drawEyes(Canvas canvas) {
		int x = canvas.getWidth(); // getting the width and height of canvas
		int y = canvas.getHeight();
		int radius = 70;

		Paint eyePaint = new Paint(); // making Paint for the eyes
		eyePaint.setColor(this.eyeColor);
		eyePaint.setStyle(Paint.Style.FILL);

		canvas.drawCircle(x / 2 - radius * 2, y / 2, radius, eyePaint);
		canvas.drawCircle(x / 2 + radius * 2, y / 2, radius, eyePaint);
	}

	// method that takes a String hairStyle and sets it to the appropriate hairStyle integer
	protected void setHairStyle(String hairStyleString) {
		switch (hairStyleString) {
			case "Afro Hair":
				this.hairStyle = 1;
				break;
			case "Blocky Hair":
				this.hairStyle = 2;
				break;
			case "Triple Circle Hair":
				this.hairStyle = 3;
				break;
			default:
				this.hairStyle = 1;
		}
		invalidate();
	}

	public void setSelectedItem(int item) { // returns currently selected item
		this.selectedItem = item;
	}

	public int getSelectedItemColor() { // returns the currently selected color
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

	// sets the item color to the currently selected item color
	public void setItemColor(int color) {
		if (this.selectedItem == 0) {
			this.hairColor = color;
		} else if (this.selectedItem == 1) {
			this.eyeColor = color;
		} else if (this.selectedItem == 2) {
			this.skinColor = color;
		}
		invalidate();
	}

	protected int getHairStyle() { // getter for hairStyle
		return this.hairStyle;
	}
}
