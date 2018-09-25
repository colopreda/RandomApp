package com.apredazzi.randomapplication.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apredazzi.randomapplication.BaseFragment;
import com.apredazzi.randomapplication.R;
import com.apredazzi.randomapplication.pojo.Users;
import com.squareup.picasso.Picasso;

public final class DetailFragment extends BaseFragment implements DetailContract.View {

    public static final String USER = "user";

    private DetailContract.Presenter mPresenter;

    Users user;

    @BindView(R.id.image) ImageView header;
    @BindView(R.id.textview_name) TextView nameTextView;
    @BindView(R.id.textview_email) TextView emailTextView;
    @BindView(R.id.textview_username) TextView usernameTextView;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout toolbar;
    @BindView(R.id.toolbar) Toolbar realToolbar;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail_layout, container, false);

        if (getArguments() != null) {
            user = getArguments().getParcelable(USER);
        }

        ButterKnife.bind(this, v);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(realToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter.loadData(user);

        return v;
    }

    @Override
    public void loadImage(final String imageUrl) {
        Picasso.get().load(imageUrl).into(header);
    }

    @Override
    public void loadPersonalData(final String name, final String username, final String email) {
        nameTextView.setText(name);
        usernameTextView.setText(username);
        emailTextView.setText(email);
        toolbar.setTitle(name);
    }
}
