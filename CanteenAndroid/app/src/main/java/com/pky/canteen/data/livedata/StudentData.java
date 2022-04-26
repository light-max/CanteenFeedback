package com.pky.canteen.data.livedata;

import androidx.lifecycle.MutableLiveData;

import com.pky.canteen.data.result.Student;

public class StudentData extends MutableLiveData<Student> {
    private static StudentData instance;

    public static StudentData getInstance() {
        if (instance == null) {
            instance = new StudentData();
        }
        return instance;
    }

    public static StudentData newInstance() {
        instance = new StudentData();
        return instance;
    }
}
