package com.example.travel_taker.Login.Twitter.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travel_taker.Login.Twitter.models.Tweet;
import com.example.travel_taker.Login.Twitter.models.TweetDateFormatter;
import com.example.travel_taker.R;

import java.util.List;

public class TweetListAdapter extends AnimatedRecyclerViewAdapter<TweetListAdapter.ViewHolder> {

    private final TweetDateFormatter mFormatter;
    private List<Tweet> mTweetList;

    public TweetListAdapter(Context context, TweetDateFormatter formatter) {
        super(context);
        this.mFormatter = formatter;
    }

    public void update(List<Tweet> tweetList) {
        this.mTweetList = tweetList;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View tweetItemView = inflater.inflate(R.layout.tweet_item, parent, false);
        return new ViewHolder(tweetItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Tweet tweet = mTweetList.get(position);
        holder.content.setText(tweet.getContent());
        holder.createdAt.setText(mFormatter.format(mContext, tweet.getCreatedAt()));
        holder.username.setText(tweet.getUsername());
        String imageUrl = tweet.getImageUrl();
        if (TextUtils.isEmpty(imageUrl)) {
            holder.imageView.setVisibility(View.GONE);
        } else {
            Glide.with(mContext).load(imageUrl).centerCrop().into(holder.imageView);
            holder.imageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mTweetList == null ? 0 : mTweetList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView username;
        private final TextView createdAt;
        private final TextView content;
        private final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.usernameTextView);
            createdAt = (TextView) itemView.findViewById(R.id.createdAtTextView);
            content = (TextView) itemView.findViewById(R.id.contentTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
