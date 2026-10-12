package com.example.ems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.Recyclervh> {
    List<MedModel> mviewlist;
    MedModel medModel;
    private Context mContext;

    public MedicinesAdapter(Context c, List<MedModel> viewModels) {
        this.mContext = c;
        this.mviewlist = viewModels;
    }

    public Recyclervh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View v = LayoutInflater.from(mContext).inflate(R.layout.medicinesrecycler, parent, false);
        return new Recyclervh(v);
    }
    @Override
    public void onBindViewHolder(@NonNull Recyclervh holder, int position) {
        medModel = mviewlist.get(position);
        if (medModel != null) {
            holder.mmedname.setText("Medicine Name : " + (medModel.getMedname() != null ? medModel.getMedname() : ""));
            holder.mmedtype.setText("Medicine Type : " + (medModel.getMedtype() != null ? medModel.getMedtype() : ""));
            holder.mdescription.setText("Description : " + (medModel.getDescription() != null ? medModel.getDescription() : ""));
        }
    }

    @Override
    public int getItemCount() {
        return mviewlist.size();
    }

    public class Recyclervh extends RecyclerView.ViewHolder {
        TextView mmedname, mmedtype, mdescription;
        public Recyclervh(View itemview) {
            super(itemview);
            mmedname = (TextView) itemview.findViewById(R.id.nid);
            mmedtype = (TextView) itemview.findViewById(R.id.mtid);
            mdescription = (TextView) itemview.findViewById(R.id.mdid);
        }
    }
}