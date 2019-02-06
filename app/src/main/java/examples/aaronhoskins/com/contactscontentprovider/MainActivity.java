package examples.aaronhoskins.com.contactscontentprovider;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import examples.aaronhoskins.com.contactscontentprovider.mangers.Contact;
import examples.aaronhoskins.com.contactscontentprovider.mangers.ContactsManager;
import examples.aaronhoskins.com.contactscontentprovider.mangers.PermissionsManager;

public class MainActivity extends AppCompatActivity implements PermissionsManager.IPermissionManager, ContactsManager.IContractManager {
    PermissionsManager permissionsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionsManager = new PermissionsManager(this, this);
        permissionsManager.checkPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.checkResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onPermissionResult(boolean isGranted) {
        Log.d("TAG", "onPermissionResult: YEP" );
        if(isGranted) {
            getContacts();
        } else {
            Toast.makeText(this, "Can not process", Toast.LENGTH_SHORT).show();
        }
    }

    public void getContacts() {
        ContactsManager contactsManager = new ContactsManager(this);
        contactsManager.getContacts();
    }

    @Override
    public void onContactsRecieved(List<Contact> contactsList) {
        for(Contact contact : contactsList) {
            Log.d("TAG", "onContactsRecieved: " + contact.toString());
        }
    }
}
