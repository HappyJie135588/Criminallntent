package com.huangjie.criminallntent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.UUID;

/**
 * Created by 黄杰 on 2016/7/27.
 */
public class CrimePagerActivity extends AppCompatActivity implements CrimeFragment.Callbacks {
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    private static final String EXTRA_CRIME_ID = "com.huangjie.criminallntent.crime_id";
    private static final String SUBTITLEVISIBLE = "com.huangjie.criminallntent.subtitleVisible";
    private UUID mCrimeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        mCrimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        int item = CrimeLab.get(this).getPosition(mCrimeId);
        mViewPager.setCurrentItem(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_crime,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_itme_delete_crime:
                CrimeLab.get(this).deleteCrime(mCrimeId);
                finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId, boolean subtitleVisible){
        Intent intent = new Intent(packageContext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        intent.putExtra(SUBTITLEVISIBLE,subtitleVisible);
        return intent;
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {

        return getIntent();
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
