package com.example.hulyquran;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.hulyquran.Base.BaseActivity;
import com.example.hulyquran.Fragments.AhadethFragment;
import com.example.hulyquran.Fragments.CountingFragment;
import com.example.hulyquran.Fragments.QuranFragment;
import com.example.hulyquran.Fragments.RadioFragment;

public class MainActivity extends BaseActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            Fragment fragment = null;
            if (id == R.id.quran) fragment = new QuranFragment();
            else if (id == R.id.counting) fragment = new CountingFragment();
            else if (id == R.id.ahadeth) fragment = new AhadethFragment();
            else if (id == R.id.radio) fragment = new RadioFragment();



            assert fragment != null;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();


            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.quran);
    }


}
