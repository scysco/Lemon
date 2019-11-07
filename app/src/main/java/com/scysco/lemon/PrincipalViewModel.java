package com.scysco.lemon;

import android.view.View;
import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;


public class PrincipalViewModel extends ViewModel {

    public ObservableField<Integer> tvTest = new ObservableField<>();
    public ObservableField<Integer> pnlHomeStore_Height = new ObservableField<>();
    public ObservableField<Integer> pnlHomeTasks_Height = new ObservableField<>();
    public ObservableField<Integer> pnlHomeMessages_Height = new ObservableField<>();
    public ObservableField<Integer> pnlConfigFavorites_Height = new ObservableField<>();
    public ObservableField<Integer> pnlConfigLocal_Height = new ObservableField<>();
    public ObservableField<Integer> pnlConfigSession_Height = new ObservableField<>();
    public ObservableField<String> pnlConfigStatus = new ObservableField<>();
    public ObservableField<String> pnlHomeStatus = new ObservableField<>();



    public PrincipalViewModel(){
        tvTest.set(0);
        int def_height = (int)(PrincipalActivity.density*60);
        pnlHomeStore_Height.set(def_height);
        pnlHomeTasks_Height.set(def_height);
        pnlHomeMessages_Height.set(def_height);
        pnlConfigFavorites_Height.set(def_height);
        pnlConfigLocal_Height.set(def_height);
        pnlConfigSession_Height.set(def_height);
        pnlConfigStatus.set("gone");
        pnlHomeStatus.set("gone");
    }

    @BindingAdapter({"android:layout_height"})
    public static void setLayoutHeight(View view, float height) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.height = (int) height;
        view.setLayoutParams(layoutParams);
    }
}
