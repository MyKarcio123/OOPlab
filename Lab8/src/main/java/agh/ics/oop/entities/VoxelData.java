package agh.ics.oop.entities;

import org.joml.Vector3f;

public class VoxelData {
    public static Vector3f[] voxelVerts = {
            new Vector3f(0f, 0f, 0f),
            new Vector3f(1f, 0f, 0f),
            new Vector3f(1f, 1f, 0f),
            new Vector3f(0f, 1f, 0f),
            new Vector3f(1f, 0f, 0f),
            new Vector3f(1f, 0f, 1f),
            new Vector3f(1f, 1f, 1f),
            new Vector3f(1f, 1f, 0f),
            new Vector3f(1f, 0f, 1f),
            new Vector3f(0f, 0f, 1f),
            new Vector3f(0f, 1f, 1f),
            new Vector3f(1f, 1f, 1f),
            new Vector3f(0f, 0f, 1f),
            new Vector3f(0f, 0f, 0f),
            new Vector3f(0f, 1f, 0f),
            new Vector3f(0f, 1f, 1f),
            new Vector3f(0f, 1f, 0f),
            new Vector3f(1f, 1f, 0f),
            new Vector3f(1f, 1f, 1f),
            new Vector3f(0f, 1f, 1f),
            new Vector3f(1f, 0f, 0f),
            new Vector3f(0f, 0f, 0f),
            new Vector3f(0f, 0f, 1f),
            new Vector3f(1f, 0f, 1f),
    };
    public static int[][] faces = {
            {0,3,1,1,3,2}, //front
            {4,7,5,5,7,6}, //right
            {8,11,9,9,11,10}, //back
            {12,15,13,13,15,14}, //left
            {16,19,17,17,19,18}, //top
            {20,23,21,21,23,22}, //bottom
    };
}
