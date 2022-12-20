package agh.ics.oop;

public class MathHelper {
    public static float InvLerp(float a, float b, float v){
        float value = (v-a)/(b-a);
        return value;
    }
    public static float Lerp(float a, float b, float v){
        float val = (1.0f-v) * a + b * v;
        return Clamp(a,b,val);
    }
    public static float Clamp(float a,float b, float v){
        if(v>b) return b;
        else if(v<a) return a;
        return v;
    }
}
