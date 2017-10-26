package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.anandniketan.skool360teacher.AsyncTasks.PTMDeleteMeetingAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.PTMTeacherStudentGetDetailAsyncTask;
import com.anandniketan.skool360teacher.Models.MainPtmSentDeleteResponse;
import com.anandniketan.skool360teacher.Models.PTMInboxResponse.FinalArrayInbox;
import com.anandniketan.skool360teacher.Models.PTMInboxResponse.MainPtmInboxResponse;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.util.HashMap;
import java.util.List;

import static android.app.PendingIntent.getActivity;

/**
 * Created by admsandroid on 10/25/2017.
 */

public class ExpandableListAdapterSent extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<FinalArrayInbox>> listChildData;
    private ProgressDialog progressDialog = null;
    private PTMDeleteMeetingAsyncTask ptmDeleteMeetingAsyncTask = null;
    private MainPtmSentDeleteResponse response;
    private PTMTeacherStudentGetDetailAsyncTask ptmTeacherStudentGetDetailAsyncTask = null;
    MainPtmInboxResponse inboxresponse;

    ExpandableListAdapterSent expandableListAdapterSent;

    public ExpandableListAdapterSent(Context context, List<String> listDataHeader,
                                     HashMap<String, List<FinalArrayInbox>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.listChildData = listChildData;
    }

    @Override
    public List<FinalArrayInbox> getChild(int groupPosition, int childPosititon) {
        return this.listChildData.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final List<FinalArrayInbox> childData = getChild(groupPosition, 0);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_sent, null);
        }


        TextView txtSubject;
        final Button delete_btn;
        txtSubject = (TextView) convertView.findViewById(R.id.txtSubject);
        delete_btn = (Button) convertView.findViewById(R.id.delete_btn);

        txtSubject.setText(childData.get(childPosition).getDescription());
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                progressDialog = new ProgressDialog(_context);
//                progressDialog.setMessage("Please Wait...");
//                progressDialog.setCancelable(false);
//                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put("MessageID", childData.get(childPosition).getMessageID());

                            ptmDeleteMeetingAsyncTask = new PTMDeleteMeetingAsyncTask(params);
                            response = ptmDeleteMeetingAsyncTask.execute().get();
                            this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
//                                    if (response.getFinalArray().size() == 0) {
                                    Utility.ping(_context, "Delete Message.");
                                    expandableListAdapterSent.notifyDataSetChanged();
//                                    } else {
//                                        progressDialog.dismiss();
//                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    private void runOnUiThread(Runnable runnable) {
                    }


                }).start();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChildData.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String[] headerTitle = getGroup(groupPosition).toString().split("\\|");

        String headerTitle1 = headerTitle[0];
        String headerTitle2 = headerTitle[1];
        String headerTitle3 = headerTitle[2];

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_inbox, null);
        }
        TextView Student_name_inbox_txt, date_inbox_txt, subject_inbox_txt, view_inbox_txt;
        Student_name_inbox_txt = (TextView) convertView.findViewById(R.id.Student_name_inbox_txt);
        date_inbox_txt = (TextView) convertView.findViewById(R.id.date_inbox_txt);
        subject_inbox_txt = (TextView) convertView.findViewById(R.id.subject_inbox_txt);
        view_inbox_txt = (TextView) convertView.findViewById(R.id.view_inbox_txt);

        Student_name_inbox_txt.setText(headerTitle1);
        date_inbox_txt.setText(headerTitle2);
        subject_inbox_txt.setText(headerTitle3);

        if (isExpanded) {
            view_inbox_txt.setTextColor(_context.getResources().getColor(R.color.present_header));
        } else {
            view_inbox_txt.setTextColor(_context.getResources().getColor(R.color.absent_header));
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void getSentData() {
        if (Utility.isNetworkConnected(_context)) {
            progressDialog = new ProgressDialog(_context);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("UserID", Utility.getPref(_context, "StaffID"));
                        params.put("UserType", "staff");
                        params.put("MessgaeType", "Sent");
                        ptmTeacherStudentGetDetailAsyncTask = new PTMTeacherStudentGetDetailAsyncTask(params);
                        inboxresponse = ptmTeacherStudentGetDetailAsyncTask.execute().get();
                        this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (response.getFinalArray().size() > 0) {

                                } else {
                                    progressDialog.dismiss();

                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                private void runOnUiThread(Runnable runnable) {
                }
            }).start();
        } else {
            Utility.ping(_context, "Network not available");
        }
    }
}



