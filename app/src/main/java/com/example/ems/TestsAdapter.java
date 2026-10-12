package com.example.ems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.Recyclervh> {
    List<TestsModel> mviewlist;
    TestsModel testsModel;
    private Context mContext;

    public TestsAdapter(Context c, List<TestsModel> viewModels) {
        this.mContext = c;
        this.mviewlist = viewModels;
    }

    public Recyclervh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View v = LayoutInflater.from(mContext).inflate(R.layout.testsrecycler, parent, false);
        return new Recyclervh(v);
    }
    @Override
    public void onBindViewHolder(@NonNull Recyclervh holder, int position) {
        testsModel = mviewlist.get(position);
        if (testsModel != null) {
            holder.mPriortests.setText("Prior Tests : " + (testsModel.getPriortests() != null ? testsModel.getPriortests() : ""));
            holder.mtesttype.setText("Test Type : " + (testsModel.getTesttype() != null ? testsModel.getTesttype() : ""));
            holder.mdescription.setText("Description : " + (testsModel.getDescription() != null ? testsModel.getDescription() : ""));
        }
    }

    @Override
    public int getItemCount() {
        return mviewlist.size();
    }

    public class Recyclervh extends RecyclerView.ViewHolder {
        TextView mPriortests, mtesttype, mdescription;
        public Recyclervh(View itemview) {
            super(itemview);
            mPriortests = (TextView) itemview.findViewById(R.id.pid);
            mtesttype = (TextView) itemview.findViewById(R.id.tid);
            mdescription = (TextView) itemview.findViewById(R.id.tdid);
        }
    }
}
