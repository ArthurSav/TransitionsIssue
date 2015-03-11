package test.test.com.transitionsissue;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transitions.everywhere.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.v1)
    RelativeLayout v1;

    @InjectView(R.id.v2)
    RelativeLayout v2;

    @InjectView(R.id.v3)
    RelativeLayout v3;

    @InjectView(R.id.vRoot)
    RelativeLayout vRoot;


    boolean isPreviousBackport = false;



    @OnClick(R.id.button)
    public void button(){

        if (!isPreviousBackport) restoreVisibilty(v1,v2);

        //Transition done by the transitions-everywhere lib
        TransitionManager.beginDelayedTransition(vRoot);
        toggleVisibility(v1, v2);

        isPreviousBackport = true;
    }


    @OnClick(R.id.button2)
    public void button2(){

        if (isPreviousBackport) restoreVisibilty(v1,v2);

        //Transition done by the android api
        android.transition.TransitionManager.beginDelayedTransition(vRoot);
        toggleVisibility(v1, v2);

        isPreviousBackport = false;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    /**
     * Toggle view visibility
     * @param views
     */
    private  void toggleVisibility(View... views) {
        for (View view : views) {
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.GONE : View.VISIBLE);
        }
    }

    private void restoreVisibilty(View... views){
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

}
