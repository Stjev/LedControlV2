package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class StarViewModel extends ViewModel {
    private MutableLiveData<Integer> starCount;
    private MutableLiveData<Integer> starSpeed;

    public StarViewModel() {
        starCount = new MutableLiveData<>();
        starSpeed = new MutableLiveData<>();
        starCount.setValue(15); // Default value is 15
        starSpeed.setValue(50); // Default value is 50%
    }

    public LiveData<Integer> getStarCount() {
        return this.starCount;
    }

    public LiveData<Integer> getStarSpeed() {
        return this.starSpeed;
    }

    public void setStarCount(int starCount) {
        this.starCount.setValue(starCount);
    }

    public void setStarSpeed(int starSpeed) {
        this.starSpeed.setValue(starSpeed);
    }
}
