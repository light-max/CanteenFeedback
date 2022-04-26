package com.pky.canteen.data.livedata;

import androidx.lifecycle.MutableLiveData;

import com.pky.canteen.data.result.Teacher;

public class TeacherData extends MutableLiveData<Teacher> {
    private static TeacherData instance;

    public static TeacherData getInstance() {
        if (instance == null) {
            instance = new TeacherData();
        }
        return instance;
    }

    public static TeacherData newInstance(){
        instance = new TeacherData();
        return instance;
    }
}
