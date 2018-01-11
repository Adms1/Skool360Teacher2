package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anandniketan.anandniketanskool360shilajTeacher.Activities.DashBoardActivity;
import com.anandniketan.anandniketanskool360shilajTeacher.BuildConfig;
import com.anandniketan.anandniketanskool360shilajTeacher.Models.LessonPlanResponse.LessonDatum;
import com.anandniketan.anandniketanskool360shilajTeacher.R;
import com.anandniketan.anandniketanskool360shilajTeacher.Utility.Utility;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by admsandroid on 10/13/2017.
 */

public class TeacherLessonPlanAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<LessonDatum> arrayList = new ArrayList<>();
    private ProgressDialog progressDialog = null;
    FragmentManager activity;
    private AlertDialog alertDialogAndroid = null;

    Button close_btn;

    File filepdf;
    Integer pageNumber = 0;
    String pdfFileName;
    String file1;


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
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
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
//                            Utility.ping(mContext, "notpresent");
//
                            File cDir = mContext.getExternalFilesDir(null);
                            saveFilePath = String.valueOf(new File(cDir.getPath() + "/" + currentTime + "Code.pdf"));
                            Log.d("path", saveFilePath);

                        }
//
                        Log.d("path", extStorageDirectory);

                        String fileURL = "http://103.8.216.132/Backend/lessonplanpdf.aspx?ID=" + arrayList.get(position).getID();
                        Log.d("URL", fileURL);
                        if (Utility.isNetworkConnected(mContext)) {
                            progressDialog = new ProgressDialog(mContext);
                            progressDialog.setMessage("Please Wait...");
                            progressDialog.setCancelable(false);
                            progressDialog.show();
                            Ion.with(mContext)
                                    .load(fileURL)  // download url
                                    .write(new File(saveFilePath))  // File no path
                                    .setCallback(new FutureCallback<File>() {
                                        //                                    @Override
                                        public void onCompleted(Exception e, File file) {

                                            if (file.length() > 0) {
                                                progressDialog.dismiss();
                                                Utility.ping(mContext, "Download complete.");
                                                file1 = file.getPath();
                                                filepdf = file.getAbsoluteFile();
                                                Log.d("file11", "" + filepdf);

                                                showCustomNotification();
                                            } else {
                                                progressDialog.dismiss();
                                                Utility.ping(mContext, "Something error");
                                            }
                                        }


                                    });
                        } else {
                            Utility.ping(mContext, "Network not available");
                        }
                    }
                });
                viewHolder.word_img.setOnClickListener(new View.OnClickListener() {
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
//                            Utility.ping(mContext, "present");
                            extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                            saveFilePath = String.valueOf(new File(extStorageDirectory, Utility.parentFolderName + "/" + Utility.childAnnouncementFolderName + "/" + currentTime).getPath());

                        } else {
                            // Sorry
//                            Utility.ping(mContext, "notpresent");

                            File cDir = mContext.getExternalFilesDir(null);
                            Log.d("*********", cDir.getPath());
                            saveFilePath = String.valueOf(new File(cDir.getPath() + "/" + currentTime + "word.doc"));
                            Log.d("path", saveFilePath);


                        }
//
                        Log.d("path", extStorageDirectory);

                        final String fileURL = "http://103.8.216.132/Backend/LessonPlanWord.aspx?ID=" + arrayList.get(position).getID();
                        Log.d("URL", fileURL);
                        if (Utility.isNetworkConnected(mContext)) {
                            progressDialog = new ProgressDialog(mContext);
                            progressDialog.setMessage("Please Wait...");
                            progressDialog.setCancelable(false);
                            progressDialog.show();
                            Ion.with(mContext)
                                    .load(fileURL)  // download url
                                    .write(new File(saveFilePath))  // File no path
                                    .setCallback(new FutureCallback<File>() {
                                        //                                    @Override
                                        public void onCompleted(Exception e, File file) {

                                            if (file.length() > 0) {
                                                progressDialog.dismiss();
                                                Utility.ping(mContext, "Download complete.");
                                                file1 = file.getPath();
                                                Log.d("file11", file1);
                                                filepdf = file.getAbsoluteFile();
                                                Log.d("file11", "" + filepdf);
                                                showCustomNotification();
                                            } else {
                                                progressDialog.dismiss();
                                                Utility.ping(mContext, "Something error");
                                            }
                                        }
                                    });
                        } else {
                            Utility.ping(mContext, "Network not available");
                        }
                    }
                });
            } catch (Exception e)

            {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    private void showCustomNotification() {
//        final int NOTIFICATION_ID = 1;
//        String ns = Context.NOTIFICATION_SERVICE;
////        final NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(ns);
//
//        int icon = R.mipmap.ic_launcher;
//        long when = System.currentTimeMillis();
//        Notification notification = new Notification(icon, mContext.getString(R.string.app_name), when);
//        Intent notificationIntent = new Intent();
//        notificationIntent.setAction(Intent.ACTION_VIEW);
//        File f1 = new File(file1);
//        Log.d("filepath", "" + f1);
//        notificationIntent.setDataAndType(Uri.fromFile(f1), "files/*");
//
////        notification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
////        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
////        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
////        notification.defaults |= Notification.DEFAULT_SOUND; // Sound
//
//
//        PendingIntent pIntent = PendingIntent.getActivity(mContext, 0,
//                notificationIntent, 0);
//
////        NotificationCompat.Builder mBuilder =
////                (NotificationCompat.Builder) new NotificationCompat.Builder(mContext)
////                        .setSmallIcon(R.drawable.downloading_updates)
////                        .setContentTitle("Downloading file")
////                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
////                        .setContentText(file1)
////                        .setContentIntent(pIntent);
//
//        Notification noti = new NotificationCompat.Builder(mContext)
//                .setSmallIcon(R.drawable.downloading_updates)
//                .setContentTitle("Downloading Completed")
//                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                .setContentText(file1)
//                .setContentIntent(pIntent).build();
//
//        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(ns);
//        notificationManager.notify(NOTIFICATION_ID, noti);
//
//=======================================
////        Intent intent = new Intent();
////        intent.setAction(android.content.Intent.ACTION_VIEW);
//        File file = new File(file1); // set your audio path
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        String ext=file.getName().substring(file.getName().indexOf(".")+1);
//        String type = mime.getMimeTypeFromExtension(ext);
//
//
//        Intent openFile = new Intent(Intent.ACTION_VIEW, Uri.fromFile(file));
//
//        openFile.setDataAndType(Uri.fromFile(file),type);
//
//        PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, openFile, 0);
//
//        Notification noti = new NotificationCompat.Builder(mContext)
//                .setContentTitle("Download completed")
//                .setContentText(file1)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setContentIntent(pIntent).build();
//
//        noti.flags |= Notification.FLAG_NO_CLEAR;
//
//        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(0, noti);
        Intent openFile;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            File apkfile = new File(FileProvider.getUriForFile(mContext,Environment.DIRECTORY_DOWNLOADS,file1),file1);
//            Uri apkUri = FileProvider.getUriForFile(mContext,BuildConfig.APPLICATION_ID+".provider",apkfile);
//            String s = apkUri.toString();
//            Log.d("HERE!!", s);
//            openFile = new Intent(Intent.ACTION_INSTALL_PACKAGE);
//            openFile.setData(apkUri);
//            openFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
////            mContext.startActivity(intent);
//
//        } else {
            File file = new File(file1);
            Log.d("DownloadfilePath", "File to download = " + String.valueOf(file));
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String ext = file.getName().substring(file.getName().indexOf(".") + 1);
            String type = mime.getMimeTypeFromExtension(ext);
            Log.d("type", type);


            openFile = new Intent(Intent.ACTION_VIEW, Uri.fromFile(file));
            openFile.setDataAndType(Uri.fromFile(file), type);
//            mContext.startActivity(openFile);
//        }


//        openFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        mContext.startActivity(openFile);


        PendingIntent p = PendingIntent.getActivity(mContext, 0, openFile, 0);

        Notification noti = new NotificationCompat.Builder(mContext)
                .setContentTitle("Download completed")
                .setContentText(file1)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(p).build();

        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);


    }


}


