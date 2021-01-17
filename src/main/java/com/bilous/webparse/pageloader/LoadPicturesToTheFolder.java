package com.bilous.webparse.pageloader;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.w3c.dom.NodeList;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadPicturesToTheFolder {

    private File destDir;
    private String url;

    public LoadPicturesToTheFolder(File destDir, String url) {
        this.destDir = destDir;
        this.url = url;
    }

    public void loadPicturesToTheFolder() {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage(url);
            NodeList imgLinks = page.getElementsByTagName("img");
            for (int i = 0; i < imgLinks.getLength(); i++) {
                loadPictureToTheFolder(i + 1, );
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void loadPictureToTheFolder(int taskNumber, String url) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOS = new FileOutputStream(destDir + "/" + taskNumber + ".png")) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}
