package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(blog,description,dimensions);
        blog.getImageList().add(image);

        // save the blog to the repository
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){

        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count = 0;
        Image image = imageRepository2.findById(id).get();
        String dimension = image.getDimensions();
        String[] str = dimension.split("X");
        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);
        String[] str2 = screenDimensions.split("X");
        int x = Integer.parseInt(str2[0]);
        int y = Integer.parseInt(str2[1]);

        int len1 = x/a;
        int len2 = y/b;

        return len1 * len2;
    }
}
