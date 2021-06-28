package com.landmarkguideapp.diiscovery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter
{
    private final Activity mContent;
    List<UserAccount> userAccountList;

    public ListAdapter(Activity mContent, List<UserAccount> userAccountList) {
        super(mContent, R.layout.list_credentials, userAccountList);
        this.mContent = mContent;
        this.userAccountList = userAccountList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContent.getLayoutInflater();
        View ListCredentialsView = inflater.inflate(R.layout.list_credentials, null,true);

        TextView tvListFirstName = ListCredentialsView.findViewById(R.id.textView_ListFirstName);
        TextView tvListLastName = ListCredentialsView.findViewById(R.id.textView_ListLastName);
        TextView tvListEmailAddress = ListCredentialsView.findViewById(R.id.textView_ListEmailAddress);

        UserAccount userAccount = userAccountList.get(position);

        tvListFirstName.setText(userAccount.getFirstName());
        tvListLastName.setText(userAccount.getLastName());
        tvListEmailAddress.setText(userAccount.getEmailAddress());

        return ListCredentialsView;
    }
}
