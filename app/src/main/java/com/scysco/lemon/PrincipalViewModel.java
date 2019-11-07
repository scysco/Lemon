package com.scysco.lemon;

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

    public PrincipalViewModel(){
        int def_height = (int)(PrincipalActivity.density*60);
        tvTest.set(0);
        pnlHomeStore_Height.set(def_height);
        pnlHomeTasks_Height.set(def_height);
        pnlHomeMessages_Height.set(def_height);
        pnlConfigFavorites_Height.set(def_height);
        pnlConfigLocal_Height.set(def_height);
        pnlConfigSession_Height.set(def_height);
    }

}
