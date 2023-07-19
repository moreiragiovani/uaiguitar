package com.uaiguitar.site.util;

public class LinkVideoConverter {

    public LinkVideoConverter(){}
    public String converterLinkVideoToIframe(String link){
        String[] stringList = new String[1];

        for(String s : link.split("/")){
            stringList[0] = s;
        }
        String cd = stringList[0];

        String code = cd.substring(8);

        String url = String.format("https://www.youtube.com/embed/%s", code);
        return url;
    }
}
