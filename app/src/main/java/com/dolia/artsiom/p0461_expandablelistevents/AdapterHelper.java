package com.dolia.artsiom.p0461_expandablelistevents;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artsiom on 2/25/15.
 */
public class AdapterHelper {

    final String ATTR_GROUP_NAME = "groupName";
    final String ATTR_PHONE_NAME = "phoneName";

    // Groups names
    String[] groups = new String[] {"HTC", "Samsung", "LG"};

    // Elements names
    String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
    String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

    // Groups list
    ArrayList<Map<String, String>> groupData;

    // Elements list
    ArrayList<Map<String, String>> childDataItem;

    // Entire list
    ArrayList<ArrayList<Map<String, String>>> childData;

    // The list of attributes
    Map<String, String> m;

    Context ctx;

    AdapterHelper(Context _ctx){
        ctx = _ctx;
    }

    SimpleExpandableListAdapter adapter;

    SimpleExpandableListAdapter getAdapter(){

        //fill the groups list from groups names
        groupData = new ArrayList<Map<String , String>>();

        for (String group : groups){
            m = new HashMap<String, String>();
            m.put(ATTR_GROUP_NAME, group);
            groupData.add(m);
        }

        //List of group attributes
        String groupFrom[] = new String[] {ATTR_GROUP_NAME};

        //List of elements ID to put attributes
        int groupTo[] = new int[] {android.R.id.text1};

        //List for lists of elements
        childData = new ArrayList<ArrayList<Map<String,String>>>();

        //Elements for 1st group
        childDataItem = new ArrayList<Map<String, String>>();

        //Fill attributes for each element
        for(String phone : phonesHTC){

            m = new HashMap<String , String >();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }

        //Add to the list of lists
        childData.add(childDataItem);

        //Elements for 2nd group
        childDataItem = new ArrayList<Map<String, String>>();

        //Fill attributes for each element
        for(String phone : phonesSams){

            m = new HashMap<String , String >();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }

        //Add to the list of lists
        childData.add(childDataItem);

        //Elements for 3rd group
        childDataItem = new ArrayList<Map<String, String>>();

        //Fill attributes for each element
        for(String phone : phonesLG){

            m = new HashMap<String , String >();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }

        //Add to the list of lists
        childData.add(childDataItem);

        //List of attributes
        String childFrom[] = new String[] {ATTR_PHONE_NAME};

        //list of elements ID to put attributes
        int childTo[] = new int[] {android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo
        );

        return adapter;
    }


    String getGroupText (int groupPos){
        return ((Map<String , String >)(adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
    }


    String getChildText (int groupPos, int childPos){
        return ((Map<String , String >)(adapter.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
    }


    String getGroupChildText(int groupPos, int childPos){
        return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
    }
}
