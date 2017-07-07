package com.developers.superheroes.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.developers.superheroes.util.OnLoadMoreListener;

/**
 * Created by Amanjeet Singh on 07-Jul-17.
 */

public class SuperHeroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnLoadMoreListener onLoadMoreListener;
    private Context context;
    private boolean isLoading;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshHold = 5;
    private int totalItemCount, lastVisibleItem;
    private GridLayoutManager layoutManager;

    public SuperHeroAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshHold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class SuperHeroViewHolder extends RecyclerView.ViewHolder {

        public SuperHeroViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

}
