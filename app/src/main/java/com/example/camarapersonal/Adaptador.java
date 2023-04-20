package com.example.camarapersonal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class Adaptador extends BaseAdapter {
    private int layout;
    private Context context;
    private List<String> items;
    public Adaptador(Context context, int layout, List<String> items ){
        this.context = context;
        this.layout = layout;
        this.items = items;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
