package com.androidapp.tobeacontinue.Todolist;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.androidapp.tobeacontinue.CafeteriaFragment;
import com.androidapp.tobeacontinue.Note;
import com.androidapp.tobeacontinue.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class CafeteriaTodolist extends AppCompatActivity implements OnTabItemSelectedListener{
    //비콘 프레그먼트에서 각 버튼을 클릭 시 열리는 새로운 액티비티

    CafeteriaFragment cafeteriaFragment;                //집 memo 목록
    //NoteWriteFragment noteFragment;             //작성 fragment

    BottomNavigationView bottomNavigationView;      //하단바

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_todolist);

        cafeteriaFragment = new CafeteriaFragment();
        //noteFragment = new NoteWriteFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container1, cafeteriaFragment).commit();
        //시작페이지는 memo목록으로

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container1,cafeteriaFragment).commit();
                        return true;

                    case R.id.tab2:

                        //getSupportFragmentManager().beginTransaction().replace(R.id.container1,noteFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onTabSelected(int position) {
        if(position ==0 ){
            bottomNavigationView.setSelectedItemId(R.id.tab1);
        }else if(position == 1){
            bottomNavigationView.setSelectedItemId(R.id.tab2);
        }
    }

    @Override
    public void showNoteWriteFragment(Note item) {
       // noteFragment = new NoteWriteFragment();
       // noteFragment.setItem(item);

      //  getSupportFragmentManager().beginTransaction().replace(R.id.container1,noteFragment).commit();
    }
}
