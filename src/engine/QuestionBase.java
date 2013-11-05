package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ben
 * Date: 11/4/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionBase {

    public static List<String> questions = new ArrayList<String>();

    public static void init(){
        //Question should be an Enum
        questions.add("Why did you make that move?");
    }
}
