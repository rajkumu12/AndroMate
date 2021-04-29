package com.andromate.Constraints;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.andromate.Model.ActionModelList;
import com.andromate.Model.ConstraintsListModelList;
import com.andromate.R;

public class ConstraintsType {

    public static void showConfigurations(Context context, ConstraintsListModelList triggerlistmodel, String s) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.configurationui_action);
        dialog.setCancelable(true);

        TextView tv_tittle = dialog.findViewById(R.id.tv_tittle);
        tv_tittle.setText(s);

        dialog.show();

    }
}
