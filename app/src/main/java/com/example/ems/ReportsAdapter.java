package com.example.ems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.Recyclervh>{
    List<ReportsModel> mviewlist;
    ReportsModel reportsModel;
    private Context mContext;

    public ReportsAdapter(Context c, List<ReportsModel> viewModels) {
        this.mContext = c;
        this.mviewlist = viewModels;
    }
    public ReportsAdapter.Recyclervh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View v = LayoutInflater.from(mContext).inflate(R.layout.reportsrecycler, parent, false);
        return new ReportsAdapter.Recyclervh(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ReportsAdapter.Recyclervh holder, int position) {
        reportsModel = mviewlist.get(position);
        if (reportsModel != null) {
            holder.mreporttype.setText("Report Type : " + (reportsModel.getReporttype() != null ? reportsModel.getReporttype() : ""));
            holder.mdescription.setText("Description : " + (reportsModel.getDescription() != null ? reportsModel.getDescription() : ""));
        }
    }

    @Override
    public int getItemCount() {
        return mviewlist.size();
    }

    public class Recyclervh extends RecyclerView.ViewHolder {
        TextView mreporttype, mdescription;
        public Recyclervh(View itemview) {
            super(itemview);
            mreporttype = (TextView) itemview.findViewById(R.id.rid);
            mdescription = (TextView) itemview.findViewById(R.id.rdid);
        }
    }
}
