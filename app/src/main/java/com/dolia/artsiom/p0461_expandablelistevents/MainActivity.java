package com.dolia.artsiom.p0461_expandablelistevents;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    final String LOG_TAG = "myLog";

    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        // create adapter
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        // element pressed
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Log.d(LOG_TAG,
                        "onChildClick groupPosition = " + groupPosition +
                        " childPosition = " + childPosition +
                        " id = " + id);

                tvInfo.setText(ah.getGroupChildText(groupPosition, childPosition));

                return false;
            }
        });

        // group tapped
        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Log.d(LOG_TAG,
                        "onGroupClick groupPosition = " + groupPosition +
                        " id = " + id);
                tvInfo.setText("Group " + ah.getGroupText(groupPosition) + " is tapped");

                if(groupPosition == 1){
                    return true;
                }
                return false;
            }
        });

        // group collapse
        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

                Log.d(LOG_TAG,
                        "Group collapsed, groupPosition = " + groupPosition);
                tvInfo.setText("Group " + ah.getGroupText(groupPosition) + " is collapsed");
            }
        });

        // group expand
        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d(LOG_TAG,
                        "Group expanded, groupPosition = " + groupPosition);
                tvInfo.setText("Group " + ah.getGroupText(groupPosition) + " is expanded");

            }
        });

        //expand group 2
        elvMain.expandGroup(2);
    }
}
