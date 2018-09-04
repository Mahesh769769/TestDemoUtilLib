package mart.boon.com.mylibrary.libutils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import mart.boon.com.mylibrary.R;


public class Utility {
    static ProgressDialog mLoadingDialog;
   static AlertDialog mErrorDialog;

    public static void displayLoadingDialog(boolean isCancellable, Context context) {
try
{
    dismissLoadingDialog();
    mLoadingDialog = new ProgressDialog(context);
    mLoadingDialog.setTitle(R.string.loading);
    mLoadingDialog.setMessage(context.getString(R.string.please_wait));
    mLoadingDialog.setIndeterminate(true);
    mLoadingDialog.setCancelable(isCancellable);
    if(context!=null) {
        mLoadingDialog.show();
    }
}
catch (Exception e)
{
    e.printStackTrace();
}
    }

    public static void dismissLoadingDialog() {
        try {
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void displayErrorDialog(String  title,  String content,Context context) {
        mErrorDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setIcon(ContextCompat.getDrawable(context, R.drawable.ic_error_24dp))
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


}
