/*
 *  Face.java - class contains methods to draw the face, set/get colors of the different
 *  parts of the face.  Face.java also contains the randomize() method which randomizes
 *  the colors and features of the face.
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

	// value for the currently selected item of the RadioGroup (hair, eyes, skin)
	private int selectedItem;
	private int hairStyle; // integer from 0 to 2 representing hairStyle

	public FaceView(Context context) {
		super(context);
		setWillNotDraw(false);

		this.selectedItem = 0;
		randomize(); // randomize the features of the face right away
	}

	public FaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);

		this.selectedItem = 0;
		randomize(); // randomize the features and colors of the face right away
	}

	// setters for colors of the items
	public void setEyeColor(int eyeColor) {
		this.eyeColor = eyeColor;
	}

	public void setSkinColor(int skinColor) {
		this.skinColor = skinColor;
	}

	public void setHairColor(int hairColor) {
		this.hairColor = hairColor;
	}

	protected void randomize() {
		Random random = new Random();

		this.hairStyle = random.nextInt(3);

		this.skinColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
		this.eyeColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
		this.hairColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));

	}

	/**
	 * onDraw - method that implements the SurfaceView class drawing
	 * @param canvas - main canvas
	 */
	public void onDraw(Canvas canvas) {
		canvas.drawColor(-1); // make the entire canvas white

		drawHairStyle(canvas, this.hairStyle);
		drawSkin(canvas);
		drawEyes(canvas);
	}

	/**
	 * drawHairStyle() - draws hair corresponding to the selected hair and hairColor
	 * @param canvas - main canvas
	 * @param hairStyle - integer representing hairStyle
	 */
	protected void drawHairStyle(Canvas canvas, int hairStyle) {
		int x = canvas.getWidth(); // getting the width and height of canvas
		int y = canvas.getHeight();
		int width = y / 4;

		// creating a paint to draw the hair with
		Paint hairPaint = new Paint();
		hairPaint.setStyle(Paint.Style.FILL);
		hairPaint.setColor(this.hairColor);

		if (hairStyle == 0) {

			// drawing the circle for Afro Hair
			canvas.drawCircle(x / 2, y / 2 - width / 3, y / 4 + 100, hairPaint);
		} else if (hairStyle == 1) {

			// drawing rectangles for cool block hair that makes Blocky Hair
			for(int i = 0; i < 13; i++) {
				canvas.drawRect(x / 2 - (y - 100) / 3 + (i*(y/21)), y / 2 - width - 40, x / 2 - (y - 100) / 3 + i*(y/21) + y/30, (3*y) / 4, hairPaint);
			}

		} else if (hairStyle == 2) {

			// drawing three circles to make Triple Circle Hair
			canvas.drawCircle(x / 2 + 200, y / 2 - width / 3, y / 6, hairPaint);
			canvas.drawCircle(x / 2, y / 2 - width / 3, y / 6, hairPaint);
			canvas.drawCircle(x / 2 - 200, y / 2 - width / 3, y / 6, hairPaint);
		}
	}

	/** drawSkin - method to draw the face
	 * @param canvas - main canvas
	 */
	protected void drawSkin(Canvas canvas) {
		int x = canvas.getWidth(); // getting the width and height of canvas
		int y = canvas.getHeight();
		int radius = y / 3;

		Paint skinPaint = new Paint(); // making Paint for the skin
		skinPaint.setColor(this.skinColor);
		skinPaint.setStyle(Paint.Style.FILL);

		canvas.drawCircle(x / 2, (y * 2) / 3, radius, skinPaint);
	}

	/** drawEyes - method to draw two eyes on the face
	 * @param canvas - main canvas
	 */
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

	/** setSelectedItem - sets the selected button variable
	 * @param item - integer representing the item to set as selected
	 */
	public void setSelectedItem(int item) { // returns currently selected item
		this.selectedItem = item;
	}

	/** getSelectedItemColor
	 * @return integer representing the color of the currently selected item
	 */
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

	/** setItemColor - sets the item color to the currently selected item color
	 * @param color - int color to set for the item
	 */
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

	/** setHairStyle - sets the current hairStyle integer
	 * @param hairStyle - integer representing the hairStlye (0-2)
	 */
	protected void setHairStyle(int hairStyle) {
		this.hairStyle = hairStyle;
		invalidate();
	}
}
