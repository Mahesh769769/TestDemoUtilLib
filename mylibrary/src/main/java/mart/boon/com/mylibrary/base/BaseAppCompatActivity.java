package mart.boon.com.mylibrary.base;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import mart.boon.com.mylibrary.BuildConfig;
import mart.boon.com.mylibrary.R;
import mart.boon.com.mylibrary.libutils.NetworkConnectionUtil;
import mart.boon.com.mylibrary.libutils.SnackbarUtils;
import mart.boon.com.mylibrary.libutils.StandardUtil;
import mart.boon.com.mylibrary.libutils.Utility;


public class BaseAppCompatActivity extends AppCompatActivity {
    private String mTag = null;
    private ProgressDialog mLoadingDialog;
    private AlertDialog mErrorDialog;
    private Snackbar mSnackbarLoading;
    public static BaseAppCompatActivity baseAppCompatActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseAppCompatActivity = this;
    }

    public FragmentManager getFm (){
        return getSupportFragmentManager();
    }

    public BaseSupportFragment getLatestFragment (int id) {
        android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentById(id);
        if (fragment != null && fragment instanceof BaseSupportFragment) {
            return ((BaseSupportFragment) fragment);
        }
        return null;
    }
    @Override
    protected void onResume() {
        super.onResume();
        baseAppCompatActivity = this;
    }

    public void StartActivity(Class aClass, Bundle bundle) {
        Intent intent = new Intent(getBaseContext(), aClass);
        intent.putExtras(bundle);
        startActivity(intent);
//        startActivitywithAnnimation(getBaseContext(),intent,false);
    }

    public void StartActivityWithFinish(Class aClass) {
        Intent intent = new Intent(getBaseContext(), aClass);
        startActivity(intent);
        finish();
    }

    public void StartActivityWithAllFinish(Class aClass) {
        Intent intent = new Intent(getBaseContext(), aClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    public void startActivitywithAnnimation(Context context, Class<?> className, boolean clearAllAct) {
        Intent intent = new Intent(context, className);
        if (clearAllAct) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
//          finish();
    }

    public void startActivitywithAnnimation(Context context, Intent intent, boolean clearAllAct) {

        if (clearAllAct) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
//          finish();
    }

    public void install(Class<?> currentClass) {
        mTag = currentClass.getSimpleName();
    }

    public void displayShortToast(String message) {
        Toast.makeText(BaseAppCompatActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void displayLongToast(String message) {
        Toast.makeText(BaseAppCompatActivity.this, message, Toast.LENGTH_LONG).show();
    }

    public void displayShortToast(@StringRes int stringResId) {
        Toast.makeText(BaseAppCompatActivity.this, getString(stringResId), Toast.LENGTH_SHORT).show();
    }

    public void displayLongToast(@StringRes int stringResId) {
        Toast.makeText(BaseAppCompatActivity.this, getString(stringResId), Toast.LENGTH_LONG).show();
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


    @SuppressLint("LongLogTag")
    public void printLog(String message) {
        if (BuildConfig.DEBUG) {
            if (mTag != null) {
                Log.e(mTag, message);
            } else {
                Log.e(BaseAppCompatActivity.class.getSimpleName(), message);
            }
        }
    }

    @SuppressLint("LongLogTag")
    public void printLog(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    @SuppressLint("LongLogTag")
    public void printError(String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            if (mTag != null) {
                Log.e(mTag, message, throwable);
            } else {
                Log.e(BaseAppCompatActivity.class.getSimpleName(), message, throwable);
            }
        }
    }

    @SuppressLint("LongLogTag")
    public void printError(String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message, throwable);
        }
    }

    public void startActivity(Class<?> className) {
        StandardUtil.startActivity(this, className);
    }

    public void startActivityAllStatClear(Class<?> className) {
        StandardUtil.startActivityAllStackClear(this, className);
    }

    public void startActivity(Class<?> className, Bundle args) {
        StandardUtil.startActivity(this, className, args);
    }

    /**
     * <p>Start an activity as a new task and clear all the activities before it, if any exists.</p>
     *
     * @param className
     */
    public void startActivityAsNewTask(Class<?> className) {
        int intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        StandardUtil.startActivity(this, className, intentFlags);
    }

    /**
     * <p>Start an activity as a new task and clear all the activities before it, if any exists.</p>
     *
     * @param className
     * @param args
     */
    public void startActivityAsNewTask(Class<?> className, Bundle args) {
        int intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        StandardUtil.startActivity(this, className, args, intentFlags);
    }

    public void startActivityAsNewTaskWithBundle(Class<?> className, Bundle args) {
        int intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        StandardUtil.startActivity(this, className, intentFlags, args);
    }

    public void setFragmentWithoutBack(int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragmentWithoutBackStack(this, viewResourceId, fragment, withAnimation);
    }

    public void setFragment(int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragment(this, viewResourceId, fragment, withAnimation);
    }

    public void addFragment(int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.addFragment(this, viewResourceId, fragment, withAnimation);
    }
 public void addFragmentWithOutBackStack(int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.addFragmentWithOutBackStack(this, viewResourceId, fragment, withAnimation);
    }

    public void setFragmentWithoutBackStack(int viewResourceId, Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragmentWithoutBackStack(this, viewResourceId, fragment, withAnimation);
    }

    public void setFragmentWithoutBackStack(int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragmentWithoutBackStack(this, viewResourceId, fragment, withAnimation);
    }

    public void displayLoadingDialog(boolean isCancellable,Context context) {
/*       mLoadingDialog = new ProgressDialog(this);
         mLoadingDialog.setTitle(R.string.loading);
        mLoadingDialog.setMessage(getString(R.string.please_wait));
        mLoadingDialog.setIndeterminate(true);
        mLoadingDialog.setCancelable(isCancellable);
        mLoadingDialog.show();
    }*/
        Utility.displayLoadingDialog(isCancellable,context);
    }

    public void dismissLoadingDialog() {

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

    public void displayLoadingSnackbar(View view, String content) {
        mSnackbarLoading = Snackbar.make(view, content, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(this);
        snackbarLayout.addView(progressBar);
        mSnackbarLoading.show();
    }

    public void displayLoadingSnackbar(View view, @StringRes int contentRes) {
        mSnackbarLoading = Snackbar.make(view, contentRes, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
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
        ProgressBar progressBar = new ProgressBar(this);
        snackbarLayout.addView(progressBar);
        mSnackbarLoading.show();
        SnackbarUtils.disableDismissSwipe(mSnackbarLoading);
    }

    public void displayFixedLoadingSnackbar(View view, @StringRes int contentRes) {
        mSnackbarLoading = Snackbar.make(view, contentRes, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
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

    public void displayErrorDialog(String title, String content) {
        mErrorDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(content)
                //.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_logo))
                .setCancelable(false)
                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void displayErrorDialog(@StringRes int titleRes, @StringRes int contentRes) {
        mErrorDialog = new AlertDialog.Builder(this)
                .setTitle(titleRes)
                .setMessage(contentRes)
                //.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_logo))
                .setCancelable(false)
                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void displayErrorDialog(String title, String content, final Runnable runnable) {
        mErrorDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(content)
                .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_error_24dp))
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
        mErrorDialog = new AlertDialog.Builder(this)
                .setTitle(titleRes)
                .setMessage(contentRes)
                .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_error_24dp))
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


    public void displayErrorDialog(String titleRes, @StringRes int contentRes) {
        mErrorDialog = new AlertDialog.Builder(this)
                .setTitle(titleRes)
                .setMessage(contentRes)
                .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_error_24dp))
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

    public void dismissErrorDialog() {
        if (mErrorDialog != null) {
            mErrorDialog.dismiss();
        }
    }

    public boolean isNetworkAvailable() {
        if (NetworkConnectionUtil.isNetworkAvailable(this)) {
            return true;
        } else {
            NetworkConnectionUtil.showNetworkUnavailableDialog(this);
            return false;
        }
    }

    public boolean isNetworkAvailable(View view) {
        if (NetworkConnectionUtil.isNetworkAvailable(this)) {
            return true;
        } else {
            NetworkConnectionUtil.showNetworkUnavailableSnackbar(this, view);
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

    public void clearFragmentBackStack(){
        FragmentManager fm=getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
