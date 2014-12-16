package yuseok.rxcycleappsandbox.activities;

import java.util.ArrayList;

import yuseok.rxcycleappsandbox.MainActivity;
import yuseok.rxcycleappsandbox.R;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class IntentTest extends Activity implements OnItemClickListener {

	ListView list;
	ArrayAdapter<Integer> adapter;
	ArrayList<Integer> array;
	final int LIST_LIMIT = 30;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_test);

		adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1);
		array = new ArrayList<Integer>();
		for (int i = 0; i < LIST_LIMIT; i++) {
			array.add(i);
		}
		adapter.addAll(array);

		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);

		list.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int item, long itemd) {
		Uri uri;
		Intent it;
		switch (item) {
		case 0:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")));
			break;
		case 1:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:38.899533,-77.036476")));
		case 2:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?f=d&saddr=출발지주소&daddr=도착지주소&hl=ko")));
			break;
		case 3:
			startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:01034585635")));
			break;
		case 4:
			startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01033985869")));
			break;
		case 5:
			it = new Intent(Intent.ACTION_VIEW);   
			it.putExtra("sms_body", "The SMS text");   
			it.setType("vnd.android-dir/mms-sms");   
			startActivity(it);  
			break;
		case 6:
			uri = Uri.parse("smsto:01082587458");
			it = new Intent(Intent.ACTION_SENDTO, uri);
			it.putExtra("sms_body", "엄마 하이");
			startActivity(it);
			break;
		case 7:
			uri = Uri.parse("content://media/external/images/media/23");
			it = new Intent(Intent.ACTION_SEND);
			it.putExtra("sms_body", "text");
			try{
			it.putExtra(Intent.EXTRA_STREAM, uri);
			}catch(Exception e){}
			it.setType("image/png");
			startActivity(it);
			break;
		case 8:
			uri = Uri.parse("mailto:oysu@naver.com");
			it = new Intent(Intent.ACTION_SENDTO, uri);
			startActivity(it);
			break;
		case 9:
			it = new Intent(Intent.ACTION_SEND);
			it.putExtra(Intent.EXTRA_EMAIL, "oysu158@gmail.com");
			it.putExtra(Intent.EXTRA_TEXT, "The email body text");
			it.setType("text/plain");
			startActivity(Intent.createChooser(it, "Choose Email Client"));
			break;
		case 10:
			it = new Intent(Intent.ACTION_SEND);
			String[] tos = {"oysu158@gmail.com"};
			String[] ccs = {"oysu158@hotmail.com"};
			it.putExtra(Intent.EXTRA_EMAIL, tos);
			it.putExtra(Intent.EXTRA_CC, ccs);
			it.putExtra(Intent.EXTRA_TEXT, "The email body text");
			it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
			it.setType("message/rfc822");
			startActivity(Intent.createChooser(it, "Choose Email Client"));
			break;
		case 11:
			it = new Intent(Intent.ACTION_SEND);
			it.putExtra(Intent.EXTRA_SUBJECT, "The email text");
			try{
			it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
			}catch(Exception e){}
			it.setType("audio/mp3");
			startActivity(Intent.createChooser(it, "Choose Email Client"));
			break;
		case 12:
			it = new Intent(Intent.ACTION_VIEW);
			uri = Uri.parse("file:///sdcard/song.mp3");
			it.setDataAndType(uri, "audio/mp3");
			startActivity(it);
			break;
		case 13:
			uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
			it = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(it);
			break;
		case 14:
			it = new Intent();
			it.setAction(Intent.ACTION_WEB_SEARCH);
			it.putExtra(SearchManager.QUERY,"엔하위키미러");
			startActivity(it);
			break;
		default:
			Toast.makeText(this, "논논비요리~", Toast.LENGTH_SHORT).show();
			break;
		}

	}
	 
}