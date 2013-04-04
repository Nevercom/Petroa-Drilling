package com.nevercom.android.petroleum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.simonvt.widget.MenuDrawer;
import net.simonvt.widget.MenuDrawerManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.nevercom.android.petroleum.drawer.Category;
import com.nevercom.android.petroleum.drawer.Item;
import com.nevercom.android.petroleum.drawer.MenuAdapter;

public class MainActivity extends Activity implements OnTouchListener {

	// private static final String TAG = "PETRO";

	private static final String STATE_MENUDRAWER = "com.nevercom.android.petroleum.MainActivity.menuDrawer";
	private static final String STATE_ACTIVE_POSITION = "com.nevercom.android.petroleum.MainActivity.activePosition";
	private int defaultIcon = R.drawable.ic_action_select_all_dark;

	private int[] texts = { R.string.crown_block, R.string.catline,
			R.string.drilling_line, R.string.monkeyboard,
			R.string.traveling_block, R.string.top_drive, R.string.mast,
			R.string.drill_pipe, R.string.doghouse, R.string.bop,
			R.string.water_tank, R.string.ect, R.string.egs,
			R.string.fuel_tanks, R.string.electric_house, R.string.mud_pump,
			R.string.bulk_mud, R.string.mud_pits, R.string.reserve_pits,
			R.string.mgs, R.string.shale_shaker, R.string.choke_manifold,
			R.string.pipe_ramp, R.string.pipe_racks, R.string.accumulator,
			R.string.annulus, R.string.brake_bands, R.string.casing_head,
			R.string.cathead, R.string.catwalk, R.string.cellar,
			R.string.conductor_pipe, R.string.degasser, R.string.desander,
			R.string.desilter, R.string.drawworks, R.string.drill_bit,
			R.string.drill_collar, R.string.drillers_console,
			R.string.elevators, R.string.hoisting_line, R.string.hook,
			R.string.kelly, R.string.kelly_bushing, R.string.kelly_spinner,
			R.string.mousehole, R.string.mud_return_line, R.string.ram_bop,
			R.string.rathole, R.string.rotary_hose, R.string.rotary_table,
			R.string.slips, R.string.spinning_chain, R.string.stairways,
			R.string.standpipe, R.string.surface_casing, R.string.substructure,
			R.string.swivel, R.string.tongs, R.string.walkways,
			R.string.weight_indicator };

	private int[] images = { R.drawable.crown_block_water_table,
			R.drawable.gin_pole, R.drawable.drill_line_on_drum,
			R.drawable.monkeyboard, R.drawable.traveling_block1_travelingb,
			R.drawable.topdrive2, R.drawable.mast_tblock, R.drawable.drillpipe,
			R.drawable.doghouse, R.drawable.bop1_small, R.drawable.water_tank2,
			R.drawable.electric_cable_tray, R.drawable.generators2,
			R.drawable.fuel_tanks, R.drawable.electric_control_panel,
			R.drawable.mud_pumps_3, R.drawable.bulk_mud_storage,
			R.drawable.mud_pumps_pits, R.drawable.rig_reserve_pit,
			R.drawable.mud_gas_separator, R.drawable.walkway,
			R.drawable.choke_manifold, R.drawable.pipe_ramp,
			R.drawable.piperack, R.drawable.accumulator2,
			R.drawable.casing_annulus, R.drawable.brake_bands,
			R.drawable.casing_head, R.drawable.cathead, R.drawable.catwalk,
			R.drawable.cellar, R.drawable.conductor_casing,
			R.drawable.degasser1, R.drawable.desander_desilter,
			R.drawable.desilter, R.drawable.drawworks, R.drawable.drill_bits,
			R.drawable.drillcollar, R.drawable.drillers_consol,
			R.drawable.drill_pipe2, R.drawable.hoisting_line,
			R.drawable.traveling_block1_hook, R.drawable.kelly,
			R.drawable.rotary_table_kelly_bushing,
			R.drawable.traveling_block1_kellyspinn, R.drawable.mouse_hole,
			R.drawable.mud_return_line, R.drawable.ram_type_bops,
			R.drawable.rathole, R.drawable.rotary_hose,
			R.drawable.rotary_table2, R.drawable.setslips,
			R.drawable.spinning_chain, R.drawable.stairways2,
			R.drawable.standpipe, R.drawable.surface_casing,
			R.drawable.substructure2, R.drawable.swivel,
			R.drawable.breaking_pipe, R.drawable.walkway,
			R.drawable.weight_indicator };

	private String[] titles = { "Crown Block and Water Table",
			"Catline Boom and Hoist Line", "Drilling Line", "Monkey Board",
			"Traveling Block", "Top Drive", "Mast", "Drill Pipe", "Doghouse",
			"Blowout Preventer", "Water Tank", "Electric Cable Tray",
			"Engine Generator Sets", "Fuel Tanks", "Electric House",
			"Mud Pump", "Bulk Mud Components in Storage", "Mud Pits",
			"Reserve Pits", "Mud Gas Separator", "Shale Shaker",
			"Choke Manifold", "Pipe Ramp", "Pipe Racks", "Accumulator",
			"Annulus", "Brake Bands", "Casing Head", "Cathead", "Catwalk",
			"Cellar", "Conductor Pipe", "Degasser", "Desander", "Desilter",
			"Drawworks", "Drill Bit", "Drill Collar", "Drillers Console",
			"Elevators", "Hoisting Line", "Hook", "Kelly", "Kelly Bushing",
			"Kelly Spinner", "Mousehole", "Mud Return Line",
			"Ram Blowout Preventer", "Rathole", "Rotary Hose", "Rotary Table",
			"Slips", "Spinning Chain", "Stairways", "Standpipe",
			"Surface Casing", "Substructure", "Swivel", "Tongs", "Walkways",
			"Weight Indicator" };

	private MenuDrawerManager mMenuDrawer;
	private MenuAdapter mAdapter;
	private MenuListView mList;
	private int mActivePosition = -1;

	private ScrollView sv;
	private float diffW;
	private float diffH;
	private float finalWidth;
	private float finalHeight;
	private Bitmap bitmap;

	private HashMap<Integer, Integer> map;
	private int startX = 0;
	private int startY = 0;

	@Override
	public void onCreate(Bundle inState) {
		super.onCreate(inState);
		if (inState != null) {
			mActivePosition = inState.getInt(STATE_ACTIVE_POSITION);
		}

		setupMenuDrawer();
		prepairList();
		sv = (ScrollView) findViewById(R.id.image_scrollview);
		sv.setOnTouchListener(this);
		final ImageView iv = (ImageView) findViewById(R.id.imageView1);

		// getting an instance of Mask Image
		Drawable drawable = getResources().getDrawable(R.drawable.rigmask);
		bitmap = ((BitmapDrawable) drawable).getBitmap();

		final float maskW = bitmap.getWidth();
		final float maskH = bitmap.getHeight();

		ViewTreeObserver vto = iv.getViewTreeObserver();
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			public boolean onPreDraw() {
				finalHeight = iv.getMeasuredHeight();
				finalWidth = iv.getMeasuredWidth();
				diffW = finalWidth / maskW;
				diffH = finalHeight / maskH;
				return true;
			}
		});

	}

	@SuppressLint("UseSparseArrays")
	private void prepairList() {

		map = new HashMap<Integer, Integer>();
		map.put(-65536, 0);
		map.put(-16772904, 1);
		map.put(-16264, 2);
		map.put(-16721904, 3);
		map.put(-18408, 4);
		map.put(-33704, 5);
		map.put(-10984193, 6);
		map.put(-3652353, 7);
		map.put(-1019736, 8);
		map.put(-6885871, 9);
		map.put(-2792917, 10);
		map.put(-8355840, 11);
		map.put(-8883080, 12);
		map.put(-7798944, 13);
		map.put(-13421688, 14);
		map.put(-5494625, 15);
		map.put(-14120293, 16);
		map.put(-2031391, 17);
		map.put(-14981789, 18);
		map.put(-2611316, 19);
		map.put(-15439077, 20);
		map.put(-9106610, 21);
		map.put(-11214683, 22);
		map.put(-5559150, 23);
		map.put(-8855297, 24);

	}

	private void setupMenuDrawer() {
		mMenuDrawer = new MenuDrawerManager(MainActivity.this,
				MenuDrawer.MENU_DRAG_WINDOW);
		mMenuDrawer.setContentView(R.layout.activity_main);

		List<Object> items = new ArrayList<Object>();
		items.add(new Category("Drilling Rig Components"));

		for (int i = 0; i < 25; i++) {
			items.add(new Item((i + 1) + "- " + titles[i], defaultIcon));

		}
		items.add(new Category("Other Components"));
		for (int i = 25; i <= 60; i++) {
			items.add(new Item((i + 1) + "- " + titles[i], defaultIcon));

		}

		// A custom ListView is needed so the drawer can be notified when it's
		// scrolled. This is to update the position
		// of the arrow indicator.
		mList = new MenuListView(this);
		mList.setCacheColorHint(Color.parseColor("#00000000"));
		mAdapter = new MenuAdapter(items, this);
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(mItemClickListener);
		mList.setOnScrollChangedListener(new MenuListView.OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				mMenuDrawer.getMenuDrawer().invalidate();
			}
		});

		mMenuDrawer.setMenuView(mList);
	}

	private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			mActivePosition = position;
			// mMenuDrawer.setActiveView(view, position);
			mMenuDrawer.closeMenu();
			// Log.d(TAG, Integer.toString(position));
			if (position <= 25) {
				// first 25 components
				showDetails(texts[position - 1], images[position - 1],
						titles[position - 1]);
			} else {
				// Additional components
				showDetails(texts[position - 2], images[position - 2],
						titles[position - 2]);
			}

		}
	};

	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);
		mMenuDrawer.onRestoreDrawerState(inState
				.getParcelable(STATE_MENUDRAWER));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(STATE_MENUDRAWER,
				mMenuDrawer.onSaveDrawerState());
		outState.putInt(STATE_ACTIVE_POSITION, mActivePosition);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mMenuDrawer.toggleMenu();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			mMenuDrawer.toggleMenu();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		final int drawerState = mMenuDrawer.getDrawerState();
		if (drawerState == MenuDrawer.STATE_OPEN
				|| drawerState == MenuDrawer.STATE_OPENING) {
			mMenuDrawer.closeMenu();
			return;
		}

		super.onBackPressed();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (v.getId() == R.id.imageView1 || v.getId() == R.id.image_scrollview) {

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = (int) event.getX();
				startY = (int) event.getY();
				break;
			case MotionEvent.ACTION_UP:
				int cordinate_Y = (int) ((event.getY() + v.getScrollY()) / diffH);
				int cordinate_X = (int) ((event.getX() + v.getScrollX()) / diffW);
				if ((Math.abs((int) event.getX() - startX) > 10)
						|| (Math.abs((int) event.getY() - startY) > 10)) {
					break;
				}

				int pixelColor = bitmap.getPixel(cordinate_X, cordinate_Y);
				if (pixelColor != -1) {
					showDetails(texts[map.get(pixelColor)],
							images[map.get(pixelColor)],
							titles[map.get(pixelColor)]);

				}

				break;

			}
		}

		return false;
	}

	private void showDetails(int text, int image, String title) {
		Intent intent = new Intent(this, DetailsActivity.class);
		intent.putExtra("TEXT", text);
		intent.putExtra("IMAGE", image);
		intent.putExtra("TITLE", title);
		startActivity(intent);

	}

}
