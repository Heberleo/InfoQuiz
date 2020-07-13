package Database;

public class LoadSaveException extends Exception{
    public LoadSaveException() {super("There was an problem with loading or saving the question"); }
    public LoadSaveException(String s) {super(s);}
}
