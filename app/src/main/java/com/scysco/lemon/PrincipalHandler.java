package com.scysco.lemon;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.databinding.ObservableField;

public class PrincipalHandler {
    Context context;
    private int dec = 0;
    private int inc = 1;

    public PrincipalHandler(Context context){
        this.context = context;
    }

    public void actionTest(ObservableField<Integer> i){
        i.set(i.get()+1);
    }

    public  void pnlButton(ObservableField<Boolean> stat,ObservableField<Boolean> stat2,ObservableField<Boolean> stat3){
        if (stat.get())stat.set(false);
        else{
            stat.set(true);
            stat2.set(false);
            stat3.set(false);
        }
    }

    public void animateListButton(View view,ObservableField<Integer> selected, ObservableField<Integer> other_1, ObservableField<Integer> other_2){
        int def_height = (int)(PrincipalActivity.density*60);

        if (other_1.get()!=def_height)
            animateHeight(view,other_1,dec);
        if (other_2.get()!=def_height)
            animateHeight(view,other_2,dec);

        animateHeight(view,selected,inc);
    }

    private void animateHeight(View view,ObservableField<Integer> height,int decorinc) {
        LinearLayout parent = (LinearLayout) view.getParent();
        float newHeight;
        if (decorinc==dec)
            newHeight =  (int)(PrincipalActivity.density*60);
        else
            newHeight =  parent.getHeight() - (120 * PrincipalActivity.density);

        ValueAnimator animator = ValueAnimator.ofInt(height.get(), (int) newHeight);
        animator.addUpdateListener(animation ->{
            height.set((Integer) animation.getAnimatedValue());
        });
        animator.setDuration(300);
        animator.start();
    }
    public void testProduct(){
        PrincipalActivity.addProduct();
    }
}
