package mart.boon.com.mylibrary.libutils;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


public class SnackbarUtils {
    public static void disableDismissSwipe(Snackbar snackbar) {
        final Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup.LayoutParams lp = layout.getLayoutParams();
                if (lp instanceof CoordinatorLayout.LayoutParams) {
                    ((CoordinatorLayout.LayoutParams) lp).setBehavior(new DisableSwipeBehavior());
                    layout.setLayoutParams(lp);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    //noinspection deprecation
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    private static class DisableSwipeBehavior extends SwipeDismissBehavior<Snackbar.SnackbarLayout> {
        @Override
        public boolean canSwipeDismissView(@NonNull View view) {
            return false;
        }
    }
}
