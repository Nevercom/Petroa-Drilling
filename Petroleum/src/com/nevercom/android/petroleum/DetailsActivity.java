package com.nevercom.android.petroleum;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nevercom.android.petroleum.utils.PersianReshape;

public class DetailsActivity extends Activity {

	private Typeface yekan;
	//private Typeface koodak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_details);
		// Create a typeface to use custom font for TextView
		yekan = Typeface.createFromAsset(getAssets(), "yekan.ttf");
		//koodak = Typeface.createFromAsset(getAssets(), "koodak.ttf");

		TextView detailTitle = (TextView) findViewById(R.id.detail_title);
		TextView detailTextView = (TextView) findViewById(R.id.detail_text);
		ImageView detailImageView = (ImageView) findViewById(R.id.detail_image);

		// setting the typeface
		detailTextView.setTypeface(yekan);

		// Get Intent extras sent to this Activity
		// This would be: Title, Image and text to be displayed in activity
		Intent intent = getIntent();
		int text = intent.getIntExtra("TEXT", 0);
		int image = intent.getIntExtra("IMAGE", 0);
		String title = intent.getStringExtra("TITLE");

		detailTextView.setGravity(Gravity.RIGHT);

		detailTitle.setText(title);
		detailTextView.setText(PersianReshape.reshape(getString(text)));
		detailImageView.setImageResource(image);

	}

}
