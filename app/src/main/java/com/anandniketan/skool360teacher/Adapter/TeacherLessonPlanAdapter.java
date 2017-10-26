package com.anandniketan.skool360teacher.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Models.LessonPlanResponse.LessonDatum;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class TeacherLessonPlanAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<LessonDatum> arrayList = new ArrayList<>();
    private ProgressDialog progressDialog = null;
    FragmentManager activity;

    // Constructor
    public TeacherLessonPlanAdapter(Context c, ArrayList<LessonDatum> arrayList, FragmentManager activity) {
        mContext = c;
        this.arrayList = arrayList;
        this.activity = activity;
    }

    private class ViewHolder {
        TextView chapter_no_txt, chapter_name_txt;
        ImageView pdf_img, word_img;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View view = null;
        convertView = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            final LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row_lessonplan, null);

            viewHolder.chapter_no_txt = (TextView) convertView.findViewById(R.id.chapter_no_txt);
            viewHolder.chapter_name_txt = (TextView) convertView.findViewById(R.id.chapter_name_txt);
            viewHolder.pdf_img = (ImageView) convertView.findViewById(R.id.pdf_img);
            viewHolder.word_img = (ImageView) convertView.findViewById(R.id.word_img);


            try {
                viewHolder.chapter_no_txt.setText(Integer.toString(arrayList.get(position).getChapterNo()));
                viewHolder.chapter_name_txt.setText(Html.fromHtml(arrayList.get(position).getChapterName()));
                viewHolder.pdf_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String extStorageDirectory = "";
                        String saveFilePath = null;
                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        Log.d("date", "" + currentTime);
                        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

                        if (isSDSupportedDevice && isSDPresent) {
                            // yes SD-card is present
                            Utility.ping(mContext, "present");
                            extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                            saveFilePath = String.valueOf(new File(extStorageDirectory, Utility.parentFolderName + "/" + Utility.childAnnouncementFolderName + "/" + currentTime).getPath());

                        } else {
                            // Sorry
                            Utility.ping(mContext, "notpresent");

                            File cDir = mContext.getExternalFilesDir(null);
                            saveFilePath = String.valueOf(new File(cDir.getPath() + "/" + currentTime + "Code.pdf"));
                            Log.d("path", saveFilePath);
                        }

                        Log.d("path", extStorageDirectory);

                        String fileURL = "http://192.168.1.9:8085/Backend/lessonplanpdf.aspx?ID=" + arrayList.get(position).getID();
                        Log.d("URL", fileURL);
                        Ion.with(mContext)
                                .load(fileURL)  // download url
                                .write(new File(saveFilePath))  // File no path
                                .setCallback(new FutureCallback<File>() {
                                    @Override
                                    public void onCompleted(Exception e, File file) {

                                        if (file.length() > 0) {
                                            String file1 = file.getPath();
                                            Log.d("file11", file1);
                                        } else {
                                            Utility.ping(mContext, e.getMessage());
                                        }
                                    }
//                                        Intent in = new Intent(this,webviewActivity.class);
//                                        in.putExtra(pathpasskravno) // ahiya tari file no path
//                                        startActivity(in);


                                });

                    }


                });

                viewHolder.word_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String wordfile = "http://192.168.1.9:8085/Backend/LessonPlanWord.aspx?ID=" + arrayList.get(position).getID();
                        String extStorageDirectory = "";
                        String saveFilePath = null;
                        long currentTime = Calendar.getInstance().getTimeInMillis();
                        Log.d("date", "" + currentTime);
                        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
                        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

                        if (isSDSupportedDevice && isSDPresent) {
                            // yes SD-card is present
                            Utility.ping(mContext, "present");
                            extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                            saveFilePath = String.valueOf(new File(extStorageDirectory, Utility.parentFolderName + "/" + Utility.childAnnouncementFolderName + "/" + currentTime).getPath());

                        } else {
                            // Sorry
                            Utility.ping(mContext, "notpresent");

                            File cDir = mContext.getExternalFilesDir(null);
                            saveFilePath = String.valueOf(new File(cDir.getPath() + "/" + currentTime + "Word.docx"));
                            Log.d("path", saveFilePath);
                        }

                        Log.d("path", extStorageDirectory);


                        Log.d("URL", wordfile);
                        Ion.with(mContext)
                                .load(wordfile)  // download url
                                .write(new File(saveFilePath))  // File no path
                                .setCallback(new FutureCallback<File>() {
                                    @Override
                                    public void onCompleted(Exception e, File file) {
//                                        Utility.ping(mContext, e.getMessage());
//                                        System.out.print(e.getMessage());
//                                        Intent in = new Intent(this,webviewActivity.class);
//                                        in.putExtra(pathpasskravno) // ahiya tari file no path
//                                        startActivity(in);
                                    }
                                });

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    public void downloadPDF(final String pdfURL) {
        final String fileName = pdfURL.substring(pdfURL.lastIndexOf('/') + 1);

        if (Utility.isFileExists(fileName, "announcement")) {
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            Utility.pong(mContext, "File already exists at " + new File(extStorageDirectory, Utility.parentFolderName + "/" + Utility.childAnnouncementFolderName + "/" + fileName).getPath());

        } else {
            progressDialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Utility.downloadFile(pdfURL, fileName, "announcement");
//                    .runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
                    progressDialog.dismiss();
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    Utility.pong(mContext, "File download complete at " + new File(extStorageDirectory, Utility.parentFolderName + "/" + Utility.childAnnouncementFolderName + "/" + fileName).getPath());
//                        }
//                    });
                }


            }).start();
        }
    }
}


