package es.ulpgc.eite.da.basicquizlab.cheat;

import java.lang.ref.WeakReference;

public class CheatPresenter implements CheatContract.Presenter {

    private final WeakReference<CheatContract.View> view;
    private  final  CheatContract.Model model;

    public CheatPresenter(
            WeakReference<CheatContract.View> view,
            CheatContract.Model model){
        this.model = model;
        this.view = view;
    }

}
