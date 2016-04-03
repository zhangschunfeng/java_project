package com.example.demo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {
	private Context context;
	private String text;
	public MyFragment(Context context,String text)
	{
		this.context=context;
		this.text=text;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_layout, container,false);
		TextView textview=(TextView) view.findViewById(R.id.fragment_text);
		textview.setText(text);
		return view;
	}


}
