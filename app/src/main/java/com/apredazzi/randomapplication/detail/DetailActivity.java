package com.apredazzi.randomapplication.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.apredazzi.randomapplication.BaseActivity;
import com.apredazzi.randomapplication.R;

public class DetailActivity extends BaseActivity {

    private static final String PERSON_DETAILS = "PERSON_DETAILS";
    private static final String USER = "user";

    DetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        setContentView(R.layout.activity_detail_layout);

        DetailFragment DetailFragment = (DetailFragment) getSupportFragmentManager()
            .findFragmentById(R.id.frame_layout_content);
        if (DetailFragment == null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(USER, getIntent().getParcelableExtra(PERSON_DETAILS));
            DetailFragment = DetailFragment.newInstance();
            DetailFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_layout_content, DetailFragment);
            transaction.commit();
        }
        mPresenter = new DetailPresenter(this, DetailFragment);
        mPresenter.start();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            onBackPressed();
            break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }
}
