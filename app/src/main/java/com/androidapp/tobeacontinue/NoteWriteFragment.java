package com.androidapp.tobeacontinue;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidapp.tobeacontinue.Todolist.OnTabItemSelectedListener;

import java.util.Date;

public class NoteWriteFragment extends Fragment {

    private static final String TAG = "NoteWriteFragment";

    EditText edtText;
    Button savebutton;
    OnTabItemSelectedListener listener;

    TextView dateTextView;
    Context context;
    int mMode = AppConstants.MODE_INSERT;
    int _id = -1;

    Note item;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;

        if(context instanceof OnTabItemSelectedListener){
            listener  = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(context !=null){
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_note_write, container, false);
        initUI(rootview);

        applyItem();
        return rootview;
    }

    private void initUI(ViewGroup rootview) {
        edtText = rootview.findViewById(R.id.edtMemo);
        dateTextView = rootview.findViewById(R.id.dateTextView);
        savebutton = rootview.findViewById(R.id.btnDone);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMode == AppConstants.MODE_INSERT) {
                    saveNote();
                }else if(mMode == AppConstants.MODE_MODIFY) {
                    modifyNote();
                }

                if(listener!=null){
                    listener.onTabSelected(0);
                }

            }
        });

        rootview.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener !=null){
                    listener.onTabSelected(0);
                }
            }
        });

    }

    public void setItem(Note item) {
        this.item = item;
    }


    public void setDateString(String dateString){
        dateTextView.setText(dateString);
    }

    public void setContents(String data){
        edtText.setText(data);
    }

    public void applyItem(){
        AppConstants.println("applyItem called.");

        if(item != null){
            mMode = AppConstants.MODE_MODIFY;

            setDateString(item.getCreateDateStr());
            setContents(item.getContents());
        }else{
            mMode =AppConstants.MODE_INSERT;
            Date currentDate = new Date();
            String currentDateString = AppConstants.dateFormat2.format(currentDate);
            setDateString(currentDateString);

            edtText.setText("");
        }
    }

    //db에 데이터 저장
    private void saveNote(){
        String contents = edtText.getText().toString();
        String sql = "insert into "+NoteDatabase.TABLE_NOTE+
                "(CONTENTS) values(" +"'"+ contents + "')";

        Log.d(TAG,"sql : "+sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    //db에 데이터 수정정
    private void modifyNote(){
        if(item != null){
            String contents = edtText.getText().toString();

            String sql = "Update "+NoteDatabase.TABLE_NOTE+
                    " set"+
                    "  CONTENTS = '"+contents+ "'"+
                    " where "+
                    "  _id = " + item._id;

            Log.d(TAG,"sql : "+sql);
            NoteDatabase database = NoteDatabase.getInstance(context);
            database.execSQL(sql);
        }
    }
}