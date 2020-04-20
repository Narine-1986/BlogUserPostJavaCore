package blogPost;

import blogPost.Interface.Commands;
import blogPost.blogStorageImpl.PostStorageImpl;
import blogPost.blogStorageImpl.UserStorageImpl;
import blogPost.exception.ModelNotFoundException;
import blogPost.models.Post;
import blogPost.models.User;


import java.util.Date;
import java.util.Scanner;

public class BlogTest implements Commands {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final PostStorageImpl POST_STORAGEIMPL = new PostStorageImpl();
    public static final UserStorageImpl USER_STORAGEIMPL = new UserStorageImpl();
    public static User logUser = null;

    public static void main(String[] args)  {

        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
                System.out.println("Input number please");
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                case SEARCH_POST:
                    search();
                    break;
                case POSTS_BY_CATEGORY:
                    printByCategory();
                    break;
                case POST_BY_TITLE:
                    getPostByTitle();
                case ALL_POSTS:
                    POST_STORAGEIMPL.printAllPosts();
                    break;
                default:
                    System.out.println("Wrong command!");
            }

        }
    }

    private static void register() {
        System.out.println("Please input name,surname,email,password");
        try {
            String userStr = SCANNER.nextLine();
            String[] userData = userStr.split(",");
            User user = new User();
            user.setName(userData[0]);
            user.setSurname(userData[1]);
            user.setEmail(userData[2]);
            user.setPassword(userData[3]);
            USER_STORAGEIMPL.add(user);
            System.out.println("You are registered");
        } catch (Exception e) {
            System.out.println("Please input again");
            register();
        }
    }

    private static void login() {
        System.out.println("Please input email, password");
        String userStr = SCANNER.nextLine();
        String[] userData = userStr.split(",");
        try {
            logUser = USER_STORAGEIMPL.getUserByEmailAndPassword(userData[0], userData[1]);
            if (USER_STORAGEIMPL.isEmpty()) {
                register();
            } else {
                UserComm();
            }
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printByCategory() {
        System.out.println("input the category");
        String category = SCANNER.nextLine();
        POST_STORAGEIMPL.printPostsByCategory(category);

    }

    private static void search() {
        System.out.println("input the search word for the post");
        String keyword = SCANNER.nextLine();
        POST_STORAGEIMPL.searchPostsByKeyword(keyword);

    }

    private static void getPostByTitle() {
        System.out.println("input the title for the post");
        try {
            String title = SCANNER.nextLine();
            POST_STORAGEIMPL.getPostByTitle(title);
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());

        }

    }

    private static void UserComm() {
        boolean isRun = true;
        while (isRun) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
                System.out.println("Input number please");
            }
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    break;
                case ADD_POST:
                    addPost();
                    break;
                default:
                    System.out.println("Wrong command!!!");
            }


        }
    }

    private static void addPost() {
        System.out.println("Please input Post data: title, text, category, createdDate");
        Post post = new Post();
        try {
            String PostDataStr = SCANNER.nextLine();
            String[] PostData = PostDataStr.split(",");
            post.setTitle(PostData[0]);
            post.setText(PostData[1]);
            post.setCategory(PostData[2]);
            post.setCreatedDate(new Date());
            POST_STORAGEIMPL.add(post);
            System.out.println("Post was added!");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid data");
            addPost();
        }
    }

}




