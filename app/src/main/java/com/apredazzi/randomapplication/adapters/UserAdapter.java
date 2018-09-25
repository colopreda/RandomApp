package com.apredazzi.randomapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.apredazzi.randomapplication.R;
import com.apredazzi.randomapplication.pojo.Users;
import com.squareup.picasso.Picasso;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder> {

    private List<Users> users;
    private final int layoutResourceId;
    private OnPersonInteraction mListener;

    public UserAdapter(final List<Users> users, final int layoutResourceId, final OnPersonInteraction listener) {
        this.users = users;
        this.layoutResourceId = layoutResourceId;
        mListener = listener;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(
            parent.getContext()).inflate(layoutResourceId, parent, false);
        return new UsersViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final UsersViewHolder holder, final int position) {
        holder.init(users.get(position));
    }

    public void addAll(List<Users> moreUsers) {
        users.addAll(moreUsers);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static final class UsersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail_picture) ImageView userPicture;
        OnPersonInteraction mListener;

        public UsersViewHolder(final View itemView,
            final OnPersonInteraction mListener) {
            super(itemView);
            this.mListener = mListener;
            ButterKnife.bind(this, itemView);
        }

        public void init(final Users userVo) {
            Picasso.get().load(userVo.getPicture().getLarge()).into(userPicture);
            userPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mListener.personClicked(userVo);
                }
            });
        }
    }

    public interface OnPersonInteraction {
        void personClicked(Users user);
    }
}
