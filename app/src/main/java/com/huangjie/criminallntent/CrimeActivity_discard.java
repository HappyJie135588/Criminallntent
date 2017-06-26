package com.huangjie.criminallntent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;

import java.util.UUID;

public class CrimeActivity_discard extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID = "com.huangjie.criminallntent.crime_id";

    @Override
    protected Fragment creatFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext,CrimeActivity_discard.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }

}
