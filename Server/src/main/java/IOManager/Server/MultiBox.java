package IOManager.Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultiBox<T> implements Serializable {
    private T mainObject;

    private MultiBox SubBox;

    private int depthOfBoxing = 1;
    public MultiBox(T toPut) {
        mainObject = toPut;
    }

    public String getMainObjectType() {
        return mainObject.getClass().getSimpleName();
    }

    public void addToBox(Object obj){
        if (SubBox == null) SubBox = new MultiBox<>(obj);
        else SubBox.addToBox(obj);
        depthOfBoxing += 1;
    }

    public MultiBox getSubBox(){
        return SubBox;
    }

    public T getMainObject(){
        return mainObject;
    }

    public List<Object> getAllObjects(){
        List<Object> objectList = new ArrayList<>(depthOfBoxing);
        if (SubBox != null) {
            List<MultiBox> boxList = new ArrayList<>(depthOfBoxing-1);
            boxList.add(getSubBox());
            for (int i = 0; i < depthOfBoxing - 2; i++) {
                boxList.add(boxList.get(i).getSubBox());
            }
            objectList.add(getMainObject());
            for (MultiBox box: boxList){
                objectList.add(box.getMainObject());
            }
        }
        else objectList.add(getMainObject());
        return objectList;
    }

    public int getDepthOfBoxing(){
        return depthOfBoxing;
    }
}
