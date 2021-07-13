package com.activator.quantsapp;

import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ConfigStrat implements TabLayoutMediator.TabConfigurationStrategy {
    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        if(position == 0){
            tab.setText("API");
            tab.setIcon(R.drawable.ic_baseline_format_list_bulleted_24);

        }
        else
            tab.setText("URL");
            tab.setIcon(R.drawable.ic_baseline_download_24);
    }
}
