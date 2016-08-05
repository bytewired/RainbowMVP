package com.ne1c.rainbowmvpexample.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ne1c.rainbowmvpexample.R;
import com.ne1c.rainbowmvpexample.api.RepoModel;

import java.util.ArrayList;
import java.util.Locale;

public class ReposAdapter extends BaseAdapter {
    private ArrayList<RepoModel> mRepos;

    public ReposAdapter(ArrayList<RepoModel> repos) {
        mRepos = repos;
    }

    @Override
    public int getCount() {
        return mRepos.size();
    }

    @Override
    public Object getItem(int i) {
        return mRepos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_item, null, false);

        final RepoModel repo = mRepos.get(position);

        ((TextView) v.findViewById(R.id.repoName_textView)).setText(repo.name);
        ((TextView) v.findViewById(R.id.repoDesc_textView)).setText(repo.description);
        ((TextView) v.findViewById(R.id.stars_textView)).setText(String.format(Locale.ENGLISH, "Stars: %d", repo.stars));

        v.findViewById(R.id.openUrl_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(repo.url)));
            }
        });

        return v;
    }
}
