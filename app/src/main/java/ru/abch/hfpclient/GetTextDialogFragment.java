/*
 * Copyright (c) 2013, The Linux Foundation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *        * Redistributions of source code must retain the above copyright
 *            notice, this list of conditions and the following disclaimer.
 *        * Redistributions in binary form must reproduce the above copyright
 *            notice, this list of conditions and the following disclaimer in the
 *            documentation and/or other materials provided with the distribution.
 *        * Neither the name of The Linux Foundation nor
 *            the names of its contributors may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NON-INFRINGEMENT ARE DISCLAIMED.    IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package ru.abch.hfpclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;



public class GetTextDialogFragment extends DialogFragment {

    private GetTextDialogListener mListener;

    public interface GetTextDialogListener {
        public void onGetTextDialogPositive(DialogFragment dialog, String text);
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.string_picker_dialog, null))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edt = (EditText) GetTextDialogFragment.this.getDialog()
                                .findViewById(R.id.text);
                        mListener.onGetTextDialogPositive(GetTextDialogFragment.this, edt.getText()
                                .toString());
                    }
                })
                .setNegativeButton(android.R.string.cancel, null);

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (GetTextDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement GetTextDialogListener");
        }
    }
}
