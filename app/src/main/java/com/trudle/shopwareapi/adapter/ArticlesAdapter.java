package com.trudle.shopwareapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trudle.shopwareapi.Address;
import com.trudle.shopwareapi.R;

import java.util.List;


/**
 * Created by developer on 28/9/16.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<Address> articles;
    private int rowLayout;
    private Context context;

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        LinearLayout articleLayout;
        TextView name;
        TextView description;
        TextView descriptionLong;
        //TextView rating;


        public ArticleViewHolder(View v) {
            super(v);
            articleLayout = (LinearLayout) v.findViewById(R.id.article_layout);
            name = (TextView) v.findViewById(R.id.name);
            description = (TextView) v.findViewById(R.id.description);
            descriptionLong = (TextView) v.findViewById(R.id.descriptionLong);
            //rating = (TextView) v.findViewById(R.id.rating);
        }
    }

    public ArticlesAdapter(List<Address> movies, int rowLayout, Context context) {
        this.articles = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ArticlesAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.name.setText(articles.get(position).getId());
        holder.description.setText(articles.get(position).getFirstname());
        holder.descriptionLong.setText(articles.get(position).getCity());
       // holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

}
