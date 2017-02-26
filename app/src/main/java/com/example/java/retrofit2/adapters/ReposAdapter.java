package com.example.java.retrofit2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.java.retrofit2.R;
import com.example.java.retrofit2.model.Repo;

import java.util.List;


/**
 * Created by java on 13.02.2017.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    private List<Repo> mDataSource;

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_repo, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReposViewHolder holder, int position) {
        Repo repo = mDataSource.get(position);
        holder.bindView(repo);
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public void setDataSource(List<Repo> dataSource) {
        mDataSource = dataSource;
        notifyDataSetChanged();
    }

    public static class ReposViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextRepo;

        private ReposViewHolder(View itemView) {
            super(itemView);
            mTextRepo = (TextView) itemView.findViewById(R.id.textRepo);

        }

        private void bindView(Repo repo) {
            mTextRepo.setText(repo.getName());
        }
    }
}
