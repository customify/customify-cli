package customify;

public class UiHandler {

    public String key;

    public void init(){
        System.out.println("........Welcome to customify.........../n/n");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.println("3. More");
        System.out.println("=: ");
    }

    public void setKey(String key){
        this.key = key;

    }
}
