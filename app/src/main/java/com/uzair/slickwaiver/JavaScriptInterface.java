package com.uzair.slickwaiver;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class JavaScriptInterface {
    private Context context;
    public JavaScriptInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void getBase64FromBlobData(String base64Data ) throws IOException {
        convertBase64StringToImageAndStoreIt(base64Data);
    }
    public static String getBase64StringFromBlobUrl(String blobUrl) {
        if(blobUrl.startsWith("blob")){
            return "javascript: var xhr = new XMLHttpRequest();" +
                    "xhr.open('GET', '"+ blobUrl +"', true);" +
                    "xhr.setRequestHeader('Content-type','application/pdf');" +
                    "xhr.responseType = 'blob';" +
                    "xhr.onload = function(e) {" +
                    "    if (this.status == 200) {" +
                    "        var blobImage = this.response;" +
                    "        var reader = new FileReader();" +
                    "        reader.readAsDataURL(blobImage);" +
                    "        reader.onloadend = function() {" +
                    "            base64data = reader.result;" +
                    "            Android.getBase64FromBlobData(base64data);" +
                    "        }" +
                    "    }" +
                    "};" +
                    "xhr.send();";
        }
        return "javascript: console.log('It is not a Blob URL');";
    }
    private void convertBase64StringToImageAndStoreIt(String base64PDf) throws IOException {
        Random random = new Random();
        int number =  random.nextInt(100000);
        String fileName = "SlickWaiver"+number+".pdf";

        final File dwldsPath = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS) + "/"+fileName);
        byte[] pdfAsBytes = Base64.decode(base64PDf.replaceFirst("^data:application/pdf;base64,", ""), 0);
        FileOutputStream os;
        os = new FileOutputStream(dwldsPath, false);
        os.write(pdfAsBytes);
        os.flush();
        Toast.makeText(context, "FILE DOWNLOADED! "+dwldsPath, Toast.LENGTH_SHORT).show();
    }
}
