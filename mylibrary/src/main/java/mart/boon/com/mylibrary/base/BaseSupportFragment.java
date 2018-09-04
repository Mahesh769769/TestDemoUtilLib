package mart.boon.com.mylibrary.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import mart.boon.com.mylibrary.R;
import mart.boon.com.mylibrary.libutils.NetworkConnectionUtil;
import mart.boon.com.mylibrary.libutils.SnackbarUtils;
import mart.boon.com.mylibrary.libutils.StandardUtil;
import mart.boon.com.mylibrary.libutils.Utility;

public class BaseSupportFragment extends Fragment {
    private String mTag = null;
    private ProgressDialog mLoadingDialog;
    private AlertDialog mErrorDialog;
    private Snackbar mSnackbarLoading;


    public interface BackCallBackOnFragment {
         void backCallBackOnFragment();

    }

    public interface OnFragmentInteractionListener {
        void showBackButton();

        void hideBackButton();

        void updateTitle(String title, boolean isSetTiele);

        void changeToolbarIcon(@DrawableRes int drawableRes, Runnable runnable);
    }

    public void install(Class<?> currentClass) {
        mTag = currentClass.getSimpleName();
    }

    public void displayShortToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void displayLongToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void displayShortToast(@StringRes int stringResId) {
        Toast.makeText(getActivity(), getString(stringResId), Toast.LENGTH_SHORT).show();
    }

    public void displayLongToast(@StringRes int stringResId) {
        Toast.makeText(getActivity(), getString(stringResId), Toast.LENGTH_LONG).show();
    }

    public void displayShortSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public void displayLongSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void displayShortSnackbar(View view, @StringRes int stringResId) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_LONG).show();
    }

    public void displayLongSnackbar(View view, @StringRes int stringResId) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_LONG).show();
    }

    public void displayIndefiniteSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).show();
    }

    public void displayIndefiniteSnackbar(View view, @StringRes int stringResId) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_INDEFINITE).show();
    }

    public void displayErrorSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    public void displayErrorSnackbar(View view, @StringRes int stringResId) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    public void displayErrorSnackbar(View view, String message, String actionMessage, final Runnable action) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionMessage, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.run();
                    }
                })
                .show();
    }

    public void displayErrorSnackbar(View view, @StringRes int stringResId, @StringRes int actionMessage, final Runnable action) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionMessage, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.run();
                    }
                })
                .show();
    }

    @SuppressLint("LongLogTag")
    public void printLog(String message) {
        if (mTag != null) {
            Log.e(mTag, message);
        } else {
            Log.e(BaseSupportFragment.class.getSimpleName(), message);
        }
    }

    @SuppressLint("LongLogTag")
    public void printLog(String tag, String message) {
        Log.e(tag, message);
    }

    public void startActivity(Class<?> className) {
        StandardUtil.startActivity(getActivity(), className);
    }

    public void startActivity(Class<?> className, Bundle args) {
        StandardUtil.startActivity(getActivity(), className, args);
    }

    /**
     * <p>Start an activity as a new task and clear all the activities before it, if any exists.</p>
     *
     * @param className
     */
    public void startActivityAsNewTask(Class<?> className) {
        int intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        StandardUtil.startActivity(getActivity(), className, intentFlags);
    }

    /**
     * <p>Start an activity as a new task and clear all the activities before it, if any exists.</p>
     *
     * @param className
     * @param args
     */
    public void startActivityAsNewTask(Class<?> className, Bundle args) {
        int intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        StandardUtil.startActivity(getActivity(), className, args, intentFlags);
    }


    public void setFragment(int viewResourceId, Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragment(getActivity(), viewResourceId, fragment, withAnimation);

    }

    public void setFragmentWithoutBackStack(int viewResourceId, Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragmentWithoutBackStack(getActivity(), viewResourceId, fragment, withAnimation);

    }

    public void setChildFragment(int viewResourceId, Fragment fragment, boolean withAnimation) {
        StandardUtil.setChildFragment(this, viewResourceId, fragment, withAnimation);
    }
    public void addFragment(int viewResourceId, Fragment fragment, boolean withAnimation) {
        StandardUtil.addFragment(getActivity(), viewResourceId, fragment, withAnimation);
    }
    public void addFragmentWithOutBackStack(int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.addFragmentWithOutBackStack(getActivity(), viewResourceId, fragment, withAnimation);
    }


    public void displayLoadingDialog(boolean isCancellable, Context context) {
        Utility.displayLoadingDialog(isCancellable,context);
    }

    public void dismissLoadingDialog() {
      /*  if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }*/
        Utility.dismissLoadingDialog();
    }

    public void updateLoadingDialogStatus(String title, String content) {
        if (mLoadingDialog != null) {
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                mLoadingDialog.setTitle(title);
                mLoadingDialog.setMessage(content);
            } else if (!TextUtils.isEmpty(title)) {
                mLoadingDialog.setTitle(title);
            } else if (!TextUtils.isEmpty(content)) {
                mLoadingDialog.setMessage(content);
            }
        }
    }

    public ProgressDialog getLoadingDialog() {
        return mLoadingDialog;
    }

    public void displayErrorDialog(String title, String content) {
        mErrorDialog = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(content)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_logo))
                .setCancelable(false)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void displayErrorDialog(@StringRes int titleRes, @StringRes int contentRes) {
        mErrorDialog = new AlertDialog.Builder(getActivity())
                .setTitle(titleRes)
                .setMessage(contentRes)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_logo))
                .setCancelable(false)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void displayErrorDialog(String title, String content, final Runnable runnable) {
        mErrorDialog = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(content)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_logo))
                .setCancelable(false)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        runnable.run();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void displayErrorDialog(@StringRes int titleRes, @StringRes int contentRes, final Runnable runnable) {
        mErrorDialog = new AlertDialog.Builder(getActivity())
                .setTitle(titleRes)
                .setMessage(contentRes)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_logo))
                .setCancelable(false)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        runnable.run();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void dismissErrorDialog() {
        if (mErrorDialog != null) {

            mErrorDialog.dismiss();
        }
    }

    public void displayLoadingSnackbar(View view, String content) {
        mSnackbarLoading = Snackbar.make(view, content, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(getActivity());
        snackbarLayout.addView(progressBar);
        mSnackbarLoading.show();
    }

    public void displayLoadingSnackbar(View view, @StringRes int contentRes) {
        mSnackbarLoading = Snackbar.make(view, contentRes, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleSmall);
        snackbarLayout.addView(progressBar);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) progressBar.getLayoutParams();
        //layoutParams.weight = 1.0f;
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        progressBar.setLayoutParams(layoutParams);
        mSnackbarLoading.show();
    }

    public void displayFixedLoadingSnackbar(View view, String content) {
        mSnackbarLoading = Snackbar.make(view, content, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(getActivity());
        snackbarLayout.addView(progressBar);
        mSnackbarLoading.show();
        SnackbarUtils.disableDismissSwipe(mSnackbarLoading);
    }

    public void displayFixedLoadingSnackbar(View view, @StringRes int contentRes) {
        mSnackbarLoading = Snackbar.make(view, contentRes, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleSmall);
        snackbarLayout.addView(progressBar);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) progressBar.getLayoutParams();
        //layoutParams.weight = 1.0f;
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        progressBar.setLayoutParams(layoutParams);
        mSnackbarLoading.show();
        SnackbarUtils.disableDismissSwipe(mSnackbarLoading);
    }

    public void dismissLoadingSnackbar() {
        if (mSnackbarLoading != null && mSnackbarLoading.isShown()) {
            mSnackbarLoading.dismiss();
        }
    }

    public boolean isNetworkAvailable() {
        if (NetworkConnectionUtil.isNetworkAvailable(getActivity())) {
            return true;
        } else {
            NetworkConnectionUtil.showNetworkUnavailableDialog(getActivity());
            return false;
        }
    }

    public boolean isNetworkAvailable(View view) {
        if (NetworkConnectionUtil.isNetworkAvailable(getActivity())) {
            return true;
        } else {
            NetworkConnectionUtil.showNetworkUnavailableSnackbar(getActivity(), view);
            return false;
        }
    }

    public void disableViews(View... views) {
        for (int i = 0; i < views.length; i++) {
            views[i].setEnabled(false);
        }
    }

    public void enableViews(View... views) {
        for (int i = 0; i < views.length; i++) {
            views[i].setEnabled(true);
        }
    }


    public void setFragmentWithBundle(int resourceView, Fragment fragment, boolean backStackFlag, Bundle bundle) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        if (backStackFlag)
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.add(resourceView, fragment);
        transaction.commit();
    }

    public void setFragmentadd(int viewResourceId, Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragmentadd(getActivity(), viewResourceId, fragment, withAnimation);
    }

    public void setFragmentaddnew(int viewResourceId, Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragmentaddnew(getActivity(), viewResourceId, fragment, withAnimation);
    }

   /* public interface FrequencyCallBack{
      public   void onRefreshFrequency(ResFrequencyListBean listBean);
    }*/
}
