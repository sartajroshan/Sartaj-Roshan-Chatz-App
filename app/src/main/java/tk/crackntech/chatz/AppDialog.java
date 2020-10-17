package tk.crackntech.chatz;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.marcoscg.dialogsheet.DialogSheet;

public class AppDialog {

Context mContext;

    public AppDialog(Context mContext) {
        this.mContext = mContext;
    }
    public DialogSheet noInternet(){
        final DialogSheet sheet = new DialogSheet(mContext);
        sheet.setTitle(R.string.app_name)
                .setMessage("No Internet Connection")
                .setColoredNavigationBar(true)
                .setCancelable(false)
                .setRoundedCorners(true)
                .setBackgroundColorRes(R.color.colorPrimary)
                .setPositiveButton("RETRY", new DialogSheet.OnPositiveClickListener() {
                    @Override
                    public void onClick(View v) {
                       sheet.dismiss();
                    }
                })

                .setButtonsColorRes(R.color.colorPrimary);
               // .setIcon(android.R.drawable.stat_notify_error);
        return sheet;

    }
}
