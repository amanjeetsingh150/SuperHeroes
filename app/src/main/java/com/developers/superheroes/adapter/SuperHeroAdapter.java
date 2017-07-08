package com.developers.superheroes.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.developers.superheroes.R;
import com.developers.superheroes.model.Image;
import com.developers.superheroes.model.SuperHero;
import com.developers.superheroes.util.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private List<SuperHero> superHeroList;

    public SuperHeroAdapter(Context context, RecyclerView recyclerView, List<SuperHero> superHeroList, final GridLayoutManager lm) {
        this.context = context;
        this.superHeroList = superHeroList;
        layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = lm.getItemCount();
                lastVisibleItem = lm.findLastVisibleItemPosition();
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
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_row_list, parent, false);
            return new SuperHeroViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return superHeroList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SuperHeroViewHolder) {
            SuperHero superHero = superHeroList.get(position);
            SuperHeroViewHolder superHeroViewHolder = (SuperHeroViewHolder) holder;
            superHeroViewHolder.heroNameTextView.setText(superHero.getName());
            Log.d("TAG",superHero.getImgUrl());
            Picasso.with(context).load(superHero.getImgUrl()).into(superHeroViewHolder.heroImageView);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return superHeroList.size();
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progress_bar)
        ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SuperHeroViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hero_image_view)
        ImageView heroImageView;
        @BindView(R.id.hero_name_text_view)
        TextView heroNameTextView;

        public SuperHeroViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

}
