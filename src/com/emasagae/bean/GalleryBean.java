package com.emasagae.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.emasagae.flickr.FlickrResponse;
import com.emasagae.flickr.Photo;
import com.google.gson.Gson;

@ManagedBean (name = "galleryBean")
@ViewScoped
public class GalleryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{userBean}")
    private UserBean userBean;
	
    private List<String> imagesUrl;  
    private String url;
    private int index;
	
	public GalleryBean() {}
    
    @PostConstruct
    public void init() {
    	if(userBean.getReportSelected().getLabel() != null && !userBean.getReportSelected().getLabel().isEmpty()) {
			String line, reply = "";
			try {
				URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&format=json&api_key=48f2342467e41b00b8e55c9896d81629&tags=" + userBean.getReportSelected().getLabel());
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
				while ((line = reader.readLine()) != null) { 
					reply += line; 
				}
			
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			reply = reply.replace("jsonFlickrApi(", "");
			reply = reply.substring(0, reply.length()-1);
			Gson gson = new Gson();
			FlickrResponse fr = gson.fromJson(reply, FlickrResponse.class);
			imagesUrl = new ArrayList<String>();
			Photo p;
			
			for(int i=0;i<fr.getPhotos().getPhoto().size();i++) {
				p = fr.getPhotos().getPhoto().get(i);
				imagesUrl.add("http://farm"+p.getFarm()+".staticflickr.com/"+p.getServer()+"/"+p.getId()+"_"+p.getSecret()+"_z.jpg");
			}
			url = imagesUrl.get(0);
		}
    }

	public String getUrl() {
		return url;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public void next() {
		index = (index + 1) % imagesUrl.size();
		url = imagesUrl.get(index);
    }

    public void previous() {
        if (index == 0) {
        	index = imagesUrl.size() - 1;
        } else {
        	index = (index - 1) % imagesUrl.size();
        }
        url = imagesUrl.get(index);
    }
	
}