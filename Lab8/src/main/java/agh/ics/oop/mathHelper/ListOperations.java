package agh.ics.oop.mathHelper;

import java.util.List;

public class ListOperations {
    public static float[] toFloatArray(List<Float> list){
        float[] array = new float[list.size()];
        for(int i=0;i<list.size();++i){
            array[i]=list.get(i);
        }
        return array;
    }
}
