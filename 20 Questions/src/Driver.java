import java.io.*;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws IOException {
        BinaryTree<String> bt;

        // load existing object from file and save to bt
        bt = (BinaryTree<String>) readObjectFromFile();

        // if we do not have an existing object, setup an initial question for the tree
        if (bt == null)
            bt = new BinaryTree<>("Find in zoo?", new BinaryTree<>("dog"), new BinaryTree<>("elephant"));

        BinaryTree<String> temp = bt;

        boolean play = true;
        String s;

        Scanner in = new Scanner(System.in);

        // TODO: Add your 20 questions logic here

        while (play) {

            if (play) {

                System.out.println(temp.getData());
                s = in.nextLine();

                if (!temp.isLeaf() && s.equals("Yes")) {
                    temp = temp.getRightSubtree();
                }
                else if (!temp.isLeaf() && s.equals("No")) {
                    temp = temp.getLeftSubtree();
                }
                else if (temp.isLeaf() && s.equals("Yes")){
                    play = false;
                }
                else if (temp.isLeaf() && s.equals("No")){
                    System.out.println("Describe your animal.");
                    String desc = in.nextLine();
                    System.out.println("Name your animal.");
                    String name = in.nextLine();

                    String oldData = temp.getData();
                    temp.setData(desc);
                    temp.setLeftSubtree(new BinaryTree<>(oldData));
                    temp.setRightSubtree(new BinaryTree<>(name));


                    play = false;
                }
            }

            if (!play){
                System.out.println("Play again?");
                s = in.nextLine();
                if (s.equals("Yes")){
                    temp = bt;
                    play = true;
                }
            }

        }

        // once we are finished processing, save object to file
        writeObjectToFile(bt);
    }

    /**
     * Write an object to file
     * @param obj the object to write to the file
     */
    public static void writeObjectToFile(Object obj) {

        try {
            FileOutputStream fileOut = new FileOutputStream("animal.dat");

            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Read an object from file
     * @return the object read from the file
     */
    public static Object readObjectFromFile() {

        try {
            FileInputStream fileIn = new FileInputStream("animal.dat");

            ObjectInputStream objectOut = new ObjectInputStream(fileIn);
            Object obj = objectOut.readObject();
            objectOut.close();
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }
}
