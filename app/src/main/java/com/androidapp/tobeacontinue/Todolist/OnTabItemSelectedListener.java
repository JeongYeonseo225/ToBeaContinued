package com.androidapp.tobeacontinue.Todolist;

import com.androidapp.tobeacontinue.Note;

public interface OnTabItemSelectedListener {
    public void onTabSelected(int position);
    public void showNoteWriteFragment(Note item);
}
