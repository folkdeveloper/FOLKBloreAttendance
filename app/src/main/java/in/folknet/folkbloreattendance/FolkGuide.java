package in.folknet.folkbloreattendance;import android.app.ProgressDialog;import android.content.Context;import android.content.Intent;import android.os.Bundle;import android.support.v7.app.AppCompatActivity;import android.view.View;import android.view.inputmethod.InputMethodManager;import android.widget.Button;import android.widget.RadioButton;import android.widget.RadioGroup;import android.widget.Toast;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import com.google.firebase.database.ValueEventListener;import java.util.Calendar;public class FolkGuide extends AppCompatActivity {Bundle bundle;    String name,no,ws;    String id="",fg,venue;    DatabaseReference mcount;    RadioButton rb;    int selectedId;    String ds,number, mjp, selected_area;    int icr;    ProgressDialog pd;    Button btn;    RadioGroup rg;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_folk_guide);        rg = (RadioGroup)findViewById(R.id.radioGroup);        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);        imm.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.RESULT_HIDDEN);        bundle = getIntent().getExtras();        btn = (Button)findViewById(R.id.btn);        name = bundle.getString("name");        selected_area = bundle.getString("selected_area");        no = bundle.getString("mobNo");        number = bundle.getString("mnum");        mjp = bundle.getString("mjp");        ws = bundle.getString("ws");        pd = new ProgressDialog(FolkGuide.this);        pd.setTitle("Please Wait...");        pd.setMessage("Make Sure Your Internet Connection Is On.");        pd.show();        pd.setCancelable(false);        mcount = FirebaseDatabase.getInstance().getReference().child("Counter").child("CountE");        ValueEventListener postListener = new ValueEventListener() {            @Override            public void onDataChange(DataSnapshot dataSnapshot) {                // Get Post object and use the values to update the UI                icr = dataSnapshot.getValue(int.class);                pd.hide();            }            @Override            public void onCancelled(DatabaseError databaseError) {}        };        mcount.addValueEventListener(postListener);        btn.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                selectedId = 0;                selectedId = rg.getCheckedRadioButtonId();                rb = (RadioButton)findViewById(selectedId);                if (selectedId == -1) {                    Toast.makeText(getApplicationContext(), "Select FOLK Guide!", Toast.LENGTH_SHORT).show();                }                else{                    ds = rb.getText().toString();                    String dkd = ds;                    ds = sort(ds);                    Toast.makeText(getApplicationContext(), ds, Toast.LENGTH_SHORT).show();                    Calendar calendar = Calendar.getInstance();                    int hours = calendar.get(Calendar.HOUR_OF_DAY);                    int minutes = calendar.get(Calendar.MINUTE);                    String c = color();                    int date = calendar.get(Calendar.DAY_OF_MONTH);                    int month = calendar.get(Calendar.MONTH);                    int year = calendar.get(Calendar.YEAR);                    month = month +1;                    String time = date + "/" + month + "/" + year + "|"+hours+":"+minutes;                String WOS = null;                    switch (ws){                        case "SELF EMPOWERMENT" :                            WOS = "SELF EMP.";                            break;                        case "HAPPINESS WORKSHOP" :                            WOS = "HAPPINESS WS";                            break;                    }                    Toast.makeText(getApplicationContext(), WOS, Toast.LENGTH_SHORT).show();                    String fin = "    FOLK BANGALORE\n **********************\nName : " + name + "\nMOBILE NO. : " + number + "\nFOLK Guide : " + ds + "\nTIME : "+ date +"/"+ month+"|"+ hours + ":" + minutes+ "\nSESSION : "+ws + "\nMeeting : "+venue+"\nCOUPON COLOR : "+c+"\n **********************\n\n\n\n.";                    Intent intent = new Intent(FolkGuide.this, MainActivity.class);                    intent.putExtra("message", fin);                    intent.putExtra("name",name);                    intent.putExtra("mjp",mjp);                    intent.putExtra("fg",ds);                    intent.putExtra("fid",number);                    intent.putExtra("time",time);                    intent.putExtra("ws",ws);                    intent.putExtra("counter",icr);//                    intent.putExtra("Venue",venue);                    String dd = "4";                    String a,b;                    a = time();                    b = fgg(dkd);                    intent.putExtra("timeNumber",a);                    intent.putExtra("fgNumber",b);                    intent.putExtra("year_fb",dd);                    intent.putExtra("selected_area", selected_area);                    Toast.makeText(getApplicationContext(), b, Toast.LENGTH_SHORT).show();                    Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();                    Toast.makeText(getApplicationContext(), dd, Toast.LENGTH_SHORT).show();//                    icr = Integer.parseInt(null);//                    mcount = null;//                    icr = icr + 1;//                    mcount.setValue(icr);                    startActivity(intent);                }}        });    }    String sort(String fguide){        String temp = null;        switch(fguide){            case "VIJAY GAURANGA DASA" :                temp = "VJGD";                venue = "AV Hall";                break;            case "ANANTHA SHAYANA DASA" :                temp = "ATSD";                venue = "2 floor above Radha Krishna Temple";                break;            case "ANIRUD BALARAMA DASA" :                temp = "ANBD";                venue = "Gita Counter Cabin";                break;            case "CHAITANYA CHANDRA DASA" :                temp = "CTCD";                venue = "14 mtrs SE";                break;            case "HEMANGA CAITANYA DASA" :                temp = "HMCD";                venue = "ENGLISH LH";                break;            case "HEMAVARNA GAURA DASA" :                temp = "HVGD";                venue = "3 Mtrs Board Room";                break;            case "INDIRAPATI DASA" :                temp = "INPD";                venue = "Samskara Hall";                break;            case "JIVAN MUKTA DASA" :                temp = "JVMD";                venue = "FOLK Seminar Hall";                break;            case "KAMALA PADA DASA" :                temp = "KMPD";                venue = "ELH";                break;            case "KARUNA KESHAVA DASA" :                temp = "KKSD";                venue = "MTH";                break;            case "KRISHNA MADHAVA DASA" :                temp = "KRMD";                venue = "In His CABIN";                break;            case "LAKSHMINATHA DASA" :                temp = "LMND";                venue = "MVT";                break;            case "PANDURANGA GOPALA DASA" :                temp = "PDRD";                venue = "MVT";                break;            case "KESHAVA BALARAMA DASA" :                temp = "KBRD";                venue = "14 Mtrs West";                break;            case "SHRESHTA RUPA DASA" :                temp = "SRRD";                venue = "3Mr. Cabin";                break;            case "SRI VIGRAHA DASA" :                temp = "SRID";                venue = "SP Office";                break;            case "VAINATEYA DASA" :                temp = "VNTD";                venue = "14 Mtrs North";                break;            case "FOLK CIRCLE" :                temp = "FOLK Circle";                venue = "";                break;            case "VRAJESHWARA GOVINDA DASA" :                temp = "VGND";                venue = "14m SW";                break;            case "NOT IN TOUCH WITH ANYONE" :                temp = "--";                venue = "";                break;        }        return temp;    }    String color(){        String col = null;        Calendar calendar = Calendar.getInstance();        int hours = calendar.get(Calendar.HOUR_OF_DAY);        int minutes = calendar.get(Calendar.MINUTE);        int seconds = calendar.get(Calendar.SECOND);        if ((hours == 15) || (hours == 16 && minutes <= 15)){            col = "GREEN";        }        else if ((hours == 16 && minutes > 15) && (hours == 16 && minutes <= 25)){            col = "PINK";        }        else if ((hours == 16 && minutes > 25) && (hours == 16 && minutes <= 35)){            col = "YELLOW";        }        else {            col = "WHITE";        }        return col;    }    String time(){        String co = null;        Calendar calendar = Calendar.getInstance();        int hours = calendar.get(Calendar.HOUR_OF_DAY);        int minutes = calendar.get(Calendar.MINUTE);        int seconds = calendar.get(Calendar.SECOND);        if ((hours == 15) || (hours == 16 && minutes <= 15)){            co = "1";        }        else if ((hours == 16 && minutes > 15) && (hours == 16 && minutes <= 25)){            co = "2";        }        else if ((hours == 16 && minutes > 25) && (hours == 16 && minutes <= 35)){            co = "3";        }        else {            co = "4";        }        return co;    }    String ffgg(String fguide){        String Num;        switch(fguide){            case "ANBD" :                Num = "1";                break;            case "ATSD" :                Num = "20";                break;            case "CTCD" :                Num = "2";                break;            case "HMCD" :                Num = "3";                break;            case "INPD" :                Num = "4";                break;            case "JVMD" :                Num = "5";                break;            case "KKSD" :                Num = "6";                break;            case "KMPD" :                Num = "7";                break;            case "LMND" :                Num = "8";                break;            case "KBRD" :                Num = "9";                break;            case "SRID" :                Num = "10";                break;            case "SRRD" :                Num = "11";                break;            case "VGND" :                Num = "12";                break;            case "VJGD" :                Num = "13";                break;            case "VNTD" :                Num = "14";                break;            case "KRMD" :                Num = "16";                break;            case "HVGD" :            Num = "17";            break;            default:                Num = "15";                break;        }        return Num;    }    String fgg(String fguide){        String temp = null;        switch(fguide){            case "VIJAY GAURANGA DASA" :                temp = "13";                break;            case "ANANTHA SHAYANA DASA" :                temp = "20";                break;            case "ANIRUD BALARAMA DASA" :                temp = "1";                break;            case "CHAITANYA CHANDRA DASA" :                temp = "2";                break;            case "HEMANGA CAITANYA DASA" :                temp = "3";                break;            case "HEMAVARNA GAURA DASA" :                temp = "17";                break;            case "INDIRAPATI DASA" :                temp = "4";                break;            case "JIVAN MUKTA DASA" :                temp = "5";                break;            case "KAMALA PADA DASA" :                temp = "7";                break;            case "KARUNA KESHAVA DASA" :                temp = "6";                break;            case "KRISHNA MADHAVA DASA" :                temp = "16";                break;            case "LAKSHMINATHA DASA" :                temp = "8";                break;            case "PANDURANGA GOPALA DASA" :                temp = "18";                break;            case "KESHAVA BALARAMA DASA" :                temp = "9";                break;            case "SHRESHTA RUPA DASA" :                temp = "11";                break;            case "SRI VIGRAHA DASA" :                temp = "10";                break;            case "VAINATEYA DASA" :                temp = "14";                break;            case "VRAJESHWARA GOVINDA DASA" :                temp = "12";                break;            case "FOLK CIRCLE" :                temp = "19";                break;            case "NOT IN TOUCH WITH ANYONE" :                temp = "15";                break;        }        return temp;    }}