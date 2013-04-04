package com.nevercom.android.petroleum.drawer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nevercom.android.petroleum.R;


public class MenuAdapter extends BaseAdapter {

	private List<Object> mItems;
	private LayoutInflater myInflater;
	private Context parentActivity;


	public MenuAdapter(List<Object> items, Context context) {
		mItems = items;
		parentActivity = context;
		myInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return getItem(position) instanceof Item ? 0 : 1;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public boolean isEnabled(int position) {
		return getItem(position) instanceof Item;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Object item = getItem(position);

		if (item instanceof Category) {
			if (v == null) {
				v = myInflater.inflate(R.layout.menu_row_category,
						parent, false);
			}

			((TextView) v).setText(((Category) item).mTitle);

		} else {
			if (v == null) {
				v = myInflater.inflate(R.layout.menu_row_item,
						parent, false);
			}

			TextView tv = (TextView) v;
			tv.setText(((Item) item).mTitle);
			tv.setCompoundDrawablesWithIntrinsicBounds(
					((Item) item).mIconRes, 0, 0, 0);
//			tv.setCompoundDrawablesWithIntrinsicBounds(
//					R.drawable.ic_action_select_all_dark, 0, 0, 0);
		}

		v.setTag(R.id.mdActiveViewPosition, position);

//		if (position == mActivePosition) {
//			//mMenuDrawer.setActiveView(v, position);
//		}

		return v;
	}
}

