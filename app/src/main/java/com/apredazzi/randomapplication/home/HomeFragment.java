package com.apredazzi.randomapplication.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.apredazzi.randomapplication.BaseFragment;
import com.apredazzi.randomapplication.R;
import com.apredazzi.randomapplication.adapters.UserAdapter;
import com.apredazzi.randomapplication.detail.DetailActivity;
import com.apredazzi.randomapplication.pojo.Users;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import java.util.ArrayList;
import java.util.List;

public final class HomeFragment extends BaseFragment implements HomeContract.View, UserAdapter.OnPersonInteraction {

    @BindView(R.id.recycler_view) SuperRecyclerView recyclerView;

    private OnHomeFragmentInteractionListener mListener;

    private HomeContract.Presenter mPresenter;

    UserAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_layout, container, false);

        ButterKnife.bind(this, v);
        setLoadMoreItemsListener();
        setSwipeRefreshLayout();

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentInteractionListener) {
            mListener = (OnHomeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    private void setSwipeRefreshLayout() {
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getUsers(50);
            }
        });
    }

    private void setLoadMoreItemsListener() {
        recyclerView.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(final int overallItemsCount, final int itemsBeforeMore,
                final int maxLastVisiblePosition) {
                mPresenter.getMoreUsers(25);
            }
        }, 5);
    }

    @Override
    public void loadMoreUsers(final List<Users> users) {
        adapter.addAll(users);
        recyclerView.hideMoreProgress();
    }

    @Override
    public void loadUsers(final List<Users> users) {
        adapter = new UserAdapter(users, R.layout.row_user_thumbail, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void personClicked(final Users user) {
        mListener.onUserDetailsReceived(user);
    }

    public interface OnHomeFragmentInteractionListener {
        void onUserDetailsReceived(Users user);
    }
}
