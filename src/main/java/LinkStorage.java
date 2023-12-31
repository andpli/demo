import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class LinkStorage {
    public List<ParsedLink> links = new ArrayList<>();
    public LinkStorage(String path) throws IOException {
        addLinks(getContentOfPage(path));
    }

    public List<ParsedLink> getLinks() {
        return links;
    }
    private String getContentOfPage(String pageAddress) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL pageURL = new URL(pageAddress);
        URLConnection uc = pageURL.openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        }
        return sb.toString();
    }

    private void addLinks(String text) {
        String[] s;
        StringBuilder sb = new StringBuilder();
        s = text.split("<a ");
        int lj = 0;
        for (int li = 1; li < s.length; li++ ){
            int liPos = s[li].indexOf("</a");
            if (liPos > 0) {
                String temp = "<a " + s[li].substring(0, liPos + 4); // + "<br>";
                liPos = temp.indexOf("href=");
                String q = temp.substring(liPos + 5, liPos + 6);
                String href = "";
                if (temp.indexOf(q, liPos + 7) > 0) {
                    href = temp.substring(liPos + 6, temp.indexOf(q, liPos + 7 ));
                    lj++;
                    newLink(lj, href, temp.substring(3, temp.length() - 4));
                }
            }
        }
    }
    private void newLink(int lj, String href, String text) {
        links.add(new ParsedLink(lj, href, text));
    }
}
