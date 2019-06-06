package com.webakruti.designpractice.PDF;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;
import com.webakruti.designpractice.R;

import java.io.File;
import java.util.List;

public class PdfActivity extends AppCompatActivity implements View.OnClickListener, OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener {

    private static final String TAG = PdfActivity.class.getSimpleName();

    private EditText editTextChoosePancard;
    private RelativeLayout relativeLayoutPANUpload;
    private PDFView pdfView;
    private TextView textViewWord;

    String displayName = null;
    String path;
    Uri uri;
    File myFile;

    Integer pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        editTextChoosePancard = (EditText)findViewById(R.id.editTextChoosePancard);
        relativeLayoutPANUpload = (RelativeLayout)findViewById(R.id.relativeLayoutPANUpload);
        pdfView = (PDFView)findViewById(R.id.pdfView);
        textViewWord = (TextView)findViewById(R.id.textViewWord);


        relativeLayoutPANUpload.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.relativeLayoutPANUpload :
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //intent = intent.setType("application/pdf");
                if (intent == intent.setType("application/pdf")) {
                    startActivityForResult(intent, 100);
                    pdfView.setVisibility(View.VISIBLE);
                    textViewWord.setVisibility(View.INVISIBLE);
                }else if (intent == intent.setType("application/msword")) // || intent == intent.setType("application/docx"))
                {
                    startActivityForResult(intent, 200);
                    pdfView.setVisibility(View.INVISIBLE);
                    textViewWord.setVisibility(View.VISIBLE);
                }
                else{
                    startActivityForResult(intent, 200);
                    pdfView.setVisibility(View.INVISIBLE);
                    textViewWord.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // Get the Uri of the selected file
            uri = data.getData();
            String uriString = uri.toString();
            myFile = new File(uriString);
            path = myFile.getAbsolutePath();


            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                        editTextChoosePancard.setText(displayName);

                    }
                } finally {
                    cursor.close();
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();
                editTextChoosePancard.setText(displayName);

            }


        }

        switch (requestCode) {

            case 100:

                pdfView.fromUri(uri)
                        .defaultPage(pageNumber)
                        .onPageChange(this)
                        .enableAnnotationRendering(true)
                        .onLoad(this)
                        .scrollHandle(new DefaultScrollHandle(this))
                        .spacing(10) // in dp
                        .onPageError(this)
                        .load();
                break;
            case 200:

                if(myFile.exists())
                {
                    if (uri.toString().startsWith("content://")) {
                        Cursor cursor = null;
                        try {
                            cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null && cursor.moveToFirst()) {
                                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                                textViewWord.setText(displayName);

                            }
                        } finally {
                            cursor.close();
                        }
                    } else if (uri.toString().startsWith("file://")) {
                        displayName = myFile.getName();
                        textViewWord.setText(displayName);

                    }
                }


                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", displayName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageError(int page, Throwable t) {
        Log.e(TAG, "Cannot load page " + page);
    }
}
