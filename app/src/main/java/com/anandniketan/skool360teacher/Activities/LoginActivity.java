package com.anandniketan.skool360teacher.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.anandniketan.skool360teacher.AsyncTasks.LoginAsyncTask;
import com.anandniketan.skool360teacher.Models.LoginModel;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.AppConfiguration;
import com.anandniketan.skool360teacher.Utility.Utility;
import com.anandniketan.skool360teacher.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginAsyncTask loginAsyncTask = null;
    private Context mContext;
    private ProgressDialog progressDialog;
    private ArrayList<LoginModel> logindetailModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mContext = this;
        checkUnmPwd();
    }

    public void login(View view) {
        if (Utility.isNetworkConnected(mContext)) {

            if (!binding.UserNameEdt.getText().toString().equalsIgnoreCase("")) {
                if (!binding.PasswordEdt.getText().toString().equalsIgnoreCase("")) {
                    if (binding.PasswordEdt.getText().toString().length() >=3 && binding.PasswordEdt.getText().toString().length() <= 13) {
                        progressDialog = new ProgressDialog(mContext);
                        progressDialog.setMessage("Please wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    HashMap<String, String> params = new HashMap<String, String>();
                                    params.put("UserID", binding.UserNameEdt.getText().toString().trim());
                                    params.put("Password", binding.PasswordEdt.getText().toString().trim());

                                    loginAsyncTask = new LoginAsyncTask(params);
                                    logindetailModels = loginAsyncTask.execute().get();

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            if (logindetailModels.size() > 0) {
//                              TODO: Store result values for future use

                                                if (binding.RememberChk.isChecked()) {
                                                    saveUserNamePwd(binding.UserNameEdt.getText().toString(), binding.PasswordEdt.getText().toString());
                                                }
                                                Utility.setPref(mContext, "StaffID", logindetailModels.get(0).getStaffID());
                                                Utility.setPref(mContext, "Emp_Code", logindetailModels.get(0).getEmp_Code());
                                                Utility.setPref(mContext, "Emp_Name", logindetailModels.get(0).getEmp_Name());
                                                Utility.setPref(mContext, "DepratmentID", logindetailModels.get(0).getDepratmentID());
                                                Utility.setPref(mContext, "DesignationID", logindetailModels.get(0).getDesignationID());
                                                Utility.setPref(mContext, "DeviceId", logindetailModels.get(0).getDeviceId());

                                                Utility.pong(mContext, "Login Successful");
                                                Intent intentDashboard = new Intent(LoginActivity.this, DashBoardActivity.class);
                                                startActivity(intentDashboard);
//                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                                finish();
                                            } else {
                                                Utility.pong(mContext, "Invalid Credentials. Please try again...");
                                            }
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } else {
                        binding.PasswordEdt.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake));
                        Utility.ping(mContext,"Password must be 3 to 13 character");
                    }
                } else {
                    binding.PasswordEdt.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake));
                    Utility.ping(mContext, "Enter Password");
                }
            } else {
                binding.UserNameEdt.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.shake));
                Utility.ping(mContext, "Enter Username");
            }
        } else {
            Utility.ping(mContext, "Network not available");
        }
    }



    public void checkUnmPwd() {
        if (!Utility.getPref(mContext, "unm").equalsIgnoreCase("")) {
            Intent intentDashboard = new Intent(LoginActivity.this, DashBoardActivity.class);
            startActivity(intentDashboard);
            finish();
        }
    }

    public void saveUserNamePwd(String unm, String pwd) {
        Utility.setPref(mContext, "unm", unm);
        Utility.setPref(mContext, "pwd", pwd);
    }
}
