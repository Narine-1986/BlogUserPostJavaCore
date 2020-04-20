package blogPost.Interface;

import blogPost.exception.ModelNotFoundException;
import blogPost.models.Post;

public interface PostStorage {

    void add(Post post);

    Post getPostByTitle(String title) throws ModelNotFoundException;

    void searchPostsByKeyword(String keyword);

    void printAllPosts();

    void printPostsByCategory(String category);

}
