package com.namvova.namgiza_sqlite_sample.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.namvova.namgiza_sqlite_sample.Model.SinhVien;
import com.namvova.namgiza_sqlite_sample.R;

import java.util.List;

public class SinhVienAddapter extends ArrayAdapter<SinhVien> {
    private List<SinhVien> sinhVienList;
    private int resource;
    private Context context;
    public SinhVienAddapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.sinhVienList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sinhvien, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvClass = (TextView) convertView.findViewById(R.id.tv_class);
            viewHolder.tvID = (TextView) convertView.findViewById(R.id.ID);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //
        SinhVien sinhVien = sinhVienList.get(position);
        viewHolder.tvID.setText(String.valueOf(sinhVien.getmID()));
        viewHolder.tvName.setText(sinhVien.getmName());
        viewHolder.tvClass.setText(sinhVien.getmClass());
        return convertView;
    }

    public class ViewHolder {
        TextView tvName, tvClass;
        TextView tvID;

    }
}
