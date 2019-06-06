package com.cmota.quiz.ui;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmota.quiz.QuizApplication;
import com.cmota.quiz.R;
import com.cmota.quiz.cb.IOnUserAction;
import com.cmota.quiz.model.Theme;

import java.util.List;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.ThemesViewHolder> {

    private List<Theme> mThemes;
    private IOnUserAction mAction;


    ThemesAdapter(List<Theme> themes, IOnUserAction action) {
        mThemes = themes;
        mAction = action;
    }

    @NonNull
    @Override
    public ThemesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ThemesViewHolder(inflater.inflate(R.layout.item_theme, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThemesViewHolder themesViewHolder, int position) {
        final Theme theme = mThemes.get(position);
        themesViewHolder.mName.setText(theme.getName());

        int color = themesViewHolder.mPreview.getContext().getResources().getColor(theme.getColor());
        themesViewHolder.mPreview.setImageDrawable(new ColorDrawable(color));
        themesViewHolder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAction.onUserClickAction(theme, view);
            }
        });

        themesViewHolder.mContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return mAction.onUserLongClickAction(theme, view);
            }
        });

        if (QuizApplication.getCurrentTheme() == theme.getTheme()) {
            int backgroundColor = themesViewHolder.mPreview.getContext().getResources().getColor(R.color.colorLightGrey);
            themesViewHolder.mContainer.setBackgroundColor(backgroundColor);

            int textColor = themesViewHolder.mPreview.getContext().getResources().getColor(android.R.color.black);
            themesViewHolder.mName.setTextColor(textColor);
        }
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    static class ThemesViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private ImageView mPreview;

        private RelativeLayout mContainer;

        ThemesViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.tv_name);
            mPreview = itemView.findViewById(R.id.iv_preview);
            mContainer = itemView.findViewById(R.id.rl_container);
        }
    }
}
