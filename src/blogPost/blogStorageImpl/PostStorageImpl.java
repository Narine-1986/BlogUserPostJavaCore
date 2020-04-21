package blogPost.blogStorageImpl;

import blogPost.Interface.PostStorage;
import blogPost.exception.ModelNotFoundException;
import blogPost.models.Post;

public class PostStorageImpl implements PostStorage {

    private Post[] posts;
    private int size = 0;

    public PostStorageImpl(int length){
        posts=new Post[length];
    }
    public PostStorageImpl(){
        posts=new Post[16];
    }


    public void add(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Post[] tmp = new Post[posts.length + 10];
        System.arraycopy(posts, 0, tmp, 0, posts.length);
        posts = tmp;
    }


    public Post getPostByTitle(String title) throws ModelNotFoundException {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equals(title)) {
                return posts[i];
            }
        }
        throw new ModelNotFoundException("Don't found that title");

    }


    public void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)) {
                System.out.println(posts[i]);
            }
        }
    }

    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getCategory().equals(category)) {
                System.out.println(posts[i]);
            }

        }
    }

    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }
    }
}
